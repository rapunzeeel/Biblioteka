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
import java.sql.SQLException;
import java.util.ArrayList;

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

import dto.AutorDTO;
import exception.NemogucPeriodException;
import exception.PostojecaKnjigaException;
import exception.PraznoFormatException;
import exception.PraznoImeException;
import exception.PraznoIsbnException;
import exception.PraznoIzdavacException;
import exception.PraznoNaslovException;
import exception.PraznoPrezimeException;
import gui.pomocniElementi.NapraviOkrugloDugme;
import gui.pomocniElementi.StilDugmeta;
import gui.pomocniElementi.StilIvice;
import gui.pomocniElementi.StilTekstPolja;
import kontroler.KnjigaKontroler;
import model.Knjiga;
import model.TipKnjige;
import model.Zanr;
import com.toedter.calendar.JYearChooser;

public class ProzorDodavanjaKnjiga {
	private JFrame pozadina;
	private int prviPutKliknuoIsbn = 0;
	private int prviPutKliknuoNaslov = 0;
	private int prviPutKliknuoIzdavac = 0;
	private int prviPutKliknuoAutori = 0;
	private int prviPutKliknuoFormat = 0;
	private JTextField unosIsbn;
	private JTextField unosNaslov;
	private JTextField unosIzdavac;
	private JTextField unosImeAutor;
	private ArrayList<AutorDTO> autori;
	private JComboBox comboBoxZanr;
	private JYearChooser godinaIzdavanja;
	private JYearChooser godinaStampanja;
	private StilTekstPolja unosFormata;
	private StilTekstPolja unosPrzAutor;
	private JLabel lblGreska;
	private JLabel lblUspesnoDodato;
	private JComboBox comboBoxTip;
	private JFrame glavniProzor;
	private KnjigaKontroler knjigaKontroler;


	public ProzorDodavanjaKnjiga(KnjigaKontroler knjigaKontroler, JFrame glavniProzor) {
		this.glavniProzor = glavniProzor;
		this.autori = new ArrayList<AutorDTO>();
		this.knjigaKontroler = knjigaKontroler;
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
		pozadina.setBounds(80, 70, 520, 819);
		pozadina.setVisible(true);
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));
		pozadina.setTitle("Dodavanje knjige");
		pozadina.setBackground(Color.WHITE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);
		pozadina.getContentPane().add(panel, BorderLayout.CENTER);

		JLabel labelaDodavanja = new StilIvice();
		labelaDodavanja.setHorizontalAlignment(SwingConstants.LEFT);
		labelaDodavanja.setText("Dodavanje knjige");
		labelaDodavanja.setIcon(new ImageIcon("slike\\knjigaISBN.png"));
		labelaDodavanja.setBounds(70, 11, 362, 60);
		labelaDodavanja.setFont(new Font("Times New Roman", Font.BOLD, 30));
		labelaDodavanja.setForeground(new Color(0xf4ece2));
		labelaDodavanja.setBackground(new Color(102, 0, 0));
		panel.add(labelaDodavanja);

		JPanel pozadinskiPanel = new JPanel();
		pozadinskiPanel.setBounds(34, 33, 440, 726);
		pozadinskiPanel.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel);
		pozadinskiPanel.setLayout(null);

		unosIsbn = new StilTekstPolja(200);
		unosIsbn.setColumns(10);
		unosIsbn.setText("ISBN");
		unosIsbn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (prviPutKliknuoIsbn == 0) {
					unosIsbn.setText("");
					prviPutKliknuoIsbn++;
				}
			}
		});

		unosIsbn.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		unosIsbn.setForeground(new Color(102, 0, 0));
		unosIsbn.setBounds(96, 48, 277, 47);
		pozadinskiPanel.add(unosIsbn);

		JLabel ikonicaISBN = new JLabel("");
		ikonicaISBN.setIcon(new ImageIcon("slike\\knjigaISBN.png"));
		ikonicaISBN.setBounds(40, 48, 46, 45);
		pozadinskiPanel.add(ikonicaISBN);

		unosNaslov = new StilTekstPolja(200);
		unosNaslov.setText("Naslov");
		unosNaslov.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (prviPutKliknuoNaslov == 0) {
					unosNaslov.setText("");
					prviPutKliknuoNaslov++;
				}
			}
		});
		unosNaslov.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		unosNaslov.setColumns(10);
		unosNaslov.setForeground(new Color(102, 0, 0));
		unosNaslov.setBounds(96, 112, 277, 47);
		pozadinskiPanel.add(unosNaslov);

		unosIzdavac = new StilTekstPolja(200);
		unosIzdavac.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (prviPutKliknuoIzdavac == 0) {
					unosIzdavac.setText("");
					prviPutKliknuoIzdavac++;
				}
			}
		});

		JLabel ikonicaNaslov = new JLabel("");
		unosIzdavac.setText("Izdavač");
		ikonicaNaslov.setIcon(new ImageIcon("slike\\naslovKnjige.png"));
		ikonicaNaslov.setBounds(40, 112, 46, 45);
		pozadinskiPanel.add(ikonicaNaslov);

		unosIzdavac.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		unosIzdavac.setColumns(10);
		unosIzdavac.setBounds(96, 272, 277, 47);
		unosIzdavac.setForeground(new Color(102, 0, 0));
		pozadinskiPanel.add(unosIzdavac);

		JLabel ikonicaIzdavac = new JLabel("");
		ikonicaIzdavac.setIcon(new ImageIcon("slike\\izdavac.png"));
		ikonicaIzdavac.setBounds(40, 272, 46, 45);
		pozadinskiPanel.add(ikonicaIzdavac);

		JLabel ikonicaTipKnjige = new JLabel("");
		ikonicaTipKnjige.setIcon(new ImageIcon("slike\\tip.png"));
		ikonicaTipKnjige.setBounds(40, 435, 46, 45);
		pozadinskiPanel.add(ikonicaTipKnjige);

		JLabel lblTip = new JLabel("Tip");
		lblTip.setForeground(new Color(102, 0, 0));
		lblTip.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblTip.setBounds(96, 443, 39, 37);
		pozadinskiPanel.add(lblTip);

		comboBoxTip = new JComboBox();
		comboBoxTip.setBorder(new LineBorder(new Color(139, 0, 0), 1, true));
		comboBoxTip.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		comboBoxTip.setForeground(new Color(102, 0, 0));
		comboBoxTip.setBackground(Color.WHITE);
		comboBoxTip.setModel(new DefaultComboBoxModel(new String[] {
				"OBICNA", "ENCIKLOPEDIJA", "SLIKOVNICA", "NOVINE" }));
		comboBoxTip.setSelectedIndex(0);
		comboBoxTip.setBounds(153, 443, 220, 37);
		pozadinskiPanel.add(comboBoxTip);

		JLabel lblGodIzd = new JLabel("God. izdavanja");
		lblGodIzd.setForeground(new Color(102, 0, 0));
		lblGodIzd.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblGodIzd.setBounds(95, 170, 149, 34);
		pozadinskiPanel.add(lblGodIzd);

		JLabel lblGodStampanja = new JLabel("God. štampanja");
		lblGodStampanja.setForeground(new Color(102, 0, 0));
		lblGodStampanja.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblGodStampanja.setBounds(96, 224, 128, 37);
		pozadinskiPanel.add(lblGodStampanja);

		JLabel ikonicaGodIzdavanja = new JLabel("");
		ikonicaGodIzdavanja.setIcon(new ImageIcon("slike\\jmbg.png"));
		ikonicaGodIzdavanja.setBounds(40, 168, 46, 45);
		pozadinskiPanel.add(ikonicaGodIzdavanja);

		JLabel ikonicaGodStampanja = new JLabel("");
		ikonicaGodStampanja.setIcon(new ImageIcon("slike\\jmbg.png"));
		ikonicaGodStampanja.setBounds(40, 216, 46, 45);
		pozadinskiPanel.add(ikonicaGodStampanja);

		godinaIzdavanja = new JYearChooser();
		godinaIzdavanja.setBounds(254, 170, 89, 28);
		godinaIzdavanja.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		pozadinskiPanel.add(godinaIzdavanja);

		godinaStampanja = new JYearChooser();
		godinaStampanja.setBounds(254, 233, 89, 28);
		godinaStampanja.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		pozadinskiPanel.add(godinaStampanja);

		unosFormata = new StilTekstPolja(200);
		unosFormata.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (prviPutKliknuoFormat == 0) {
					unosFormata.setText("");
					prviPutKliknuoFormat++;
				}
			}
		});

		unosFormata.setText("Format (npr. 20x15cm)");
		unosFormata.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		unosFormata.setForeground(new Color(102, 0, 0));
		unosFormata.setBounds(96, 378, 277, 47);
		pozadinskiPanel.add(unosFormata);

		JLabel ikonicaFormat = new JLabel("");
		ikonicaFormat.setIcon(new ImageIcon("slike\\format.png"));
		ikonicaFormat.setBounds(40, 378, 46, 45);
		pozadinskiPanel.add(ikonicaFormat);

		JLabel lblZanr = new JLabel("Žanr");
		lblZanr.setForeground(new Color(102, 0, 0));
		lblZanr.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblZanr.setBounds(96, 330, 60, 37);
		pozadinskiPanel.add(lblZanr);

		JLabel ikonicaZanr = new JLabel("");
		ikonicaZanr.setIcon(new ImageIcon("slike\\zanr.png"));
		ikonicaZanr.setBounds(40, 322, 46, 45);
		pozadinskiPanel.add(ikonicaZanr);

		comboBoxZanr = new JComboBox();
		comboBoxZanr.setBorder(new LineBorder(new Color(102, 0, 0), 1, true));
		comboBoxZanr.setForeground(new Color(102, 0, 0));
		comboBoxZanr.setModel(new DefaultComboBoxModel(Zanr.values()));
		comboBoxZanr.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		comboBoxZanr.setBounds(153, 330, 220, 37);
		pozadinskiPanel.add(comboBoxZanr);

		JLabel lblAutori = new JLabel("Ime autora");
		lblAutori.setForeground(new Color(102, 0, 0));
		lblAutori.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblAutori.setBounds(40, 507, 116, 37);
		pozadinskiPanel.add(lblAutori);

		unosImeAutor = new StilTekstPolja(200);
		unosImeAutor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (prviPutKliknuoAutori == 0) {
					unosImeAutor.setText("");
					prviPutKliknuoAutori++;
				}
			}
		});

		unosImeAutor.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		unosImeAutor.setColumns(10);
		unosImeAutor.setBounds(153, 502, 220, 47);
		unosImeAutor.setForeground(new Color(102, 0, 0));
		unosImeAutor.setVisible(true);
		pozadinskiPanel.add(unosImeAutor);

		JLabel lblPrezimeAutora = new JLabel("Prezime autora");
		lblPrezimeAutora.setForeground(new Color(102, 0, 0));
		lblPrezimeAutora.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblPrezimeAutora.setBounds(10, 563, 146, 37);
		pozadinskiPanel.add(lblPrezimeAutora);

		unosPrzAutor = new StilTekstPolja(200);
		unosPrzAutor.setBounds(153, 562, 220, 47);
		unosPrzAutor.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		unosPrzAutor.setColumns(10);
		unosPrzAutor.setForeground(new Color(102, 0, 0));
		unosPrzAutor.setVisible(true);
		pozadinskiPanel.add(unosPrzAutor);

		JButton dugmeDodavanja = new NapraviOkrugloDugme("");
		dugmeDodavanja.setBounds(383, 562, 51, 47);
		pozadinskiPanel.add(dugmeDodavanja);
		dugmeDodavanja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AutorDTO autor = new AutorDTO(unosImeAutor.getText(), unosPrzAutor.getText());
				if (!autor.getIme().equals("") && !autor.getPrezime().equals("")) {
					autori.add(autor);
					unosImeAutor.setText("");
					unosPrzAutor.setText("");
				}else {
					unosImeAutor.setBackground(new Color(255, 179, 179));
					unosPrzAutor.setBackground(new Color(255, 179, 179));
				}

			}
		});
		dugmeDodavanja.setIcon(new ImageIcon("slike\\dodaj.png"));
		dugmeDodavanja.setForeground(new Color(0xf4ece2));
		dugmeDodavanja.setVisible(true);

		JButton dugmePotvrde = new JButton("");
		dugmePotvrde.setText("Dodaj knjigu");
		dugmePotvrde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				promeniBojuInputa();
				lblGreska.setVisible(false);
				lblUspesnoDodato.setVisible(false);
				
				AutorDTO autor = new AutorDTO(unosImeAutor.getText(), unosPrzAutor.getText());
				if (!autori.contains(autor) && !autor.getIme().equals("") && !autor.getPrezime().equals(""))
					autori.add(autor);
				
				if(autori.size() == 0)
				{
					lblGreska.setText("Niste uneli nijednog autora!");
					lblGreska.setVisible(true);
					unosImeAutor.setBackground(new Color(255, 179, 179));
					unosPrzAutor.setBackground(new Color(255, 179, 179));	
				}
				else {
					Knjiga knjiga = new Knjiga(unosIsbn.getText(), unosNaslov.getText(), godinaIzdavanja.getYear() + "",
							godinaStampanja.getYear() + "", unosIzdavac.getText(),
							Zanr.valueOf(comboBoxZanr.getSelectedItem().toString()), unosFormata.getText(),
							TipKnjige.valueOf(comboBoxTip.getSelectedItem().toString()), 0, null, null, null, null);
					
					try {
						knjigaKontroler.dodajNovuKnjigu(knjiga, autori);
						
						lblGreska.setVisible(false);
						lblUspesnoDodato.setVisible(true);
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (PostojecaKnjigaException e1) {
						lblGreska.setVisible(true);
						lblGreska.setText(e1.getValueName());
						unosIsbn.setBackground(new Color(255, 179, 179));
						unosIsbn.setText("");
					} catch (NemogucPeriodException e1) {
						lblGreska.setVisible(true);
						lblGreska.setText(e1.getValueName());
					} catch (PraznoIsbnException e1) {
						lblGreska.setVisible(true);
						lblGreska.setText(e1.getValueName());
						unosIsbn.setBackground(new Color(255, 179, 179));
						unosIsbn.setText("");
					} catch (PraznoNaslovException e1) {
						lblGreska.setVisible(true);
						lblGreska.setText(e1.getValueName());
						unosNaslov.setBackground(new Color(255, 179, 179));
						unosNaslov.setText("");
					} catch (PraznoIzdavacException e1) {
						lblGreska.setVisible(true);
						lblGreska.setText(e1.getValueName());
						unosIzdavac.setBackground(new Color(255, 179, 179));
						unosIzdavac.setText("");
					} catch (PraznoFormatException e1) {
						lblGreska.setVisible(true);
						lblGreska.setText(e1.getValueName());
						unosFormata.setBackground(new Color(255, 179, 179));
						unosFormata.setText("");
					} catch (PraznoImeException e1) {
						lblGreska.setVisible(true);
						lblGreska.setText(e1.getValueName());
						unosImeAutor.setBackground(new Color(255, 179, 179));
						unosImeAutor.setText("");
						autori.clear();
					} catch (PraznoPrezimeException e1) {
						lblGreska.setVisible(true);
						lblGreska.setText(e1.getValueName());
						unosPrzAutor.setBackground(new Color(255, 179, 179));
						unosPrzAutor.setText("");
						autori.clear();
					}
				
				}

			}
		});
		dugmePotvrde.setBackground(new Color(102, 0, 0));
		dugmePotvrde.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmePotvrde.setUI(new StilDugmeta());
		dugmePotvrde.setFont(new Font("Times New Roman", Font.BOLD, 30));
		dugmePotvrde.setForeground(new Color(0xf4ece2));
		dugmePotvrde.setBounds(97, 642, 277, 45);
		pozadinskiPanel.add(dugmePotvrde);

		lblGreska = new JLabel("");
		lblGreska.setForeground(new Color(102, 0, 0));
		lblGreska.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblGreska.setBounds(96, 681, 300, 34);
		lblGreska.setVisible(false);
		pozadinskiPanel.add(lblGreska);

		lblUspesnoDodato = new JLabel(":) Uspešno ste dodali knjigu!");
		lblUspesnoDodato.setForeground(new Color(0, 102, 0));
		lblUspesnoDodato.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblUspesnoDodato.setBounds(96, 681, 256, 34);
		lblUspesnoDodato.setVisible(false);
		pozadinskiPanel.add(lblUspesnoDodato);

	}

	private void promeniBojuInputa() {
		unosIsbn.setBackground(new Color(255, 255, 255));
		unosNaslov.setBackground(new Color(255, 255, 255));
		unosIzdavac.setBackground(new Color(255, 255, 255));
		unosFormata.setBackground(new Color(255, 255, 255));
		unosImeAutor.setBackground(new Color(255, 255, 255));
		unosPrzAutor.setBackground(new Color(255, 255, 255));
	}
}
