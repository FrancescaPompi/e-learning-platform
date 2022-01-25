package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Corso;
import org.generation.italy.repository.CorsoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CorsoService {

	@Autowired
	private CorsoRepository repo;
	
	public List<Corso> findAllSortedByRecent(){
		return repo.findAll(Sort.by("dataCreazione"));
	}
	
	public Corso getById(Integer id) {
		return repo.getById(id);
	}
	
	public List<Corso> findByKeywordSortedByTitolo(String keyword) {
		return repo.findByTitoloContainingIgnoreCaseOrderByTitolo(keyword);
	}
}
