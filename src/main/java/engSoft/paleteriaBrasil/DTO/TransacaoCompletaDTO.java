package engSoft.paleteriaBrasil.DTO;

import java.sql.Time;
import java.util.List;

public class TransacaoCompletaDTO {
    private Integer id;
    private String data;
    private Time hora;
    private Float valor;
    private String formaPagamento;
    private Integer quant;
    private List<EstoqueResumoDTO> estoques;

    public TransacaoCompletaDTO(Integer id, String data,Time hora, Float valor, String formaPagamento,
                                Integer quant, List<EstoqueResumoDTO> estoques) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
        this.quant = quant;
        this.estoques = estoques;
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public Time getHora() { return hora; }
    public void setHora(Time hora) { this.hora = hora; }

    public Float getValor() { return valor; }
    public void setValor(Float valor) { this.valor = valor; }

    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }

    public Integer getQuant() { return quant; }
    public void setQuant(Integer quant) { this.quant = quant; }

    public List<EstoqueResumoDTO> getEstoques() { return estoques; }
    public void setEstoques(List<EstoqueResumoDTO> estoques) { this.estoques = estoques; }
}
