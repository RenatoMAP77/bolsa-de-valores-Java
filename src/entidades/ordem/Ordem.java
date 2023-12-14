package entidades.ordem;

import entidades.interfaces.Notificacao;

public interface Ordem extends Notificacao{
     /**
     * Obtém o tipo da ordem.
     *
     * @return Tipo da ordem (COMPRA, VENDA, INFO)
     */
    public TipoOrdemEnum getTipoOrdem();
    /**
     * Define o tipo da ordem.
     *
     * @param tipo Tipo da ordem (COMPRA, VENDA, INFO)
     */
    public void setTipoOrdem(TipoOrdemEnum tipo);
}
