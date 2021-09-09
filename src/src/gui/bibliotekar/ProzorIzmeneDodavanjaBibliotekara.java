package gui.bibliotekar;

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
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import dto.RegistracijaBibliotekaraDTO;
import exception.NeispravanJmbgException;
import exception.NepostojeciPostanskiBrojException;
import exception.PostojeciEmailException;
import exception.PraznoAdresaException;
import exception.PraznoImeException;
import exception.PraznoPrezimeException;
import gui.pomocniElementi.StilDugmeta;
import gui.pomocniElementi.StilIvice;
import gui.pomocniElementi.StilTekstPolja;
import kontroler.BibliotekaKontroler;
import model.Bibliotekar;
import model.Filijala;
import model.TipZaposlenog;
import observer.Observer;
import observer.RepositoryUpdateEvent;

public class ProzorIzmeneDodavanjaBibliotekara implements Observer {
	private JFrame pozadina;
	private int prviPutKliknuoEmail = 0;
	private int prviPutKliknuoPrezime = 0;
	private int prviPutKliknuoIme = 0;
	private int prviPutKliknuoJMBG = 0;
	private int prviPutKliknuoAdresa = 0;
	private int prviPutKliknuPostanskiBroj = 0;
	private JTextField unosEmail;
	private JTextField unosIme;
	private JTextField unosPrezime;
	private JTextField unosJMBG;
	private JTextField unosPostanskiBroj;
	private JTextField unosAdresa;

	private BibliotekaKontroler bibliotekaKontroler;
	private JLabel lblGreska;
	private JLabel lblUspesno;
	private JDateChooser datumRodjenja;
	private JComboBox comboBoxTip;
	private JFrame glavniProzor;
	private Bibliotekar bibliotekar;
	private JComboBox comboBoxFilijala;

	public ProzorIzmeneDodavanjaBibliotekara(JFrame glavniProzor, Bibliotekar bibliotekar,
			BibliotekaKontroler bibliotekaKontroler) {
		this.glavniProzor = glavniProzor;
		this.bibliotekar = bibliotekar;
		this.bibliotekaKontroler = bibliotekaKontroler;

		bibliotekaKontroler.getFilijalaRepo().addObserver(this);
		initialize();
	}

	private void initialize() {
		pozadina = new JFrame();
		pozadina.setResizable(false);
		pozadina.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pozadina.setVisible(false);
				pozadina.dispose();
				glavniProzor.setVisible(true);
			}
		});
		pozadina.setBounds(80, 70, 520, 726);
		pozadina.setVisible(true);
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));

		JPanel panel = new JPanel();
		pozadina.getContentPane().add(panel, BorderLayout.CENTER);
		pozadina.setBackground(Color.WHITE);
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);

		JLabel labelaPrijava = new StilIvice();
		labelaPrijava.setHorizontalAlignment(SwingConstants.LEFT);
		labelaPrijava.setIcon(new ImageIcon("slike\\bibliotekar.png"));
		labelaPrijava.setBounds(70, 11, 365, 52);
		panel.add(labelaPrijava);
		labelaPrijava.setFont(new Font("Times New Roman", Font.BOLD, 30));
		labelaPrijava.setForeground(new Color(0xf4ece2));
		labelaPrijava.setBackground(new Color(102, 0, 0));

		JPanel pozadinskiPanel = new JPanel();
		pozadinskiPanel.setBounds(33, 34, 440, 650);
		pozadinskiPanel.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel);
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
		unosEmail.setForeground(new Color(102, 0, 0));
		unosEmail.setBounds(95, 61, 277, 47);
		pozadinskiPanel.add(unosEmail);
		unosEmail.setColumns(10);

		JLabel ikonicaEmail = new JLabel("");
		ikonicaEmail.setIcon(new ImageIcon("slike\\email.png"));
		ikonicaEmail.setBounds(40, 61, 46, 45);
		pozadinskiPanel.add(ikonicaEmail);

		unosIme = new StilTekstPolja(200);
		unosIme.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (prviPutKliknuoIme == 0) {
					unosIme.setText("");
					prviPutKliknuoIme++;
				}
			}
		});
		unosIme.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		unosIme.setColumns(10);
		unosIme.setForeground(new Color(102, 0, 0));
		unosIme.setBounds(95, 130, 277, 47);
		pozadinskiPanel.add(unosIme);

		unosPrezime = new StilTekstPolja(200);
		unosPrezime.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (prviPutKliknuoPrezime == 0) {
					unosPrezime.setText("");
					prviPutKliknuoPrezime++;
				}
			}
		});

		unosPrezime.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		unosPrezime.setColumns(10);
		unosPrezime.setBounds(95, 198, 277, 47);
		unosPrezime.setForeground(new Color(102, 0, 0));
		pozadinskiPanel.add(unosPrezime);

		unosJMBG = new StilTekstPolja(200);
		unosJMBG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (prviPutKliknuoJMBG == 0) {
					unosJMBG.setText("");
					prviPutKliknuoJMBG++;
				}
			}
		});

		unosJMBG.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		unosJMBG.setColumns(10);
		unosJMBG.setBounds(95, 268, 277, 47);
		unosJMBG.setForeground(new Color(102, 0, 0));
		pozadinskiPanel.add(unosJMBG);

		unosAdresa = new StilTekstPolja(200);
		unosAdresa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (prviPutKliknuoAdresa == 0) {
					unosAdresa.setText("");
					prviPutKliknuoAdresa++;
				}
			}
		});

		unosAdresa.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		unosAdresa.setColumns(10);
		unosAdresa.setBounds(95, 337, 277, 47);
		unosAdresa.setForeground(new Color(102, 0, 0));
		pozadinskiPanel.add(unosAdresa);

		unosPostanskiBroj = new StilTekstPolja(200);
		unosPostanskiBroj.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (prviPutKliknuPostanskiBroj == 0) {
					unosPostanskiBroj.setText("");
					prviPutKliknuPostanskiBroj++;
				}
			}
		});

		unosPostanskiBroj.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		unosPostanskiBroj.setColumns(10);
		unosPostanskiBroj.setBounds(95, 407, 277, 47);
		unosPostanskiBroj.setForeground(new Color(102, 0, 0));
		pozadinskiPanel.add(unosPostanskiBroj);

		comboBoxTip = new JComboBox();
		comboBoxTip.setBorder(new LineBorder(new Color(102, 0, 0), 1, true));
		comboBoxTip.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		comboBoxTip.setForeground(new Color(102, 0, 0));
		comboBoxTip.setBackground(Color.WHITE);
		comboBoxTip.setModel(new DefaultComboBoxModel(new String[] { "KLASICAN", "OBRADJIVAC", "ADMINISTRATOR" }));
		comboBoxTip.setSelectedIndex(0);
		comboBoxTip.setBounds(95, 466, 149, 37);
		pozadinskiPanel.add(comboBoxTip);

		JLabel ikonicaIme = new JLabel("");
		ikonicaIme.setIcon(new ImageIcon("slike\\ime.png"));
		ikonicaIme.setBounds(40, 132, 46, 45);
		pozadinskiPanel.add(ikonicaIme);

		JLabel ikonicaPrezime = new JLabel("");
		ikonicaPrezime.setIcon(new ImageIcon("slike\\ime.png"));
		ikonicaPrezime.setBounds(40, 198, 46, 45);
		pozadinskiPanel.add(ikonicaPrezime);

		JLabel ikonicajmbg = new JLabel("");
		ikonicajmbg.setIcon(new ImageIcon("slike\\jmbg.png"));
		ikonicajmbg.setBounds(40, 268, 46, 45);
		pozadinskiPanel.add(ikonicajmbg);

		JLabel ikonicaAdresa = new JLabel("");
		ikonicaAdresa.setIcon(new ImageIcon("slike\\adresa.png"));
		ikonicaAdresa.setBounds(40, 337, 46, 45);
		pozadinskiPanel.add(ikonicaAdresa);

		JLabel ikonicaPostanskiBroj = new JLabel("");
		ikonicaPostanskiBroj.setIcon(new ImageIcon("slike\\postanskiBroj.png"));
		ikonicaPostanskiBroj.setBounds(40, 407, 46, 45);
		pozadinskiPanel.add(ikonicaPostanskiBroj);

		JButton dugmeDodaj = new JButton("");
		dugmeDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblGreska.setVisible(false);
				lblUspesno.setVisible(false);

				azuriranjeBibliotekara();

				try {
					bibliotekaKontroler.izlistajBibliotekare();
					lblUspesno.setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		dugmeDodaj.setBackground(new Color(102, 0, 0));
		dugmeDodaj.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmeDodaj.setUI(new StilDugmeta());
		dugmeDodaj.setFont(new Font("Times New Roman", Font.BOLD, 30));
		dugmeDodaj.setForeground(new Color(0xf4ece2));
		dugmeDodaj.setBounds(95, 567, 277, 45);
		pozadinskiPanel.add(dugmeDodaj);

		lblGreska = new JLabel("");
		lblGreska.setHorizontalAlignment(SwingConstants.CENTER);
		lblGreska.setForeground(new Color(102, 0, 0));
		lblGreska.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblGreska.setBounds(40, 611, 379, 34);
		lblGreska.setVisible(false);
		pozadinskiPanel.add(lblGreska);

		datumRodjenja = new JDateChooser();
		datumRodjenja.setForeground(new Color(102, 0, 0));
		datumRodjenja.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		datumRodjenja.setBounds(246, 465, 127, 37);
		pozadinskiPanel.add(datumRodjenja);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -18);
		Date d = new Date(c.getTimeInMillis());
		datumRodjenja.setMaxSelectableDate(d);
		datumRodjenja.setDate(d);

		lblUspesno = new JLabel(":) Uspešno ste registrovali bibliotekara!");
		lblUspesno.setHorizontalAlignment(SwingConstants.CENTER);
		lblUspesno.setForeground(new Color(0, 102, 0));
		lblUspesno.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblUspesno.setBounds(21, 611, 398, 34);
		lblUspesno.setVisible(false);
		pozadinskiPanel.add(lblUspesno);

		comboBoxFilijala = new JComboBox();
		comboBoxFilijala.setForeground(new Color(102, 0, 0));
		comboBoxFilijala.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		comboBoxFilijala.setBorder(new LineBorder(new Color(139, 0, 0), 1, true));
		comboBoxFilijala.setBackground(Color.WHITE);
		comboBoxFilijala.setBounds(95, 519, 277, 37);
		pozadinskiPanel.add(comboBoxFilijala);

		try {
			bibliotekaKontroler.nadjiFilijale();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		if (bibliotekar == null) {
			pozadina.setTitle("Dodavanje bibliotekara");
			labelaPrijava.setText("Dodavanje bibliotekara");
			unosEmail.setText("Email");
			unosIme.setText("Ime");
			unosPrezime.setText("Prezime");
			unosJMBG.setText("JMBG");
			unosAdresa.setText("Adresa");
			unosPostanskiBroj.setText("Poštanski broj");
			dugmeDodaj.setText("Dodaj bibliotekara");
			lblUspesno.setText(":) Uspešno ste registrovali bibliotekara!");

		} else {
			pozadina.setTitle("Izmena bibliotekara");
			labelaPrijava.setText("Izmena bibliotekara");
			unosEmail.setText(bibliotekar.getEmail());
			unosEmail.setEditable(false);
			unosIme.setText(bibliotekar.getIme());
			unosPrezime.setText(bibliotekar.getPrezime());
			unosJMBG.setText(bibliotekar.getJmbg());
			unosAdresa.setText(bibliotekar.getAdresa());
			unosPostanskiBroj.setText(bibliotekar.getMesto().getPttBroj() + "");
			comboBoxFilijala.setSelectedItem(bibliotekar.getFilijala());
			comboBoxTip.setSelectedItem(bibliotekar.getTip().name());
			lblUspesno.setText(":) Uspešno ste izmenili podatke o bibliotekaru!");
			dugmeDodaj.setText("Izmeni bibliotekara");
		}

	}

	private void promeniBojuInputa() {
		unosEmail.setBackground(new Color(255, 255, 255));
		unosIme.setBackground(new Color(255, 255, 255));
		unosPrezime.setBackground(new Color(255, 255, 255));
		unosJMBG.setBackground(new Color(255, 255, 255));
		unosAdresa.setBackground(new Color(255, 255, 255));
		unosPostanskiBroj.setBackground(new Color(255, 255, 255));
	}

	@Override
	public void updatePerformed(RepositoryUpdateEvent event) {
		ArrayList<Filijala> filijale = (ArrayList<Filijala>) event.getAkcija();

		for (Filijala filijala : filijale) {
			comboBoxFilijala.addItem(filijala.getNaziv());
		}

	}

	private void azuriranjeBibliotekara() {
		RegistracijaBibliotekaraDTO registracijaDto = new RegistracijaBibliotekaraDTO(unosEmail.getText(),
				unosIme.getText(), unosPrezime.getText(), unosJMBG.getText(), datumRodjenja.getDate(),
				unosAdresa.getText(), unosPostanskiBroj.getText(),
				TipZaposlenog.valueOf(comboBoxTip.getSelectedItem().toString()),
				comboBoxFilijala.getSelectedItem().toString());
		promeniBojuInputa();

		boolean dodavanje = (bibliotekar == null) ? true : false;

		try {
			bibliotekaKontroler.azuriranjeBibliotekara(registracijaDto, dodavanje);
			lblGreska.setVisible(false);
			lblUspesno.setVisible(true);
		} catch (PostojeciEmailException e2) {
			lblGreska.setVisible(true);
			lblGreska.setText(e2.getValueName());
			unosEmail.setBackground(new Color(255, 179, 179));
			unosEmail.setText("");
		} catch (PraznoImeException e4) {
			lblGreska.setVisible(true);
			lblGreska.setText(e4.getValueName());
			unosIme.setBackground(new Color(255, 179, 179));
			unosIme.setText("");
		} catch (PraznoPrezimeException e3) {
			lblGreska.setVisible(true);
			lblGreska.setText(e3.getValueName());
			unosPrezime.setBackground(new Color(255, 179, 179));
			unosPrezime.setText("");
		} catch (NeispravanJmbgException e5) {
			lblGreska.setVisible(true);
			lblGreska.setText(e5.getValueName());
			unosJMBG.setBackground(new Color(255, 179, 179));
			unosJMBG.setText("");
		} catch (PraznoAdresaException e6) {
			lblGreska.setVisible(true);
			lblGreska.setText(e6.getValueName());
			unosAdresa.setBackground(new Color(255, 179, 179));
			unosAdresa.setText("");
		} catch (NepostojeciPostanskiBrojException e7) {
			lblGreska.setVisible(true);
			lblGreska.setText(e7.getValueName());
			unosPostanskiBroj.setBackground(new Color(255, 179, 179));
			unosPostanskiBroj.setText("");
		} catch (SQLException e8) {
			e8.printStackTrace();
		}
	}

}
