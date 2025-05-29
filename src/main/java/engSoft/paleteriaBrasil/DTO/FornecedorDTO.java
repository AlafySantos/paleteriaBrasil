package engSoft.paleteriaBrasil.DTO;

public class FornecedorDTO {
    private Integer id;
    private String nome_fornecedor;

    public FornecedorDTO(Integer id, String nome_fornecedor) {
        this.id = id;
        this.nome_fornecedor = nome_fornecedor;
    }

    // getters e setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome_fornecedor() {
        return nome_fornecedor;
    }
    public void setNome_fornecedor(String nome_fornecedor) {
        this.nome_fornecedor = nome_fornecedor;
    }
}
