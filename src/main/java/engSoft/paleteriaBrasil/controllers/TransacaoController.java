package engSoft.paleteriaBrasil.controllers;

import engSoft.paleteriaBrasil.entities.TransacaoMonetaria;
import engSoft.paleteriaBrasil.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
