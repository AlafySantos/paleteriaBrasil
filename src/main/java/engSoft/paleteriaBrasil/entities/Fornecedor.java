package engSoft.paleteriaBrasil.entities;

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

}