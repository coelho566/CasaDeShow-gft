package br.com.gft.casadeshow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.casadeshow.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	Event findByName(String name);
}
