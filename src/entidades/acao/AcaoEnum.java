package entidades.acao;
/**
 * Classe para utlização de Acoes, cada enum possui como atributo sua seu nome como String
 */
public enum AcaoEnum {
    ABEV3("ABEV3"),
    PETR4("PETR4"),
    VALE5("VALE5"),
    ITUB4("ITUB4"),
    BBDC4("BBDC4"),
    BBAS3("BBAS3"),
    CIEL3("CIEL3"),
    PETR3("PETR3");

    private final String acao; // Nome da ação
    /**
     * Construtor da classe
     * @param acao
     */
    AcaoEnum(String acao) {
        this.acao = acao;  
    }
 
}
