package entidades.interfaces;

/**
 * Interface para objetos observáveis.
 */
public interface Observable {
    /**
     * Adiciona um observador.
     *
     * @param observer Observador a ser adicionado
     */
    public void addObserver(Observer observer);
    /**
     * Remove um observador.
     *
     * @param observer Observador a ser removido
     */
    public void removeObserver(Observer observer);
     /**
     * Notifica os observadores com uma determinada notificação.
     *
     * @param notificacao Notificação a ser enviada aos observadores
     */
    public void notificarObservers(Notificacao notificacao);
}