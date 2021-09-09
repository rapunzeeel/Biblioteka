package repos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.BibliotekarDTO;
import dto.RegistracijaBibliotekaraDTO;
import konekcija.Konekcija;
import model.Autor;
import model.Bibliotekar;
import model.Clan;
import model.Knjiga;
import model.TipClana;
import model.TipKnjige;
import model.TipZaposlenog;
import model.Zanr;
import observer.Observer;
import observer.Publisher;
import observer.RepositoryUpdateEvent;

public class BibliotekarRepo implements Publisher{
	private MestoRepo mestoRepo;
	private ArrayList<Observer> observers;
	
	public BibliotekarRepo(MestoRepo mestoRepo) {
		this.mestoRepo = mestoRepo;
	}
	
	public ArrayList<Bibliotekar> getBibliotekari() throws SQLException {
		
		ArrayList<Bibliotekar> bibliotekari = new ArrayList<>();
		
		String query = "select * from Bibliotekar";
		try (Connection connection = Konekcija.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery()){
			

			while (resultSet.next()) {
				Bibliotekar b = new Bibliotekar(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6),
						 resultSet.getString(7), mestoRepo.nadjiMesto(resultSet.getInt(8)), TipZaposlenog.valueOf(resultSet.getString(9)), resultSet.getString(10));
				bibliotekari.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return bibliotekari;
	}
	
	public ArrayList<Bibliotekar> nadjiBibliotekare(String naziv) throws SQLException {
		String query = "select * from bibliotekar where nazivfilijale = ?";
		ArrayList<Bibliotekar> bibliotekari = new ArrayList<Bibliotekar>();
		Connection connection = Konekcija.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, naziv);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Bibliotekar bibliotekar = new Bibliotekar(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6),
						 resultSet.getString(7), mestoRepo.nadjiMesto(resultSet.getInt(8)), TipZaposlenog.valueOf(resultSet.getString(9)), resultSet.getString(10));
				bibliotekari.add(bibliotekar);
			}
			
		return bibliotekari;
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
    
    public Boolean proveriKredencijale(String email, String lozinka) throws SQLException
    {	
		String query = "select count(*) from bibliotekar where email = ? and lozinka = ?";
		
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

	@Override
	public void notifyObservers(Object o) {
		for (Observer observer : this.observers) {
            observer.updatePerformed(new RepositoryUpdateEvent(o));
        }
	}

	public TipZaposlenog proveriTipZaposlenog(String email) throws SQLException {
		String query = "select tipZaposlenog from bibliotekar where email = ?";
		
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();

		resultSet.next();
		
		TipZaposlenog tip = TipZaposlenog.valueOf(resultSet.getString(1));
		return tip;
	}
	
	public void registrujBibliotekara(RegistracijaBibliotekaraDTO registracijaDTO) throws SQLException {
		String query = "insert into bibliotekar (email, lozinka, ime, prz, jmbg, datumrodj, adresa, mestof, tipzaposlenog, nazivfilijale) values (? , ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
		preparedStatement.setString(9, registracijaDTO.getTipZaposlenog().name());
		preparedStatement.setString(10, registracijaDTO.getNazivFilijale());
		
		Boolean resultSet = preparedStatement.execute();
		
	}

	public boolean proveriPostojanjeBibliotekara(String email) throws SQLException {
		String query = "select * from bibliotekar where email = ?";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			return true;
		}
		return false;
	}

	public void izlistajBibliotekare() throws SQLException {
		String query = "select email, ime||' '||prz, jmbg, adresa, nazivfilijale from bibliotekar";
		ArrayList<BibliotekarDTO> bibliotekari = new ArrayList<BibliotekarDTO>();
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			BibliotekarDTO bibliotekar = new BibliotekarDTO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
			bibliotekari.add(bibliotekar);
		}
		
		
		notifyObservers(bibliotekari);
		
	}

	public void obrisiBibliotekara(String emailBibliotekara) throws SQLException {
		String query = "delete from bibliotekar where email=?";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		
		preparedStatement.setString(1, emailBibliotekara);
		
		int resultSet = preparedStatement.executeUpdate();
		
	}

	public Bibliotekar nadjiBibliotekara(String emailBibliotekara) throws SQLException {
		String query = "select * from bibliotekar where email=?";
		Bibliotekar bibliotekar;
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, emailBibliotekara);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		resultSet.next();
		bibliotekar = new Bibliotekar(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6),
				 resultSet.getString(7), mestoRepo.nadjiMesto(resultSet.getInt(8)), TipZaposlenog.valueOf(resultSet.getString(9)), resultSet.getString(10));
		
		
		return bibliotekar;
	}

	public void izmeniBibliotekara(RegistracijaBibliotekaraDTO registracijaDTO) throws SQLException {
		String query = "update bibliotekar set ime=?, prz=?, jmbg=?,datumrodj=?,adresa=?,mestof=?,tipzaposlenog=?, nazivfilijale=? where email=?";
		Bibliotekar bibliotekar;
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		java.sql.Date datumRodj = new java.sql.Date(registracijaDTO.getDatumRodjenja().getTime());

		preparedStatement.setString(1, registracijaDTO.getIme());
		preparedStatement.setString(2, registracijaDTO.getPrezime());
		preparedStatement.setString(3, registracijaDTO.getJmbg());
		preparedStatement.setDate(4, datumRodj);
		preparedStatement.setString(5, registracijaDTO.getAdresa());
		preparedStatement.setString(6, registracijaDTO.getPostanskiBroj());
		preparedStatement.setString(7, registracijaDTO.getTipZaposlenog().name());
		preparedStatement.setString(8, registracijaDTO.getNazivFilijale());
		preparedStatement.setString(9, registracijaDTO.getEmail());
		
		int resultSet = preparedStatement.executeUpdate();
		
	}

}
