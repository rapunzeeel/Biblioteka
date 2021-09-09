package dto;

import java.util.ArrayList;

import model.Autor;
import model.Zanr;

public class KnjigaDTO {
	private String isbn;
	private String naslov;
	private ArrayList<Autor> listAutora;
	private String izdavac;
	private Zanr zanr;
	
	public KnjigaDTO(String isbn, String naslov, ArrayList<Autor> listAutora, String izdavac, Zanr zanr) {
		super();
		this.isbn = isbn;
		this.naslov = naslov;
		this.listAutora = listAutora;
		this.izdavac = izdavac;
		this.zanr = zanr;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	public ArrayList<Autor> getListAutora() {
		return listAutora;
	}
	
	public String getListAutoraString()
	{
		String str="";
		for (Autor autor : listAutora) {
			str += autor.getIme()+" "+autor.getPrezime()+", ";
		}
		return str.substring(0, str.length()-2);
		
	}
	
	public void setListAutora(ArrayList<Autor> listAutora) {
		this.listAutora = listAutora;
	}
	public String getIzdavac() {
		return izdavac;
	}
	public void setIzdavac(String izdavac) {
		this.izdavac = izdavac;
	}
	public Zanr getZanr() {
		return zanr;
	}
	public void setZanr(Zanr zanr) {
		this.zanr = zanr;
	}

}
