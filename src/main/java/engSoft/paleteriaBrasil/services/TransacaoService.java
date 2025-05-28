package engSoft.paleteriaBrasil.services;

import engSoft.paleteriaBrasil.entities.Estoque;
import engSoft.paleteriaBrasil.entities.TransacaoMonetaria;
import engSoft.paleteriaBrasil.repositories.EstoqueRepository;
import engSoft.paleteriaBrasil.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    public void associarEstoque(Integer transacaoId, Integer estoqueId) {
        TransacaoMonetaria transacao = transacaoRepository.findById(transacaoId)
                .orElseThrow(() -> new RuntimeException("Transacao não encontrada"));

        Estoque estoque = estoqueRepository.findById(estoqueId)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        transacao.getEstoques().add(estoque);
        transacaoRepository.save(transacao);
    }


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

    // UPDATE
    public void alterar(TransacaoMonetaria transacao) {
        transacaoRepository.save(transacao);
    }

    // DELETE
    public void removerPorId(Integer id) {
        transacaoRepository.deleteById(id);
    }

}
