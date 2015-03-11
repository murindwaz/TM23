package ca.concordia.game.model;

/**
 * Calss City card is a type of Card. It contains the 12 different Area Cards a unique card number and it's ability.
*@author Pascal Maniraho 
 *@author Gustavo Pereira
 *@author Bhavik Desai 
 *@author Jesus Esteban Garro Matamoros 
 *@author Diego Pizarro

 *
 */

public class CityCard extends Card {
	public String ability = null;
	private int cardNumber;
	/**
	 * Constructor: Depending on the integer sent it sets tha name and ability of a card.
	 * @param i
	 */
	public CityCard(int i) {
		//City Cards are always Visible!
		super(true,false);
		switch(i) {
			case 0:
				this.setName("The Shades");
				this.cardNumber=0;
				this.ability = "Once per turn you can place one trouble marker in the Shades or an adjacent area.";
				break;
			case 1:
				this.setName("Dolly Sisters");
				this.cardNumber=1;
				this.ability = "Once per turn you can pay $3 and place one of your minions in Dolly Sisters or an adjacent area.";
				break;
			case 2:
				this.setName("The Scours");
				this.cardNumber=2;
				this.ability = "Once per turn you can discard one card and take $2 from the bank.";
				break;
			case 3:
				this.setName("Small Gods");
				this.cardNumber=3;
				break;
			case 4:
				this.setName("The Hippo");
				this.cardNumber=4;
				break;
			case 5:
				this.setName("Longwall");
				this.cardNumber=5;
				break;
			case 6:
				this.setName("Seven Sleepers");
				this.cardNumber=6;
				break;
			case 7:
				this.setName("Nap Hill");
				this.cardNumber=7;
				break;
			case 8:
				this.setName("Dimwell");
				this.cardNumber=8;
				break;
			case 9:
				this.setName("Isle of Gods");
				this.cardNumber=9;
				break;
			case 10:
				this.setName("Dragon's Landing");
				this.cardNumber=10;
				break;
			case 11:
				this.setName("Unreal Estate");
				this.cardNumber=11;
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
	 * @return
	 */
	public int getCardNumber()
	{
		return this.cardNumber;
	}
	

}
