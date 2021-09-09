package dto;

import java.sql.Date;

public class PozajmiceDTO {
	private int sifraPrimerka;
	private String isbn;
	private String naslovKnjige;
	private String izdavac;
	private Date datumIzdavanja;

	public PozajmiceDTO(int sifraPrimerka, String isbn, String naslovKnjige, String izdavac, Date datumIzdavanja) {
		super();
		this.isbn = isbn;
		this.naslovKnjige = naslovKnjige;
		this.izdavac = izdavac;
		this.datumIzdavanja = datumIzdavanja;
		this.sifraPrimerka = sifraPrimerka;
	}

	public String getISBN() {
		return isbn;
	}

	public void setISBN(String isbn) {
		this.isbn = isbn;
	}

	public String getNaslovKnjige() {
		return naslovKnjige;
	}

	public void setNaslovKnjige(String naslovKnjige) {
		this.naslovKnjige = naslovKnjige;
	}

	public String getIzdavac() {
		return izdavac;
	}

	public void setIzdavac(String izdavac) {
		this.izdavac = izdavac;
	}

	public Date getDatumIzdavanja() {
		return datumIzdavanja;
	}

	public void setDatumIzdavanja(Date datumIzdavanja) {
		this.datumIzdavanja = datumIzdavanja;
	}

	public int getSifraPrimerka() {
		return sifraPrimerka;
	}

	public void setSifraPrimerka(int sifraPrimerka) {
		this.sifraPrimerka = sifraPrimerka;
	}

	@Override
	public String toString() {
		return "PozajmiceDTO [sifraPrimerka=" + sifraPrimerka + ", isbn=" + isbn + ", naslovKnjige=" + naslovKnjige
				+ ", izdavac=" + izdavac + ", datumIzdavanja=" + datumIzdavanja + "]";
	}
	
	
}
