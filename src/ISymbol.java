/*
 * ISymbol.java
 * EinarmigerBandit
 *
 * Copyright © 2021 Jan Kott. All rights reserved.
 */

import javax.swing.ImageIcon;

public interface ISymbol {
    /* Setter-Methode für das Bild des Symbol-Objekts */
    void setSymbol(String path);

    /* Gibt das Bild des Symbol-Objekts zurück */
    ImageIcon getSymbol();

    /* Gibt den Wert des Symbol-Objekts zurück */
    int getValue();
}
