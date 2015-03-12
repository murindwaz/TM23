package ca.concordia.game.common;

<<<<<<< HEAD
import java.awt.Color;

public class common {
	public enum Colors {
		RED("RED"), BLUE("BLUE"), YELLOW("YELLOW"), GREEN("GREEN"), NONE("NONE");
		
		private final String stringValue;
		
		private Colors(String color) {
			switch(color.toUpperCase()) {
				case "RED":
				case "BLUE":
				case "YELLOW":
				case "GREEN":
					stringValue = color.toUpperCase();
					break;
				default:
					stringValue = "NONE";
			}
		}
		
		@Override public String toString() {
			return stringValue();
		}
		
		//Return the string which represents the color enum.
		public String stringValue() {
			return stringValue;
		}
		
		//Returns the actual color represented by the color enum.
		public Color color() {
			Color resp = new Color(1f,1f,1f,0f);
			switch(stringValue) {
			case "RED":
					resp = Color.RED;
					break;
			case "BLUE":
					resp = Color.BLUE;
					break;
			case "YELLOW":
					resp = Color.YELLOW;
					break;
			case "GREEN":
					resp = Color.GREEN;
					break;
			}
			return resp;
		}		
	}
	
	public enum CityAreas {
=======
public  class common {
	
	public enum CityAreas{
>>>>>>> bc68bf4fe0066b214b6dcf3f27c90677c5459619
		DOLLYSISTERS(1), UNREALESTATE(2), DRAGONSLANDING(3), SMALLGODS(4),
		THESCOURS(5), THEHIPPO(6), THESHADES(7), DIMWELL(8), LONGWALL(9), ISLEOFGODS(10),
		SEVENSLEEPERS(11), NAPHILL(12);
		
		private final int intValue;
		
		private CityAreas(int value) { 
			intValue = value;
		}
		
		//return integer value which represents the area.
<<<<<<< HEAD
		public int intValue() {
			return intValue;
		}
		
		//Returns the Human-readable string for a given area.
		public String stringValue() {
			String resp = "UNKNOWN CITY";
			
			switch(intValue) {
				case 1:
					resp = "Dolly Sisters";
					break;
				case 2:
					resp = "Unreal Estate";
					break;
				case 3:
					resp = "Dragon's Landing";
					break;
				case 4:
					resp = "Small Gods";
					break;
				case 5:
					resp = "The Scours";
					break;
				case 6:
					resp = "The Hippo";
					break;
				case 7:
					resp = "The Shades";
					break;
				case 8:
					resp = "Dimwell";
					break;
				case 9:
					resp = "Longwall";
					break;
				case 10:
					resp = "Isle of Gods";
					break;
				case 11:
					resp = "Seven Sleepers";
					break;
				case 12:
					resp = "Nap Hill";
					break;
			}
			
			return resp;
		}
	}
=======
		public int intValue()
		{
			return intValue();
		}
	}
	
	
>>>>>>> bc68bf4fe0066b214b6dcf3f27c90677c5459619

}
