/*
 * CSymbol.java
 * EinarmigerBandit
 *
 * Copyright © 2021 Jan Kott. All rights reserved.
 */

import java.awt.Image;
import javax.swing.ImageIcon;

public class CSymbol implements ISymbol {

	/* Deklariert die privaten Variablen zur Aufnahme des Wertes und des Bildes des Symbolobjekts */
	private final int value;
	private ImageIcon symbol;

	/* Deklarieren des Konstruktors für den Typ Symbol */
	public CSymbol(int value, String path) {
		this.value = value;
		this.setSymbol(path);
	}

	/* Setter-Methode für das Bild des Symbol-Objekts */
	@Override
	public void setSymbol(String path) {
		this.symbol = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
	}

	/* Gibt das Bild des Symbol-Objekts zurück */
	@Override
	public ImageIcon getSymbol() {
		return this.symbol;
	}

	/* Gibt den Wert des Symbol-Objekts zurück */
	@Override
	public int getValue() {
		return this.value;
	}

	/* Vergleicht zwei Symbol-Objekte und gibt true zurück, wenn beide Objekte den gleichen Wert haben. Gibt false zurück, wenn nicht */
	public boolean compareSymbols(CSymbol object1, CSymbol object2) {
		return object1.value == object2.value;
	}
}
