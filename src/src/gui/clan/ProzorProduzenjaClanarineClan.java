package gui.clan;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import exception.ClanarinaUplacenaException;
import exception.IsteklaClanarinaException;
import exception.NijePronadjenClanExcepiton;
import gui.pomocniElementi.StilDugmeta;
import gui.pomocniElementi.StilIvice;
import kontroler.BibliotekaKontroler;
import kontroler.ClanKontroler;
import observer.Observer;
import observer.RepositoryUpdateEvent;

public class ProzorProduzenjaClanarineClan implements Observer {
	private JFrame pozadina;
	private JLabel lblObavestenje;
	private JLabel lblUspesnoProduzeno;
	private JLabel lblGreska;
	private BibliotekaKontroler bibliotekaKontroler;
	private String ulogovaniKorisnik;
	private JFrame prethodniProzor;
	private ClanKontroler clanKontroler;
	private Date datumVazenja;

	public ProzorProduzenjaClanarineClan(ClanKontroler clanKontroler, BibliotekaKontroler bibliotekaKontroler,
			String ulogovaniKorisnik, JFrame prethodniProzor) {
		this.bibliotekaKontroler = bibliotekaKontroler;
		this.ulogovaniKorisnik = ulogovaniKorisnik;
		this.clanKontroler = clanKontroler;
		this.prethodniProzor = prethodniProzor;
		this.clanKontroler.getClanRepo().addObserver(this);
		initialize();
	}

	private void initialize() {
		pozadina = new JFrame();
		pozadina.setVisible(true);
		pozadina.setResizable(false);
		pozadina.setTitle("Produženje članarine");
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));
		pozadina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pozadina.setBounds(100, 100, 468, 263);
		pozadina.setBackground(new Color(0xeadbc8));
		pozadina.getContentPane().setLayout(null);
		pozadina.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pozadina.setVisible(false);
				prethodniProzor.setVisible(true);
				pozadina.dispose();
			}
		});

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 526, 1000);
		panel.setForeground(new Color(127, 255, 0));
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);
		pozadina.getContentPane().add(panel);

		JLabel lblProduzenjeClanarine = new StilIvice();
		lblProduzenjeClanarine.setHorizontalAlignment(SwingConstants.LEFT);
		lblProduzenjeClanarine.setText("Produženje članarine");
		lblProduzenjeClanarine.setIcon(new ImageIcon("slike\\novac.png"));
		lblProduzenjeClanarine.setBounds(66, 11, 335, 52);
		panel.add(lblProduzenjeClanarine);
		lblProduzenjeClanarine.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblProduzenjeClanarine.setForeground(new Color(0xf4ece2));
		lblProduzenjeClanarine.setBackground(new Color(102, 0, 0));
		
		JPanel pozadinskiPanel = new JPanel();
		pozadinskiPanel.setBounds(22, 24, 407, 185);
		pozadinskiPanel.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel);
		pozadinskiPanel.setLayout(null);

		lblObavestenje = new JLabel();
		lblObavestenje.setForeground(new Color(102, 0, 0));
		lblObavestenje.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblObavestenje.setBounds(48, 55, 344, 34);
		pozadinskiPanel.add(lblObavestenje);

		proveriDatumVazenjaClanarine();

		JButton dugmeProduzi = new JButton("Produži članarinu");
		dugmeProduzi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblGreska.setVisible(false);
					bibliotekaKontroler.produziClanarinu(ulogovaniKorisnik);
					lblUspesnoProduzeno.setVisible(true);
				} catch (NijePronadjenClanExcepiton e1) {
					lblUspesnoProduzeno.setVisible(false);
					lblGreska.setText(e1.getValueName());
					lblGreska.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClanarinaUplacenaException e1) {
					lblUspesnoProduzeno.setVisible(false);
					lblGreska.setText(e1.getValueName());
					lblGreska.setVisible(true);
				}
			}
		});
		dugmeProduzi.setBackground(new Color(102, 0, 0));
		dugmeProduzi.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmeProduzi.setUI(new StilDugmeta());
		dugmeProduzi.setFont(new Font("Times New Roman", Font.BOLD, 20));
		dugmeProduzi.setForeground(new Color(0xf4ece2));
		dugmeProduzi.setBounds(107, 98, 217, 45);
		pozadinskiPanel.add(dugmeProduzi);

		lblUspesnoProduzeno = new JLabel(":) Uspešno ste produžili članarinu!");
		lblUspesnoProduzeno.setForeground(new Color(0, 102, 0));
		lblUspesnoProduzeno.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblUspesnoProduzeno.setBounds(91, 154, 266, 34);
		lblUspesnoProduzeno.setVisible(false);
		pozadinskiPanel.add(lblUspesnoProduzeno);

		lblGreska = new JLabel();
		lblGreska.setForeground(new Color(102, 0, 0));
		lblGreska.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblGreska.setBounds(131, 154, 177, 34);
		lblGreska.setVisible(false);
		pozadinskiPanel.add(lblGreska);

	}

	private void proveriDatumVazenjaClanarine() {
		Format format = new SimpleDateFormat("dd.MM.yyyy.");
		try {
			clanKontroler.proveriDatumClanarine(ulogovaniKorisnik);
			lblObavestenje.setText("Vaša članarina traje do: " + format.format(datumVazenja));
			lblObavestenje.setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IsteklaClanarinaException e) {
			lblObavestenje.setText("Vaša članarina je istekla: " + format.format(datumVazenja));
			lblObavestenje.setVisible(true);
		}
	}

	@Override
	public void updatePerformed(RepositoryUpdateEvent event) {
		datumVazenja = (Date) event.getAkcija();

	}

}
