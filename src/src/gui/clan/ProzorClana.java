package gui.clan;

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

import dto.PozajmljeneKnjigeDTO;
import gui.pomocniElementi.StilDugmeta;
import gui.zajednicki.PocetniProzor;
import gui.zajednicki.ProzorPretragaKnjiga;
import gui.zajednicki.ProzorPromeneLozinke;
import kontroler.BibliotekaKontroler;
import kontroler.ClanKontroler;
import kontroler.KnjigaKontroler;
import kontroler.RezervacijaKontroler;
import observer.Observer;
import observer.RepositoryUpdateEvent;
import repos.AutorRepo;
import repos.KnjigaRepo;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ProzorClana implements Observer {
	private JFrame pozadina;
	private BibliotekaKontroler bibliotekaKontroler;
	private KnjigaKontroler knjigaKontroler;
	private AutorRepo autorRepo;
	private KnjigaRepo knjigaRepo;
	private String ulogovaniKorisnik;
	private ClanKontroler clanKontroler;
	private RezervacijaKontroler rezervacijaKontroler;
	private PozajmljeneKnjigeDTO pozajmljeneKnjigeDTO;
	private JFrame glavniProzor;

	public ProzorClana(ClanKontroler clanKontroler, BibliotekaKontroler bibliotekaKontroler, AutorRepo autorRepo,
			KnjigaRepo knjigaRepo, KnjigaKontroler knjigaKontroler, String ulogovaniKorisnik,
			RezervacijaKontroler rezervacijaKontroler, JFrame glavniProzor) {
		this.bibliotekaKontroler = bibliotekaKontroler;
		this.knjigaKontroler = knjigaKontroler;
		this.autorRepo = autorRepo;
		this.knjigaRepo = knjigaRepo;
		this.clanKontroler = clanKontroler;
		this.ulogovaniKorisnik = ulogovaniKorisnik;
		this.rezervacijaKontroler = rezervacijaKontroler;
		this.glavniProzor = glavniProzor;

		this.knjigaRepo.addObserver(this);
		initialize();
	}

	private void initialize() {
		pozadina = new JFrame();
		pozadina.setBounds(100, 100, 750, 440);
		pozadina.setTitle("Meni clana");
		pozadina.setVisible(true);
		pozadina.setResizable(false);
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));
		pozadina.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pozadina.dispose();
				glavniProzor.setVisible(true);
			}
		});

		JPanel panel = new JPanel();
		pozadina.getContentPane().add(panel, BorderLayout.CENTER);
		pozadina.setBackground(Color.WHITE);
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);

		JLabel naslov = new JLabel("Meni člana");
		naslov.setForeground(new Color(102, 0, 0));
		naslov.setFont(new Font("Times New Roman", Font.BOLD, 30));
		naslov.setBounds(46, 25, 164, 41);
		panel.add(naslov);

		JPanel pozadinskiPanel = new JPanel();
		pozadinskiPanel.setBounds(20, 44, 700, 343);
		pozadinskiPanel.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel);
		pozadinskiPanel.setLayout(null);

		JLabel lblClanarina = new JLabel("");
		lblClanarina.setBounds(489, 70, 65, 56);
		lblClanarina.setIcon(new ImageIcon("slike\\novac.png"));
		pozadinskiPanel.add(lblClanarina);

		JLabel lblOdjava = new JLabel("");
		lblOdjava.setBounds(265, 216, 65, 56);
		lblOdjava.setIcon(new ImageIcon("slike\\odjava.png"));
		pozadinskiPanel.add(lblOdjava);

		JLabel lblZaduzenje = new JLabel("");
		lblZaduzenje.setBounds(265, 70, 65, 56);
		pozadinskiPanel.add(lblZaduzenje);
		lblZaduzenje.setIcon(new ImageIcon("slike\\evidencijaZaduzenja.png"));

		JLabel lblLozinka = new JLabel("");
		lblLozinka.setBounds(42, 216, 65, 56);
		lblLozinka.setIcon(new ImageIcon("slike\\sifra.png"));
		pozadinskiPanel.add(lblLozinka);

		JButton buttonIstorijaZaduzenja = new JButton("<html>Istorija <br/>zaduzenja <html>");
		buttonIstorijaZaduzenja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					knjigaKontroler.nadjiKnjigeClana(ulogovaniKorisnik);
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				pozadina.setVisible(false);
				new ProzorIstorijeZaduzenja(clanKontroler, knjigaKontroler, ulogovaniKorisnik, pozajmljeneKnjigeDTO,
						pozadina);
			}
		});
		buttonIstorijaZaduzenja.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		buttonIstorijaZaduzenja.setHorizontalAlignment(SwingConstants.RIGHT);
		buttonIstorijaZaduzenja.setVerticalAlignment(SwingConstants.BOTTOM);
		buttonIstorijaZaduzenja.setBackground(new Color(102, 0, 0));
		buttonIstorijaZaduzenja.setForeground(new Color(0xf4ece2));
		buttonIstorijaZaduzenja.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		buttonIstorijaZaduzenja.setUI(new StilDugmeta());
		buttonIstorijaZaduzenja.setBounds(252, 55, 194, 120);
		pozadinskiPanel.add(buttonIstorijaZaduzenja);

		JLabel lblPretraga = new JLabel("");
		lblPretraga.setIcon(new ImageIcon("slike\\pretraga.png"));
		lblPretraga.setBounds(42, 70, 65, 56);
		pozadinskiPanel.add(lblPretraga);

		JButton buttonPretragaKnjiga = new JButton("<html>Pretraga <br/> knjiga<html>");
		buttonPretragaKnjiga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pozadina.setVisible(false);
				new ProzorPretragaKnjiga(knjigaKontroler, autorRepo, knjigaRepo, pozadina, ulogovaniKorisnik,
						clanKontroler, rezervacijaKontroler);
			}
		});
		buttonPretragaKnjiga.setIcon(null);
		buttonPretragaKnjiga.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		buttonPretragaKnjiga.setHorizontalAlignment(SwingConstants.RIGHT);
		buttonPretragaKnjiga.setVerticalAlignment(SwingConstants.BOTTOM);
		buttonPretragaKnjiga.setBackground(new Color(102, 0, 0));
		buttonPretragaKnjiga.setForeground(new Color(0xf4ece2));
		buttonPretragaKnjiga.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		buttonPretragaKnjiga.setUI(new StilDugmeta());
		buttonPretragaKnjiga.setBounds(27, 55, 194, 120);
		pozadinskiPanel.add(buttonPretragaKnjiga);

		JButton buttonProduzenjeClanarine = new JButton("<html>Produzenje <br/>clanarine<html>");
		buttonProduzenjeClanarine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pozadina.setVisible(false);
				new ProzorProduzenjaClanarineClan(clanKontroler, bibliotekaKontroler, ulogovaniKorisnik, pozadina);
			}
		});
		buttonProduzenjeClanarine.setHorizontalAlignment(SwingConstants.RIGHT);
		buttonProduzenjeClanarine.setVerticalAlignment(SwingConstants.BOTTOM);
		buttonProduzenjeClanarine.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		buttonProduzenjeClanarine.setBackground(new Color(102, 0, 0));
		buttonProduzenjeClanarine.setForeground(new Color(0xf4ece2));
		buttonProduzenjeClanarine.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		buttonProduzenjeClanarine.setUI(new StilDugmeta());
		buttonProduzenjeClanarine.setBounds(478, 55, 194, 120);
		pozadinskiPanel.add(buttonProduzenjeClanarine);

		JButton buttonPromeneLozinke = new JButton("<html>Promena <br/>lozinke<html>");
		buttonPromeneLozinke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProzorPromeneLozinke(bibliotekaKontroler, ulogovaniKorisnik, pozadina);
			}
		});
		buttonPromeneLozinke.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		buttonPromeneLozinke.setVerticalAlignment(SwingConstants.BOTTOM);
		buttonPromeneLozinke.setHorizontalAlignment(SwingConstants.RIGHT);
		buttonPromeneLozinke.setBackground(new Color(102, 0, 0));
		buttonPromeneLozinke.setForeground(new Color(0xf4ece2));
		buttonPromeneLozinke.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		buttonPromeneLozinke.setUI(new StilDugmeta());
		buttonPromeneLozinke.setBounds(27, 203, 194, 120);
		pozadinskiPanel.add(buttonPromeneLozinke);

		JButton buttonOdjava = new JButton("Odjava");
		buttonOdjava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pozadina.setVisible(false);
				new PocetniProzor();
			}
		});
		buttonOdjava.setVerticalAlignment(SwingConstants.BOTTOM);
		buttonOdjava.setHorizontalAlignment(SwingConstants.RIGHT);
		buttonOdjava.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		buttonOdjava.setBackground(new Color(102, 0, 0));
		buttonOdjava.setForeground(new Color(0xf4ece2));
		buttonOdjava.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		buttonOdjava.setUI(new StilDugmeta());
		buttonOdjava.setBounds(252, 203, 194, 120);
		pozadinskiPanel.add(buttonOdjava);

	}

	@Override
	public void updatePerformed(RepositoryUpdateEvent event) {
		try {
			pozajmljeneKnjigeDTO = (PozajmljeneKnjigeDTO) event.getAkcija();
		} catch (Exception e) {

		}
	}
}