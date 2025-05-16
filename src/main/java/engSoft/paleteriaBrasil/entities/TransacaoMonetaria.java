package engSoft.paleteriaBrasil.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "transacao_monetaria")
public class TransacaoMonetaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transacao")
    private Integer id;

    @Column(length = 11)
    private String data;

    @Column
    private Float valor;

    @Column(name = "forma_pagamento", length = 255)
    private String formaPagamento;

    @Column
    private Integer quant;


    /*  --- RELACOES --- */
    @OneToOne
    @JoinColumn(name = "fk_estoque_id_estoque", nullable = false)
    private Estoque estoque;

    @ManyToOne
    @JoinColumn(name = "fk_produto_id_produto")
    private Produto produto;


    /*  --- CONSTRUTORES --- */
    public TransacaoMonetaria() { }
    public TransacaoMonetaria(String data, Float valor, String formaPagamento, Integer quant, Estoque estoque, Produto produto) {
        this.data = data;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
        this.quant = quant;
        this.estoque = estoque;
        this.produto = produto;
    }


    /*  --- GETTERS E SETTERS --- */
    public Integer getId() {
        return id;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public Float getValor() {
        return valor;
    }
    public void setValor(Float valor) {
        this.valor = valor;
    }
    public String getFormaPagamento() {
        return formaPagamento;
    }
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    public Integer getQuant() {
        return quant;
    }
    public void setQuant(Integer quant) {
        this.quant = quant;
    }
    public Estoque getEstoque() {
        return estoque;
    }
    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}