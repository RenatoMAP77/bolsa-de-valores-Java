package entidades.ordem;

import java.time.LocalDateTime;

import entidades.ordem.TipoOrdemEnum;

public class Info implements Ordem {
    private LocalDateTime dataHora;
    private TipoOrdemEnum tipo; 

    public Info(LocalDateTime dataHora, TipoOrdemEnum tipo) {
        this.dataHora = dataHora;
        this.tipo = tipo;
    }

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

    @Override
    public TipoOrdemEnum getTipoOrdem() {
        return this.tipo;
    }

    @Override
	public void setTipoOrdem(TipoOrdemEnum tipo) {
		this.tipo = tipo;
	}

}
