package br.com.gft.casadeshow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.gft.casadeshow.model.Historico;
import br.com.gft.casadeshow.service.HistoricoService;

@Controller
public class HistoricoController {
	
	@Autowired
	private HistoricoService service;
	
	@RequestMapping("/historico")
	private  ModelAndView historico() {
		ModelAndView mv = new ModelAndView("historico");
		List<Historico> histo = service.listHistorico();
		
		mv.addObject("listaHisto", histo);
		return mv;
	}
}
