package entidades.ordem;

import java.util.List;
import java.util.Map;

import entidades.acao.AcaoEnum;
/**
 * Interface que define métodos específicos para ordens transacionais, ou seja, ordens de compra e venda.
 */
public interface OrdemTransacional extends Ordem {
    /**
     * Obtém a quantidade da ordem transacional.
     *
     * @return Quantidade da ordem
     */
    public int getQuantidade();
     /**
     * Define a quantidade da ordem transacional.
     *
     * @param quantidade Quantidade da ordem
     */
    public void setQuantidade(int quantidade);
    /**
     * Define o valor da ordem transacional.
     *
     * @param valor Valor da ordem
     */
    public void setValor(double valor);
    /**
     * Obtém o valor da ordem transacional.
     *
     * @return Valor da ordem
     */
    public double getValor();
    /**
     * Obtém o nome do broker da ordem transacional.
     *
     * @return Nome do broker
     */
    public String getBroker();
    /**
     * Define o nome do broker da ordem transacional.
     *
     * @param broker Nome do broker
     */
    public void setBroker(String broker);

    /**
     * Obtém o tipo da ordem transacional.
     *
     * @return Tipo da ordem (COMPRA, VENDA, INFO)
     */
    public TipoOrdemEnum getTipoOrdem();
    /**
     * Define o tipo da ordem transacional.
     *
     * @param tipo Tipo da ordem (COMPRA, VENDA, INFO)
     */
    public void setTipoOrdem(TipoOrdemEnum tipo);

    /**
     * Obtém a ação associada à ordem.
     *
     * @return Ação da ordem
     */
    public AcaoEnum getAcao();
     /**
     * Define a ação associada à ordem.
     *
     * @param acao Ação da ordem
     */
    public void setAcao(AcaoEnum acao);

    /**
     * Processa a ordem, atualizando os mapas de ordens de compra e venda.
     *
     * @param ordensCompra Mapa de ordens de compra
     * @param ordensVenda  Mapa de ordens de venda
     */
    void processarOrdem( Map<Double, List<Compra>> ordensCompra, Map<Double, List<Venda>> ordensVenda);
}
