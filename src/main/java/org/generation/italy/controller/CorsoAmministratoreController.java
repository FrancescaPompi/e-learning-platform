package org.generation.italy.controller;


import java.util.List;

import javax.validation.Valid;

import org.generation.italy.model.Capitolo;
import org.generation.italy.model.Corso;
import org.generation.italy.model.Insegnante;
import org.generation.italy.model.Tag;
import org.generation.italy.service.CapitoloService;
import org.generation.italy.service.CorsoService;
import org.generation.italy.service.InsegnanteService;
import org.generation.italy.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/amministrazione/corsi")
public class CorsoAmministratoreController {

	@Autowired
	private CorsoService corsiService;

	@Autowired
	private InsegnanteService insegnanteService;

	@Autowired
	private TagService tagService;

	@Autowired
	private CapitoloService capService;

	@GetMapping
	public String corsi(Model model) {
		return "/amministrazione/corsi/edit";
	}

	@GetMapping("/create")
	public String creaCorso(Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("corso", new Corso());
		model.addAttribute("insegnanti", insegnanteService.findAllSortedByCognome());
		model.addAttribute("tags", tagService.findAllSortByNome());
		return "/amministrazione/corsi/edit";
	}

	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("corso") Corso formCorsi, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) throws Exception {

		if (bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for (ObjectError e : allErrors) {
				System.out.println(e);
			}
			model.addAttribute("insegnanti", insegnanteService.findAllSortedByCognome());
			model.addAttribute("tags", tagService.findAllSortByNome());
			return "/amministrazione/corsi/edit";

		}
		try {
			
			corsiService.save(formCorsi);
			redirectAttributes.addFlashAttribute("successMessage", "Corso salvato nel sistema!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Impossibile salvare il corso!");
			e.printStackTrace();
		}

		return "redirect:/amministrazione/corsi/list";
	}

	@GetMapping("/list")
	public String list(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
		List<Corso> lista = corsiService.listAll(keyword);
		model.addAttribute("list", lista);
		model.addAttribute("keyword", keyword);
		return "/amministrazione/corsi/list";
	}

	@GetMapping("/delete/{id}")
	public String doDelete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		if (corsiService.getById(id) == null) {

		}
		List<Capitolo> listCapitolo = corsiService.getById(id).getCapitoli();
		
		capService.deleteAll(listCapitolo);
	
		corsiService.deleteById(id);
		redirectAttributes.addFlashAttribute("successMessage", "Corso cancellato!");
		return "redirect:/amministrazione/corsi/list";
	}

	@GetMapping("/edit/{id}")
	public String modificaCorso(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("edit", true);
		model.addAttribute("corso", corsiService.getById(id));
		model.addAttribute("insegnanti", insegnanteService.findAllSortedByCognome());
		model.addAttribute("tags", tagService.findAllSortByNome());
		return "/amministrazione/corsi/edit";
	}

	@PostMapping("/edit/{id}")
	public String modificaCorso(@Valid @ModelAttribute("corso") Corso formCorsi, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) throws Exception{

		if (bindingResult.hasErrors()) {
			model.addAttribute("edit", true);

			return "/amministrazione/corsi/edit";
		}
		try {
		corsiService.save(formCorsi);
		redirectAttributes.addFlashAttribute("successMessage", "Corso modificato nel sistema!");
	} catch (Exception e) {
		redirectAttributes.addFlashAttribute("errorMessage", "Impossibile salvare il corso!");
		e.printStackTrace();
	}

		return "redirect:/amministrazione/corsi/list";
	}

	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("corso", corsiService.getById(id));
		List<Insegnante> listIns = corsiService.getById(id).getInsegnanti();
		model.addAttribute("listIns", listIns);
		List<Tag> listTag = corsiService.getById(id).getTags();
		model.addAttribute("listTag", listTag);
		return "/amministrazione/corsi/detail";
	}

}