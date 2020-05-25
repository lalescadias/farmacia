package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drograria.domain.Fabricante;

@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBean {
	private ListDataModel<Fabricante> itens;
	private Fabricante fabricante;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public ListDataModel<Fabricante> getItens() {
		return itens;
	}

	public void setItens(ListDataModel<Fabricante> itens) {
		this.itens = itens;
	}

	@PostConstruct // esse metodo sera chamado automaticamente antes da pagina ser desenhada
					//serve pra disparar quando a pagina for carregada
	public void prepararPesquisa() {// se chama asssim pq ele ira rodar antes de exibir
		try {
			FabricanteDAO dao = new FabricanteDAO();
			ArrayList<Fabricante> lista = dao.listar();
			itens = new ListDataModel<Fabricante>(lista);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}
	public void prepararNovo() {
		fabricante = new Fabricante();
	}

	public void novo() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.salvar(fabricante);
			ArrayList<Fabricante> lista = dao.listar();
			itens = new ListDataModel<Fabricante>(lista);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

}
