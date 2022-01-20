package br.com.mercearia.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.mercearia.modelo.Cliente;
import br.com.mercearia.modelo.dto.AlteraClienteDto;
import br.com.mercearia.modelo.dto.ClienteDto;
import br.com.mercearia.repository.ClienteRepository;

@Service
public class ClienteService {
	
	ClienteRepository repository;
	
	@Autowired
	public ClienteService(ClienteRepository repository) {
		this.repository = repository;
	}
	
	public Page<Cliente> buscarTodos(Pageable paginacao){
		return this.repository.findAll(paginacao);
	}

	public Optional<Cliente> buscarPorId( Long id) {
		
		return this.repository.findById(id);
	}
	
	@Transactional
	public Cliente salvar(ClienteDto clienteDto) {
		Cliente cliente = ClienteDto.convert(clienteDto);
		return this.repository.save(cliente);
		
	}
	
	@Transactional
	public Boolean alterar(Long id, AlteraClienteDto alteraClienteDto) {
		
		Optional<Cliente> optional = this.repository.findById(id);
		
		if(optional.isPresent()) {
			Cliente cliente = optional.get();
			cliente.setNome(alteraClienteDto.getNome());
			cliente.setSobrenome(alteraClienteDto.getSobrenome());
			cliente.setSituacao(alteraClienteDto.getSituacao());
			cliente.setSaldo(alteraClienteDto.getSaldo());
			return Boolean.TRUE;	
		}
		
		return Boolean.FALSE;
	}

	@Transactional
	public Boolean excluir(Long id) {
		
		try {
			this.repository.deleteById(id);
			return Boolean.TRUE;
			
		} catch (Exception e) {
			return Boolean.FALSE;
		}
		
		
	}

}
