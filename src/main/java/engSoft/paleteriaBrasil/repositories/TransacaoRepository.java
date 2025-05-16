package engSoft.paleteriaBrasil.repositories;

import engSoft.paleteriaBrasil.entities.TransacaoMonetaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<TransacaoMonetaria, Integer> {
}
