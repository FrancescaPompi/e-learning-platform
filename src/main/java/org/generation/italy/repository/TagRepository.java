package org.generation.italy.repository;

import java.util.List;

import org.generation.italy.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
	List<Tag> findByNomeContainingIgnoreCaseOrderByNome(String keyword);

}
