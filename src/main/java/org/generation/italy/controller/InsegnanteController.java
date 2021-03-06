package org.generation.italy.controller;

import java.util.List;

import org.generation.italy.model.Insegnante;

import org.generation.italy.model.PhotoForm;
import org.generation.italy.service.InsegnanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
			model.addAttribute("words", false);
			model.addAttribute("keyword", keyword);
		} else {
			result = service.findAllSortedByCognome();
		}
		model.addAttribute("list", result);
		return "/corso/insegnanti/list";
	}
	
	
	

	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("insegnante", service.getById(id));		
		
		model.addAttribute("immagine", new PhotoForm());
		model.addAttribute("list", service.getById(id));	
		
		return "/corso/insegnanti/detail";
	}
	
	@RequestMapping(value = "/{id}/foto", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getPhotoContent(@PathVariable Integer id){
		Insegnante insegnante = service.getById(id);
		byte[] photoContent = insegnante.getFoto();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(photoContent, headers, HttpStatus.OK);
	}

}
