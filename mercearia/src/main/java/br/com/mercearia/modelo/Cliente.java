package br.com.mercearia.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ManyToAny;

@Entity(name = "TB01_CLIENTES")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLI_ID")
	private Long id;

	@Column(name = "CLI_NOME")
	private String nome;

	@Column(name = "CLI_SOBRENOME")
	private String sobrenome;

	@Column(name = "CLI_DATA_CRIACAO")
	private LocalDateTime dataCriacao = LocalDateTime.now();

	@Column(name = "CLI_SITUACAO")
	@Enumerated(value = EnumType.STRING)
	private SituacaoDoCliente situacao = SituacaoDoCliente.NORMAL;

	@ManyToOne()
	private Usuario cadastrador;

	@Column(name = "CLI_SALDO")
	private BigDecimal saldo = BigDecimal.ZERO;

	@Column(name = "COM_ID")
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<Compra> compras = new ArrayList<>();

	public Cliente() {

	}

	public Cliente(String nome, String sobrenome, Usuario cadastrador) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cadastrador = cadastrador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cadastrador == null) ? 0 : cadastrador.hashCode());
		result = prime * result + ((compras == null) ? 0 : compras.hashCode());
		result = prime * result + ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		result = prime * result + ((sobrenome == null) ? 0 : sobrenome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (cadastrador == null) {
			if (other.cadastrador != null)
				return false;
		} else if (!cadastrador.equals(other.cadastrador))
			return false;
		if (compras == null) {
			if (other.compras != null)
				return false;
		} else if (!compras.equals(other.compras))
			return false;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (saldo == null) {
			if (other.saldo != null)
				return false;
		} else if (!saldo.equals(other.saldo))
			return false;
		if (situacao != other.situacao)
			return false;
		if (sobrenome == null) {
			if (other.sobrenome != null)
				return false;
		} else if (!sobrenome.equals(other.sobrenome))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public SituacaoDoCliente getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoDoCliente situacao) {
		this.situacao = situacao;
	}

	public Usuario getCadastrador() {
		return cadastrador;
	}

	public void setCadastrador(Usuario cadastrador) {
		this.cadastrador = cadastrador;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

}
