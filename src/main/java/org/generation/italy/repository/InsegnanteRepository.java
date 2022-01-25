/**
 * 
 */
package org.generation.italy.repository;

import java.util.List;

import org.generation.italy.model.Insegnante;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lucai
 *
 */
public interface InsegnanteRepository extends JpaRepository<Insegnante, Integer> {
	List<Insegnante> findByNomeContainingIgnoreCaseOrderByCognome(String keyword);
}
