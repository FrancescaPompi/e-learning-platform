package org.generation.italy.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.italy.model.Insegnante;
import org.generation.italy.model.Prenotazione;
import org.generation.italy.service.FasceOrarieService;
import org.generation.italy.service.InsegnanteService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/insegnanti/info")
public class PrenotazioneController {
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@Autowired
	private FasceOrarieService fasceOrarieService;
	
	@Autowired
	private InsegnanteService insegnanteService;
	
	
	@GetMapping("/{id}/prenota")
	public String prenota(@PathVariable("id") Integer id, Model model) {
		List<Prenotazione> listaPren = insegnanteService.getById(id).getPrenotazioni();
		model.addAttribute("list", listaPren);
		model.addAttribute("orariList", fasceOrarieService.findAllSortedByFasciaOraria());
		model.addAttribute("prenotazione", new Prenotazione());
		return "/corso/insegnanti/prenotazioni/form";
	}
	
	@PostMapping("/{idInsegnante}/prenota")
	public String doPrenota(@PathVariable("idInsegnante") Integer idInsegnante, @Valid @ModelAttribute("prenotazione") Prenotazione formPrenotazione,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("list", prenotazioneService.findAllSortByFasciaOraria());
			model.addAttribute("orariList", fasceOrarieService.findAllSortedByFasciaOraria());
			return "/corso/insegnanti/prenotazioni/form";
		}
		
		List<Prenotazione> list = insegnanteService.getById(idInsegnante).getPrenotazioni();
		Insegnante insegnante = insegnanteService.getById(idInsegnante);;
		
		boolean valid = true;
		for(Prenotazione pren : list) {
			if(pren.compareTo(formPrenotazione)) {
				valid = false;
			} 
		}
		if(!valid) {
			redirectAttributes.addFlashAttribute("errorMessage", "Prenotazione già esistente!");
		} else {
	
			formPrenotazione.setInsegnante(insegnante);
			prenotazioneService.save(formPrenotazione);
			redirectAttributes.addFlashAttribute("successMessage", "Prenotato!");
		}
			
		return "redirect:/insegnanti/info/" + insegnante.getId() + "/prenota";
	}
	
	@GetMapping("/delete/{idInsegnante}/{id}")
	public String delete(@PathVariable("id") Integer id, @PathVariable("idInsegnante") Integer idInsegnante, Model model) {
		prenotazioneService.deleteById(id);
		
		model.addAttribute("insegnante", insegnanteService.getById(idInsegnante));
		return "redirect:/insegnanti/info/{idInsegnante}";
	}
}
