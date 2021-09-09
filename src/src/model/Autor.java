package model;

import java.util.ArrayList;

public class Autor {
	private int idAutor;
	private String ime;
	private String prezime;
	private String slika;
	
	public Autor() {};
	
	public Autor(int idAutor, String ime, String prezime, String slika) {
		super();
		this.idAutor = idAutor;
		this.ime = ime;
		this.prezime = prezime;
		this.slika = slika;
	}
	public int getIdAutor() {
		return idAutor;
	}
	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getSlika() {
		return slika;
	}
	public void setSlika(String slika) {
		this.slika = slika;
	}
	
}
