package entidades.bolsaDeValores;
import java.util.Map;

import entidades.acao.AcaoEnum;
import entidades.broker.Broker;
import entidades.interfaces.Observer;
import entidades.livroDeOfertas.LivroDeOfertas;
import entidades.ordem.*;
import exceptions.ExcecaoNomeInvalido;
/**
 * Representa a Bolsa de Valores, responsável por gerenciar os livros de ofertas.
 */
public class BolsaDeValores {
    private static BolsaDeValores INSTANCE;
    private Map<AcaoEnum,LivroDeOfertas> livrosDeOfertas;
    
    
    /**
     * Obtém a instância única da Bolsa de Valores (Padrão Singleton).
     *
     * @return Instância única da BolsaDeValores
     */
    public static synchronized BolsaDeValores getInstance(){
        if(INSTANCE == null){
            INSTANCE = new BolsaDeValores();
        }
        return INSTANCE;
    }

    
    /**
     * Inscreve uma ordem em um livro de ofertas.
     *
     * @param ordem Ordem a ser inscrita
     * @param livro Livro de ofertas
     */
    public void inscreverLivo(OrdemTransacional ordem, LivroDeOfertas livro) {
        livrosDeOfertas.put(ordem.getAcao(), livro);
        
    }
    /**
     * Inscreve uma ordem e um observador em um livro de ofertas.
     *
     * @param ordem Ordem a ser inscrita
     * @param b     Observador a ser inscrito
     * @throws ExcecaoNomeInvalido Se o nome for inválido
     */
    public void inscreverOrdem(OrdemTransacional ordem, Observer b) throws ExcecaoNomeInvalido{
        LivroDeOfertas livro = livrosDeOfertas.get(ordem.getAcao());
        if(livro == null){
            livro = new LivroDeOfertas();
            inscreverLivo(ordem, livro);
        }
        livro.inscreverOrdem(ordem);
        if (!livro.getBrokersObservadores().contains(b)) {
            b.subscribe(livro);
        }
        


    }


	public Map<AcaoEnum, LivroDeOfertas> getLivrosDeOfertas() {
        return livrosDeOfertas;
	}

}