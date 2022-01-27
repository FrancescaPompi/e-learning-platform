package org.generation.italy.controller;


import java.util.List;

import javax.validation.Valid;

import org.generation.italy.model.Capitolo;

import org.generation.italy.service.CapitoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/amministrazione/capitoli")
public class CapitoloAmministrazioneController {
	
	@Autowired
	private CapitoloService capService;
	
	@GetMapping("/create")
	public String creaCapitolo(Model model) {
		model.addAttribute("capitolo", new Capitolo());
		return "amministrazione/capitoli/edit";
		
	}
	

	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("capitolo") Capitolo formCapitolo, BindingResult bindingResult, Model model) {
	
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for (ObjectError e : allErrors) {
				System.out.println(e);
			}
			
			return "/amministrazione/capitoli/edit";
		}
	
		capService.save(formCapitolo);
		return "redirect:/amministrazione/capitoli/list";	
	}
	
	
}
