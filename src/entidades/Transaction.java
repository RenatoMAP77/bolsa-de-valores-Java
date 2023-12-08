package entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import exceptions.ExcecaoNomeInvalido;

public class Transaction {
    private int quantidade;
    private double valor;
    
    private LocalDateTime data_hora;
    private String nomeAcao;
    private Order Compra;
    private Order Venda;

    public Transaction(int quantidade, double valor, String nomeAcao, Order Compra, Order Venda) throws ExcecaoNomeInvalido {
        setQuantidade(quantidade);
        setValor(valor);
        setNomeAcao(nomeAcao);
        setCompra(Compra);
        setVenda(Venda);
        setDataHora(LocalDateTime.now());
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
 
    private void setDataHora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public double getValor() {
        return this.valor;
    }

    public LocalDateTime getData_hora() {
        return this.data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public Order getCompra() {
        return this.Compra;
    }

    public void setCompra(Order compra) {
        this.Compra = compra;
    }

    public Order getVenda() {
        return this.Venda;
    }

    public void setVenda(Order venda) {
        this.Venda = venda;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dataFormatada = data_hora.format(formatter);

        return "Transação [Ação: "+ nomeAcao 
                +", quantidade=" + quantidade + 
                ", valor=" + valor + 
                ", broker Vendedor=" + Venda.getBroker() + 
                ", broker Comprador=" + Compra.getBroker() +
                ", data_hora=" + dataFormatada + "]";
    }
}
