/*
 * CView.java
 * EinarmigerBandit
 *
 * Copyright © 2021 Jan Kott. All rights reserved.
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CView extends JFrame {

	/* Definiert Jlabels */
	private final JLabel labelStakeDisplay;
	private final JLabel labelStake;
	private final JLabel labelCapitalDisplay;
	private final JLabel labelCapital;
	private final JLabel labelInfoMessage;

	/* Definiert Jlabel für 3 Reels */
	private final JLabel labelReel1;
	private final JLabel labelReel2;
	private final JLabel labelReel3;

	/* Definiert JButtons */
	private final JButton buttonAddCapital;
	private final JButton buttonAddStake;
	private final JButton buttonSpin;
	private final JButton buttonStop;
	private final JButton buttonPayOut;

	/* Deklariert den Konstruktor und ruft die entsprechenden Methoden zur Ausführung auf. Hier wird der mainPanel erzeugt */
	public CView() {

		/* Initialisiert JLabels */
		labelStakeDisplay = new JLabel("Einsatz: CHF ");
		labelStake = new JLabel();
		labelCapitalDisplay = new JLabel("Kapital: CHF ");
		labelCapital = new JLabel();
		labelInfoMessage = new JLabel("");

		/* Initialisiert JLabels für 3 Reels */
		labelReel1 = new JLabel();
		labelReel2 = new JLabel();
		labelReel3 = new JLabel();

		/* Initialisiert JButtons */
		buttonAddCapital = new JButton("Kapital CHF +0.50");
		buttonAddStake = new JButton("Einsatz CHF +0.50");
		buttonSpin = new JButton("Drehen");
		buttonStop = new JButton("Stopp");
		buttonPayOut = new JButton("Auszahlen");

		/* Definiert das mainPanel zur Aufnahme von Unter-JPanel-Komponenten */
		JPanel mainPanel = new JPanel();
		initializeView(mainPanel);

		this.setTitle("Einarmiger Bandit");
		this.add(mainPanel);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		/* Fenster-Listeners zum Schliessen der Anwendung beim Klicken auf die Schaltfläche "Beenden" */
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
	}

	/* Setter-Methode für das JLabel labelInfoMessage. Dieses Label zeigt alle zugehörigen Meldungen an */
	public void setInfoMessage(String message) {
		this.labelInfoMessage.setText(message);
	}

	/* Getter-Methoden für Reel JLabels */
	public JLabel getLabelReel1() {
		return this.labelReel1;
	}

	public JLabel getLabelReel2() {
		return this.labelReel2;
	}

	public JLabel getLabelReel3() {
		return this.labelReel3;
	}

	/* Getter-Methoden für JButtons */
	public JButton getButtonAddCapital() {
		return buttonAddCapital;
	}

	public JButton getButtonAddStake() {
		return buttonAddStake;
	}

	public JButton getButtonSpin() {
		return buttonSpin;
	}

	public JButton getButtonStop() {
		return buttonStop;
	}

	public JButton getButtonPayOut() {
		return buttonPayOut;
	}

	/* Getter- und Setter-Methoden für die das JLabel labelStake und labelCapital */
	public double getStakeAmount() {
		return Double.parseDouble(labelStake.getText());
	}

	public void setStakeAmount(double betAmount) {
		String betAmountString = String.format("%.2f", betAmount);
		this.labelStake.setText(betAmountString);
	}

	public double getCapital() {
		return Double.parseDouble(labelCapital.getText());
	}

	public void setCapital(double score) {
		String creditAmountString = String.format("%.2f", score);
		this.labelCapital.setText(creditAmountString);
	}

	/* Unterpanels zum Hinzufügen von Komponenten zu diesen */
	public void initializeView(JPanel mainPanel) {

		/* Definiert JPanels zur Aufnahme von Komponenten */
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();

		String url = "images/seven.png";
		ImageIcon imgObj = new ImageIcon(
				new ImageIcon(url).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		labelReel1.setIcon(imgObj);
		labelReel2.setIcon(imgObj);
		labelReel3.setIcon(imgObj);

		panel1.setLayout(new GridLayout(2, 1));
		labelInfoMessage.setHorizontalAlignment(JLabel.CENTER);
		panel1.add(labelInfoMessage);

		panel2.setLayout(new GridLayout(1, 4));
		labelStakeDisplay.setHorizontalAlignment(JLabel.RIGHT);
		labelCapitalDisplay.setHorizontalAlignment(JLabel.RIGHT);

		panel2.add(labelStakeDisplay);
		panel2.add(labelStake);
		panel2.add(labelCapitalDisplay);
		panel2.add(labelCapital);
		panel1.add(panel2);

		panel3.setLayout(new GridLayout(1, 3));

		labelReel1.setHorizontalAlignment(JLabel.CENTER);
		labelReel2.setHorizontalAlignment(JLabel.CENTER);
		labelReel3.setHorizontalAlignment(JLabel.CENTER);

		panel3.add(labelReel1);
		panel3.add(labelReel2);
		panel3.add(labelReel3);
		panel3.setBorder(new EmptyBorder(10, 10, 10, 10));

		panel4.add(buttonAddStake);
		panel4.add(buttonAddCapital);
		panel4.setBorder(new EmptyBorder(10, 10, 10, 10));

		panel5.add(buttonSpin);
		panel5.add(buttonPayOut);
		panel5.add(buttonStop);
		panel4.add(panel5);

		mainPanel.setLayout(new GridLayout(3, 1));
		mainPanel.add(panel1);
		mainPanel.add(panel3);
		mainPanel.add(panel4);
		mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
	}
}
