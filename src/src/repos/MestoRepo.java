package repos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import konekcija.Konekcija;
import model.Mesto;
import observer.Observer;
import observer.Publisher;
import observer.RepositoryUpdateEvent;

public class MestoRepo implements Publisher{
	private ArrayList<Observer> observers;
	
	public ArrayList<Mesto> getMesta() throws SQLException
	{
		ArrayList<Mesto> mesta = new ArrayList<>();
		String query = "select * from mesto";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Mesto m = new Mesto(resultSet.getInt(1), resultSet.getString(2));
			mesta.add(m);
		}
		
		return mesta;
	}
	
	public Mesto nadjiMesto(int pttBr) throws SQLException {
		String query = "select * from mesto where pttBroj = ?";
		Mesto m = null;
		Connection connection = Konekcija.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, pttBr);
			ResultSet resultSet = preparedStatement.executeQuery();
			
				while (resultSet.next()) {
					m = new Mesto(resultSet.getInt(1), resultSet.getString(2));
				}
		
		return m;
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
}

