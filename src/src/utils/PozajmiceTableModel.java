package utils;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import dto.ZahtevRezervacijeDTO;

public class PozajmiceTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 3937985517090995701L;
	private ArrayList<ZahtevRezervacijeDTO> lista;
	private String[] columnNames = { "Email člana", "Ime i prezime", "ISBN", "Naslov knjige", "Izdavač"};

	public PozajmiceTableModel(ArrayList<ZahtevRezervacijeDTO> lista) {
		this.lista = lista;
	}

	public void setData(ArrayList<ZahtevRezervacijeDTO> lista) {
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
		ZahtevRezervacijeDTO e = this.lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return e.getEmail();
		case 1:
			return e.getImePrezime();
		case 2:
			return e.getISBN();
		case 3:
			return e.getNaslovKnjige();
		case 4:
			return e.getIzdavac();
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

	public ArrayList<ZahtevRezervacijeDTO> getRows() {
		return this.lista;
	}
}
