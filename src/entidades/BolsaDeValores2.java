
package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BolsaDeValores2 {
    private static BolsaDeValores2 instance;
    private Map<String, List<Broker>> stockObservers;

    private BolsaDeValores2() {
        stockObservers = new HashMap<>();
    }

    public static synchronized BolsaDeValores2 getInstance() {
        if (instance == null) {
            instance = new BolsaDeValores2();
        }
        return instance;
    }

    // Registra um Broker para receber atualizações sobre uma ação específica.
    public void registerObserver(String stockSymbol, Broker broker) {
        stockObservers.computeIfAbsent(stockSymbol, k -> new ArrayList<>()).add(broker);
    }

    // Remove um Broker do interesse de uma ação específica.
    public void unregisterObserver(String stockSymbol, Broker broker) {
        if (stockObservers.containsKey(stockSymbol)) {
            stockObservers.get(stockSymbol).remove(broker);
            if (stockObservers.get(stockSymbol).isEmpty()) {
                stockObservers.remove(stockSymbol);
            }
        }
    }

    // Notifica os Brokers interessados em uma ação específica.
    private void notifyObservers(String stockSymbol, TradeUpdate update) {
        if (stockObservers.containsKey(stockSymbol)) {
            for (Broker observer : stockObservers.get(stockSymbol)) {
                observer.update(this, update);
            }
        }
    }

    // Processa e notifica sobre uma ordem de compra.
    public void processOrder(Order order) {
        // Processamento da ordem...
        Transaction update = new Transaction("Compra", order.getStock(), order.getPrice(), order.getQuantity());
        notifyObservers(order.getStock(), update);
    }

    // Processa e notifica sobre uma ordem de venda.
    public void processSellOrder(Order ordemVenda) {
        // Processamento da ordem...
        Transaction update = new Transaction("Venda", ordemVenda.getStock(), ordemVenda.getPrice(), ordemVenda.getQuantity());
        notifyObservers(ordemVenda.getStock(), update);
    }

    // Outros métodos...
}
