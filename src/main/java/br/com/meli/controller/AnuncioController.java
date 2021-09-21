package br.com.meli.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.meli.dto.AnuncioDTO;
import br.com.meli.entity.Anuncio;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/anuncios")
public class AnuncioController {

	@Autowired
	private AnuncioService service; //new AnuncioService()

	@PostMapping(value = "/cadastra")
	public ResponseEntity<AnuncioDTO> cadastro(@RequestBody AnuncioDTO objetoDesserializado, UriComponentsBuilder uriBuilder) {
		Anuncio anuncio = objetoDesserializado.converte(objetoDesserializado);
		Anuncio anuncioCadastrado = service.cadastrar(anuncio);
		URI uri = uriBuilder.path("/anuncios/obtem/{id}").buildAndExpand(anuncioCadastrado.getId()).toUri();
		return ResponseEntity.created(uri).body(objetoDesserializado.converteToDTO(anuncioCadastrado));
	}

	@GetMapping(value = "/obtem/{id}")
	public Anuncio obtemAnuncioPorId(@PathVariable("id") Long id) {
		return service.obterAnuncio(id);
	}

	@GetMapping(value = "/list")
	public List<AnuncioDTO> lista() {
		List<Anuncio> listaDeAnuncios = service.listagem();
		List<AnuncioDTO> listaDeDTOs = AnuncioDTO.converte(listaDeAnuncios);
		return listaDeDTOs;
	}

	@DeleteMapping(value = "/delete/{id}")
	public String remover(@PathVariable("id") Long id) {
		service.removerAnuncio(id);
		return "deletado";
	}

	@PutMapping(value = "/atualiza")
	public Anuncio atualizar(@RequestBody Anuncio anuncio) {
		return service.atualizarAnuncio(anuncio);
	}
}
