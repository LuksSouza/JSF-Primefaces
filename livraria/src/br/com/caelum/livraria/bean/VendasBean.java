package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.caelum.livraria.dao.VendaDao;
import br.com.caelum.livraria.modelo.Venda;

@Named
@ViewScoped
public class VendasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Venda venda;
	
//	@Inject
//	private LivroDao livroDao;
	
	@Inject
	private VendaDao vendaDao;

	public Venda getVenda() {
		return this.venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	public BarChartModel getVendasModel() {
		
		BarChartModel model = new BarChartModel();
		
	    ChartSeries vendaSeries = new ChartSeries();
	    vendaSeries.setLabel("Vendas 2018");
	    
	    List<Venda> vendas = this.vendaDao.listaTodos();
	    
	    for (Venda venda : vendas) {
			vendaSeries.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}

	    model.setAnimate(true);
	    model.addSeries(vendaSeries);

	    return model;
	}

}
