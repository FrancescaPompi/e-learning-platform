package org.generation.italy.controller;


import java.util.List;

import javax.validation.Valid;

import org.generation.italy.model.Capitolo;
import org.generation.italy.model.Corso;
import org.generation.italy.service.CapitoloService;
import org.generation.italy.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/amministrazione/capitoli")
public class CapitoloAmministrazioneController {
	
	@Autowired
	private CapitoloService capService;
	
	@Autowired
	private CorsoService corsoService;
	
	
	
	@GetMapping("/{corsoId}/create")
	public String creaCapitolo(@PathVariable("corsoId") Integer id, Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("capitolo", new Capitolo());
		model.addAttribute("corso", corsoService.getById(id));
		return "amministrazione/capitoli/edit";
		
	}
	

	@PostMapping("/{corsoId}/create")
	public String doCreate(@PathVariable("corsoId") Integer id, @Valid @ModelAttribute("capitolo") Capitolo formCapitolo, BindingResult bindingResult, Model model) {
	
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("edit", false);
			model.addAttribute("corso", corsoService.getById(id));		
			
			return "/amministrazione/capitoli/edit";
		}
	
		capService.save(formCapitolo);
		return "redirect:/amministrazione/corsi/detail/{corsoId}";	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id , Model model) {
		model.addAttribute("capitoli", capService.getById(id));
		return "/amministrazione/capitoli/detail";
	}

	
	
	
	
	
	
	@GetMapping("/delete/{id}")
	public String doDelete(@PathVariable("id") Integer id) {		
		capService.deleteById(id);
		return "redirect:/amministrazione/corsi/detail";
	}
	
	
	
	
	
	
	
	
	@GetMapping("/edit/{id}")
	public String modificaCorso(@PathVariable("id") Integer id, Model model) {	
		model.addAttribute("edit", true);
		model.addAttribute("capitolo", capService.getById(id));
		return "/amministrazione/capitoli/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String modificaCorso(@Valid @ModelAttribute("capitolo") Capitolo formCapitolo, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("capitolo", true);
						
			return "/amministrazione/capitoli/edit";
		}
		
		capService.save(formCapitolo);
	
		return "redirect:/amministrazione/corsi/detail";	
	}
	

	
	
	
	
}
