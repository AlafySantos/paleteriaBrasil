package engSoft.paleteriaBrasil.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Embeddable
public class RegistraId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer transacaoId;
    private Integer estoqueId;

    public RegistraId() { }
}
