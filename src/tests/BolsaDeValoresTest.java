package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import entidades.acao.AcaoEnum;
import entidades.bolsaDeValores.BolsaDeValores;
import entidades.livroDeOfertas.LivroDeOfertas;
import entidades.ordem.OrdemTransacional;
import exceptions.ExcecaoNomeInvalido;

public class BolsaDeValoresTest {

    @Test
    public void testGetInstance() {
        BolsaDeValores bolsa1 = BolsaDeValores.getInstance();
        BolsaDeValores bolsa2 = BolsaDeValores.getInstance();
        assertEquals(bolsa1, bolsa2);
    }

    @Test
    public void testInscreverOrdem() {
        BolsaDeValores bolsa = BolsaDeValores.getInstance();
        LivroDeOfertas livro = new LivroDeOfertas();
        OrdemTransacional ordem = new OrdemTransacional(/* Parâmetros para a ordem */);
        
        // Teste se a ordem está sendo inscrita corretamente no livro de ofertas
        bolsa.inscreverLivo(ordem, livro);
        Map<AcaoEnum, LivroDeOfertas> livros = bolsa.getLivrosDeOfertas();
        assertNotNull(livros.get(ordem.getAcao()));
    }
}
