package br.com.gft.casadeshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.casadeshow.model.User;
import br.com.gft.casadeshow.service.UserService;

@Controller
public class RegisterController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping("/cadastro")
	private ModelAndView cadastro() {
		User user = new User();
		ModelAndView mv = new ModelAndView("cadastraUsuario");
		mv.addObject("user", user);
		
		return mv;
	}
	
	@PostMapping("/saveUser")
	private ModelAndView salvarUser(@Validated User user, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("cadastraUsuario");
		
		if(errors.hasErrors() == true) {
			return mv;
		}
		
		if(service.hasUser(user) == true) {
			attributes.addFlashAttribute("erro", "Usuário já existe!");
			return mv;
		}
		
		service.saveUser(user);
		attributes.addFlashAttribute("sucesso", "Usuário cadastrado com sucesso!");
		
		return mv;
	}
}
