package org.generation.italy.service;



import org.generation.italy.model.Capitolo;

import org.generation.italy.repository.CapitoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CapitoloService {
	
	@Autowired
	private CapitoloRepository capitoloRepo;
	
	public Capitolo save(Capitolo capitolo) {
		return capitoloRepo.save(capitolo);
		

	}
	
	public void deleteById(Integer id) {
		capitoloRepo.deleteById(id);
	}
	

}
