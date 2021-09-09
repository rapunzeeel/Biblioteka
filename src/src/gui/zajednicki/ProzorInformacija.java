package gui.zajednicki;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Filijala;
import repos.BibliotekaRepo;

import javax.swing.SwingConstants;

public class ProzorInformacija {
	private JFrame pozadina;
	private ArrayList<JButton> listDugmica = new ArrayList<JButton>();
	private ArrayList<JLabel> listaLabela = new ArrayList<JLabel>();
	private BibliotekaRepo bibliotekaRepo;

	public ProzorInformacija(BibliotekaRepo bibliotekaRepo) {
		this.bibliotekaRepo = bibliotekaRepo;
		initialize();
	}

	private void initialize() {
		pozadina = new JFrame();
		pozadina.setVisible(true);
		pozadina.setResizable(false);
		pozadina.setTitle("Informacije o biblioteci");
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));
		pozadina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pozadina.setBounds(100, 100, 530, 400);
		pozadina.setBackground(new Color(0xeadbc8));
		pozadina.getContentPane().setLayout(null);
		pozadina.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pozadina.setVisible(false);
				new PocetniProzor();
			}
		});

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 550, 1000);
		panel.setForeground(new Color(127, 255, 0));
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);
		pozadina.getContentPane().add(panel);

		JPanel pozadinskiPanel = new JPanel();
		pozadinskiPanel.setBounds(22, 24, 476, 320);
		pozadinskiPanel.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel);
		pozadinskiPanel.setLayout(null);

		JLabel labelaLokacija = new JLabel("LOKACIJE");
		labelaLokacija.setBounds(20, 0, 130, 50);
		labelaLokacija.setForeground(new Color(102, 0, 0));
		labelaLokacija.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		labelaLokacija.setHorizontalAlignment(SwingConstants.LEFT);
		pozadinskiPanel.add(labelaLokacija);

		ispisInformacijaFilijale(bibliotekaRepo.getBiblioteka().getFilijale().get(0), pozadinskiPanel);

		int y = 50;
		for (Filijala f : bibliotekaRepo.getBiblioteka().getFilijale()) {
			JButton dugmeFilijale = new JButton("<html><u>" + f.getNaziv() + "</u></html>");
			dugmeFilijale.setName(f.getNaziv());
			dugmeFilijale.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
			dugmeFilijale.setForeground(new Color(0x2d5986));
			dugmeFilijale.setBackground(new Color(102, 0, 0));
			dugmeFilijale.setBorderPainted(false);
			dugmeFilijale.setOpaque(false);
			dugmeFilijale.setHorizontalAlignment(SwingConstants.LEFT);
			dugmeFilijale.setBounds(5, y, 200, 30);
			pozadinskiPanel.add(dugmeFilijale);
			listDugmica.add(dugmeFilijale);

			y += 25;
		}
		for (JButton b : listDugmica) {
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					for (Filijala f : bibliotekaRepo.getBiblioteka().getFilijale()) {
						if (f.getNaziv().equals(b.getName())) {
							ocistiInformacijeFilijale(pozadinskiPanel);
							ispisInformacijaFilijale(f, pozadinskiPanel);
						}
					}
				}
			});
		}
	}

	private void ispisInformacijaFilijale(Filijala f, JPanel panel) {
		JLabel labelaKontakt = new JLabel("KONTAKT");
		labelaKontakt.setBounds(250, 50, 130, 50);
		labelaKontakt.setForeground(new Color(102, 0, 0));
		labelaKontakt.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		labelaKontakt.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(labelaKontakt);

		JLabel labelaNazivFilijale = new JLabel("Filijala: " + f.getNaziv());
		labelaNazivFilijale.setBounds(250, 70, 220, 50);
		labelaNazivFilijale.setForeground(new Color(102, 0, 0));
		labelaNazivFilijale.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		labelaNazivFilijale.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(labelaNazivFilijale);

		JLabel labelaAdresaFilijale = new JLabel("Adresa: " + f.getAdresa());
		labelaAdresaFilijale.setBounds(250, 90, 250, 50);
		labelaAdresaFilijale.setForeground(new Color(102, 0, 0));
		labelaAdresaFilijale.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		labelaAdresaFilijale.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(labelaAdresaFilijale);

		JLabel labelaMesta = new JLabel("Mesto: " + f.getMesto().getPttBroj() + " " + f.getMesto().getNaziv());
		labelaMesta.setBounds(250, 110, 250, 50);
		labelaMesta.setForeground(new Color(102, 0, 0));
		labelaMesta.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		labelaMesta.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(labelaMesta);

		JLabel labelaRadnoVreme = new JLabel("RADNO VREME");
		labelaRadnoVreme.setBounds(250, 150, 130, 50);
		labelaRadnoVreme.setForeground(new Color(102, 0, 0));
		labelaRadnoVreme.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		labelaRadnoVreme.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(labelaRadnoVreme);

		JLabel labelaRadniDani = new JLabel("Radni dani: " + f.getRadniDan());
		labelaRadniDani.setBounds(250, 170, 180, 50);
		labelaRadniDani.setForeground(new Color(102, 0, 0));
		labelaRadniDani.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		labelaRadniDani.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(labelaRadniDani);

		JLabel labelaSubota = new JLabel("Subota: " + f.getSubota());
		labelaSubota.setBounds(250, 190, 180, 50);
		labelaSubota.setForeground(new Color(102, 0, 0));
		labelaSubota.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		labelaSubota.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(labelaSubota);

		JLabel labelaNedelja = new JLabel("Nedelja: neradan dan");
		labelaNedelja.setBounds(250, 210, 250, 50);
		labelaNedelja.setForeground(new Color(102, 0, 0));
		labelaNedelja.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		labelaNedelja.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(labelaNedelja);

		listaLabela.add(labelaNedelja);
		listaLabela.add(labelaSubota);
		listaLabela.add(labelaRadniDani);
		listaLabela.add(labelaAdresaFilijale);
		listaLabela.add(labelaMesta);
		listaLabela.add(labelaNazivFilijale);

		panel.repaint();
	}

	private void ocistiInformacijeFilijale(JPanel panel) {
		for (JLabel l : listaLabela) {
			panel.remove(l);
			panel.repaint();
		}
		listaLabela.clear();
	}
}
