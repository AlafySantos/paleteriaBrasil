package engSoft.paleteriaBrasil.repositories;

import engSoft.paleteriaBrasil.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findProdutoByTipoProduto(String tipoProduto);
    List<Produto> findProdutoBySubtipoProduto(String subtipoProduto);
}
