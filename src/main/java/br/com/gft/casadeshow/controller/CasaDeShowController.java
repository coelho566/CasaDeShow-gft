package br.com.gft.casadeshow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.gft.casadeshow.model.HouseShow;

@Controller
public class CasaDeShowController {
	
	@RequestMapping("/casa")
	private String casa(Model model) {
		return "CasaDeShow";
	}
	
	@PostMapping("/salvar")
	private ModelAndView salvarCasa(HouseShow house) {
		ModelAndView mv = new ModelAndView("CasaDeShow");
		System.out.println(house.getName());
		
		return mv;
	}
}
