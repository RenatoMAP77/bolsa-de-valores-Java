package entidades.ordem.factory;

import entidades.acao.AcaoEnum;
import entidades.ordem.Ordem;
import entidades.ordem.TipoOrdemEnum;

public interface OrderFactory {
    public Ordem ciarOrder(int quantidade, double valor, String broker, TipoOrdemEnum tipo, AcaoEnum acao);
} 