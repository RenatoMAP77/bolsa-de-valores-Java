package entidades.ordem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entidades.acao.AcaoEnum;
import entidades.broker.Broker;
/**
     * Classe que representa uma ordem de venda no sistema.
     */
public class Venda implements OrdemTransacional {
    private int quantidade;
    private double valor;
    private String broker;
    private TipoOrdemEnum tipo;
    private AcaoEnum acao;

    
    /**
     * Construtor da classe Venda.
     *
     * @param quantidade Quantidade da ordem
     * @param valor      Valor da ordem
     * @param broker     Corretor associado à ordem
     * @param tipo       Tipo da ordem (COMPRA, VENDA, INFO)
     * @param acao       Ação associada à ordem
     */
    public Venda(int quantidade, double valor, String broker, TipoOrdemEnum tipo, AcaoEnum acao) {
        setQuantidade(quantidade);
        setValor(valor);
        setBroker(broker);
        setTipoOrdem(tipo);
        setAcao(acao);
        this.exibirDetalhes();
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

    @Override
    public double getValor() {
        return valor;
    }

    @Override
    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String getBroker() {
        return broker;
    }

    @Override
    public void setBroker(String broker) {
        this.broker = broker;
    }

    @Override
    public TipoOrdemEnum getTipoOrdem() {
        return tipo;
    }

    @Override
    public void setTipoOrdem(TipoOrdemEnum tipo) {
        this.tipo = tipo;
    }

    @Override
    public AcaoEnum getAcao() {
        return acao;
    }

    @Override
    public void setAcao(AcaoEnum acao) {
        this.acao = acao;
    }

    public void vendaSuperiorQtdeCompra(int compra) {
        this.quantidade = this.quantidade - compra;
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
        ordensVenda.computeIfAbsent(this.getValor(), k -> new ArrayList<>()).add(this);
    }
}