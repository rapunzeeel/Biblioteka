package model;

import java.util.Date;

public class Rezervacija {
	private Date datumRezervacije;
	private ZahtevZaRezervaciju zahtev;
	private Primerak primerak;
	public Rezervacija(Date datumRezervacije, ZahtevZaRezervaciju zahtev,
			Primerak primerak) {
		super();
		this.datumRezervacije = datumRezervacije;
		this.zahtev = zahtev;
		this.primerak = primerak;
	}
	
	public Date getDatumRezervacije() {
		return datumRezervacije;
	}
	public void setDatumRezervacije(Date datumRezervacije) {
		this.datumRezervacije = datumRezervacije;
	}
	public ZahtevZaRezervaciju getZahtev() {
		return zahtev;
	}
	public void setZahtev(ZahtevZaRezervaciju zahtev) {
		this.zahtev = zahtev;
	}
	public Primerak getPrimerak() {
		return primerak;
	}
	public void setPrimerak(Primerak primerak) {
		this.primerak = primerak;
	}
	
	
}
