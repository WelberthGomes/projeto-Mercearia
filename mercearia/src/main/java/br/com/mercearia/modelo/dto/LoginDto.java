package br.com.mercearia.modelo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginDto {
	
	@NotNull @NotEmpty
	private String email;
	
	@NotNull @NotEmpty
	private String senha;
	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public UsernamePasswordAuthenticationToken convert() {
		
		return new UsernamePasswordAuthenticationToken(this.email, this.senha);
	}
	
	
}
