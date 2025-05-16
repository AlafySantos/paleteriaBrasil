package engSoft.paleteriaBrasil.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fornecedor")
    private Integer id;

    @Column(length = 11)
    private String telefone;

    @Column(length = 14)
    private String cnpj;

    @Column(name = "nome_fornecedor", length = 80)
    private String nomeFornecedor;


    /*  --- RELACOES --- */
    @OneToMany(mappedBy = "fornecedor")
    private List<Produto> produtos;


    /*  --- CONSTRUTORES --- */
    public Fornecedor() { }
    public Fornecedor(String telefone, String cnpj, String nomeFornecedor) {
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.nomeFornecedor = nomeFornecedor;
    }


    /*  --- GETTERS E SETTERS --- */
    public Integer getId() {
        return id;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getNomeFornecedor() {
        return nomeFornecedor;
    }
    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }
    public List<Produto> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}