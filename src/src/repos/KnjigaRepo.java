package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dto.AutorDTO;
import dto.KnjigaDTO;
import dto.NaprednaPretragaDTO;
import dto.PozajmljeneKnjigeDTO;
import konekcija.Konekcija;
import model.Autor;
import model.Knjiga;
import model.TipKnjige;
import model.Zanr;
import observer.Observer;
import observer.Publisher;
import observer.RepositoryUpdateEvent;

public class KnjigaRepo implements Publisher {
	private AutorRepo autorRepo;
	private ArrayList<Observer> observers;

	public KnjigaRepo(AutorRepo autorRepo) {
		this.autorRepo = autorRepo;
	}

	public ArrayList<Knjiga> getKnjige() throws SQLException {

		ArrayList<Knjiga> knjige = new ArrayList<>();

		String query = "select * from Knjiga where obrisana=0";

		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			Knjiga k = new Knjiga(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5), Zanr.valueOf(resultSet.getString(6)),
					resultSet.getString(7), TipKnjige.valueOf(resultSet.getString(8)), resultSet.getFloat(9),
					resultSet.getString(10), autorRepo.nadjiAutore(resultSet.getString(1)), resultSet.getString(12), resultSet.getString(11));
			knjige.add(k);
		}

		return knjige;
	}

	public Knjiga nadjiKnjigu(String isbn) throws SQLException {
		String query = "select * from knjiga where isbn = ?";
		Knjiga knjiga = null;
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, isbn);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			knjiga = new Knjiga(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5), Zanr.valueOf(resultSet.getString(6)),
					resultSet.getString(7), TipKnjige.valueOf(resultSet.getString(8)), resultSet.getFloat(9),
					resultSet.getString(10), autorRepo.nadjiAutore(resultSet.getString(1)), resultSet.getString(12), resultSet.getString(11));
		}

		return knjiga;
	}

	public void nadjiKnjiguPoKoricama(String slikaKorice) throws SQLException {
		String query = "select * from knjiga where slika like ? and obrisana=0";
		Knjiga knjiga = null;
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, slikaKorice);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			knjiga = new Knjiga(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5), Zanr.valueOf(resultSet.getString(6)),
					resultSet.getString(7), TipKnjige.valueOf(resultSet.getString(8)), resultSet.getFloat(9),
					resultSet.getString(10), autorRepo.nadjiAutore(resultSet.getString(1)), resultSet.getString(12), resultSet.getString(11));
		}

		notifyObservers(knjiga);
	}

	public void pretraziPoKategoriji(String kategorija) throws SQLException {
		// kategorija
		ArrayList<Knjiga> knjige = new ArrayList<>();

		String query = "select * from Knjiga WHERE LOWER(zanr) like ? and obrisana=0";

		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, kategorija.toLowerCase());

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			ArrayList<Autor> autori = new ArrayList<>();

			String upit = "select a.idautor, a.ime, a.prz, a.slika from autorKnjige ak, autor a where ak.idautor = a.idautor and ak.isbn like ?";

			Connection konekcija = Konekcija.getConnection();
			PreparedStatement ps = konekcija.prepareStatement(upit);

			ps.setString(1, resultSet.getString(1));

			ResultSet skup = ps.executeQuery();

			while (skup.next()) {
				Autor a = new Autor(skup.getInt(1), skup.getString(2), skup.getString(3), "slika.png");
				autori.add(a);
			}

			Knjiga k = new Knjiga(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5), Zanr.valueOf(resultSet.getString(6)),
					resultSet.getString(7), TipKnjige.valueOf(resultSet.getString(8)), resultSet.getFloat(9),
					resultSet.getString(10), autori, resultSet.getString(12), resultSet.getString(11));
			knjige.add(k);
		}

		notifyObservers(knjige);
	}

	public void pretraziPoAutoru(int id) throws SQLException {
		ArrayList<Knjiga> knjige = new ArrayList<>();

		String query = "select k.isbn, k.naslov, k.godizdavanja, k.godstampanja, k.izdavac, k.zanr, k.formatk, k.tip, k.ocena, k.slika, k.opis, k.kljucnereci"
				+ " from autorKnjige ak, autor a, knjiga k where k.isbn = ak.isbn and ak.idautor = a.idautor and ak.idautor like ? and k.obrisana=0";

		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setInt(1, id);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			ArrayList<Autor> autori = new ArrayList<>();

			String upit = "select a.idautor, a.ime, a.prz, a.slika from autorKnjige ak, autor a where ak.idautor = a.idautor and ak.isbn like ?";

			Connection konekcija = Konekcija.getConnection();
			PreparedStatement ps = konekcija.prepareStatement(upit);

			ps.setString(1, resultSet.getString(1));

			ResultSet skup = ps.executeQuery();

			while (skup.next()) {
				Autor a = new Autor(skup.getInt(1), skup.getString(2), skup.getString(3), "slika.png");
				autori.add(a);
			}

			Knjiga k = new Knjiga(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5), Zanr.valueOf(resultSet.getString(6)),
					resultSet.getString(7), TipKnjige.valueOf(resultSet.getString(8)), resultSet.getFloat(9),
					resultSet.getString(10), autori, resultSet.getString(11), resultSet.getString(12));
			knjige.add(k);
		}

		notifyObservers(knjige);

	}

	public void nadjiKnjigeClana(String ulogovaniKorisnik) throws SQLException {

		ArrayList<Knjiga> knjige = new ArrayList<>();
		ArrayList<Date> datumiPozajmljivanja = new ArrayList<>();
		ArrayList<Date> datumiVracanja = new ArrayList<>();
		ArrayList<Integer> listaSifri = new ArrayList<>();

		String query = "select k.isbn, k.naslov, k.godizdavanja, k.godstampanja, k.izdavac, k.zanr, k.formatk, k.tip, k.ocena, k.slika,"
				+ " po.datumpoz, po.datumvr, k.opis, p.sifrapr, k.kljucnereci from knjiga k, clan c, primerak p, pozajmica po where c.email = ? and po.email = ? "
				+ " and po.sifrapr = p.sifrapr and p.isbn = k.isbn";

		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, ulogovaniKorisnik);
		preparedStatement.setString(2, ulogovaniKorisnik);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			ArrayList<Autor> autori = new ArrayList<>();

			String upit = "select a.idautor, a.ime, a.prz, a.slika from autorKnjige ak, autor a where ak.idautor = a.idautor and ak.isbn like ?";

			Connection konekcija = Konekcija.getConnection();
			PreparedStatement ps = konekcija.prepareStatement(upit);

			ps.setString(1, resultSet.getString(1));

			ResultSet skup = ps.executeQuery();

			while (skup.next()) {
				Autor a = new Autor(skup.getInt(1), skup.getString(2), skup.getString(3), "slika.png");
				autori.add(a);
			}

			Knjiga k = new Knjiga(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5), Zanr.valueOf(resultSet.getString(6)),
					resultSet.getString(7), TipKnjige.valueOf(resultSet.getString(8)), resultSet.getFloat(9),
					resultSet.getString(10), autori, resultSet.getString(13), resultSet.getString(15));
			knjige.add(k);
			datumiPozajmljivanja.add(resultSet.getDate(11));
			datumiVracanja.add(resultSet.getDate(12));
			listaSifri.add(resultSet.getInt(14));
		}
		PozajmljeneKnjigeDTO pozajmljeneKnjigeDTO = new PozajmljeneKnjigeDTO(knjige, datumiPozajmljivanja,
				datumiVracanja, listaSifri);
		notifyObservers(pozajmljeneKnjigeDTO);

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
	public void notifyObservers(Object o) {
		for (Observer observer : this.observers) {
			observer.updatePerformed(new RepositoryUpdateEvent(o));
		}
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : this.observers) {
			observer.updatePerformed(new RepositoryUpdateEvent());
		}
	}

	public void naprednaPretraga(NaprednaPretragaDTO dto) throws SQLException {
		ArrayList<Knjiga> knjige = new ArrayList<>();
		String query = "";
		
		String query1 = "select k.isbn, k.naslov, k.godizdavanja, k.godstampanja, k.izdavac, k.zanr, k.formatk, k.tip, k.ocena, k.slika, k.opis, k.kljucnereci"
				+ " from knjiga k WHERE k.obrisana=0 and LOWER(naslov) LIKE '%'|| ? ||'%'" + dto.getOperator2()
				+ " LOWER(izdavac) LIKE '%'|| ? ||'%'" + dto.getOperator3() + " LOWER(godizdavanja) LIKE '%'|| ? ||'%' "
				+ dto.getOperator4() + " LOWER(kljucnereci) LIKE '%'|| ? ||'%' ";

		String query2 = "select distinct k.isbn, k.naslov, k.godizdavanja, k.godstampanja, k.izdavac, k.zanr, k.formatk, k.tip, k.ocena, k.slika, k.opis, k.kljucnereci"
				+ " from autorKnjige ak, autor a, knjiga k where k.obrisana=0 and k.isbn = ak.isbn and ak.idautor = a.idautor and LOWER(a.ime) LIKE '%'|| ? ||'%'"
				+ " OR LOWER(a.prz) LIKE '%'|| ? ||'%' OR '%'|| ? || '%' LIKE LOWER(a.ime) OR '%'|| ? || '%' LIKE LOWER(a.prz)";

		switch (dto.getOperator1()) {
		case "AND":
			query = query1 + " INTERSECT " + query2;
			break;
		case "OR":
			query = query1 + " UNION " + query2;
			break;
		}

		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, dto.getNaslov().toLowerCase());
		preparedStatement.setString(2, dto.getIzdavac().toLowerCase());
		preparedStatement.setString(3, dto.getGodinaIzdavanja().toLowerCase());
		preparedStatement.setString(4, dto.getKljucneReci().toLowerCase());

		preparedStatement.setString(5, dto.getAutor().toLowerCase());
		preparedStatement.setString(6, dto.getAutor().toLowerCase());
		preparedStatement.setString(7, dto.getAutor().toLowerCase());
		preparedStatement.setString(8, dto.getAutor().toLowerCase());

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {

			ArrayList<Autor> autori = new ArrayList<>();

			String upit = "select a.idautor, a.ime, a.prz, a.slika from autorKnjige ak, autor a where ak.idautor = a.idautor and ak.isbn like ?";

			Connection konekcija = Konekcija.getConnection();
			PreparedStatement ps = konekcija.prepareStatement(upit);

			ps.setString(1, resultSet.getString(1));

			ResultSet skup = ps.executeQuery();

			while (skup.next()) {
				Autor a = new Autor(skup.getInt(1), skup.getString(2), skup.getString(3), "slika.png");
				autori.add(a);
			}
			Knjiga k = new Knjiga(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5), Zanr.valueOf(resultSet.getString(6)),
					resultSet.getString(7), TipKnjige.valueOf(resultSet.getString(8)), resultSet.getFloat(9),
					resultSet.getString(10), autori, resultSet.getString(11), resultSet.getString(12));
			knjige.add(k);
		}

		notifyObservers(knjige);

	}

	public void izlistajKnjige() throws SQLException {
		String query = "select isbn,naslov,izdavac,zanr from knjiga where obrisana=0";
		ArrayList<KnjigaDTO> knjige = new ArrayList<KnjigaDTO>();
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			ArrayList<Autor> autori = new ArrayList<>();

			String upit = "select a.idautor, a.ime, a.prz, a.slika from autorKnjige ak, autor a where ak.idautor = a.idautor and ak.isbn like ?";

			Connection konekcija = Konekcija.getConnection();
			PreparedStatement ps = konekcija.prepareStatement(upit);

			ps.setString(1, resultSet.getString(1));

			ResultSet skup = ps.executeQuery();

			while (skup.next()) {
				Autor a = new Autor(skup.getInt(1), skup.getString(2), skup.getString(3), "slika.png");
				autori.add(a);
			}
			KnjigaDTO knjiga = new KnjigaDTO(resultSet.getString(1), resultSet.getString(2), autori, resultSet.getString(3), Zanr.valueOf(resultSet.getString(4)));
			knjige.add(knjiga);
		}

		notifyObservers(knjige);
		
	}

	public void obrisiKnjigu(String isbn) throws SQLException {
		String query = "update knjiga set obrisana=1 where isbn=?";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		preparedStatement.setString(1, isbn);
		
		int resultSet = preparedStatement.executeUpdate();
		
	}

	public void dodajNovuKnjigu(Knjiga knjiga) throws SQLException {
		String query = "insert into KNJIGA (ISBN,NASLOV,GODIZDAVANJA,GODSTAMPANJA,IZDAVAC,ZANR,FORMATK,TIP,OCENA,OBRISANA) "
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		
		preparedStatement.setString(1, knjiga.getISBN());
		preparedStatement.setString(2, knjiga.getNaslov());
		preparedStatement.setString(3, knjiga.getGodinaIzdavanja());
		preparedStatement.setString(4, knjiga.getGodinaStampanja());
		preparedStatement.setString(5, knjiga.getIzdavac());
		preparedStatement.setString(6, knjiga.getZanr().name());
		preparedStatement.setString(7, knjiga.getFormat());
		preparedStatement.setString(8, knjiga.getTip().name());
		preparedStatement.setFloat(9, knjiga.getOcena());
		preparedStatement.setInt(10, 0);
		
		boolean resultSet = preparedStatement.execute();
		
		
		
	}

	public void dodajAutorKnjiga(String isbn, ArrayList<AutorDTO> autori) throws SQLException {
		for (AutorDTO autor : autori) {
			int idAutora = autorRepo.vratiIdAutora(autor.getIme(), autor.getPrezime());
			if(idAutora == -1) {
				int noviIdAutora = autorRepo.nadjiSledeciId();
				String query = "insert into autor (IDAUTOR,IME,PRZ) "
						+ "values(?, ?, ?)";
				Connection connection = Konekcija.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query,
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				
				
				preparedStatement.setInt(1, noviIdAutora);
				preparedStatement.setString(2, autor.getIme());
				preparedStatement.setString(3, autor.getPrezime());
				
				boolean resultSet = preparedStatement.execute();
				connection.close();
				
				idAutora = noviIdAutora;
			}
			
			String query = "insert into autorknjige (ISBN,IDAUTOR) "
					+ "values(?, ?)";
			Connection connection = Konekcija.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query,
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			
			preparedStatement.setString(1, isbn);
			preparedStatement.setInt(2, idAutora);
			
			boolean resultSet = preparedStatement.execute();
			connection.close();
				
		
		}
		izlistajKnjige();
		
	}

	public void izmeniKnjigu(String isbn, String opis, String kljucneReci, String slika) throws SQLException {
		String query = "update KNJIGA set opis=?, kljucnereci=?, slika=? where isbn = ?";
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		
		preparedStatement.setString(1, opis);
		preparedStatement.setString(2, kljucneReci);
		preparedStatement.setString(3, slika);
		preparedStatement.setString(4, isbn);
		
		int resultSet = preparedStatement.executeUpdate();
		
	}

	public void nadjiKnjiguObavestenje(String isbn) throws SQLException {
		String query = "select * from knjiga where isbn = ?";
		Knjiga knjiga = null;
		Connection connection = Konekcija.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, isbn);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			knjiga = new Knjiga(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5), Zanr.valueOf(resultSet.getString(6)),
					resultSet.getString(7), TipKnjige.valueOf(resultSet.getString(8)), resultSet.getFloat(9),
					resultSet.getString(10), autorRepo.nadjiAutore(resultSet.getString(1)), resultSet.getString(12), resultSet.getString(11));
		}

		notifyObservers(knjiga);
	}
}
