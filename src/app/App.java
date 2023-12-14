package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entidades.acao.*;
import entidades.broker.*;
import entidades.livroDeOfertas.LivroDeOfertas;
import entidades.ordem.Compra;
import entidades.ordem.Ordem;
import entidades.ordem.OrdemTransacional;
import entidades.ordem.TipoOrdemEnum;
import entidades.ordem.Venda;
import entidades.ordem.factory.CompraFactory;
import entidades.ordem.factory.OrderFactory;
import entidades.ordem.factory.VendaFactory;
import entidades.bolsaDeValores.*;
import entidades.*;


public class App {

    private static List<Broker> bro = new ArrayList<>();
    private static BolsaDeValores bolsa = BolsaDeValores.getInstance();
    private static Broker[] brokers = new Broker[20]; // Cria um array para armazenar as instâncias

    
    /** 
     * @param args
     */
    public static void main(String[] args) {

        popular();

        while (true) {

            Thread threadCompra = new Thread(() -> {
                try {
                    while (true) {
                        for (int i = 0; i < 20; i++) {
                            // Gera valores aleatórios
                            Random random = new Random();
                            int quantidade = random.nextInt(96) + 5; // Números aleatórios entre 5 e 100
                            float valor = 15.0f + random.nextFloat() * (200.0f - 15.0f); // Números aleatórios entre
                                                                                         // 15.0 e
                                                                                         // 200.0

                            // Seleciona uma ação aleatória das ações adicionadas ao broker
                            AcaoEnum[] acoes = AcaoEnum.values();
                            int index = random.nextInt(acoes.length);
                            AcaoEnum acao = acoes[index];

                            int indexBrokers = random.nextInt(brokers.length);

                            OrderFactory vendaFactory = new VendaFactory();

                            // Criar ordem de compra com acção aleatória
                            Ordem o = vendaFactory.ciarOrder(quantidade, valor, brokers[indexBrokers].getName(), TipoOrdemEnum.COMPRA, acao);
                            brokers[indexBrokers].enviarOrdem(o, BolsaDeValores.getInstance());
                            Thread.sleep(2000);
                        }

                    } } catch (Exception e) {
                        e.getMessage();
                    }
            });

            Thread threadVenda = new Thread(() -> {
                try {
                    while (true) {
                        for (int i = 0; i < 20; i++) {
                            // Gera valores aleatórios
                            Random random = new Random();
                            int quantidade = random.nextInt(96) + 5; // Números aleatórios entre 5 e 100
                            float valor = 15.0f + random.nextFloat() * (200.0f - 15.0f); // Números aleatórios entre
                                                                                         // 15.0 e
                                                                                         // 200.0

                            // Seleciona uma ação aleatória das ações adicionadas ao broker
                            AcaoEnum[] acoes = AcaoEnum.values();
                            int index = random.nextInt(acoes.length);
                            AcaoEnum acao = acoes[index];

                            int indexBrokers = random.nextInt(brokers.length);

                            OrderFactory vendaFactory = new VendaFactory();

                            // Criar ordem de venda com ação aleatória
                            Ordem o = vendaFactory.ciarOrder(quantidade, valor, brokers[indexBrokers].getName(), TipoOrdemEnum.VENDA, acao);
                            brokers[indexBrokers].enviarOrdem(o, BolsaDeValores.getInstance());
                            Thread.sleep(20000);
                        }

                    } } catch (Exception e) {
                        e.getMessage();
                    }
            });

            threadCompra.start();
            threadVenda.start();

            try {
                threadCompra.join(); // Aguarda até que a threadCompra seja concluída
                threadVenda.join(); // Aguarda até que a threadVenda seja concluída
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // Permite que o programa principal continue a execução sem bloquear nas threads

            // Espera por algum tempo antes de encerrar o programa (por exemplo, 1 minuto)

            //LivroDeOfertas.imprimirLivroDeOfertas();

        }
    }

    public static void sysot(String s) {
        System.out.println(s + "\n");

    }

    public static void popular() {
        // Instanciando Brokers
        for (int i = 0; i < brokers.length; i++) {
            String brokerName = "Broker" + (i + 1);
            brokers[i] = new Broker(brokerName);
        }
    }

}
