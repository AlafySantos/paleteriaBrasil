package engSoft.paleteriaBrasil.services;

import engSoft.paleteriaBrasil.entities.Estoque;
import engSoft.paleteriaBrasil.repositories.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    EstoqueRepository estoqueRepository;

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

    // UPDATE
    public void alterar(Estoque estoque) {
        estoqueRepository.save(estoque);
    }

    // DELETE
    public void removerPorId(Integer id) {
        estoqueRepository.deleteById(id);
    }

}
