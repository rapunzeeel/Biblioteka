package repos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dto.ZahtevRezervacijeDTO;
import konekcija.Konekcija;
import model.Knjiga;
import model.ZahtevZaRezervaciju;
import observer.Observer;
import observer.Publisher;
import observer.RepositoryUpdateEvent;

public class ZahtevZaRezervacijuRepo implements Publisher{
	private KnjigaRepo knjigaRepo;
	private ArrayList<Observer> observers;
	
	public ZahtevZaRezervacijuRepo(KnjigaRepo knjigaRepo) {
		this.knjigaRepo = knjigaRepo;
	}

	
	public ArrayList<ZahtevZaRezervaciju> getZahtevi()
	{
		ArrayList<ZahtevZaRezervaciju> zahtevi = new ArrayList<>();
		
		String query = "select * from zahtevZaRezervaciju";
		
		try (Connection connection = Konekcija.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				ZahtevZaRezervaciju z = new ZahtevZaRezervaciju(resultSet.getString(1), knjigaRepo.nadjiKnjigu(resultSet.getString(2)),  resultSet.getTimestamp(3));
				zahtevi.add(z);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return zahtevi;
	}
	
	public ZahtevZaRezervaciju nadjiZahtev(String email, String isbn, Date datumZahteva) throws SQLException {
		String query = "select * from zahtevZaRezervaciju where email = ? and isbn = ? and datumZah = ?";
		ZahtevZaRezervaciju z = null;
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, email);
			preparedStatement.setString(2, isbn);
			preparedStatement.setDate(3, (java.sql.Date) datumZahteva);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				z = new ZahtevZaRezervaciju(resultSet.getString(1), knjigaRepo.nadjiKnjigu(resultSet.getString(2)),  resultSet.getTimestamp(3));
			}

		return z;
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
    
    public void dodajZahtevZaRezervaciju(String clan, Knjiga knjiga, Date datumZahteva) throws SQLException {
    	String query = "insert into zahtevZaRezervaciju (email,isbn,datumZah) values (? , ?, ?)";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		preparedStatement.setString(1, clan);
		preparedStatement.setString(2, knjiga.getISBN());
		preparedStatement.setDate(3, (java.sql.Date) datumZahteva);
		Boolean resultSet = preparedStatement.execute();
	}


	@Override
	public void notifyObservers(Object o) {
		for (Observer observer : this.observers) {
            observer.updatePerformed(new RepositoryUpdateEvent(o));
        }
	}
	
	public Boolean proveriPostojanjeZahteva(String email, String isbn) throws SQLException {
		String query = "select * from zahtevzarezervaciju where email = ? and isbn = ?";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		preparedStatement.setString(1, email);
		preparedStatement.setString(2, isbn);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			return true;
		}
		return false;
	}


	public void izlistajZahteve() throws SQLException {
		ArrayList<ZahtevRezervacijeDTO> zahtevi = new ArrayList<ZahtevRezervacijeDTO>();
		String query = "select z.email, c.ime||' '||c.prz, z.isbn, k.naslov, k.izdavac, z.datumzah from zahtevZaRezervaciju z, Clan c, Knjiga k"
				+ " where z.email=c.email and k.isbn = z.isbn order by z.datumzah";
		ZahtevRezervacijeDTO z = null;
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			z = new ZahtevRezervacijeDTO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), 
					resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6));
			zahtevi.add(z);
		}

		notifyObservers(zahtevi);
		
	}


	public void izbrisiZahtev(ZahtevRezervacijeDTO zahtev) throws SQLException {
		String query = "delete from zahtevzarezervaciju where email=? and ISBN=? and datumzah=?";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		java.sql.Date datum = new java.sql.Date(zahtev.getDatumZahteva().getTime());
		
		preparedStatement.setString(1, zahtev.getEmail());
		preparedStatement.setString(2, zahtev.getISBN());
		preparedStatement.setDate(3, datum);
		int resultSet = preparedStatement.executeUpdate();
		
		izlistajZahteve();
	}
}
