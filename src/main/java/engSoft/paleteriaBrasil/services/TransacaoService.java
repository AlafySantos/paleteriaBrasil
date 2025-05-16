package engSoft.paleteriaBrasil.services;

import engSoft.paleteriaBrasil.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;
}
