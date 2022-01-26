package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.FasceOrarie;
import org.generation.italy.repository.FasceOrarieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FasceOrarieService {
	
	@Autowired
	private FasceOrarieRepository repository;
	
	public List<FasceOrarie> findAllSortedByFasciaOraria(){
		return repository.findAll(Sort.by("fasciaOraria"));
	}
}
