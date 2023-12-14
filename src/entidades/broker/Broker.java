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

/**
 * Representa um corretor que pode enviar ordens para a Bolsa de Valores.
 */
public class Broker implements Observer {
    private String name;
    private Set<Observable> observando;

    /**
     * Construtor da classe.
     * @param name Nome do corretor.
     */
    public Broker(String name) {
        this.name = name;
        observando = new HashSet<>();
    }

    /**
     * Inscreve-se em um objeto observável.
     *
     * @param o Objeto observável
     */
    @Override
    public void subscribe(Observable o) {
        o.addObserver(this);
        observando.add(o);
    }

    /**
     * Cancela a inscrição em um objeto observável.
     *
     * @param o Objeto observável
     */
    @Override
    public void unsubscribe(Observable o) {
        o.removeObserver(this);
        observando.remove(o);
    }
    /**
     * Envia uma ordem para a Bolsa de Valores.
     *
     * @param o     Ordem a ser enviada
     * @param bolsa Bolsa de Valores
     * @throws ExcecaoNomeInvalido Se o nome for inválido
     */
    public void enviarOrdem(Ordem o, BolsaDeValores bolsa) throws ExcecaoNomeInvalido {
        bolsa.inscreverOrdem((OrdemTransacional) o, this);

    }
    /**
     * Atualiza o corretor com uma notificação.
     *
     * @param notificacao Notificação a ser exibida
     */
    @Override
    public void update(Notificacao notificacao) {
        notificacao.exibirDetalhes();
    }
    /**
     * Obtém o nome do corretor.
     *
     * @return Nome do corretor
     */
    public String getName() {
        return name;
    }
    /**
     * Define o nome do corretor.
     *
     * @param name Novo nome do corretor
     */
    public void setName(String name) {
        this.name = name;
    }

}
