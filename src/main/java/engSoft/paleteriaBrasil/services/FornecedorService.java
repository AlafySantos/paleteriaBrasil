package engSoft.paleteriaBrasil.services;

import engSoft.paleteriaBrasil.DTO.FornecedorDTO;
import engSoft.paleteriaBrasil.entities.Fornecedor;
import engSoft.paleteriaBrasil.repositories.FornecedorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }
    // CREATE
    public void inserir(Fornecedor fornecedor){
        fornecedorRepository.save(fornecedor);
    }

    // READ
    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    //Listar Fornecedor Resumido ID + NOME
    public List<FornecedorDTO> listarFornecedores() {
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();

        // Converte a lista de entidade para lista de DTO
        return fornecedores.stream()
                .map(f -> new FornecedorDTO(f.getId(), f.getNome_fornecedor()))
                .collect(Collectors.toList());
    }

    //READ POR ID
    public Optional<Fornecedor> buscarPorId(Integer id) {
        return fornecedorRepository.findById(id);
    }

    // DELETE
    public void removerPorId(Integer id) {
        fornecedorRepository.deleteById(id);
    }

    // UPDATE
    public void alterar(Fornecedor fornecedor) {
        fornecedorRepository.save(fornecedor);
    }

    // UPDATE POR ID
    public Fornecedor alterarPorID(Integer id, Fornecedor fornecedorAtualizado) {
        Fornecedor fornecedorExistente = fornecedorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado com id: " + id));

        // Dados de forncedor que devem ser alterados
        fornecedorExistente.setNome_fornecedor(fornecedorAtualizado.getNome_fornecedor());
        fornecedorExistente.setCnpj(fornecedorAtualizado.getCnpj());
        fornecedorExistente.setTelefone(fornecedorAtualizado.getTelefone());

        //altera forncedor no banco com novos dados.
        return fornecedorRepository.save(fornecedorExistente);
    }
    //Função para Tratar Exeption Alterar Por Id.
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
