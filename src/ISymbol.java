
import javax.swing.ImageIcon;

public interface ISymbol {
	/* Setter method for the image of the Symbol object */
	void setImage(String url);

	/* Returns the image of the Symbol object */
	ImageIcon getImage();

	/* Returns the value of the Symbol object */
	int getValue();

}
