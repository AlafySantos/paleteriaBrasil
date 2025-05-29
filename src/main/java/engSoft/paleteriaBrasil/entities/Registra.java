package engSoft.paleteriaBrasil.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "registra")
public class Registra {
    @EmbeddedId
    private RegistraId id = new RegistraId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("transacaoId")
    @JoinColumn(name = "fk_transacao_monetaria_id_transacao")
    @JsonIgnore
    private TransacaoMonetaria transacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("estoqueId")
    @JoinColumn(name = "fk_estoque_id_estoque")
    @JsonIgnore
    private Estoque estoque;

    @Column(name = "quantidade_saida")
    private Integer quantidadeSaida;

    public Registra() { }

    public Registra(TransacaoMonetaria transacao, Estoque estoque, Integer quantidadeSaida) {
        this.transacao = transacao;
        this.estoque = estoque;
        this.quantidadeSaida = quantidadeSaida;
        this.id = new RegistraId(transacao.getId(), estoque.getId());
    }
}
