package utils;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import dto.IzvestajDTO;


public class IzvestajTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 3937985517090995701L;
	private ArrayList<IzvestajDTO> lista;
	private String[] columnNames = {"Naslov", "Broj pozajmica", "Procenat čitanosti"};
	
	public IzvestajTableModel(ArrayList<IzvestajDTO> lista, String prvaKolona) {
		this.lista = lista;
		this.columnNames[0] = prvaKolona;
	}
	
	public void setData(ArrayList<IzvestajDTO> lista) {
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
		IzvestajDTO e = this.lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return e.getNaslov();
		case 1:
			return e.getUkupanBroj();
		case 2:
			return e.getProcenat();
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
	
	public ArrayList<IzvestajDTO> getRows(){
		return this.lista;
	}
}
