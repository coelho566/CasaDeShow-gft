package br.com.gft.casadeshow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.casadeshow.model.House;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
	
	House findByName(String name);
	
}
