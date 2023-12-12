package entidades.ordem;

import entidades.acao.AcaoEnum;

public class Venda implements OrdemTransacional {
    private int quantidade;
    private double valor;
    private String broker;
    private TipoOrdemEnum tipo;
    private AcaoEnum acao;

    public Venda(int quantidade, double valor, String broker, TipoOrdemEnum tipo, AcaoEnum acao) {
        setQuantidade(quantidade);
        setValor(valor);
        setBroker(broker);
        setTipoOrdem(tipo);
        setAcao(acao);
    }
    @Override
    public int getQuantidade() {
        return quantidade;
    }
    @Override
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    @Override
    public double getValor() {
        return valor;
    }
    @Override
    public void setValor(double valor) {
        this.valor = valor;
    }
    @Override
    public String getBroker() {
        return broker;
    }
    @Override
    public void setBroker(String broker) {
        this.broker = broker;
    }
    @Override
    public TipoOrdemEnum getTipoOrdem() {
        return tipo;
    }
    @Override
    public void setTipoOrdem(TipoOrdemEnum tipo) {
        this.tipo = tipo;
    }
    @Override
    public AcaoEnum getAcao() {
        return acao;
    }
    @Override
    public void setAcao(AcaoEnum acao) {
        this.acao = acao;
    }

    public void vendaSuperiorQtdeCompra(int compra) {
        this.quantidade = this.quantidade - compra;
    }
}