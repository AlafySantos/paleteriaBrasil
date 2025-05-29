package engSoft.paleteriaBrasil.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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
    //TODO: TIRAR ESSE ATRIBUTO ADICIONAR NA TABELA REGISTRA
    @Column(name = "data_saida", length = 11)
    private String dataSaida;

    //TODO: RETIRAR ESSE ATRIBUTO O MESMO JA ESTA CONTIDO NO PRODUTO ASSOCIADO
    @Column(name = "nome_prod", length = 100)
    private String nomeProd;

    @Column(name = "validade_prod", length = 11)
    private String validadeProd;

    //TODO: RETIRAR ESSE ATRIBUTO O MESMO JA ESTA CONTIDO NO PRODUTO ASSOCIADO
    @Column(name = "tipo_prod", length = 20)
    private String tipoProd;

    @Column(name = "status_prod", length = 20)
    private String statusProd;


    /*  --- RELACOES --- */
   @ManyToOne
   @JoinColumn(name = "fk_produto_id_produto", nullable = false)
   private Produto produto;

    @ManyToMany(mappedBy = "estoques")
    @JsonIgnore
    private Set<TransacaoMonetaria> transacoesMonetarias = new HashSet<>();


    /*  --- CONSTRUTORES --- */
    public Estoque() { }
    public Estoque(Integer quantProduto, String dataEnt, String dataSaida, String nomeProd,
                   String validadeProd, String tipoProd, String statusProd, Produto produto) {
        this.quantProduto = quantProduto;
        this.dataEnt = dataEnt;
        this.dataSaida = dataSaida;
        this.nomeProd = nomeProd;
        this.validadeProd = validadeProd;
        this.tipoProd = tipoProd;
        this.statusProd = statusProd;
        this.produto = produto;
    }

}