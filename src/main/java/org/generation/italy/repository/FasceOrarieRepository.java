package org.generation.italy.repository;

import org.generation.italy.model.FasceOrarie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FasceOrarieRepository extends JpaRepository<FasceOrarie, Integer> {

}
