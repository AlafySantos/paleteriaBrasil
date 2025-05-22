package engSoft.paleteriaBrasil.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import engSoft.paleteriaBrasil.entities.Fornecedor;
import engSoft.paleteriaBrasil.repositories.FornecedorRepository;
import engSoft.paleteriaBrasil.services.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

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

}
