package apk;

import java.awt.EventQueue;

import gui.zajednicki.PocetniProzor;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new PocetniProzor();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
