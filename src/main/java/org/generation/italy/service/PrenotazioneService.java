package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Prenotazione;
import org.generation.italy.repository.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {
	
	@Autowired
	private PrenotazioneRepository repository;
	
	public List<Prenotazione> findAllSortByDataInizio() {
		return repository.findAll(Sort.by("dataInizio"));
	}
	
	public Prenotazione save(Prenotazione prenotazione) {
		return repository.save(prenotazione);
	}
	
	public Prenotazione getById(Integer id) {
		return repository.getById(id);
	}
}
