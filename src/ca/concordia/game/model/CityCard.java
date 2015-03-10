package ca.concordia.game.model;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Calss City card is a type of Card. It contains the 12 different Area Cards a unique card number and it's ability.
 * @author Pascal,Gustavo,bhavik,Esteban,Diego
 *
 */

public class CityCard extends Card {
	public String ability = null;
	private int cardNumber;
	private ArrayList<Integer> adjacentAreas; //Contains adjacent area codes referencing an area code.
	/**
	 * Constructor: Depending on the integer sent it sets tha name and ability of a card.
	 * @param i
	 */
	public CityCard(int i) {
		
		//City Cards are always Visible!
		super(true,false);
		
		this.adjacentAreas= new ArrayList<Integer>();
		Integer [] temp;
		switch(i) {
			case 0:
				this.setName("The Shades");
				this.cardNumber=1;
				this.ability = "Once per turn you can place one trouble marker in the Shades or an adjacent area.";
				temp= new Integer[]{2,3,12};
				this.adjacentAreas.addAll(Arrays.asList(temp));
				break;
			case 1:
				this.setName("Dolly Sisters");
				this.cardNumber=2;
				this.ability = "Once per turn you can pay $3 and place one of your minions in Dolly Sisters or an adjacent area.";
				temp= new Integer[]{1,3,4,10,11,12};
				this.adjacentAreas.addAll(Arrays.asList(temp));
				break;
			case 2:
				this.setName("The Scours");
				this.cardNumber=3;
				this.ability = "Once per turn you can discard one card and take $2 from the bank.";
				temp= new Integer[]{1,2,4};
				this.adjacentAreas.addAll(Arrays.asList(temp));
				break;
			case 3:
				this.setName("Small Gods");
				this.cardNumber=4;
				temp= new Integer[]{2,3,5,6,10};
				this.adjacentAreas.addAll(Arrays.asList(temp));
				break;
			case 4:
				this.setName("The Hippo");
				this.cardNumber=5;
				temp= new Integer[]{4,6,7,8,10};
				this.adjacentAreas.addAll(Arrays.asList(temp));
				break;
			case 5:
				this.setName("Longwall");
				this.cardNumber=6;
				temp= new Integer[]{4,5,7};
				this.adjacentAreas.addAll(Arrays.asList(temp));
				break;
			case 6:
				this.setName("Seven Sleepers");
				this.cardNumber=7;
				temp= new Integer[]{5,6,8};
				this.adjacentAreas.addAll(Arrays.asList(temp));
				break;
			case 7:
				this.setName("Nap Hill");
				this.cardNumber=8;
				temp= new Integer[]{5,7,9};
				this.adjacentAreas.addAll(Arrays.asList(temp));
				break;
			case 8:
				this.setName("Dimwell");
				this.cardNumber=9;
				temp= new Integer[]{8,10,11};
				this.adjacentAreas.addAll(Arrays.asList(temp));
				break;
			case 9:
				this.setName("Isle of Gods");
				this.cardNumber=10;
				temp= new Integer[]{2,4,5,9,11};
				this.adjacentAreas.addAll(Arrays.asList(temp));
				break;
			case 10:
				this.setName("Dragon's Landing");
				this.cardNumber=11;
				temp= new Integer[]{2,9,10,12};
				this.adjacentAreas.addAll(Arrays.asList(temp));
				break;
			case 11:
				this.setName("Unreal Estate");
				this.cardNumber=12;
				temp= new Integer[]{1,2,11};
				this.adjacentAreas.addAll(Arrays.asList(temp));
				break;
			default:
				System.out.println("Initializing City Card with the wrong index");
				break;
		}
	}
	

	/**
	 * Constructor takes String.
	 * @param name
	 */
	public CityCard (String name) {
		//City Cards are always Visible!
		super(true,false);
		
		this.setName(name);
	}
	

	/**
	 * getter Unique card Number.
	 * @return int
	 */
	public int getCardNumber()
	{
		return this.cardNumber;
	}
	
	/**
	 * checks whether an area is adjacent to the current city area.
	 * @param areaCode
	 * @return boolean
	 */
	public boolean isAdjacent(int areaCode)
	{
		return this.adjacentAreas.contains(areaCode);
	}

}
