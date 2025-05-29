package engSoft.paleteriaBrasil.DTO;

public class EstoqueResumoDTO {
    private Integer id;
    private String nomeProd;
    private Integer quantProduto; // <-- NOVO CAMPO

    public EstoqueResumoDTO(Integer id, String nomeProd, Integer quantProduto) {
        this.id = id;
        this.nomeProd = nomeProd;
        this.quantProduto = quantProduto;
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNomeProd() { return nomeProd; }
    public void setNomeProd(String nomeProd) { this.nomeProd = nomeProd; }

    public Integer getQuantProduto() { return quantProduto; }
    public void setQuantProduto(Integer quantProduto) { this.quantProduto = quantProduto; }

}
