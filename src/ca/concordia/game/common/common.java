package ca.concordia.game.common;

import java.awt.Color;

public class common {
	
	//Minimum number of cards a player can have.
	public static final int numberOfCard=5;
	
	public static final int maxNumberOfDemons=4;
	public static final int maxNumberOfTrolls=3;
	public static final int beginingOfPlayersLoadGame=13;
	
	
	public enum Colors {
		RED("RED"), BLUE("BLUE"), YELLOW("YELLOW"), GREEN("GREEN"), BROWN("BROWN"),NONE("NONE");
		
		private final String stringValue;
		
		private Colors(String color) {
			switch(color.toUpperCase()) {
				case "RED":
				case "BLUE":
				case "YELLOW":
				case "GREEN":
				case "BROWN":
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
		
		//Return the Colors based on an index.
		public static Colors colorForIndex(int i) {
			switch(i%5) {
				case 0:
					return Colors.BLUE;
				case 1:
					return Colors.GREEN;
				case 2:
					return Colors.YELLOW;
				case 3:
					return Colors.RED;
				case 4:
					return Colors.BROWN;
			}
			
			return Colors.NONE;
		}
		
		//Returns the Colors based on a string.
		public static Colors colorForString(String s) {
			switch(s.toUpperCase()) {
			case "RED":
				return Colors.RED;
			case "BLUE":
				return Colors.BLUE;
			case "YELLOW":
				return Colors.YELLOW;
			case "GREEN":
				return Colors.GREEN;
			case "BROWN":
				return Colors.BROWN;
			default:
				return Colors.NONE;
			}
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
			//TODO:add Brown Color
			}
			return resp;
		}		
	}
	
	public enum CityAreas {
		DOLLYSISTERS(1), UNREALESTATE(2), DRAGONSLANDING(3), SMALLGODS(4),
		THESCOURS(5), THEHIPPO(6), THESHADES(7), DIMWELL(8), LONGWALL(9), ISLEOFGODS(10),
		SEVENSLEEPERS(11), NAPHILL(12);
		
		private final int intValue;
		
		private CityAreas(int value) { 
			intValue = value;
		}
		
		//return integer value which represents the area.

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

}
