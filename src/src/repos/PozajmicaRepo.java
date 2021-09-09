package repos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dto.PozajmiceDTO;
import konekcija.Konekcija;
import model.Pozajmica;
import observer.Observer;
import observer.Publisher;
import observer.RepositoryUpdateEvent;

public class PozajmicaRepo implements Publisher{
	private PrimerakRepo primerakRepo;
	private ArrayList<Observer> observers;
	
	public PozajmicaRepo(PrimerakRepo primerakRepo) {
		this.primerakRepo = primerakRepo;
	}

	public ArrayList<Pozajmica> getPozajmice() {
		
		ArrayList<Pozajmica> pozajmice = new ArrayList<>();
		
		String query = "select * from Pozajmica";
		
		try (Connection connection = Konekcija.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Pozajmica p = new Pozajmica(resultSet.getString(1), primerakRepo.nadjiPrimerak(resultSet.getInt(2)), resultSet.getTimestamp(3), resultSet.getTimestamp(4), resultSet.getBoolean(5));
				pozajmice.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pozajmice;
	}
	
	public ArrayList<Pozajmica> nadjiPozajmice(String email) throws SQLException {
		String query = "select * from pozajmica where email = ?";
		ArrayList<Pozajmica> pozajmice = new ArrayList<Pozajmica>();
		Connection connection = Konekcija.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Pozajmica poz = new Pozajmica(resultSet.getString(1), primerakRepo.nadjiPrimerak(resultSet.getInt(2)), resultSet.getTimestamp(3), resultSet.getTimestamp(4), resultSet.getBoolean(5));
				pozajmice.add(poz);
			}
			
		return pozajmice;
	}
	
	public void nadjiPozajmljeneKnjige(String email) throws SQLException {
		String query = "select pr.sifrapr, k.isbn,k.naslov,k.izdavac,p.datumpoz from knjiga k, primerak pr,pozajmica p where k.isbn=pr.isbn and p.sifrapr=pr.sifrapr and p.email = ? and p.datumvr is NULL";
		ArrayList<PozajmiceDTO> pozajmice = new ArrayList<PozajmiceDTO>();
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();
			
		while (resultSet.next()) {
			PozajmiceDTO poz = new PozajmiceDTO(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5));
			pozajmice.add(poz);
		}
		resultSet.close();
		connection.close();
	
		notifyObservers(pozajmice);
	} 
	
	public int brojZaduzenja(String email) throws SQLException
	{
		String query = "select count(*) from pozajmica where email = ? and datumvr is NULL";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();

		resultSet.next();
		
		return resultSet.getInt(1);		
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
		for (Observer observer : this.observers) {
			observer.updatePerformed(new RepositoryUpdateEvent(o));
		}
	}

	public void dodajNovuPozajmicu(String email, String isbn) throws SQLException {
		int sifraPr = nadjiSlobodanPrimerak(isbn);
		String query = "insert into pozajmica (email, sifrapr, datumpoz) values (? , ?, ?)";
		
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		java.sql.Date datum = new java.sql.Date(new Date().getTime());

		preparedStatement.setString(1, email);
		preparedStatement.setInt(2, sifraPr);
		preparedStatement.setDate(3, datum);
		
		Boolean resultSet = preparedStatement.execute();
		
		primerakRepo.promeniZauzecePrimerka(false, sifraPr);
		
	}
	
	private int nadjiSlobodanPrimerak(String isbn) throws SQLException
	{
		String query = "select sifrapr from primerak where isbn like ? and slobodan = 1";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		

		preparedStatement.setString(1, isbn);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		
		return resultSet.getInt(1);
	}

	public void evidentirajVracanjePozajmice(PozajmiceDTO pozajmica, String email, Boolean ostecena) throws SQLException {
		String query = "update pozajmica set datumvr=?, ostecena = ? where email=? and datumpoz=? and sifrapr = ?";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		java.sql.Date datum = new java.sql.Date(pozajmica.getDatumIzdavanja().getTime());
		java.sql.Date danasnjiDatum = new java.sql.Date(new Date().getTime());
		
		preparedStatement.setDate(1, danasnjiDatum);
		int ost = (ostecena) ? 1 : 0;
		preparedStatement.setInt(2, ost);
		preparedStatement.setString(3, email);
		preparedStatement.setDate(4, datum);
		preparedStatement.setInt(5, pozajmica.getSifraPrimerka());
		
		int resultSet = preparedStatement.executeUpdate();
		
		primerakRepo.promeniZauzecePrimerka(true, pozajmica.getSifraPrimerka());
		connection.close();
		
		nadjiPozajmljeneKnjige(email);
	}

	public void produziPozajmicu(Date datumPoz, int sifraPrimerka, int produzenje) throws SQLException {
		
		String query = "update pozajmica set datumpoz=?, produzeno=1 where sifrapr=? and datumvr is NULL";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		
		 Calendar c = Calendar.getInstance();
	     c.setTime(datumPoz);
	     c.add(Calendar.DATE, produzenje);
	     Date d =  new Date(c.getTimeInMillis());
		
	    java.sql.Date datum = new java.sql.Date(d.getTime());
		preparedStatement.setDate(1, datum);
		preparedStatement.setInt(2, sifraPrimerka);
		
		int resultSet = preparedStatement.executeUpdate();
	}

	public boolean proveriMogucnostProduzenja(Date datumPoz, int sifraPr) throws SQLException {
		
		String query = "select produzeno from pozajmica where datumpoz = ? and datumvr is NULL and sifrapr = ?";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		java.sql.Date datum = new java.sql.Date(datumPoz.getTime());
		preparedStatement.setDate(1, datum);
		preparedStatement.setInt(2, sifraPr);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		
		if(resultSet.getInt(1) == 0)
			return true;
		return false;
	}
}
