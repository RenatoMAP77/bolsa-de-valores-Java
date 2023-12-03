package entidades;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BolsaDeValores extends Observable {

    private final static BolsaDeValores INSTANCE = new BolsaDeValores();
    //ListaDeOfertas
   // private Map<String , List<Order>> ListaDeOfertas;

    //Instaciar varios LivroDeOfertas
    //Sendo String = NomeDaAção / StockSymbol e LivroDeOfertas o livro de cada ação
    private Map<String, LivroDeOfertas> lista;
    
    //Interessados/observadores
    private Map<String, List<Broker>> observadores;

    //ListaDeTransacoes
    private List<Transaction> listaDeTransacoes;

    private BolsaDeValores() {}

    public static BolsaDeValores getInstance() {
        return INSTANCE;
    }
    
    public void addCompradores(Broker broker, String acao) {
        // Verifica se a ação já está no mapa
        if (!Compradores.containsKey(acao)) {
            Compradores.put(acao, new ArrayList<>()); 
        }

        
        Compradores.get(acao).add(broker);
        
    }
    

    public void addOrdem(Order order) {
        // Verifica se a ação já está no mapa
        if (!ListaDeOfertas.containsKey(order.getAcao())) {
            ListaDeOfertas.put(order.getAcao(), new ArrayList<>()); 
        }

        // Adiciona a ordem na lista de ofertas
        ListaDeOfertas.get(order.getAcao()).add(order);
        
        // Notifica os observadores
        notifyObservers();
    }
    
    public void notifyObservers() {
        //Notificar compradores e atualizar caso a compra ou venda bater com o preço
        for (String acao : ListaDeOfertas.keySet()) {
            for (Order order : ListaDeOfertas.get(acao)) {
                
                    if (order.getTipo().equals("Compra")) {
                        if (order.getValor() <= order.getValor()) {
                            //Notificar lista de Compradores
                            for (Broker broker : Compradores.get(acao)) {
                                broker.update();
                            }
                        }
                    } else {
                        if (order.getValor() >= order.getValor()) {
                            order.update();
                        }
                    }
                
            }
        }
    }

    public void processarOrdemDeCompra(Compra order) {
        
    }

    public void processarOrdemDeVenda(Venda order) {

    }
    
}
