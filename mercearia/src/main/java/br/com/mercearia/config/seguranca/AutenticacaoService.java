package br.com.mercearia.config.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mercearia.service.UsuarioService;

@Service
public class AutenticacaoService implements  UserDetailsService{
	
	@Autowired
	UsuarioService usuariService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuariService.buscarPorLogin(username);
	}

}
