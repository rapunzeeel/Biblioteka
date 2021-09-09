package gui.zajednicki;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import exception.NepronadjeneVrednostiException;
import exception.NijePronadjenClanExcepiton;
import gui.bibliotekar.ProzorBibliotekara;
import gui.clan.ProzorClana;
import gui.pomocniElementi.StilDugmeta;
import gui.pomocniElementi.StilIvice;
import gui.pomocniElementi.StilPoljaLozinke;
import gui.pomocniElementi.StilTekstPolja;
import kontroler.BibliotekaKontroler;
import kontroler.ClanKontroler;
import kontroler.KnjigaKontroler;
import kontroler.RezervacijaKontroler;
import repos.AutorRepo;
import repos.KnjigaRepo;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProzorPrijave {
	private JFrame pozadina;
	private int prviPutKliknuoEmail = 0;
	private int prviPutKliknuoLozinku = 0;
	private JTextField unosEmail;
	private JTextField unosLozinka;
	private BibliotekaKontroler bibliotekaController;
	private KnjigaKontroler knjigaKontroler;
	private AutorRepo autorRepo;
	private KnjigaRepo knjigaRepo;
	private JLabel lblGreska;
	private String ulogovaniKorisnik;
	private ClanKontroler clanKontroler;
	private RezervacijaKontroler rezervacijaKontroler;

	public ProzorPrijave(ClanKontroler clanKontroler, BibliotekaKontroler bibliotekaController, AutorRepo autorRepo,
			KnjigaRepo knjigaRepo, KnjigaKontroler knjigaKontroler, String ulogovaniKorisnik,
			RezervacijaKontroler rezervacijaKontroler) {
		this.bibliotekaController = bibliotekaController;
		this.knjigaKontroler = knjigaKontroler;
		this.autorRepo = autorRepo;
		this.knjigaRepo = knjigaRepo;
		this.ulogovaniKorisnik = ulogovaniKorisnik;
		this.clanKontroler = clanKontroler;
		this.rezervacijaKontroler = rezervacijaKontroler;
		initialize();
	}

	private void initialize() {
		pozadina = new JFrame();
		pozadina.setResizable(false);
		pozadina.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pozadina.setVisible(false);
				new PocetniProzor();
			}
		});
		pozadina.setBounds(100, 100, 750, 540);
		pozadina.setTitle("Prijava");
		pozadina.setVisible(true);
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));

		JPanel panel = new JPanel();
		pozadina.getContentPane().add(panel, BorderLayout.CENTER);
		pozadina.setBackground(Color.WHITE);
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);

		JLabel labelaPrijava = new StilIvice();
		labelaPrijava.setHorizontalAlignment(SwingConstants.LEFT);
		labelaPrijava.setText("Prijava");
		labelaPrijava.setIcon(new ImageIcon("slike\\prijava2.png"));
		labelaPrijava.setBounds(215, 117, 313, 52);
		panel.add(labelaPrijava);
		labelaPrijava.setFont(new Font("Times New Roman", Font.BOLD, 30));
		labelaPrijava.setForeground(new Color(0xf4ece2));
		labelaPrijava.setBackground(new Color(102, 0, 0));

		JPanel pozadinskiPanel = new JPanel();
		pozadinskiPanel.setBounds(155, 145, 439, 345);
		pozadinskiPanel.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel);

		JLabel labelaNaslova = new JLabel("Gradska biblioteka SIMS");
		labelaNaslova.setForeground(new Color(102, 0, 0));
		labelaNaslova.setFont(new Font("Times New Roman", Font.BOLD, 50));
		labelaNaslova.setBounds(57, 21, 550, 85);
		panel.add(labelaNaslova);
		pozadinskiPanel.setLayout(null);

		unosEmail = new StilTekstPolja(200);
		unosEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (prviPutKliknuoEmail == 0) {
					unosEmail.setText("");
					prviPutKliknuoEmail++;
				}
			}
		});

		unosEmail.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		unosEmail.setText("Email");
		unosEmail.setForeground(new Color(102, 0, 0));
		unosEmail.setBounds(116, 96, 256, 47);
		pozadinskiPanel.add(unosEmail);
		unosEmail.setColumns(10);

		JLabel ikonicaEmail = new JLabel("");
		ikonicaEmail.setIcon(new ImageIcon("slike\\email.png"));
		ikonicaEmail.setBounds(55, 97, 46, 45);
		pozadinskiPanel.add(ikonicaEmail);

		unosLozinka = new StilPoljaLozinke(200);
		unosLozinka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prijava();
			}
		});
		((JPasswordField) unosLozinka).setEchoChar((char) 0);
		unosLozinka.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (prviPutKliknuoLozinku == 0) {
					unosLozinka.setText("");
					((JPasswordField) unosLozinka).setEchoChar('\u2022');
					prviPutKliknuoLozinku++;
				}
			}
		});

		unosLozinka.setText("Lozinka");
		unosLozinka.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		unosLozinka.setColumns(10);
		unosLozinka.setForeground(new Color(102, 0, 0));
		unosLozinka.setBounds(116, 172, 256, 47);
		pozadinskiPanel.add(unosLozinka);

		JLabel ikonicaLozinka = new JLabel("");
		ikonicaLozinka.setIcon(new ImageIcon("slike\\sifra.png"));
		ikonicaLozinka.setBounds(55, 170, 46, 45);
		pozadinskiPanel.add(ikonicaLozinka);

		JButton dugmePrijave = new JButton("Prijavi se");
		dugmePrijave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prijava();
			}
		});
		dugmePrijave.setBackground(new Color(102, 0, 0));
		dugmePrijave.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmePrijave.setUI(new StilDugmeta());
		dugmePrijave.setFont(new Font("Times New Roman", Font.BOLD, 30));
		dugmePrijave.setForeground(new Color(0xf4ece2));
		dugmePrijave.setBounds(116, 258, 155, 45);
		pozadinskiPanel.add(dugmePrijave);

		lblGreska = new JLabel(":( Neispravni kredencijali. Pokušajte ponovo!");
		lblGreska.setForeground(new Color(102, 0, 0));
		lblGreska.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		lblGreska.setBounds(116, 314, 256, 34);
		lblGreska.setVisible(false);
		pozadinskiPanel.add(lblGreska);

	}

	private void prijava() {
		ulogovaniKorisnik = unosEmail.getText();
		try {
			bibliotekaController.proveriKredencijale(unosEmail.getText(), unosLozinka.getText());
			bibliotekaController.nadjiClana(unosEmail.getText());
			pozadina.setVisible(false);
			new ProzorClana(clanKontroler, bibliotekaController, autorRepo, knjigaRepo, knjigaKontroler,
					ulogovaniKorisnik, rezervacijaKontroler, pozadina);
		} catch (NijePronadjenClanExcepiton e1) {
			pozadina.setVisible(false);
			new ProzorBibliotekara(bibliotekaController, autorRepo, knjigaRepo, knjigaKontroler, ulogovaniKorisnik,
					rezervacijaKontroler);
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(pozadina, "Doslo je do greske u sistemu!", "Greska",
					JOptionPane.ERROR_MESSAGE);
		} catch (NepronadjeneVrednostiException e3) {
			unosEmail.setBackground(new Color(255, 179, 179));
			unosLozinka.setBackground(new Color(255, 179, 179));
			lblGreska.setVisible(true);
		}
	}
}
