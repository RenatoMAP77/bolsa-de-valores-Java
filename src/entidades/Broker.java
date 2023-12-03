package entidades;

import exceptions.ExcecaoNomeInvalido;

public class Broker extends Observer {
    private String name;

    public Broker(String name, Observable observable) throws ExcecaoNomeInvalido {
        super(observable);
        if (name == null || name.length() != 4) {
            throw new ExcecaoNomeInvalido(name);
        } else {
             this.name = name;
        }
    }

    @Override
    public void update() {
        System.out.println("Broker " + this.name + " foi notificado!");
        
    }

    public void enviarOrdemDeCompra(Compra order) {
        BolsaDeValores.getInstance().processarOrdemDeCompra(order);
    }

    public void enviarOrdemDeVenda(Venda order) {
        BolsaDeValores.getInstance().processarOrdemDeVenda(order);
    }
    
}
