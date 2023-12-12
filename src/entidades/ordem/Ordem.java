package entidades.ordem;

import entidades.interfaces.Notificacao;

public interface Ordem  extends Notificacao{
    public TipoOrdemEnum getTipoOrdem();
    public void setTipoOrdem(TipoOrdemEnum tipo);
}
