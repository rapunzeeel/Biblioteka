package model;

import java.util.Date;

public class Bibliotekar {
	private String email;
	private String lozinka;
	private String ime;
	private String prezime;
	private String jmbg;
	private Date datumRodj;
	private String adresa;
	private String nazivFilijale;
	private Mesto mesto;
	private TipZaposlenog tip;
	
	
	public Bibliotekar(String email, String lozinka, String ime, String prezime, String jmbg, Date datumRodj,
			String adresa, Mesto mesto, TipZaposlenog tip, String filijala) {
		super();
		this.email = email;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.datumRodj = datumRodj;
		this.adresa = adresa;
		this.nazivFilijale = filijala;
		this.mesto = mesto;
		this.tip = tip;
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
	public String getFilijala() {
		return nazivFilijale;
	}
	public void setFilijala(String filijala) {
		this.nazivFilijale = filijala;
	}
	public TipZaposlenog getTip() {
		return tip;
	}
	public void setTip(TipZaposlenog tip) {
		this.tip = tip;
	}
	public Mesto getMesto() {
		return mesto;
	}
	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}
	
	
	
}
