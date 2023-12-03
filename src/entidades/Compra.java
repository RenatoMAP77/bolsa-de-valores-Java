package entidades;

public class Compra implements Order {
    private int quantidade;
    private double valor;
    private String broker;
    private OrderEnum tipo;
    
    public Compra(int quantidade, double valor, String broker) {
        setQuantidade(quantidade);
        setValor(valor);
        setBroker(broker);
        this.tipo = OrderEnum.COMPRA;
    }

    @Override
    public void setValor(double valor) {
        this.valor = valor;
    }
    @Override
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    @Override
    public void setBroker(String broker) {
        this.broker = broker;
    }
    
    @Override
    public double getValor() {
        return this.valor;
    }
    @Override
    public int getQuantidade() {
        return this.quantidade;
    }
    @Override
    public String getBroker() {
        return this.broker;
    }
    @Override
    public OrderEnum getTipo() {
        return this.tipo;
    }
}
