package repos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import konekcija.Konekcija;
import model.MestoPrimerka;
import observer.Observer;
import observer.Publisher;
import observer.RepositoryUpdateEvent;

public class MestoPrimerkaRepo implements Publisher{
	private ArrayList<Observer> observers;
	
	public ArrayList<MestoPrimerka> getMestaPrimeraka() throws SQLException {
		
		ArrayList<MestoPrimerka> mestaPrimeraka = new ArrayList<>();
		
		String query = "select * from mestoPrimerka";
		try (Connection connection = Konekcija.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery()){
			
			while (resultSet.next()) {
				MestoPrimerka mp = new MestoPrimerka(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4));
				mestaPrimeraka.add(mp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mestaPrimeraka;
	}
	
	
	public MestoPrimerka nadjiMestoPrimerka(int sifra) throws SQLException
	{
		String query = "select * from mestoPrimerka where sifraPr = ?";
		MestoPrimerka mesto = null;
		Connection connection = Konekcija.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, sifra);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				mesto = new MestoPrimerka(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4));
			}
		
		return mesto;
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
