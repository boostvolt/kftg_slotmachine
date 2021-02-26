/*
 * CSymbol.java
 * EinarmigerBandit
 *
 * Copyright © 2021 Jan Kott. All rights reserved.
 */

import java.awt.Image;
import javax.swing.ImageIcon;

public class CSymbol implements ISymbol {
	/*Declaring the private variables to hold the value and the image of the Symbol Object */
	private final int value;
	private ImageIcon image;
	
	/* Declaring the constructor for the type Symbol*/
	public CSymbol(int value, String url){
		this.value=value;
		this.setImage(url);
	}
	/*Setter method for the image of the Symbol object*/
	@Override
	public void setImage(String url){
		this.image= new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH));
	}

	/*Returns the image of the Symbol object*/
	@Override
	public ImageIcon getImage(){
		return this.image;
	}

	/*Returns the value of the Symbol object*/
	@Override
	public int getValue(){
		return this.value;
	}
	
	/*Compares two Symbol objects and returns true if both objects have same Value. Returns false if not */
	public boolean compareSymbols(CSymbol obj1, CSymbol obj2){
		return obj1.value==obj2.value;	
	}
}
