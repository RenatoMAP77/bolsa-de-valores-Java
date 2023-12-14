package entidades.interfaces;

import entidades.broker.Broker;
/**
 * Interface para objetos que podem ser notificados por um corretor.
 */
public interface Notificacao {
    /**
     * Exibe os detalhes da notificação para um corretor.
     *
     * @param broker Corretor a ser notificado
     */
    public void exibirDetalhes();
}
