package kontroler;

import java.sql.SQLException;
import java.util.Date;

import exception.IsteklaClanarinaException;
import exception.MaxBrojPozajmicaException;
import exception.NedostajacaVrednostException;
import exception.RecenzijaVecPostojiException;
import exception.VecProduzenaPozajmicaException;
import model.Clan;
import repos.ClanRepo;
import repos.MestoRepo;
import repos.PozajmicaRepo;
import repos.RecenzijaRepo;

public class ClanKontroler {
	private MestoRepo mestoRepo;
	private PozajmicaRepo pozajmicaRepo;
	private RecenzijaRepo recenzijaRepo;
	private ClanRepo clanRepo;
	
		
	public ClanKontroler(MestoRepo mestoRepo, PozajmicaRepo pozajmicaRepo, RecenzijaRepo recenzijaRepo, ClanRepo clanRepo) {
		this.mestoRepo = mestoRepo;
		this.pozajmicaRepo = pozajmicaRepo;
		this.recenzijaRepo = recenzijaRepo;
		this.clanRepo = clanRepo;
	}
	
	public Clan nadjiClana(String ulogovaniKorisnik) throws SQLException, NedostajacaVrednostException {
		if(ulogovaniKorisnik != null) {
			return this.clanRepo.nadjiClana(ulogovaniKorisnik);
		}
		throw new NedostajacaVrednostException();
	}
	
	public void imaMogucnostPozajmice(String email) throws SQLException, MaxBrojPozajmicaException {
		if (!clanRepo.pravoZaduzenja(email)) {
			throw new MaxBrojPozajmicaException();
		}
	}

	public void proveriDatumClanarine(String email) throws SQLException, IsteklaClanarinaException {
		Date d = clanRepo.proveriDatumClanarine(email);
		Date danasnji = new Date();
		
		if(danasnji.compareTo(d) > 0)
			throw new IsteklaClanarinaException();
	}

	public ClanRepo getClanRepo() {
		return clanRepo;
	}

	public void proveriMogucnostProduzenja(Date datumPoz, int sifraPr) throws SQLException, VecProduzenaPozajmicaException {
		if(!pozajmicaRepo.proveriMogucnostProduzenja(datumPoz, sifraPr))
			throw new VecProduzenaPozajmicaException();
	}
	
	public void produziDatumPozajmice(Date datumPoz, int sifraPr, String email) throws SQLException, VecProduzenaPozajmicaException {
		Clan c = clanRepo.nadjiClana(email);
		int produzenje = clanRepo.vratiRokVracanja(c.getTip());
		
		if(!pozajmicaRepo.proveriMogucnostProduzenja(datumPoz, sifraPr))
			throw new VecProduzenaPozajmicaException();
		
		pozajmicaRepo.produziPozajmicu(datumPoz, sifraPr, produzenje);
		
	}

	public void proveriPostojanjeRecenzije(String email, String isbn) throws SQLException, RecenzijaVecPostojiException {
		if(recenzijaRepo.proveriPostojanjeRecenzije(email, isbn))
			throw new RecenzijaVecPostojiException();
	}
	
	public void dodajRecenziju(String email, String isbn, int ocena) throws SQLException, RecenzijaVecPostojiException {
		if(recenzijaRepo.proveriPostojanjeRecenzije(email, isbn))
			throw new RecenzijaVecPostojiException();
		recenzijaRepo.dodajRecenziju(email, isbn, ocena);
		
	}
	
	
}

