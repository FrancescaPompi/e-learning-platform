package org.generation.italy.controller;

import java.io.IOException;

import javax.validation.Valid;


import org.generation.italy.model.PhotoForm;
import org.generation.italy.service.InsegnanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/amministrazione/insegnanti")
public class InsegnanteAmministratoreController {
	
	@Autowired
	private InsegnanteService insegnanteService;
	
	
	
	@GetMapping("/create")
	public String creaInsegnante(Model model) {
		model.addAttribute("insegnante", new PhotoForm());
		return "/amministrazione/insegnanti/edit";
	}
	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("insegnante") PhotoForm insegnante, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			
			return "/amministrazione/insegnanti/edit";
		}
		
		try {
			insegnanteService.save(insegnante);
			redirectAttributes.addFlashAttribute("successMessage", "Insegnante salvata!");
		} catch (IOException e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Impossibile salvare l'insegnante!");
			e.printStackTrace();
		}
		return "redirect:/insegnanti";	
	}

}