package model;

import java.util.ArrayList;
import java.util.Date;

public class Clan {
	private String email;
	private String lozinka;
	private String ime;
	private String prezime;
	private String jmbg;
	private Date datumRodj;
	private String adresa;
	private Mesto mesto;
	private TipClana tip;
	private Date datumUplate;
	private ArrayList<Pozajmica> pozajmice;
	private ArrayList<Recenzija> recenzije;
	
	
	public Clan(String email, String lozinka, String ime, String prezime, String jmbg, Date datumRodj, String adresa,
			Mesto mesto, TipClana tip, Date datumUplate, ArrayList<Pozajmica> pozajmice, ArrayList<Recenzija> recenzije) {
		super();
		this.email = email;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.datumRodj = datumRodj;
		this.adresa = adresa;
		this.mesto = mesto;
		this.tip = tip;
		this.pozajmice = pozajmice;
		this.recenzije = recenzije;
		this.datumUplate = datumUplate;
	}

	public Date getDatumUplate() {
		return datumUplate;
	}


	public void setDatumUplate(Date datumUplate) {
		this.datumUplate = datumUplate;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getLozinka() {
		return lozinka;
	}


	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
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


	public String getJmbg() {
		return jmbg;
	}


	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}


	public Date getDatumRodj() {
		return datumRodj;
	}


	public void setDatumRodj(Date datumRodj) {
		this.datumRodj = datumRodj;
	}


	public String getAdresa() {
		return adresa;
	}


	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}


	public Mesto getMesto() {
		return mesto;
	}


	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}


	public TipClana getTip() {
		return tip;
	}


	public void setTip(TipClana tip) {
		this.tip = tip;
	}


	public ArrayList<Pozajmica> getPozajmice() {
		return pozajmice;
	}


	public void setPozajmice(ArrayList<Pozajmica> pozajmice) {
		this.pozajmice = pozajmice;
	}


	public ArrayList<Recenzija> getRecenzije() {
		return recenzije;
	}


	public void setRecenzije(ArrayList<Recenzija> recenzije) {
		this.recenzije = recenzije;
	}
	
	
	
}
