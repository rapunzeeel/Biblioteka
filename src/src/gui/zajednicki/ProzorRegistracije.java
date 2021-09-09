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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import kontroler.BibliotekaKontroler;
import model.TipClana;

import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import dto.RegistracijaDTO;
import exception.NeispravanJmbgException;
import exception.NepostojeciPostanskiBrojException;
import exception.PostojeciEmailException;
import exception.PraznoAdresaException;
import exception.PraznoImeException;
import exception.PraznoPrezimeException;
import gui.pomocniElementi.StilDugmeta;
import gui.pomocniElementi.StilIvice;
import gui.pomocniElementi.StilTekstPolja;

public class ProzorRegistracije {
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
	private JLabel lblUspesnoProduzeno;
	private JDateChooser datumRodjenja;
	private JComboBox comboBoxTip;
	private JFrame glavniProzor;
	private JLabel lblObavestenje;

	public ProzorRegistracije(BibliotekaKontroler bibliotekaController, JFrame glavniProzor) {
		this.bibliotekaKontroler = bibliotekaController;
		this.glavniProzor = glavniProzor;
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
		pozadina.setBounds(80, 70, 520, 700);
		pozadina.setTitle("Registracija");
		pozadina.setVisible(true);
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));

		JPanel panel = new JPanel();
		pozadina.getContentPane().add(panel, BorderLayout.CENTER);
		pozadina.setBackground(Color.WHITE);
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);
		
		JLabel lblRegistracija = new StilIvice();
		lblRegistracija.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistracija.setText("Registracija");
		lblRegistracija.setIcon(new ImageIcon("slike\\dodajClana.png"));
		lblRegistracija.setBounds(95, 11, 313, 52);
		panel.add(lblRegistracija);
		lblRegistracija.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblRegistracija.setForeground(new Color(0xf4ece2));
		lblRegistracija.setBackground(new Color(102, 0, 0));

		JPanel pozadinskiPanel = new JPanel();
		pozadinskiPanel.setBounds(33, 34, 440, 610);
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
		unosEmail.setText("Email");
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

		unosIme.setText("Ime");
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

		unosPrezime.setText("Prezime");
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

		unosJMBG.setText("JMBG");
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

		unosAdresa.setText("Adresa");
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

		unosPostanskiBroj.setText("Poštanski broj");
		unosPostanskiBroj.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		unosPostanskiBroj.setColumns(10);
		unosPostanskiBroj.setBounds(95, 407, 277, 47);
		unosPostanskiBroj.setForeground(new Color(102, 0, 0));
		pozadinskiPanel.add(unosPostanskiBroj);

		comboBoxTip = new JComboBox();
		comboBoxTip.setBorder(new LineBorder(new Color(139, 0, 0), 1, true));
		comboBoxTip.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		comboBoxTip.setForeground(new Color(102, 0, 0));
		comboBoxTip.setBackground(Color.WHITE);
		comboBoxTip.setModel(new DefaultComboBoxModel(
				new String[] { "PREDSKOLAC", "DJAK", "STUDENT", "ZAPOSLENI", "PENZIONER", "POCASNI" }));
		comboBoxTip.setSelectedIndex(3);
		comboBoxTip.setBounds(95, 466, 139, 37);
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

		JButton dugmePrijave = new JButton("Registruj \u010Dlana");
		dugmePrijave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistracijaDTO registracijaDto = new RegistracijaDTO(unosEmail.getText(), unosIme.getText(),
						unosPrezime.getText(), unosJMBG.getText(), datumRodjenja.getDate(), unosAdresa.getText(),
						unosPostanskiBroj.getText(), TipClana.valueOf(comboBoxTip.getSelectedItem().toString()));
				promeniBojuInputa();

				try {
					bibliotekaKontroler.registrujClana(registracijaDto);
					lblGreska.setVisible(false);
					lblUspesnoProduzeno.setVisible(true);
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
					// TODO Auto-generated catch block
					e8.printStackTrace();
				}

			}
		});
		dugmePrijave.setBackground(new Color(102, 0, 0));
		dugmePrijave.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmePrijave.setUI(new StilDugmeta());
		dugmePrijave.setFont(new Font("Times New Roman", Font.BOLD, 30));
		dugmePrijave.setForeground(new Color(0xf4ece2));
		dugmePrijave.setBounds(116, 514, 236, 45);
		pozadinskiPanel.add(dugmePrijave);

		lblGreska = new JLabel(":( Neispravni kredencijali. Pokušajte ponovo!");
		lblGreska.setForeground(new Color(102, 0, 0));
		lblGreska.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblGreska.setBounds(40, 565, 390, 34);
		lblGreska.setVisible(false);
		pozadinskiPanel.add(lblGreska);

		datumRodjenja = new JDateChooser();
		datumRodjenja.setForeground(new Color(102, 0, 0));
		datumRodjenja.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		datumRodjenja.setBounds(244, 465, 128, 37);
		pozadinskiPanel.add(datumRodjenja);
		datumRodjenja.setDate(Date.valueOf(LocalDate.now()));
		
		Calendar c = Calendar.getInstance();
	    c.add(Calendar.DATE, -1095);
	    Date d =  new Date(c.getTimeInMillis());
		datumRodjenja.setMaxSelectableDate(d);

		lblUspesnoProduzeno = new JLabel(":) Uspešno ste registrovali člana!");
		lblUspesnoProduzeno.setForeground(new Color(0, 102, 0));
		lblUspesnoProduzeno.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblUspesnoProduzeno.setBounds(95, 565, 300, 34);
		lblUspesnoProduzeno.setVisible(false);
		pozadinskiPanel.add(lblUspesnoProduzeno);

	}

	private void promeniBojuInputa() {
		unosEmail.setBackground(new Color(255, 255, 255));
		unosIme.setBackground(new Color(255, 255, 255));
		unosPrezime.setBackground(new Color(255, 255, 255));
		unosJMBG.setBackground(new Color(255, 255, 255));
		unosAdresa.setBackground(new Color(255, 255, 255));
		unosPostanskiBroj.setBackground(new Color(255, 255, 255));
	}
}
