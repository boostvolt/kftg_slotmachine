
import javax.swing.ImageIcon;

public interface ISymbol {
	/* Setter method for the image of the Symbol object */
	void setImage(String url);

	/* Returns the image of the Symbol object */
	ImageIcon getImage();

	/* Setter method for the value of the Symbol object */
	void setValue(int v);

	/* Returns the value of the Symbol object */
	int getValue();

}
