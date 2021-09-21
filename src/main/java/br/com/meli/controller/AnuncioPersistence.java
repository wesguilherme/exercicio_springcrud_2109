package br.com.meli.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.meli.entity.Anuncio;

//classe especializada em realizar persistencia de anuncios

public class AnuncioPersistence {

	private static List<Anuncio> anuncios = new ArrayList<>();


	public Anuncio cadastro(Anuncio anuncio) {
		anuncio.setId(anuncios.size() + 1L);
		anuncios.add(anuncio);
		return anuncio;
	}

	public List<Anuncio> listagem() {
		return anuncios;
	}

	public Anuncio obtemAnuncio(Long id) {
		for (Anuncio anuncio : anuncios) {
			if (anuncio.getId().equals(id)) {
				return anuncio;
			}
		}
		return null;
	}

	public void remove(Long id) {
		for (Anuncio anuncio : anuncios) {
			if (anuncio.getId().equals(id)) {
				anuncios.remove(anuncio);
			}
		}
	}

	public Anuncio atualizar(Anuncio anuncio) {
		for (int i = 0; i < anuncios.size(); i++) {
			if (anuncios.get(i).getId().equals(anuncio.getId())) {
				anuncios.set(i, anuncio);
			}
		}
		return anuncio;
	}
}
