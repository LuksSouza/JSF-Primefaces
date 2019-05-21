package br.com.caelum.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.livraria.modelo.Venda;

public class VendaDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;
	
	private DAO<Venda> dao;
	
	@PostConstruct
	public void init() {
		this.dao = new DAO<Venda>(em, Venda.class);
	}

	public void adiciona(Venda t) {
		dao.adiciona(t);
	}

	public void remove(Venda t) {
		dao.remove(t);
	}

	public void atualiza(Venda t) {
		dao.atualiza(t);
	}

	public List<Venda> listaTodos() {
		return dao.listaTodos();
	}

	public Venda buscaPorId(Integer id) {
		return dao.buscaPorId(id);
	}

}
