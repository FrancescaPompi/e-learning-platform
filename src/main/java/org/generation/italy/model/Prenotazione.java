package org.generation.italy.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Prenotazione {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="data_prenotazione")
	@NotNull
	private LocalDate dataPrenotazione;
	
	@Lob
	private String note;
	
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
	
}
