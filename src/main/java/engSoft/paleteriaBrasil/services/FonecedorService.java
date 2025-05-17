package engSoft.paleteriaBrasil.services;

import engSoft.paleteriaBrasil.entities.Fornecedor;
import engSoft.paleteriaBrasil.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FonecedorService {

    @Autowired
    FornecedorRepository fornecedorRepository;

    // CREATE
    public void inserir(Fornecedor fornecedor) {
        fornecedorRepository.save(fornecedor);
    }

    // READ
    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }
    public Optional<Fornecedor> buscarPorId(Integer id) {
        return fornecedorRepository.findById(id);
    }

    // UPDATE
    public void alterar(Fornecedor fornecedor) {
        fornecedorRepository.save(fornecedor);
    }

    // DELETE
    public void removerPorId(Integer id) {
        fornecedorRepository.deleteById(id);
    }

}
