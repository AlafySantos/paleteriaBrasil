package engSoft.paleteriaBrasil.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoPorTipoDTO {
    private Integer id;
    private String nomeProd;
    private String tipoProduto;
    private String subtipoProduto;

    public void ProdutoPorTipoDTO(Integer id, String nomeProd, String tipoProduto, String subtipoProduto) {
        this.id = id;
        this.nomeProd = nomeProd;
        this.tipoProduto = tipoProduto;
        this.subtipoProduto = subtipoProduto;

    }

}
