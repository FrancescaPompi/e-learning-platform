package org.generation.italy.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class FasceOrarie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@NotEmpty
	@Column(name="fascia_oraria")
	private String fasciaOraria;
	
	@OneToMany(mappedBy="fasciaOraria")
	private List<Prenotazione> prenotazioniFasciaOraria;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFasciaOraria() {
		return fasciaOraria;
	}

	public void setFasciaOraria(String fasciaOraria) {
		this.fasciaOraria = fasciaOraria;
	}

	public List<Prenotazione> getPrenotazioniFasciaOraria() {
		return prenotazioniFasciaOraria;
	}

	public void setPrenotazioniFasciaOraria(List<Prenotazione> prenotazioniFasciaOraria) {
		this.prenotazioniFasciaOraria = prenotazioniFasciaOraria;
	}
	
	
}
