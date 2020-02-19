package br.com.gft.casadeshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import br.com.gft.casadeshow.model.User;
import br.com.gft.casadeshow.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public void saveUser(User user) {
		
		String geradoSenha = BCrypt.gensalt();
		String senhaCrypt = BCrypt.hashpw(user.getPassword(), geradoSenha);
		
		user.setRoles("USER");
		user.setPassword(senhaCrypt);
		repository.save(user);

	}

	public boolean hasUser(User user) {
		
		if (repository.findByUsername(user.getUsername()) != null) {
			return true;
		}
		return false;
	}

}
