package model;

public class Recenzija {
	private String clan;
	private Knjiga knjiga;
	private int ocena;
	public Recenzija(String clan, Knjiga knjiga, int ocena) {
		super();
		this.clan = clan;
		this.knjiga = knjiga;
		this.ocena = ocena;
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
	public int getOcena() {
		return ocena;
	}
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	
	
}
