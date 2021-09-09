package repos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import konekcija.Konekcija;
import model.Primerak;
import observer.Observer;
import observer.Publisher;
import observer.RepositoryUpdateEvent;

public class PrimerakRepo implements Publisher{
	private KnjigaRepo knjigaRepo;
	private MestoPrimerkaRepo mestoPrimerkaRepo;
	private ArrayList<Observer> observers;
	
	public PrimerakRepo(KnjigaRepo knjigaRepo, MestoPrimerkaRepo mestoPrimerkaRepo) {
		this.knjigaRepo = knjigaRepo;
		this.mestoPrimerkaRepo = mestoPrimerkaRepo;
	}
	
	public ArrayList<Primerak> getPrimerci() {
		
		ArrayList<Primerak> primerci = new ArrayList<>();
		String query = "select * from primerak where rownum < 10";
		
		try (Connection connection = Konekcija.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Primerak p = new Primerak(resultSet.getInt(1), resultSet.getBoolean(2),
						knjigaRepo.nadjiKnjigu(resultSet.getString(3)), mestoPrimerkaRepo.nadjiMestoPrimerka(resultSet.getInt(1)));
				primerci.add(p);
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return primerci;
	}
	
	public Primerak nadjiPrimerak(int sifra) throws SQLException {
		String query = "select * from primerak where sifrapr = ?";
		Primerak p = null;
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, sifra);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				p = new Primerak(resultSet.getInt(1), resultSet.getBoolean(2),
						knjigaRepo.nadjiKnjigu(resultSet.getString(3)), mestoPrimerkaRepo.nadjiMestoPrimerka(resultSet.getInt(1)));
			}
		return p;
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
	
	public Boolean nadjiSlobodanPrimerak(String isbn) throws SQLException {
		String query = "select p.sifrapr from primerak p where p.isbn like ? and slobodan = 1";
		int sifra = -1;
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, isbn);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next())
			sifra = resultSet.getInt(1);
		connection.close();
		
		if(sifra == -1)
			return false;
		return true;
	}

	public void promeniZauzecePrimerka(boolean novoStanje, int sifraPrimerka) throws SQLException {
		String query = "update primerak set slobodan = ? where sifrapr = ?";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		int slobodan = (novoStanje)?1:0;
		preparedStatement.setInt(1, slobodan);
		preparedStatement.setInt(2, sifraPrimerka);
		int resultSet = preparedStatement.executeUpdate();
		connection.close();
	}

	public boolean sviPrimerciSlobodni(String isbn) throws SQLException {
		String query = "select slobodan from primerak p where p.isbn like ?";
		int slobodan = -1;
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, isbn);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			slobodan = resultSet.getInt(1);
			if(slobodan == 0)
				return false;
			
		}
		connection.close();
		
		return true;
		
	}

	public int nadjiPrviSlobodanPrimerak(String isbn) throws SQLException {
		String query = "select p.sifrapr from primerak p where p.isbn like ? and slobodan = 1";
		int sifra = -1;
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, isbn);
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			sifra = resultSet.getInt(1);
			return sifra;			
		}
		connection.close();
		
		return -1;
	}

}
