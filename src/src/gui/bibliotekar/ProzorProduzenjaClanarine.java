package gui.bibliotekar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

import dto.RegistracijaDTO;
import exception.ClanarinaUplacenaException;
import exception.NeispravanJmbgException;
import exception.NepostojeciPostanskiBrojException;
import exception.NijePronadjenClanExcepiton;
import exception.PostojeciEmailException;
import exception.PraznoAdresaException;
import exception.PraznoImeException;
import exception.PraznoPrezimeException;
import gui.pomocniElementi.StilDugmeta;
import gui.pomocniElementi.StilIvice;
import gui.pomocniElementi.StilTekstPolja;
import kontroler.BibliotekaKontroler;
import model.TipClana;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProzorProduzenjaClanarine {
	private JFrame pozadina;
	private JFrame glavniProzor;
	private JTextField unosEmail;
	private int prviPutKliknuoEmail = 0;
	private JLabel lblGreska;
	private JLabel lblUspesnoProduzeno;
	private BibliotekaKontroler bibliotekaKontroler;

	public ProzorProduzenjaClanarine(BibliotekaKontroler bibliotekaController, JFrame glavniProzor) {
		this.bibliotekaKontroler = bibliotekaController;
		this.glavniProzor = glavniProzor;
		initialize();
	}

	private void initialize() {
		pozadina = new JFrame();
		pozadina.setVisible(true);
		pozadina.setResizable(false);
		pozadina.setTitle("Produženje članarine");
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));
		pozadina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pozadina.setBounds(100, 100, 530, 250);
		pozadina.setBackground(new Color(0xeadbc8));
		pozadina.getContentPane().setLayout(null);
		pozadina.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pozadina.setVisible(false);
				pozadina.dispose();
				glavniProzor.setVisible(true);
			}
		});

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 530, 250);
		panel.setForeground(new Color(127, 255, 0));
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);
		pozadina.getContentPane().add(panel);

		JLabel lblProduzenjeClanarine = new StilIvice();
		lblProduzenjeClanarine.setHorizontalAlignment(SwingConstants.LEFT);
		lblProduzenjeClanarine.setText("Produženje članarine");
		lblProduzenjeClanarine.setIcon(new ImageIcon("slike\\novac.png"));
		lblProduzenjeClanarine.setBounds(95, 11, 335, 52);
		panel.add(lblProduzenjeClanarine);
		lblProduzenjeClanarine.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblProduzenjeClanarine.setForeground(new Color(0xf4ece2));
		lblProduzenjeClanarine.setBackground(new Color(102, 0, 0));

		JPanel pozadinskiPanel = new JPanel();
		pozadinskiPanel.setBounds(22, 24, 476, 178);
		pozadinskiPanel.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel);
		pozadinskiPanel.setLayout(null);

		unosEmail = new StilTekstPolja(200);
		unosEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				potvrdaProduzenja();
			}
		});
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
		unosEmail.setBounds(73, 64, 256, 47);
		pozadinskiPanel.add(unosEmail);
		unosEmail.setColumns(10);

		JLabel ikonicaEmail = new JLabel("");
		ikonicaEmail.setIcon(new ImageIcon("slike\\email.png"));
		ikonicaEmail.setBounds(21, 66, 46, 45);
		pozadinskiPanel.add(ikonicaEmail);

		lblGreska = new JLabel();
		lblGreska.setHorizontalAlignment(SwingConstants.CENTER);
		lblGreska.setForeground(new Color(102, 0, 0));
		lblGreska.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblGreska.setBounds(43, 120, 350, 34);
		lblGreska.setVisible(false);
		pozadinskiPanel.add(lblGreska);

		lblUspesnoProduzeno = new JLabel(":) Uspešno ste produžili članarinu!");
		lblUspesnoProduzeno.setForeground(new Color(0, 102, 0));
		lblUspesnoProduzeno.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblUspesnoProduzeno.setBounds(88, 120, 289, 34);
		lblUspesnoProduzeno.setVisible(false);
		pozadinskiPanel.add(lblUspesnoProduzeno);
		
		JButton dugmePotvrde = new JButton("Produži");
		dugmePotvrde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				potvrdaProduzenja();

			}
		});
		dugmePotvrde.setBackground(new Color(102, 0, 0));
		dugmePotvrde.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmePotvrde.setUI(new StilDugmeta());
		dugmePotvrde.setFont(new Font("Times New Roman", Font.BOLD, 20));
		dugmePotvrde.setForeground(new Color(0xf4ece2));
		dugmePotvrde.setBounds(339, 66, 127, 39);
		pozadinskiPanel.add(dugmePotvrde);

	}

	private void potvrdaProduzenja() {
		try {
			lblGreska.setVisible(false);
			bibliotekaKontroler.produziClanarinu(unosEmail.getText());
			lblUspesnoProduzeno.setVisible(true);
		} catch (NijePronadjenClanExcepiton e1) {
			lblUspesnoProduzeno.setVisible(false);
			lblGreska.setText(e1.getValueName());
			lblGreska.setVisible(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (ClanarinaUplacenaException e1) {
			lblUspesnoProduzeno.setVisible(false);
			lblGreska.setText(e1.getValueName());
			lblGreska.setVisible(true);
		}
	}

}
