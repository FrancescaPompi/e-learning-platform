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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
		model.addAttribute("edit", false);
		model.addAttribute("corso", new Corso());
		return "/amministrazione/corsi/edit";
	}
	
	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("corso") Corso formCorsi, BindingResult bindingResult, Model model) {
	
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for (ObjectError e : allErrors) {
				System.out.println(e);
			}
			
			return "/amministrazione/corsi/edit";
		}
	
		corsiService.save(formCorsi);
		return "redirect:/amministrazione/corsi/list";	
	}
	
	
	
	@GetMapping ("/list")
	public String list(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
		List<Corso> result;
		if (keyword != null) {
			result = corsiService.findByKeywordSortedByTitolo(keyword);
			model.addAttribute("keyword", keyword);
		} else
			result = corsiService.findAllSortedByRecent();
		model.addAttribute("list", result);
		return "/amministrazione/corsi/list";
	}
	
	
	@GetMapping("/delete/{id}")
	public String doDelete(@PathVariable("id") Integer id) {		
		corsiService.deleteById(id);
		return "redirect:/amministrazione/corsi/list";
	}
	
	
	
	
	@GetMapping("/edit/{id}")
	public String modificaCorso(@PathVariable("id") Integer id, Model model) {	
		model.addAttribute("edit", true);
		model.addAttribute("corso", corsiService.getById(id));
		return "/amministrazione/corsi/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String modificaCorso(@Valid @ModelAttribute("corso") Corso formCorsi, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("edit", true);
						
			return "/amministrazione/corsi/edit";
		}
		
		corsiService.update(formCorsi);
		return "redirect:/amministrazione/corsi/list";	
	}

} 