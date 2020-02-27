package br.com.gft.casadeshow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.casadeshow.model.CarrinhoCompras;
import br.com.gft.casadeshow.model.CarrinhoItem;
import br.com.gft.casadeshow.model.Event;
import br.com.gft.casadeshow.model.TipoPreco;
import br.com.gft.casadeshow.service.EventService;

@Controller
@RequestMapping("/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController {

	@Autowired
	private EventService service;

	@Autowired
	private CarrinhoCompras carrinho;

	@RequestMapping("/add")
	public ModelAndView addItem(Integer produtoId, TipoPreco tipoPreco, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/");
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);
		carrinho.add(carrinhoItem);

		carrinhoItem.getPreco();

		attributes.addFlashAttribute("sucesso", "Item adicionado no carrinho!");

		return mv;
	}

	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
		Event event = this.service.get(produtoId);
		int qtdAtual = event.getCapacity() - 1;
		event.setCapacity(qtdAtual);
		service.save(event);

		CarrinhoItem carrinhoItem = new CarrinhoItem(event, tipoPreco);
		return carrinhoItem;
	}

	@RequestMapping(value = "/itens", method = RequestMethod.GET)
	public ModelAndView itens() {
		ModelAndView mv = new ModelAndView("itens");

		return mv;
	}

	@RequestMapping("/finalizar")
	public ModelAndView finalizar() {
		ModelAndView mv = new ModelAndView("redirect:/");
		System.out.println(carrinho.getItens().isEmpty());
		return mv;
	}

}
