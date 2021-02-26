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
    private MyThread thread1;
    private MyThread thread2;
    private MyThread thread3;

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
        view.setErrorMessage("Willkommen im Einarmigen Bandit von Jan Kott.");
        this.actionListenerCreator();
        view.setVisible(true);
    }

    /* Action-Listener für Buttons */
    public void actionListenerCreator() {
        // Wenn der Button "Kapital: +0.50 CHF" ausgewählt ist, wird die Methode addCapitalControl() aufgerufen
        view.getButtonAddCapital().addActionListener(e -> {
            view.setErrorMessage("");
            addCapitalControl();
        });
        // Wenn der Button "Einsatz: +0.50 CHF" ausgewählt ist, wird die Methode addStakeControl() aufgerufen
        view.getButtonAddStake().addActionListener(e -> {
            view.setErrorMessage("");
            addStakeControl();
        });
        // Wenn der Button "Drehen" ausgewählt ist, wird die Methode threadControl() aufgerufen
        view.getButtonSpin().addActionListener(e -> {
            view.setErrorMessage("");
            threadControl(true);
        });
        // Wenn der Button "Stopp" ausgewählt ist, wird die Methode winCalculator() aufgerufen
        view.getButtonStop().addActionListener(e -> {
            view.setErrorMessage("");
            threadControl(false);
            winCalculator();
        });
        // Wenn der Button "Auszahlen" ausgewählt ist, wird die Methode payOut() aufruft
        view.getButtonPayOut().addActionListener(e -> {
            view.setErrorMessage("");
            payOut();
        });
    }

    /* Steuert die Bedienung des Buttons "Kapital: +0.50 CHF". Fügt 0.50 CHF zum Kapital hinzu */
    public void addCapitalControl() {
        double credit = view.getCapital();
        double newCredit = model.addCapital(credit);
        view.setCapital(newCredit);
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
            view.setErrorMessage("Du hast kein Kapital mehr.");
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
            double betAmnt = view.getStakeAmount();
            CSymbol sym1 = thread1.getObj();
            CSymbol sym2 = thread2.getObj();
            CSymbol sym3 = thread3.getObj();

            boolean result1 = sym1.compareSymbols(sym1, sym2);
            boolean result2 = sym1.compareSymbols(sym2, sym3);

            double credits = view.getCapital();
            spin = false;
            if (sym1.getValue() == 7 && sym2.getValue() == 7 && sym3.getValue() == 7) {
                view.setStakeAmount(initialStake);
                double symbolAmnt = 4.00;
                double newCredits = model.addWinningStake(credits, betAmnt, symbolAmnt);
                double wonCredits = newCredits - credits;
                view.setCapital(newCredits);
                view.setErrorMessage("Du hast CHF " + wonCredits + "0 gewonnen.");
            } else if (result1 && result2) {
                view.setStakeAmount(initialStake);
                double symbolAmnt = 2.00;
                double newCredits = model.addWinningStake(credits, betAmnt, symbolAmnt);
                double wonCredits = newCredits - credits;
                view.setCapital(newCredits);
                view.setErrorMessage("Du hast CHF " + wonCredits + "0 gewonnen.");
            } else if (sym1.getValue() == 7 && sym2.getValue() == 7 || sym2.getValue() == 7 && sym3.getValue() == 7) {
                view.setStakeAmount(initialStake);
                double symbolAmnt = 2.00;
                double newCredits = model.addWinningStake(credits, betAmnt, symbolAmnt);
                double wonCredits = newCredits - credits;
                view.setCapital(newCredits);
                view.setErrorMessage("Du hast CHF " + wonCredits + "0 gewonnen.");
            } else if (sym1.getValue() == 7 || sym2.getValue() == 7 || sym3.getValue() == 7) {
                view.setStakeAmount(initialStake);
                double symbolAmnt = 1.00;
                double newCredits = model.addWinningStake(credits, betAmnt, symbolAmnt);
                double wonCredits = newCredits - credits;
                view.setCapital(newCredits);
                view.setErrorMessage("Du hast CHF " + wonCredits + "0 gewonnen.");
            } else {
                view.setStakeAmount(initialStake);
                view.setErrorMessage(gameover);
            }
        }
    }

    /* Startet und stoppt Threads entsprechend dem Wert des booleschen Flags */
    public void threadControl(boolean flag) {
        double betAmnt = view.getStakeAmount();
        if (betAmnt > 0.00) {
            if (flag) {
                CReel reel1 = new CReel();
                CReel reel2 = new CReel();
                CReel reel3 = new CReel();

                CSymbol[] symArray1 = reel1.spin();
                CSymbol[] symArray2 = reel2.spin();
                CSymbol[] symArray3 = reel3.spin();

                thread1 = new MyThread(symArray1, view.getLabelReel1());
                thread2 = new MyThread(symArray2, view.getLabelReel2());
                thread3 = new MyThread(symArray3, view.getLabelReel3());

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
            view.setErrorMessage("Du hast keinen Einsatz festgelegt.");
        }
    }

    /* Innere Klasse, die zum Erzeugen von Threads verwendet wird */
    static class MyThread extends Thread {
        private CSymbol obj;
        private final CSymbol[] symArray;
        private final JLabel label;

        // Deklarieren des Konstruktors
        MyThread(CSymbol[] symArray, JLabel label) {
            this.symArray = symArray;
            this.label = label;
        }

        // Getter-Methode für Objekt vom Typ Symbol
        public CSymbol getObj() {
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
                    // Wenn i = 6, wird eine ArrayIndexOutOfBoundsException geworfen. Das macht dann i = 0
                    i = 0;
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException");
                }
            }
        }
    }
}
