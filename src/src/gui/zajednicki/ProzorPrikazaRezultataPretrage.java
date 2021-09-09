package gui.zajednicki;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kontroler.ClanKontroler;
import kontroler.KnjigaKontroler;
import kontroler.RezervacijaKontroler;
import model.Autor;
import model.Clan;
import model.Knjiga;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gui.clan.ProzorRezervacijeKnjiga;
import gui.pomocniElementi.NapraviOkrugloDugme;
import gui.pomocniElementi.StilIvice;

import java.awt.event.MouseAdapter;

public class ProzorPrikazaRezultataPretrage {
	private JFrame pozadina;
	private JTextField txtPretrazi;
	private KnjigaKontroler knjigaController;
	private ClanKontroler clanController;
	private Clan ulogovaniKorisnik;
	private String email;
	private RezervacijaKontroler rezervacijaController;
	private JLabel lblObavestenje;
	private ArrayList<Knjiga> listaPretrazenihKnjiga;
	private ArrayList<JPanel> listaPanela = new ArrayList<JPanel>();
	private int i = 3;
	private JPanel panelKnjiga;

	public ProzorPrikazaRezultataPretrage(KnjigaKontroler knjigaKontroler, String email, ClanKontroler clanKontroler,
			RezervacijaKontroler rezervacijaKontroler, ArrayList<Knjiga> listaPretrazenihKnjiga) {
		this.knjigaController = knjigaKontroler;
		this.email = email;
		this.clanController = clanKontroler;
		this.rezervacijaController = rezervacijaKontroler;
		this.listaPretrazenihKnjiga = listaPretrazenihKnjiga;
		initialize();
	}

	private void initialize() {
		pozadina = new JFrame();
		pozadina.setBounds(100, 100, 710, 610);
		pozadina.setTitle("Prikaz knjiga");
		pozadina.setVisible(true);
		pozadina.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pozadina.dispose();
			}
		});
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));
		
		JPanel panel = new JPanel();
		pozadina.getContentPane().add(panel, BorderLayout.CENTER);
		pozadina.setBackground(Color.WHITE);
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);

		nacrtajKnjigu(panel);
		
		JButton dugmeDole = new NapraviOkrugloDugme("");
		dugmeDole.setBackground(new Color(0xeadbc8));
		dugmeDole.setIcon(new ImageIcon("slike//dole.png"));
		dugmeDole.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(i<listaPretrazenihKnjiga.size()) {
					i++;
					izbrisiKnjige(panel);
					nacrtajKnjigu(panel);
				}else {
					dugmeDole.disable();
				}
			}
		});
		dugmeDole.setBounds(646, 523, 42, 42);
		panel.add(dugmeDole);
		
		
		JButton dugmeGore = new NapraviOkrugloDugme("");
		dugmeGore.setBackground(new Color(0xeadbc8));
		dugmeGore.setIcon(new ImageIcon("slike//gore.png"));
		dugmeGore.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(i>3) {
					i--;
					izbrisiKnjige(panel);
					nacrtajKnjigu(panel);
				}else {
					dugmeGore.disable();
				}
			}
		});
		dugmeGore.setBounds(646, 9, 42, 42);
		panel.add(dugmeGore);
	}

	private void izbrisiKnjige(JPanel panel) {
		for(JPanel p: listaPanela) {
			panel.remove(p);
			panel.repaint();
		}
		listaPanela.clear();
	}
	
	private void nacrtajKnjigu(JPanel panel) {
		int yPanel = 2;
		int yKorica = 2;
		int yOcena = 50;
		int yAutori = 107;
		int yIzd = 147;
		for (int j = i - 3; j < i; j++) {
			if(j>listaPretrazenihKnjiga.size()-1 && listaPretrazenihKnjiga.size() < 3)
				break;
			JPanel panel1 = new JPanel();
			panel1.setBounds(6, yPanel, 600, 183);
			panel.add(panel1, BorderLayout.CENTER);
			panel1.setBackground(new Color(0xf4ece2));
			panel1.setLayout(null);
			
			Knjiga knjiga = listaPretrazenihKnjiga.get(j);
			JLabel labelaKorice = new JLabel("");
			labelaKorice.setBounds(56, yKorica, 150, 190);
			labelaKorice.setIcon(new ImageIcon("slike\\" + knjiga.getSlika()));
			panel1.add(labelaKorice);
			
			panel1.addMouseListener(
					new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							new ProzorRezervacijeKnjiga(knjiga.getISBN(), knjigaController, email, clanController, 
									rezervacijaController, pozadina);
							
						}
					});

			JLabel labelaNazivKnjige = new JLabel(knjiga.getNaslov());
			labelaNazivKnjige.setFont(new Font("Times New Roman", Font.BOLD, 30));
			labelaNazivKnjige.setBounds(236, yKorica, 290, 44);
			labelaNazivKnjige.setForeground(new Color(102, 0, 0));
			panel1.add(labelaNazivKnjige);
			String slikaOcene = nadjiSlikuOcene(knjiga);

			JLabel labelaOcena = new JLabel();
			labelaOcena.setBounds(236, yOcena, 289, 55);
			labelaOcena.setIcon(new ImageIcon(slikaOcene));
			panel1.add(labelaOcena);

			String autori = "";
			for (Autor a : knjiga.getAutori()) {
				autori += a.getIme() + " " + a.getPrezime() + ", ";
			}
			autori = autori.substring(0, autori.length() - 2);

			JLabel labelaNazivAutora = new JLabel(autori);
			labelaNazivAutora.setFont(new Font("Times New Roman", Font.BOLD, 25));
			labelaNazivAutora.setBounds(236, yAutori, 261, 30);
			labelaNazivAutora.setForeground(new Color(102, 0, 0));
			panel1.add(labelaNazivAutora);

			JLabel labelaIzdavaca = new JLabel(knjiga.getIzdavac());
			labelaIzdavaca.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			labelaIzdavaca.setBounds(236, yIzd, 261, 30);
			labelaIzdavaca.setForeground(new Color(102, 0, 0));
			panel1.add(labelaIzdavaca);
			listaPanela.add(panel1);
			yPanel += 190;
		}
	}

	private String nadjiSlikuOcene(Knjiga knjiga) {
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
}
