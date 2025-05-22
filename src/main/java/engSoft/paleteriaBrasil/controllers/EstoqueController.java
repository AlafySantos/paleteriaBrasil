package engSoft.paleteriaBrasil.controllers;


import engSoft.paleteriaBrasil.entities.Estoque;
import engSoft.paleteriaBrasil.repositories.EstoqueRepository;
import engSoft.paleteriaBrasil.services.EstoqueService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@RestController
public class EstoqueController {
    private EstoqueService estoqueService;
    private EstoqueRepository estoqueRepository;


}
