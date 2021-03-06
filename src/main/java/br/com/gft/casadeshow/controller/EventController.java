package br.com.gft.casadeshow.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.casadeshow.model.Event;
import br.com.gft.casadeshow.model.House;
import br.com.gft.casadeshow.model.MusicCategory;
import br.com.gft.casadeshow.model.TipoPreco;
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
		List<Event> listaEvento = eventService.listEvent();

		mv.addObject("listaEvento", listaEvento);
		return mv;
	}

	@RequestMapping("/cadastraEvento")
	public ModelAndView cadastra() {
		List<House> listaCasa = houseService.listaCasa();
		ModelAndView mv = new ModelAndView("eventoCadastro");

		mv.addObject(new Event());
		mv.addObject("listaCasa", listaCasa);
		return mv;
	}

	@PostMapping("/saveEvent")
	public ModelAndView saveEvent(@RequestParam MultipartFile file, @Validated Event event, Errors errors,
			RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("eventoCadastro");

		if (errors.hasErrors()) {
			return mv;
		}else if(eventService.hasEvent(event) ==  true){
			
			mv.addObject("erro", "Evento já existe!");
			return mv;
			
		}

		eventService.saveEvent(event, file);
		attributes.addFlashAttribute("sucesso", "Evento cadastrado com sucesso!");

		return new ModelAndView("redirect:/evento");

	}

	@RequestMapping("editar/{id}")
	public ModelAndView editarEvento(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("editarEvento");
		Event evento = eventService.get(id);

		mv.addObject("tipoPreco", TipoPreco.values());
		mv.addObject("event", evento);

		return mv;
	}

	@PostMapping("/editEvent/{id}")
	public ModelAndView saveEdit(@PathVariable Integer id, @RequestParam MultipartFile file, @Validated Event event,
			Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("editarEvento");

		if (errors.hasErrors()) {
			return mv;
		}

		eventService.editar(id, event, file);

		attributes.addFlashAttribute("sucesso", "Evento editado com sucesso!");
		return new ModelAndView("redirect:/evento");
	}

	@GetMapping("/delete/{id}")
	public ModelAndView deleteEvent(@PathVariable Integer id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/");
		eventService.deleteEvent(id);

		attributes.addFlashAttribute("sucesso", "Evento excluido com sucesso");
		return mv;
	}
	
	@GetMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable Integer id) {
		Event event = eventService.get(id);
		ModelAndView mv = new ModelAndView("detalhes");
		mv.addObject("evento", event);
		
		return mv;
		
	}

	@ModelAttribute("tipoPreco")
	public List<TipoPreco> tipoPreco(){
		return Arrays.asList(TipoPreco.values());
	}

	@ModelAttribute("categoria")
	public List<MusicCategory> todasCategorias() {
		return Arrays.asList(MusicCategory.values());
	}

	@ModelAttribute("listaCasa")
	public List<House> listaCasa() {
		List<House> name = new ArrayList<>();
		houseService.listaCasa().forEach(house -> name.add(house));
		return name;
	}

}
