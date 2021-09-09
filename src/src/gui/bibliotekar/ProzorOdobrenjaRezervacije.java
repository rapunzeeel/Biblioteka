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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import dto.ZahtevRezervacijeDTO;
import exception.NijePronadjenjaPrimerakException;
import exception.SviPrimerciSlobodniException;
import gui.pomocniElementi.StilDugmeta;
import gui.pomocniElementi.StilIvice;
import kontroler.BibliotekaKontroler;
import observer.Observer;
import observer.RepositoryUpdateEvent;
import utils.ZahteviTableModel;

public class ProzorOdobrenjaRezervacije implements Observer {
	private JFrame pozadina;
	private JFrame glavniProzor;
	private JScrollPane scrollPane;
	private JButton dugmePotvrdi;
	private JLabel lblObavestenje;
	private ZahteviTableModel tableModel;
	private JTable table;
	private BibliotekaKontroler bibliotekaKontroler;
	private ArrayList<ZahtevRezervacijeDTO> zahtevi;
	private TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();

	public ProzorOdobrenjaRezervacije(BibliotekaKontroler bibliotekaKontroler, JFrame glavniProzor) {
		this.bibliotekaKontroler = bibliotekaKontroler;
		this.glavniProzor = glavniProzor;
		this.bibliotekaKontroler.getZahtevZaRezervacijuRepo().addObserver(this);

		initialize();
	}

	private void initialize() {
		pozadina = new JFrame();
		pozadina.setVisible(true);
		pozadina.setResizable(false);
		pozadina.setTitle("Odobrenje rezervacije");
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));
		pozadina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pozadina.setBounds(100, 100, 945, 671);
		pozadina.setBackground(new Color(0xeadbc8));
		pozadina.getContentPane().setLayout(null);
		pozadina.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pozadina.setVisible(false);
				glavniProzor.setVisible(true);
			}
		});

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1000, 700);
		panel.setForeground(new Color(127, 255, 0));
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);
		pozadina.getContentPane().add(panel);

		JLabel lblOdobrenjeRezervacije = new StilIvice();
		lblOdobrenjeRezervacije.setText("Prikaz zahteva");
		lblOdobrenjeRezervacije.setIcon(new ImageIcon("slike\\odobrenjeRezervacije.png"));
		lblOdobrenjeRezervacije.setHorizontalAlignment(SwingConstants.LEFT);
		lblOdobrenjeRezervacije.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblOdobrenjeRezervacije.setForeground(new Color(0xf4ece2));
		lblOdobrenjeRezervacije.setBackground(new Color(102, 0, 0));
		lblOdobrenjeRezervacije.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblOdobrenjeRezervacije.setBounds(319, 10, 266, 47);
		panel.add(lblOdobrenjeRezervacije);

		JPanel pozadinskiPanel = new JPanel();
		pozadinskiPanel.setBounds(20, 20, 900, 600);
		pozadinskiPanel.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel);
		pozadinskiPanel.setLayout(null);

		dugmePotvrdi = new JButton("Potvrdi rezervaciju");
		dugmePotvrdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblObavestenje.setVisible(false);
				int redTabele = table.getSelectedRow();

				if (redTabele != -1) {
					int index = table.convertRowIndexToModel(redTabele);
					ZahtevRezervacijeDTO zahtev = (ZahtevRezervacijeDTO) ((ZahteviTableModel) table.getModel())
							.getRows().get(index);

					try {
						bibliotekaKontroler.potvrdiRezervaciju(zahtev);
						lblObavestenje.setText("Uspešno potvrđena rezervacija!");
						lblObavestenje.setForeground(new Color(0, 102, 0));
						lblObavestenje.setVisible(true);
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (SviPrimerciSlobodniException e1) {
						lblObavestenje.setText(e1.getValueName());
						lblObavestenje.setForeground(new Color(102, 0, 0));
						lblObavestenje.setVisible(true);
					} catch (NijePronadjenjaPrimerakException e1) {
						lblObavestenje.setText(e1.getValueName());
						lblObavestenje.setForeground(new Color(102, 0, 0));
						lblObavestenje.setVisible(true);
					}

				}

			}
		});
		dugmePotvrdi.setFont(new Font("Times New Roman", Font.BOLD, 20));
		dugmePotvrdi.setBackground(new Color(102, 0, 0));
		dugmePotvrdi.setForeground(new Color(0xf4ece2));
		dugmePotvrdi.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmePotvrdi.setUI(new StilDugmeta());
		dugmePotvrdi.setBounds(669, 537, 201, 34);
		pozadinskiPanel.add(dugmePotvrdi);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 70, 850, 450);
		pozadinskiPanel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		lblObavestenje = new JLabel("");
		lblObavestenje.setBounds(449, 549, 210, 14);
		lblObavestenje.setForeground(new Color(102, 0, 0));
		lblObavestenje.setFont(new Font("Times New Roman", Font.ITALIC, 15));

		pozadinskiPanel.add(lblObavestenje);

		lblObavestenje.setVisible(false);

		try {
			bibliotekaKontroler.izlistajZahteve();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void updatePerformed(RepositoryUpdateEvent event) {
		zahtevi = (ArrayList<ZahtevRezervacijeDTO>) event.getAkcija();
		tableModel = new ZahteviTableModel(zahtevi);
		table.setModel(tableModel);
		table.getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 22));

		table.getTableHeader().setBackground(new Color(102, 0, 0));
		table.getTableHeader().setForeground(new Color(0xf4ece2));
		table.getColumnModel().getColumn(0).setPreferredWidth(160);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);

		table.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		table.setForeground(new Color(102, 0, 0));
		table.setRowHeight(20);
		pozadina.repaint();
		table.setVisible(true);
		tableSorter.setModel((AbstractTableModel) table.getModel());
		table.setRowSorter(tableSorter);
	}
}
