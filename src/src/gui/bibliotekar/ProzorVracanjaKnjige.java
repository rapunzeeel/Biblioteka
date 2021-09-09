package gui.bibliotekar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import dto.PozajmiceDTO;
import gui.pomocniElementi.StilDugmeta;
import gui.pomocniElementi.StilIvice;
import gui.zajednicki.ProzorObavestenjaKorisnika;
import kontroler.BibliotekaKontroler;
import observer.Observer;
import observer.RepositoryUpdateEvent;
import utils.ZaduzeneKnjigeClanaTableModel;

public class ProzorVracanjaKnjige implements Observer {
	private JFrame pozadina;
	private ArrayList<PozajmiceDTO> rezultatPozajmice = new ArrayList<>();
	private ZaduzeneKnjigeClanaTableModel tableModel;
	private JTable table;
	private TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	private JScrollPane scrollPane;
	private JButton dugmePotvrdi;
	private JLabel lblObavestenje;
	private JCheckBox checkBoxOstecena;
	private JPanel pozadinskiPanel;
	private JPanel panel;
	private BibliotekaKontroler bibliotekaKontroler;
	private String emailClana;

	public ProzorVracanjaKnjige(BibliotekaKontroler bibliotekaController, String emailClana) {
		this.bibliotekaKontroler = bibliotekaController;
		this.emailClana = emailClana;
		this.bibliotekaKontroler.getPozajmicaRepo().addObserver(this);
		initialize();
	}

	private void initialize() {

		pozadina = new JFrame();
		pozadina.setVisible(true);
		pozadina.setResizable(false);
		pozadina.setTitle("Evidencija pozajmica");
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));
		pozadina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pozadina.setBounds(100, 100, 704, 455);
		pozadina.setBackground(new Color(0xeadbc8));
		pozadina.getContentPane().setLayout(null);
		pozadina.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pozadina.setVisible(false);
				pozadina.dispose();
			}
		});

		panel = new JPanel();
		panel.setBounds(0, 0, 697, 438);
		panel.setForeground(new Color(127, 255, 0));
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);
		pozadina.getContentPane().add(panel);

		JLabel lblEvidencijaPoz = new StilIvice();
		lblEvidencijaPoz.setText("Vraćanje knjige");
		lblEvidencijaPoz.setIcon(new ImageIcon("slike\\evidencijaZaduzenja.png"));
		lblEvidencijaPoz.setHorizontalAlignment(SwingConstants.LEFT);
		lblEvidencijaPoz.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblEvidencijaPoz.setForeground(new Color(0xf4ece2));
		lblEvidencijaPoz.setBackground(new Color(102, 0, 0));
		lblEvidencijaPoz.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblEvidencijaPoz.setBounds(199, 11, 334, 47);
		panel.add(lblEvidencijaPoz);

		pozadinskiPanel = new JPanel();
		pozadinskiPanel.setBounds(22, 21, 657, 394);
		pozadinskiPanel.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel);
		pozadinskiPanel.setLayout(null);

		lblObavestenje = new JLabel("");
		lblObavestenje.setBounds(340, 362, 305, 26);
		lblObavestenje.setForeground(new Color(0, 102, 0));
		lblObavestenje.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		pozadinskiPanel.add(lblObavestenje);
		lblObavestenje.setVisible(false);

		dugmePotvrdi = new JButton("Potvrdi vraćanje");
		dugmePotvrdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int redTabele = table.getSelectedRow();

				if (redTabele != -1) {
					int index = table.convertRowIndexToModel(redTabele);
					PozajmiceDTO pozajmica = (PozajmiceDTO) ((ZaduzeneKnjigeClanaTableModel) table.getModel()).getRows()
							.get(index);
					try {
						Boolean ostecena = checkBoxOstecena.isSelected();
						bibliotekaKontroler.evidentirajVracanjePozajmice(pozajmica, emailClana, ostecena);
						bibliotekaKontroler.nadjiPozajmiceClana(emailClana);
						lblObavestenje.setText("Vraćanje knjige je uspešno evidentirano.");

						pozadina.repaint();
						lblObavestenje.setVisible(true);
						lblObavestenje.setForeground(new Color(0, 102, 0));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		dugmePotvrdi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		dugmePotvrdi.setBackground(new Color(102, 0, 0));
		dugmePotvrdi.setForeground(new Color(0xf4ece2));
		dugmePotvrdi.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmePotvrdi.setUI(new StilDugmeta());
		dugmePotvrdi.setBounds(475, 317, 170, 34);
		dugmePotvrdi.setVisible(false);
		pozadinskiPanel.add(dugmePotvrdi);

		checkBoxOstecena = new JCheckBox("Oštećena");
		checkBoxOstecena.setForeground(new Color(102, 0, 0));
		checkBoxOstecena.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		checkBoxOstecena.setBounds(340, 323, 129, 23);
		pozadinskiPanel.add(checkBoxOstecena);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 635, 244);
		pozadinskiPanel.add(scrollPane);
		scrollPane.setVisible(false);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setVisible(false);
		checkBoxOstecena.setVisible(false);
		try {
			bibliotekaKontroler.nadjiPozajmiceClana(emailClana);
			if (rezultatPozajmice.size() == 0) {
				pozadina.setVisible(false);
				new ProzorObavestenjaKorisnika("Odabrani član trenutno nema pozajmica.");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void updatePerformed(RepositoryUpdateEvent event) {
		pozadina.setBounds(100, 100, 530, 400);
		panel.setBounds(0, 0, 522, 375);
		pozadinskiPanel.setBounds(22, 21, 483, 335);
		scrollPane.setVisible(false);
		table.setVisible(false);
		dugmePotvrdi.setVisible(false);
		checkBoxOstecena.setVisible(false);

		rezultatPozajmice = (ArrayList<PozajmiceDTO>) event.getAkcija();
		tableModel = new ZaduzeneKnjigeClanaTableModel(rezultatPozajmice);

		if (rezultatPozajmice.size() != 0) {
			table.setModel(tableModel);
			table.getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 22));

			table.getTableHeader().setBackground(new Color(102, 0, 0));
			table.getTableHeader().setForeground(new Color(0xf4ece2));
			table.getColumnModel().getColumn(0).setPreferredWidth(155);
			table.getColumnModel().getColumn(1).setPreferredWidth(105);
			table.getColumnModel().getColumn(2).setPreferredWidth(140);
			table.getColumnModel().getColumn(3).setPreferredWidth(135);

			pozadina.setBounds(100, 100, 740, 500);
			pozadinskiPanel.setBounds(22, 21, 680, 420);
			panel.setBounds(0, 0, 740, 500);
			table.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			table.setForeground(new Color(102, 0, 0));
			table.setRowHeight(20);
			scrollPane.setVisible(true);
			pozadina.repaint();
			table.setVisible(true);
			tableSorter.setModel((AbstractTableModel) table.getModel());
			table.setRowSorter(tableSorter);
			pozadina.setBounds(100, 100, 740, 500);
			pozadinskiPanel.setBounds(22, 21, 680, 420);
			panel.setBounds(0, 0, 740, 500);
			dugmePotvrdi.setVisible(true);
			checkBoxOstecena.setVisible(true);
		}
	}
}
