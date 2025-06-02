package engSoft.paleteriaBrasil.services;

import engSoft.paleteriaBrasil.DTO.EstoqueResumoDTO;
import engSoft.paleteriaBrasil.DTO.TransacaoCompletaDTO;
import engSoft.paleteriaBrasil.DTO.TransacaoProdutoDTO;
import engSoft.paleteriaBrasil.entities.TransacaoMonetaria;
import engSoft.paleteriaBrasil.repositories.EstoqueRepository;
import engSoft.paleteriaBrasil.repositories.TransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;


    // CREATE
    public void inserir(TransacaoMonetaria transacao) {
        transacaoRepository.save(transacao);
    }

    // READ
    public List<TransacaoMonetaria> listarTodos() {
        return transacaoRepository.findAll();
    }

    public Optional<TransacaoMonetaria> buscarPorId(Integer id) {
        return transacaoRepository.findById(id);
    }

    // DELETE
    public void removerPorId(Integer id) {
        transacaoRepository.deleteById(id);
    }
    // UPDATE
    //public void alterar(TransacaoMonetaria transacao) {transacaoRepository.save(transacao);}

    //TODO: Rever com  a estrutura nova de automatização de valores pode precisar de ajustes
    // UPDATE POR ID
    public TransacaoMonetaria alterarPorID(Integer id, TransacaoMonetaria transacaoAtualizado) {
        TransacaoMonetaria transacaoExistente = transacaoRepository.findById(id)
                .orElseThrow(() -> new TransacaoService.ResourceNotFoundException("Transação não encontrado com id: " + id));

        // Dados de produto que devem ser alterados
        transacaoExistente.setData(transacaoAtualizado.getData());
        transacaoExistente.setFormaPagamento(transacaoAtualizado.getFormaPagamento());
        transacaoExistente.setQuantTotal(transacaoAtualizado.getQuantTotal());
        transacaoExistente.setValor(transacaoAtualizado.getValor());


        //altera Produto no banco com novos dados.
        return transacaoRepository.save(transacaoExistente);
    }
    //Função para Tratar Exeption Alterar Por Id.
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    //Função para construir tabela Historico do Dia
    @Transactional
    public List<TransacaoProdutoDTO> listarVendasDoDia() {
        LocalDate hoje = LocalDate.now();
        String dataFormatada = hoje.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        List<TransacaoMonetaria> transacoes = transacaoRepository.findByDataWithProdutos(dataFormatada);

        return transacoes.stream()
                .flatMap(transacao -> transacao.getRegistros().stream().map(registro -> {
                    TransacaoProdutoDTO dto = new TransacaoProdutoDTO();
                    dto.setId(transacao.getId());
                    dto.setData(transacao.getData());
                    dto.setHora(transacao.getHora());
                    dto.setValor(transacao.getValor());
                    dto.setFormaPagamento(transacao.getFormaPagamento());


                    dto.setIdEstoque(registro.getEstoque().getId());
                    dto.setNomeProd(registro.getEstoque().getProduto().getNomeProd());
                    dto.setTipoProduto(registro.getEstoque().getProduto().getTipoProduto());
                    dto.setSubtipoProduto(registro.getEstoque().getProduto().getSubtipoProduto());
                    dto.setQuantProduto(registro.getQuantidadeSaida());

                    // Pega valor unitário do Produto
                    float valorUnitario = registro.getEstoque().getProduto().getValorProd();

                    // Multiplica pela quantidade saída
                    float valorTotal = valorUnitario * registro.getQuantidadeSaida();

                    dto.setValorUnitario(valorUnitario);
                    dto.setValorTotal(valorTotal);

                    return dto;
                }))
                .collect(Collectors.toList());
    }

    //Função para construir tabela Historico do Dia
    @Transactional
    public List<TransacaoProdutoDTO> listarPorData(String data) {

        if (!StringUtils.hasText(data)) {
            throw new IllegalArgumentException("O parâmetro 'data' é obrigatório e não pode ser nulo ou vazio.");
        }

        try {
            DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter formatoSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate dataConvertida = LocalDate.parse(data, formatoEntrada);
            String dataFormatada = dataConvertida.format(formatoSaida);

            List<TransacaoMonetaria> transacoes = transacaoRepository.findByDataWithProdutos(dataFormatada);

            return transacoes.stream()
                    .flatMap(transacao -> transacao.getRegistros().stream().map(registro -> {
                        TransacaoProdutoDTO dto = new TransacaoProdutoDTO();
                        dto.setId(transacao.getId());
                        dto.setData(transacao.getData());
                        dto.setHora(transacao.getHora());
                        dto.setValor(transacao.getValor());
                        dto.setFormaPagamento(transacao.getFormaPagamento());

                        dto.setIdEstoque(registro.getEstoque().getId());
                        dto.setNomeProd(registro.getEstoque().getProduto().getNomeProd());
                        dto.setTipoProduto(registro.getEstoque().getProduto().getTipoProduto());
                        dto.setSubtipoProduto(registro.getEstoque().getProduto().getSubtipoProduto());
                        dto.setQuantProduto(registro.getQuantidadeSaida());

                        float valorUnitario = registro.getEstoque().getProduto().getValorProd();
                        float valorTotal = valorUnitario * registro.getQuantidadeSaida();

                        dto.setValorUnitario(valorUnitario);
                        dto.setValorTotal(valorTotal);

                        return dto;
                    }))
                    .collect(Collectors.toList());

        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Formato de data inválido. O formato correto é dd-MM-yyyy.", ex);
        }
    }

    @Transactional
    public List<TransacaoCompletaDTO> listarVendas() {
        LocalDate hoje = LocalDate.now();
        String dataFormatada = hoje.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        List<TransacaoMonetaria> transacoes = transacaoRepository.findByDataWithProdutos(dataFormatada);

        return transacoes.stream().map(transacao -> {
            List<EstoqueResumoDTO> estoques = transacao.getRegistros().stream()
                    .map(registra -> new EstoqueResumoDTO(
                            registra.getEstoque().getId(),
                            registra.getEstoque().getProduto().getNomeProd(),
                            registra.getQuantidadeSaida()
                    ))
                    .collect(Collectors.toList());

            return new TransacaoCompletaDTO(
                    transacao.getId(),
                    transacao.getData(),
                    transacao.getHora(),
                    transacao.getValor(),
                    transacao.getFormaPagamento(),
                    transacao.getQuantTotal(),
                    estoques
            );
        }).collect(Collectors.toList());
    }



}
