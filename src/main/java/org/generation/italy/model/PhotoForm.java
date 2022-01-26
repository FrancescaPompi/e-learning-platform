package org.generation.italy.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class PhotoForm {
	
	private Integer id;
	
	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String cognome;
	
	private MultipartFile foto;
	
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

	public MultipartFile getFoto() {
		return foto;
	}

	public void setFoto(MultipartFile foto) {
		this.foto = foto;
	}


	
	
}
