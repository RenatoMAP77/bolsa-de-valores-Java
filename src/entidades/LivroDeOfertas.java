//TA ERRADO TEM Q USAR O OBSERVER
package entidades;

import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.NavigableSet;
import java.util.Map;
import java.util.Collections;

public class LivroDeOfertas {
    private TreeMap<Double, Order> ordemCompra;
    private TreeMap<Double, Order> ordemVenda;
    private String nomeAcao;

    public LivroDeOfertas() {
        // Usamos um comparator reverso para as ordens de compra, para ter o maior preço no topo
        this.ordemCompra = new TreeMap<>(Collections.reverseOrder());
        this.ordemVenda = new TreeMap<>();
    }

    public void addOrder(Order order) {
        TreeMap<Double, Order> orders = order.getTipo().equals(OrderEnum.COMPRA) ? ordemCompra : ordemVenda;
        orders.put(order.getValor(), order);
        matchOrders();
    }

    public void removeOrder(Order order) {
        TreeMap<Double, Order> orders = order.getTipo().equals(OrderEnum.COMPRA) ? ordemCompra : ordemVenda;
        orders.remove(order.getValor(), order);
    }

 private void matchOrders() {
        while (!ordemCompra.isEmpty() && !ordemVenda.isEmpty()) {
            Entry<Double, Order> melhorOfertaCompraEntry = ordemCompra.firstEntry();
            Entry<Double, Order> melhorOfertaVendaEntry = ordemVenda.firstEntry();

            Order melhorOrdemCompra = melhorOfertaCompraEntry.getValue();
            Order melhorOrdemVenda = melhorOfertaVendaEntry.getValue();

            // Confere se a ordem de compra tem um preço maior ou igual ao da ordem de venda
            if (melhorOrdemCompra.getValor() >= melhorOrdemVenda.getValor()) {
                int quantidadeTransacao = Math.min(melhorOrdemCompra.getQuantidade(), melhorOrdemVenda.getQuantidade());

                // Processa a transação - este método deve ser implementado para lidar com a lógica de transação
                processTransaction(melhorOrdemCompra, melhorOrdemVenda, quantidadeTransacao);

                // Ajusta as quantidades das ordens ou as remove se foram totalmente preenchidas
                adjustOrRemoveOrder(melhorOrdemCompra, quantidadeTransacao, ordemCompra);
                adjustOrRemoveOrder(melhorOrdemVenda, quantidadeTransacao, ordemVenda);
            } else {
                // Se a melhor ordem de compra não corresponde ao preço da melhor ordem de venda, saia do loop
                break;
            }
        }
    }

    private void processTransaction(Order ordemCompra, Order ordemVenda, int quantidadeTransacao) {
        // Aqui você implementaria a lógica para registrar a transação.
        // Por exemplo, notificar os Brokers envolvidos, registrar a transação no histórico, etc.
        System.out.println("Transação realizada: " + quantidadeTransacao + " ações de " +
                           ordemVenda.getStockSymbol() + " a " + ordemVenda.getValor() + " entre " +
                           ordemCompra.getBroker() + " e " + ordemVenda.getBroker());
    }

    private void adjustOrRemoveOrder(Order order, int tradedQuantity, TreeMap<Double, Order> orderBook) {
        if (order.getQuantidade() > tradedQuantity) {
            order.setQuantidade(order.getQuantidade() - tradedQuantity);
        } else {
            orderBook.remove(order.getValor());
        }
    }

    // Métodos adicionais para acessar e modificar o livro de ofertas...
}
