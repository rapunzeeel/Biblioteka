package gui.clan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dto.PozajmljeneKnjigeDTO;
import gui.pomocniElementi.NapraviOkrugloDugme;
import gui.pomocniElementi.StilIvice;
import kontroler.ClanKontroler;
import kontroler.KnjigaKontroler;
import model.Autor;

import javax.swing.JScrollPane;

public class ProzorIstorijeZaduzenja {

	private JFrame pozadina;
	private KnjigaKontroler knjigaKontroler;
	private String ulogovaniKorisnik;
	private ClanKontroler clanKontroler;
	private PozajmljeneKnjigeDTO pozajmljeneKnjigeDTO;
	private ArrayList<JButton> listaDugmica = new ArrayList<>();
	private ArrayList<JLabel> listaLabela = new ArrayList<>();
	private int i = 5;
	private int brojElemenata = 5;
	private JPanel pozadinskiPanel;
	private JPanel pozadinskiPanel2;
	private JFrame glavniProzor;

	public ProzorIstorijeZaduzenja(ClanKontroler clanKontroler, KnjigaKontroler knjigaKontroler,
			String ulogovaniKorisnik, PozajmljeneKnjigeDTO pozajmljeneKnjigeDTO, JFrame glavniProzor) {
		this.knjigaKontroler = knjigaKontroler;
		this.clanKontroler = clanKontroler;
		this.ulogovaniKorisnik = ulogovaniKorisnik;
		this.pozajmljeneKnjigeDTO = pozajmljeneKnjigeDTO;
		this.glavniProzor = glavniProzor;
		initialize();

	}

	private void initialize() {

		pozadina = new JFrame();
		pozadina.setBounds(100, 100, 450, 300);
		pozadina.setSize(1230, 734);
		pozadina.setTitle("Biblioteka");
		pozadina.setVisible(true);
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

		JLabel lblPrethodnaZaduzenja = new StilIvice();
		lblPrethodnaZaduzenja.setText("Prethodna zaduženja");
		lblPrethodnaZaduzenja.setIcon(new ImageIcon("slike\\izvestaj.png"));
		lblPrethodnaZaduzenja.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrethodnaZaduzenja.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblPrethodnaZaduzenja.setForeground(new Color(0xf4ece2));
		lblPrethodnaZaduzenja.setBackground(new Color(102, 0, 0));
		lblPrethodnaZaduzenja.setBounds(20, 380, 330, 47);
		panel.add(lblPrethodnaZaduzenja);

		JLabel lblTrenutnaZaduzenja = new StilIvice();
		lblTrenutnaZaduzenja.setText("Trenutna zaduženja");
		lblTrenutnaZaduzenja.setIcon(new ImageIcon("slike\\izvestaj.png"));
		lblTrenutnaZaduzenja.setHorizontalAlignment(SwingConstants.LEFT);
		lblTrenutnaZaduzenja.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblTrenutnaZaduzenja.setForeground(new Color(0xf4ece2));
		lblTrenutnaZaduzenja.setBackground(new Color(102, 0, 0));
		lblTrenutnaZaduzenja.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblTrenutnaZaduzenja.setBounds(20, 70, 330, 47);
		panel.add(lblTrenutnaZaduzenja);

		pozadinskiPanel = new JPanel();
		pozadinskiPanel.setBounds(10, 89, 1196, 284);
		pozadinskiPanel.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel);
		pozadinskiPanel.setLayout(null);

		pozadinskiPanel2 = new JPanel();
		pozadinskiPanel2.setBounds(10, 396, 1196, 291);
		pozadinskiPanel2.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel2);
		pozadinskiPanel2.setLayout(null);

		JButton dugmeNazad = new NapraviOkrugloDugme("");
		dugmeNazad.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (i > 5) {
					i--;
					izbrisiKnjige(pozadinskiPanel);
					nacrtajKnjige();
				} else {
					dugmeNazad.disable();
				}
			}
		});
		dugmeNazad.setIcon(new ImageIcon("slike\\nazad.png"));
		dugmeNazad.setBounds(10, 81, 52, 52);
		dugmeNazad.setBackground(new Color(0xf4ece2));
		pozadinskiPanel.add(dugmeNazad);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(597, 20, 2, 2);
		pozadinskiPanel.add(scrollPane);

		nacrtajKnjige();

		JButton dugmeNapred = new NapraviOkrugloDugme("");
		dugmeNapred.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (i < listaDugmica.size()) {
					i++;
					izbrisiKnjige(pozadinskiPanel);
					nacrtajKnjige();
				} else {
					dugmeNapred.disable();
				}
			}
		});
		dugmeNapred.setIcon(new ImageIcon("slike\\napred.png"));
		dugmeNapred.setBounds(1134, 81, 52, 52);
		dugmeNapred.setBackground(new Color(0xf4ece2));
		pozadinskiPanel.add(dugmeNapred);

		JLabel lblIstorijaZaduzenja = new StilIvice();
		lblIstorijaZaduzenja.setText("Istorija zaduženja");
		lblIstorijaZaduzenja.setIcon(new ImageIcon("slike\\evidencijaZaduzenja.png"));
		lblIstorijaZaduzenja.setHorizontalAlignment(SwingConstants.LEFT);
		lblIstorijaZaduzenja.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblIstorijaZaduzenja.setForeground(new Color(0xf4ece2));
		lblIstorijaZaduzenja.setBackground(new Color(102, 0, 0));
		lblIstorijaZaduzenja.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblIstorijaZaduzenja.setBounds(400, 10, 303, 47);
		panel.add(lblIstorijaZaduzenja);

		JButton dugmeNazadPrethodne = new NapraviOkrugloDugme("");
		dugmeNazadPrethodne.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (i > 5) {
					i--;
					izbrisiKnjige(pozadinskiPanel2);
					nacrtajKnjige();
				} else {
					dugmeNazad.disable();
				}
			}
		});
		pozadinskiPanel2.setLayout(null);
		dugmeNazadPrethodne.setIcon(new ImageIcon("slike\\nazad.png"));
		dugmeNazadPrethodne.setBounds(10, 81, 52, 52);
		dugmeNazadPrethodne.setBackground(new Color(0xf4ece2));
		pozadinskiPanel2.add(dugmeNazadPrethodne);

		nacrtajKnjige();
		JButton dugmeNapredPrethodne = new NapraviOkrugloDugme("");
		dugmeNapredPrethodne.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (i < listaDugmica.size()) {
					i++;
					izbrisiKnjige(pozadinskiPanel2);
					nacrtajKnjige();
				} else {
					dugmeNapred.disable();
				}
			}
		});
		dugmeNapredPrethodne.setIcon(new ImageIcon("slike\\napred.png"));
		dugmeNapredPrethodne.setBounds(1134, 81, 52, 52);
		dugmeNapredPrethodne.setBackground(new Color(0xf4ece2));
		pozadinskiPanel2.add(dugmeNapredPrethodne);

		for (JButton b : listaDugmica) {
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (b.getName().substring(0, 1).contains("!")) {
						int index = Integer.parseInt(b.getToolTipText());
						new ProzorProduzenjaDatuma(b.getName().substring(1), knjigaKontroler, ulogovaniKorisnik,
								clanKontroler, pozajmljeneKnjigeDTO, index);
					} else {
						pozadina.setVisible(false);
						new ProzorOcenjivanja(b.getName().substring(1), knjigaKontroler, ulogovaniKorisnik,
								clanKontroler, pozadina);
					}

				}
			});
		}

	}

	private void izbrisiKnjige(JPanel pozadinskiPanel) {
		for (JButton b : listaDugmica) {
			pozadinskiPanel.remove(b);
			pozadinskiPanel.repaint();
		}
		for (JLabel l : listaLabela) {
			pozadinskiPanel.remove(l);
			pozadinskiPanel.repaint();
		}
		listaDugmica.clear();
		listaLabela.clear();
	}

	private void nacrtajKnjige() {
		int xDugmePanel1 = 90;
		int xDugmePanel2 = 90;
		int brojPozajmica = 0;
		JPanel panelPozadine;
		int index = 0;
		for (int j = i - brojElemenata; j < i; j++) {
			int x;
			if (brojPozajmica == pozajmljeneKnjigeDTO.getListaPozajmljenihKnjiga().size()) {
				break;
			}
			if (pozajmljeneKnjigeDTO.getListaDatumaVracanja().get(j) == null) {
				x = xDugmePanel1;
				panelPozadine = pozadinskiPanel;
			} else {
				x = xDugmePanel2;
				panelPozadine = pozadinskiPanel2;
			}
			JButton datumKnjige = new JButton("");
			datumKnjige.setToolTipText(index + "");
			index++;
			if (pozajmljeneKnjigeDTO.getListaDatumaVracanja().get(j) == null) {
				datumKnjige.setName("!" + pozajmljeneKnjigeDTO.getListaPozajmljenihKnjiga().get(j).getISBN());
			} else {
				datumKnjige.setName(" " + pozajmljeneKnjigeDTO.getListaPozajmljenihKnjiga().get(j).getISBN());
			}
			datumKnjige.setIcon(
					new ImageIcon("slike\\" + pozajmljeneKnjigeDTO.getListaPozajmljenihKnjiga().get(j).getSlika()));
			datumKnjige.setBounds(x, 40, 120, 170);
			panelPozadine.add(datumKnjige);
			listaDugmica.add(datumKnjige);
			String nazivLabele = "<html> " + pozajmljeneKnjigeDTO.getListaPozajmljenihKnjiga().get(j).getNaslov()
					+ "<br/> ";
			for (Autor a : pozajmljeneKnjigeDTO.getListaPozajmljenihKnjiga().get(j).getAutori()) {
				nazivLabele += a.getIme() + " " + a.getPrezime() + "<br/>";
			}
			Format format = new SimpleDateFormat("dd.MM.yyyy.");
			nazivLabele += "Pozajmljeno: " + format.format(pozajmljeneKnjigeDTO.getListDatuma().get(j));
			JLabel lblNaslovKnjige = new JLabel(nazivLabele);
			lblNaslovKnjige.setForeground(new Color(102, 0, 0));
			lblNaslovKnjige.setFont(new Font("Times New Roman", Font.BOLD, 17));
			lblNaslovKnjige.setHorizontalAlignment(SwingConstants.CENTER);
			lblNaslovKnjige.setBounds(x, 220, 190, 61);
			panelPozadine.add(lblNaslovKnjige);
			listaLabela.add(lblNaslovKnjige);
			if (panelPozadine == pozadinskiPanel) {
				xDugmePanel1 += 240;
			} else {
				xDugmePanel2 += 240;
			}
			brojPozajmica++;
		}
	}

}
