package gui.bibliotekar;

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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gui.pomocniElementi.StilIvice;
import gui.pomocniElementi.StilTekstPolja;
import kontroler.KnjigaKontroler;
import model.Knjiga;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ProzorIzmeneKnjige {
	private JFrame pozadina;
	private KnjigaKontroler knjigaKontroler;
	private JLabel lblUspesnoProduzeno;
	private JFrame glavniProzor;
	private Knjiga knjiga;
	private JTextArea txtAreaOpis;
	private JTextArea txtAreaKljucneReci;
	private JTextField unosSlika;

	public ProzorIzmeneKnjige(KnjigaKontroler knjigaKontroler,JFrame glavniProzor, Knjiga knjiga) {
		this.glavniProzor = glavniProzor;
		this.knjiga = knjiga;
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
		pozadina.setBounds(80, 70, 634, 620);
		pozadina.setVisible(true);
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));

		JPanel panel = new JPanel();
		pozadina.getContentPane().add(panel, BorderLayout.CENTER);
		pozadina.setBackground(Color.WHITE);
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);

		JLabel labelaIzmena = new StilIvice();
		labelaIzmena.setHorizontalAlignment(SwingConstants.LEFT);
		labelaIzmena.setIcon(new ImageIcon("slike\\knjigaISBN.png"));
		labelaIzmena.setBounds(179, 10, 276, 60);
		panel.add(labelaIzmena);
		labelaIzmena.setFont(new Font("Times New Roman", Font.BOLD, 30));
		labelaIzmena.setForeground(new Color(0xf4ece2));
		labelaIzmena.setBackground(new Color(102, 0, 0));

		JPanel pozadinskiPanel = new JPanel();
		pozadinskiPanel.setBounds(20, 31, 588, 540);
		pozadinskiPanel.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel);
		pozadinskiPanel.setLayout(null);

		lblUspesnoProduzeno = new JLabel(":) Uspešno ste izmenili knjigu!");
		lblUspesnoProduzeno.setForeground(new Color(0, 102, 0));
		lblUspesnoProduzeno.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		lblUspesnoProduzeno.setBounds(223, 500, 256, 34);
		lblUspesnoProduzeno.setVisible(false);
		pozadinskiPanel.add(lblUspesnoProduzeno);
		
		JLabel lblOpis = new JLabel("Opis");
		lblOpis.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOpis.setForeground(new Color(102, 0, 0));
		lblOpis.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblOpis.setBounds(111, 80, 61, 37);
		pozadinskiPanel.add(lblOpis);
        
        JLabel lblKljucneReci = new JLabel("Klučne reči");
        lblKljucneReci.setHorizontalAlignment(SwingConstants.RIGHT);
        lblKljucneReci.setForeground(new Color(102, 0, 0));
        lblKljucneReci.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblKljucneReci.setBounds(71, 240, 101, 37);
        pozadinskiPanel.add(lblKljucneReci);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(182, 83, 358, 124);
        pozadinskiPanel.add(scrollPane);
        
        txtAreaOpis = new JTextArea();
        scrollPane.setViewportView(txtAreaOpis);
        txtAreaOpis.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        txtAreaOpis.setForeground(new Color(102, 0, 0));
        txtAreaOpis.setBackground(new Color(0xf4ece2));
        txtAreaOpis.setWrapStyleWord(true);
        txtAreaOpis.setLineWrap(true);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(185, 243, 356, 106);
        pozadinskiPanel.add(scrollPane_1);
        
        txtAreaKljucneReci = new JTextArea();
        scrollPane_1.setViewportView(txtAreaKljucneReci);
        txtAreaKljucneReci.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        txtAreaKljucneReci.setForeground(new Color(102, 0, 0));
        txtAreaKljucneReci.setBackground(new Color(0xf4ece2));
        txtAreaKljucneReci.setWrapStyleWord(true);
        txtAreaKljucneReci.setLineWrap(true);
        
        JLabel lblSlika = new JLabel("Slika");
        lblSlika.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSlika.setForeground(new Color(102, 0, 0));
        lblSlika.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblSlika.setBounds(111, 377, 61, 37);
        pozadinskiPanel.add(lblSlika);
        
        unosSlika = new StilTekstPolja(200);
        unosSlika.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        unosSlika.setForeground(new Color(102, 0, 0));
        unosSlika.setBounds(186, 377, 354, 37);
        pozadinskiPanel.add(unosSlika);
        unosSlika.setColumns(10);
        
        JButton dugmePotvrde = new JButton("");
        dugmePotvrde.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					knjigaKontroler.izmeniKnjigu(knjiga.getISBN(), txtAreaOpis.getText(), txtAreaKljucneReci.getText(), unosSlika.getText());
					lblUspesnoProduzeno.setVisible(true);
        		} catch (SQLException e1) {
					e1.printStackTrace();
				}
        	}
        });
        dugmePotvrde.setForeground(new Color(244, 236, 226));
        dugmePotvrde.setFont(new Font("Times New Roman", Font.BOLD, 30));
        dugmePotvrde.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
        dugmePotvrde.setBackground(new Color(102, 0, 0));
        dugmePotvrde.setBounds(213, 450, 277, 45);
        pozadinskiPanel.add(dugmePotvrde);
	
		pozadina.setTitle("Izmena knjige");
		labelaIzmena.setText("Izmena knjige");
		dugmePotvrde.setText("Izmeni knjigu");
		
		JLabel ikonicaSlika = new JLabel("");
		ikonicaSlika.setIcon(new ImageIcon("slike\\slika.png"));
		ikonicaSlika.setBounds(71, 369, 46, 45);
		pozadinskiPanel.add(ikonicaSlika);
		
		JLabel ikonicaReci = new JLabel("");
		ikonicaReci.setIcon(new ImageIcon("slike\\kljucneReci.png"));
		ikonicaReci.setBounds(22, 229, 46, 45);
		pozadinskiPanel.add(ikonicaReci);
		
		JLabel ikonicaOpis = new JLabel("");
		ikonicaOpis.setIcon(new ImageIcon("slike\\opis.png"));
		ikonicaOpis.setBounds(73, 73, 46, 45);
		pozadinskiPanel.add(ikonicaOpis);
		
		popuni();

	}

	private void popuni() {
		txtAreaOpis.setText(knjiga.getOpis());
		txtAreaKljucneReci.setText(knjiga.getKljucneReci());
		unosSlika.setText(knjiga.getSlika());
	}
}
