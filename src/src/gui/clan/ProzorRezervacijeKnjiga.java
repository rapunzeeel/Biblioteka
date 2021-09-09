package gui.clan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import exception.MaxBrojPozajmicaException;
import exception.ZahtevVecPostojiException;
import gui.pomocniElementi.StilDugmeta;
import gui.zajednicki.ProzorObavestenjaKorisnika;
import kontroler.ClanKontroler;
import kontroler.KnjigaKontroler;
import kontroler.RezervacijaKontroler;
import model.Autor;
import model.Clan;
import model.Knjiga;
import observer.Observer;
import observer.RepositoryUpdateEvent;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ProzorRezervacijeKnjiga implements Observer {
	private JFrame pozadina;
	private String isbn;
	private Knjiga knjiga;
	private KnjigaKontroler knjigaKontroler;
	private ClanKontroler clanKontroler;
	private Clan ulogovaniKorisnik;
	private String email;
	private RezervacijaKontroler rezervacijaKontroler;
	private JFrame glavniProzor;
	private JLabel lblObavestenje;

	public ProzorRezervacijeKnjiga(String isbn, KnjigaKontroler knjigaKontroler, String email,
			ClanKontroler clanKontroler, RezervacijaKontroler rezervacijaKontroler, JFrame glavniProzor) {
		this.knjigaKontroler = knjigaKontroler;
		this.isbn = isbn;
		this.email = email;
		this.clanKontroler = clanKontroler;
		this.rezervacijaKontroler = rezervacijaKontroler;
		this.glavniProzor = glavniProzor;

		this.knjigaKontroler.getKnjigaRepo().addObserver(this);
		initialize();
	}

	private void initialize() {
		try {
			this.knjigaKontroler.nadjiKnjigu(isbn);

		} catch (Exception e2) {
			new ProzorObavestenjaKorisnika("Doslo je do greske u sistemu!");
			return;
		}
		pozadina = new JFrame();
		pozadina.setBounds(100, 100, 810, 610);
		pozadina.setTitle("Slanje zahteva za rezervaciju knjige");
		pozadina.setVisible(true);
		pozadina.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pozadina.setVisible(false);
				glavniProzor.setVisible(true);
				pozadina.dispose();
			}
		});
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));

		JPanel panel = new JPanel();
		pozadina.getContentPane().add(panel, BorderLayout.CENTER);
		pozadina.setBackground(Color.WHITE);
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);

		lblObavestenje = new JLabel("");
		lblObavestenje.setBounds(460, 252, 350, 26);
		lblObavestenje.setForeground(new Color(0, 102, 0));
		lblObavestenje.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		panel.add(lblObavestenje);
		lblObavestenje.setVisible(false);

		JLabel labelaKorice = new JLabel("");
		labelaKorice.setBounds(56, 59, 150, 190);
		labelaKorice.setIcon(new ImageIcon("slike\\" + knjiga.getSlika()));
		panel.add(labelaKorice);

		JLabel labelaNazivKnjige = new JLabel(knjiga.getNaslov());
		labelaNazivKnjige.setFont(new Font("Times New Roman", Font.BOLD, 30));
		labelaNazivKnjige.setBounds(236, 59, 290, 44);
		labelaNazivKnjige.setForeground(new Color(102, 0, 0));
		panel.add(labelaNazivKnjige);
		String slikaOcene = nadjiSlikuOcene();

		JLabel labelaOcena = new JLabel();
		labelaOcena.setBounds(236, 114, 289, 55);
		labelaOcena.setIcon(new ImageIcon(slikaOcene));
		panel.add(labelaOcena);

		JLabel labelaNazivAutora = new JLabel(
				knjiga.getAutori().get(0).getIme() + " " + knjiga.getAutori().get(0).getPrezime());
		labelaNazivAutora.setFont(new Font("Times New Roman", Font.BOLD, 25));
		labelaNazivAutora.setBounds(236, 170, 261, 30);
		labelaNazivAutora.setForeground(new Color(102, 0, 0));
		panel.add(labelaNazivAutora);

		JLabel labelaIzdavaca = new JLabel(knjiga.getIzdavac());
		labelaIzdavaca.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		labelaIzdavaca.setBounds(236, 211, 261, 30);
		labelaIzdavaca.setForeground(new Color(102, 0, 0));
		panel.add(labelaIzdavaca);

		JLabel labelaOpis = new JLabel("Opis");
		labelaOpis.setFont(new Font("Times New Roman", Font.BOLD, 25));
		labelaOpis.setBounds(56, 260, 151, 44);
		labelaOpis.setForeground(new Color(102, 0, 0));
		panel.add(labelaOpis);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 301, 714, 122);
		panel.add(scrollPane);
		JTextArea opisKnjige = new JTextArea();
		scrollPane.setViewportView(opisKnjige);
		opisKnjige.setLineWrap(true);
		opisKnjige.setWrapStyleWord(true);
		opisKnjige.setText(knjiga.getOpis());
		opisKnjige.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		opisKnjige.setForeground(new Color(102, 0, 0));
		opisKnjige.setBackground(new Color(0xf4ece2));

		JButton dugmeRezervacije = new JButton("Rezerviši");
		dugmeRezervacije.setFont(new Font("Times New Roman", Font.BOLD, 30));
		dugmeRezervacije.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmeRezervacije.setUI(new StilDugmeta());
		dugmeRezervacije.setBackground(new Color(102, 0, 0));
		dugmeRezervacije.setForeground(new Color(0xf4ece2));
		dugmeRezervacije.setIcon(new ImageIcon("slike\\rezervacija.png"));
		dugmeRezervacije.setHorizontalAlignment(SwingConstants.LEFT);
		dugmeRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (email == null) {
						lblObavestenje.setText("Morate biti ulogovani da biste rezervisali knjigu!");
						lblObavestenje.setVisible(true);
						lblObavestenje.setForeground(new Color(102, 0, 0));
					} else {
						ulogovaniKorisnik = clanKontroler.nadjiClana(email);
						clanKontroler.imaMogucnostPozajmice(email);
						rezervacijaKontroler.dodajZahtevZaRezervaciju(email, knjiga);
						lblObavestenje.setText("Uspešno je poslat zahtev za rezervaciju!");
						lblObavestenje.setVisible(true);
						lblObavestenje.setForeground(new Color(0, 102, 0));
						dugmeRezervacije.setEnabled(false);
					}
				} catch (MaxBrojPozajmicaException e3) {
					lblObavestenje.setText(e3.getValueName());
					lblObavestenje.setVisible(true);
					lblObavestenje.setForeground(new Color(102, 0, 0));
				} catch (SQLIntegrityConstraintViolationException e2) {
					lblObavestenje.setText("Vec ste rezervisali izabranu knjigu!");
					lblObavestenje.setVisible(true);
					lblObavestenje.setForeground(new Color(102, 0, 0));
				} catch (Exception e1) {
					lblObavestenje.setText("Doslo je do greske u sistemu!");
					lblObavestenje.setVisible(true);
					lblObavestenje.setForeground(new Color(102, 0, 0));
					return;
				}
			}
		});
		dugmeRezervacije.setBounds(538, 191, 234, 50);
		panel.add(dugmeRezervacije);

		JPanel linija = new JPanel();
		linija.setBackground(new Color(102, 0, 0));
		linija.setBounds(56, 434, 714, 3);
		panel.add(linija);

		String autori = "";
		for (Autor a : knjiga.getAutori()) {
			autori += a.getIme() + " " + a.getPrezime() + ", ";
		}
		autori = autori.substring(0, autori.length() - 2);

		JLabel labeleAutori = new JLabel("Autori: " + autori);
		labeleAutori.setFont(new Font("Times New Roman", Font.BOLD, 20));
		labeleAutori.setBounds(56, 430, 400, 44);
		labeleAutori.setForeground(new Color(102, 0, 0));
		panel.add(labeleAutori);

		JLabel labelaNaslov = new JLabel("Naslov: " + knjiga.getNaslov());
		labelaNaslov.setFont(new Font("Times New Roman", Font.BOLD, 20));
		labelaNaslov.setBounds(56, 460, 261, 30);
		labelaNaslov.setForeground(new Color(102, 0, 0));
		panel.add(labelaNaslov);

		JLabel labelaIzdavacaK = new JLabel("Izdavač: " + knjiga.getIzdavac() + ", " + knjiga.getGodinaStampanja());
		labelaIzdavacaK.setFont(new Font("Times New Roman", Font.BOLD, 20));
		labelaIzdavacaK.setBounds(56, 483, 400, 30);
		labelaIzdavacaK.setForeground(new Color(102, 0, 0));
		panel.add(labelaIzdavacaK);

		JLabel labelaFizickiOpis = new JLabel("Fizički opis: " + knjiga.getFormat());
		labelaFizickiOpis.setFont(new Font("Times New Roman", Font.BOLD, 20));
		labelaFizickiOpis.setBounds(56, 500, 300, 44);
		labelaFizickiOpis.setForeground(new Color(102, 0, 0));
		panel.add(labelaFizickiOpis);

		JLabel labelaISBN = new JLabel("ISBN: " + knjiga.getISBN());
		labelaISBN.setFont(new Font("Times New Roman", Font.BOLD, 20));
		labelaISBN.setBounds(56, 525, 300, 44);
		labelaISBN.setForeground(new Color(102, 0, 0));
		panel.add(labelaISBN);

		try {
			rezervacijaKontroler.proveriPostojanjeZahteva(email, knjiga);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ZahtevVecPostojiException e1) {
			lblObavestenje.setText("Za ovu knjigu ste već poslali zahtev!");
			lblObavestenje.setVisible(true);
			lblObavestenje.setForeground(new Color(102, 0, 0));
			dugmeRezervacije.setEnabled(false);
		}
	}

	private String nadjiSlikuOcene() {
		if (knjiga.getOcena() == 0.0) {
			return "slike\\0.png";
		} else if (knjiga.getOcena() == 0.5) {
			return "slike\\0_5.png";
		} else if (knjiga.getOcena() == 1.0) {
			return "slike\\1.0.png";
		} else if (knjiga.getOcena() == 1.5) {
			return "slike\\1.5.png";
		} else if (knjiga.getOcena() == 2.0) {
			return "slike\\2.png";
		} else if (knjiga.getOcena() == 2.5) {
			return "slike\\2.5.png";
		} else if (knjiga.getOcena() == 3.0) {
			return "slike\\3.png";
		} else if (knjiga.getOcena() == 3.5) {
			return "slike\\3.5.png";
		} else if (knjiga.getOcena() == 4.0) {
			return "slike\\4.png";
		} else if (knjiga.getOcena() == 4.5) {
			return "slike\\4_5.png";
		} else {
			return "slike\\5.png";
		}
	}

	@Override
	public void updatePerformed(RepositoryUpdateEvent event) {
		try {
			knjiga = (Knjiga) event.getAkcija();
			if (knjiga == null) {
				lblObavestenje.setText("Nije pronadjena knjiga po zadatim koricama!");
				lblObavestenje.setVisible(true);
				lblObavestenje.setForeground(new Color(102, 0, 0));
			}
		} catch (Exception e) {

		}
	}
}
