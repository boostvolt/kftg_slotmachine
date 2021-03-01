/*
 * CController.java
 * EinarmigerBandit
 *
 * Copyright © 2021 Jan Kott. All rights reserved.
 */

import java.io.Serializable;
import javax.swing.JLabel;

public class CController implements Serializable {

    /* Deklaration relevanter privater Attribute */
    private final CView view;
    private final CModel model;
    private static final int initialCapital = 10;
    private static final int initialStake = 0;
    private static final String gameover = "Du hast leider nichts gewonnen.";

    /* Deklarieren der Threads, welche für die Animation der Rollen verwendet werden sollen */
    private thread thread1;
    private thread thread2;
    private thread thread3;

    /* Prüft, ob der Spin ein- oder ausgeschaltet ist */
    private boolean spin = false;

    /* Deklarieren des Konstruktors */
    public CController(CView view, CModel model) {
        this.view = view;
        this.model = model;
    }

    /* Initialisierung der relevanten Attribute */
    public void initialise() {
        view.setCapital(initialCapital);
        view.setStakeAmount(initialStake);
        view.setInfoMessage("Willkommen im Einarmigen Bandit von Jan Kott.");
        this.actionListenerCreator();
        view.setVisible(true);
    }

    /* Action-Listener für Buttons */
    public void actionListenerCreator() {
        // Wenn der Button "Kapital: +0.50 CHF" ausgewählt ist, wird die Methode addCapitalControl() aufgerufen
        view.getButtonAddCapital().addActionListener(e -> {
            view.setInfoMessage("Kapital CHF +0.50");
            addCapitalControl();
        });
        // Wenn der Button "Einsatz: +0.50 CHF" ausgewählt ist, wird die Methode addStakeControl() aufgerufen
        view.getButtonAddStake().addActionListener(e -> {
            view.setInfoMessage("Einsatz CHF +0.50");
            addStakeControl();
        });
        // Wenn der Button "Drehen" ausgewählt ist, wird die Methode threadControl() aufgerufen
        view.getButtonSpin().addActionListener(e -> {
            view.setInfoMessage("");
            threadControl(true);
        });
        // Wenn der Button "Stopp" ausgewählt ist, wird die Methode winCalculator() aufgerufen
        view.getButtonStop().addActionListener(e -> {
            view.setInfoMessage("");
            threadControl(false);
            winCalculator();
        });
        // Wenn der Button "Auszahlen" ausgewählt ist, wird die Methode payOut() aufruft
        view.getButtonPayOut().addActionListener(e -> {
            view.setInfoMessage("Kapital wurde ausgezahlt.");
            payOut();
        });
    }

    /* Steuert die Bedienung des Buttons "Kapital: +0.50 CHF". Fügt 0.50 CHF zum Kapital hinzu */
    public void addCapitalControl() {
        double capital = view.getCapital();
        double newCapital = model.addCapital(capital);
        view.setCapital(newCapital);
    }

    /* Steuert die Bedienung des Buttons "Einsatz: + 0.50 CHF". Fügt 0.50 CHF zum Einsatz hinzu, wenn das Kapital grösser als 0 ist und reduziert das Kapital um 0.50 CHF */
    public void addStakeControl() {
        double capital = view.getCapital();
        if (capital > 0.00) {
            double oldStake = view.getStakeAmount();
            double newStake = model.addStake(oldStake);
            double newCapital = model.removeCapital(capital);
            view.setCapital(newCapital);
            view.setStakeAmount(newStake);
        } else {
            view.setInfoMessage("Du hast kein Kapital mehr.");
        }
    }

    /* Steuert die Funktion des Buttons "Auszahlen". Setzt das Kapital und der Einsatz auf 0 */
    public void payOut() {
        double existingCredit = view.getCapital();
        view.setCapital(existingCredit + view.getStakeAmount());
        view.setStakeAmount(0.00);
        view.setCapital(0.00);
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
            double stakeAmount = view.getStakeAmount();
            CSymbol symbol1 = thread1.getObject();
            CSymbol symbol2 = thread2.getObject();
            CSymbol symbol3 = thread3.getObject();

            boolean result1 = symbol1.compareSymbols(symbol1, symbol2);
            boolean result2 = symbol1.compareSymbols(symbol2, symbol3);

            double capital = view.getCapital();
            spin = false;
            if (symbol1.getValue() == 7 && symbol2.getValue() == 7 && symbol3.getValue() == 7) {
                view.setStakeAmount(initialStake);
                double symbolAmount = 4.00;
                double newCapital = model.addWinningStake(capital, stakeAmount, symbolAmount);
                double wonStake = newCapital - capital;
                view.setCapital(newCapital);
                view.setInfoMessage("Du hast CHF " + wonStake + "0 gewonnen.");
            } else if (result1 && result2) {
                view.setStakeAmount(initialStake);
                double symbolAmount = 2.00;
                double newCapital = model.addWinningStake(capital, stakeAmount, symbolAmount);
                double wonStake = newCapital - capital;
                view.setCapital(newCapital);
                view.setInfoMessage("Du hast CHF " + wonStake + "0 gewonnen.");
            } else if (symbol1.getValue() == 7 && symbol2.getValue() == 7 || symbol2.getValue() == 7 && symbol3.getValue() == 7 || symbol1.getValue() == 7 && symbol3.getValue() == 7) {
                view.setStakeAmount(initialStake);
                double symbolAmount = 2.00;
                double newCapital = model.addWinningStake(capital, stakeAmount, symbolAmount);
                double wonStake = newCapital - capital;
                view.setCapital(newCapital);
                view.setInfoMessage("Du hast CHF " + wonStake + "0 gewonnen.");
            } else if (symbol1.getValue() == 7 || symbol2.getValue() == 7 || symbol3.getValue() == 7) {
                view.setStakeAmount(initialStake);
                double symbolAmount = 1.00;
                double newCapital = model.addWinningStake(capital, stakeAmount, symbolAmount);
                double wonStake = newCapital - capital;
                view.setCapital(newCapital);
                view.setInfoMessage("Du hast CHF " + wonStake + "0 gewonnen.");
            } else {
                view.setStakeAmount(initialStake);
                view.setInfoMessage(gameover);
            }
        }
    }

    /* Startet und stoppt Threads entsprechend dem Wert des booleschen Flags */
    public void threadControl(boolean flag) {
        double stakeAmount = view.getStakeAmount();
        if (stakeAmount > 0.00) {
            if (flag) {
                CReel reel1 = new CReel();
                CReel reel2 = new CReel();
                CReel reel3 = new CReel();

                CSymbol[] symbolArray1 = reel1.spin();
                CSymbol[] symbolArray2 = reel2.spin();
                CSymbol[] symbolArray3 = reel3.spin();

                thread1 = new thread(symbolArray1, view.getLabelReel1());
                thread2 = new thread(symbolArray2, view.getLabelReel2());
                thread3 = new thread(symbolArray3, view.getLabelReel3());

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
            view.setInfoMessage("Du hast keinen Einsatz festgelegt.");
        }
    }

    /* Innere Klasse, die zum Erzeugen von Threads verwendet wird */
    static class thread extends Thread {
        private CSymbol object;
        private final CSymbol[] symbolArray;
        private final JLabel label;

        // Deklarieren des Konstruktors
        thread(CSymbol[] symbolArray, JLabel label) {
            this.symbolArray = symbolArray;
            this.label = label;
        }

        // Getter-Methode für Objekt vom Typ Symbol
        public CSymbol getObject() {
            return object;
        }

        @Override
        public void run() {
            for (int i = 0; i <= symbolArray.length; i++) {
                try {
                    object = symbolArray[i];
                    label.setIcon(symbolArray[i].getSymbol());
                    Thread.sleep(100);
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Wenn i = 6, wird eine ArrayIndexOutOfBoundsException geworfen. Das macht dann i = 0
                    i = 0;
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException");
                }
            }
        }
    }
}
