package engSoft.paleteriaBrasil.repositories;

import engSoft.paleteriaBrasil.entities.TransacaoMonetaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<TransacaoMonetaria, Integer> {

    List<TransacaoMonetaria> findByData(String data);
}
