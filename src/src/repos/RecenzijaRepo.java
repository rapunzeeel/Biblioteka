package repos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import konekcija.Konekcija;
import model.Recenzija;
import observer.Observer;
import observer.Publisher;
import observer.RepositoryUpdateEvent;


public class RecenzijaRepo implements Publisher{
	private KnjigaRepo knjigaRepo;
	private ArrayList<Observer> observers;
	
	public RecenzijaRepo(KnjigaRepo knjigaRepo) {
		this.knjigaRepo = knjigaRepo;
	}
	
	public ArrayList<Recenzija> getRecenzije() {
		
		ArrayList<Recenzija> recenzije = new ArrayList<>();
		
		String query = "select * from Recenzija";
		
		try (Connection connection = Konekcija.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				Recenzija r = new Recenzija(resultSet.getString(1), knjigaRepo.nadjiKnjigu(resultSet.getString(2)), resultSet.getInt(3));
				recenzije.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return recenzije;
	}
	
	public ArrayList<Recenzija> nadjiRecenzije(String email) throws SQLException {
		String query = "select * from recenzija where email = ?";
		ArrayList<Recenzija> recenzije = new ArrayList<Recenzija>();
		Connection connection = Konekcija.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Recenzija recenzija = new Recenzija(resultSet.getString(1), knjigaRepo.nadjiKnjigu(resultSet.getString(2)), resultSet.getInt(3));
				recenzije.add(recenzija);
			}
			
		return recenzije;
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

	public void dodajRecenziju(String email, String isbn, int ocena) throws SQLException {
		String query = "insert into recenzija (email, isbn, ocena) values (?, ?, ?)";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		preparedStatement.setString(1, email);
		preparedStatement.setString(2, isbn);
		preparedStatement.setInt(3, ocena);
		
		Boolean resultSet = preparedStatement.execute();
		
	}

	public Boolean proveriPostojanjeRecenzije(String email, String isbn) throws SQLException {
		String query = "select * from recenzija where email = ? and isbn = ?";
		ArrayList<Recenzija> recenzije = new ArrayList<Recenzija>();
		Connection connection = Konekcija.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, email);
			preparedStatement.setString(2, isbn);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				return true;
			}
			
		return false;
		
	}
}
