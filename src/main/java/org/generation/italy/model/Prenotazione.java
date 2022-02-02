package org.generation.italy.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Prenotazione {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String cognome;
	
	@NotNull
	private String email;
	
	@Column(name="data_prenotazione")
	@NotNull
	private LocalDate dataPrenotazione;

	@ManyToOne
	private Insegnante insegnante;
	
	@ManyToOne
	private FasceOrarie fasciaOraria;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataPrenotazione() {
		return dataPrenotazione;
	}

	public void setDataPrenotazione(LocalDate dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	public Insegnante getInsegnante() {
		return insegnante;
	}

	public void setInsegnante(Insegnante insegnante) {
		this.insegnante = insegnante;
	}

	public FasceOrarie getFasciaOraria() {
		return fasciaOraria;
	}

	public void setFasciaOraria(FasceOrarie fasciaOraria) {
		this.fasciaOraria = fasciaOraria;
	}
	
	public boolean compareTo(Prenotazione prenotazione) {
		if (this.dataPrenotazione.equals(prenotazione.dataPrenotazione) &&
				this.fasciaOraria.equals(prenotazione.fasciaOraria)) {
			return true;
		} else {
			return false;
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
