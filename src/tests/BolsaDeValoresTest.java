package tests;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import entidades.acao.AcaoEnum;
import entidades.bolsaDeValores.BolsaDeValores;
import entidades.broker.Broker;
import entidades.livroDeOfertas.LivroDeOfertas;
import entidades.ordem.Ordem;
import entidades.ordem.OrdemTransacional;
import entidades.ordem.TipoOrdemEnum;
import entidades.ordem.factory.OrderFactory;
import entidades.ordem.factory.VendaFactory;
import exceptions.ExcecaoNomeInvalido;

public class BolsaDeValoresTest {

    @BeforeEach
    public void setUp () {
         Broker broker;
         broker = new Broker("Broker1");
    }

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
        OrderFactory vendaFactory = new VendaFactory();
        Ordem ordemVenda = vendaFactory.ciarOrder(10, 10.5, "Broker1", TipoOrdemEnum.COMPRA, AcaoEnum.ABEV3);

        // Teste se a ordem está sendo inscrita corretamente no livro de ofertas
        bolsa.inscreverLivo((OrdemTransacional) ordemVenda, livro);
        Map<AcaoEnum, LivroDeOfertas> livros = bolsa.getLivrosDeOfertas();
        assertNotNull(livros.get(((OrdemTransacional) ordemVenda).getAcao()));
    }
}
