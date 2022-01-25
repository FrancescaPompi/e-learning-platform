package org.generation.italy.controller;

import java.util.List;

import org.generation.italy.model.Insegnante;
import org.generation.italy.service.InsegnanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/insegnanti")
public class InsegnanteController {
	@Autowired
	private InsegnanteService service;

	@GetMapping
	public String list(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
		List<Insegnante> result;
		if (keyword != null) {
			result = service.findByKeywordSortedByCognome(keyword);
			model.addAttribute("keyword", keyword);
		} else
			result = service.findAllSortedByCognome();
		model.addAttribute("list", result);
		return "/corso/insegnanti/list";
	}

	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("insegnante", service.getById(id));
		return "/corso/insegnanti/info";
	}

}
