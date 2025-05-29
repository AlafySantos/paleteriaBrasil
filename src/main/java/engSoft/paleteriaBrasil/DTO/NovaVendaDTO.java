package engSoft.paleteriaBrasil.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NovaVendaDTO {
    private List<Integer> idsEstoque;      // IDs dos estoques vendidos
    private List<Integer> quantidades;     // Quantidades respectivas de venda
    private String dataVenda;              // Data da venda
    private Float valorTotal;              // Valor total da venda
    private String formaPagamento;         // Ex: "Dinheiro", "Cartão"

}
