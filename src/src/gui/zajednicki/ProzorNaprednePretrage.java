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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Knjiga;
import observer.Observer;
import observer.RepositoryUpdateEvent;
import repos.KnjigaRepo;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;

import dto.NaprednaPretragaDTO;
import gui.pomocniElementi.StilDugmeta;
import gui.pomocniElementi.StilTekstPolja;
import kontroler.ClanKontroler;
import kontroler.KnjigaKontroler;
import kontroler.RezervacijaKontroler;

public class ProzorNaprednePretrage implements Observer {
	private KnjigaKontroler knjigaKontroler;
	private String ulogovaniKorisnik;
	private ClanKontroler clanKontroler;
	private RezervacijaKontroler rezervacijaKontroler;
	private KnjigaRepo knjigaRepo;
	private JFrame pozadina;
	private JTextField unosNaslov;
	private int prviPutKliknuoNaziv = 0;
	private int prviPutKliknuoAutor = 0;
	private int prviPutKliknuoIzdavac = 0;
	private int prviPutKliknuoGodIzdavanja = 0;
	private int prviPutKliknuoKljucneReci = 0;
	private ArrayList<Knjiga> listaPretrazenihKnjiga = new ArrayList<>();
	private StilTekstPolja unosAutor;
	private StilTekstPolja unosIzdavac;
	private StilTekstPolja unosGodina;
	private StilTekstPolja unosReci;

	public ProzorNaprednePretrage(KnjigaKontroler knjigaKontroler, String ulogovaniKorisnik,
			ClanKontroler clanKontroler, RezervacijaKontroler rezervacijaKontroler, KnjigaRepo knjigaRepo) {
		this.knjigaKontroler = knjigaKontroler;
		this.knjigaRepo = knjigaRepo;
		this.ulogovaniKorisnik = ulogovaniKorisnik;
		this.clanKontroler = clanKontroler;
		this.rezervacijaKontroler = rezervacijaKontroler;
		this.knjigaRepo.addObserver(this);
		initialize();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		pozadina = new JFrame();
		pozadina.setBounds(100, 100, 470, 488);
		pozadina.setTitle("Napredna pretraga");
		pozadina.setVisible(true);
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));
		pozadina.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pozadina.setVisible(false);
			}
		});
		JPanel panel = new JPanel();
		pozadina.getContentPane().add(panel, BorderLayout.CENTER);
		pozadina.setBackground(Color.WHITE);
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);

		JPanel pozadinskiPanel = new JPanel();
		pozadinskiPanel.setBounds(10, 21, 434, 409);
		pozadinskiPanel.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel);
		pozadinskiPanel.setLayout(null);

		unosNaslov = new StilTekstPolja(100);
		unosNaslov.setBounds(112, 31, 173, 40);
		unosNaslov.setHorizontalAlignment(SwingConstants.LEFT);
		unosNaslov.setFont(new Font("Times New Roman", Font.PLAIN | Font.ITALIC, 20));
		unosNaslov.setText("Naslov");
		unosNaslov.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		unosNaslov.setForeground(new Color(102, 0, 0));
		unosNaslov.setColumns(10);
		unosNaslov.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (prviPutKliknuoNaziv == 0) {
					unosNaslov.setText("");
					prviPutKliknuoNaziv++;
					unosNaslov.setForeground(new Color(102, 0, 0));
				}
			}
		});
		pozadinskiPanel.add(unosNaslov);

		JComboBox comboBox1Operatori = new JComboBox();
		comboBox1Operatori.setFocusTraversalKeysEnabled(false);
		comboBox1Operatori.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		comboBox1Operatori.setFocusable(false);
		comboBox1Operatori.setFont(new Font("Times New Roman", Font.BOLD, 17));
		comboBox1Operatori.setModel(new DefaultComboBoxModel(new String[] { "AND", "OR" }));
		comboBox1Operatori.setBackground(new Color(0xeadbc8));
		comboBox1Operatori.setForeground(new Color(102, 0, 0));
		comboBox1Operatori.setBounds(304, 37, 65, 29);
		pozadinskiPanel.add(comboBox1Operatori);

		JComboBox comboBox2Operatori = new JComboBox();
		comboBox2Operatori.setModel(new DefaultComboBoxModel(new String[] { "AND", "OR" }));
		comboBox2Operatori.setForeground(new Color(102, 0, 0));
		comboBox2Operatori.setFont(new Font("Times New Roman", Font.BOLD, 17));
		comboBox2Operatori.setFocusable(false);
		comboBox2Operatori.setFocusTraversalKeysEnabled(false);
		comboBox2Operatori.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		comboBox2Operatori.setBackground(new Color(234, 219, 200));
		comboBox2Operatori.setBounds(304, 102, 65, 29);
		pozadinskiPanel.add(comboBox2Operatori);

		unosAutor = new StilTekstPolja(100);
		unosAutor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (prviPutKliknuoAutor == 0) {
					unosAutor.setText("");
					prviPutKliknuoAutor++;
					unosAutor.setForeground(new Color(102, 0, 0));
				}
			}
		});
		unosAutor.setHorizontalAlignment(SwingConstants.LEFT);
		unosAutor.setBounds(112, 96, 173, 40);
		pozadinskiPanel.add(unosAutor);
		unosAutor.setFont(new Font("Times New Roman", Font.PLAIN | Font.ITALIC, 20));
		unosAutor.setText("Autor");
		unosAutor.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		unosAutor.setForeground(new Color(102, 0, 0));
		unosAutor.setColumns(10);

		JComboBox comboBox3Operatori = new JComboBox();
		comboBox3Operatori.setModel(new DefaultComboBoxModel(new String[] { "AND", "OR" }));
		comboBox3Operatori.setForeground(new Color(102, 0, 0));
		comboBox3Operatori.setFont(new Font("Times New Roman", Font.BOLD, 17));
		comboBox3Operatori.setFocusable(false);
		comboBox3Operatori.setFocusTraversalKeysEnabled(false);
		comboBox3Operatori.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		comboBox3Operatori.setBackground(new Color(234, 219, 200));
		comboBox3Operatori.setBounds(304, 167, 65, 29);
		pozadinskiPanel.add(comboBox3Operatori);

		unosIzdavac = new StilTekstPolja(100);
		unosIzdavac.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (prviPutKliknuoIzdavac == 0) {
					unosIzdavac.setText("");
					prviPutKliknuoIzdavac++;
					unosIzdavac.setForeground(new Color(102, 0, 0));
				}
			}
		});
		unosIzdavac.setHorizontalAlignment(SwingConstants.LEFT);
		unosIzdavac.setBounds(112, 161, 173, 40);
		pozadinskiPanel.add(unosIzdavac);
		unosIzdavac.setFont(new Font("Times New Roman", Font.PLAIN | Font.ITALIC, 20));
		unosIzdavac.setText("Izdavač");
		unosIzdavac.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		unosIzdavac.setForeground(new Color(102, 0, 0));
		unosIzdavac.setColumns(10);

		JComboBox comboBox4Operatori = new JComboBox();
		comboBox4Operatori.setModel(new DefaultComboBoxModel(new String[] { "AND", "OR" }));
		comboBox4Operatori.setForeground(new Color(102, 0, 0));
		comboBox4Operatori.setFont(new Font("Times New Roman", Font.BOLD, 17));
		comboBox4Operatori.setFocusable(false);
		comboBox4Operatori.setFocusTraversalKeysEnabled(false);
		comboBox4Operatori.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		comboBox4Operatori.setBackground(new Color(234, 219, 200));
		comboBox4Operatori.setBounds(304, 228, 65, 29);
		pozadinskiPanel.add(comboBox4Operatori);

		unosGodina = new StilTekstPolja(100);
		unosGodina.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (prviPutKliknuoGodIzdavanja == 0) {
					unosGodina.setText("");
					prviPutKliknuoGodIzdavanja++;
					unosGodina.setForeground(new Color(102, 0, 0));
				}
			}
		});
		unosGodina.setHorizontalAlignment(SwingConstants.LEFT);
		unosGodina.setBounds(112, 222, 173, 40);
		pozadinskiPanel.add(unosGodina);
		unosGodina.setFont(new Font("Times New Roman", Font.PLAIN | Font.ITALIC, 20));
		unosGodina.setText("Godina izdavanja");
		unosGodina.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		unosGodina.setForeground(new Color(102, 0, 0));
		unosGodina.setColumns(10);

		unosReci = new StilTekstPolja(100);
		unosReci.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (prviPutKliknuoKljucneReci == 0) {
					unosReci.setText("");
					prviPutKliknuoKljucneReci++;
					unosReci.setForeground(new Color(102, 0, 0));
				}
			}
		});
		unosReci.setHorizontalAlignment(SwingConstants.LEFT);
		unosReci.setBounds(112, 282, 173, 40);
		pozadinskiPanel.add(unosReci);
		unosReci.setFont(new Font("Times New Roman", Font.PLAIN | Font.ITALIC, 20));
		unosReci.setText("Ključne reči");
		unosReci.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		unosReci.setForeground(new Color(102, 0, 0));
		unosReci.setColumns(10);

		JButton dugmePretrazi = new JButton("Pretraži");
		dugmePretrazi.setIcon(new ImageIcon("slike\\pretraga.png"));
		dugmePretrazi.setHorizontalAlignment(SwingConstants.LEFT);
		dugmePretrazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pozadina.setVisible(false);
				try {
					NaprednaPretragaDTO dto = new NaprednaPretragaDTO(unosNaslov.getText(), unosAutor.getText(),
							unosIzdavac.getText(), unosGodina.getText(), unosReci.getText(),
							comboBox1Operatori.getSelectedItem().toString(),
							comboBox2Operatori.getSelectedItem().toString(),
							comboBox3Operatori.getSelectedItem().toString(),
							comboBox4Operatori.getSelectedItem().toString());
					knjigaKontroler.naprednaPretraga(dto);
					if(listaPretrazenihKnjiga.size() == 0)
						new ProzorObavestenjaKorisnika("Ne postoji knjiga za zadate kriterijume.");
					else
						new ProzorPrikazaRezultataPretrage(knjigaKontroler, ulogovaniKorisnik, clanKontroler,
							rezervacijaKontroler, listaPretrazenihKnjiga);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		dugmePretrazi.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 21));
		dugmePretrazi.setForeground(new Color(0xf4ece2));
		dugmePretrazi.setBackground(new Color(102, 0, 0));
		dugmePretrazi.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmePretrazi.setUI(new StilDugmeta());
		dugmePretrazi.setBounds(231, 342, 193, 57);
		pozadinskiPanel.add(dugmePretrazi);

		JLabel ikonicaGodIzdavanja = new JLabel("");
		ikonicaGodIzdavanja.setIcon(new ImageIcon("slike\\jmbg.png"));
		ikonicaGodIzdavanja.setBounds(27, 217, 46, 45);
		pozadinskiPanel.add(ikonicaGodIzdavanja);

		JLabel ikonicaIzdavac = new JLabel("");
		ikonicaIzdavac.setIcon(new ImageIcon("slike\\izdavac.png"));
		ikonicaIzdavac.setBounds(27, 161, 46, 45);
		pozadinskiPanel.add(ikonicaIzdavac);

		JLabel ikonicaNaslov = new JLabel("");
		ikonicaNaslov.setIcon(new ImageIcon("slike\\naslovKnjige.png"));
		ikonicaNaslov.setBounds(27, 26, 46, 45);
		pozadinskiPanel.add(ikonicaNaslov);

		JLabel ikonicaAutor = new JLabel("");
		ikonicaAutor.setIcon(new ImageIcon("slike\\autori.png"));
		ikonicaAutor.setBounds(27, 96, 46, 45);
		pozadinskiPanel.add(ikonicaAutor);

		JLabel ikonicaKljucneReci = new JLabel("");
		ikonicaKljucneReci.setIcon(new ImageIcon("slike\\kljucneReci.png"));
		ikonicaKljucneReci.setBounds(27, 277, 46, 45);
		pozadinskiPanel.add(ikonicaKljucneReci);

	}

	@Override
	public void updatePerformed(RepositoryUpdateEvent event) {
		try {
			listaPretrazenihKnjiga = (ArrayList<Knjiga>) event.getAkcija();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
