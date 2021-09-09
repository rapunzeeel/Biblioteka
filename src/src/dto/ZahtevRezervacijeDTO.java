package dto;

import java.util.Date;

public class ZahtevRezervacijeDTO {
	private String email;
	private String imePrezime;
	private String isbn;
	private String naslovKnjige;
	private String izdavac;
	private Date datumZahteva;

	public ZahtevRezervacijeDTO(String email, String imePrezime, String isbn, String naslovKnjige, String izdavac, Date datumZahteva) {
		super();
		this.email = email;
		this.imePrezime = imePrezime;
		this.isbn = isbn;
		this.naslovKnjige = naslovKnjige;
		this.izdavac = izdavac;
		this.datumZahteva = datumZahteva;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImePrezime() {
		return imePrezime;
	}

	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
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

	public Date getDatumZahteva() {
		return datumZahteva;
	}

	public void setDatumZahteva(Date datumZahteva) {
		this.datumZahteva = datumZahteva;
	}

}
