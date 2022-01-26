package org.generation.italy.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.italy.model.Corso;
import org.generation.italy.service.CorsoService;
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
@RequestMapping("/amministrazione/corsi")
public class CorsoAmministratoreController {
	
	@Autowired
	private CorsoService corsiService;
	
	@GetMapping
	public String corsi(Model model) {		
		return "/amministrazione/corsi/edit";
	}
	
	
	@GetMapping("/create")
	public String creaCorso(Model model) {
		model.addAttribute("corso", new Corso());
		return "/amministrazione/corsi/edit";
	}
	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("corso") Corso formCorsi, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for (ObjectError e : allErrors) {
				System.out.println(e);
			}
			
			return "/amministrazione/corsi/edit";
		}
		
		corsiService.save(formCorsi);
		return "redirect:/corsi";	
	}
	
	

} 