package gui.bibliotekar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import exception.PrijavljenAdministratorException;
import exception.PrijavljenObradjivacException;
import gui.pomocniElementi.StilDugmeta;
import gui.zajednicki.PocetniProzor;
import gui.zajednicki.ProzorPretragaKnjiga;
import gui.zajednicki.ProzorPromeneLozinke;
import gui.zajednicki.ProzorRegistracije;
import kontroler.BibliotekaKontroler;
import kontroler.KnjigaKontroler;
import kontroler.RezervacijaKontroler;
import repos.AutorRepo;
import repos.KnjigaRepo;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ProzorBibliotekara {
	private JFrame pozadina;
	private JLabel lblOdrzavanjeBibliotekara;
	
	private BibliotekaKontroler bibliotekaKontroler;
	private KnjigaKontroler knjigaKontroler;
	private AutorRepo autorRepo;
	private KnjigaRepo knjigaRepo;
	private RezervacijaKontroler rezervacijaKontroler;
	private String ulogovaniKorisnik;

	public ProzorBibliotekara(BibliotekaKontroler bibliotekaKontroler, AutorRepo autorRepo, KnjigaRepo knjigaRepo,
			KnjigaKontroler knjigaKontroler, String ulogovaniKorisnik, RezervacijaKontroler rezervacijaKontroler) {
		this.bibliotekaKontroler = bibliotekaKontroler;
		this.knjigaKontroler = knjigaKontroler;
		this.autorRepo = autorRepo;
		this.knjigaRepo = knjigaRepo;
		this.ulogovaniKorisnik = ulogovaniKorisnik;
		this.rezervacijaKontroler = rezervacijaKontroler;

		initialize();
	}

	private void initialize() {
		pozadina = new JFrame();
		pozadina.setBounds(100, 100, 750, 620);
		pozadina.setTitle("Meni bibliotekara");
		pozadina.setVisible(true);
		pozadina.setResizable(false);
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));
		pozadina.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pozadina.setVisible(false);
				new PocetniProzor();
			}
		});

		JPanel panel = new JPanel();
		pozadina.getContentPane().add(panel, BorderLayout.CENTER);
		pozadina.setBackground(Color.WHITE);
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);

		JLabel naslov = new JLabel("Meni bibliotekara");
		naslov.setForeground(new Color(102, 0, 0));
		naslov.setFont(new Font("Times New Roman", Font.BOLD, 30));
		naslov.setBounds(46, 25, 236, 41);
		panel.add(naslov);

		JPanel pozadinskiPanel = new JPanel();
		pozadinskiPanel.setBounds(20, 44, 700, 508);
		pozadinskiPanel.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel);
		pozadinskiPanel.setLayout(null);

		JLabel lblOdrzavanjeKnjiga = new JLabel("");
		lblOdrzavanjeKnjiga.setIcon(new ImageIcon("slike\\knjigaISBN.png"));
		lblOdrzavanjeKnjiga.setBounds(489, 363, 65, 56);
		pozadinskiPanel.add(lblOdrzavanjeKnjiga);

		JLabel lblIzvestaj = new JLabel("");
		lblIzvestaj.setBounds(489, 212, 65, 56);
		lblIzvestaj.setIcon(new ImageIcon("slike\\izvestaj.png"));
		pozadinskiPanel.add(lblIzvestaj);

		JLabel lblRegistracija = new JLabel("");
		lblRegistracija.setIcon(new ImageIcon("slike\\dodajClana.png"));
		lblRegistracija.setBounds(265, 212, 65, 56);
		pozadinskiPanel.add(lblRegistracija);

		JLabel lblOdjava = new JLabel("");
		lblOdjava.setIcon(new ImageIcon("slike\\odjava.png"));
		lblOdjava.setBounds(265, 363, 65, 56);
		pozadinskiPanel.add(lblOdjava);

		JLabel lblPozajmica = new JLabel("");
		lblPozajmica.setBounds(489, 70, 65, 56);
		lblPozajmica.setIcon(new ImageIcon("slike\\evidencijaZaduzenja.png"));
		pozadinskiPanel.add(lblPozajmica);

		JLabel lblPromenaLozinke = new JLabel("");
		lblPromenaLozinke.setBounds(42, 363, 65, 56);
		lblPromenaLozinke.setIcon(new ImageIcon("slike\\sifra.png"));
		pozadinskiPanel.add(lblPromenaLozinke);

		JLabel lblOdobravanje = new JLabel("");
		lblOdobravanje.setBounds(265, 70, 65, 56);
		pozadinskiPanel.add(lblOdobravanje);
		lblOdobravanje.setIcon(new ImageIcon("slike\\odobrenjeRezervacije.png"));

		JLabel lblClanarina = new JLabel("");
		lblClanarina.setBounds(42, 216, 65, 56);
		lblClanarina.setIcon(new ImageIcon("slike\\novac.png"));
		pozadinskiPanel.add(lblClanarina);
		
		JLabel lblPretraga = new JLabel("");
		lblPretraga.setIcon(new ImageIcon("slike\\pretraga.png"));
		lblPretraga.setBounds(42, 70, 65, 56);
		pozadinskiPanel.add(lblPretraga);

		JButton dugmeOdobravanjeRezervacije = new JButton("<html>Odobravanje <br/>rezervacije <html>");
		dugmeOdobravanjeRezervacije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pozadina.setVisible(false);
				new ProzorOdobrenjaRezervacije(bibliotekaKontroler, pozadina);
			}
		});
		dugmeOdobravanjeRezervacije.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		dugmeOdobravanjeRezervacije.setHorizontalAlignment(SwingConstants.RIGHT);
		dugmeOdobravanjeRezervacije.setVerticalAlignment(SwingConstants.BOTTOM);
		dugmeOdobravanjeRezervacije.setBackground(new Color(102, 0, 0));
		dugmeOdobravanjeRezervacije.setForeground(new Color(0xf4ece2));
		dugmeOdobravanjeRezervacije.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmeOdobravanjeRezervacije.setUI(new StilDugmeta());
		dugmeOdobravanjeRezervacije.setBounds(252, 55, 194, 120);
		pozadinskiPanel.add(dugmeOdobravanjeRezervacije);

		JButton dugmePretraga = new JButton("<html>Pretraga <br/> knjiga<html>");
		dugmePretraga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pozadina.setVisible(false);
				new ProzorPretragaKnjiga(knjigaKontroler, autorRepo, knjigaRepo, pozadina, ulogovaniKorisnik,
						rezervacijaKontroler);
			}
		});
		dugmePretraga.setIcon(null);
		dugmePretraga.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		dugmePretraga.setHorizontalAlignment(SwingConstants.RIGHT);
		dugmePretraga.setVerticalAlignment(SwingConstants.BOTTOM);
		dugmePretraga.setBackground(new Color(102, 0, 0));
		dugmePretraga.setForeground(new Color(0xf4ece2));
		dugmePretraga.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmePretraga.setUI(new StilDugmeta());
		dugmePretraga.setBounds(27, 55, 194, 120);
		pozadinskiPanel.add(dugmePretraga);

		JButton dugmeEvidencijaPozajmice = new JButton("<html>Evidencija <br/>pozajmice<html>");
		dugmeEvidencijaPozajmice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pozadina.setVisible(false);
				new ProzorEvidencijePozajmica(bibliotekaKontroler, pozadina);
			}
		});
		dugmeEvidencijaPozajmice.setHorizontalAlignment(SwingConstants.RIGHT);
		dugmeEvidencijaPozajmice.setVerticalAlignment(SwingConstants.BOTTOM);
		dugmeEvidencijaPozajmice.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		dugmeEvidencijaPozajmice.setBackground(new Color(102, 0, 0));
		dugmeEvidencijaPozajmice.setForeground(new Color(0xf4ece2));
		dugmeEvidencijaPozajmice.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmeEvidencijaPozajmice.setUI(new StilDugmeta());
		dugmeEvidencijaPozajmice.setBounds(478, 55, 194, 120);
		pozadinskiPanel.add(dugmeEvidencijaPozajmice);

		JButton dugmePromenaLozinke = new JButton("<html>Promena <br/>lozinke<html>");
		dugmePromenaLozinke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProzorPromeneLozinke(bibliotekaKontroler, ulogovaniKorisnik, pozadina);
			}
		});
		dugmePromenaLozinke.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		dugmePromenaLozinke.setVerticalAlignment(SwingConstants.BOTTOM);
		dugmePromenaLozinke.setHorizontalAlignment(SwingConstants.RIGHT);
		dugmePromenaLozinke.setBackground(new Color(102, 0, 0));
		dugmePromenaLozinke.setForeground(new Color(0xf4ece2));
		dugmePromenaLozinke.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmePromenaLozinke.setUI(new StilDugmeta());
		dugmePromenaLozinke.setBounds(27, 353, 194, 120);
		pozadinskiPanel.add(dugmePromenaLozinke);

		JButton dugmeOdjava = new JButton("Odjava");
		dugmeOdjava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pozadina.setVisible(false);
				new PocetniProzor();
			}
		});
		dugmeOdjava.setVerticalAlignment(SwingConstants.BOTTOM);
		dugmeOdjava.setHorizontalAlignment(SwingConstants.RIGHT);
		dugmeOdjava.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		dugmeOdjava.setBackground(new Color(102, 0, 0));
		dugmeOdjava.setForeground(new Color(0xf4ece2));
		dugmeOdjava.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmeOdjava.setUI(new StilDugmeta());
		dugmeOdjava.setBounds(252, 353, 194, 120);
		pozadinskiPanel.add(dugmeOdjava);

		JButton dugmeProduzavanjeClanarine = new JButton("<html>Produžavanje <br/>članarine<html>");
		dugmeProduzavanjeClanarine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pozadina.setVisible(false);
				new ProzorProduzenjaClanarine(bibliotekaKontroler, pozadina);
			}
		});
		dugmeProduzavanjeClanarine.setVerticalAlignment(SwingConstants.BOTTOM);
		dugmeProduzavanjeClanarine.setHorizontalAlignment(SwingConstants.RIGHT);
		dugmeProduzavanjeClanarine.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		dugmeProduzavanjeClanarine.setBackground(new Color(102, 0, 0));
		dugmeProduzavanjeClanarine.setForeground(new Color(0xf4ece2));
		dugmeProduzavanjeClanarine.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmeProduzavanjeClanarine.setUI(new StilDugmeta());
		dugmeProduzavanjeClanarine.setBounds(27, 203, 194, 120);
		pozadinskiPanel.add(dugmeProduzavanjeClanarine);

		JButton dugmeRegistracijaClanova = new JButton("<html>Registracija<br/>člana<html>");
		dugmeRegistracijaClanova.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pozadina.setVisible(false);
				new ProzorRegistracije(bibliotekaKontroler, pozadina);
			}
		});
		dugmeRegistracijaClanova.setVerticalAlignment(SwingConstants.BOTTOM);
		dugmeRegistracijaClanova.setBounds(252, 203, 194, 120);
		dugmeRegistracijaClanova.setHorizontalAlignment(SwingConstants.RIGHT);
		dugmeRegistracijaClanova.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		dugmeRegistracijaClanova.setBackground(new Color(102, 0, 0));
		dugmeRegistracijaClanova.setForeground(new Color(0xf4ece2));
		dugmeRegistracijaClanova.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmeRegistracijaClanova.setUI(new StilDugmeta());
		pozadinskiPanel.add(dugmeRegistracijaClanova);

		JButton dugmePrikazIzvestaja = new JButton("<html>Prikaz izveštaja <br/>o čitanosti<html>");
		dugmePrikazIzvestaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pozadina.setVisible(false);
				new ProzorIzvestaja(bibliotekaKontroler, pozadina);
			}
		});
		dugmePrikazIzvestaja.setVerticalAlignment(SwingConstants.BOTTOM);
		dugmePrikazIzvestaja.setBackground(new Color(102, 0, 0));
		dugmePrikazIzvestaja.setForeground(new Color(0xf4ece2));
		dugmePrikazIzvestaja.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmePrikazIzvestaja.setUI(new StilDugmeta());
		dugmePrikazIzvestaja.setHorizontalAlignment(SwingConstants.RIGHT);
		dugmePrikazIzvestaja.setFont(new Font("Times New Roman", Font.ITALIC, 20));

		dugmePrikazIzvestaja.setBounds(478, 203, 194, 120);

		pozadinskiPanel.add(dugmePrikazIzvestaja);

		JButton dugmeOdrzavanjeKnjiga = new JButton("<html>Održavanje <br/>podataka o <br/>knjigama<html>");
		dugmeOdrzavanjeKnjiga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pozadina.setVisible(false);
				new ProzorOdrzavanjePodatakaOKnjigama(knjigaKontroler, pozadina, bibliotekaKontroler);
			}
		});
		dugmeOdrzavanjeKnjiga.setIcon(null);
		dugmeOdrzavanjeKnjiga.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		dugmeOdrzavanjeKnjiga.setHorizontalAlignment(SwingConstants.RIGHT);
		dugmeOdrzavanjeKnjiga.setVerticalAlignment(SwingConstants.BOTTOM);
		dugmeOdrzavanjeKnjiga.setBackground(new Color(102, 0, 0));
		dugmeOdrzavanjeKnjiga.setForeground(new Color(0xf4ece2));
		dugmeOdrzavanjeKnjiga.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmeOdrzavanjeKnjiga.setUI(new StilDugmeta());
		dugmeOdrzavanjeKnjiga.setBounds(478, 353, 194, 120);
		pozadinskiPanel.add(dugmeOdrzavanjeKnjiga);

		JButton dugmeOdrzavanjeBibliotekara = new JButton("<html>Održavanje <br/>podataka o <br/>bibliotekarima<html>");
		dugmeOdrzavanjeBibliotekara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pozadina.setVisible(false);
				new ProzorOdrzavanjaPodatakaOBibliotekarima(pozadina, bibliotekaKontroler);
			}
		});

		lblOdrzavanjeBibliotekara = new JLabel("");
		lblOdrzavanjeBibliotekara.setIcon(new ImageIcon("slike\\bibliotekar.png"));
		lblOdrzavanjeBibliotekara.setBounds(42, 509, 65, 56);
		pozadinskiPanel.add(lblOdrzavanjeBibliotekara);
		dugmeOdrzavanjeBibliotekara.setIcon(null);
		dugmeOdrzavanjeBibliotekara.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		dugmeOdrzavanjeBibliotekara.setHorizontalAlignment(SwingConstants.RIGHT);
		dugmeOdrzavanjeBibliotekara.setVerticalAlignment(SwingConstants.BOTTOM);
		dugmeOdrzavanjeBibliotekara.setBackground(new Color(102, 0, 0));
		dugmeOdrzavanjeBibliotekara.setForeground(new Color(0xf4ece2));
		dugmeOdrzavanjeBibliotekara.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmeOdrzavanjeBibliotekara.setUI(new StilDugmeta());
		dugmeOdrzavanjeBibliotekara.setBounds(27, 500, 194, 120);
		pozadinskiPanel.add(dugmeOdrzavanjeBibliotekara);
		pozadina.setBounds(100, 100, 750, 710);
		pozadinskiPanel.setBounds(20, 44, 700, 627);

		try {
			bibliotekaKontroler.tipPrijavljenogZaposlenog(this.ulogovaniKorisnik);
			dugmeOdrzavanjeKnjiga.setVisible(false);
			lblOdrzavanjeKnjiga.setVisible(false);
			dugmeOdrzavanjeBibliotekara.setVisible(false);
			lblOdrzavanjeBibliotekara.setVisible(false);
			pozadinskiPanel.setBounds(20, 44, 700, 527);
			pozadina.setBounds(100, 100, 750, 590);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (PrijavljenObradjivacException e1) {
			dugmeOdrzavanjeKnjiga.setVisible(true);
			lblOdrzavanjeKnjiga.setVisible(true);
			dugmeOdrzavanjeBibliotekara.setVisible(false);
			lblOdrzavanjeBibliotekara.setVisible(false);
			pozadinskiPanel.setBounds(20, 44, 700, 527);
			pozadina.setBounds(100, 100, 750, 590);

		} catch (PrijavljenAdministratorException e1) {
			dugmeOdrzavanjeKnjiga.setVisible(true);
			lblOdrzavanjeKnjiga.setVisible(true);
			dugmeOdrzavanjeBibliotekara.setVisible(true);
			lblOdrzavanjeBibliotekara.setVisible(true);
		}

	}
}
