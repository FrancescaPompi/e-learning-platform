package org.generation.italy.controller;



import javax.validation.Valid;


import org.generation.italy.model.Insegnante;

import org.generation.italy.service.InsegnanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/index/amministrazione/insegnanti")
public class InsegnanteController {
	
	@Autowired
	private InsegnanteService insegnanteService;
	
	@GetMapping
	public String insegnanti(Model model) {
		return "/amministrazione/insegnanti/edit";
	}
	
	
	@GetMapping("/create")
	public String creaInsegnante(Model model) {
		model.addAttribute("insegnante", new Insegnante());
		return "/amministrazione/insegnanti/edit";
	}
	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("insegnante") Insegnante formInsegnante, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			
			return "/amministrazione/insegnanti/edit";
		}
		
		insegnanteService.save(formInsegnante);
		return "/amministrazione/insegnanti/edit";	
	}

}
