package gui.zajednicki;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gui.pomocniElementi.StilDugmeta;

public class ProzorObavestenjaKorisnika {
	private JFrame pozadina;
	private String poruka;

	public ProzorObavestenjaKorisnika(String poruka) {
		this.poruka = poruka;
		initialize();
	}

	private void initialize() {
		pozadina = new JFrame();
		pozadina.setBounds(100, 100, 450, 300);
		pozadina.setSize(368, 181);
		pozadina.setTitle("Obaveštenje");
		pozadina.setVisible(true);
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));
		pozadina.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pozadina.setVisible(false);
				pozadina.dispose();
			}
		});
		pozadina.setAlwaysOnTop(true);

		JPanel panel = new JPanel();
		pozadina.getContentPane().add(panel, BorderLayout.CENTER);
		pozadina.setBackground(Color.WHITE);
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);

		JPanel pozadinskiPanel1 = new JPanel();
		pozadinskiPanel1.setBounds(10, 11, 332, 120);
		pozadinskiPanel1.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel1);
		pozadinskiPanel1.setLayout(null);

		JButton dugmePotvrde = new JButton("OK");
		dugmePotvrde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pozadina.setVisible(false);
				pozadina.dispose();
			}
		});
		dugmePotvrde.setLocation(109, 63);
		dugmePotvrde.setSize(127, 42);
		dugmePotvrde.setBackground(new Color(102, 0, 0));
		dugmePotvrde.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmePotvrde.setUI(new StilDugmeta());
		dugmePotvrde.setFont(new Font("Times New Roman", Font.BOLD, 25));
		dugmePotvrde.setForeground(new Color(0xf4ece2));
		pozadinskiPanel1.add(dugmePotvrde);
		
		JLabel lblObavestenje = new JLabel(poruka);
		lblObavestenje.setHorizontalAlignment(SwingConstants.CENTER);
		lblObavestenje.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblObavestenje.setBounds(10, 11, 327, 41);
		lblObavestenje.setForeground(new Color(102, 0, 0));
		pozadinskiPanel1.add(lblObavestenje);
	}
}
