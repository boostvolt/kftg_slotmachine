import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class SlotMachineView extends JFrame {

	/* Defining labels */
	private JLabel lblBetAmountDisplay;
	private JLabel lblBetAmount;
	private JLabel lblCreditDisplay;
	private JLabel lblCredit;
	private JLabel lblErrMsg;

	/* Defining labels for 3 reels */
	private JLabel lblReel1;
	private JLabel lblReel2;
	private JLabel lblReel3;

	/* Defining buttons */
	private JButton btnAddCoin;
	private JButton btnBetOne;
	private JButton btnBetMax;
	private JButton btnSpin;
	private JButton btnStop;
	private JButton btnReset;
	private JButton btnKapital05;
	private JButton btnKapital1;
	private JButton btnKapital2;
	private JButton	btnKapital5;

	/* Defining borders for selected and unselected stages of the reel labels */
	private Border unselectedBorder = new MatteBorder(2, 2, 2, 2, Color.GRAY);
	private Border selectedBorder = new CompoundBorder(new LineBorder(new Color(255, 255, 0, 191), 3),
			new EmptyBorder(2, 2, 2, 2));

	/*
	 * Declaring the constructor and calling the relevant methods to execute. In
	 * here the main JFrame is created
	 */
	public SlotMachineView() {
		
		/* Initializing labels */
		lblBetAmountDisplay = new JLabel("Einsatz: CHF ");
		lblBetAmount = new JLabel();
		lblCreditDisplay = new JLabel("Kapital: CHF ");
		lblCredit = new JLabel();
		lblErrMsg = new JLabel("ERR");

		/* Initializing labels for 3 reels */
		lblReel1 = new JLabel();
		lblReel2 = new JLabel();
		lblReel3 = new JLabel();

		/* Initializing buttons */
		btnAddCoin = new JButton("Kapital: CHF +1.00");
		btnBetOne = new JButton("Einsatz: CHF +1.00");
		btnSpin = new JButton("Drehen");
		btnStop = new JButton("Stopp");
		btnReset = new JButton("Auszahlen");
		
		/* Defining main JPanel to hold sub JPanel components */
		JPanel mainPanel = new JPanel();
		createView(mainPanel);

		this.setTitle("Einarmiger Bandit");
		this.add(mainPanel);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// Adding a window listener to display a confirmation box when clicking
		// exit button
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				System.exit(0);
			}
		});
	}

	/*
	 * Setter method for lblErrMsg label. This lblErrMsg label displays all the
	 * related messages
	 */
	public void setErrMsg(String message) {
		this.lblErrMsg.setText(message);
	}

	/* Getter methods for Reel labels */
	public JLabel getLblReel1() {
		return this.lblReel1;
	}

	public JLabel getLblReel2() {
		return this.lblReel2;
	}

	public JLabel getLblReel3() {
		return this.lblReel3;
	}
	
	/* Defining getter methods for buttons */
	public JButton getBtnAddCoin() {
		return btnAddCoin;
	}

	public JButton getBtnBetOne() {
		return btnBetOne;
	}

	public JButton getBtnSpin() {
		return btnSpin;
	}

	public JButton getBtnStop() {
		return btnStop;
	}

	public JButton getBtnReset() {
		return btnReset;
	}

	/* Defining getter methods for Borders */
	public Border getUnselectedBorder() {
		return unselectedBorder;
	}

	public Border getSelectedBorder() {
		return selectedBorder;
	}

	/* Getter and Setter methods for lblBetAmount and lblCredit labels */
	public double getBetAmount() {
		return Double.parseDouble(lblBetAmount.getText());
	}

	public void setBetAmount(double betAmount) {
		String betAmountString = String.format("%.2f", betAmount);
		this.lblBetAmount.setText(betAmountString);
	}

	public double getCredit() {
		return Double.parseDouble(lblCredit.getText());
	}

	public void setCredit(double score) {
		String creditAmountString = String.format("%.2f", score);
		this.lblCredit.setText(creditAmountString);
	}
	
	/* Creating sub panels and adding components to them */
	public void createView(JPanel mainPanel) {
		
		/* Defining JPanels to hold components */
		JPanel p0 = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();

		String url = "Images/seven.png";
		ImageIcon imgObj = new ImageIcon(
				new ImageIcon(url).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		lblReel1.setIcon(imgObj);
		lblReel2.setIcon(imgObj);
		lblReel3.setIcon(imgObj);

		p0.setLayout(new GridLayout(2, 1));
		lblErrMsg.setHorizontalAlignment(JLabel.CENTER);
		p0.add(lblErrMsg);

		p1.setLayout(new GridLayout(1, 4));
		lblBetAmountDisplay.setHorizontalAlignment(JLabel.RIGHT);
		lblCreditDisplay.setHorizontalAlignment(JLabel.RIGHT);

		p1.add(lblBetAmountDisplay);
		p1.add(lblBetAmount);
		p1.add(lblCreditDisplay);
		p1.add(lblCredit);
		p0.add(p1);

		p2.setLayout(new GridLayout(1, 3));

		lblReel1.setHorizontalAlignment(JLabel.CENTER);
		lblReel2.setHorizontalAlignment(JLabel.CENTER);
		lblReel3.setHorizontalAlignment(JLabel.CENTER);

		lblReel1.setBorder(unselectedBorder);
		lblReel2.setBorder(unselectedBorder);
		lblReel3.setBorder(unselectedBorder);

		p2.add(lblReel1);
		p2.add(lblReel2);
		p2.add(lblReel3);
		p2.setBorder(new EmptyBorder(10, 10, 10, 10));

		p3.add(btnBetOne);
		p3.add(btnAddCoin);
		p3.setBorder(new EmptyBorder(10, 10, 10, 10));

		p4.add(btnSpin);
		p4.add(btnReset);
		p4.add(btnStop);
		p3.add(p4);

		mainPanel.setLayout(new GridLayout(3, 1));
		mainPanel.add(p0);
		mainPanel.add(p2);
		mainPanel.add(p3);
		mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
	}

	/*
	 * Disables or enables all the buttons according to the passing boolean flag
	 * value
	 */
	public void buttonControl(boolean flag) {
		btnAddCoin.setEnabled(flag);
		btnBetOne.setEnabled(flag);
		btnBetMax.setEnabled(flag);
		btnSpin.setEnabled(flag);
		btnStop.setEnabled(flag);
		btnReset.setEnabled(flag);
	}
}
