package org.generation.italy.service;



import org.generation.italy.model.Insegnante;

import org.generation.italy.repository.InsegnanteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class InsegnanteService {
	
	@Autowired
	private InsegnanteRepository insegnanteRepo;
	
	public Insegnante save(Insegnante insegnante) {		
		return insegnanteRepo.save(insegnante);

	}
	
	public void deleteById(Integer id) {
		insegnanteRepo.deleteById(id);
	}

}
