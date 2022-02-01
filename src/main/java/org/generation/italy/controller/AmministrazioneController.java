
package org.generation.italy.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.generation.italy.model.Corso;
import org.generation.italy.model.Insegnante;
import org.generation.italy.service.CorsoService;
import org.generation.italy.service.InsegnanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/amministrazione")
public class AmministrazioneController {
	
	@Autowired
	private CorsoService corsoService;

	@Autowired
	private InsegnanteService insService;

	@GetMapping
	public String index(Model model) {
		// corsi più visualizzati
		List<Corso> piuVisualizzazioni = corsoService.corsiPiuVisualizzati();
		model.addAttribute("corsi", piuVisualizzazioni);
		
		// corsi più recenti
		List<Corso> corsi = corsoService.findAllSortedByRecent();
		List<Corso> listaRecente = new ArrayList<Corso>();
		for(int i = 0; i < corsi.size(); i++) {
			if(corsi.get(i).getDataCreazione().isAfter(LocalDate.now().minusDays(7)) && corsi.get(i).getDataCreazione().isBefore(LocalDate.now())) {
				listaRecente.add(corsi.get(i));
			}
		}
		model.addAttribute("listaRecente", listaRecente);
		
		// prenotazioni settimana successiva
		List<Insegnante> insegnanti = insService.findAllSortedByCognome();
		model.addAttribute("insegnanti", insegnanti);
		return "amministrazione/index";
	}
	
}
