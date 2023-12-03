package exceptions;

import entidades.OrderEnum;

public class ExcecaoTipoOrdemInvalido extends Exception {
    public ExcecaoTipoOrdemInvalido(Enum<OrderEnum> tipo) {
        super("Tipo de ordem: "+ tipo +" não presente no sistema! ");
    }
    
}
