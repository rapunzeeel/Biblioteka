package kontroler;

import java.sql.SQLException;
import java.time.LocalDate;

import exception.ZahtevVecPostojiException;
import model.Knjiga;
import repos.KnjigaRepo;
import repos.PrimerakRepo;
import repos.ZahtevZaRezervacijuRepo;

public class RezervacijaKontroler {
	private KnjigaRepo knjigaRepo;
	private ZahtevZaRezervacijuRepo zahtevZaRezervacijuRepo;
	private PrimerakRepo primerakRepo;

	public RezervacijaKontroler(KnjigaRepo knjigaRepo, ZahtevZaRezervacijuRepo zahtevZaRezervacijuRepo,
			PrimerakRepo primerakRepo) {
		this.knjigaRepo = knjigaRepo;
		this.zahtevZaRezervacijuRepo = zahtevZaRezervacijuRepo;
		this.primerakRepo = primerakRepo;
	}

	public void dodajZahtevZaRezervaciju(String clan, Knjiga knjiga) throws SQLException {
		zahtevZaRezervacijuRepo.dodajZahtevZaRezervaciju(clan, knjiga, java.sql.Date.valueOf(LocalDate.now()));
	}
	
	public void proveriPostojanjeZahteva(String email, Knjiga knjiga) throws SQLException, ZahtevVecPostojiException {
		if(zahtevZaRezervacijuRepo.proveriPostojanjeZahteva(email, knjiga.getISBN()))
			throw new ZahtevVecPostojiException();
	}

}
