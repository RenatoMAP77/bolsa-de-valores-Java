package entidades;

public class Venda implements Order {
    private int quantidade;
    private double valor;
    private String broker;
    private OrderEnum tipo;
    
    public Venda(int quantidade, double valor, String broker) {
        setQuantidade(quantidade);
        setValor(valor);
        setBroker(broker);
        this.tipo = OrderEnum.VENDA;
    }

    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public void setBroker(String broker) {
        this.broker = broker;
    }
    
    
    public double getValor() {
        return this.valor;
    }
    
    public int getQuantidade() {
        return this.quantidade;
    }
    
    public String getBroker() {
        return this.broker;
    }
    
    public OrderEnum getTipo() {
        return this.tipo;
    }
    
    public void vendaInferiorQuantidade(int compra){
        this.quantidade = this.quantidade - compra;
    }

}
