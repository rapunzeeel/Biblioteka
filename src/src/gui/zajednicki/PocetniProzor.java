package gui.zajednicki;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import dto.NaprednaPretragaDTO;
import exception.NepronadjeneVrednostiException;
import gui.clan.ProzorRezervacijeKnjiga;
import gui.pomocniElementi.NapraviOkrugloDugme;
import gui.pomocniElementi.StilDugmeta;
import gui.pomocniElementi.StilTekstPolja;
import kontroler.BibliotekaKontroler;
import kontroler.ClanKontroler;
import kontroler.KnjigaKontroler;
import kontroler.RezervacijaKontroler;
import model.Knjiga;
import observer.Observer;
import observer.RepositoryUpdateEvent;
import repos.AutorRepo;
import repos.BibliotekaRepo;
import repos.BibliotekarRepo;
import repos.ClanRepo;
import repos.FilijalaRepo;
import repos.KnjigaRepo;
import repos.MestoPrimerkaRepo;
import repos.MestoRepo;
import repos.PozajmicaRepo;
import repos.PrimerakRepo;
import repos.RecenzijaRepo;
import repos.RezervacijaRepo;
import repos.ZahtevZaRezervacijuRepo;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PocetniProzor implements Observer {
	private JFrame pozadina;
	private JTextField txtPretrazi;
	private int i = 9;
	private int brojElemenata = 9;
	private int prviPutKliknuoPretrazi = 0;
	private ArrayList<JButton> listDugmica = new ArrayList<JButton>();
	private ArrayList<JLabel> listLabela = new ArrayList<JLabel>();
	private String ulogovaniKorisnik;

	private AutorRepo autorRepo;
	private BibliotekarRepo bibliotekarRepo;
	private BibliotekaRepo bibliotekaRepo;
	private ClanRepo clanRepo;
	private FilijalaRepo filijalaRepo;
	private KnjigaRepo knjigaRepo;
	private MestoRepo mestoRepo;
	private MestoPrimerkaRepo mestoPrimerkaRepo;
	private PrimerakRepo primerakRepo;
	private PozajmicaRepo pozajmicaRepo;
	private ZahtevZaRezervacijuRepo zahtevZaRezervacijuRepo;
	private RecenzijaRepo recenzijaRepo;
	private RezervacijaRepo rezervacijaRepo;

	private RezervacijaKontroler rezervacijaKontroler;
	private KnjigaKontroler knjigaKontroler;
	private ClanKontroler clanKontroler;
	private BibliotekaKontroler bibliotekaKontroler;

	private ArrayList<Knjiga> listaPretrazenihKnjiga = new ArrayList<>();

	public PocetniProzor() {
		mestoRepo = new MestoRepo();
		bibliotekarRepo = new BibliotekarRepo(mestoRepo);
		autorRepo = new AutorRepo();
		knjigaRepo = new KnjigaRepo(autorRepo);
		mestoPrimerkaRepo = new MestoPrimerkaRepo();
		primerakRepo = new PrimerakRepo(knjigaRepo, mestoPrimerkaRepo);
		pozajmicaRepo = new PozajmicaRepo(primerakRepo);
		zahtevZaRezervacijuRepo = new ZahtevZaRezervacijuRepo(knjigaRepo);
		rezervacijaRepo = new RezervacijaRepo(knjigaRepo, zahtevZaRezervacijuRepo, primerakRepo);
		recenzijaRepo = new RecenzijaRepo(knjigaRepo);
		clanRepo = new ClanRepo(mestoRepo, pozajmicaRepo, recenzijaRepo);
		filijalaRepo = new FilijalaRepo(mestoRepo, bibliotekarRepo);
		bibliotekaRepo = new BibliotekaRepo(filijalaRepo, knjigaRepo, clanRepo);

		rezervacijaKontroler = new RezervacijaKontroler(knjigaRepo, zahtevZaRezervacijuRepo, primerakRepo);
		knjigaKontroler = new KnjigaKontroler(autorRepo, knjigaRepo);
		clanKontroler = new ClanKontroler(mestoRepo, pozajmicaRepo, recenzijaRepo, clanRepo);
		bibliotekaKontroler = new BibliotekaKontroler(pozajmicaRepo, primerakRepo, filijalaRepo, knjigaRepo, clanRepo,
				bibliotekarRepo, bibliotekaRepo, mestoRepo, zahtevZaRezervacijuRepo, rezervacijaRepo);

		this.knjigaRepo.addObserver(this);
		initialize();
	}

	private void initialize() {

		pozadina = new JFrame();
		pozadina.setBounds(100, 100, 450, 300);
		pozadina.setSize(1230, 700);
		pozadina.setTitle("Biblioteka");
		pozadina.setVisible(true);
		pozadina.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));

		JPanel panel = new JPanel();
		pozadina.getContentPane().add(panel, BorderLayout.CENTER);
		pozadina.setBackground(Color.WHITE);
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);

		JPanel pozadinskiPanel1 = new JPanel();
		pozadinskiPanel1.setBounds(10, 116, 1196, 190);
		pozadinskiPanel1.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel1);

		JPanel pozadinskiPanel2 = new JPanel();
		pozadinskiPanel2.setBounds(10, 323, 1196, 327);
		pozadinskiPanel2.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel2);
		pozadinskiPanel2.setLayout(null);

		JLabel labelaNaslova = new JLabel("Gradska biblioteka SIMS");
		labelaNaslova.setForeground(new Color(102, 0, 0));
		labelaNaslova.setFont(new Font("Times New Roman", Font.BOLD, 50));
		labelaNaslova.setBounds(57, 21, 550, 85);
		panel.add(labelaNaslova);

		txtPretrazi = new StilTekstPolja(100);
		txtPretrazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (prviPutKliknuoPretrazi == 0) {
					txtPretrazi.setText("");
					prviPutKliknuoPretrazi++;
					txtPretrazi.setForeground(new Color(102, 0, 0));
				}
				try {
					NaprednaPretragaDTO dto = new NaprednaPretragaDTO(txtPretrazi.getText(), txtPretrazi.getText(),
							txtPretrazi.getText(), txtPretrazi.getText(), txtPretrazi.getText());
					knjigaKontroler.naprednaPretraga(dto);
					if (listaPretrazenihKnjiga.size() == 0)
						new ProzorObavestenjaKorisnika("Nema knjiga za zadati unos!");
					else
						new ProzorPrikazaRezultataPretrage(knjigaKontroler, ulogovaniKorisnik, clanKontroler,
								rezervacijaKontroler, listaPretrazenihKnjiga);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		txtPretrazi.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPretrazi.setFont(new Font("Times New Roman", Font.PLAIN | Font.ITALIC, 25));
		txtPretrazi.setText("Pretrazi");
		txtPretrazi.setBounds(715, 40, 256, 50);
		txtPretrazi.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		txtPretrazi.setForeground(new Color(102, 0, 0));
		panel.add(txtPretrazi);
		txtPretrazi.setColumns(10);
		txtPretrazi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (prviPutKliknuoPretrazi == 0) {
					txtPretrazi.setText("");
					prviPutKliknuoPretrazi++;
					txtPretrazi.setForeground(new Color(102, 0, 0));
				}
			}
		});

		JButton dugmePretrage = new NapraviOkrugloDugme("");
		dugmePretrage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					NaprednaPretragaDTO dto = new NaprednaPretragaDTO(txtPretrazi.getText(), txtPretrazi.getText(),
							txtPretrazi.getText(), txtPretrazi.getText(), txtPretrazi.getText());
					knjigaKontroler.naprednaPretraga(dto);
					if (listaPretrazenihKnjiga.size() == 0)
						new ProzorObavestenjaKorisnika("Nema knjiga za zadati unos!");
					else
						new ProzorPrikazaRezultataPretrage(knjigaKontroler, ulogovaniKorisnik, clanKontroler,
								rezervacijaKontroler, listaPretrazenihKnjiga);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		dugmePretrage.setIcon(new ImageIcon("slike\\pretragaVeca.png"));
		dugmePretrage.setBackground(new Color(0xeadbc8));
		dugmePretrage.setBounds(977, 23, 80, 80);
		panel.add(dugmePretrage);

		JButton dugmeInformacija = new NapraviOkrugloDugme("");
		dugmeInformacija.setIcon(new ImageIcon("slike\\informa.png"));
		dugmeInformacija.setBackground(new Color(0xeadbc8));
		dugmeInformacija.setBounds(627, 23, 80, 80);
		panel.add(dugmeInformacija);
		pozadinskiPanel1.setLayout(null);
		dugmeInformacija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pozadina.setVisible(false);
				new ProzorInformacija(bibliotekaRepo);

			}
		});

		JLabel labelaKategorije = new JLabel("Kategorije");
		labelaKategorije.setForeground(new Color(102, 0, 0));
		labelaKategorije.setFont(new Font("Times New Roman", Font.BOLD, 30));
		labelaKategorije.setBounds(57, 6, 139, 35);
		pozadinskiPanel1.add(labelaKategorije);

		JButton dugmePrijave = new JButton("Prijava");
		dugmePrijave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pozadina.setVisible(false);
				new ProzorPrijave(clanKontroler, bibliotekaKontroler, autorRepo, knjigaRepo, knjigaKontroler,
						ulogovaniKorisnik, rezervacijaKontroler);
			}
		});
		dugmePrijave.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 21));
		dugmePrijave.setForeground(Color.WHITE);
		dugmePrijave.setBackground(new Color(102, 0, 0));
		dugmePrijave.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmePrijave.setUI(new StilDugmeta());
		dugmePrijave.setBounds(1066, 40, 99, 46);
		panel.add(dugmePrijave);

		String[] listSlika = { "slike\\ljubavni.png", "slike\\decije.png", "slike\\motivacija i inspiracija.png",
				"slike\\fantazija.png", "slike\\klasici.png", "slike\\naucna fantastika.png", "slike\\zdravlje.png",
				"slike\\marketing i menadzment.png", "slike\\psihologija.png", "slike\\istorijski.png",
				"slike\\umetnost.png", "slike\\detektivski.png", "slike\\biografija.png", "slike\\drama.png",
				"slike\\edukacija.png", "slike\\poezija.png" };
		String[] listaLabela = { "romantika", "dečije", "<html>motivacija i <br/>inspiracija<html>", "fantazija",
				"klasici", "<html>naučna <br/>fantastika<html>", "zdravlje", "<html>marketing i <br/>menadžment<html>",
				"psihologija", "istorijski", "umetnost", "detektivski", "biografija", "drama", "edukacija", "poezija" };

		nacrtajKategorije(pozadinskiPanel1, listSlika, listaLabela);

		JButton dugmeNazad = new NapraviOkrugloDugme("");
		dugmeNazad.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (i > 9) {
					i--;
					izbrisiKategorije(pozadinskiPanel1);
					nacrtajKategorije(pozadinskiPanel1, listSlika, listaLabela);
				} else {
					dugmeNazad.disable();
				}
			}
		});
		dugmeNazad.setIcon(new ImageIcon("slike\\nazad.png"));
		dugmeNazad.setBounds(35, 60, 51, 51);
		dugmeNazad.setBackground(new Color(0xf4ece2));
		pozadinskiPanel1.add(dugmeNazad);
		JButton dugmeNapred = new NapraviOkrugloDugme("");
		dugmeNapred.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (i < listSlika.length) {
					i++;
					izbrisiKategorije(pozadinskiPanel1);
					nacrtajKategorije(pozadinskiPanel1, listSlika, listaLabela);
				} else {
					dugmeNapred.disable();
				}
			}
		});
		dugmeNapred.setIcon(new ImageIcon("slike\\napred.png"));
		dugmeNapred.setBounds(1140, 60, 51, 51);
		dugmeNapred.setBackground(new Color(0xf4ece2));
		pozadinskiPanel1.add(dugmeNapred);

		JLabel labelaKnjige = new JLabel("Popularne knjige");
		labelaKnjige.setForeground(new Color(102, 0, 0));
		labelaKnjige.setFont(new Font("Times New Roman", Font.BOLD, 30));
		labelaKnjige.setBounds(57, 10, 300, 35);
		pozadinskiPanel2.add(labelaKnjige);

		String[] listSlikaKnjiga = { "maliPrinc.png", "na drini cuprija.png", "mindset.png", "bludni sin.png",
				"romeo i julija.png" };
		String[] listaLabelaKnjiga = { "<html>Mali Princ <br/> Antoan de Sent <br/>Egziperi<html>",
				"<html>Na Drini ćuprija <br/> Ivo Andrić<html>", "<html>Mindset <br/> Carol Dweck<html>",
				"<html>Bludni sin<br/> Čarls Bukovski<html>", "<html>Romeo i Julija<br/> Vilijam Šekspir<html>" };
		String[] listaISBN = {"978-86-52-12215-8", "978-86-75-80306-5", "978-03-45-47232-8", "978-86-83-49964-9", "978-86-10-01769-4"};
		
		int x = 120;
		for (int j = 0; j < 5; j++) {
			JButton dugmeKnjige = new JButton("");
			dugmeKnjige.setName(listaISBN[j]);
			ImageIcon slika = new ImageIcon("slike\\" + listSlikaKnjiga[j]);
			dugmeKnjige.setIcon(slika);
			dugmeKnjige.setBounds(x, 70, 120, 171);
			dugmeKnjige.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pozadina.setVisible(false);
					new ProzorRezervacijeKnjiga(dugmeKnjige.getName(), knjigaKontroler, ulogovaniKorisnik,
							clanKontroler, rezervacijaKontroler, pozadina);

				}
			});
			pozadinskiPanel2.add(dugmeKnjige);

			JLabel labelaNaslovaKategorijeKnjige = new JLabel(listaLabelaKnjiga[j]);
			labelaNaslovaKategorijeKnjige.setForeground(new Color(102, 0, 0));
			labelaNaslovaKategorijeKnjige.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			labelaNaslovaKategorijeKnjige.setHorizontalAlignment(SwingConstants.LEFT);
			labelaNaslovaKategorijeKnjige.setBounds(x, 240, 130, 70);
			pozadinskiPanel2.add(labelaNaslovaKategorijeKnjige);
			x += 210;
		}
	}

	private void izbrisiKategorije(JPanel pozadinskiPanel1) {
		for (JButton b : listDugmica) {
			pozadinskiPanel1.remove(b);
			pozadinskiPanel1.repaint();
		}
		for (JLabel l : listLabela) {
			pozadinskiPanel1.remove(l);
			pozadinskiPanel1.repaint();
		}
		listDugmica.clear();
		listLabela.clear();
	}

	private void nacrtajKategorije(JPanel pozadinskiPanel1, String[] listSlika, String[] listaLabela) {
		int x = 100;
		int xk = 30;
		for (int j = i - brojElemenata; j < i; j++) {
			JButton dugmeKategorije = new NapraviOkrugloDugme("");
			dugmeKategorije.setName(listSlika[j].substring(6, listSlika[j].length() - 4));
			dugmeKategorije.setIcon(new ImageIcon(listSlika[j]));
			dugmeKategorije.setBounds(x, 54, 71, 71);
			pozadinskiPanel1.add(dugmeKategorije);
			listDugmica.add(dugmeKategorije);
			JLabel labelaNaslovaKategorije = new JLabel(listaLabela[j]);
			labelaNaslovaKategorije.setForeground(new Color(102, 0, 0));
			labelaNaslovaKategorije.setFont(new Font("Times New Roman", Font.BOLD, 17));
			labelaNaslovaKategorije.setHorizontalAlignment(SwingConstants.CENTER);
			labelaNaslovaKategorije.setBounds(xk, 120, 190, 45);
			pozadinskiPanel1.add(labelaNaslovaKategorije);
			listLabela.add(labelaNaslovaKategorije);
			x += 120;
			xk += 120;
		}
		dodajAkcije();

	}

	private void dodajAkcije() {
		for (JButton b : listDugmica) {
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						knjigaKontroler.pretraziPoKategoriji(b.getName());
						if (listaPretrazenihKnjiga.size() > 0)
							new ProzorPrikazaRezultataPretrage(knjigaKontroler, ulogovaniKorisnik, clanKontroler,
									rezervacijaKontroler, listaPretrazenihKnjiga);
						else
							new ProzorObavestenjaKorisnika("Nema knjiga za izabranu kategoriju!");
					} catch (NepronadjeneVrednostiException e1) {
						new ProzorObavestenjaKorisnika(e1.getValueName());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
	}

	@Override
	public void updatePerformed(RepositoryUpdateEvent event) {

		try {
			listaPretrazenihKnjiga = (ArrayList<Knjiga>) event.getAkcija();
		} catch (Exception e) {

		}
	}
}
