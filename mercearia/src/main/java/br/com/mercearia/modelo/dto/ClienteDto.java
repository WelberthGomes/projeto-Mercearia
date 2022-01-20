package br.com.mercearia.modelo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.mercearia.modelo.Cliente;
import br.com.mercearia.modelo.SituacaoDoCliente;

public class ClienteDto {

	private Long id;

	@NotNull
	@NotEmpty
	@Length(min = 3)
	private String nome;

	@NotNull
	@NotEmpty
	@Length(min = 2)
	private String sobrenome;

	@NotNull
	private LocalDateTime dataCriacao;

	@NotNull
	private SituacaoDoCliente situacao;

	@NotNull
	private BigDecimal saldo;

	public ClienteDto() {
	}

	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.sobrenome = cliente.getSobrenome();
		this.dataCriacao = cliente.getDataCriacao();
		this.situacao = cliente.getSituacao();
		this.saldo = cliente.getSaldo();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public SituacaoDoCliente getSituacao() {
		return situacao;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public static List<ClienteDto> covert(List<Cliente> clientes) {
		return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());

	}

	public static ClienteDto convert(Cliente cliente) {
		ClienteDto clienteDto = new ClienteDto(cliente);
		return clienteDto;
	}

	public static Cliente convert(ClienteDto clienteDto) {
		Cliente cliente = new Cliente();

		cliente.setDataCriacao(clienteDto.getDataCriacao());
		cliente.setNome(clienteDto.getNome());
		cliente.setSobrenome(cliente.getSobrenome());
		cliente.setSituacao(clienteDto.getSituacao());
		cliente.setSaldo(clienteDto.getSaldo());
		cliente.setCadastrador(null);

		return cliente;
	}

	public static Cliente convert(AlteraClienteDto alteraClienteDto) {
		Cliente cliente = new Cliente();

		cliente.setNome(alteraClienteDto.getNome());
		cliente.setSobrenome(alteraClienteDto.getSobrenome());
		cliente.setSituacao(alteraClienteDto.getSituacao());
		cliente.setSaldo(alteraClienteDto.getSaldo());
		cliente.setCadastrador(null);

		return cliente;
	}

}
