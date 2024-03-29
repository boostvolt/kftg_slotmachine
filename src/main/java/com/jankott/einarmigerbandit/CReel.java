/*
 * CReel.java
 * EinarmigerBandit
 *
 * Copyright © 2021 Jan Kott. All rights reserved.
 */

package com.jankott.einarmigerbandit;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CReel {

    /* Erstellen von Instanzen des Typs "Symbol" zur Aufnahme verschiedener sechs Symbole */
    private final CSymbol seven = new CSymbol(7, "/symbols/seven.png");
    private final CSymbol bell = new CSymbol(6, "/symbols/bell.png");
    private final CSymbol grape = new CSymbol(5, "/symbols/grape.png");
    private final CSymbol strawberry = new CSymbol(4, "/symbols/strawberry.png");
    private final CSymbol coin = new CSymbol(3, "/symbols/coin.png");
    private final CSymbol cherry = new CSymbol(2, "/symbols/cherry.png");

    /* Dieses Array enthält die sechs verschiedenen Symbol-Objekte */
    private final CSymbol[] symbolArray = new CSymbol[6];

    /* Diese Methode gibt ein zufällig gemischtes Array mit den sechs Symbols-Objekten zurück */
    public CSymbol[] spin() {
        initializeArray(symbolArray);
        shuffleArray(symbolArray);
        return symbolArray;
    }

    /* Mischt zufällig ein Array vom Typ Symbol */
    private void shuffleArray(CSymbol[] array) {
        //  Ein Zufallszahlengenerator, der für den aktuellen Thread isoliert ist
        Random random = ThreadLocalRandom.current();
        for (int i = array.length - 1; i > 0; i--) {
            // Gibt einen zufälligen, gleichmässig verteilten Wert zwischen dem angegebenen kleinsten Wert (einschliesslich) und der Grenze (ausschliesslich) zurück
            int index = random.nextInt(i + 1);
            // Einfacher Tausch
            CSymbol symbol = array[index];
            array[index] = array[i];
            array[i] = symbol;
        }
    }

    /* Initialisieren des Symbol-Arrays mit sechs Symbol-Objekten */
    private void initializeArray(CSymbol[] array) {
        array[0] = seven;
        array[1] = bell;
        array[2] = grape;
        array[3] = strawberry;
        array[4] = coin;
        array[5] = cherry;
    }
}
