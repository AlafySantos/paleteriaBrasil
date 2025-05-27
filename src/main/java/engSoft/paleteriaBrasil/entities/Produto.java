package engSoft.paleteriaBrasil.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Integer id;

    @Column(name = "nome_prod", length = 100)
    private String nomeProd;

    @Column(name = "tipo_produto", length = 20)
    private String tipoProduto;

    @Column(name = "valor_prod")
    private Float valorProd;


    /*  --- RELACOES --- */
    @ManyToOne
    @JoinColumn(name = "fk_fornecedor_id_fornecedor", nullable = false)
    private Fornecedor fornecedor;

    @OneToMany(mappedBy = "produto")
    @JsonIgnore
    private List<Estoque> estoques;


    /*  --- CONSTRUTORES --- */
    public Produto() { }
    public Produto(String nomeProd, String tipoProduto, Float valorProd, Fornecedor fornecedor) {
        this.nomeProd = nomeProd;
        this.tipoProduto = tipoProduto;
        this.valorProd = valorProd;
        this.fornecedor = fornecedor;
    }


}