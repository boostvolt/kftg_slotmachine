/*
 * CMain.java
 * EinarmigerBandit
 *
 * Copyright © 2021 Jan Kott. All rights reserved.
 */

package com.jankott.einarmigerbandit;

public class CMain {

    public static void main(String[] args) {
        CView myView = new CView();
        CModel myModel = new CModel();
        CController myController = new CController(myView, myModel);
        myController.initializeController();
    }
}
