package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Capitolo;
import org.generation.italy.repository.CapitoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CapitoloService {

	@Autowired
	private CapitoloRepository capitoloRepo;
	
	public Capitolo getById(Integer id) {
		return capitoloRepo.getById(id);
	}
	
	public List<Capitolo> getFindAll(){
		return capitoloRepo.findAll();
	}
}
