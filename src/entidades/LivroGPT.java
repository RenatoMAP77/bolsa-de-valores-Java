//TA ERRADO TEM Q USAR O OBSERVER
package entidades;

import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class LivroGPT {
    private TreeMap<Double, List<Order>> ordemCompra;
    private TreeMap<Double, List<Order>> ordemVenda;
    private String stockSymbol; // Símbolo da ação para este LivroGPT

    public LivroGPT(String stockSymbol) {
        this.stockSymbol = stockSymbol;
        // Usamos um comparator reverso para as ordens de compra, para ter o maior preço no topo
        this.ordemCompra = new TreeMap<>(Collections.reverseOrder());
        this.ordemVenda = new TreeMap<>();
    }

    public void addOrder(Order order) {
        TreeMap<Double, List<Order>> orders = order.getTipo().equals(OrderEnum.COMPRA) ? ordemCompra : ordemVenda;
        orders.computeIfAbsent(order.getValor(), k -> new ArrayList<>()).add(order);
        matchOrders();
    }

    public void removeOrder(Order order) {
        TreeMap<Double, List<Order>> orders = order.getTipo().equals(OrderEnum.COMPRA) ? ordemCompra : ordemVenda;
        List<Order> orderList = orders.get(order.getValor());
        if (orderList != null) {
            orderList.remove(order);
            if (orderList.isEmpty()) {
                orders.remove(order.getValor());
            }
        }
    }

    // ... O método matchOrders precisa ser atualizado para lidar com listas de ordens ...

    private void processTransaction(Order ordemCompra, Order ordemVenda, int quantidadeTransacao) {
        // Logica para registrar a transação, assumindo que ordemCompra e ordemVenda pertencem ao mesmo stockSymbol
        System.out.println("Transação realizada: " + quantidadeTransacao + " ações de " +
                           this.stockSymbol + " a " + ordemVenda.getValor() + " entre " +
                           ordemCompra.getBroker() + " e " + ordemVenda.getBroker());
        // Adicionar lógica para notificar Brokers e atualizar listaDeTransacoes
    }

    // ... O método adjustOrRemoveOrder precisa ser atualizado para lidar com listas de ordens ...

    // Métodos adicionais para acessar e modificar o livro de ofertas...
}
