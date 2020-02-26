package br.com.gft.casadeshow.model;

import java.math.BigDecimal;

public class CarrinhoItem {
	
	private Event event;
	private TipoPreco tipoPreco;
	
	public CarrinhoItem(Event event, TipoPreco tipoPreco) {

		this.event = event;
		this.tipoPreco = tipoPreco;
	}
	
	public BigDecimal getPreco() {
		return event.precoPara(tipoPreco);
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public TipoPreco getTipoPreco() {
		return tipoPreco;
	}

	public void setTipoPreco(TipoPreco tipoPreco) {
		this.tipoPreco = tipoPreco;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarrinhoItem other = (CarrinhoItem) obj;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		return true;
	}

	public BigDecimal getTotal(int quantidade) {
		return this.getPreco().multiply(new BigDecimal(quantidade));			
	}
}
