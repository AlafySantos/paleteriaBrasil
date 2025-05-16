package engSoft.paleteriaBrasil.repositories;

import engSoft.paleteriaBrasil.entities.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
}
