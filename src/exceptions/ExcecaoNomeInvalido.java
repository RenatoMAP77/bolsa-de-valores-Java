package exceptions;


public class ExcecaoNomeInvalido extends Exception {
    public ExcecaoNomeInvalido(String nome) {
        super("Nome: "+ nome +" não adequado ao padrão do sistema! ");
    }
}
