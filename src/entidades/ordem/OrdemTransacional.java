package entidades.ordem;

import entidades.acao.AcaoEnum;

public interface OrdemTransacional extends Ordem {

    public int getQuantidade();
    public void setQuantidade(int quantidade);

    public void setValor(double valor);
    public double getValor();

    public String getBroker();
    public void setBroker(String broker);

    public TipoOrdemEnum getTipoOrdem();
    public void setTipoOrdem(TipoOrdemEnum tipo);

    public AcaoEnum getAcao();
    public void setAcao(AcaoEnum acao);

}
