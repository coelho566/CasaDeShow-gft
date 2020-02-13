package br.com.gft.casadeshow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.gft.casadeshow.model.User;
import br.com.gft.casadeshow.model.UserPrincipal;
import br.com.gft.casadeshow.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = repo.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User 404");
		}
		 
		return new UserPrincipal(user);
	}
	
}
