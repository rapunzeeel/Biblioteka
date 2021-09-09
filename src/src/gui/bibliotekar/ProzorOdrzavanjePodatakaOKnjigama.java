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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import dto.KnjigaDTO;
import gui.pomocniElementi.StilDugmeta;
import gui.pomocniElementi.StilIvice;
import kontroler.BibliotekaKontroler;
import kontroler.KnjigaKontroler;
import model.Knjiga;
import observer.Observer;
import observer.RepositoryUpdateEvent;
import utils.KnjigaTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ProzorOdrzavanjePodatakaOKnjigama implements Observer {
	private JFrame pozadina;
	private JTable table;
	KnjigaTableModel tableModel;
	KnjigaKontroler knjigaKontroler;
	BibliotekaKontroler bibliotekaKontroler;
	private ArrayList<KnjigaDTO> listaKnjiga = new ArrayList<KnjigaDTO>();
	private TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	private JFrame glavniProzor;
	private Knjiga knjiga;

	public ProzorOdrzavanjePodatakaOKnjigama(KnjigaKontroler knjigaKontroler, JFrame glavniProzor,
			BibliotekaKontroler bibliotekaKontroler) {
		this.glavniProzor = glavniProzor;
		this.knjigaKontroler = knjigaKontroler;
		this.bibliotekaKontroler = bibliotekaKontroler;
		this.bibliotekaKontroler.getKnjigaRepo().addObserver(this);
		initialize();
	}

	private void initialize() {
		pozadina = new JFrame();
		pozadina.setBounds(100, 100, 450, 300);
		pozadina.setSize(1052, 720);
		pozadina.setTitle("Održavanje podataka o knjigama");
		pozadina.setVisible(true);
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));
		pozadina.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				pozadina.setVisible(false);
				pozadina.dispose();
				glavniProzor.setVisible(true);
			}
		});

		JPanel panel = new JPanel();
		pozadina.getContentPane().add(panel, BorderLayout.CENTER);
		pozadina.setBackground(Color.WHITE);
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);

		JLabel labelaOdrzavanjeKnjiga = new StilIvice();
		labelaOdrzavanjeKnjiga.setHorizontalAlignment(SwingConstants.LEFT);
		labelaOdrzavanjeKnjiga.setText("Održavanje podataka o knjigama");
		labelaOdrzavanjeKnjiga.setIcon(new ImageIcon("slike\\knjigaISBN.png"));
		labelaOdrzavanjeKnjiga.setBounds(302, 11, 488, 52);
		panel.add(labelaOdrzavanjeKnjiga);
		labelaOdrzavanjeKnjiga.setFont(new Font("Times New Roman", Font.BOLD, 30));
		labelaOdrzavanjeKnjiga.setForeground(new Color(0xf4ece2));
		labelaOdrzavanjeKnjiga.setBackground(new Color(102, 0, 0));

		JPanel pozadinskiPanel1 = new JPanel();
		pozadinskiPanel1.setBounds(57, 36, 932, 634);
		pozadinskiPanel1.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel1);
		pozadinskiPanel1.setLayout(null);

		JButton dugmeDodavanje = new JButton("Dodaj");
		dugmeDodavanje.setHorizontalAlignment(SwingConstants.LEFT);
		dugmeDodavanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProzorDodavanjaKnjiga(knjigaKontroler, pozadina);
				
			}
		});
		dugmeDodavanje.setBackground(new Color(102, 0, 0));
		dugmeDodavanje.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmeDodavanje.setUI(new StilDugmeta());
		dugmeDodavanje.setFont(new Font("Times New Roman", Font.BOLD, 25));
		dugmeDodavanje.setForeground(new Color(0xf4ece2));
		dugmeDodavanje.setBounds(41, 71, 283, 45);
		dugmeDodavanje.setIcon(new ImageIcon("slike\\dodajKnjigu.png"));
		pozadinskiPanel1.add(dugmeDodavanje);

		JButton dugmeIzmene = new JButton("Izmeni");
		dugmeIzmene.setHorizontalAlignment(SwingConstants.LEFT);
		dugmeIzmene.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int redTabele = table.getSelectedRow();

				if (redTabele != -1) {
					int index = table.convertRowIndexToModel(redTabele);
					String isbn = tableModel.getRows().get(index).getIsbn();
					try {
						knjigaKontroler.nadjiKnjigu(isbn);
						new ProzorIzmeneKnjige(knjigaKontroler, pozadina, knjiga);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		dugmeIzmene.setBackground(new Color(102, 0, 0));
		dugmeIzmene.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmeIzmene.setUI(new StilDugmeta());
		dugmeIzmene.setFont(new Font("Times New Roman", Font.BOLD, 25));
		dugmeIzmene.setForeground(new Color(0xf4ece2));
		dugmeIzmene.setBounds(324, 71, 283, 45);
		dugmeIzmene.setIcon(new ImageIcon("slike\\izmeni.png"));
		pozadinskiPanel1.add(dugmeIzmene);

		JButton dugmeBrisanje = new JButton("Obrisi");
		dugmeBrisanje.setHorizontalAlignment(SwingConstants.LEFT);
		dugmeBrisanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int redTabele = table.getSelectedRow();

				if (redTabele != -1) {
					int index = table.convertRowIndexToModel(redTabele);

					String isbn = tableModel.getRows().get(index).getIsbn();
					new ProzorPotvrdeBrisanja(bibliotekaKontroler, isbn, "Da li želite da obrišete knjigu?", true);
				}
			}
		});
		dugmeBrisanje.setBackground(new Color(102, 0, 0));
		dugmeBrisanje.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmeBrisanje.setUI(new StilDugmeta());
		dugmeBrisanje.setFont(new Font("Times New Roman", Font.BOLD, 25));
		dugmeBrisanje.setForeground(new Color(0xf4ece2));
		dugmeBrisanje.setBounds(607, 71, 283, 45);
		dugmeBrisanje.setIcon(new ImageIcon("slike\\obrisi.png"));
		pozadinskiPanel1.add(dugmeBrisanje);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 128, 849, 475);
		pozadinskiPanel1.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		try {
			bibliotekaKontroler.izlistajKnjige();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void updatePerformed(RepositoryUpdateEvent event) {
		try {
		listaKnjiga = (ArrayList<KnjigaDTO>) event.getAkcija();
		tableModel = new KnjigaTableModel(listaKnjiga);
		table.setModel(tableModel);
		table.getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 22));

		table.getTableHeader().setBackground(new Color(102, 0, 0));
		table.getTableHeader().setForeground(new Color(0xf4ece2));
		table.getColumnModel().getColumn(0).setPreferredWidth(160);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);

		table.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		table.setForeground(new Color(102, 0, 0));
		table.setRowHeight(20);
		pozadina.repaint();
		table.setVisible(true);
		tableSorter.setModel((AbstractTableModel) table.getModel());
		table.setRowSorter(tableSorter);
		}catch (Exception e) {
			try {
				knjiga = (Knjiga) event.getAkcija();				
			}
			catch(Exception e1) {
				
				
			}
		}
		
	}
}
