package utils;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import dto.KnjigaDTO;

public class KnjigaTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 3937985517090995701L;
	private ArrayList<KnjigaDTO> lista;
	private String[] columnNames = { "ISBN", "Naslov", "Autor", "Izdavač", "Žanr"};

	public KnjigaTableModel(ArrayList<KnjigaDTO> lista) {
		this.lista = lista;
	}

	public void setData(ArrayList<KnjigaDTO> lista) {
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
		KnjigaDTO e = this.lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return e.getIsbn();
		case 1:
			return e.getNaslov();
		case 2:
			return e.getListAutoraString();
		case 3:
			return e.getIzdavac();
		case 4:
			return e.getZanr();
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

	public ArrayList<KnjigaDTO> getRows() {
		return this.lista;
	}
}

