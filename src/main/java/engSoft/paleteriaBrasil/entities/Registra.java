package engSoft.paleteriaBrasil.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "registra")
public class Registra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_transacao_monetaria_id_transacao")
    private TransacaoMonetaria transacao;

    @ManyToOne
    @JoinColumn(name = "fk_estoque_id_estoque")
    private Estoque estoque;

    @Column
    private Integer quantidadeSaida;
}
