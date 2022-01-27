package org.generation.italy.service;

import java.util.List;

import java.util.List;

import org.generation.italy.model.Capitolo;
import org.generation.italy.repository.CapitoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
	
	public Capitolo save(Capitolo capitolo) {
		return capitoloRepo.save(capitolo);
	}
	
	public void deleteById(Integer id) {
		capitoloRepo.deleteById(id);
	}

	
	public List<Capitolo> findAllSortedByRecent(){
		return capitoloRepo.findAll(Sort.by("numeroCapitolo"));
	}


}
