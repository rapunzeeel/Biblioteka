package utils;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import dto.BibliotekarDTO;

public class BibliotekarTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 3937985517090995701L;
	private ArrayList<BibliotekarDTO> lista;
	private String[] columnNames = { "Email", "Ime i prezime", "JMBG", "Adresa", "Naziv filijale"};

	public BibliotekarTableModel(ArrayList<BibliotekarDTO> lista) {
		this.lista = lista;
	}

	public void setData(ArrayList<BibliotekarDTO> lista) {
		this.lista = lista;
	}

	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}

	@Override
	public int getRowCount() {
		return this.lista.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		BibliotekarDTO e = this.lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return e.getEmail();
		case 1:
			return e.getImePrezime();
		case 2:
			return e.getJmbg();
		case 3:
			return e.getAdresa();
		case 4:
			return e.getNazivFilijale();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return this.columnNames[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return this.getValueAt(0, columnIndex).getClass();
	}

	public ArrayList<BibliotekarDTO> getRows() {
		return this.lista;
	}
}
