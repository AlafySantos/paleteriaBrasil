package engSoft.paleteriaBrasil.services;

import engSoft.paleteriaBrasil.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

}
