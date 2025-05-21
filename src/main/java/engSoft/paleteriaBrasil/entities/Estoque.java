package engSoft.paleteriaBrasil.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estoque")
    private Integer id;

    @Column(name = "quant_produto")
    private Integer quantProduto;

    @Column(name = "data_ent", length = 11)
    private String dataEnt;

    @Column(name = "data_saida", length = 11)
    private String dataSaida;

    @Column(name = "nome_prod", length = 100)
    private String nomeProd;

    @Column(name = "validade_prod", length = 11)
    private String validadeProd;

    @Column(name = "tipo_prod", length = 20)
    private String tipoProd;


    /*  --- RELACOES --- */
   @OneToMany(mappedBy = "estoque")
   private List<Produto> produtos;

   @OneToMany(mappedBy = "estoque")
   private List<TransacaoMonetaria> transacoes;


    /*  --- CONSTRUTORES --- */
    public Estoque() { }
    // todo construtor all args

}