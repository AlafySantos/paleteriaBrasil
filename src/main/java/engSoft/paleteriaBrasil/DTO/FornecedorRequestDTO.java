package engSoft.paleteriaBrasil.DTO;

public record FornecedorRequestDTO(Integer idFornecedor, String telefone, String cnpj, String nomeFornecedor) {
    public Integer getIdFornecedor() {
        return this.idFornecedor;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getNomeFornecedor() {
        return this.nomeFornecedor;
    }
}

