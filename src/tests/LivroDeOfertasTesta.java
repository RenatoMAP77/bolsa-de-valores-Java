package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import entidades.acao.AcaoEnum;
import entidades.broker.Broker;
import entidades.interfaces.Observer;
import entidades.ordem.Compra;
import entidades.ordem.OrdemTransacional;
import exceptions.ExcecaoNomeInvalido;

public class LivroDeOfertasTest {

    @Test
    public void testInscreverOrdem() {
        LivroDeOfertas livro = new LivroDeOfertas();
        OrdemTransacional ordem = new Compra(/* Parâmetros para a ordem */);
        
        // Teste se a ordem está sendo inscrita corretamente no livro de ofertas
        try {
            livro.inscreverOrdem(ordem);
            Map<Double, List<Compra>> ordensCompra = livro.getOrdensCompra();
            assertNotNull(ordensCompra.get(ordem.getValor()));
        } catch (ExcecaoNomeInvalido e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }

    @Test
    public void testChecarPossivelTransacao() {
        // Teste para verificar se a função checarPossivelTransacao funciona corretamente
        LivroDeOfertas livro = new LivroDeOfertas();
        // Adicione ordens de compra e venda para testar a transação
        // ...
        try {
            livro.checarPossivelTransacao();
            // Verifique se as transações foram realizadas corretamente
            // ...
        } catch (ExcecaoNomeInvalido e) {
            fail("Exceção inesperada: " + e.getMessage());
        }
    }
}
