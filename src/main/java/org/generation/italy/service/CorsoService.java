package org.generation.italy.service;

import java.time.LocalDate;
import java.util.List;

import org.generation.italy.model.Corso;


import org.generation.italy.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CorsoService {

	@Autowired
	private CorsoRepository corsoRepo;
	
	public List<Corso> findAllSortedByRecent(){
		return corsoRepo.findAll(Sort.by("dataCreazione"));
	}
	
	public Corso getById(Integer id) {
		return corsoRepo.getById(id);
	}
	
	public List<Corso> findByKeywordSortedByTitolo(String keyword) {
		return corsoRepo.findByTitoloContainingIgnoreCaseOrderByTitolo(keyword);
	}
	
	public Corso save(Corso corso) {
		if (corso.getDataCreazione() == null)
			corso.setDataCreazione(LocalDate.now());
		
		return corsoRepo.save(corso);

	}
	
	public void deleteById(Integer id) {
		corsoRepo.deleteById(id);
	}
	
	public Corso update(Corso corso) {		
		return corsoRepo.save(corso);

}
}
