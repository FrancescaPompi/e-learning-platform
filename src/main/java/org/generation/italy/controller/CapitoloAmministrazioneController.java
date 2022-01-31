package org.generation.italy.controller;


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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/amministrazione/capitoli")
public class CapitoloAmministrazioneController {

	@Autowired
	private CapitoloService capService;

	@Autowired
	private CorsoService corsoService;

	@GetMapping("/create/{corsoId}")

	public String capitolo(@PathVariable("corsoId") Integer corsoId, Model model) {
//		List<Capitolo> ListaCorso = corsoService.getById(corsoId).getCapitoli();
		model.addAttribute("edit", false);

		model.addAttribute("capitoloObj", new Capitolo());

		Corso corso = corsoService.getById(corsoId);

		model.addAttribute("corso", corso);

		return "/amministrazione/capitoli/edit";

	}

	@PostMapping("/create/{corsoId}")
	public String doCreate(@PathVariable("corsoId") Integer corsoId,
			@Valid @ModelAttribute("capitoloObj") Capitolo formCapitolo, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) throws Exception{

		
		if (bindingResult.hasErrors()) {
			model.addAttribute("edit", false);

			return "/amministrazione/capitoli/edit";
		}
		
	
		Corso corso = corsoService.getById(corsoId);
		formCapitolo.setCorso(corso);
		
		try {	capService.save(formCapitolo);
	

		redirectAttributes.addFlashAttribute("successMessage", "Capitolo salvato nel sistema!");
	} catch (Exception e) {
		redirectAttributes.addFlashAttribute("errorMessage", "Impossibile salvare il capitolo!");
		e.printStackTrace();
	}

		


		return "redirect:/amministrazione/corsi/detail/" + corso.getId();
	}
	
	
	
	
	
	@GetMapping("/edit/{capitoloId}")

	public String editCapitolo(@PathVariable("capitoloId") Integer capitoloId, Model model) {
	
		
		model.addAttribute("edit", true);

		model.addAttribute("capitoloObj", capService.getById(capitoloId));

	
		return "/amministrazione/capitoli/edit";

	}

	@PostMapping("/edit/{corsoId}/{capitoloId}")
	public String doEdit(@PathVariable("capitoloId") Integer capitoloId, @PathVariable("corsoId") Integer corsoId,
			@Valid @ModelAttribute("capitoloObj") Capitolo formCapitolo, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("edit", true);

			return "/amministrazione/capitoli/edit";
		}try {

		Corso corso = corsoService.getById(corsoId);
		formCapitolo.setCorso(corso);
		formCapitolo.setId(capitoloId);
		capService.save(formCapitolo);
		redirectAttributes.addFlashAttribute("successMessage", "Capitolo modificato nel sistema!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Impossibile salvare il capitolo!");
			e.printStackTrace();
		}
		Corso corso = corsoService.getById(corsoId);
		formCapitolo.setCorso(corso);

		return "redirect:/amministrazione/corsi/detail/" + corso.getId();
	}
	
	
	
	
	
	
	

	@GetMapping("/delete/{corsoId}/{id}")
	public String doDelete(@PathVariable("id") Integer id, @PathVariable("corsoId") Integer corsoId, Model model,
			RedirectAttributes redirectAttributes) {
		capService.deleteById(id);

		model.addAttribute("corso", corsoService.getById(corsoId));
		redirectAttributes.addFlashAttribute("successMessage", "Capitolo cancellato!");
		return "redirect:/amministrazione/corsi/detail/{corsoId}";
	}

}
