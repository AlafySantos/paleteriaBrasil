package engSoft.paleteriaBrasil.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoPorSubtipoDTO {
    private Integer id;
    private String nomeProd;
    private String tipoProduto;
    private String subtipoProduto;
}
