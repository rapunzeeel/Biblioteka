package dto;


public class BibliotekarDTO {
	private String email;
	private String imePrezime;
	private String jmbg;
	private String adresa;
	private String nazivFilijale;
	
	public BibliotekarDTO(String email, String imePrezime, String jmbg, String adresa, String nazivFilijale) {
		super();
		this.email = email;
		this.imePrezime = imePrezime;
		this.jmbg = jmbg;
		this.adresa = adresa;
		this.nazivFilijale = nazivFilijale;
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
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getNazivFilijale() {
		return nazivFilijale;
	}
	public void setNazivFilijale(String nazivFilijale) {
		this.nazivFilijale = nazivFilijale;
	}
}
