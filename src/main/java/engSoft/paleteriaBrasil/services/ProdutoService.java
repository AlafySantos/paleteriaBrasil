package engSoft.paleteriaBrasil.services;

import engSoft.paleteriaBrasil.entities.Produto;
import engSoft.paleteriaBrasil.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    // CREATE
    public void inserir(Produto produto) {
        produtoRepository.save(produto);
    }

    // READ
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }
    public Optional<Produto> buscarPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    // UPDATE
    public void alterar(Produto produto) {
        produtoRepository.save(produto);
    }

    // DELETE
    public void removerPorId(Integer id) {
        produtoRepository.deleteById(id);
    }

}
