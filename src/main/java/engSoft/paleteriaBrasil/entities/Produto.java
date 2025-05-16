package engSoft.paleteriaBrasil.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Integer id;

    @Column(name = "nome_prod", length = 20)
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
    private List<Estoque> estoques;

    @OneToMany(mappedBy = "produto")
    private List<TransacaoMonetaria> transacoesMonetarias;


    /*  --- CONSTRUTORES --- */
    public Produto() { }
    public Produto(String nomeProd, String tipoProduto, Float valorProd, Fornecedor fornecedor) {
        this.nomeProd = nomeProd;
        this.tipoProduto = tipoProduto;
        this.valorProd = valorProd;
        this.fornecedor = fornecedor;
    }


    /*  --- GETTERS E SETTERS --- */
    public Integer getId() {
        return id;
    }
    public String getNomeProd() {
        return nomeProd;
    }
    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }
    public String getTipoProduto() {
        return tipoProduto;
    }
    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
    public Float getValorProd() {
        return valorProd;
    }
    public void setValorProd(Float valorProd) {
        this.valorProd = valorProd;
    }
    public Fornecedor getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    public List<Estoque> getEstoques() {
        return estoques;
    }
    public void setEstoques(List<Estoque> estoques) {
        this.estoques = estoques;
    }
    public List<TransacaoMonetaria> getTransacoesMonetarias() {
        return transacoesMonetarias;
    }
    public void setTransacoesMonetarias(List<TransacaoMonetaria> transacoesMonetarias) {
        this.transacoesMonetarias = transacoesMonetarias;
    }

}