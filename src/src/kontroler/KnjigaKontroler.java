package kontroler;

import java.nio.channels.NonWritableChannelException;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.AutorDTO;
import dto.NaprednaPretragaDTO;
import exception.NemogucPeriodException;
import exception.NepronadjeneVrednostiException;
import exception.PostojecaKnjigaException;
import exception.PraznoFormatException;
import exception.PraznoImeException;
import exception.PraznoIsbnException;
import exception.PraznoIzdavacException;
import exception.PraznoNaslovException;
import exception.PraznoPrezimeException;
import model.Knjiga;
import repos.AutorRepo;
import repos.KnjigaRepo;

public class KnjigaKontroler {
	private KnjigaRepo knjigaRepo;
	
	public KnjigaRepo getKnjigaRepo() {
		return knjigaRepo;
	}

	public void setKnjigaRepo(KnjigaRepo knjigaRepo) {
		this.knjigaRepo = knjigaRepo;
	}

	private AutorRepo autorRepo;

	public KnjigaKontroler(AutorRepo autorRepo, KnjigaRepo knjigaRepo) {
		this.autorRepo = autorRepo;
		this.knjigaRepo = knjigaRepo;
	}

	public void nadjiKnjiguPoKoricama(String nazivKorice) throws SQLException {
		this.knjigaRepo.nadjiKnjiguPoKoricama(nazivKorice);
	}

	public void pretraziPoStringu(NaprednaPretragaDTO dto) throws SQLException, NepronadjeneVrednostiException {
		this.knjigaRepo.naprednaPretraga(dto);
//		ArrayList<Knjiga> listaPretrage = knjigaRepo.pretraziPoStringu(trazeniString);
//		if(listaPretrage.size() == 0)
//			throw new NoFoundDataException("Ne postoje knjige sa zadatim kriterijumom.");
	}

	public void pretraziPoKategoriji(String kategorija) throws SQLException, NepronadjeneVrednostiException {
		knjigaRepo.pretraziPoKategoriji(kategorija);
	}

	public void pretraziPoAutoru(String id) throws NumberFormatException, SQLException {
		this.knjigaRepo.pretraziPoAutoru(Integer.parseInt(id));
	}

	public void nadjiKnjigeClana(String ulogovaniKorisnik) throws NumberFormatException, SQLException {
		this.knjigaRepo.nadjiKnjigeClana(ulogovaniKorisnik);
	}

	public void naprednaPretraga(NaprednaPretragaDTO dto) throws SQLException {
		this.knjigaRepo.naprednaPretraga(dto);

	}

	public void dodajNovuKnjigu(Knjiga knjiga, ArrayList<AutorDTO> autori) throws SQLException, PostojecaKnjigaException, NemogucPeriodException, PraznoIsbnException, PraznoNaslovException, PraznoIzdavacException, PraznoFormatException, PraznoImeException, PraznoPrezimeException {
		
		if(knjiga.getISBN().equals("") || knjigaRepo.nadjiKnjigu(knjiga.getISBN()) != null || knjiga.getISBN().equals("ISBN"))
			throw new PraznoIsbnException();
		else if(knjiga.getNaslov().equals("") || knjiga.getNaslov().equals("Naslov"))
			throw new PraznoNaslovException();
		else if(knjiga.getIzdavac().equals("") || knjiga.getIzdavac().equals("Izdavač"))
			throw new PraznoIzdavacException();
		else if(knjiga.getFormat().equals("") || knjiga.getFormat().equals("Format (npr. 20x15cm)"))
			throw new PraznoFormatException();
		else if(Integer.parseInt(knjiga.getGodinaIzdavanja()) < Integer.parseInt(knjiga.getGodinaStampanja()))
			throw new NemogucPeriodException();
		for(AutorDTO a: autori) {
			if(a.getIme().equals(""))
				throw new PraznoImeException();
			else if (a.getPrezime().equals(""))
				throw new PraznoPrezimeException();
		}
		
		knjigaRepo.dodajNovuKnjigu(knjiga);
		
		knjigaRepo.dodajAutorKnjiga(knjiga.getISBN(), autori);
		
		
		
	}

	public void izmeniKnjigu(String isbn, String opis, String kljucneReci, String slika) throws SQLException {
		knjigaRepo.izmeniKnjigu(isbn, opis, kljucneReci, slika);
		
	}

	public void nadjiKnjigu(String isbn) throws SQLException {
		knjigaRepo.nadjiKnjiguObavestenje(isbn);
	}
}
//treba napraviti funkciju koja proverava da li su sve knjige slobodne i da li su sve zauzete