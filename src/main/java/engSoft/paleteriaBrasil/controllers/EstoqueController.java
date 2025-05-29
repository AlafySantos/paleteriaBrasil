package engSoft.paleteriaBrasil.controllers;


import engSoft.paleteriaBrasil.DTO.EstoqueCreateDTO;
import engSoft.paleteriaBrasil.DTO.NovaVendaDTO;
import engSoft.paleteriaBrasil.entities.Estoque;
import engSoft.paleteriaBrasil.services.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @GetMapping("/listar")
    public List<Estoque> listar(){
        return estoqueService.listarTodos();
    }

    @PostMapping("/inserir")
    public ResponseEntity<Estoque> inserir(@RequestBody EstoqueCreateDTO dto) {
        Estoque novoEstoque = estoqueService.inserir(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEstoque);
    }

    @DeleteMapping("/remover/{id}")
    public void remover(@PathVariable("id") Integer id) throws IOException {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        estoqueService.removerPorId(id);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Estoque> alterar(@PathVariable Integer id, @RequestBody Estoque estoqueAtualizado){
        Estoque estoque = estoqueService.alterarPorID(id, estoqueAtualizado);
        return ResponseEntity.ok(estoque);
    }

    @PostMapping("/novaVenda")
    public ResponseEntity<String> novaVenda(@RequestBody NovaVendaDTO vendaDTO) {
        try {
            estoqueService.novaVenda(vendaDTO);
            return ResponseEntity.ok("Venda realizada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro na venda: " + e.getMessage());
        }
    }





}
