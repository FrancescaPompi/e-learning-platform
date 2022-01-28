package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Tag;
import org.generation.italy.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TagService {
	
	@Autowired
	private TagRepository tagRepository;
	
	public List<Tag> findAllSortByNome() {
		return tagRepository.findAll(Sort.by("nome"));
	}
}
