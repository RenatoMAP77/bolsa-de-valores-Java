package entidades.interfaces;

import entidades.ordem.Ordem;

/**
 * Observale.java
 */
public interface Observable {
    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
     public void notificarObservers(Notificacao notificacao);
     
}