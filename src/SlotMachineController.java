import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import javax.swing.JLabel;

public class SlotMachineController implements Serializable {

	/* Declaring relevant private attributes */
	private SlotMachineView theView;
	private SlotMachineModel theModel;
	private static final int INITIALCREDIT = 10;
	private static final int INITIALBET = 0;
	private static final String GAMEOVER = "Du hast nichts gewonnen!";
	private int[] statArray = new int[4]; 
	//[0]=wins, [1]=loses, [2]=total bets, [3]=total times

	/* Declaring thread which is going to use to animate reels */
	private MyThread thread1;
	private MyThread thread2;
	private MyThread thread3;

	// Checks whether the spin is on or off
	private boolean spin = false;

	/* Declaring the constructor */
	public SlotMachineController(SlotMachineView theView, SlotMachineModel theModel) {
		this.theView = theView;
		this.theModel = theModel;
	}

	/* Initialize the relevant attributes */
	public void initialise() {
		theView.setCredit(INITIALCREDIT);
		theView.setBetAmount(INITIALBET);
		theView.setErrMsg("Willkommen im Einarmigen Bandit von Jan Kott.");
		this.actionListnerCreator();
		theView.setVisible(true);
	}

	/* Action listeners for buttons */
	public void actionListnerCreator() {
		/*
		 * When the "Add Coin" button selected calls the addCoinControl() method
		 */
		theView.getBtnAddCoin().addActionListener(e -> {
			theView.setErrMsg("");
			addCoinControl();
		});
		/*
		 * When the "Bet One" button selected calls the betOneControl() method
		 */
		theView.getBtnBetOne().addActionListener(e -> {
			theView.setErrMsg("");
			betOneControl();
		});
		/*
		 * When the "SPIN" button selected disables all the buttons and calls
		 * threadControl() method
		 */
		theView.getBtnSpin().addActionListener(e -> {
			theView.setErrMsg("");
			threadControl(true);
		});
		/*
		 * When the "Stop" button selected disables all the buttons and calls
		 * threadControl() method
		 */
		theView.getBtnStop().addActionListener(e -> {
			theView.setErrMsg("");
			threadControl(false);
			winCalculator();
		});
		/* When the "RESET" button selected calls resetControl() method */
		theView.getBtnReset().addActionListener(e -> {
			theView.setErrMsg("");
			resetControl();
		});
	}

	/* Controls the "Add Coin" button operation.Adds one coin for Credit Area */
	public void addCoinControl() {
		double credit = theView.getCredit();
		double newCredit = theModel.addCoin(credit);
		theView.setCredit(newCredit);
	}

	/*
	 * Controls the "Bet One" button operation. Adds one coin for bet area if
	 * credits>0 and reduce one coin from Credit Area
	 */
	public void betOneControl() {
		double credit = theView.getCredit();
		if (credit > 0.00) {
			double oldBet = theView.getBetAmount();
			double newBet = theModel.betOne(oldBet);
			double newCredit = theModel.removeOneCoin(credit);
			theView.setCredit(newCredit);
			theView.setBetAmount(newBet);
		} else {
			theView.setErrMsg("You have no credits");
		}
	}

	/*
	 * Controls the "Bet Max" button operation.Add three coins for bet area if
	 * credits>2 and reduce three coins from Credit Area
	 */
	public void betMaxControl() {
		double credit = theView.getCredit();
		if (credit > 2.00) {
			double oldBet = theView.getBetAmount();
			double newCredit = theModel.betMax(credit, oldBet);
			theView.setCredit(newCredit);
			theView.setBetAmount(3.00);
		} else {
			theView.setErrMsg("You have less than 3 credits");
		}
	}

	/*
	 * Controls the "Reset" button operation. Sets Bet Area to 0 and return bet
	 * amount to Credit Area
	 */
	public void resetControl() {
		double existingCredit = theView.getCredit();
		theView.setCredit(existingCredit + theView.getBetAmount());
		theView.setBetAmount(0.00);
		theView.setCredit(0.00);
	}

	/*
	 * Checks whether pictures displayed in the reel label are same or not. If 3
	 * reel labels are matching corresponding values are added to credit area.
	 * If 2 reel labels are matching another spinning chance is given. If no
	 * reel labels are matching bet amount is reduced from credit area. This
	 * process happens if the spin is true.
	 */
	public void winCalculator() {
		if (spin) {
			double betAmnt = theView.getBetAmount();
			Symbol sym1 = thread1.getObj();
			Symbol sym2 = thread2.getObj();
			Symbol sym3 = thread3.getObj();

			boolean result1 = sym1.compareSymbols(sym1, sym2);
			boolean result2 = sym1.compareSymbols(sym2, sym3);
			boolean result3 = sym1.compareSymbols(sym1, sym3);

			double credits = theView.getCredit();
			spin = false;
			if (sym1.getValue() == 7 && sym2.getValue() == 7 && sym3.getValue() == 7){
				theView.setBetAmount(INITIALBET);
				double symbolAmnt = 4.00;
				double newCredits = theModel.addWinningCoins(credits, betAmnt, symbolAmnt);
				double wonCredits = newCredits - (credits + (symbolAmnt * betAmnt));
				theView.setCredit(newCredits);
				theView.setErrMsg("Du hast: CHF " + wonCredits + "0 Gewonnen!");
				System.out.println("3x 7");
			} else if (result1 && result2) {
				theView.setBetAmount(INITIALBET);
				double symbolAmnt = 2.00;
				double newCredits = theModel.addWinningCoins(credits, betAmnt, symbolAmnt);
				double wonCredits = newCredits - (credits + (symbolAmnt * betAmnt));
				theView.setCredit(newCredits);
				theView.setErrMsg("Du hast: CHF " + wonCredits + "0 Gewonnen!");
			} else if (sym1.getValue() == 7 && sym2.getValue() == 7 || sym2.getValue() == 7 && sym3.getValue() == 7) {
				theView.setBetAmount(INITIALBET);
				double symbolAmnt = 2.00;
				double newCredits = theModel.addWinningCoins(credits, betAmnt, symbolAmnt);
				double wonCredits = newCredits - (credits + (symbolAmnt * betAmnt));
				theView.setCredit(newCredits);
				theView.setErrMsg("Du hast: CHF " + wonCredits + "0 Gewonnen!");
				System.out.println("2x 7");
			} else if (sym1.getValue() == 7  || sym2.getValue() == 7 || sym3.getValue() == 7) {
				theView.setBetAmount(INITIALBET);
				double symbolAmnt = 1.00;
				double newCredits = theModel.addWinningCoins(credits, betAmnt, symbolAmnt);
				double wonCredits = newCredits - (credits + betAmnt);
				theView.setCredit(newCredits);
				theView.setErrMsg("Du hast: CHF " + wonCredits + "0 Gewonnen!");
				System.out.println("1x 7");
			} else {
				theView.setBetAmount(INITIALBET);
				theView.setErrMsg(GAMEOVER);
			}
		}
	}

	/* starts and stops threads according to the boolean flag value */
	@SuppressWarnings("deprecation")
	public void threadControl(boolean flag) {
		double betAmnt = theView.getBetAmount();
		if (betAmnt > 0.00) {
			if (flag) {
				Reel reel1 = new Reel();
				Reel reel2 = new Reel();
				Reel reel3 = new Reel();

				Symbol[] symArray1 = reel1.spin();
				Symbol[] symArray2 = reel2.spin();
				Symbol[] symArray3 = reel3.spin();

				thread1 = new MyThread(symArray1, theView.getLblReel1());
				thread2 = new MyThread(symArray2, theView.getLblReel2());
				thread3 = new MyThread(symArray3, theView.getLblReel3());

				thread1.start();
				thread2.start();
				thread3.start();
				spin = true;
			} else if (spin) {
				thread1.stop();
				thread2.stop();
				thread3.stop();
			}
		} else {
			theView.buttonControl(true);
			theView.setErrMsg("You don't have any bet");
		}
	}

	/* Inner class which is used to create threads */
	class MyThread extends Thread {
		private Symbol obj;
		private Symbol[] symArray;
		private JLabel label;

		/* Declaring the constructor */
		MyThread(Symbol[] symArray, JLabel label) {
			this.symArray = symArray;
			this.label = label;
		}

		/* Getter method for Symbol type object */
		public Symbol getObj() {
			return obj;
		}

		@Override
		public void run() {
			for (int i = 0; i <= symArray.length; i++) {
				try {
					obj = symArray[i];
					label.setIcon(symArray[i].getImage());
					Thread.sleep(100);
				} catch (ArrayIndexOutOfBoundsException e) {
					// when the i becomes 6 an ArrayIndexOutOfBoundsException is
					// thrown. Then this makes i=0
					i = 0;
				} catch (InterruptedException e) {
					System.out.println("InterruptedException");
				}
			}
		}
	}
}
