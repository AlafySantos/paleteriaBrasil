package engSoft.paleteriaBrasil.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import engSoft.paleteriaBrasil.entities.Fornecedor;
import engSoft.paleteriaBrasil.repositories.FornecedorRepository;
import engSoft.paleteriaBrasil.services.FornecedorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
@RestController
public class FornecedorController {
    private FornecedorRepository fornecedorRepository;
    private FornecedorService fornecedorService;

    @PostMapping(path = "/inserir", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public void inserir(@RequestBody String dados) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Fornecedor fornecedor = mapper.readValue(dados, Fornecedor.class);
        System.out.println("Dados recebidos para inserção: " + fornecedor);
        FonecedorService.inserir(fornecedor);
    }









}
