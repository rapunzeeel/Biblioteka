package model;

import java.util.Date;

public class Pozajmica {
	private String clan;
	private Primerak primerak;
	private Date datumPozajmljivanja;
	private Date datumVracanja;
	private Boolean ostecena;
	
	public Pozajmica(String clan, Primerak primerak, Date datumPozajmljivanja, Date datumVracanja, Boolean ostecena) {
		super();
		this.clan = clan;
		this.primerak = primerak;
		this.datumPozajmljivanja = datumPozajmljivanja;
		this.datumVracanja = datumVracanja;
		this.ostecena = ostecena;
	}
	
	public String getClan() {
		return clan;
	}
	public void setClan(String clan) {
		this.clan = clan;
	}
	public Primerak getPrimerak() {
		return primerak;
	}
	public void setPrimerak(Primerak primerak) {
		this.primerak = primerak;
	}
	public Date getDatumPozajmljivanja() {
		return datumPozajmljivanja;
	}
	public void setDatumPozajmljivanja(Date datumPozajmljivanja) {
		this.datumPozajmljivanja = datumPozajmljivanja;
	}
	public Date getDatumVracanja() {
		return datumVracanja;
	}
	public void setDatumVracanja(Date datumVracanja) {
		this.datumVracanja = datumVracanja;
	}
	public Boolean getOstecena() {
		return ostecena;
	}
	public void setOstecena(Boolean ostecena) {
		this.ostecena = ostecena;
	}
	
	
	
}
