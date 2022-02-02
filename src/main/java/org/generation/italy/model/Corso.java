package org.generation.italy.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
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
public class Corso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "data_di_creazione") // , columnDefinition="date default CURRENT_DATE")
	@NotNull(message = "Inserisci la data di creazione")
	private LocalDate dataCreazione;

	@NotNull
	@NotEmpty(message = "Inserisci il titolo")
	private String titolo;

	@NotNull
	@NotEmpty(message = "Inserisci una descrizione")
	@Lob
	private String descrizione;

	@NotNull
	@NotEmpty(message = "Inserisci una categoria")
	private String categoria;

	@NotNull
	@NotEmpty(message = "Inserisci il livello di difficoltà")
	private String livello;

	@NotNull(message = "Inserisci la durata")

	private int durata;

	@Column(columnDefinition = "integer default 0")
	private Integer visualizzazioni = 0;

	@Column(columnDefinition = "integer default 0")
	private Integer miPiace = 0;

	@OneToMany(mappedBy = "corso")
	private List<Capitolo> capitoli;

	@ManyToMany
	@NotNull
	@NotEmpty(message = "Seleziona un insegnante")
	private List<Insegnante> insegnanti;

	@ManyToMany
	private List<Tag> tags;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDate dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getLivello() {
		return livello;
	}

	public void setLivello(String livello) {
		this.livello = livello;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	public Integer getVisualizzazioni() {
		return visualizzazioni;
	}

	public void setVisualizzazioni(Integer visualizzazioni) {
		this.visualizzazioni = visualizzazioni;
	}

	public Integer getMiPiace() {
		return miPiace;
	}

	public void setMiPiace(Integer miPiace) {
		this.miPiace = miPiace;
	}

	public List<Capitolo> getCapitoli() {
		return capitoli;
	}

	public void setCapitoli(List<Capitolo> capitoli) {
		this.capitoli = capitoli;
	}

	public List<Insegnante> getInsegnanti() {
		return insegnanti;
	}

	public void setInsegnanti(List<Insegnante> insegnanti) {
		this.insegnanti = insegnanti;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

}
