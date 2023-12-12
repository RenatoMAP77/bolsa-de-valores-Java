package exceptions;


import entidades.ordem.TipoOrdemEnum;

public class ExcecaoTipoOrdemInvalido extends Exception {
    public ExcecaoTipoOrdemInvalido(Enum<TipoOrdemEnum> tipo) {
        super("Tipo de ordem: "+ tipo +" não presente no sistema! ");
    }
    
}
