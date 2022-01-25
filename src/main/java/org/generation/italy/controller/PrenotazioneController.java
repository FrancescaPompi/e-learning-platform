package org.generation.italy.controller;

import javax.validation.Valid;

import org.generation.italy.model.Prenotazione;
import org.generation.italy.service.PrenotazioneService;
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
@RequestMapping("/insegnanti/info")
public class PrenotazioneController {
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@GetMapping("/{id}/prenota")
	public String prenota(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("list", prenotazioneService.findAllSortByDataInizio());
		model.addAttribute("prenotazione", new Prenotazione());
		return "/corso/insegnanti/prenotazioni/form";
	}
	
	@PostMapping("/{id}/prenota")
	public String doPrenota(@Valid @ModelAttribute("prenotazione") Prenotazione prenotazione,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "/corso/insegnanti/prenotazioni/form";
		}
		prenotazioneService.save(prenotazione);
		return "redirect:/prenota" + prenotazione.getInsegnante().getId();
	}
	
}
