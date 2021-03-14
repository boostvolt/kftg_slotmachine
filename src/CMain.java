/*
 * CMain.java
 * EinarmigerBandit
 *
 * Copyright © 2021 Jan Kott. All rights reserved.
 */

public class CMain {

	/* Folgende Argumente zur JVM hinzufügen: -Xdock:name="Einarmiger Bandit" -Xdock:icon=images/icon.png -Dapple.awt.application.appearance=system */
	public static void main(String[] args) {
		CView myView = new CView();
		CModel myModel = new CModel();
		CController myController = new CController(myView, myModel);
		myController.initializeController();
	}
}
