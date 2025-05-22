package engSoft.paleteriaBrasil.DTO;

public record FornecedorRequestDTO(Integer idFornecedor) {
    public Integer getIdFornecedor() {
        return this.idFornecedor;
    }
}

