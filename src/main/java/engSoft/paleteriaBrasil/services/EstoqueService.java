package engSoft.paleteriaBrasil.services;

import engSoft.paleteriaBrasil.entities.Estoque;
import engSoft.paleteriaBrasil.entities.Produto;
import engSoft.paleteriaBrasil.repositories.EstoqueRepository;
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


}
