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
import model.Rezervacija;
import observer.Observer;
import observer.Publisher;
import observer.RepositoryUpdateEvent;

public class RezervacijaRepo implements Publisher{
	private KnjigaRepo knjigaRepo;
	private ZahtevZaRezervacijuRepo zahtevZaRezervacijuRepo;
	private PrimerakRepo primerakRepo;
	private ArrayList<Observer> observers;

	
	public RezervacijaRepo(KnjigaRepo knjigaRepo, ZahtevZaRezervacijuRepo zahtevZaRezervacijuRepo, PrimerakRepo primerakRepo) {
		this.knjigaRepo = knjigaRepo;
		this.zahtevZaRezervacijuRepo = zahtevZaRezervacijuRepo;
		this.primerakRepo = primerakRepo;
	}
	
	
	public ArrayList<Rezervacija> getRezervacije() {
		
		ArrayList<Rezervacija> rezervacije = new ArrayList<>();
		
		String query = "select * from Rezervacija";
		
		try (Connection connection = Konekcija.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String email = resultSet.getString(1);
				Knjiga knjiga = knjigaRepo.nadjiKnjigu(resultSet.getString(2));
				Date datum = resultSet.getDate(3);
				Rezervacija r = new Rezervacija(resultSet.getDate(5),zahtevZaRezervacijuRepo.nadjiZahtev(email, knjiga.getISBN(), datum),
						primerakRepo.nadjiPrimerak(resultSet.getInt(4)));
				rezervacije.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rezervacije;
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


	@Override
	public void notifyObservers(Object o) {
		// TODO Auto-generated method stub
		
	}


	public void dodajRezervaciju(ZahtevRezervacijeDTO zahtev, int sifrapr) throws SQLException {
		String query = "insert into rezervacija values(?,?,?,?,?)";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		java.sql.Date datum = new java.sql.Date(zahtev.getDatumZahteva().getTime());
		java.sql.Date datumDanasnji = new java.sql.Date(new Date().getTime());

		preparedStatement.setString(1, zahtev.getEmail());
		preparedStatement.setString(2, zahtev.getISBN());
		preparedStatement.setDate(3, datum);
		preparedStatement.setInt(4, sifrapr);
		preparedStatement.setDate(5, datumDanasnji);
		
		
		Boolean resultSet = preparedStatement.execute();
		
	}
}
