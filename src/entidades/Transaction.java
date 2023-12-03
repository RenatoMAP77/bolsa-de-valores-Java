package entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import exceptions.ExcecaoNomeInvalido;

public class Transaction {
    private int quantidade;
    private double valor;
    private String corretora;
    private String tipo;
    private LocalDateTime data_hora;
    private String nomeAcao;

    public Transaction(int quantidade, double valor, String corretora, String tipo, String nomeAcao) throws ExcecaoNomeInvalido {
        setQuantidade(quantidade);
        setValor(valor);
        setCorretora(corretora);
        setTipo(tipo);
        setDataHora(LocalDateTime.now());
        setNomeAcao(nomeAcao);
    }

    private void setNomeAcao(String nomeAcao) throws ExcecaoNomeInvalido {
        if (nomeAcao == null || nomeAcao.length() != 5) {
            throw new ExcecaoNomeInvalido(nomeAcao);
        } else {
             this.nomeAcao = nomeAcao;
        }
    }
    public String getNomeAcao(){
        return nomeAcao;
    }

    private void setValor(double valor) {
        this.valor = valor;
    }

    private void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    private void setCorretora(String corretora) {
        this.corretora = corretora;
    }

    private void setTipo(String tipo) {
        this.tipo = tipo;
    }

    private void setDataHora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValor() {
        return valor;
    }

    public String getCorretora() {
        return corretora;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dataFormatada = data_hora.format(formatter);

        return "Transação [Ação: "+ nomeAcao 
                +", quantidade=" + quantidade + 
                ", valor=" + valor + 
                ", corretora=" + corretora + 
                ", tipo="+ tipo + 
                ", data_hora=" + dataFormatada + "]";
    }
}
