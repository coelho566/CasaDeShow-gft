package br.com.gft.casadeshow.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.gft.casadeshow.model.Event;
import br.com.gft.casadeshow.model.House;
import br.com.gft.casadeshow.model.MusicCategory;
import br.com.gft.casadeshow.service.EventService;
import br.com.gft.casadeshow.service.HouseService;

@Controller
@RequestMapping("/evento")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private HouseService houseService;
	
	@RequestMapping
	public ModelAndView evento() {
		ModelAndView mv = new ModelAndView();
		
		return mv;
	}
	
	@RequestMapping("/cadastraEvento")
	public ModelAndView cadastra() {
		ModelAndView mv = new ModelAndView("eventoCadastro");
		mv.addObject(new Event());
		return mv;
	}
	
	@PostMapping("/saveEvent")
	public ModelAndView saveEvent(@RequestParam MultipartFile file,  Event event) {
		ModelAndView mv = new ModelAndView("eventoCadastro");
		
		System.out.println();
	
		
		return mv;
	}
	
	
	@ModelAttribute("categoria")
	public List<MusicCategory> todasCategorias(){
		return Arrays.asList(MusicCategory.values());
	}
	
	@ModelAttribute("listaCasa")
	public List<House> listaCasa(){
		List<House> name = new ArrayList<>();
		houseService.listaCasa().forEach(house-> name.add(house));
		return name;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
