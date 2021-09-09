package model;

import java.util.ArrayList;

public class Filijala {
	private String naziv;
	private String adresa;
	private String biblioteka;
	private String radniDan;
	private String subota;
	private Mesto mesto;
	private ArrayList<Bibliotekar> bibliotekari;
	
	public Filijala(String naziv, String adresa, String biblioteka, String radniDan, String subota, Mesto mesto,
			ArrayList<Bibliotekar> bibliotekari) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.biblioteka = biblioteka;
		this.radniDan = radniDan;
		this.subota = subota;
		this.mesto = mesto;
		this.bibliotekari = bibliotekari;
	}
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getBiblioteka() {
		return biblioteka;
	}
	public void setBiblioteka(String biblioteka) {
		this.biblioteka = biblioteka;
	}
	public String getRadniDan() {
		return radniDan;
	}
	public void setRadniDan(String radniDan) {
		this.radniDan = radniDan;
	}
	public String getSubota() {
		return subota;
	}
	public void setSubota(String subota) {
		this.subota = subota;
	}
	public Mesto getMesto() {
		return mesto;
	}
	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}
	public ArrayList<Bibliotekar> getBibliotekari() {
		return bibliotekari;
	}
	public void setBibliotekari(ArrayList<Bibliotekar> bibliotekari) {
		this.bibliotekari = bibliotekari;
	}
	
	
	
}
