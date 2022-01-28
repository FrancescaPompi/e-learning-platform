package org.generation.italy.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.generation.italy.model.Insegnante;
import org.generation.italy.model.PhotoForm;
import org.generation.italy.model.Prenotazione;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/amministrazione/insegnanti")
public class InsegnanteAmministratoreController {

	@Autowired
	private InsegnanteService insegnanteService;
	
	@Autowired
	private PrenotazioneService prenotazioneService;

	@GetMapping("/create")
	public String creaInsegnante(Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("insegnante", new PhotoForm());
		
		return "/amministrazione/insegnanti/edit";
	}

	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("insegnante") PhotoForm insegnante, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) throws IOException {
		if (bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			return "/amministrazione/insegnanti/edit";
		}

		try {
			insegnanteService.save(insegnante);
			redirectAttributes.addFlashAttribute("successMessage", "Insegnante salvata!");
		} catch (IOException e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Impossibile salvare l'insegnante!");
			e.printStackTrace();
		}

		return "redirect:/amministrazione/insegnanti";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("edit", true);
		model.addAttribute("insegnante", insegnanteService.getById(id));
		return "/amministrazione/insegnanti/edit";
	}

	@PostMapping("/edit/{id}")
	public String doUpdate(@PathVariable("id") Integer id, @Valid @ModelAttribute("insegnante") PhotoForm insegnante, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) throws IOException {
		if (bindingResult.hasErrors()) {
			model.addAttribute("edit", true);
			return "/amministrazione/insegnanti/edit";
		}
		try {
			insegnanteService.update(insegnante);
			redirectAttributes.addFlashAttribute("successMessage", "Insegnante salvata!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Impossibile salvare l'insegnante!");
			e.printStackTrace();
		}
		return "redirect:/amministrazione/insegnanti";
	}

	@GetMapping
	public String list(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
		List<Insegnante> result;
		if (keyword != null) {
			result = insegnanteService.findByKeywordSortedByCognome(keyword);
			model.addAttribute("keyword", keyword);
		} else
			result = insegnanteService.findAllSortedByCognome();
		model.addAttribute("list", result);
		return "/amministrazione/insegnanti/list";
	}

	@GetMapping("/delete/{id}")
	public String doDelete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		if (insegnanteService.getById(id) == null) {
			// Return error message
		}
		List<Prenotazione> listPrenotazioni = insegnanteService.getById(id).getPrenotazioni();
		prenotazioneService.deleteAll(listPrenotazioni);
		insegnanteService.deleteById(id);
		redirectAttributes.addFlashAttribute("successMessage", "Insegnante cancellato!");
		return "redirect:/amministrazione/insegnanti";
	}
	

}