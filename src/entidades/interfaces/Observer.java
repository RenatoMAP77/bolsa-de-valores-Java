package entidades.interfaces;

/**
 * Interface para objetos observadores.
 */
public interface Observer {
    /**
     * Atualiza o observador com uma notificação.
     *
     * @param notificacao Notificação a ser recebida
     */
    void update(Notificacao notificacao);

    /**
     * Inscreve-se em um objeto observável.
     *
     * @param observable Objeto observável a ser observado
     */
    void subscribe(Observable observable);

    /**
     * Cancela a inscrição em um objeto observável.
     *
     * @param observable Objeto observável a ser removido da inscrição
     */
    void unsubscribe(Observable observable);
}
