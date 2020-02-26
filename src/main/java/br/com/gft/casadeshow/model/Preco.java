package br.com.gft.casadeshow.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

@Embeddable
public class Preco {
	
	@NotNull(message = "Valor obrigatorio")
	@DecimalMin(value = "0.01", message = "Valor não pode ser menor que 0,01")
	@NumberFormat(pattern = "#, ##0.00")
	private BigDecimal valor;
	private TipoPreco tipo;
	

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoPreco getTipo() {
		return tipo;
	}

	public void setTipo(TipoPreco tipo) {
		this.tipo = tipo;
	}
	
	
}
