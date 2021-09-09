package dto;

public class IzvestajDTO {
	private String naslov;
	private int ukupanBroj;
	private float procenat;

	public IzvestajDTO(String naslov, int ukupanBroj, float procenat) {
		super();
		this.naslov = naslov;
		this.ukupanBroj = ukupanBroj;
		this.procenat = procenat;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public int getUkupanBroj() {
		return ukupanBroj;
	}

	public void setUkupanBroj(int ukupanBroj) {
		this.ukupanBroj = ukupanBroj;
	}

	public float getProcenat() {
		return procenat;
	}

	public void setProcenat(float procenat) {
		this.procenat = procenat;
	}

	@Override
	public String toString() {
		return "IzvestajDTO [naslov=" + naslov + ", ukupanBroj=" + ukupanBroj + ", procenat=" + procenat + "]";
	}

}
