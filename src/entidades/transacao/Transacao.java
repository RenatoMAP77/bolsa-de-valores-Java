package entidades.transacao;

import java.time.LocalDateTime;

import exceptions.ExcecaoNomeInvalido;

import entidades.acao.AcaoEnum;
import entidades.broker.Broker;
import entidades.interfaces.Notificacao;
import entidades.ordem.Compra;

import entidades.ordem.Venda;

public class Transacao implements Notificacao {
    private int quantidade;
    private double valor;
    private LocalDateTime dataHora;
    private AcaoEnum nomeAcao;
    private Compra compra;
    private Venda venda;

    public Transacao(int quantidade, double valor, AcaoEnum nomeAcao, Compra compra, Venda venda) throws ExcecaoNomeInvalido {
        setQuantidade(quantidade);
        setValor(valor);
        setNomeAcao(nomeAcao);
        setCompra(compra);
        setVenda(venda);
        setDataHora(LocalDateTime.now());
    }

    /** 
     * @return int
     */
    public int getQuantidade() {
        return quantidade;
    }

    
    /** 
     * @param quantidade
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public AcaoEnum getNomeAcao() {
        return nomeAcao;
    }

    public void setNomeAcao(AcaoEnum nomeAcao) {
        this.nomeAcao = nomeAcao;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("------------------------------");
       // System.out.println("Prezado :" + b.getName());
        System.out.println("Nova transação realizada!"
                + " Quantidade: " + getQuantidade()
                + ", Valor: " + getValor()
                + ", Ação: " + getNomeAcao()
                + ", Comprador: " + getCompra().getBroker()
                + ", Vendedor: " + getVenda().getBroker());
    }
}
