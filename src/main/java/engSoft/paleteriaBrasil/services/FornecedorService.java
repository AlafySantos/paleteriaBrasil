package engSoft.paleteriaBrasil.services;

import engSoft.paleteriaBrasil.entities.Fornecedor;
import engSoft.paleteriaBrasil.repositories.FornecedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }
    // CREATE
    public void inserirFornecedor(Fornecedor fornecedor){
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
    public void alterarFornecedor(Fornecedor fornecedor) {
        fornecedorRepository.save(fornecedor);
    }

    // DELETE
    public void removerFornecedorId(Integer id) {
        fornecedorRepository.deleteById(id);
    }

}
