package engSoft.paleteriaBrasil.services;

import engSoft.paleteriaBrasil.DTO.ProdutoPorTipoDTO;
import engSoft.paleteriaBrasil.DTO.ProdutoSubtipoDTO;
import engSoft.paleteriaBrasil.entities.Produto;
import engSoft.paleteriaBrasil.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // READ POR TIPO PRODUTO.
    public List<ProdutoPorTipoDTO> listarPorTipoProduto(String tipoProduto) {
        List<Produto> produtos = produtoRepository.findProdutoByTipoProduto(tipoProduto);

        // Converter lista de Produto para ProdutoPorTipoDTO
        return produtos.stream()
                .map(produto -> {
                    ProdutoPorTipoDTO dto = new ProdutoPorTipoDTO();
                    dto.setId(produto.getId());
                    dto.setNomeProd(produto.getNomeProd());
                    dto.setTipoProduto(produto.getTipoProduto());
                    dto.setSubtipoProduto(produto.getSubtipoProduto());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    // READ - PARA RETORNAR SUBTIPO RECEBENDO TIPO
    public List<ProdutoSubtipoDTO> listarSubtipoPorTipo(String tipoProduto) {
        List<Produto> produtos = produtoRepository.findProdutoByTipoProduto(tipoProduto);

        // Converter lista de Produto para ProdutoSubtipoDTO
        return produtos.stream()
                .map(produto -> {
                    ProdutoSubtipoDTO dto = new ProdutoSubtipoDTO();
                    dto.setSubtipoProduto(produto.getSubtipoProduto());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // DELETE
    public void removerPorId(Integer id) {
        produtoRepository.deleteById(id);
    }

    // UPDATE
    public void alterar(Produto produto) {
        produtoRepository.save(produto);
    }

    // UPDATE POR ID
    public Produto alterarPorID(Integer id, Produto produtoAtualizado) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoService.ResourceNotFoundException("Produto não encontrado com id: " + id));

        // Dados de produto que devem ser alterados
        produtoExistente.setNomeProd(produtoAtualizado.getNomeProd());
        produtoExistente.setTipoProduto(produtoAtualizado.getTipoProduto());
        produtoExistente.setSubtipoProduto(produtoAtualizado.getSubtipoProduto());
        produtoExistente.setValorProd(produtoAtualizado.getValorProd());
        produtoExistente.setFornecedor(produtoAtualizado.getFornecedor());

        //altera Produto no banco com novos dados.
        return produtoRepository.save(produtoExistente);
    }
    //Função para Tratar Exeption Alterar Por Id.
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }




}
