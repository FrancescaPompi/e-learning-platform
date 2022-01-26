package org.generation.italy.repository;

import java.util.List;

import org.generation.italy.model.Corso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorsoRepository extends JpaRepository<Corso, Integer> {

	List<Corso> findByTitoloContainingIgnoreCaseOrderByTitolo(String keyword);

}
