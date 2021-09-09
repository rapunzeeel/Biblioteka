package repos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dto.RegistracijaBibliotekaraDTO;
import dto.RegistracijaDTO;
import konekcija.Konekcija;
import model.Clan;
import model.Knjiga;
import model.TipClana;
import model.TipKnjige;
import model.Zanr;
import observer.Observer;
import observer.Publisher;
import observer.RepositoryUpdateEvent;

public class ClanRepo implements Publisher{
	private MestoRepo mestoRepo;
	private PozajmicaRepo pozajmicaRepo;
	private RecenzijaRepo recenzijaRepo;
	private ArrayList<Observer> observers;
		
	public ClanRepo(MestoRepo mestoRepo, PozajmicaRepo pozajmicaRepo, RecenzijaRepo recenzijaRepo) {
		this.mestoRepo = mestoRepo;
		this.pozajmicaRepo = pozajmicaRepo;
		this.recenzijaRepo = recenzijaRepo;
	}
	
	
	public ArrayList<Clan> getClanovi() throws SQLException {
		
		ArrayList<Clan> clanovi = new ArrayList<>();
		
		String query = "select * from Clan";
		
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			String email = resultSet.getString(1);
			Clan c = new Clan(email, resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getTimestamp(6),
					 resultSet.getString(7), mestoRepo.nadjiMesto(resultSet.getInt(8)), TipClana.valueOf(resultSet.getString(9)), resultSet.getTimestamp(10),
					 pozajmicaRepo.nadjiPozajmice(email), recenzijaRepo.nadjiRecenzije(email));
			clanovi.add(c);
		}

		
		return clanovi;
	}
	public Clan nadjiClana(String email) throws SQLException {
		String query = "select * from clan where email = ?";
		Clan clan = null;
		Connection connection = Konekcija.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				clan = new Clan(email, resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getTimestamp(6),
						 resultSet.getString(7), mestoRepo.nadjiMesto(resultSet.getInt(8)), TipClana.valueOf(resultSet.getString(9)), resultSet.getTimestamp(10),
						 pozajmicaRepo.nadjiPozajmice(email), recenzijaRepo.nadjiRecenzije(email));
			}
		return clan;
	}
	
	@Override
    public void addObserver(Observer observer) {
        if (null == observers)
            observers = new ArrayList<Observer>();
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (null == observers)
            return;
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.updatePerformed(new RepositoryUpdateEvent());
        }
    }
    
    public Boolean pravoZaduzenja(String email) throws SQLException {
    	String query = "select count(*) as brojac from ogranicenjaPoTipuClana og, clan c where c.tipClana = og.tipClana and c.email like ? and og.maxPozajmica > (select count(*) from pozajmica where email = ? and datumvr is null)";
    	Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, email);
		preparedStatement.setString(2, email);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();

		//ako vrati 1, onda znaci da ima pravo na jos knjiga
		if(resultSet.getInt("brojac") == 0)
			return false;
		return true;
    }
    
    public Boolean proveriKredencijale(String email, String lozinka) throws SQLException
    {	
		String query = "select count(*) from clan where email = ? and lozinka = ?";
		
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, lozinka);
		ResultSet resultSet = preparedStatement.executeQuery();

		resultSet.next();
		
		if(resultSet.getInt(1) == 0)
			return false;
		
		return true;
    }

    public void registrujClana(RegistracijaDTO registracijaDTO) throws SQLException {
    	String query = "insert into clan (email, lozinka, ime, prz, jmbg, datumrodj, adresa, mestof, tipclana) values (? , ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		java.sql.Date datumRodj = new java.sql.Date(registracijaDTO.getDatumRodjenja().getTime());

		preparedStatement.setString(1, registracijaDTO.getEmail());
		preparedStatement.setString(2, registracijaDTO.getLozinka());
		preparedStatement.setString(3, registracijaDTO.getIme());
		preparedStatement.setString(4, registracijaDTO.getPrezime());
		preparedStatement.setString(5, registracijaDTO.getJmbg());
		preparedStatement.setDate(6, datumRodj);
		preparedStatement.setString(7, registracijaDTO.getAdresa());
		preparedStatement.setInt(8, Integer.parseInt(registracijaDTO.getPostanskiBroj()));
		preparedStatement.setString(9, registracijaDTO.getTipClana().name());
		
		Boolean resultSet = preparedStatement.execute();
    	
    }

    @Override
	public void notifyObservers(Object o) {
		for (Observer observer : this.observers) {
			observer.updatePerformed(new RepositoryUpdateEvent(o));
		}
	}


	public Date proveriDatumClanarine(String email) throws SQLException {
		String query = "select (datumUplate+365) from clan where email = ?";
		
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();

		resultSet.next();
		
		notifyObservers(resultSet.getDate(1));
		return resultSet.getDate(1);
	}


	public int vratiMaxBrojPozajmica(TipClana tip) throws SQLException {
		String query = "select maxpozajmica from ogranicenjapotipuclana where tipclana = ?";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setString(1, tip.name());
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		
		return resultSet.getInt(1);
	}
	
	public int vratiRokVracanja(TipClana tip) throws SQLException {
		String query = "select rokvracanja from ogranicenjapotipuclana where tipclana = ?";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setString(1, tip.name());
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		
		return resultSet.getInt(1);
	}

}
