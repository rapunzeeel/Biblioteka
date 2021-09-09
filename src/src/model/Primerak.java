package model;

public class Primerak {
	
	private int sifra;
	private Boolean slobodan;
	private Knjiga knjiga;
	private MestoPrimerka mestoPrimerka;
	
	public Primerak(int sifra, Boolean slobodan, Knjiga knjiga, MestoPrimerka mestoPrimerka) {
		super();
		this.sifra = sifra;
		this.slobodan = slobodan;
		this.knjiga = knjiga;
		this.mestoPrimerka = mestoPrimerka;
	}
	public int getSifra() {
		return sifra;
	}
	public void setSifra(int sifra) {
		this.sifra = sifra;
	}
	public Boolean getSlobodan() {
		return slobodan;
	}
	public void setSlobodan(Boolean slobodan) {
		this.slobodan = slobodan;
	}
	public Knjiga getKnjiga() {
		return knjiga;
	}
	public void setKnjiga(Knjiga knjiga) {
		this.knjiga = knjiga;
	}
	public MestoPrimerka getMestoPrimerka() {
		return mestoPrimerka;
	}
	public void setMestoPrimerka(MestoPrimerka mestoPrimerka) {
		this.mestoPrimerka = mestoPrimerka;
	}
	
	
}
