package br.com.gft.casadeshow.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Preencha o campo Usuário")
	private String username;

	@NotEmpty(message = "Preencha o campo E-mail")
	private String email;

	@NotEmpty(message = "Preencha o campo Senha")
	private String password;

	private String roles = "";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getRoles() {
		return roles;
	}

	public List<String> getRolesList() {
		if (this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}

		return new ArrayList<>();
	}

}
