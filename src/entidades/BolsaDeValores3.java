package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BolsaDeValores3 {

    //Singleton
    private final BolsaDeValores3 INSTANCE = new BolsaDeValores3();
    private Map<String, BookOrders> livrosDeOrdens;

    public BolsaDeValores3 getInstance(){
        return INSTANCE;
    }
    
    public void registerBookOrders(String stockSymbol, BookOrders bookOrders) {
        livrosDeOrdens.computeIfAbsent(stockSymbol, k -> new ArrayList<>()).add(bookOrders);
    }


    
    
}
