package br.com.gft.casadeshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.gft.casadeshow.model.CarrinhoCompras;
import br.com.gft.casadeshow.model.CarrinhoItem;
import br.com.gft.casadeshow.model.Event;
import br.com.gft.casadeshow.model.TipoPreco;
import br.com.gft.casadeshow.service.EventService;

@Controller
@RequestMapping("/carrinho")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController {
	
	@Autowired
	private EventService service;
	
	@Autowired
	private CarrinhoCompras carrinho;
	
	@RequestMapping("/add")
	public ModelAndView addItem(Integer produtoId, TipoPreco tipoPreco ) {
		ModelAndView mv = new ModelAndView("redirect:/");
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);
		carrinho.add(carrinhoItem);
		
		System.out.println(">>> "+carrinhoItem.getPreco());
		
		return mv;
	}
	
	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
		Event event = this.service.get(produtoId);
		CarrinhoItem carrinhoItem = new CarrinhoItem(event, tipoPreco);
		return carrinhoItem;
	}
	
	@RequestMapping(value = "/itens", method=RequestMethod.GET)
	public ModelAndView itens() {
		ModelAndView mv = new ModelAndView("itens");
		
		return mv;
	}
}
