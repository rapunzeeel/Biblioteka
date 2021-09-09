package model;

import java.util.Date;

public class ZahtevZaRezervaciju {
	private String clan;
	private Knjiga knjiga;
	private Date datumZahteva;
	
	public ZahtevZaRezervaciju(String clan, Knjiga knjiga, Date datumZahteva) {
		super();
		this.clan = clan;
		this.knjiga = knjiga;
		this.datumZahteva = datumZahteva;
	}
	
	
	public String getClan() {
		return clan;
	}
	public void setClan(String clan) {
		this.clan = clan;
	}
	public Knjiga getKnjiga() {
		return knjiga;
	}
	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}
	public Date getDatumZahteva() {
		return datumZahteva;
	}
	public void setDatumZahteva(Date datumZahteva) {
		this.datumZahteva = datumZahteva;
	}
	
	
}	
