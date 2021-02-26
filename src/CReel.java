/*******************************************************************************
 *
 * CReel.java
 * EinarmigerBandit
 *
 * Copyright © 2021 Jan Kott. All rights reserved.
 *
 ******************************************************************************/

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CReel {

	/* Erstellen von Instanzen des Typs "Symbol" zur Aufnahme verschiedener sechs Symbole */
	private final CSymbol seven = new CSymbol(7, "Images/seven.png");
	private final CSymbol bell = new CSymbol(6, "Images/bell.png");
	private final CSymbol grape = new CSymbol(5, "Images/grape.png");
	private final CSymbol strawberry = new CSymbol(4, "Images/strawberry.png");
	private final CSymbol coin = new CSymbol(3, "Images/coin.png");
	private final CSymbol cherry = new CSymbol(2, "Images/cherry.png");

	/* Dieses Array enthält die sechs verschiedenen Symbol-Objekte */
	private final CSymbol[] picArray = new CSymbol[6];

	/* Diese Methode gibt ein zufällig gemischtes Array mit den sechs Symbols-Objekten zurück */
	public CSymbol[] spin() {
		initializeArray(picArray);
		shuffleArray(picArray);
		return picArray;
	}

	/* Mischt zufällig ein Array vom Typ Symbol */
	private void shuffleArray(CSymbol[] ar) {
		//  Ein Zufallszahlengenerator, der für den aktuellen Thread isoliert ist
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--) {
			// Gibt einen zufälligen, gleichmäßig verteilten Wert zwischen dem angegebenen kleinsten Wert (einschließlich) und der Grenze (ausschließlich) zurück
			int index = rnd.nextInt(i + 1);
			// Einfacher Tausch
			CSymbol a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	/* Initialisieren des Symbol-Arrays mit sechs Symbol-Objekten */
	private void initializeArray(CSymbol[] ar) {
		ar[0] = seven;
		ar[1] = bell;
		ar[2] = grape;
		ar[3] = strawberry;
		ar[4] = coin;
		ar[5] = cherry;
	}
}
