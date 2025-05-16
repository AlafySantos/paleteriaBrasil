package engSoft.paleteriaBrasil.repositories;

import engSoft.paleteriaBrasil.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
