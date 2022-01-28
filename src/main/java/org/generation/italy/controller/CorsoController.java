package org.generation.italy.controller;

import java.util.List;

import org.generation.italy.model.Capitolo;
import org.generation.italy.model.Corso;
import org.generation.italy.model.Insegnante;
import org.generation.italy.service.CapitoloService;
import org.generation.italy.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/corsi")
public class CorsoController {
	
	@Autowired
	private CorsoService service;
	
	@Autowired
	private CapitoloService capitoloService;
	
	@GetMapping
	public String list(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
		List<Corso> result;
		if (keyword != null) {
			result = service.findByKeywordSortedByTitolo(keyword);
			model.addAttribute("keyword", keyword);
		} else
			result = service.findAllSortedByRecent();
		model.addAttribute("list", result);
		return "/corsi/list";
	}
	
	@GetMapping("/{id}/detail")
	public String detail(@PathVariable("id") Integer id , Model model) {
		model.addAttribute("corso", service.getById(id));
		List<Insegnante> listIns = service.getById(id).getInsegnanti();
		model.addAttribute("listIns", listIns);
		return "/corsi/detail";
	}
	
	@GetMapping("/capitolo/{id}/watch")
	public String capitolo(@PathVariable("id") Integer id, Model model) {
		Capitolo capitoloCorrente = capitoloService.getById(id);
		model.addAttribute("capitolo", capitoloCorrente);
		Corso corsoCorrente = service.getById(capitoloCorrente.getCorso().getId());
		model.addAttribute("corso", corsoCorrente);
		return "/corsi/capitolo/video";
	}
	
	@GetMapping("/capitolo/{id}/next")
	public String next(@PathVariable("id")Integer id, Model model) {
		Capitolo capitoloCorrente = capitoloService.getById(id);
		Corso corsoCorrente = service.getById(capitoloCorrente.getCorso().getId());
		
		List<Capitolo> listCap = corsoCorrente.getCapitoli();
		Capitolo next;
		
		try {
			next = null;
			for (Capitolo c : listCap) {
				if (capitoloCorrente.getNumeroCapitolo() + 1 == c.getNumeroCapitolo()) {
					next = c;
				}
			}
			return "redirect:/corsi/capitolo/" + next.getId() + "/watch";
		} catch (Exception e) {
			e.getMessage();
		}
		return "redirect:/corsi/" + corsoCorrente.getId() + "/detail";
	}
	
	@GetMapping("/capitolo/{id}/before")
	public String before(@PathVariable("id")Integer id, Model model) {
		Capitolo capitoloCorrente = capitoloService.getById(id);
		Corso corsoCorrente = service.getById(capitoloCorrente.getCorso().getId());
		
		List<Capitolo> listCap = corsoCorrente.getCapitoli();
		Capitolo before;
		
		try {
			before = null;
			for (Capitolo c : listCap) {

				if (capitoloCorrente.getNumeroCapitolo() - 1 == c.getNumeroCapitolo()) {
					before = c;
				}
			}
			return "redirect:/corsi/capitolo/" + before.getId() + "/watch";
			
		} catch (Exception e) {
			e.getMessage();
		}
		
		return "redirect:/corsi/" + corsoCorrente.getId() + "/detail";
	}
	
}
