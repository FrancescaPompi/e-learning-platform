package org.generation.italy.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.italy.model.Tag;
import org.generation.italy.service.CorsoService;
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
@RequestMapping("/amministrazione/tags")
public class TagController {
	@Autowired
	private CorsoService corsoService;

	@Autowired
	private TagService tagService;

	@GetMapping("/create")
	public String tag(Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("tagObj", new Tag());
//		Corso corso = corsoService.getById(corsoId);
//		model.addAttribute("corso", corso);
		return "/amministrazione/tags/edit";

	}

	@PostMapping("/create")
	public String doCreate(@Valid @ModelAttribute("tagObj") Tag formTag, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) throws Exception {
		if (bindingResult.hasErrors()) {
			model.addAttribute("edit", false);
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for (ObjectError e : allErrors) {
				System.out.println(e);

			}
			model.addAttribute("corsi", corsoService.findAllSortedByRecent());
			return "/amministrazione/tags/edit";

		}
		try {
			tagService.save(formTag);
			redirectAttributes.addFlashAttribute("successMessage", "Tag salvato nel sistema!");

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Impossibile salvare il tag!");
			e.printStackTrace();

		}

		return "redirect:/amministrazione/tags";
	}

	@GetMapping("/delete/{id}")
	public String doDelete(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
		if (tagService.getById(id) == null) {

		}
		try {
			tagService.deleteById(id);
			redirectAttributes.addFlashAttribute("successMessage", "Tag cancellato!");
		} catch (Exception e) {
			e.getMessage();
			redirectAttributes.addFlashAttribute("successMessage",
					"Impossibile cancellare il tag se associato ad un corso.");
		}
		return "redirect:/amministrazione/tags";

	}

	@GetMapping
	public String list(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
		List<Tag> lista = tagService.findAllSortByNome();
		model.addAttribute("list", lista);
		model.addAttribute("keyword", keyword);
		return "/amministrazione/tags/list";
	}

	@GetMapping("/edit/{id}")
	public String modificaCorso(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("edit", true);
		model.addAttribute("tagObj", tagService.getById(id));
		return "/amministrazione/tags/edit";
	}

	@PostMapping("/edit/{id}")
	public String modificaCorso(@Valid @ModelAttribute("corso") Tag formTag, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) throws Exception {

		if (bindingResult.hasErrors()) {
			model.addAttribute("edit", true);

			return "/amministrazione/tags/edit";
		}
		try {
			tagService.save(formTag);
			redirectAttributes.addFlashAttribute("successMessage", "Tag modificato nel sistema!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Impossibile salvare il tag!");
			e.printStackTrace();
		}
		return "redirect:/amministrazione/tags";

	}

}
