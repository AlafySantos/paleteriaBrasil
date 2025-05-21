package engSoft.paleteriaBrasil.entities;

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

    @ManyToOne
    @JoinColumn(name = "fk_estoque_id_estoque", nullable = false)
    private Estoque estoque;

    @ManyToOne
    @JoinColumn(name = "fk_transacao_monetaria_id_transacao")
    private TransacaoMonetaria transacaoMonetaria;


    /*  --- CONSTRUTORES --- */
    public Produto() { }
    // todo construtor all args

}