package entidades.ordem.factory;

import entidades.acao.AcaoEnum;
import entidades.ordem.Ordem;
import entidades.ordem.TipoOrdemEnum;
import entidades.ordem.Venda;

public class VendaFactory implements OrderFactory {
    public Ordem ciarOrder(int quantidade, double valor, String broker, TipoOrdemEnum tipo, AcaoEnum acao) {
        return new Venda(quantidade, valor, broker, tipo, acao);
    }
}
