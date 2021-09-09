package gui.bibliotekar;

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

import gui.pomocniElementi.StilDugmeta;
import gui.pomocniElementi.StilIvice;
import kontroler.BibliotekaKontroler;

public class ProzorPotvrdeBrisanja {
	private JFrame pozadina;
	private BibliotekaKontroler bibliotekaKontroler;
	private String poruka;
	private String kriterijum;
	private Boolean potvrdaBrisanjaKnjige;

	public ProzorPotvrdeBrisanja(BibliotekaKontroler bibliotekaKontroler, String kriterijum, String poruka, boolean potvrdaBrisanjaKnjige) {
		this.bibliotekaKontroler = bibliotekaKontroler;
		this.kriterijum = kriterijum;
		this.poruka = poruka;
		this.potvrdaBrisanjaKnjige = potvrdaBrisanjaKnjige;
		initialize();
	}

	private void initialize() {
		pozadina = new JFrame();
		pozadina.setBounds(100, 100, 450, 300);
		pozadina.setSize(431, 216);
		pozadina.setTitle("Potvrda brisanja");
		pozadina.setVisible(true);
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));
		pozadina.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pozadina.setVisible(false);
				pozadina.dispose();
			}
		});

		JPanel panel = new JPanel();
		pozadina.getContentPane().add(panel, BorderLayout.CENTER);
		pozadina.setBackground(Color.WHITE);
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);

		JPanel pozadinskiPanel1 = new JPanel();
		pozadinskiPanel1.setBounds(10, 11, 396, 155);
		pozadinskiPanel1.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel1);
		pozadinskiPanel1.setLayout(null);

		JButton dugmePotvrde = new JButton("DA");
		dugmePotvrde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(potvrdaBrisanjaKnjige) {
					try {
						bibliotekaKontroler.obrisiKnjigu(kriterijum);
						bibliotekaKontroler.izlistajKnjige();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else {
					try {
						bibliotekaKontroler.obrisiBibliotekara(kriterijum);
						bibliotekaKontroler.izlistajBibliotekare();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				pozadina.dispose();
			}
		});
		dugmePotvrde.setLocation(66, 84);
		dugmePotvrde.setSize(127, 42);
		dugmePotvrde.setBackground(new Color(102, 0, 0));
		dugmePotvrde.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmePotvrde.setUI(new StilDugmeta());
		dugmePotvrde.setFont(new Font("Times New Roman", Font.BOLD, 25));
		dugmePotvrde.setForeground(new Color(0xf4ece2));
		dugmePotvrde.setIcon(new ImageIcon("slike\\odobrenjeRezervacije.png"));
		pozadinskiPanel1.add(dugmePotvrde);
		dugmePotvrde.setActionCommand("OK");

		JButton dugmeOdustajanja = new JButton("NE");
		dugmeOdustajanja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pozadina.setVisible(false);
				pozadina.dispose();
			}
		});
		dugmeOdustajanja.setLocation(212, 84);
		dugmeOdustajanja.setSize(127, 42);
		dugmeOdustajanja.setBackground(new Color(102, 0, 0));
		dugmeOdustajanja.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmeOdustajanja.setUI(new StilDugmeta());
		dugmeOdustajanja.setFont(new Font("Times New Roman", Font.BOLD, 25));
		dugmeOdustajanja.setForeground(new Color(0xf4ece2));
		dugmeOdustajanja.setIcon(new ImageIcon("slike\\obrisi.png"));
		pozadinskiPanel1.add(dugmeOdustajanja);
		dugmeOdustajanja.setActionCommand("Cancel");

		JLabel labelaOdrzavanjeKnjiga = new StilIvice();
		labelaOdrzavanjeKnjiga.setBounds(36, 11, 328, 52);
		pozadinskiPanel1.add(labelaOdrzavanjeKnjiga);
		labelaOdrzavanjeKnjiga.setHorizontalAlignment(SwingConstants.LEFT);
		labelaOdrzavanjeKnjiga.setText(poruka);
		labelaOdrzavanjeKnjiga.setIcon(new ImageIcon("slike\\brisanje.png"));
		labelaOdrzavanjeKnjiga.setFont(new Font("Times New Roman", Font.BOLD, 20));
		labelaOdrzavanjeKnjiga.setForeground(new Color(0xf4ece2));
		labelaOdrzavanjeKnjiga.setBackground(new Color(102, 0, 0));
	}
}