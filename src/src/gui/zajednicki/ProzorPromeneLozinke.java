package gui.zajednicki;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import exception.NeispravnaLozinkaException;
import gui.pomocniElementi.StilDugmeta;
import gui.pomocniElementi.StilIvice;
import gui.pomocniElementi.StilPoljaLozinke;
import kontroler.BibliotekaKontroler;

import javax.swing.JPasswordField;

public class ProzorPromeneLozinke {
	private JFrame pozadina;
	private BibliotekaKontroler bibliotekaKontroler;
	private JPasswordField staraLozinka;
	private JPasswordField novaLozinka;

	private String ulogovaniKorisnik;
	private JLabel lblObavestenje;
	private JFrame glavniProzor;

	public ProzorPromeneLozinke(BibliotekaKontroler bibliotekaKontroler, String ulogovaniKorisnik, JFrame glavniProzor) {
		this.bibliotekaKontroler = bibliotekaKontroler;
		this.ulogovaniKorisnik = ulogovaniKorisnik;
		this.glavniProzor = glavniProzor;
		initialize();
	}

	private void initialize() {
		pozadina = new JFrame();
		pozadina.setResizable(false);
		pozadina.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pozadina.dispose();
				glavniProzor.setVisible(true);
			}
		});
		pozadina.setBounds(100, 100, 350, 410);
		pozadina.setTitle("Promena lozinke");
		pozadina.setVisible(true);
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));

		JPanel panel = new JPanel();
		pozadina.getContentPane().add(panel, BorderLayout.CENTER);
		pozadina.setBackground(Color.WHITE);
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);

		JLabel labelaPrijava = new StilIvice();
		labelaPrijava.setHorizontalAlignment(SwingConstants.LEFT);
		labelaPrijava.setText("Promena lozinke");
		labelaPrijava.setIcon(new ImageIcon("slike\\promenaLozinke.png"));
		labelaPrijava.setBounds(43, 38, 250, 52);
		panel.add(labelaPrijava);
		labelaPrijava.setFont(new Font("Times New Roman", Font.BOLD, 25));
		labelaPrijava.setForeground(new Color(0xf4ece2));
		labelaPrijava.setBackground(new Color(102, 0, 0));

		JPanel pozadinskiPanel = new JPanel();
		pozadinskiPanel.setBounds(32, 61, 273, 298);
		pozadinskiPanel.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel);
		pozadinskiPanel.setLayout(null);

		JLabel lblStaraLozinka = new JLabel("Stara lozinka");
		lblStaraLozinka.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblStaraLozinka.setBounds(10, 50, 132, 24);
		pozadinskiPanel.add(lblStaraLozinka);
		lblStaraLozinka.setForeground(new Color(102, 0, 0));

		JLabel lblNovaLozinka = new JLabel("Nova lozinka");
		lblNovaLozinka.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNovaLozinka.setBounds(10, 147, 114, 14);
		pozadinskiPanel.add(lblNovaLozinka);
		lblNovaLozinka.setForeground(new Color(102, 0, 0));

		staraLozinka = new StilPoljaLozinke(300);
		staraLozinka.setFont(new Font("Times New Roman", Font.BOLD, 20));
		staraLozinka.setBounds(10, 75, 253, 42);
		staraLozinka.setForeground(new Color(102, 0, 0));

		pozadinskiPanel.add(staraLozinka);

		novaLozinka = new StilPoljaLozinke(300);
		novaLozinka.setBounds(10, 167, 253, 42);
		novaLozinka.setFont(new Font("Times New Roman", Font.BOLD, 20));
		novaLozinka.setForeground(new Color(102, 0, 0));
		pozadinskiPanel.add(novaLozinka);

		JButton dugmeSacuvaj = new JButton("Sačuvaj");
		dugmeSacuvaj.setBounds(58, 220, 205, 43);
		pozadinskiPanel.add(dugmeSacuvaj);
		dugmeSacuvaj.setFont(new Font("Times New Roman", Font.BOLD, 30));
		dugmeSacuvaj.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmeSacuvaj.setUI(new StilDugmeta());
		dugmeSacuvaj.setBackground(new Color(102, 0, 0));
		dugmeSacuvaj.setForeground(new Color(0xf4ece2));
		dugmeSacuvaj.setIcon(new ImageIcon("slike\\sacuvaj.png"));
		dugmeSacuvaj.setHorizontalAlignment(SwingConstants.LEFT);

		lblObavestenje = new JLabel("");
		lblObavestenje.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblObavestenje.setBounds(58, 274, 205, 14);
		lblObavestenje.setVisible(false);

		pozadinskiPanel.add(lblObavestenje);
		dugmeSacuvaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bibliotekaKontroler.promeniLozinku(ulogovaniKorisnik, staraLozinka.getText(),
							novaLozinka.getText());
					lblObavestenje.setText("Uspešno promenjena lozinka.");
					lblObavestenje.setForeground(new Color(0, 102, 0));
					lblObavestenje.setVisible(true);

				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (NeispravnaLozinkaException e1) {
					lblObavestenje.setText("Pogrešno uneta stara lozinka.");
					lblObavestenje.setForeground(new Color(102, 0, 0));
					lblObavestenje.setVisible(true);
				}
			}
		});

		JLabel ikonicaLozinka = new JLabel("");
		ikonicaLozinka.setBounds(143, 38, 46, 45);
		panel.add(ikonicaLozinka);
		ikonicaLozinka.setIcon(new ImageIcon("slike\\sifra.png"));

	}
}
