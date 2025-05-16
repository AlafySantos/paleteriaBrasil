package engSoft.paleteriaBrasil.services;

import engSoft.paleteriaBrasil.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FonecedorService {

    @Autowired
    FornecedorRepository fornecedorRepository;

}
