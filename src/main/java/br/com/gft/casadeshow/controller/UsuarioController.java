package br.com.gft.casadeshow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {
	
	@RequestMapping("/login")
	private ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
		
		return mv;
	}
}
