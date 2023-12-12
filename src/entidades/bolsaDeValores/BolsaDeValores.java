package entidades.bolsaDeValores;
import java.util.Map;

import entidades.acao.AcaoEnum;
import entidades.livroDeOfertas.LivroDeOfertas;
import entidades.ordem.*;
import exceptions.ExcecaoNomeInvalido;

public class BolsaDeValores {
    private static BolsaDeValores INSTANCE;
    private Map<AcaoEnum,LivroDeOfertas> livrosDeOfertas;
    
    public static synchronized BolsaDeValores getInstance(){
        if(INSTANCE == null){
            INSTANCE = new BolsaDeValores();
        }
        return INSTANCE;
    }

    public void inscreverLivo(OrdemTransacional ordem, LivroDeOfertas livro) {
        livrosDeOfertas.put(ordem.getAcao(), livro);
        
    }
    public void inscreverOrdem(OrdemTransacional ordem) throws ExcecaoNomeInvalido{
        LivroDeOfertas livro = livrosDeOfertas.get(ordem.getAcao());
        if(livro == null){
            livro = new LivroDeOfertas();
            inscreverLivo(ordem, livro);
        }
        livro.inscreverOrdem(ordem);

    }

}