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

import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class CView extends JFrame {

	/* Defining labels */
	private final JLabel labelStakeAmountDisplay;
	private final JLabel labelStakeAmount;
	private final JLabel labelCapitalDisplay;
	private final JLabel labelCapital;
	private final JLabel labelInfoMessage;

	/* Defining labels for 3 reels */
	private final JLabel labelReel1;
	private final JLabel labelReel2;
	private final JLabel labelReel3;

	/* Defining buttons */
	private final JButton buttonAddCapital;
	private final JButton buttonAddStake;
	private final JButton buttonSpin;
	private final JButton buttonStop;
	private final JButton buttonPayOut;

	/* Defining borders for selected and unselected stages of the reel labels */
	private final Border borderUnselected = new MatteBorder(2, 2, 2, 2, Color.GRAY);

	/* Declaring the constructor and calling the relevant methods to execute. In here the main JFrame is created */
	public CView() {

		/* Initializing labels */
		labelStakeAmountDisplay = new JLabel("Einsatz: CHF ");
		labelStakeAmount = new JLabel();
		labelCapitalDisplay = new JLabel("Kapital: CHF ");
		labelCapital = new JLabel();
		labelInfoMessage = new JLabel("");

		/* Initializing labels for 3 reels */
		labelReel1 = new JLabel();
		labelReel2 = new JLabel();
		labelReel3 = new JLabel();

		/* Initializing buttons */
		buttonAddCapital = new JButton("Kapital CHF +0.50");
		buttonAddStake = new JButton("Einsatz CHF +0.50");
		buttonSpin = new JButton("Drehen");
		buttonStop = new JButton("Stopp");
		buttonPayOut = new JButton("Auszahlen");

		/* Defining main JPanel to hold sub JPanel components */
		JPanel mainPanel = new JPanel();
		createView(mainPanel);

		this.setTitle("Einarmiger Bandit");
		this.add(mainPanel);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		/* Adding a window listener to close the Application when clicking the exit button */
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
	}

	/* Setter method for lblErrMsg label. This lblErrMsg label displays all the related messages */
	public void setInfoMessage(String message) {
		this.labelInfoMessage.setText(message);
	}

	/* Getter methods for Reel labels */
	public JLabel getLabelReel1() {
		return this.labelReel1;
	}

	public JLabel getLabelReel2() {
		return this.labelReel2;
	}

	public JLabel getLabelReel3() {
		return this.labelReel3;
	}

	/* Defining getter methods for buttons */
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

	/* Getter and Setter methods for lblBetAmount and lblCredit labels */
	public double getStakeAmount() {
		return Double.parseDouble(labelStakeAmount.getText());
	}

	public void setStakeAmount(double betAmount) {
		String betAmountString = String.format("%.2f", betAmount);
		this.labelStakeAmount.setText(betAmountString);
	}

	public double getCapital() {
		return Double.parseDouble(labelCapital.getText());
	}

	public void setCapital(double score) {
		String creditAmountString = String.format("%.2f", score);
		this.labelCapital.setText(creditAmountString);
	}

	/* Creating sub panels and adding components to them */
	public void createView(JPanel mainPanel) {

		/* Defining JPanels to hold components */
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
		labelStakeAmountDisplay.setHorizontalAlignment(JLabel.RIGHT);
		labelCapitalDisplay.setHorizontalAlignment(JLabel.RIGHT);

		panel2.add(labelStakeAmountDisplay);
		panel2.add(labelStakeAmount);
		panel2.add(labelCapitalDisplay);
		panel2.add(labelCapital);
		panel1.add(panel2);

		panel3.setLayout(new GridLayout(1, 3));

		labelReel1.setHorizontalAlignment(JLabel.CENTER);
		labelReel2.setHorizontalAlignment(JLabel.CENTER);
		labelReel3.setHorizontalAlignment(JLabel.CENTER);

		//labelReel1.setBorder(borderUnselected);
		//labelReel2.setBorder(borderUnselected);
		//labelReel3.setBorder(borderUnselected);

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
