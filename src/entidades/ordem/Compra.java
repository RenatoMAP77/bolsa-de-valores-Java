package entidades.ordem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entidades.acao.AcaoEnum;
import entidades.broker.Broker;

/**
 * Classe que representa uma ordem de compra no sistema.
 */
public class Compra implements OrdemTransacional {
    private int quantidade;
    private double valor;
    private String broker;
    private TipoOrdemEnum tipo;
    private AcaoEnum acao;

    /**
     * Construtor da classe Compra.
     *
     * @param quantidade Quantidade da ordem
     * @param valor      Valor da ordem
     * @param broker     Corretor associado à ordem
     * @param tipo       Tipo da ordem (COMPRA, VENDA, INFO)
     * @param acao       Ação associada à ordem
     */
    public Compra(int quantidade, double valor, String broker, TipoOrdemEnum tipo, AcaoEnum acao) {
        setQuantidade(quantidade);
        setValor(valor);
        setBroker(broker);
        setTipoOrdem(tipo);
        setAcao(acao);
    }

    /**
     * @return int
     */
    @Override
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade
     */
    @Override
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Obtém o valor da ordem de compra.
     *
     * @return Valor da ordem
     */
    @Override
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor da ordem de compra.
     *
     * @param valor Valor da ordem
     */
    @Override
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Obtém o corretor associado à ordem de compra.
     *
     * @return Nome do corretor
     */
    @Override
    public String getBroker() {
        return broker;
    }

    /**
     * Define o corretor associado à ordem de compra.
     *
     * @param broker Nome do corretor
     */
    @Override
    public void setBroker(String broker) {
        this.broker = broker;
    }

    /**
     * Obtém o tipo da ordem de compra.
     *
     * @return Tipo da ordem (COMPRA, VENDA, INFO)
     */
    @Override
    public TipoOrdemEnum getTipoOrdem() {
        return tipo;
    }

    /**
     * Define o tipo da ordem de compra.
     *
     * @param tipo Tipo da ordem (COMPRA, VENDA, INFO)
     */
    @Override
    public void setTipoOrdem(TipoOrdemEnum tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtém a ação associada à ordem de compra.
     *
     * @return Ação da ordem
     */
    @Override
    public AcaoEnum getAcao() {
        return acao;
    }

    /**
     * Define a ação associada à ordem de compra.
     *
     * @param acao Ação da ordem
     */
    @Override
    public void setAcao(AcaoEnum acao) {
        this.acao = acao;
    }
    /**
     * Atualiza a quantidade da ordem de compra.
     *
     * @param quantidade Quantidade da ordem
     */
    public void compraSuperiorQtdeCompra(int quantidade) {
        this.quantidade = this.quantidade - quantidade;
    }

    /**
     * Exibe detalhes da ordem de compra para um corretor específico.
     *
     * @param broker Corretor que receberá a notificação
     */
    @Override
    public void exibirDetalhes() {
        System.out.println("------------------------------");
       // System.out.println("Prezado: " + broker.getName());
        System.out.println("Nova ordem adicionada!"
                + " Tipo" + getTipoOrdem()
                + ", Quantidade: " + getQuantidade()
                + ", Valor: " + getValor()
                + ", Ação: " + getAcao()
                + ", Broker: " + getBroker());

    }

    /**
     * Processa a ordem de compra, atualizando os mapas de ordens de compra e venda.
     *
     * @param ordensCompra Mapa de ordens de compra
     * @param ordensVenda  Mapa de ordens de venda
     */
    @Override
    public void processarOrdem(Map<Double, List<Compra>> ordensCompra, Map<Double, List<Venda>> ordensVenda) {
        ordensCompra.computeIfAbsent(this.getValor(), k -> new ArrayList<>()).add(this);
    }

}
