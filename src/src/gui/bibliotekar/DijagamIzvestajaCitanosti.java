package gui.bibliotekar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.demo.charts.ExampleChart;

public class DijagamIzvestajaCitanosti implements ExampleChart<PieChart> {
	private HashMap<String, Float> map;

	public DijagamIzvestajaCitanosti(HashMap<String, Float> map) {
		this.map = map;
	}

	public static void pieChart(HashMap<String, Float> map) {
		JFrame pozadina = new JFrame("Grafički prikaz čitanosti");
		pozadina.setLayout(new BorderLayout());
		pozadina.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ExampleChart<PieChart> exampleChart = new DijagamIzvestajaCitanosti(map);
		PieChart grafik = exampleChart.getChart();
		JPanel panel = new XChartPanel<PieChart>(grafik);
		pozadina.add(panel , BorderLayout.CENTER);
		pozadina.pack();
		pozadina.setVisible(true);
	}

	@Override
	public PieChart getChart() {

		PieChart grafik = new PieChartBuilder().width(700).height(700).title("Grafički prikaz čitanosti").build();
		grafik.getStyler().setChartTitleFont(new Font("Times New Roman", Font.BOLD, 25));
		grafik.getStyler().setPlotBorderColor(new Color(102, 0, 0));
		grafik.getStyler().setChartBackgroundColor(new Color(0xf4ece2));
		grafik.getStyler().setChartFontColor(new Color(102, 0, 0));
		grafik.getStyler().setLegendFont(new Font("Times New Roman", Font.ITALIC, 18));
		grafik.getStyler().setSumFont(new Font("Times New Roman", Font.BOLD, 20));
		for (Entry<String, Float> s : map.entrySet()) {
			if (s.getValue() > 0) {
				grafik.addSeries(s.getKey(), s.getValue());
			}
		}

		return grafik;
	}

	@Override
	public String getExampleChartName() {
		return null;
	}

	public HashMap<String, Float> getMap() {
		return map;
	}

}