package engSoft.paleteriaBrasil.controllers;

import engSoft.paleteriaBrasil.DTO.TransacaoCompletaDTO;
import engSoft.paleteriaBrasil.DTO.TransacaoProdutoDTO;
import engSoft.paleteriaBrasil.entities.TransacaoMonetaria;
import engSoft.paleteriaBrasil.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping("/listar")
    public List<TransacaoMonetaria> listar(){
        return transacaoService.listarTodos();
    }
    @PostMapping("/inserir")
    public void inserir(@RequestBody TransacaoMonetaria transacao) {
        System.out.println("Dados recebidos para inserção: " + transacao);
        transacaoService.inserir(transacao);
    }

    @DeleteMapping("/remover/{id}")
    public void remover(@PathVariable("id") Integer id) throws IOException {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        transacaoService.removerPorId(id);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<TransacaoMonetaria> alterar(@PathVariable Integer id, @RequestBody TransacaoMonetaria transacaoAtualizado){
        TransacaoMonetaria transacao = transacaoService.alterarPorID(id, transacaoAtualizado);
        return ResponseEntity.ok(transacao);
    }

    @GetMapping("/vendasDoDia")
    public List<TransacaoProdutoDTO> listarVendasDoDia() {
        return transacaoService.listarVendasDoDia();
    }
    @GetMapping("/listarVendas")
    public List<TransacaoCompletaDTO> listarVendas() {
        return transacaoService.listarVendas();
    }
}