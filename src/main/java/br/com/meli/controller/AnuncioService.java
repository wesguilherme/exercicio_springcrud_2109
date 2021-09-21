package br.com.meli.controller;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.meli.entity.Anuncio;

@Service
public class AnuncioService {

	private AnuncioPersistence persistence = new AnuncioPersistence();

	private boolean codigoNaoUtilizado(String codigo) {
		for (Anuncio anuncio : persistence.listagem()) {
			if (anuncio.getCodigo().equals(codigo)) {
				return false;
			}
		}
		return true;
	}

	public Anuncio cadastrar(Anuncio anuncio) {
		if (codigoNaoUtilizado(anuncio.getCodigo())) {
			anuncio.setId(persistence.listagem().size() + 1L);
			return persistence.cadastro(anuncio);
		} else {
			throw new RuntimeException("Código já utilizado");
		}
	}

	public List<Anuncio> listagem() {
		return persistence.listagem();
	}

	public Anuncio obterAnuncio(Long id) {
		return persistence.obtemAnuncio(id);
	}

	public void removerAnuncio(Long id) {
		persistence.remove(id);
	}

	public Anuncio atualizarAnuncio(Anuncio anuncio) {
		return persistence.atualizar(anuncio);
	}
}
