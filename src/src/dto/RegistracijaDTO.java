package dto;

import java.util.Date;
import java.util.Random;

import model.TipClana;

public class RegistracijaDTO {
	private String email;
	private String lozinka;
	private String ime;
	private String prezime;
	private String jmbg;
	private Date datumRodjenja;
	private String adresa;
	private String postanskiBroj;
	private TipClana tipClana;

	public RegistracijaDTO(String email, String ime, String prezime, String jmbg, Date datumRodjenja, String adresa,
			String postanskiBroj, TipClana tipClana) {
		super();
		this.email = email;
		this.lozinka = generisiLozinku();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.datumRodjenja = datumRodjenja;
		this.adresa = adresa;
		this.postanskiBroj = postanskiBroj;
		this.tipClana = tipClana;
	}

	private String generisiLozinku() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

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

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getPostanskiBroj() {
		return postanskiBroj;
	}

	public void setPostanskiBroj(String postanskiBroj) {
		this.postanskiBroj = postanskiBroj;
	}

	public TipClana getTipClana() {
		return tipClana;
	}

	public void setTipClana(TipClana tipClana) {
		this.tipClana = tipClana;
	}

}
