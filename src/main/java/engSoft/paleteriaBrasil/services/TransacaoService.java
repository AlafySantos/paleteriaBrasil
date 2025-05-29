package engSoft.paleteriaBrasil.services;

import engSoft.paleteriaBrasil.entities.TransacaoMonetaria;
import engSoft.paleteriaBrasil.repositories.EstoqueRepository;
import engSoft.paleteriaBrasil.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;


    // CREATE
    public void inserir(TransacaoMonetaria transacao) {
        transacaoRepository.save(transacao);
    }

    // READ
    public List<TransacaoMonetaria> listarTodos() {
        return transacaoRepository.findAll();
    }

    public Optional<TransacaoMonetaria> buscarPorId(Integer id) {
        return transacaoRepository.findById(id);
    }

    // DELETE
    public void removerPorId(Integer id) {
        transacaoRepository.deleteById(id);
    }
    // UPDATE
    //public void alterar(TransacaoMonetaria transacao) {transacaoRepository.save(transacao);}

    //TODO: Rever com  a estrutura nova de automatização de valores pode precisar de ajustes
    // UPDATE POR ID
    public TransacaoMonetaria alterarPorID(Integer id, TransacaoMonetaria transacaoAtualizado) {
        TransacaoMonetaria transacaoExistente = transacaoRepository.findById(id)
                .orElseThrow(() -> new TransacaoService.ResourceNotFoundException("Transação não encontrado com id: " + id));

        // Dados de produto que devem ser alterados
        transacaoExistente.setData(transacaoAtualizado.getData());
        transacaoExistente.setFormaPagamento(transacaoAtualizado.getFormaPagamento());
        transacaoExistente.setQuantTotal(transacaoAtualizado.getQuantTotal());
        transacaoExistente.setValor(transacaoAtualizado.getValor());


        //altera Produto no banco com novos dados.
        return transacaoRepository.save(transacaoExistente);
    }
    //Função para Tratar Exeption Alterar Por Id.
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

//    //Função para construir tabela Historico do Dia
//    public List<TransacaoCompletaDTO> listarVendasDoDia() {
//        LocalDate hoje = LocalDate.now();
//        String dataFormatada = hoje.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//
//        List<TransacaoMonetaria> transacoes = transacaoRepository.findByData(dataFormatada);
//
//        return transacoes.stream().map(transacao -> {
//            List<EstoqueResumoDTO> estoques = transacao.getEstoques().stream()
//                    .map(estoque -> new EstoqueResumoDTO(
//                            estoque.getId(),
//                            estoque.getNomeProd(),
//                            estoque.getQuantProduto() // <-- mapeando quantidade do estoque
//                    ))
//                    .collect(Collectors.toList());
//
//            return new TransacaoCompletaDTO(
//                    transacao.getId(),
//                    transacao.getData(),
//                    transacao.getValor(),
//                    transacao.getFormaPagamento(),
//                    transacao.getQuant(),
//                    estoques
//            );
//        }).collect(Collectors.toList());
//    }

}
