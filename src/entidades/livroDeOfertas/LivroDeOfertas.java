package entidades.livroDeOfertas;

import java.util.*;

import entidades.interfaces.Notificacao;
import entidades.interfaces.Observable;
import entidades.interfaces.Observer;
import entidades.ordem.Compra;

import entidades.ordem.OrdemTransacional;
import entidades.ordem.TipoOrdemEnum;
import entidades.ordem.Venda;
import entidades.transacao.Transacao;
import exceptions.ExcecaoNomeInvalido;

/**
 * Representa um livro de ofertas, onde as ordens são registradas e podem ser
 * observadas por corretores.
 */
public class LivroDeOfertas implements Observable {
    private Map<Double, List<Compra>> ordensCompra;
    private Map<Double, List<Venda>> ordensVenda;
    public Set<Observer> brokersObservadores;
    public List<Transacao> transacoes;

    /**
     * Construtor da classe.
     */
    public LivroDeOfertas() {
        this.ordensCompra = new TreeMap<>(Collections.reverseOrder());
        this.ordensVenda = new TreeMap<>();
        this.brokersObservadores = new HashSet<>();
        this.transacoes = new ArrayList<>();
    }

    /**
     * Inscreve uma ordem no livro de ofertas.
     *
     * @param ordem Ordem a ser inscrita
     * @throws ExcecaoNomeInvalido Se o nome for inválido
     */
    public void inscreverOrdem(OrdemTransacional ordem) throws ExcecaoNomeInvalido {
        ordem.processarOrdem(ordensCompra, ordensVenda);
        notificarObservers(ordem);
        checarPossivelTransacao();
    }

    /**
     * Notifica os observadores com uma determinada notificação.
     *
     * @param notificacao Notificação a ser enviada aos observadores
     */
    @Override
    public void notificarObservers(Notificacao notificacao) {
        for (Observer broker : brokersObservadores) {
            broker.update(notificacao);
        }
    }

    /**
     * Adiciona um observador ao livro de ofertas.
     *
     * @param broker Observador a ser adicionado
     */
    @Override
    public void addObserver(Observer broker) {
        brokersObservadores.add(broker);
    }

    /**
     * Remove um observador do livro de ofertas.
     *
     * @param broker Observador a ser removido
     */
    @Override
    public void removeObserver(Observer broker) {
        brokersObservadores.remove(broker);
    }

    /**
     * Verifica se é possível realizar uma transação.
     *
     * @throws ExcecaoNomeInvalido Se o nome for inválido
     */
    public void checarPossivelTransacao() throws ExcecaoNomeInvalido {

        Double maiorValorCompra = ordensCompra.keySet().stream().max(Double::compare).orElse(null);

        Double menorValorVenda = ordensVenda.keySet().stream().min(Double::compare).orElse(null);
        if (maiorValorCompra != null && menorValorVenda != null) {
            if (maiorValorCompra >= menorValorVenda) {
                List<Compra> ordensCompraParaMaiorValor = ordensCompra.get(maiorValorCompra);
                if (ordensCompraParaMaiorValor != null && !ordensCompraParaMaiorValor.isEmpty()) {
                    Compra compra = ordensCompraParaMaiorValor.get(0);

                    // Verificar se existem ordens de venda para o menor valor
                    List<Venda> ordensVendaParaMenorValor = ordensVenda.get(menorValorVenda);
                    if (ordensVendaParaMenorValor != null && !ordensVendaParaMenorValor.isEmpty()) {
                        Venda venda = ordensVendaParaMenorValor.get(0);
                        try {
                            realizarTransacao(compra, venda);
                        } catch (ExcecaoNomeInvalido e) {
                            e.getMessage(); 
                        }
                    }
                }
            }
        }
    }

    /**
     * Realiza uma transação.
     *
     * @param compra Ordem de compra
     * @param venda  Ordem de venda
     * @throws ExcecaoNomeInvalido Se o nome for inválido
     */
    private void realizarTransacao(Compra compra, Venda venda) throws ExcecaoNomeInvalido {
        int quantidadeTransacionada = Math.min(compra.getQuantidade(), venda.getQuantidade());
        double valorTransacao = venda.getValor();

        Transacao transacao = new Transacao(quantidadeTransacionada, valorTransacao, compra.getAcao(),
                compra, venda);

        transacoes.add(transacao);
        notificarObservers(transacao);

        
        compra.compraSuperiorQtdeCompra(quantidadeTransacionada);
        venda.vendaSuperiorQtdeCompra(quantidadeTransacionada);

        
        if (compra.getQuantidade() == 0) {
            ordensCompra.get(compra.getValor()).remove(compra);
            if (ordensCompra.get(compra.getValor()).isEmpty()) {
                ordensCompra.remove(compra.getValor());
            }
        }

        if (venda.getQuantidade() == 0) {
            ordensVenda.get(venda.getValor()).remove(venda);
            if (ordensVenda.get(venda.getValor()).isEmpty()) {
                ordensVenda.remove(venda.getValor());
            }
        }
    }

    /*
     * Getters e setters
     */
    public Set<Observer> getBrokersObservadores() {
        return brokersObservadores;
    }

    public void setBrokersObservadores(Set<Observer> brokersObservadores) {
        this.brokersObservadores = brokersObservadores;
    }

}
