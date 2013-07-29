import java.awt.Color;

public class ColorDecoder {
	
	private static boolean equalsIgnoreCase(String s1, String s2){
		return (s1.compareToIgnoreCase(s2) == 0);
	}
	public static Color getColor(String colorName){
		if (equalsIgnoreCase(colorName, "red")){
			return Color.red;
		}
		else if (equalsIgnoreCase(colorName, "black")){
			return Color.black;
		}
		else{
			return null;
		}
	}
}
