package gui.bibliotekar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import exception.MaxBrojPozajmicaException;
import exception.NijePronadjenClanExcepiton;
import gui.pomocniElementi.StilDugmeta;
import gui.pomocniElementi.StilIvice;
import gui.pomocniElementi.StilTekstPolja;
import kontroler.BibliotekaKontroler;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ProzorEvidencijePozajmica {
	private JFrame pozadina;
	private JFrame glavniProzor;
	private JTextField unosEmailaClana;
	private JLabel lblObavestenje;
	private JPanel pozadinskiPanel;
	private JPanel panel;
	private BibliotekaKontroler bibliotekaKontroler;

	public ProzorEvidencijePozajmica(BibliotekaKontroler bibliotekaController, JFrame glavniProzor) {
		this.bibliotekaKontroler = bibliotekaController;
		this.glavniProzor = glavniProzor;
		initialize();
	}

	private void initialize() {
		pozadina = new JFrame();
		pozadina.setVisible(true);
		pozadina.setResizable(false);
		pozadina.setTitle("Evidencija pozajmica");
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));
		pozadina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pozadina.setBounds(100, 100, 530, 219);
		pozadina.setBackground(new Color(0xeadbc8));
		pozadina.getContentPane().setLayout(null);
		pozadina.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pozadina.setVisible(false);
				glavniProzor.setVisible(true);
			}
		});

		panel = new JPanel();
		panel.setBounds(0, 0, 522, 191);
		panel.setForeground(new Color(127, 255, 0));
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);
		pozadina.getContentPane().add(panel);

		JLabel lblEvidencijaPoz = new StilIvice();
		lblEvidencijaPoz.setText("Evidencija pozajmice");
		lblEvidencijaPoz.setIcon(new ImageIcon("slike\\evidencijaZaduzenja.png"));
		lblEvidencijaPoz.setHorizontalAlignment(SwingConstants.LEFT);
		lblEvidencijaPoz.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblEvidencijaPoz.setForeground(new Color(0xf4ece2));
		lblEvidencijaPoz.setBackground(new Color(102, 0, 0));
		lblEvidencijaPoz.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblEvidencijaPoz.setBounds(109, 7, 334, 47);
		panel.add(lblEvidencijaPoz);

		pozadinskiPanel = new JPanel();
		pozadinskiPanel.setBounds(22, 21, 483, 151);
		pozadinskiPanel.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel);
		pozadinskiPanel.setLayout(null);

		lblObavestenje = new JLabel("");
		lblObavestenje.setBounds(67, 126, 393, 26);
		lblObavestenje.setForeground(new Color(0, 102, 0));
		lblObavestenje.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		pozadinskiPanel.add(lblObavestenje);
		lblObavestenje.setVisible(false);

		unosEmailaClana = new StilTekstPolja(200);
		unosEmailaClana.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				unosEmailaClana.setText("");
			}
		});
		unosEmailaClana.setText("Email člana");
		unosEmailaClana.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		unosEmailaClana.setForeground(new Color(102, 0, 0));
		unosEmailaClana.setBounds(87, 70, 193, 45);
		pozadinskiPanel.add(unosEmailaClana);
		unosEmailaClana.setColumns(10);

		JButton dugmeIznajmi = new JButton("Iznajmi knjigu");
		dugmeIznajmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					bibliotekaKontroler.nadjiClana(unosEmailaClana.getText());
					bibliotekaKontroler.proveriMogucnostZaduzenja(unosEmailaClana.getText());
					pozadina.setBounds(100, 100, 530, 400);
					panel.setBounds(0, 0, 522, 375);
					pozadinskiPanel.setBounds(22, 21, 483, 335);
					lblObavestenje.setVisible(false);
					new ProzorIznajmljivanjaKnjige(bibliotekaKontroler, glavniProzor, unosEmailaClana.getText());

				} catch (NijePronadjenClanExcepiton e1) {
					lblObavestenje.setText(e1.getValueName());
					lblObavestenje.setForeground(new Color(102, 0, 0));
					lblObavestenje.setVisible(true);
				} catch (MaxBrojPozajmicaException e1) {
					lblObavestenje.setText(e1.getValueName());
					lblObavestenje.setForeground(new Color(102, 0, 0));
					lblObavestenje.setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		dugmeIznajmi.setBounds(315, 50, 158, 34);
		dugmeIznajmi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		dugmeIznajmi.setBackground(new Color(102, 0, 0));
		dugmeIznajmi.setForeground(new Color(0xf4ece2));
		dugmeIznajmi.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmeIznajmi.setUI(new StilDugmeta());
		pozadinskiPanel.add(dugmeIznajmi);

		JButton dugmeVrati = new JButton("Vrati knjigu");
		dugmeVrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblObavestenje.setVisible(false);
					bibliotekaKontroler.nadjiClana(unosEmailaClana.getText());
					new ProzorVracanjaKnjige(bibliotekaKontroler, unosEmailaClana.getText());
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (NijePronadjenClanExcepiton e1) {
					lblObavestenje.setText(e1.getValueName());
					lblObavestenje.setForeground(new Color(102, 0, 0));
					lblObavestenje.setVisible(true);
				}
			}
		});
		dugmeVrati.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		dugmeVrati.setBackground(new Color(102, 0, 0));
		dugmeVrati.setForeground(new Color(0xf4ece2));
		dugmeVrati.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmeVrati.setUI(new StilDugmeta());
		dugmeVrati.setBounds(315, 95, 158, 34);
		pozadinskiPanel.add(dugmeVrati);

		JLabel slikaEmail = new JLabel("");
		slikaEmail.setIcon(new ImageIcon("slike\\email.png"));
		slikaEmail.setBounds(30, 70, 46, 45);
		pozadinskiPanel.add(slikaEmail);

	}
}
