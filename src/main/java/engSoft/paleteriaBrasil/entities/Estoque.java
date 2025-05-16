package engSoft.paleteriaBrasil.entities;

import jakarta.persistence.*;

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
    @ManyToOne
    @JoinColumn(name = "fk_produto_id_produto", nullable = false)
    private Produto produto;

    @OneToOne(mappedBy = "estoque")
    private TransacaoMonetaria transacaoMonetaria;


    /*  --- CONSTRUTORES --- */
    public Estoque() { }
    public Estoque(Integer quantProduto, String dataEnt, String dataSaida, String nomeProd, String validadeProd, String tipoProd, Produto produto) {
        this.quantProduto = quantProduto;
        this.dataEnt = dataEnt;
        this.dataSaida = dataSaida;
        this.nomeProd = nomeProd;
        this.validadeProd = validadeProd;
        this.tipoProd = tipoProd;
        this.produto = produto;
    }


    /*  --- GETTERS E SETTERS --- */
    public Integer getId() {
        return id;
    }
    public Integer getQuantProduto() {
        return quantProduto;
    }
    public void setQuantProduto(Integer quantProduto) {
        this.quantProduto = quantProduto;
    }
    public String getDataEnt() {
        return dataEnt;
    }
    public void setDataEnt(String dataEnt) {
        this.dataEnt = dataEnt;
    }
    public String getDataSaida() {
        return dataSaida;
    }
    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }
    public String getNomeProd() {
        return nomeProd;
    }
    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }
    public String getValidadeProd() {
        return validadeProd;
    }
    public void setValidadeProd(String validadeProd) {
        this.validadeProd = validadeProd;
    }
    public String getTipoProd() {
        return tipoProd;
    }
    public void setTipoProd(String tipoProd) {
        this.tipoProd = tipoProd;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public TransacaoMonetaria getTransacaoMonetaria() {
        return transacaoMonetaria;
    }
    public void setTransacaoMonetaria(TransacaoMonetaria transacaoMonetaria) {
        this.transacaoMonetaria = transacaoMonetaria;
    }

}