package br.com.mercearia.modelo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.mercearia.modelo.Cliente;
import br.com.mercearia.modelo.SituacaoDoCliente;

public class AlteraClienteDto {
	
	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String nome;

	@NotNull
	@NotEmpty
	@Length(min = 2)
	private String sobrenome;

	@NotNull
	private SituacaoDoCliente situacao;

	@NotNull
	private BigDecimal saldo;

	public AlteraClienteDto( String nome,String sobrenome, SituacaoDoCliente situacao, BigDecimal saldo) {
		super();
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.situacao = situacao;
		this.saldo = saldo;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public SituacaoDoCliente getSituacao() {
		return situacao;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}
	
	public static Cliente convert(AlteraClienteDto alteraClienteDto) {
		Cliente cliente = new Cliente();
		
		cliente.setNome(alteraClienteDto.getNome());
		cliente.setSobrenome(cliente.getSobrenome());
		cliente.setSituacao(alteraClienteDto.getSituacao());
		cliente.setSaldo(alteraClienteDto.getSaldo());
		cliente.setCadastrador(null);

		return cliente;
	}
	
	
}
