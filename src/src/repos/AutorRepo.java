package repos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import konekcija.Konekcija;
import model.Autor;
import observer.Observer;
import observer.Publisher;
import observer.RepositoryUpdateEvent;

public class AutorRepo implements Publisher{
	private ArrayList<Observer> observers;
	
	public AutorRepo() {
		
	}

	public ArrayList<Autor> getAutori() throws SQLException {
		
		ArrayList<Autor> autori = new ArrayList<>();
		
		String query = "select * from autor";
		
		try(Connection connection = Konekcija.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery()) {
			

			while (resultSet.next()) {
				Autor a = new Autor(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));;
				autori.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return autori;
	}
	
	public ArrayList<Autor> nadjiAutore(String isbn) throws SQLException
	{
		String query = "select a.idautor, a.ime, a.prz, a.slika from autor a, autorknjige ak where ak.isbn = ? and a.idautor = ak.idautor";
		ArrayList<Autor> autori = new ArrayList<>();
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, isbn);
			ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					autori.add(new Autor(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
				}
		
		return autori;
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

	public int vratiIdAutora(String ime, String prezime) throws SQLException {
		String query = "select idautor from autor where ime=? and prz=?";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, ime);
		preparedStatement.setString(2, prezime);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			return resultSet.getInt(1);
		}
		
		return -1;
	}

	public int nadjiSledeciId() throws SQLException {
		String query = "select max(idautor) from autor";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			return resultSet.getInt(1) + 1;
		}
		
		return -1;
	}

}
