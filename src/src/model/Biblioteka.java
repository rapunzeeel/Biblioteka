package model;

import java.util.ArrayList;

public class Biblioteka {
	private String naziv;
	private ArrayList<Filijala> filijale;
	private ArrayList<Knjiga> knjige;
	private ArrayList<Clan> clanovi;
	
	
	public Biblioteka(String naziv, ArrayList<Filijala> filijale, ArrayList<Knjiga> knjige, ArrayList<Clan> clanovi) {
		super();
		this.naziv = naziv;
		this.filijale = filijale;
		this.knjige = knjige;
		this.clanovi = clanovi;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public ArrayList<Filijala> getFilijale() {
		return filijale;
	}
	public void setFilijale(ArrayList<Filijala> filijale) {
		this.filijale = filijale;
	}
	public ArrayList<Knjiga> getKnjige() {
		return knjige;
	}
	public void setKnjige(ArrayList<Knjiga> knjige) {
		this.knjige = knjige;
	}
	public ArrayList<Clan> getClanovi() {
		return clanovi;
	}
	public void setClanovi(ArrayList<Clan> clanovi) {
		this.clanovi = clanovi;
	}

	
		
}
