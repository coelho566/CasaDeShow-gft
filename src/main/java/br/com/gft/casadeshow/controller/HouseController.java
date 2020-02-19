package br.com.gft.casadeshow.controller;

import java.io.InputStream;
import java.util.List;

import javax.persistence.GeneratedValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.casadeshow.model.House;
import br.com.gft.casadeshow.service.HouseService;

@Controller
@RequestMapping("/casa")
public class HouseController {

	@Autowired
	private HouseService service;
	
	@RequestMapping
	private ModelAndView casa() {
		House house = new House();
		List<House> listHouse = service.listaCasa();
		
		ModelAndView mv = new ModelAndView("cadastraCasa");

		mv.addObject("casa", house);
		mv.addObject("listaCasa", listHouse);
		return mv;
	}

	@PostMapping("/saveHouse")
	private ModelAndView salvarCasa(House house, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("cadastraCasa");
		
		if(service.hasHouse(house) == true) {
			mv.addObject("erro", "Casa de show já existe!");
			return mv;
		}
		
		service.saveHouse(house);
		attributes.addFlashAttribute("sucesso", "Casa de show salva com sucesso!");

		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("editarCasa");
		
		House house = service.get(id);
		mv.addObject("casa", house);
		
		return mv;
		
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		service.deletaCasa(id);
		
		attributes.addFlashAttribute("sucesso", "Casa de show excluida com sucesso");
		return "redirect:/";
	}
		
}
