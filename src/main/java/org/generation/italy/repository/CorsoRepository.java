package org.generation.italy.repository;

import java.util.List;

import org.generation.italy.model.Corso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CorsoRepository extends JpaRepository<Corso, Integer> {
	
	@Query("SELECT c FROM Corso c WHERE CONCAT(c.titolo, ' ', c.livello, ' ', c.categoria, ' ', c.durata) LIKE %?1%")
    public List<Corso> search(String keyword);
	
	@Query(nativeQuery = true, value="select * from Corso c order by visualizzazioni desc limit 5")
	public List<Corso> dashboard();
}
