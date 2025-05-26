package engSoft.paleteriaBrasil.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fornecedor")
    private Integer id;

    @Column(length = 15)
    private String telefone;

    @Column(length = 18)
    private String cnpj;

    @Column(length = 80)
    private String nome_fornecedor;


    /*  --- RELACOES --- */
    @OneToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Produto> produtos;


    /*  --- CONSTRUTORES --- */
    public Fornecedor() { }
    public Fornecedor(String telefone, String cnpj, String nome_fornecedor) {
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.nome_fornecedor = nome_fornecedor;
    }

}