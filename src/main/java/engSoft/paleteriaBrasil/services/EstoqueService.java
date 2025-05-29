package engSoft.paleteriaBrasil.services;

import engSoft.paleteriaBrasil.DTO.NovaVendaDTO;
import engSoft.paleteriaBrasil.entities.Estoque;
import engSoft.paleteriaBrasil.entities.Produto;
import engSoft.paleteriaBrasil.entities.TransacaoMonetaria;
import engSoft.paleteriaBrasil.repositories.EstoqueRepository;
import engSoft.paleteriaBrasil.repositories.TransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    EstoqueRepository estoqueRepository;
    @Autowired
    TransacaoRepository transacaoRepository;

    // CREATE
    public void inserir(Estoque estoque) {
        estoqueRepository.save(estoque);
    }

    // READ
    public List<Estoque> listarTodos() {
        return estoqueRepository.findAll();
    }
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
        estoqueExistente.setDataSaida(estoqueAtualizado.getDataSaida());
        estoqueExistente.setNomeProd(estoqueAtualizado.getNomeProd());
        estoqueExistente.setQuantProduto(estoqueAtualizado.getQuantProduto());
        estoqueExistente.setStatusProd(estoqueAtualizado.getStatusProd());
        estoqueExistente.setTipoProd(estoqueAtualizado.getTipoProd());
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

//    @Transactional
//    public void novaVenda(NovaVendaDTO vendaDTO) {
//        TransacaoMonetaria transacao = new TransacaoMonetaria(
//                vendaDTO.getDataVenda(),
//                vendaDTO.getValorTotal(),
//                vendaDTO.getFormaPagamento(),
//                vendaDTO.getQuantidades().stream().mapToInt(Integer::intValue).sum()  // soma total de itens vendidos
//        );
//
//        for (int i = 0; i < vendaDTO.getIdsEstoque().size(); i++) {
//            Integer idEstoque = vendaDTO.getIdsEstoque().get(i);
//            Integer quantidadeVendida = vendaDTO.getQuantidades().get(i);
//
//            Estoque estoque = estoqueRepository.findById(idEstoque)
//                    .orElseThrow(() -> new RuntimeException("Estoque não encontrado: ID " + idEstoque));
//
//            if (estoque.getQuantProduto() < quantidadeVendida) {
//                throw new RuntimeException("Estoque insuficiente para o produto: " + estoque.getNomeProd());
//            }
//
//            estoque.setQuantProduto(estoque.getQuantProduto() - quantidadeVendida);
//            estoqueRepository.save(estoque);  // atualiza estoque
//
//            transacao.getEstoques().add(estoque);  // associa à transação
//        }
//
//        transacaoRepository.save(transacao);  // persiste transação e atualiza tabela 'registra'
//    }

    @Transactional
    public void novaVenda(NovaVendaDTO vendaDTO) {
        TransacaoMonetaria transacao = new TransacaoMonetaria(
                vendaDTO.getDataVenda(),
                vendaDTO.getValorTotal(),
                vendaDTO.getFormaPagamento(),
                vendaDTO.getQuantidades().stream().mapToInt(Integer::intValue).sum()
        );

        for (int i = 0; i < vendaDTO.getIdsEstoque().size(); i++) {
            Integer idEstoque = vendaDTO.getIdsEstoque().get(i);
            Integer quantidadeVendida = vendaDTO.getQuantidades().get(i);

            Estoque estoque = estoqueRepository.findById(idEstoque)
                    .orElseThrow(() -> new RuntimeException("Estoque não encontrado: ID " + idEstoque));

            if (estoque.getQuantProduto() < quantidadeVendida) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + estoque.getNomeProd());
            }

            // Decrementa a quantidade
            estoque.setQuantProduto(estoque.getQuantProduto() - quantidadeVendida);

            // Se quantidade zerar, marca como indisponível
            if (estoque.getQuantProduto() == 0) {
                estoque.setStatusProd("indisponivel");
            }

            estoqueRepository.save(estoque);
            transacao.getEstoques().add(estoque);
        }

        transacaoRepository.save(transacao);
    }

}
