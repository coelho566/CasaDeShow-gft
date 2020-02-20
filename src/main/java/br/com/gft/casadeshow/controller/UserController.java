package br.com.gft.casadeshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.casadeshow.model.User;
import br.com.gft.casadeshow.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping("/cadastro")
	private ModelAndView cadastro() {
		
		ModelAndView mv = new ModelAndView("cadastraUsuario");
		mv.addObject(new User());
		
		return mv;
	}
	
	@PostMapping("/saveUser")
	private ModelAndView salvarUser(@Validated User user, BindingResult errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("cadastraUsuario");
		
		if(errors.hasErrors()) {
			return mv;
		}else if(service.hasUser(user) == true) {
			mv.addObject("erro", "Usuário já existe!");
			return mv;
		}
		
		service.saveUser(user);
		attributes.addFlashAttribute("sucesso", "Usuário cadastrado com sucesso!");
		
		return new ModelAndView("redirect:/login");
	}
}
