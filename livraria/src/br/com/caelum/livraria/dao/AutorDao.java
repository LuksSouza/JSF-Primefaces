package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.tx.Log;

public class AutorDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;
	
	private DAO<Autor> dao;
	
	@PostConstruct
	public void init() {
		this.dao = new DAO<Autor>(this.em, Autor.class);
	}

	@Log
	public void adiciona(Autor t) {
		this.dao.adiciona(t);
	}

	@Log
	public void remove(Autor t) {
		this.dao.remove(t);
	}

	@Log
	public void atualiza(Autor t) {
		this.dao.atualiza(t);
	}

	@Log
	public List<Autor> listaTodos() {
		return this.dao.listaTodos();
	}

	@Log
	public Autor buscaPorId(Integer id) {
		return this.dao.buscaPorId(id);
	}
	
}
