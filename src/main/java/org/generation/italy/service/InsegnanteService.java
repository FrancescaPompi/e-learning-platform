/**
 * 
 */
package org.generation.italy.service;

import java.io.IOException;
import java.util.List;

import org.generation.italy.model.Insegnante;
import org.generation.italy.model.PhotoForm;
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
	private InsegnanteRepository insegnanteRep;
	
	public List<Insegnante> findAllSortedByCognome() {
		return insegnanteRep.findAll(Sort.by("cognome"));
	}
	
	// Search bar method
	public List<Insegnante> findByKeywordSortedByCognome(String keyword) {
		return insegnanteRep.findByNomeContainingIgnoreCaseOrderByCognome(keyword);
	}
	
	public Insegnante getById(Integer id) {
		return insegnanteRep.getById(id);
	}
	
	public Insegnante save(PhotoForm newInsegnante) throws IOException {	
		Insegnante insegnante = new Insegnante();
		insegnante.setNome(newInsegnante.getNome());
		insegnante.setCognome(newInsegnante.getCognome());
		if(newInsegnante.getFoto() != null) {
			byte [] contentSerialized = newInsegnante.getFoto().getBytes();
			insegnante.setFoto(contentSerialized);
		}
		
		return insegnanteRep.save(insegnante);

	}
	
	public void deleteById(Integer id) {
		insegnanteRep.deleteById(id);
	}
	
	
	
	
		


}
