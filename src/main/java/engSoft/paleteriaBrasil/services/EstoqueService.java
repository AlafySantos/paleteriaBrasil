package engSoft.paleteriaBrasil.services;

import engSoft.paleteriaBrasil.DTO.EstoqueCreateDTO;
import engSoft.paleteriaBrasil.DTO.NovaVendaDTO;
import engSoft.paleteriaBrasil.entities.*;
import engSoft.paleteriaBrasil.repositories.EstoqueRepository;
import engSoft.paleteriaBrasil.repositories.ProdutoRepository;
import engSoft.paleteriaBrasil.repositories.RegistraRepository;
import engSoft.paleteriaBrasil.repositories.TransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    private RegistraRepository registraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Estoque inserir(EstoqueCreateDTO dto) {
        Estoque estoque = new Estoque();

        estoque.setQuantProduto(dto.getQuantProduto());

        //Formata a data de entrada para "dd/MM/yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = LocalDate.now().format(formatter);
        estoque.setDataEnt(dataFormatada);
        //TODO: COMO MELHORIA FUTURA COLOCAR DATA AUTOMATICA EM PRODUTOS PRODUZIDOS PELA PALETERIA BRASIL
        estoque.setValidadeProd(dto.getValidadeProd());

        estoque.setStatusProd(dto.getStatusProd());

        // Associa produto
        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: ID " + dto.getProdutoId()));
        estoque.setProduto(produto);

        return estoqueRepository.save(estoque);
    }

    // READ
    public List<Estoque> listarTodos() {
        return estoqueRepository.findAll();
    }
    // READ POR ID
    public Optional<Estoque> buscarPorId(Integer id) {
        return estoqueRepository.findById(id);
    }

    // DELETE
    public void removerPorId(Integer id) {
        estoqueRepository.deleteById(id);
    }

    // UPDATE
    public void alterar(Estoque estoque) {
        estoqueRepository.save(estoque);
    }

    // UPDATE POR ID
    public Estoque alterarPorID(Integer id, Estoque estoqueAtualizado) {
        Estoque estoqueExistente = estoqueRepository.findById(id)
                .orElseThrow(() -> new EstoqueService.ResourceNotFoundException("Estoque não encontrado com id: " + id));

        // Dados de produto que devem ser alterados
        estoqueExistente.setDataEnt(estoqueAtualizado.getDataEnt());
        estoqueExistente.setQuantProduto(estoqueAtualizado.getQuantProduto());
        estoqueExistente.setStatusProd(estoqueAtualizado.getStatusProd());
        estoqueExistente.setValidadeProd(estoqueAtualizado.getValidadeProd());

        //altera Produto no banco com novos dados.
        return estoqueRepository.save(estoqueExistente);
    }
    //Função para Tratar Exeption Alterar Por Id.
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    @Transactional
    public void novaVenda(NovaVendaDTO vendaDTO) {
        // ✅ Data atual como String: "dd/MM/yyyy"
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataAtual = LocalDateTime.now().format(dateFormatter);

        // ✅ Hora atual como java.sql.Time
        LocalDateTime agora = LocalDateTime.now();
        Time horaAtual = Time.valueOf(agora.toLocalTime());

        // ✅ Valor total da quantidade de saída
        int totalQuantidade = vendaDTO.getQuantidades().stream().mapToInt(Integer::intValue).sum();

        // ✅ Valor total da compra
        float valorTotalCompra = 0;

        // ✅ Calcular o valor total da compra
        for (int i = 0; i < vendaDTO.getIdsEstoque().size(); i++) {
            Integer idEstoque = vendaDTO.getIdsEstoque().get(i);
            Integer quantidadeVendida = vendaDTO.getQuantidades().get(i);

            Estoque estoque = estoqueRepository.findById(idEstoque)
                    .orElseThrow(() -> new RuntimeException("Estoque não encontrado: ID " + idEstoque));

            Produto produto = estoque.getProduto();
            double precoUnitario = produto.getValorProd();

            valorTotalCompra += precoUnitario * quantidadeVendida;
        }

        // ✅ Criar TransacaoMonetaria com data, hora, valor total e quantidade
        TransacaoMonetaria transacao = new TransacaoMonetaria();
        transacao.setData(dataAtual);
        transacao.setHora(horaAtual);
        transacao.setValor(valorTotalCompra);
        transacao.setFormaPagamento(vendaDTO.getFormaPagamento());
        transacao.setQuantTotal(totalQuantidade);

        transacaoRepository.save(transacao); // Primeiro salva para gerar o ID

        // ✅ Atualiza Estoques e cria registros
        for (int i = 0; i < vendaDTO.getIdsEstoque().size(); i++) {
            Integer idEstoque = vendaDTO.getIdsEstoque().get(i);
            Integer quantidadeVendida = vendaDTO.getQuantidades().get(i);

            Estoque estoque = estoqueRepository.findById(idEstoque)
                    .orElseThrow(() -> new RuntimeException("Estoque não encontrado: ID " + idEstoque));

            if (estoque.getQuantProduto() < quantidadeVendida) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + estoque.getId());
            }

            // ✅ Decrementa a quantidade
            estoque.setQuantProduto(estoque.getQuantProduto() - quantidadeVendida);

            if (estoque.getQuantProduto() == 0) {
                estoque.setStatusProd("indisponivel");
            }

            estoqueRepository.save(estoque);

            // ✅ Criar a entidade intermediária Registra
            Registra registra = new Registra();

            RegistraId registraId = new RegistraId();
            registraId.setTransacaoId(transacao.getId());
            registraId.setEstoqueId(estoque.getId());

            registra.setId(registraId);
            registra.setTransacao(transacao);
            registra.setEstoque(estoque);
            registra.setQuantidadeSaida(quantidadeVendida);

            registraRepository.save(registra);
        }
    }



}
