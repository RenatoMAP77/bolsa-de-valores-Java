package entidades;

import exceptions.ExcecaoTipoOrdemInvalido;

public class OrderFactory {
public static Order createOrder(int quantidade, double valor, String corretora, OrderEnum tipo) throws ExcecaoTipoOrdemInvalido {
    switch (tipo) {
        case COMPRA:
            return new Compra(quantidade, valor, corretora);
            
        case VENDA:
            return new Venda(quantidade, valor, corretora);
            
        default:
            throw new ExcecaoTipoOrdemInvalido(tipo);
    }
}

}   