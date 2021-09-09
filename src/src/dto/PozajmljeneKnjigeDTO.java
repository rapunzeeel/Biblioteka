package dto;

import java.util.ArrayList;
import java.util.Date;

import model.Knjiga;

public class PozajmljeneKnjigeDTO {
	ArrayList<Knjiga> listaPozajmljenihKnjiga = new ArrayList<>();
	ArrayList<Date> listDatuma = new ArrayList<>();
	ArrayList<Date> listaDatumaVracanja = new ArrayList<>();
	ArrayList<Integer> listaSifriPrimeraka = new ArrayList<>();

	public PozajmljeneKnjigeDTO(ArrayList<Knjiga> listaPozajmljenihKnjiga, ArrayList<Date> listDatuma,
			ArrayList<Date> listaDatumaVracanja, ArrayList<Integer> listaSifriPrimeraka) {
		super();
		this.listaPozajmljenihKnjiga = listaPozajmljenihKnjiga;
		this.listDatuma = listDatuma;
		this.listaDatumaVracanja = listaDatumaVracanja;
		this.listaSifriPrimeraka = listaSifriPrimeraka;
	}

	public ArrayList<Integer> getListaSifriPrimeraka() {
		return listaSifriPrimeraka;
	}

	public void setListaSifriPrimeraka(ArrayList<Integer> listaSifriPrimeraka) {
		this.listaSifriPrimeraka = listaSifriPrimeraka;
	}

	public ArrayList<Knjiga> getListaPozajmljenihKnjiga() {
		return listaPozajmljenihKnjiga;
	}

	public void setListaPozajmljenihKnjiga(ArrayList<Knjiga> listaPozajmljenihKnjiga) {
		this.listaPozajmljenihKnjiga = listaPozajmljenihKnjiga;
	}

	public ArrayList<Date> getListDatuma() {
		return listDatuma;
	}

	public void setListDatuma(ArrayList<Date> listDatuma) {
		this.listDatuma = listDatuma;
	}

	public ArrayList<Date> getListaDatumaVracanja() {
		return listaDatumaVracanja;
	}

	public void setListaDatumaVracanja(ArrayList<Date> listaDatumaVracanja) {
		this.listaDatumaVracanja = listaDatumaVracanja;
	}

}
