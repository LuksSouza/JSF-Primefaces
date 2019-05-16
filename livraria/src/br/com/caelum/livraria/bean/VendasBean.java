package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Venda;

@Named
@ViewScoped
public class VendasBean implements Serializable {
	
	private Venda venda;
	private DAO<Livro> livroDao = new DAO<Livro>(Livro.class);

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	public List<Venda> getVendas() {
		
		List<Livro> livros = livroDao.listaTodos();
		List<Venda> vendas = new ArrayList<Venda>();
		
		Random random = new Random(1234);
		
		for (Livro livro : livros) {
			vendas.add(new Venda(livro, random.nextInt(500)));
		}
				
		return vendas;
	}
	
	public BarChartModel getVendasModel() {
		
		BarChartModel model = new BarChartModel();
		
	    ChartSeries vendaSeries = new ChartSeries();
	    vendaSeries.setLabel("Vendas 2018");
	    
	    List<Venda> vendas = getVendas();
	    
	    for (Venda venda : vendas) {
			vendaSeries.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}

	    model.addSeries(vendaSeries);

	    return model;
	}

}
