package dto;

public class NaprednaPretragaDTO {
	private String naslov;
	private String autor;
	private String izdavac;
	private String godinaIzdavanja;
	private String kljucneReci;

	private String operator1;
	private String operator2;
	private String operator3;
	private String operator4;

	public NaprednaPretragaDTO(String naslov, String autor, String izdavac, String godinaIzdavanja, String kljucneReci,
			String operator1, String operator2, String operator3, String operator4) {
		super();
		this.naslov = naslov;
		this.autor = autor;
		this.izdavac = izdavac;
		this.godinaIzdavanja = godinaIzdavanja;
		this.kljucneReci = kljucneReci;
		this.operator1 = operator1;
		this.operator2 = operator2;
		this.operator3 = operator3;
		this.operator4 = operator4;
	}

	public NaprednaPretragaDTO(String naslov, String autor, String izdavac, String godinaIzdavanja,
			String kljucneReci) {
		super();
		this.naslov = naslov;
		this.autor = autor;
		this.izdavac = izdavac;
		this.godinaIzdavanja = godinaIzdavanja;
		this.kljucneReci = kljucneReci;
		this.operator1 = "OR";
		this.operator2 = "OR";
		this.operator3 = "OR";
		this.operator4 = "OR";
	}

	public String getNaslov() {
		if(naslov.equals("Naslov"))
			return "";
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getAutor() {
		if(autor.equals("Autor"))
			return "";
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIzdavac() {
		if(izdavac.equals("Izdavač"))
			return "";
		return izdavac;
	}

	public void setIzdavac(String izdavac) {
		this.izdavac = izdavac;
	}

	public String getGodinaIzdavanja() {
		if(godinaIzdavanja.equals("Godina izdavanja"))
			return "";
		return godinaIzdavanja;
	}

	public void setGodinaIzdavanja(String godinaIzdavanja) {
		this.godinaIzdavanja = godinaIzdavanja;
	}

	public String getKljucneReci() {
		if(kljucneReci.equals("Ključne reči"))
			return "";
		return kljucneReci;
	}

	public void setKljucneReci(String kljucneReci) {
		this.kljucneReci = kljucneReci;
	}

	public String getOperator1() {
		return operator1;
	}

	public void setOperator1(String operator1) {
		this.operator1 = operator1;
	}

	public String getOperator2() {
		return operator2;
	}

	public void setOperator2(String operator2) {
		this.operator2 = operator2;
	}

	public String getOperator3() {
		return operator3;
	}

	public void setOperator3(String operator3) {
		this.operator3 = operator3;
	}

	public String getOperator4() {
		return operator4;
	}

	public void setOperator4(String operator4) {
		this.operator4 = operator4;
	}

	@Override
	public String toString() {
		return "NaprednaPretragaDTO [naslov=" + naslov + ", autor=" + autor + ", izdavac=" + izdavac
				+ ", godinaIzdavanja=" + godinaIzdavanja + ", kljucneReci=" + kljucneReci + ", operator1=" + operator1
				+ ", operator2=" + operator2 + ", operator3=" + operator3 + ", operator4=" + operator4 + "]";
	}

}
