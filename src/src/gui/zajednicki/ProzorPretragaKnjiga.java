package gui.zajednicki;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dto.NaprednaPretragaDTO;
import exception.NepronadjeneVrednostiException;
import gui.clan.ProzorRezervacijeKnjiga;
import gui.pomocniElementi.NapraviOkrugloDugme;
import gui.pomocniElementi.StilDugmeta;
import gui.pomocniElementi.StilTekstPolja;
import kontroler.ClanKontroler;
import kontroler.KnjigaKontroler;
import kontroler.RezervacijaKontroler;
import model.Autor;
import model.Knjiga;
import model.Zanr;
import observer.Observer;
import observer.RepositoryUpdateEvent;
import repos.AutorRepo;
import repos.KnjigaRepo;

public class ProzorPretragaKnjiga implements Observer {
	private KnjigaKontroler knjigaKontroler;
	private AutorRepo autorRepo;
	String ulogovaniKorisnik;
	private ClanKontroler clanKontroler;
	private RezervacijaKontroler rezervacijaKontroler;

	private JFrame pozadina;
	private JTextField txtPretrazi;
	private int prviPutKliknuoPretrazi = 0;
	private int pozicija = 5;
	private int brojElemenata = 5;
	private ArrayList<JButton> listDugmicaAutori = new ArrayList<JButton>();
	private ArrayList<JButton> listDugmicaKategorije = new ArrayList<JButton>();
	private ArrayList<Autor> listaAutora = new ArrayList<Autor>();
	private ArrayList<Knjiga> listaPretrazenihKnjiga = new ArrayList<>();
	private JFrame glavniProzor;

	public ProzorPretragaKnjiga(KnjigaKontroler knjigaKontroler, AutorRepo autorRepo, KnjigaRepo knjigaRepo,
			JFrame glavniProzor, String ulogovaniKorisnik, ClanKontroler clanKontroler,
			RezervacijaKontroler rezervacijaKontroler) {
		this.knjigaKontroler = knjigaKontroler;
		this.autorRepo = autorRepo;
		this.glavniProzor = glavniProzor;
		this.clanKontroler = clanKontroler;
		this.ulogovaniKorisnik = ulogovaniKorisnik;
		this.rezervacijaKontroler = rezervacijaKontroler;

		this.knjigaKontroler.getKnjigaRepo().addObserver(this);
		initialize();
	}

	public ProzorPretragaKnjiga(KnjigaKontroler knjigaKontroler, AutorRepo autorRepo, KnjigaRepo knjigaRepo,
			JFrame glavniProzor, String ulogovaniKorisnik, RezervacijaKontroler rezervacijaKontroler) {
		this.knjigaKontroler = knjigaKontroler;
		this.autorRepo = autorRepo;
		this.glavniProzor = glavniProzor;
		this.ulogovaniKorisnik = ulogovaniKorisnik;
		this.rezervacijaKontroler = rezervacijaKontroler;

		this.knjigaKontroler.getKnjigaRepo().addObserver(this);
		initialize();
	}

	private void initialize() {
		pozadina = new JFrame();
		pozadina.setBounds(100, 100, 450, 300);
		pozadina.setSize(1230, 720);
		pozadina.setTitle("Pretraga knjiga");
		pozadina.setVisible(true);
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));
		pozadina.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pozadina.setVisible(false);
				pozadina.dispose();
				glavniProzor.setVisible(true);
			}
		});

		pozadina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		pozadina.getContentPane().add(panel, BorderLayout.CENTER);
		pozadina.setBackground(Color.WHITE);
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);

		JPanel pozadinskiPanel1 = new JPanel();
		pozadinskiPanel1.setBounds(57, 123, 1149, 151);
		pozadinskiPanel1.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel1);
		pozadinskiPanel1.setLayout(null);

		JPanel pozadinskiPanel2 = new JPanel();
		pozadinskiPanel2.setBounds(57, 285, 1147, 230);
		pozadinskiPanel2.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel2);
		pozadinskiPanel2.setLayout(null);

		JLabel labelaNaslova = new JLabel("Gradska biblioteka SIMS");
		labelaNaslova.setForeground(new Color(102, 0, 0));
		labelaNaslova.setFont(new Font("Times New Roman", Font.BOLD, 50));
		labelaNaslova.setBounds(57, -15, 550, 85);
		panel.add(labelaNaslova);

		txtPretrazi = new StilTekstPolja(100);
		txtPretrazi.setHorizontalAlignment(SwingConstants.LEFT);
		txtPretrazi.setFont(new Font("Times New Roman", Font.PLAIN | Font.ITALIC, 25));
		txtPretrazi.setText("Pretrazi");
		txtPretrazi.setBounds(117, 62, 838, 50);
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
		txtPretrazi.addActionListener(new ActionListener() {
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
		dugmePretrage.setIcon(new ImageIcon("slike\\pretraga.png"));
		dugmePretrage.setBackground(new Color(0xeadbc8));
		dugmePretrage.setBounds(57, 62, 50, 50);
		panel.add(dugmePretrage);
		pozadinskiPanel1.setLayout(null);

		JLabel labelaKategorije = new JLabel("Kategorije");
		labelaKategorije.setForeground(new Color(102, 0, 0));
		labelaKategorije.setFont(new Font("Times New Roman", Font.BOLD, 30));
		labelaKategorije.setBounds(10, 0, 134, 35);
		pozadinskiPanel1.add(labelaKategorije);

		JButton dugmePrijave = new JButton("Napredna  pretraga");
		dugmePrijave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProzorNaprednePretrage(knjigaKontroler, ulogovaniKorisnik, clanKontroler, rezervacijaKontroler,
						knjigaKontroler.getKnjigaRepo());
			}
		});
		dugmePrijave.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 21));
		dugmePrijave.setForeground(new Color(0xf4ece2));
		dugmePrijave.setBackground(new Color(102, 0, 0));
		dugmePrijave.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmePrijave.setUI(new StilDugmeta());
		dugmePrijave.setBounds(985, 65, 219, 46);
		panel.add(dugmePrijave);

		nacrtajKategorije(pozadinskiPanel1);

		for (JButton b : listDugmicaKategorije) {
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						knjigaKontroler.pretraziPoKategoriji(b.getText());
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

		JLabel labelaKnjige = new JLabel("Popularne knjige");
		labelaKnjige.setForeground(new Color(102, 0, 0));
		labelaKnjige.setFont(new Font("Times New Roman", Font.BOLD, 30));
		labelaKnjige.setBounds(10, 0, 300, 35);
		pozadinskiPanel2.add(labelaKnjige);

		JPanel pozadinskiPanel3 = new JPanel();
		pozadinskiPanel3.setBounds(57, 526, 1147, 144);
		panel.add(pozadinskiPanel3);
		pozadinskiPanel3.setLayout(null);
		pozadinskiPanel3.setBackground(new Color(244, 236, 226));

		try {
			listaAutora = autorRepo.getAutori();
		} catch (SQLException e1) {
			new ProzorObavestenjaKorisnika("Doslo je do greske u programu!");
		}

		JLabel lblPopularniAutori = new JLabel("Popularni autori");
		lblPopularniAutori.setForeground(new Color(102, 0, 0));
		lblPopularniAutori.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblPopularniAutori.setBounds(10, 0, 300, 35);
		pozadinskiPanel3.add(lblPopularniAutori);
		JButton dugmeNapred = new NapraviOkrugloDugme("");
		dugmeNapred.setBounds(1086, 59, 51, 51);
		pozadinskiPanel3.add(dugmeNapred);
		dugmeNapred.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (pozicija < listaAutora.size()) {
					pozicija++;
					izbrisiAutore(pozadinskiPanel3);
					nacrtajAutore(pozadinskiPanel3, listaAutora);
					dodajAkciju();
				} else {
					dugmeNapred.disable();
				}
			}
		});
		dugmeNapred.setIcon(new ImageIcon("slike\\napred.png"));
		dugmeNapred.setBackground(new Color(0xf4ece2));

		JButton dugmeNazad = new NapraviOkrugloDugme("");
		dugmeNazad.setBounds(10, 59, 51, 51);
		pozadinskiPanel3.add(dugmeNazad);
		dugmeNazad.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (pozicija > 5) {
					pozicija--;
					izbrisiAutore(pozadinskiPanel3);
					nacrtajAutore(pozadinskiPanel3, listaAutora);
					dodajAkciju();
				} else {
					dugmeNazad.disable();
				}
			}
		});
		dugmeNazad.setIcon(new ImageIcon("slike\\nazad.png"));
		dugmeNazad.setBackground(new Color(0xf4ece2));

		String[] listSlikaKnjiga = { "maliPrinc.png", "na drini cuprija.png", "mindset.png", "bludni sin.png",
				"romeo i julija.png" };
		String[] listaISBN = {"978-86-52-12215-8", "978-86-75-80306-5", "978-03-45-47232-8", "978-86-83-49964-9", "978-86-10-01769-4"};
		int x = 120;
		for (int j = 0; j < 5; j++) {
			JButton dugmeKnjige = new JButton("");
			dugmeKnjige.setName(listaISBN[j]);
			ImageIcon slika = new ImageIcon("slike\\" + listSlikaKnjiga[j]);
			dugmeKnjige.setIcon(slika);
			dugmeKnjige.setBounds(x, 50, 120, 171);
			dugmeKnjige.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pozadina.setVisible(false);
					new ProzorRezervacijeKnjiga(dugmeKnjige.getName(), knjigaKontroler, ulogovaniKorisnik,
							clanKontroler, rezervacijaKontroler, pozadina);

				}
			});
			pozadinskiPanel2.add(dugmeKnjige);
			x += 210;
		}

		nacrtajAutore(pozadinskiPanel3, listaAutora);
		dodajAkciju();

	}

	private void dodajAkciju() {
		for (JButton b : listDugmicaAutori) {
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {

						knjigaKontroler.pretraziPoAutoru(b.getName());
						if (listaPretrazenihKnjiga.size() == 0)
							new ProzorObavestenjaKorisnika("Nema knjiga za zadatog autora!");
						else
							new ProzorPrikazaRezultataPretrage(knjigaKontroler, ulogovaniKorisnik, clanKontroler,
									rezervacijaKontroler, listaPretrazenihKnjiga);
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
	}

	private void izbrisiAutore(JPanel pozadinskiPanel1) {
		for (JButton b : listDugmicaAutori) {
			pozadinskiPanel1.remove(b);
			pozadinskiPanel1.repaint();
		}
		listDugmicaAutori.clear();
	}

	private void nacrtajKategorije(JPanel pozadinskiPanel1) {
		int x = 10;
		int y = 38;
		int red = 1;
		int brojKomponenti = 1;
		for (Zanr zanr : Zanr.values()) {
			if ((red == 1) && (brojKomponenti == 8)) {
				red++;
				brojKomponenti = 1;
				y += 38;
				x = 10;
			} else if ((red == 2) && (brojKomponenti == 9)) {
				red++;
				brojKomponenti = 1;
				y += 38;
				x = 10;
			}

			JButton dugmeKategorije = new JButton(zanr.getAction());
			dugmeKategorije.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
			dugmeKategorije.setForeground(new Color(0xf4ece2));
			dugmeKategorije.setBounds(x, y, zanr.getAction().length() * 10 + 40, 30);
			dugmeKategorije.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
			dugmeKategorije.setUI(new StilDugmeta());
			dugmeKategorije.setBackground(new Color(102, 0, 0));
			pozadinskiPanel1.add(dugmeKategorije);
			listDugmicaKategorije.add(dugmeKategorije);
			brojKomponenti++;
			x += zanr.getAction().length() * 10 + 45;
		}
	}

	private void nacrtajAutore(JPanel pozadinskiPanel3, ArrayList<Autor> listaAutora) {
		int xx = 85;
		for (int j = pozicija - brojElemenata; j < pozicija; j++) {
			JButton dugmeAutora = new JButton(
					"<html>" + listaAutora.get(j).getIme() + "<br/>" + listaAutora.get(j).getPrezime() + "<html>");
			dugmeAutora.setName(listaAutora.get(j).getIdAutor() + "");
			dugmeAutora.setFont(new Font("Times New Roman", Font.BOLD, 17));
			dugmeAutora.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
			dugmeAutora.setUI(new StilDugmeta());
			dugmeAutora.setBackground(new Color(102, 0, 0));
			dugmeAutora.setForeground(new Color(0xf4ece2));
			dugmeAutora.setIcon(new ImageIcon("slike\\" + listaAutora.get(j).getSlika()));
			dugmeAutora.setHorizontalAlignment(SwingConstants.LEFT);
			dugmeAutora.setBounds(xx, 45, 182, 80);
			pozadinskiPanel3.add(dugmeAutora);
			listDugmicaAutori.add(dugmeAutora);
			xx += 200;
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
