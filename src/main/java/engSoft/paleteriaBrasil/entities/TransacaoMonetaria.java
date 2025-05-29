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
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(
            name = "registra",
            joinColumns = @JoinColumn(name = "fk_transacao_monetaria_id_transacao"),
            inverseJoinColumns = @JoinColumn(name = "fk_estoque_id_estoque")
    )
    private Set<Estoque> estoques = new HashSet<>();


    /*  --- CONSTRUTORES --- */
    public TransacaoMonetaria() { }
    public TransacaoMonetaria(String data, Float valor, String formaPagamento, Integer quant) {
        this.data = data;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
        this.quant = quant;
    }
}