package org.generation.italy.controller;

import org.generation.italy.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/corsi")
public class CorsoController {
	
	@Autowired
	private CorsoService service;
	
	@GetMapping
	public String list(Model model) {
		model.addAttribute("list", service.findAllSortedByRecent());
		return "/corsi/list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id , Model model) {
		model.addAttribute("corso", service.getById(id));
		return "/corsi/detail";
	}
}
