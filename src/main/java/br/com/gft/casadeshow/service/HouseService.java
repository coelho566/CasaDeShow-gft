package br.com.gft.casadeshow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.gft.casadeshow.model.House;
import br.com.gft.casadeshow.repository.HouseRepository;

@Service
public class HouseService {
	
	
	@Autowired
	private HouseRepository repository;
	
	public void saveHouse(House house) {
		repository.save(house);
	}
	
	public boolean hasHouse(House house) {
		
		if(repository.findByName(house.getName()) != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public List<House> listaCasa(){
		return repository.findAll();
	}
	
	public House get(Long id) {
		return repository.findById(id).get();
	}
	
	public void editar(Long id, House house) {
		
		House casa = this.get(id);
		
		repository.save(casa);
	}
	
	public void deletaCasa(Long id) {
		repository.deleteById(id);
	}
}
