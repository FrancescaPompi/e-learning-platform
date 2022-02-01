package org.generation.italy.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Insegnante {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@NotEmpty
	private String nome;
	
	@NotNull
	@NotEmpty
	private String cognome;
	
	@Lob
	private byte[] foto;
	
	@OneToMany(mappedBy="insegnante")
	private List<Prenotazione> prenotazioni;
	
	@ManyToMany(mappedBy="insegnanti")
	private List<Corso> corsi;

	/**
	 * @return the corsi
	 */
	public List<Corso> getCorsi() {
		return corsi;
	}

	/**
	 * @param corsi the corsi to set
	 */
	public void setCorsi(List<Corso> corsi) {
		this.corsi = corsi;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public List<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}
	
	public List<Prenotazione> getPrenotazioniFuture() {
		List<Prenotazione> prenotazioni = getPrenotazioni();
		List<Prenotazione> prossimePrenotazioni = new ArrayList<>();
		if(prenotazioni.size() != 0) {
			for(Prenotazione prenotazione : prenotazioni) {
				if(prenotazione.getDataPrenotazione().isAfter(LocalDate.now()) && prenotazione.getDataPrenotazione().isBefore(LocalDate.now().plusDays(7))) {
					prossimePrenotazioni.add(prenotazione);
				}
			}
		}
		return prossimePrenotazioni;	
	}

	public void setPrenotazioni(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}
	
	
}
