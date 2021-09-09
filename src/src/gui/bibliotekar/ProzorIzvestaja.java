package gui.bibliotekar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.toedter.calendar.JDateChooser;

import dto.IzvestajDTO;
import exception.NemogucPeriodException;
import gui.pomocniElementi.StilDugmeta;
import gui.pomocniElementi.StilIvice;
import kontroler.BibliotekaKontroler;
import observer.Observer;
import observer.RepositoryUpdateEvent;
import utils.IzvestajTableModel;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ProzorIzvestaja implements Observer {
	private JFrame pozadina;
	private JTable table;
	private JDateChooser pocetniDatum;
	private JDateChooser krajnjiDatum;
	private TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
	private ArrayList<IzvestajDTO> rezultatIzvestaja;
	IzvestajTableModel tableModel;
	private BibliotekaKontroler bibliotekaKontroler;
	private JLabel lblGreska;
	private HashMap<String, Float> mapaCitanosti;
	private JComboBox comboBoxKriterijum;
	private JFrame glavniProzor;

	public ProzorIzvestaja(BibliotekaKontroler bibliotekaKontroler, JFrame glavniProzor) {
		this.bibliotekaKontroler = bibliotekaKontroler;
		this.glavniProzor = glavniProzor;

		this.bibliotekaKontroler.getBibliotekaRepo().addObserver(this);
		initialize();
	}

	private void initialize() {
		pozadina = new JFrame();
		pozadina.setVisible(true);
		pozadina.setResizable(false);
		pozadina.setTitle("Prikaz izveštaja o čitanosti");
		pozadina.setIconImage(Toolkit.getDefaultToolkit().getImage("slike\\book.png"));
		pozadina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pozadina.setBounds(100, 100, 598, 572);
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
		panel.setBounds(0, 0, 577, 1000);
		panel.setForeground(new Color(127, 255, 0));
		panel.setBackground(new Color(0xeadbc8));
		panel.setLayout(null);
		pozadina.getContentPane().add(panel);

		JLabel lblIzvestaja = new StilIvice();
		lblIzvestaja.setText("Izveštaj čitanosti");
		lblIzvestaja.setIcon(new ImageIcon("slike\\izvestaj.png"));
		lblIzvestaja.setHorizontalAlignment(SwingConstants.LEFT);
		lblIzvestaja.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblIzvestaja.setForeground(new Color(0xf4ece2));
		lblIzvestaja.setBackground(new Color(102, 0, 0));
		lblIzvestaja.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblIzvestaja.setBounds(109, 7, 303, 47);
		panel.add(lblIzvestaja);

		JPanel pozadinskiPanel = new JPanel();
		pozadinskiPanel.setBounds(10, 25, 557, 505);
		pozadinskiPanel.setBackground(new Color(0xf4ece2));
		panel.add(pozadinskiPanel);
		pozadinskiPanel.setLayout(null);

		lblGreska = new StilIvice();
		lblGreska.setForeground(new Color(0xf4ece2));
		lblGreska.setBackground(new Color(102, 0, 0));
		lblGreska.setText("Uneti vremenski period je nemoguć.");

		lblGreska.setHorizontalAlignment(SwingConstants.CENTER);
		lblGreska.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		lblGreska.setBounds(67, 260, 417, 49);
		pozadinskiPanel.add(lblGreska);
		lblGreska.setVisible(false);

		pocetniDatum = new JDateChooser();
		pocetniDatum.setForeground(new Color(102, 0, 0));
		pocetniDatum.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		pocetniDatum.setDate(Date.valueOf(LocalDate.now()));
		pocetniDatum.setBounds(147, 56, 162, 35);
		pozadinskiPanel.add(pocetniDatum);

		krajnjiDatum = new JDateChooser();
		krajnjiDatum.setForeground(new Color(102, 0, 0));
		krajnjiDatum.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		krajnjiDatum.setDate(Date.valueOf(LocalDate.now()));
		krajnjiDatum.setBounds(147, 107, 162, 35);
		pozadinskiPanel.add(krajnjiDatum);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 213, 542, 281);
		pozadinskiPanel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setVisible(false);

		JButton dugme = new JButton("Izgeneriši");
		dugme.setFont(new Font("Times New Roman", Font.BOLD, 25));

		dugme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lblGreska.setVisible(false);
					String kriterijum = (String) comboBoxKriterijum.getSelectedItem();
					bibliotekaKontroler.generisiIzvestaj(pocetniDatum.getDate(), krajnjiDatum.getDate(), kriterijum);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NemogucPeriodException e1) {
					lblGreska.setVisible(true);
				}
			}
		});
		dugme.setBounds(340, 82, 139, 35);
		dugme.setBackground(new Color(102, 0, 0));
		dugme.setForeground(new Color(0xf4ece2));
		dugme.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugme.setUI(new StilDugmeta());
		pozadinskiPanel.add(dugme);

		JButton dugmeDijagram = new JButton("Grafički prikaz");
		dugmeDijagram.setFont(new Font("Times New Roman", Font.BOLD, 25));
		dugmeDijagram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String kriterijum = (String) comboBoxKriterijum.getSelectedItem();
					bibliotekaKontroler.generisiIzvestaj(pocetniDatum.getDate(), krajnjiDatum.getDate(), kriterijum);
					DijagamIzvestajaCitanosti.pieChart(mapaCitanosti);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
				} catch (NemogucPeriodException e1) {
					lblGreska.setVisible(true);
				}
			}
		});
		dugmeDijagram.setBounds(340, 132, 207, 35);
		dugmeDijagram.setBackground(new Color(102, 0, 0));
		dugmeDijagram.setForeground(new Color(0xf4ece2));
		dugmeDijagram.setBorder(BorderFactory.createLineBorder(new Color(102, 0, 0)));
		dugmeDijagram.setUI(new StilDugmeta());
		pozadinskiPanel.add(dugmeDijagram);

		JLabel lblPocDatum = new JLabel("Početni datum");
		lblPocDatum.setHorizontalAlignment(SwingConstants.RIGHT);

		lblPocDatum.setForeground(new Color(102, 0, 0));
		lblPocDatum.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblPocDatum.setBounds(13, 56, 128, 30);
		pozadinskiPanel.add(lblPocDatum);

		JLabel lblKrajnjiDatum = new JLabel("Krajnji datum");
		lblKrajnjiDatum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKrajnjiDatum.setForeground(new Color(102, 0, 0));
		lblKrajnjiDatum.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblKrajnjiDatum.setBounds(27, 112, 114, 30);
		pozadinskiPanel.add(lblKrajnjiDatum);

		comboBoxKriterijum = new JComboBox();
		comboBoxKriterijum.setFont(new Font("Times New Roman", Font.ITALIC, 17));
		comboBoxKriterijum.setModel(new DefaultComboBoxModel(new String[] { "Autor", "Žanr", "Naslov" }));
		comboBoxKriterijum.setSelectedIndex(0);
		comboBoxKriterijum.setBounds(147, 161, 162, 35);
		pozadinskiPanel.add(comboBoxKriterijum);

		JLabel lblKriterijum = new JLabel("Kriterijum");
		lblKriterijum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKriterijum.setForeground(new Color(102, 0, 0));
		lblKriterijum.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblKriterijum.setBounds(27, 165, 114, 30);
		pozadinskiPanel.add(lblKriterijum);
	}

	private void kreirajMapu(ArrayList<IzvestajDTO> lista) {
		mapaCitanosti = new HashMap<>();
		for (IzvestajDTO i : lista) {
			mapaCitanosti.put(i.getNaslov(), i.getProcenat());
		}
	}

	@Override
	public void updatePerformed(RepositoryUpdateEvent event) {
		rezultatIzvestaja = (ArrayList<IzvestajDTO>) event.getAkcija();
		kreirajMapu(rezultatIzvestaja);
		String kriterijum = (String) comboBoxKriterijum.getSelectedItem();
		tableModel = new IzvestajTableModel(rezultatIzvestaja, kriterijum);
		table.setModel(tableModel);
		table.getTableHeader().setFont(new Font("Times New Roman", Font.PLAIN, 22));

		table.getTableHeader().setBackground(new Color(102, 0, 0));
		table.getTableHeader().setForeground(new Color(0xf4ece2));
		table.getColumnModel().getColumn(0).setPreferredWidth(160);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);

		table.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		table.setForeground(new Color(102, 0, 0));
		table.setRowHeight(20);
		pozadina.repaint();
		table.setVisible(true);
		tableSorter.setModel((AbstractTableModel) table.getModel());
		table.setRowSorter(tableSorter);
	}
}
