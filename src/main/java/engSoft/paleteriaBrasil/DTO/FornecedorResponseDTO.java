package engSoft.paleteriaBrasil.DTO;

import engSoft.paleteriaBrasil.entities.Fornecedor;

public record FornecedorResponseDTO(Integer idFornecedor, String telefone, String cnpj, String nome_fornecedor) {

}
