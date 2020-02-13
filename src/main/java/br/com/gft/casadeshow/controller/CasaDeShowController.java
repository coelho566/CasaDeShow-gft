package br.com.gft.casadeshow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CasaDeShowController {
	
	@RequestMapping("/casa")
	private String casa(Model model) {
		return "CasaDeShow";
	}
}
