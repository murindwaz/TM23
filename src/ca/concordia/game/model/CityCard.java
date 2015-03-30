package ca.concordia.game.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Calss City card is a type of Card. It contains the 12 different Area Cards a
 * unique card number and it's ability.
 *
 * @author Pascal Maniraho
 * @author Gustavo Pereira
 * @author Bhavik Desai
 * @author Jesus Esteban Garro Matamoros
 * @author Diego Pizarro
 *
 */

public class CityCard extends Card {
	public String ability = null;
	private int cardNumber;
	private ArrayList<Integer> adjacentAreas; // Contains adjacent area codes
												// referencing an area code.
	private int buildingCost;
	public Boolean doesFlood = false;

	/**
	 * Constructor: Depending on the integer sent it sets the name and ability
	 * of a card and the adjacent areas to the city card.
	 * 
	 * @param i
	 */
	public CityCard(int i) {

		// City Cards are always Visible!
		super(true, false);

		this.adjacentAreas = new ArrayList<Integer>();
		Integer[] temp;
		switch (i) {
		case 0:
			this.setName("Dolly Sisters");
			this.cardNumber = 1;
			this.buildingCost = 6;
			this.ability = "Once per turn you can pay $3 and place one of your minions in Dolly Sisters or an adjacent area.";
			temp = new Integer[] { 2, 3, 12 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			this.doesFlood = true;
			break;
		case 1:
			this.setName("Unreal Estate");
			this.cardNumber = 2;
			this.buildingCost = 18;
			this.ability = "";
			temp = new Integer[] { 1, 3, 4, 10, 11, 12 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			this.doesFlood = true;
			break;
		case 2:
			this.setName("Dragon's Landing");
			this.cardNumber = 3;
			this.buildingCost = 12;
			this.ability = "";
			temp = new Integer[] { 1, 2, 4 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			break;
		case 3:
			this.setName("Small Gods");
			this.cardNumber = 4;
			this.buildingCost = 18;
			temp = new Integer[] { 2, 3, 5, 6, 10 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			break;
		case 4:
			this.setName("The Scours");
			this.cardNumber = 5;
			this.buildingCost = 6;
			temp = new Integer[] { 4, 6, 7, 8, 10 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			this.ability = "Once per turn you can discard one card and take $2 from the bank.";
			this.doesFlood = true;
			break;
		case 5:
			this.setName("The Hippo");
			this.cardNumber = 6;
			this.buildingCost = 12;
			temp = new Integer[] { 4, 5, 7 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			break;
		case 6:
			this.setName("The Shades");
			this.cardNumber = 7;
			this.buildingCost = 6;
			temp = new Integer[] { 5, 6, 8 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			this.ability = "Once per turn you can place one trouble marker in the Shades or an adjacent area.";
			this.doesFlood = true;
			break;
		case 7:
			this.setName("Dimwell");
			this.cardNumber = 8;
			this.buildingCost = 6;
			temp = new Integer[] { 5, 7, 9 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			this.doesFlood = true;
			break;
		case 8:
			this.setName("Longwall");
			this.cardNumber = 9;
			this.buildingCost = 12;
			temp = new Integer[] { 8, 10, 11 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			break;
		case 9:
			this.setName("Isle of Gods");
			this.cardNumber = 10;
			this.buildingCost = 12;
			temp = new Integer[] { 2, 4, 5, 9, 11 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			this.doesFlood = true;
			break;
		case 10:
			this.setName("Seven Sleepers");
			this.cardNumber = 11;
			this.buildingCost = 18;
			temp = new Integer[] { 2, 9, 10, 12 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			this.doesFlood = true;
			break;
		case 11:
			this.setName("Nap Hill");
			this.cardNumber = 12;
			this.buildingCost = 12;
			temp = new Integer[] { 1, 2, 11 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			this.doesFlood = true;
			break;
		default:
			System.out.println("Initializing City Card with the wrong index");
			break;
		}
	}

	/**
	 * Constructor takes String.
	 * 
	 * @param name
	 */
	public CityCard(String name) {
		// City Cards are always Visible!
		super(true, false);
		this.setName(name);
	}

	/**
	 * getter Unique card Number.
	 * 
	 * @return int
	 */
	public int getCardNumber() {
		return this.cardNumber;
	}

	/**
	 * getter cost of putting a building.
	 * 
	 * @return int
	 */
	public int getBuldingCost() {
		return this.buildingCost;
	}

	/**
	 * checks whether an area is adjacent to the current city area.
	 * 
	 * @param areaCode
	 * @return boolean
	 */
	public boolean isAdjacent(int areaCode) {
		return this.adjacentAreas.contains(areaCode);
	}

	/**
	 * Return all adjacent areas to the one being called.
	 * 
	 * @return ArrayList<Integer>
	 */
	public ArrayList<Integer> getAdjacentAreas() {
		return this.adjacentAreas;
	}

}
