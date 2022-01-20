package br.com.mercearia.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mercearia.modelo.Usuario;
import br.com.mercearia.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Usuario buscarPorLogin(String username) {
		
		Optional<Usuario> optional =  usuarioRepository.findByEmail(username);
		
		if(optional.isPresent()) {
			return optional.get();	
		}
		
	   throw new UsernameNotFoundException("Usuário Inválido");
	}
}
