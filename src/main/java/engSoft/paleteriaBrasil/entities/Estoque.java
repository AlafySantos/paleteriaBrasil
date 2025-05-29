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

    @Column(name = "validade_prod", length = 11)
    private String validadeProd;

    @Column(name = "status_prod", length = 20)
    private String statusProd;


    /*  --- RELACOES --- */
   @ManyToOne
   @JoinColumn(name = "fk_produto_id_produto", nullable = false)
   private Produto produto;

    @OneToMany(mappedBy = "estoque", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Registra> registros = new HashSet<>();

    /*  --- CONSTRUTORES --- */
    public Estoque() { }
    public Estoque(Integer quantProduto, String dataEnt, String dataSaida, String nomeProd,
                   String validadeProd, String tipoProd, String statusProd, Produto produto) {
        this.quantProduto = quantProduto;
        this.dataEnt = dataEnt;
        this.validadeProd = validadeProd;
        this.statusProd = statusProd;
        this.produto = produto;
    }
    public Estoque(Integer id, Integer quantProduto) {
        this.id= id;
        this.quantProduto = quantProduto;
    }

}