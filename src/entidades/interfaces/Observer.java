package entidades.interfaces;

import entidades.ordem.Ordem;

public interface Observer {
    public void update(Notificacao notificacao);
    public void subscribe(Observable observable);
    public void unsubscribe(Observable observable);
}
