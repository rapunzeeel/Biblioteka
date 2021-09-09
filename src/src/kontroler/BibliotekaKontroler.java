package kontroler;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import dto.PozajmiceDTO;
import dto.RegistracijaBibliotekaraDTO;
import dto.RegistracijaDTO;
import dto.ZahtevRezervacijeDTO;
import exception.ClanarinaUplacenaException;
import exception.MaxBrojPozajmicaException;
import exception.NeispravanJmbgException;
import exception.NeispravnaLozinkaException;
import exception.NemogucPeriodException;
import exception.NepostojeciPostanskiBrojException;
import exception.NepronadjeneVrednostiException;
import exception.NijePronadjenClanExcepiton;
import exception.NijePronadjenjaKnjigaException;
import exception.NijePronadjenjaPrimerakException;
import exception.PostojeciEmailException;
import exception.PraznoAdresaException;
import exception.PraznoImeException;
import exception.PraznoPrezimeException;
import exception.PrijavljenAdministratorException;
import exception.PrijavljenObradjivacException;
import exception.SviPrimerciSlobodniException;
import model.Clan;
import model.Knjiga;
import model.TipZaposlenog;
import model.ZahtevZaRezervaciju;
import repos.BibliotekaRepo;
import repos.BibliotekarRepo;
import repos.ClanRepo;
import repos.FilijalaRepo;
import repos.KnjigaRepo;
import repos.MestoRepo;
import repos.PozajmicaRepo;
import repos.PrimerakRepo;
import repos.RezervacijaRepo;
import repos.ZahtevZaRezervacijuRepo;

public class BibliotekaKontroler {
	private FilijalaRepo filijalaRepo;
	private KnjigaRepo knjigaRepo;
	private ClanRepo clanRepo;
	private BibliotekarRepo bibliotekarRepo;
	private BibliotekaRepo bibliotekaRepo;
	private MestoRepo mestoRepo;
	private PrimerakRepo primerakRepo;
	private PozajmicaRepo pozajmicaRepo;
	private ZahtevZaRezervacijuRepo zahtevZaRezervacijuRepo;
	private RezervacijaRepo rezervacijaRepo;
	
	
	public BibliotekaKontroler(PozajmicaRepo pozajmicaRepo, PrimerakRepo primerakRepo, FilijalaRepo filijalaRepo, 
			KnjigaRepo knjigaRepo, ClanRepo clanRepo,  BibliotekarRepo bibliotekarRepo, BibliotekaRepo bibliotekaRepo, 
			MestoRepo mestoRepo, ZahtevZaRezervacijuRepo zahtevZaRezervacijuRepo, RezervacijaRepo rezervacijaRepo) {
		this.filijalaRepo = filijalaRepo;
		this.knjigaRepo = knjigaRepo;
		this.clanRepo = clanRepo;
		this.bibliotekarRepo = bibliotekarRepo;
		this.bibliotekaRepo = bibliotekaRepo;
		this.mestoRepo = mestoRepo;
		this.primerakRepo = primerakRepo;
		this.pozajmicaRepo = pozajmicaRepo;
		this.zahtevZaRezervacijuRepo = zahtevZaRezervacijuRepo;
		this.rezervacijaRepo = rezervacijaRepo;
	}
	
	public void proveriKredencijale(String email, String lozinka) throws SQLException, NepronadjeneVrednostiException
	{
		if(!clanRepo.proveriKredencijale(email, lozinka) && !bibliotekarRepo.proveriKredencijale(email, lozinka))
			throw new NepronadjeneVrednostiException();
	}

	public void nadjiClana(String email) throws SQLException, NijePronadjenClanExcepiton {
		Clan clan = this.clanRepo.nadjiClana(email);
		if (clan == null) {
			throw new NijePronadjenClanExcepiton();
		}
	}
	
	
	public void generisiIzvestaj(Date pocetniDatum, Date krajnjiDatum, String kriterijum) throws SQLException, NemogucPeriodException
	{
		if(pocetniDatum.compareTo(krajnjiDatum) > 0)
			throw new NemogucPeriodException();
		
		switch(kriterijum) {
		case "Žanr":{
			bibliotekaRepo.generisiIzvestajZanr(pocetniDatum, krajnjiDatum);
			break;
		}
		case "Autor":{
			bibliotekaRepo.generisiIzvestajAutor(pocetniDatum, krajnjiDatum);
			break;
		}
		case "Naslov":
		{
			bibliotekaRepo.generisiIzvestajNaslov(pocetniDatum, krajnjiDatum);
			break;			
		}
		}
		
	}
	
	public boolean proveriPostanskiBroj(int postanskiBroj) throws SQLException
	{
		if(mestoRepo.nadjiMesto(postanskiBroj) == null)
			return false;
		return true;
		
	}
	
	public void registrujClana(RegistracijaDTO registracijaDTO) throws PostojeciEmailException, PraznoPrezimeException, PraznoImeException, NeispravanJmbgException, PraznoAdresaException, NepostojeciPostanskiBrojException, NumberFormatException, SQLException
	{
		if(registracijaDTO.getEmail().equals("") || registracijaDTO.getEmail().equals("Email"))
			throw new PostojeciEmailException();
		
		try 
		{
			nadjiClana(registracijaDTO.getEmail());
			throw new PostojeciEmailException();
		}
		catch(NijePronadjenClanExcepiton ex) {}
		
		if(registracijaDTO.getIme().equals("") || registracijaDTO.getIme().equals("Ime"))
		{
			throw new PraznoImeException();			
		}
		if(registracijaDTO.getPrezime().equals("") || registracijaDTO.getPrezime().equals("Prezime"))
		{
			throw new PraznoPrezimeException();
		}
		if(registracijaDTO.getJmbg().equals("") || !registracijaDTO.getJmbg().matches("[0-9]+") || 
				registracijaDTO.getJmbg().length() != 13 || registracijaDTO.getJmbg().equals("JMBG"))
		{
			throw new NeispravanJmbgException();
		}
		if(registracijaDTO.getAdresa().equals("") || registracijaDTO.getAdresa().equals("Adresa"))
		{
			throw new PraznoAdresaException();
		}
		try
		{
			if(registracijaDTO.getPostanskiBroj().equals("") || !proveriPostanskiBroj(Integer.parseInt(registracijaDTO.getPostanskiBroj())) || registracijaDTO.getPostanskiBroj().equals("Poštanski broj"))
				throw new NepostojeciPostanskiBrojException();		
		}catch(NumberFormatException | NepostojeciPostanskiBrojException e1)
		{
			throw new NepostojeciPostanskiBrojException();		
		}				
		clanRepo.registrujClana(registracijaDTO);
		
	}

	public BibliotekaRepo getBibliotekaRepo()
	{
		return bibliotekaRepo;
	}

	public void promeniLozinku(String ulogovaniKorisnik, String staraLozinka, String novaLozinka) throws SQLException, NeispravnaLozinkaException {
		
		String sifra = bibliotekaRepo.vratiSifru(ulogovaniKorisnik);
		
		if(sifra.equals(staraLozinka))
			bibliotekaRepo.postaviNovuLozinku(ulogovaniKorisnik, novaLozinka);
		else
			throw new NeispravnaLozinkaException(); 
		
	}

	public void produziClanarinu(String email) throws NijePronadjenClanExcepiton, SQLException, ClanarinaUplacenaException {
		Clan c = clanRepo.nadjiClana(email);

		if(c == null)
			throw new NijePronadjenClanExcepiton();

		Calendar cal = Calendar.getInstance();
		cal.setTime(c.getDatumUplate());
		cal.add(Calendar.DATE, 362);
		Date d = new Date();
		
		if(cal.getTime().compareTo(d) > 0)
			throw new ClanarinaUplacenaException();
		
		bibliotekaRepo.produziClanarinu(email);
		
	}

	public void nadjiKnjigu(String isbn) throws SQLException, NijePronadjenjaKnjigaException, NijePronadjenjaPrimerakException {
		Knjiga k = knjigaRepo.nadjiKnjigu(isbn);
		
		if(k == null)
			throw new NijePronadjenjaKnjigaException();
		
		if(!primerakRepo.nadjiSlobodanPrimerak(isbn))
		{
			throw new NijePronadjenjaPrimerakException();
		}
	}

	public void dodajNovuPozajmicu(String email, String isbn) throws SQLException {
		pozajmicaRepo.dodajNovuPozajmicu(email, isbn);
	}

	public PozajmicaRepo getPozajmicaRepo() {
		return pozajmicaRepo;
	}

	public void nadjiPozajmiceClana(String email) throws SQLException {
		pozajmicaRepo.nadjiPozajmljeneKnjige(email);
		
	}

	public void evidentirajVracanjePozajmice(PozajmiceDTO pozajmica, String email, Boolean ostecena) throws SQLException {
		pozajmicaRepo.evidentirajVracanjePozajmice(pozajmica, email, ostecena);
		
	}

	public void proveriMogucnostZaduzenja(String email) throws SQLException, MaxBrojPozajmicaException {
		int brojZaduzenja = pozajmicaRepo.brojZaduzenja(email);
		
		Clan clan = clanRepo.nadjiClana(email);
		
		int maxBrojZaduzenja = clanRepo.vratiMaxBrojPozajmica(clan.getTip());
		
		if(brojZaduzenja >= maxBrojZaduzenja)
			throw new MaxBrojPozajmicaException();
		
	}

	public void tipPrijavljenogZaposlenog(String ulogovaniKorisnik) throws SQLException, PrijavljenObradjivacException, PrijavljenAdministratorException {
		TipZaposlenog tip = bibliotekarRepo.proveriTipZaposlenog(ulogovaniKorisnik);
		if(tip == TipZaposlenog.OBRADJIVAC)
			throw new PrijavljenObradjivacException();
		else if(tip == TipZaposlenog.ADMINISTRATOR)
			throw new PrijavljenAdministratorException();
	}

	public ZahtevZaRezervacijuRepo getZahtevZaRezervacijuRepo() {
		return zahtevZaRezervacijuRepo;
	}

	public void izlistajZahteve() throws SQLException {
		zahtevZaRezervacijuRepo.izlistajZahteve();
		
	}

	public void potvrdiRezervaciju(ZahtevRezervacijeDTO zahtev) throws SQLException, SviPrimerciSlobodniException, NijePronadjenjaPrimerakException {
//		if(primerakRepo.sviPrimerciSlobodni(zahtev.getISBN())) 
//			throw new SviPrimerciSlobodniException();
		
		int sifrapr = primerakRepo.nadjiPrviSlobodanPrimerak(zahtev.getISBN());
		
		if(sifrapr != -1) {
			rezervacijaRepo.dodajRezervaciju(zahtev, sifrapr);
			
			zahtevZaRezervacijuRepo.izbrisiZahtev(zahtev);			
		}
		else
			throw new NijePronadjenjaPrimerakException();
		
		
	}
	
	

	public FilijalaRepo getFilijalaRepo() {
		return filijalaRepo;
	}



	public void azuriranjeBibliotekara(RegistracijaBibliotekaraDTO registracijaDTO, boolean dodavanje) throws SQLException, PostojeciEmailException, PraznoImeException, PraznoPrezimeException, NeispravanJmbgException, PraznoAdresaException, NepostojeciPostanskiBrojException {
		if(registracijaDTO.getEmail().equals("") || registracijaDTO.getEmail().equals("Email"))
			throw new PostojeciEmailException();
		
		if(dodavanje && bibliotekarRepo.proveriPostojanjeBibliotekara(registracijaDTO.getEmail()))
			throw new PostojeciEmailException();
		
		if(registracijaDTO.getIme().equals("") || registracijaDTO.getIme().equals("Ime"))
		{
			throw new PraznoImeException();			
		}
		if(registracijaDTO.getPrezime().equals("") || registracijaDTO.getPrezime().equals("Prezime"))
		{
			throw new PraznoPrezimeException();
		}
		if(registracijaDTO.getJmbg().equals("") || !registracijaDTO.getJmbg().matches("[0-9]+") || 
				registracijaDTO.getJmbg().length() != 13 || registracijaDTO.getJmbg().equals("JMBG"))
		{
			throw new NeispravanJmbgException();
		}
		if(registracijaDTO.getAdresa().equals("") || registracijaDTO.getAdresa().equals("Adresa"))
		{
			throw new PraznoAdresaException();
		}
		try
		{
			if(registracijaDTO.getPostanskiBroj().equals("") || !proveriPostanskiBroj(Integer.parseInt(registracijaDTO.getPostanskiBroj())) || registracijaDTO.getPostanskiBroj().equals("Poštanski broj"))
				throw new NepostojeciPostanskiBrojException();		
		}catch(NumberFormatException | NepostojeciPostanskiBrojException e1)
		{
			throw new NepostojeciPostanskiBrojException();		
		}			
		
		if(dodavanje)
			bibliotekarRepo.registrujBibliotekara(registracijaDTO);
		else
			bibliotekarRepo.izmeniBibliotekara(registracijaDTO);
		
	}

	public void nadjiFilijale() throws SQLException {
		filijalaRepo.getSveFilijale();
		
	}

	public void izlistajBibliotekare() throws SQLException {
		bibliotekarRepo.izlistajBibliotekare();		
	}

	public BibliotekarRepo getBibliotekarRepo() {
		return bibliotekarRepo;
	}

	public void obrisiBibliotekara(String emailBibliotekara) throws SQLException {
		bibliotekarRepo.obrisiBibliotekara(emailBibliotekara);
		
	}

	public void izlistajKnjige() throws SQLException {
		knjigaRepo.izlistajKnjige();
		
	}

	public KnjigaRepo getKnjigaRepo() {
		return knjigaRepo;
	}

	public void obrisiKnjigu(String isbn) throws SQLException {
		knjigaRepo.obrisiKnjigu(isbn);
		
	}
	
	
	
	
	
}
