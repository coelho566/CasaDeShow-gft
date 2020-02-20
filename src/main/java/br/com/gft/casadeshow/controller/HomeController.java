package br.com.gft.casadeshow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.gft.casadeshow.model.Event;
import br.com.gft.casadeshow.service.EventService;

@Controller
public class HomeController {
	
	@Autowired
	private EventService service;
	
	@RequestMapping
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("index");
		List<Event> listaEvento = service.listEvent();
		
		mv.addObject("listaEvento", listaEvento);
		return mv;
	}
}
