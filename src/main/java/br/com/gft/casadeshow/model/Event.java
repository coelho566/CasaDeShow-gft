package br.com.gft.casadeshow.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "Preencha o campo nome")
	private String name;

	@NotNull(message = "Campo capacidade não pode esta vazio")
	private int capacity;

	@NotNull(message = "Preencha o campo data")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dateEvent;

	@ElementCollection
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn
	private List<Preco> precos;

	@Enumerated(EnumType.STRING)
	private MusicCategory category;

	private String foto;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private House houseShow;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Date getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(Date dateEvent) {
		this.dateEvent = dateEvent;
	}

	public List<Preco> getPrecos() {
		return precos;
	}

	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}

	public MusicCategory getCategory() {
		return category;
	}

	public void setCategory(MusicCategory category) {
		this.category = category;
	}

	public House getHouseShow() {
		return houseShow;
	}

	public void setHouseShow(House houseShow) {
		this.houseShow = houseShow;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Event other = (Event) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public BigDecimal precoPara(TipoPreco tipoPreco) {
		return precos.stream().filter(preco -> preco.getTipo().equals(tipoPreco)).findFirst().get().getValor();
	}

}
