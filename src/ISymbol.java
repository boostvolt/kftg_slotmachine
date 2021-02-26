/*
 * ISymbol.java
 * EinarmigerBandit
 *
 * Copyright © 2021 Jan Kott. All rights reserved.
 */

import javax.swing.ImageIcon;

public interface ISymbol {
	/* Setter-Methode für das Bild des Symbol-Objekts */
	void setImage(String url);

	/* Liefert das Bild des Symbol-Objekts */
	ImageIcon getImage();

	/* Liefert den Wert des Symbol-Objekts */
	int getValue();
}
