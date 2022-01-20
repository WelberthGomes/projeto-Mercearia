package br.com.mercearia.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ManyToAny;

@Entity(name = "TB02_COMPRAS")
public class Compra {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COM_ID")
	private Long id;
	
	@Column(name = "COM_DATA_COMPRA")
	private LocalDateTime dataDaCompra = LocalDateTime.now();
	
	
	@ManyToOne
	private Cliente cliente;
	
	@Column(name = "PRD_ID")
	@OneToMany(fetch = FetchType.LAZY)
	private List<Produto> produtos = new ArrayList<Produto>();

	public Compra() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataDaCompra == null) ? 0 : dataDaCompra.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((produtos == null) ? 0 : produtos.hashCode());
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
		Compra other = (Compra) obj;
		if (dataDaCompra == null) {
			if (other.dataDaCompra != null)
				return false;
		} else if (!dataDaCompra.equals(other.dataDaCompra))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (produtos == null) {
			if (other.produtos != null)
				return false;
		} else if (!produtos.equals(other.produtos))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataDaCompra() {
		return dataDaCompra;
	}

	public void setDataDaCompra(LocalDateTime dataDaCompra) {
		this.dataDaCompra = dataDaCompra;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	};

}
