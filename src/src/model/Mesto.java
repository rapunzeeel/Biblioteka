package model;

public class Mesto {
	
	private int pttBroj;
	private String naziv;
	
	
	public Mesto(int pttBroj, String naziv) {
		super();
		this.pttBroj = pttBroj;
		this.naziv = naziv;
	}


	public int getPttBroj() {
		return pttBroj;
	}


	public void setPttBroj(int pttBroj) {
		this.pttBroj = pttBroj;
	}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	
}
