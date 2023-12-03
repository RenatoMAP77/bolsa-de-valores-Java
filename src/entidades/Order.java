package entidades;

public interface Order {
    public void setQuantidade(int quantidade);
    public void setValor(double reais);
    public void setBroker(String broker);

    public int getQuantidade();
    public double getValor();
    public String getBroker();
    public OrderEnum getTipo();
 }
