package entidades.livroDeOfertas;
import java.util.*;

import entidades.interfaces.Notificacao;
import entidades.interfaces.Observable;
import entidades.interfaces.Observer;
import entidades.ordem.Compra;
import entidades.ordem.Ordem;
import entidades.ordem.OrdemTransacional;
import entidades.ordem.TipoOrdemEnum;
import entidades.ordem.Venda;
import entidades.transacao.Transacao;
import exceptions.ExcecaoNomeInvalido;

public class LivroDeOfertas implements Observable{
    private Map<Double, List<OrdemTransacional>> ordensCompra;
    private Map<Double, List<OrdemTransacional>> ordensVenda;
    public Set<Observer> brokersObservadores;
    public List<Transacao> transacoes;
    // Construtor da Classe
    public LivroDeOfertas() {
        this.ordensCompra = new TreeMap<>(Collections.reverseOrder());
        this.ordensVenda = new TreeMap<>();
        this.brokersObservadores = new HashSet<>();
        this.transacoes = new ArrayList<>();
    }
    // Métodos da Classe
    public void inscreverOrdem(OrdemTransacional ordem) throws ExcecaoNomeInvalido {
        if (ordem.getTipoOrdem() == TipoOrdemEnum.COMPRA) {
            ordensCompra.computeIfAbsent(ordem.getValor(), k -> new ArrayList<>()).add(ordem);
        } else {
            ordensVenda.computeIfAbsent(ordem.getValor(), k -> new ArrayList<>()).add(ordem);
        }
        notificarObservers(ordem);
        checarPossivelTransacao();
    }
    @Override
    public void notificarObservers(Notificacao notificacao) {
        for (Observer broker : brokersObservadores) {
            broker.update(notificacao);
        }
    }
   @Override
    public void addObserver(Observer broker) {
        brokersObservadores.add(broker);
    }
    @Override
    public void removeObserver(Observer broker) {
        brokersObservadores.remove(broker);
    }
    // Priorizar oferta de compra de maior valor primeiro, comprando sempre a de menor valor disponível.
    public void checarPossivelTransacao() throws ExcecaoNomeInvalido {
    
        // Pegar o maior double disponível para ordens de compra
        Double maiorValorCompra = ordensCompra.keySet().stream().max(Double::compare).orElse(null);
    
        // Pegar o menor double disponível para ordens de venda
        Double menorValorVenda = ordensVenda.keySet().stream().min(Double::compare).orElse(null);
    
        // Verificar se existem ordens de compra e venda
        if (maiorValorCompra != null && menorValorVenda != null) {
            // Verificar se é possível realizar uma transação
            if (maiorValorCompra >= menorValorVenda) {
                // Verificar se existem ordens de compra para o maior valor
                List<OrdemTransacional> ordensCompraParaMaiorValor = ordensCompra.get(maiorValorCompra);
                if (ordensCompraParaMaiorValor != null && !ordensCompraParaMaiorValor.isEmpty()) {
                    // Pegar a primeira ordem de compra disponível
                    OrdemTransacional compra = ordensCompraParaMaiorValor.get(0);
    
                    // Verificar se existem ordens de venda para o menor valor
                    List<OrdemTransacional> ordensVendaParaMenorValor = ordensVenda.get(menorValorVenda);
                    if (ordensVendaParaMenorValor != null && !ordensVendaParaMenorValor.isEmpty()) {
                        // Pegar a primeira ordem de venda disponível
                        OrdemTransacional venda = ordensVendaParaMenorValor.get(0);
    
                        // Realizar a transação
                        try {
                            realizarTransacao(compra, venda);
                        } catch (ExcecaoNomeInvalido e) {
                            e.printStackTrace(); // Trate a exceção de acordo com sua lógica
                        }
                    }
                }
            }
        }
    }
    
    
    
    
    
    private void realizarTransacao(OrdemTransacional compra, OrdemTransacional venda) throws ExcecaoNomeInvalido {
        int quantidadeTransacionada = Math.min(compra.getQuantidade(), venda.getQuantidade());
        double valorTransacao = venda.getValor();
    
        Transacao transacao = new Transacao(quantidadeTransacionada, valorTransacao, compra.getAcao(),
                (Compra) compra, (Venda) venda);
    
        transacoes.add(transacao);
        notificarObservers(transacao);
    
        // Atualizar quantidades nas ordens de compra e venda
        ((Compra) compra).compraSuperiorQtdeCompra(quantidadeTransacionada);
        ((Venda) venda).vendaSuperiorQtdeCompra(quantidadeTransacionada);
    
        // Remover ordens com quantidade zero
        if (((Compra) compra).getQuantidade() == 0) {
            ordensCompra.get(compra.getValor()).remove(compra);
            if (ordensCompra.get(compra.getValor()).isEmpty()) {
                ordensCompra.remove(compra.getValor());
            }
        }
    
        if (((Venda) venda).getQuantidade() == 0) {
            ordensVenda.get(venda.getValor()).remove(venda);
            if (ordensVenda.get(venda.getValor()).isEmpty()) {
                ordensVenda.remove(venda.getValor());
            }
        }
    }}
    
