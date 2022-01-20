package br.com.mercearia.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mercearia.modelo.Cliente;
import br.com.mercearia.modelo.Usuario;
import br.com.mercearia.modelo.dto.AlteraClienteDto;
import br.com.mercearia.modelo.dto.ClienteDto;
import br.com.mercearia.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService service;

	@RequestMapping
	@Cacheable(value = "listaDeClientes")
	public Page<ClienteDto> buscarClientes(@PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.DESC) Pageable paginacao) {
		
		return service.buscarTodos(paginacao).map(ClienteDto::new);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> buscarPorId(@PathVariable(name = "id") Long id) {
		Optional<Cliente> optional = service.buscarPorId(id);
		
		if(optional.isPresent())
		return ResponseEntity.ok(ClienteDto.convert(optional.get()));
		
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/cadastro")
	@CacheEvict(value = "listaDeClientes", allEntries = true)// LIMPA O CACHE
	public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteDto clienteDto,
			UriComponentsBuilder uriBilder) {
		
		Cliente cliente = service.salvar(clienteDto);

		URI uri = uriBilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

		return ResponseEntity.created(uri).body(clienteDto);
	}
	
	@PutMapping("/{id}")
	@CacheEvict(value = "listaDeClientes", allEntries = true)// LIMPA O CACHE
	public ResponseEntity<AlteraClienteDto> alterar(@PathVariable Long id, @RequestBody AlteraClienteDto alteraClienteDto){
		
		if(this.service.alterar(id, alteraClienteDto))
		return ResponseEntity.ok(alteraClienteDto);
		
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{id}")
	@CacheEvict(value = "listaDeClientes", allEntries = true)// LIMPA O CACHE
	public ResponseEntity<Boolean> excluir(@PathVariable Long id){
		
		this.service.excluir(id);
		
		return ResponseEntity.ok(Boolean.TRUE);
	}
}
