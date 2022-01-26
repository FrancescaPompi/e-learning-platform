/**
 * 
 */
package org.generation.italy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lucai
 *
 */

@Controller
@RequestMapping("/amministrazione")
public class AmministrazioneController {

	@GetMapping
	public String index() {
		return "amministrazione/index";
	}
	
}
