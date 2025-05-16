package engSoft.paleteriaBrasil.services;

import engSoft.paleteriaBrasil.repositories.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    @Autowired
    EstoqueRepository estoqueRepository;

}
