package engSoft.paleteriaBrasil.controllers;


import engSoft.paleteriaBrasil.entities.Fornecedor;
import engSoft.paleteriaBrasil.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {
    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping("/listar")
    public List<Fornecedor> listar(){
        return fornecedorService.listarTodos();
    }

    @PostMapping("/inserir")
    public void inserir(@RequestBody Fornecedor fornecedor) {
        System.out.println("Dados recebidos para inserção: " + fornecedor);
        fornecedorService.inserir(fornecedor);
    }
    @DeleteMapping("/remover/{id}")
    public void remover(@PathVariable("id") Integer id) throws IOException {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        fornecedorService.removerPorId(id);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Fornecedor> alterar(@PathVariable Integer id, @RequestBody Fornecedor fornecedorAtualizado){
        Fornecedor fornecedor = fornecedorService.alterarPorID(id, fornecedorAtualizado);
        return ResponseEntity.ok(fornecedor);
    }
}
