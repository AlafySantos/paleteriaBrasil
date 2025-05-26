package engSoft.paleteriaBrasil.controllers;


import engSoft.paleteriaBrasil.entities.Fornecedor;
import engSoft.paleteriaBrasil.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {
    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping("/inserirFornecedor")
    public void inserirFornecedor(@RequestBody Fornecedor fornecedor) {
        System.out.println("Dados recebidos para inserção: " + fornecedor);
        fornecedorService.inserirFornecedor(fornecedor);
    }
    @DeleteMapping("/removerFornecedor/{id}")
    public void removerFornecedor(@PathVariable("id") Integer id) throws IOException {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        fornecedorService.removerFornecedorId(id);
    }

    @PutMapping("/alterarFornecedor/{id}")
    public ResponseEntity<Fornecedor> alterarFornecedor(@PathVariable Integer id, @RequestBody Fornecedor fornecedorAtualizado){
        Fornecedor fornecedor = fornecedorService.alterarFornecedorByID(id, fornecedorAtualizado);
        return ResponseEntity.ok(fornecedor);
    }
}
