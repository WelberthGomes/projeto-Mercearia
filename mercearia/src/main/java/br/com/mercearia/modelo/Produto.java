package br.com.mercearia.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "TB03_PRODUTOS")
public class Produto {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRD_ID")
	private Long id;
	
	@Column(name = "PRD_DESCRICAO")
	private String descricao;
	
	@Column(name = "PRD_PRECO_UNITARIO")
	private BigDecimal precoUnitario;
	
	@Column(name = "PRD_DATA_CADASTRO")
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@ManyToOne
	private Usuario cadastrador;
	
	@Column(name = "PRD_EM_ESTOQUE")
	private Boolean emEstoque = false;

	public Produto() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cadastrador == null) ? 0 : cadastrador.hashCode());
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((emEstoque == null) ? 0 : emEstoque.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((precoUnitario == null) ? 0 : precoUnitario.hashCode());
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
		Produto other = (Produto) obj;
		if (cadastrador == null) {
			if (other.cadastrador != null)
				return false;
		} else if (!cadastrador.equals(other.cadastrador))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (emEstoque == null) {
			if (other.emEstoque != null)
				return false;
		} else if (!emEstoque.equals(other.emEstoque))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (precoUnitario == null) {
			if (other.precoUnitario != null)
				return false;
		} else if (!precoUnitario.equals(other.precoUnitario))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Usuario getCadastrador() {
		return cadastrador;
	}

	public void setCadastrador(Usuario cadastrador) {
		this.cadastrador = cadastrador;
	}

	public Boolean getEmEstoque() {
		return emEstoque;
	}

	public void setEmEstoque(Boolean emEstoque) {
		this.emEstoque = emEstoque;
	};

}
