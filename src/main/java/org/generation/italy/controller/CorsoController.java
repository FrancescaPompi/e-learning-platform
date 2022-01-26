package org.generation.italy.controller;

import java.util.List;

import org.generation.italy.model.Corso;
import org.generation.italy.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/corsi")
public class CorsoController {
	
	@Autowired
	private CorsoService service;
	
	@GetMapping
	public String list(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
		List<Corso> result;
		if (keyword != null) {
			result = service.findByKeywordSortedByTitolo(keyword);
			model.addAttribute("keyword", keyword);
		} else
			result = service.findAllSortedByRecent();
		model.addAttribute("list", result);
		return "/corsi/list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id , Model model) {
		model.addAttribute("corso", service.getById(id));
		return "/corsi/detail";
	}
}
