package entidades.broker;

import java.util.HashSet;
import java.util.Set;

import entidades.bolsaDeValores.BolsaDeValores;
import entidades.interfaces.Notificacao;
import entidades.interfaces.Observable;
import entidades.interfaces.Observer;
import entidades.ordem.Ordem;
import entidades.ordem.OrdemTransacional;
import entidades.transacao.Transacao;
import exceptions.ExcecaoNomeInvalido;

public class Broker implements Observer {
    private String name;
    private Set<Observable> observando;

    public Broker(String name) {
        this.name = name;
        observando = new HashSet<>();
    }
    @Override
    public void subscribe(Observable o){
        o.addObserver(this);
        observando.add(o);
    }
    @Override
    public void unsubscribe(Observable o){
        o.removeObserver(this);
        observando.remove(o);
    }
    public void enviarOrdem(Ordem o, BolsaDeValores bolsa) throws ExcecaoNomeInvalido{
        bolsa.inscreverOrdem((OrdemTransacional) o);

    }

    @Override
    public void update(Notificacao notificacao) {
        if (notificacao instanceof OrdemTransacional) {
            OrdemTransacional ordem = (OrdemTransacional) notificacao;
            System.out.println("Nova ordem adicionada!"
                    +" Tipo" + ordem.getTipoOrdem() 
                    + ", Quantidade: " + ordem.getQuantidade() 
                    + ", Valor: " + ordem.getValor() 
                    + ", Ação: " + ordem.getAcao() 
                    + ", Broker: " + ordem.getBroker());
        }
        else if (notificacao instanceof Transacao) {
            Transacao transacao = (Transacao) notificacao;
            System.out.println("Nova transação realizada!"
                    +" Quantidade: " + transacao.getQuantidade() 
                    + ", Valor: " + transacao.getValor() 
                    + ", Ação: " + transacao.getNomeAcao() 
                    + ", Comprador: " + transacao.getCompra().getBroker() 
                    + ", Vendedor: "+ transacao.getVenda().getBroker());
            
        }
    }
    
}
