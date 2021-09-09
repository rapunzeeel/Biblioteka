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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import exception.RecenzijaVecPostojiException;
import gui.pomocniElementi.StilDugmeta;
import gui.zajednicki.ProzorObavestenjaKorisnika;
import kontroler.ClanKontroler;
import kontroler.KnjigaKontroler;
import model.Autor;
import model.Clan;
import model.Knjiga;
import observer.Observer;
import observer.RepositoryUpdateEvent;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ProzorOcenjivanja implements Observer {
	private JFrame pozadina;
	private String isbn;
	private Knjiga knjiga;
	private JTextField txtPretrazi;
	private KnjigaKontroler knjigaKontroler;
	private ClanKontroler clanKontroler;
	private Clan ulogovaniKorisnik;
	private String email;
	private JSpinner spinner;
	private JLabel lblObavestenje;
	private JFrame glavniProzor;

	public ProzorOcenjivanja(String isbn, KnjigaKontroler knjigaKontroler, String email, ClanKontroler clanKontroler,
			JFrame glavniProzor) {
		this.knjigaKontroler = knjigaKontroler;
		this.isbn = isbn;
		this.email = email;
		this.clanKontroler = clanKontroler;
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
				pozadina.dispose();
				glavniProzor.setVisible(true);
			}
		});
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));

		JPanel panel = new JPanel();
		pozadina.getContentPane().add(panel, BorderLayout.CENTER);
		pozadina.setBackground(Color.WHITE);
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);

		JLabel labelaKorice = new JLabel("");
		labelaKorice.setBounds(56, 59, 150, 190);
		labelaKorice.setIcon(new ImageIcon("slike\\" + knjiga.getSlika()));
		panel.add(labelaKorice);

		JLabel labelaNazivKnjige = new JLabel(knjiga.getNaslov());
		labelaNazivKnjige.setFont(new Font("Times New Roman", Font.BOLD, 30));
		labelaNazivKnjige.setBounds(236, 59, 261, 44);
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
		opisKnjige.setEditable(false);
		opisKnjige.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		opisKnjige.setForeground(new Color(102, 0, 0));
		opisKnjige.setBackground(new Color(0xf4ece2));

		JButton dugmeOcene = new JButton("Oceni");
		dugmeOcene.setFont(new Font("Times New Roman", Font.BOLD, 30));
		dugmeOcene.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmeOcene.setUI(new StilDugmeta());
		dugmeOcene.setBackground(new Color(102, 0, 0));
		dugmeOcene.setForeground(new Color(0xf4ece2));
		dugmeOcene.setIcon(new ImageIcon("slike\\ocenjivanje.png"));
		dugmeOcene.setHorizontalAlignment(SwingConstants.LEFT);
		dugmeOcene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double ocena = (Double) spinner.getValue();
				int o = (int) ocena;
				try {
					clanKontroler.dodajRecenziju(email, knjiga.getISBN(), o);
					lblObavestenje.setText("Uspešno ste dali recenziju za knjigu.");
					lblObavestenje.setForeground(new Color(0, 102, 0));
					lblObavestenje.setVisible(true);
					spinner.setVisible(false);
					dugmeOcene.setEnabled(false);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (RecenzijaVecPostojiException e1) {
					lblObavestenje.setText(e1.getValueName());
					lblObavestenje.setVisible(true);
					lblObavestenje.setForeground(new Color(102, 0, 0));
				}
			}
		});
		dugmeOcene.setBounds(538, 191, 232, 50);
		panel.add(dugmeOcene);

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

		spinner = new JSpinner();
		spinner.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		spinner.setModel(new SpinnerNumberModel(0.0, 0.0, 5.0, 1.0));
		spinner.setBounds(535, 136, 110, 33);
		spinner.setForeground(new Color(102, 0, 0));
		panel.add(spinner);

		lblObavestenje = new JLabel("");
		lblObavestenje.setHorizontalAlignment(SwingConstants.CENTER);
		lblObavestenje.setBounds(428, 252, 342, 25);
		lblObavestenje.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblObavestenje.setForeground(new Color(102, 0, 0));
		panel.add(lblObavestenje);
		lblObavestenje.setVisible(false);

		try {
			clanKontroler.proveriPostojanjeRecenzije(email, isbn);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (RecenzijaVecPostojiException e1) {
			lblObavestenje.setText("Knjigu ste već ocenili!");
			lblObavestenje.setVisible(true);
			lblObavestenje.setForeground(new Color(102, 0, 0));
			dugmeOcene.setEnabled(false);
			spinner.setVisible(false);
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
		knjiga = (Knjiga) event.getAkcija();
		if (knjiga == null)
			new ProzorObavestenjaKorisnika("Nije pronadjena knjiga za zadati ISBN!");
	}
}
