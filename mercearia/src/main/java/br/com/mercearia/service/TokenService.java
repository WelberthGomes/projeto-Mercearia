package br.com.mercearia.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.mercearia.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authenticate) {
		
		Usuario logado = (Usuario) authenticate.getPrincipal();
		Date dataGeracao = new Date();
		Date dataExpiracao = new Date(dataGeracao.getTime() + Long.valueOf(expiration));
		
		return Jwts.builder()
		.setIssuer("API de mercearia")
		.setSubject(logado.getId().toString())
		.setIssuedAt(dataGeracao)
		.setExpiration(dataExpiracao)
		.signWith(SignatureAlgorithm.HS256 , secret)
		.compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		
		Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		
		return Long.parseLong(body.getSubject());
	}

}
