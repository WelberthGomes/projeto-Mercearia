package br.com.mercearia.config.seguranca;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.mercearia.modelo.Usuario;
import br.com.mercearia.repository.UsuarioRepository;
import br.com.mercearia.service.TokenService;
import br.com.mercearia.service.UsuarioService;
import jdk.internal.joptsimple.internal.Strings;

public class AutenticacaoViaTokenFilter  extends OncePerRequestFilter{
	
	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;
	
	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		
		boolean valido = tokenService.isTokenValido(token);
		
		if (valido) {
			auteticarCliente(token);
		}
		
		
		filterChain.doFilter(request, response);
	}

	private void auteticarCliente(String token) {
		
		Long id = tokenService.getIdUsuario(token);
		
		Usuario usuario = usuarioRepository.findById(id).get();
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if(token == null || !token.startsWith("Bearer ")) {
			return null;	
		}
		
		return token.substring(7, token.length());
	}

}
