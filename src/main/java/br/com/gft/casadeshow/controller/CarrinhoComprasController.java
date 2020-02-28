package br.com.gft.casadeshow.controller;

import java.util.Random;

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
import br.com.gft.casadeshow.model.Historico;
import br.com.gft.casadeshow.model.TipoPreco;
import br.com.gft.casadeshow.service.EventService;
import br.com.gft.casadeshow.service.HistoricoService;

@Controller
@RequestMapping("/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController {

	@Autowired
	private EventService service;
	
	@Autowired
	private CarrinhoCompras carrinho;
	
	@Autowired
	private HistoricoService hisService;

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
	
	@RequestMapping("/remover")
	public ModelAndView remover(Integer produtoId, TipoPreco tipoPreco, RedirectAttributes attributes) {
		
		Event event = service.get(produtoId);
		int qtd = event.getCapacity() + carrinho.getQuantidade();
		event.setCapacity(qtd);
		service.save(event);
		
		carrinho.remover(produtoId, tipoPreco);
		attributes.addFlashAttribute("sucesso", "Item removido do carrinho");
		return new ModelAndView("redirect:/");
	}

	@RequestMapping("/finalizar")
	public ModelAndView finalizar(RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("itens");
		
		if(carrinho.getItens().isEmpty()) {
			
			mv.addObject("erro", "Carrinho está vazio");
			return mv;
		}
		Random radom = new Random();
		
		for (CarrinhoItem element : carrinho.getItens()) {
			Historico histo = new Historico();
			int valor = radom.nextInt(999999999) + 111111111;
			
			histo.setNome(element.getEvent().getName());
			histo.setLocal(element.getEvent().getHouseShow().getName());
			histo.setDataEvento(element.getEvent().getDateEvent());
			histo.setQtd(carrinho.getQuantidade(element));
			histo.setTotal(element.getTotal(carrinho.getQuantidade(element)));
			histo.setCodigo(valor);
			
			hisService.saveHistorico(histo);
		}
		
		carrinho.getItens().clear();
		
		attributes.addFlashAttribute("sucesso", "Compra realizada com sucesso");
		return new ModelAndView("redirect:/");
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
