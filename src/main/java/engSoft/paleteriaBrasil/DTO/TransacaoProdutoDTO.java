package engSoft.paleteriaBrasil.DTO;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class TransacaoProdutoDTO {
    private Integer id;
    private String data;
    private Time hora;
    private Float valor;
    private String formaPagamento;

    private Integer idEstoque;
    private String nomeProd;
    private String tipoProduto;
    private String subtipoProduto;
    private Integer quantProduto;
    private Float valorUnitario;
    private Float valorTotal;

}
