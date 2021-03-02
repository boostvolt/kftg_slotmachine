/*
 * CMain.java
 * EinarmigerBandit
 *
 * Copyright © 2021 Jan Kott. All rights reserved.
 */

public class CMain {

	/* Folgende Argumente zur JVM hinzufügen: -Xdock:name="Einarmiger Bandit" -Xdock:icon=images/icon.png -Dapple.awt.application.appearance=system */
	public static void main(String[] args) {
		CView view = new CView();
		CModel model = new CModel();
		CController controller = new CController(view, model);
		controller.initializeController();
	}
}
