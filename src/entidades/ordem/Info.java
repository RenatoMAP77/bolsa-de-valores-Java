package entidades.ordem;

import entidades.broker.Broker;

import java.time.LocalDateTime;

/**
 * Classe que representa uma ordem do tipo "INFO", utilizada para informações gerais.
 */
public class Info implements Ordem {
    private LocalDateTime dataHora;
    private TipoOrdemEnum tipo;

    /**
     * Construtor da classe Info.
     *
     * @param dataHora Data e hora da ordem
     * @param tipo     Tipo da ordem
     */
    public Info(LocalDateTime dataHora, TipoOrdemEnum tipo) {
        this.dataHora = dataHora;
        this.tipo = tipo;
    }

    /**
     * Obtém a data e hora da ordem.
     *
     * @return Data e hora da ordem
     */
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    /**
     * Define a data e hora da ordem.
     *
     * @param dataHora Data e hora da ordem
     */
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    /**
     * Obtém o tipo da ordem.
     *
     * @return Tipo da ordem (COMPRA, VENDA, INFO)
     */
    @Override
    public TipoOrdemEnum getTipoOrdem() {
        return tipo;
    }

    /**
     * Define o tipo da ordem.
     *
     * @param tipo Tipo da ordem (COMPRA, VENDA, INFO)
     */
    @Override
    public void setTipoOrdem(TipoOrdemEnum tipo) {
        this.tipo = tipo;
    }

    /**
     * Exibe detalhes da ordem para um corretor específico.
     *
     * @param broker Corretor que receberá a notificação
     */
    @Override
    public void exibirDetalhes() {
        System.out.println("------------------------------");
        //System.out.println("Prezado :" + broker.getName());
        System.out.println("Data e hora: " + getDataHora());
        System.out.println("Tipo: " + getTipoOrdem());
    }
}
