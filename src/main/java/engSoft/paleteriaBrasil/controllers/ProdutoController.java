package engSoft.paleteriaBrasil.controllers;

import engSoft.paleteriaBrasil.DTO.ProdutoPorTipoDTO;
import engSoft.paleteriaBrasil.entities.Produto;
import engSoft.paleteriaBrasil.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listar")
    public List<Produto> listar(){
        return produtoService.listarTodos();
    }

    @GetMapping("/listar/{tipoProduto}")
    public List<ProdutoPorTipoDTO> listarPorTipoProduto(@PathVariable String tipoProduto) {
        return produtoService.listarPorTipoProduto(tipoProduto);
    }


    @PostMapping("/inserir")
    public void inserir(@RequestBody Produto produto) {
        System.out.println("Dados recebidos para inserção: " + produto);
        produtoService.inserir(produto);
    }

    @DeleteMapping("/remover/{id}")
    public void remover(@PathVariable("id") Integer id) throws IOException {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        produtoService.removerPorId(id);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Produto> alterar(@PathVariable Integer id, @RequestBody Produto produtoAtualizado){
        Produto produto = produtoService.alterarPorID(id, produtoAtualizado);
        return ResponseEntity.ok(produto);
    }
}
