/*
 * CController.java
 * EinarmigerBandit
 *
 * Copyright © 2021 Jan Kott. All rights reserved.
 */

import java.io.Serializable;
import javax.swing.JLabel;

public class CController implements Serializable {

    /* Deklaration privater Attribute */
    private final CView myView;
    private final CModel myModel;
    private static final int initialCapital = 0;
    private static final int initialStake = 0;
    private static final String insufficientCapital = "Du hast leider nichts gewonnen.";

    /* Deklarieren der Threads, welche für die Animation der Reels verwendet werden sollen */
    private thread thread1;
    private thread thread2;
    private thread thread3;

    /* Prüft, ob der Spin ein- oder ausgeschaltet ist */
    private boolean spin = false;

    /* Deklarieren des Konstruktors */
    public CController(CView view, CModel model) {
        this.myView = view;
        this.myModel = model;
    }

    /* Initialisierung der Attribute */
    public void initializeController() {
        myView.setCapital(initialCapital);
        myView.setStake(initialStake);
        myView.setInfoMessage("Willkommen im Einarmigen Bandit von Jan Kott.");
        this.actionListenerCreator();
        myView.setVisible(true);
    }

    /* Action-Listener für Buttons */
    public void actionListenerCreator() {
        // Wenn der Button "Kapital: +0.50 CHF" ausgewählt ist, wird die Methode addCapitalControl() aufgerufen
        myView.getButtonAddCapital().addActionListener(e -> {
            myView.setInfoMessage("Kapital CHF +0.50");
            addCapitalControl();
        });
        // Wenn der Button "Einsatz: +0.50 CHF" ausgewählt ist, wird die Methode addStakeControl() aufgerufen
        myView.getButtonAddStake().addActionListener(e -> {
            myView.setInfoMessage("Einsatz CHF +0.50");
            addStakeControl();
        });
        // Wenn der Button "Drehen" ausgewählt ist, wird die Methode threadControl() aufgerufen
        myView.getButtonSpin().addActionListener(e -> {
            myView.setInfoMessage("");
            threadControl(true);
        });
        // Wenn der Button "Stopp" ausgewählt ist, wird die Methode calculateWin() aufgerufen
        myView.getButtonStop().addActionListener(e -> {
            myView.setInfoMessage("");
            threadControl(false);
            calculateWin();
        });
        // Wenn der Button "Auszahlen" ausgewählt ist, wird die Methode payOut() aufgeruft
        myView.getButtonPayOut().addActionListener(e -> {
            myView.setInfoMessage("Kapital wurde ausgezahlt.");
            payOut();
        });
    }

    /* Steuert die Bedienung des Buttons "Kapital: +0.50 CHF". Fügt 0.50 CHF zum Kapital hinzu */
    public void addCapitalControl() {
        double capital = myView.getCapital();
        double newCapital = myModel.addCapital(capital);
        myView.setCapital(newCapital);
    }

    /* Steuert die Bedienung des Buttons "Einsatz: + 0.50 CHF". Fügt 0.50 CHF zum Einsatz hinzu, wenn das Kapital grösser als 0 ist und reduziert das Kapital um 0.50 CHF */
    public void addStakeControl() {
        double capital = myView.getCapital();
        if (capital > 0.00) {
            double oldStake = myView.getStake();
            double newStake = myModel.addStake(oldStake);
            double newCapital = myModel.removeCapital(capital);
            myView.setCapital(newCapital);
            myView.setStake(newStake);
        } else {
            myView.setInfoMessage("Du hast zu wenig Kapital.");
        }
    }

    /* Steuert die Funktion des Buttons "Auszahlen". Setzt das Kapital und der Einsatz auf 0 */
    public void payOut() {
        double existingCapital = myView.getCapital();
        myView.setCapital(existingCapital + myView.getStake());
        myView.setCapital(0.00);
    }

    /* Prüft, ob die im Reel angezeigten Symbole gleich sind oder nicht. */
    public void calculateWin() {
        if (spin) {
            double stake = myView.getStake();
            CSymbol symbol1 = thread1.getObject();
            CSymbol symbol2 = thread2.getObject();
            CSymbol symbol3 = thread3.getObject();

            boolean result1 = symbol1.compareSymbols(symbol1, symbol2);
            boolean result2 = symbol1.compareSymbols(symbol2, symbol3);

            double capital = myView.getCapital();
            spin = false;
            // 3x das Symbol "Sieben" multipliziert den Einsatz x4
            if (symbol1.getValue() == 7 && symbol2.getValue() == 7 && symbol3.getValue() == 7) {
                myView.setStake(initialStake);
                double symbolAmount = 4.00;
                double newCapital = myModel.addWinningStake(capital, stake, symbolAmount);
                double wonStake = newCapital - capital;
                myView.setCapital(newCapital);
                myView.setInfoMessage("Du hast CHF " + wonStake + "0 gewonnen.");
                // 3x ein beliebiges Symbol multipliziert den Einsatz x2
            } else if (result1 && result2) {
                myView.setStake(initialStake);
                double symbolAmount = 2.00;
                double newCapital = myModel.addWinningStake(capital, stake, symbolAmount);
                double wonStake = newCapital - capital;
                myView.setCapital(newCapital);
                myView.setInfoMessage("Du hast CHF " + wonStake + "0 gewonnen.");
                // 2x das Symbol "Sieben" multipliziert den Einsatz x2
            } else if (symbol1.getValue() == 7 && symbol2.getValue() == 7 || symbol2.getValue() == 7 && symbol3.getValue() == 7 || symbol1.getValue() == 7 && symbol3.getValue() == 7) {
                myView.setStake(initialStake);
                double symbolAmount = 2.00;
                double newCapital = myModel.addWinningStake(capital, stake, symbolAmount);
                double wonStake = newCapital - capital;
                myView.setCapital(newCapital);
                myView.setInfoMessage("Du hast CHF " + wonStake + "0 gewonnen.");
                // 1x das Symbol "Sieben" multipliziert den Einsatz x1
            } else if (symbol1.getValue() == 7 || symbol2.getValue() == 7 || symbol3.getValue() == 7) {
                myView.setStake(initialStake);
                double symbolAmount = 1.00;
                double newCapital = myModel.addWinningStake(capital, stake, symbolAmount);
                double wonStake = newCapital - capital;
                myView.setCapital(newCapital);
                myView.setInfoMessage("Du hast CHF " + wonStake + "0 gewonnen.");
                // Wenn keines der Abfragen zutrifft, geht der Einsatz verloren
            } else {
                myView.setStake(initialStake);
                myView.setInfoMessage(insufficientCapital);
            }
        }
    }

    /* Startet und stoppt Threads entsprechend dem Wert des booleschen Flags */
    public void threadControl(boolean flag) {
        double stake = myView.getStake();
        if (stake > 0.00) {
            if (flag) {
                CReel reel1 = new CReel();
                CReel reel2 = new CReel();
                CReel reel3 = new CReel();

                CSymbol[] symbolArray1 = reel1.spin();
                CSymbol[] symbolArray2 = reel2.spin();
                CSymbol[] symbolArray3 = reel3.spin();

                thread1 = new thread(symbolArray1, myView.getLabelReel1());
                thread2 = new thread(symbolArray2, myView.getLabelReel2());
                thread3 = new thread(symbolArray3, myView.getLabelReel3());

                thread1.start();
                thread2.start();
                thread3.start();
                spin = true;
            } else if (spin) {
                thread1.interrupt();
                thread2.interrupt();
                thread3.interrupt();
            }
        } else {
            myView.setInfoMessage("Du hast keinen Einsatz festgelegt.");
        }
    }

    /* Klasse, die zum Erzeugen von Threads verwendet wird */
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
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    for (int i = 0; i <= symbolArray.length; i++) {
                        try {
                            object = symbolArray[i];
                            label.setIcon(symbolArray[i].getSymbol());
                            Thread.sleep(100);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            // Wenn i = 6, wird eine ArrayIndexOutOfBoundsException geworfen. Das macht dann i = 0
                            i = 0;
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
