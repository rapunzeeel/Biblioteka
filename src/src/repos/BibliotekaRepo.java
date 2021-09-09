package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dto.IzvestajDTO;
import konekcija.Konekcija;
import model.Biblioteka;
import model.Clan;
import model.TipClana;
import observer.Observer;
import observer.Publisher;
import observer.RepositoryUpdateEvent;

public class BibliotekaRepo implements Publisher {

	private FilijalaRepo filijalaRepo;
	private KnjigaRepo knjigaRepo;
	private ClanRepo clanRepo;
	private ArrayList<Observer> observers;

	public BibliotekaRepo(FilijalaRepo filijalaRepo, KnjigaRepo knjigaRepo, ClanRepo clanRepo) {
		this.filijalaRepo = filijalaRepo;
		this.knjigaRepo = knjigaRepo;
		this.clanRepo = clanRepo;
	}

	public Biblioteka getBiblioteka() {
		Biblioteka biblioteka = null;
		String query = "select * from biblioteka";

		try (Connection connection = Konekcija.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				biblioteka = new Biblioteka(resultSet.getString(1), filijalaRepo.getFilijale(), knjigaRepo.getKnjige(),
						clanRepo.getClanovi());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return biblioteka;
	}

	public void generisiIzvestajNaslov(Date pocetniDatum, Date krajnjiDatum) throws SQLException {
		ArrayList<IzvestajDTO> listaIzvestaja = new ArrayList<IzvestajDTO>();

		String query = "select k.naslov, count(p.sifrapr) from primerak pr RIGHT OUTER JOIN knjiga k ON k.isbn=pr.isbn LEFT OUTER JOIN pozajmica p ON"
				+ " p.sifrapr = pr.sifrapr where p.datumpoz is NULL or (p.datumpoz > ? and p.datumpoz < ?) group by k.naslov order by k.naslov desc";

		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		java.sql.Date pocDatum = new java.sql.Date(pocetniDatum.getTime());
		java.sql.Date krajDatum = new java.sql.Date(krajnjiDatum.getTime());

		preparedStatement.setDate(1, pocDatum);
		preparedStatement.setDate(2, krajDatum);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			int brojPozajmica = resultSet.getInt(2);
			float procenat = ((float)brojPozajmica / (float)izbrojPozajmice(pocetniDatum, krajnjiDatum)) * 100;
			listaIzvestaja.add(new IzvestajDTO(resultSet.getString(1), brojPozajmica, procenat));
		}
		resultSet.close();
		connection.close();
		notifyObservers(listaIzvestaja);
	}

	private int izbrojPozajmice(Date pocetniDatum, Date krajnjiDatum) throws SQLException {
		String query = "select count(*) from pozajmica";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
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

	public String vratiSifru(String ulogovaniKorisnik) throws SQLException {
		String query = "select lozinka from clan where email = ? UNION select lozinka from bibliotekar where email = ?";
		
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement.setString(1, ulogovaniKorisnik);
		preparedStatement.setString(2, ulogovaniKorisnik);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		
		return resultSet.getString(1);
		
	}
	
	public void postaviNovuLozinku(String ulogovaniKorisnik, String novaLozinka) throws SQLException
	{
		String query1 = "update clan set lozinka = ? where email = ?";
		String query2 = " update bibliotekar set lozinka = ? where email = ?";
		
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query1);
		
		preparedStatement.setString(1, novaLozinka);
		preparedStatement.setString(2, ulogovaniKorisnik);
		
		int resultSet = preparedStatement.executeUpdate();
		
		preparedStatement = connection.prepareStatement(query2);
		preparedStatement.setString(1, novaLozinka);
		preparedStatement.setString(2, ulogovaniKorisnik);
		
		resultSet = preparedStatement.executeUpdate();
		
	}

	public void produziClanarinu(String email) throws SQLException {
		Date d = new Date();
		String query = "update clan set datumUplate = ? where email = ?";
		
		java.sql.Date datum = new java.sql.Date(d.getTime());
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setDate(1, datum);
		preparedStatement.setString(2, email);
		
		int resultSet = preparedStatement.executeUpdate();
		
	}

	public void generisiIzvestajZanr(Date pocetniDatum, Date krajnjiDatum) throws SQLException {
		ArrayList<IzvestajDTO> listaIzvestaja = new ArrayList<IzvestajDTO>();

		String query = "select k.zanr, count(p.sifrapr) from primerak pr RIGHT OUTER JOIN knjiga k ON k.isbn=pr.isbn LEFT OUTER JOIN pozajmica p ON"
				+ " p.sifrapr = pr.sifrapr where p.datumpoz is NULL or (p.datumpoz > ? and p.datumpoz < ?) group by k.zanr order by zanr desc";

		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		java.sql.Date pocDatum = new java.sql.Date(pocetniDatum.getTime());
		java.sql.Date krajDatum = new java.sql.Date(krajnjiDatum.getTime());

		preparedStatement.setDate(1, pocDatum);
		preparedStatement.setDate(2, krajDatum);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			int brojPozajmica = resultSet.getInt(2);
			float procenat = ((float)brojPozajmica / (float)izbrojPozajmice(pocetniDatum, krajnjiDatum)) * 100;
			listaIzvestaja.add(new IzvestajDTO(resultSet.getString(1), brojPozajmica, procenat));
		}
		resultSet.close();
		connection.close();
		notifyObservers(listaIzvestaja);		
	}

	public void generisiIzvestajAutor(Date pocetniDatum, Date krajnjiDatum) throws SQLException {
		ArrayList<IzvestajDTO> listaIzvestaja = new ArrayList<IzvestajDTO>();

		String query = "select a.ime||' '||a.prz , count(p.sifrapr) "
				+ "from primerak pr RIGHT OUTER JOIN knjiga k ON k.isbn=pr.isbn LEFT OUTER JOIN pozajmica p ON"
				+ " p.sifrapr = pr.sifrapr RIGHT OUTER JOIN autorknjige ak ON ak.isbn=k.isbn RIGHT OUTER JOIN Autor a ON a.idautor=ak.idautor "
				+ "where p.datumpoz is NULL or (p.datumpoz > ? and p.datumpoz < ?) group by a.ime, a.prz order by a.ime, a.prz desc";

		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		java.sql.Date pocDatum = new java.sql.Date(pocetniDatum.getTime());
		java.sql.Date krajDatum = new java.sql.Date(krajnjiDatum.getTime());

		preparedStatement.setDate(1, pocDatum);
		preparedStatement.setDate(2, krajDatum);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			int brojPozajmica = resultSet.getInt(2);
			float procenat = ((float)brojPozajmica / (float)izbrojPozajmice(pocetniDatum, krajnjiDatum)) * 100;
			listaIzvestaja.add(new IzvestajDTO(resultSet.getString(1), brojPozajmica, procenat));
		}
		resultSet.close();
		connection.close();
		notifyObservers(listaIzvestaja);		
		
	}

}