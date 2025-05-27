package engSoft.paleteriaBrasil.controllers;

import engSoft.paleteriaBrasil.entities.Produto;
import engSoft.paleteriaBrasil.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    
}
