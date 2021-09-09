package utils;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import dto.PozajmiceDTO;

public class ZaduzeneKnjigeClanaTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 3937985517090995701L;
	private ArrayList<PozajmiceDTO> lista;
	private String[] columnNames = { "ISBN", "Naslov knjige", "Izdavač", "Datum pozajmice" };

	public ZaduzeneKnjigeClanaTableModel(ArrayList<PozajmiceDTO> lista) {
		this.lista = lista;
	}

	public void setData(ArrayList<PozajmiceDTO> lista) {
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
		PozajmiceDTO e = this.lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return e.getISBN();
		case 1:
			return e.getNaslovKnjige();
		case 2:
			return e.getIzdavac();
		case 3:
			return e.getDatumIzdavanja();
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

	public ArrayList<PozajmiceDTO> getRows() {
		return this.lista;
	}

}

