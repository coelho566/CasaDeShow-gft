package br.com.gft.casadeshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.casadeshow.model.User;
import br.com.gft.casadeshow.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public void saveUser(User user) {

		user.setRoles("USER");
		repository.save(user);

	}

	public boolean hasUser(User user) {
		
		if (repository.findByUsername(user.getUsername()) != null) {
			return true;
		}
		return false;
	}

}
