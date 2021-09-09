package model;

import java.util.ArrayList;

public class Knjiga {

	private String ISBN;
	private String naslov;
	private String godinaIzdavanja;
	private String godinaStampanja;
	private String izdavac;
	private String format;
	private TipKnjige tip;
	private Zanr zanr;
	private float ocena;
	private String slika;
	private ArrayList<Autor> autori;
	private String opis;
	private String kljucneReci;

	public Knjiga(String iSBN, String naslov, String godinaIzdavanja, String godinaStampanja, String izdavac, Zanr zanr,
			String format, TipKnjige tip, float ocena, String slika, ArrayList<Autor> autori, String opis, String kljucneReci) {
		super();
		ISBN = iSBN;
		this.naslov = naslov;
		this.godinaIzdavanja = godinaIzdavanja;
		this.godinaStampanja = godinaStampanja;
		this.izdavac = izdavac;
		this.format = format;
		this.tip = tip;
		this.zanr = zanr;
		this.ocena = ocena;
		this.slika = slika;
		this.autori = autori;
		this.opis = opis;
		this.kljucneReci = kljucneReci;
	}
	
	
	public String getKljucneReci() {
		return kljucneReci;
	}


	public void setKljucneReci(String kljucneReci) {
		this.kljucneReci = kljucneReci;
	}


	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Zanr getZanr() {
		return zanr;
	}

	public void setZanr(Zanr zanr) {
		this.zanr = zanr;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getGodinaIzdavanja() {
		return godinaIzdavanja;
	}

	public void setGodinaIzdavanja(String godinaIzdavanja) {
		this.godinaIzdavanja = godinaIzdavanja;
	}

	public String getGodinaStampanja() {
		return godinaStampanja;
	}

	public void setGodinaStampanja(String godinaStampanja) {
		this.godinaStampanja = godinaStampanja;
	}

	public String getIzdavac() {
		return izdavac;
	}

	public void setIzdavac(String izdavac) {
		this.izdavac = izdavac;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public TipKnjige getTip() {
		return tip;
	}

	public void setTip(TipKnjige tip) {
		this.tip = tip;
	}

	public float getOcena() {
		return ocena;
	}

	public void setOcena(float ocena) {
		this.ocena = ocena;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public ArrayList<Autor> getAutori() {
		return autori;
	}

	public void setAutori(ArrayList<Autor> autori) {
		this.autori = autori;
	}

	@Override
	public String toString() {
		return "Knjiga [ISBN=" + ISBN + ", naslov=" + naslov + ", godinaIzdavanja=" + godinaIzdavanja
				+ ", godinaStampanja=" + godinaStampanja + ", izdavac=" + izdavac + ", format=" + format + ", tip="
				+ tip + ", zanr=" + zanr + ", ocena=" + ocena + ", slika=" + slika + "]";
	}

}
