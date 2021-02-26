/*******************************************************************************
 *
 * CMain.java
 * EinarmigerBandit
 *
 * Copyright © 2021 Jan Kott. All rights reserved.
 *
 ******************************************************************************/

public class CMain {

	public static void main(String[] args) {
		CView view = new CView();
		CModel model = new CModel();
		CController controller = new CController(view, model);
		controller.initialise();
	}
}
