package model;

public class MestoPrimerka {
	
	private int sifra;
	private String nazivFilijale;
	private int brojPolice;
	private int brojReda;
	
	
	public MestoPrimerka(int sifra, String filijala, int brojPolice, int brojReda) {
		super();
		this.sifra = sifra;
		this.nazivFilijale = filijala;
		this.brojPolice = brojPolice;
		this.brojReda = brojReda;
	}
	public int getSifra() {
		return sifra;
	}
	public void setSifra(int sifra) {
		this.sifra = sifra;
	}
	public String getFilijala() {
		return nazivFilijale;
	}
	public void setFilijala(String filijala) {
		this.nazivFilijale = filijala;
	}
	public int getBrojPolice() {
		return brojPolice;
	}
	public void setBrojPolice(int brojPolice) {
		this.brojPolice = brojPolice;
	}
	public int getBrojReda() {
		return brojReda;
	}
	public void setBrojReda(int brojReda) {
		this.brojReda = brojReda;
	}
	
	
}
