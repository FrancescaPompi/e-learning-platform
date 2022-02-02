package org.generation.italy.controller;

import javax.validation.Valid;

import org.generation.italy.model.Corso;
import org.generation.italy.model.CorsoLike;
import org.generation.italy.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/corsi")
public class LikeController {
	@Autowired
	private CorsoService service;

	@GetMapping("/{id}/detail/like")
	public CorsoLike getLike(@PathVariable("id") Integer id, Model model) {
		Corso corso = service.getById(id);
		model.addAttribute("like", corso.getMiPiace());
		return new CorsoLike(corso.getMiPiace());
	}

	@PostMapping("/{id}/detail/like")
	public CorsoLike postLike(@PathVariable("id") Integer id, CorsoLike corsoLike, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
		Corso corso = service.getById(id);
		int incrementLike = corsoLike.getNumeroLike();
		incrementLike += corso.getMiPiace();
		incrementLike++;
		corso.setMiPiace(incrementLike);
		model.addAttribute("like", corso.getMiPiace());
		service.update(corso);

		return new CorsoLike(corso.getMiPiace());

	}

}

// ඞ 