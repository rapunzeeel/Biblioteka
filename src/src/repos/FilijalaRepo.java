package repos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import konekcija.Konekcija;
import model.Filijala;
import observer.Observer;
import observer.Publisher;
import observer.RepositoryUpdateEvent;

public class FilijalaRepo implements Publisher{
	private MestoRepo mestoRepo;
	private BibliotekarRepo bibliotekarRepo;
	private ArrayList<Observer> observers;
		
	public FilijalaRepo(MestoRepo mestoRepo, BibliotekarRepo bibliotekarRepo) {
		this.mestoRepo = mestoRepo;
		this.bibliotekarRepo = bibliotekarRepo;
	}
	
	public ArrayList<Filijala> getFilijale() throws SQLException {
		
		ArrayList<Filijala> filijale = new ArrayList<>();
		
		String query = "select * from filijala";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		

		while (resultSet.next()) {
			String naziv = resultSet.getString(1);
			Filijala f = new Filijala(naziv, resultSet.getString(2), 
					resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), 
					mestoRepo.nadjiMesto(resultSet.getInt(6)), bibliotekarRepo.nadjiBibliotekare(naziv));
			filijale.add(f);
		}
		
		return filijale;
	}
	
	public void getSveFilijale() throws SQLException {
		
		ArrayList<Filijala> filijale = new ArrayList<>();
		
		String query = "select * from filijala";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		

		while (resultSet.next()) {
			String naziv = resultSet.getString(1);
			Filijala f = new Filijala(naziv, resultSet.getString(2), 
					resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), 
					mestoRepo.nadjiMesto(resultSet.getInt(6)), bibliotekarRepo.nadjiBibliotekare(naziv));
			filijale.add(f);
		}
		
		notifyObservers(filijale);
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
	
}
