/**
 * 
 */
package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Insegnante;
import org.generation.italy.repository.InsegnanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author lucai
 *
 */

@Service
public class InsegnanteService {

	@Autowired
	private InsegnanteRepository rep;
	
	public List<Insegnante> findAllSortedByCognome() {
		return rep.findAll(Sort.by("cognome"));
	}
	
	// Search bar method
	public List<Insegnante> findByKeywordSortedByCognome(String keyword) {
		return rep.findByNomeContainingIgnoreCaseOrderByCognome(keyword);
	}
	
	public Insegnante getById(Integer id) {
		return rep.getById(id);
	}
}
