package br.com.gft.casadeshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.casadeshow.model.Event;
import br.com.gft.casadeshow.repository.EventRepository;

@Service
public class EventService {
	
	@Autowired
	private EventRepository repository;
	
	public void saveEvent(Event event) {
		repository.save(event);
	}
}
