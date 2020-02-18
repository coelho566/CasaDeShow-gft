package br.com.gft.casadeshow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.casadeshow.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	User findByEmail(String email);

}
