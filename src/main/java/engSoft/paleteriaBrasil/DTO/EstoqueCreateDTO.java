package engSoft.paleteriaBrasil.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstoqueCreateDTO {
    private Integer quantProduto;
    private String validadeProd;  // Recebe como String e converte para LocalDate
    private String statusProd;
    private Integer produtoId;  // Apenas o ID do produto

}
