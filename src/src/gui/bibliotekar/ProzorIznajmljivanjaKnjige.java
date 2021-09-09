package gui.bibliotekar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import dto.PozajmiceDTO;
import dto.ZahtevRezervacijeDTO;
import exception.MaxBrojPozajmicaException;
import exception.NijePronadjenClanExcepiton;
import exception.NijePronadjenjaKnjigaException;
import exception.NijePronadjenjaPrimerakException;
import exception.SviPrimerciSlobodniException;
import gui.pomocniElementi.StilDugmeta;
import gui.pomocniElementi.StilIvice;
import gui.pomocniElementi.StilTekstPolja;
import kontroler.BibliotekaKontroler;
import observer.Observer;
import observer.RepositoryUpdateEvent;
import utils.ZaduzeneKnjigeClanaTableModel;
import utils.ZahteviTableModel;

public class ProzorIznajmljivanjaKnjige {
	private JFrame pozadina;
	private JFrame glavniProzor;
	private JLabel lblUspesno;
	private JTextField unosISBN;
	private JLabel slikaISBN;
	private JLabel lblObavestenje;
	private JPanel pozadinskiPanel;
	private JPanel panel;
	private BibliotekaKontroler bibliotekaKontroler;
	private String emailClana;

	public ProzorIznajmljivanjaKnjige(BibliotekaKontroler bibliotekaController, JFrame glavniProzor, String emailClana) {
		this.bibliotekaKontroler = bibliotekaController;
		this.glavniProzor = glavniProzor;
		this.emailClana = emailClana;
		initialize();
	}

	private void initialize() {
		pozadina = new JFrame();
		pozadina.setVisible(true);
		pozadina.setResizable(false);
		pozadina.setTitle("Iznajmljivanje knjige");
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));
		pozadina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pozadina.setBounds(100, 100, 530, 214);
		pozadina.setBackground(new Color(0xeadbc8));
		pozadina.getContentPane().setLayout(null);
		pozadina.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pozadina.setVisible(false);
			}
		});

		panel = new JPanel();
		panel.setBounds(0, 0, 522, 375);
		panel.setForeground(new Color(127, 255, 0));
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);
		pozadina.getContentPane().add(panel);

		JLabel lblEvidencijaPoz = new StilIvice();
		lblEvidencijaPoz.setText("Iznajmljivanje knjige");
		lblEvidencijaPoz.setIcon(new ImageIcon("slike\\knjigaISBN.png"));
		lblEvidencijaPoz.setHorizontalAlignment(SwingConstants.LEFT);
		lblEvidencijaPoz.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblEvidencijaPoz.setForeground(new Color(0xf4ece2));
		lblEvidencijaPoz.setBackground(new Color(102, 0, 0));
		lblEvidencijaPoz.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblEvidencijaPoz.setBounds(109, 7, 334, 47);
		panel.add(lblEvidencijaPoz);

		pozadinskiPanel = new JPanel();
		pozadinskiPanel.setBounds(22, 21, 483, 145);
		pozadinskiPanel.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel);
		pozadinskiPanel.setLayout(null);

		slikaISBN = new JLabel("");
		slikaISBN.setIcon(new ImageIcon("slike\\knjigaISBN.png"));
		slikaISBN.setBounds(34, 45, 46, 45);

		lblObavestenje = new JLabel("");
		lblObavestenje.setBounds(87, 95, 323, 26);
		lblObavestenje.setForeground(new Color(0, 102, 0));
		lblObavestenje.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		pozadinskiPanel.add(lblObavestenje);
		lblObavestenje.setVisible(false);
		pozadinskiPanel.add(slikaISBN);

		JButton dugmeIznajmi = new JButton("Iznajmi knjigu");
		dugmeIznajmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				potvrdaIznajmljivanja();
			}
		});
		dugmeIznajmi.setBounds(315, 50, 158, 34);
		dugmeIznajmi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		dugmeIznajmi.setBackground(new Color(102, 0, 0));
		dugmeIznajmi.setForeground(new Color(0xf4ece2));
		dugmeIznajmi.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmeIznajmi.setUI(new StilDugmeta());
		pozadinskiPanel.add(dugmeIznajmi);

		unosISBN = new StilTekstPolja(200);
		unosISBN.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				unosISBN.setText("");
			}
		});
		unosISBN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				potvrdaIznajmljivanja();
			}
		});
		unosISBN.setText("ISBN knjige");
		unosISBN.setForeground(new Color(102, 0, 0));
		unosISBN.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		unosISBN.setForeground(new Color(102, 0, 0));
		unosISBN.setBounds(87, 45, 193, 45);
		pozadinskiPanel.add(unosISBN);
		unosISBN.setColumns(10);

		lblUspesno = new JLabel(":) Uspešno ste izdali knjigu!");
		lblUspesno.setForeground(new Color(0, 102, 0));
		lblUspesno.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblUspesno.setBounds(87, 95, 256, 26);
		lblUspesno.setVisible(false);
		pozadinskiPanel.add(lblUspesno);

	}

	private void potvrdaIznajmljivanja() {
		lblUspesno.setVisible(false);
		lblObavestenje.setVisible(false);
		try {
			lblObavestenje.setVisible(false);
			lblObavestenje.setForeground(new Color(0, 102, 0));
			bibliotekaKontroler.nadjiKnjigu(unosISBN.getText());
			bibliotekaKontroler.proveriMogucnostZaduzenja(emailClana); 
			bibliotekaKontroler.dodajNovuPozajmicu(emailClana, unosISBN.getText());
			lblUspesno.setVisible(true);
			unosISBN.setText("ISBN knjige");
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (NijePronadjenjaKnjigaException e1) {
			pozadina.repaint();
			lblObavestenje.setText(e1.getValueName());
			lblObavestenje.setForeground(new Color(102, 0, 0));
			lblObavestenje.setVisible(true);
		} catch (NijePronadjenjaPrimerakException e1) {
			pozadina.repaint();
			lblObavestenje.setText(e1.getValueName());
			lblObavestenje.setForeground(new Color(102, 0, 0));
			lblObavestenje.setVisible(true);
		}catch (MaxBrojPozajmicaException e) {
			lblObavestenje.setText(e.getValueName());
			lblObavestenje.setForeground(new Color(102, 0, 0));
			lblObavestenje.setVisible(true);
		}
	}
}
