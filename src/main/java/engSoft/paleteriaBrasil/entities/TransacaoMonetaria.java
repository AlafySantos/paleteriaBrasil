package engSoft.paleteriaBrasil.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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

    @Column(name = "forma_pagamento", length = 20)
    private String formaPagamento;

    @Column
    private Integer quant;


    /*  --- RELACOES --- */
    @OneToMany(mappedBy = "transacaoMonetaria")
    private List<Produto> produtos;

    @ManyToOne
    @JoinColumn(name = "fk_estoque_id_estoque")
    private Estoque estoque;


    /*  --- CONSTRUTORES --- */
    public TransacaoMonetaria() { }
    // todo construtor all args

}