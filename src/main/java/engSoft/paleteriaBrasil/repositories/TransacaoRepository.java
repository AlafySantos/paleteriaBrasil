package engSoft.paleteriaBrasil.repositories;

import engSoft.paleteriaBrasil.entities.TransacaoMonetaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<TransacaoMonetaria, Integer> {

    @Query("SELECT t FROM TransacaoMonetaria t " +
            "JOIN FETCH t.registros r " +
            "JOIN FETCH r.estoque e " +
            "JOIN FETCH e.produto " +
            "WHERE t.data = :data")
    List<TransacaoMonetaria> findByDataWithProdutos(@Param("data") String data);
}
