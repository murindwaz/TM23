package ca.concordia.game.model;

/**
 * Brown card is a type of Card. for this version of the system it contains a unique number and the color Brown for all cards of this type.
 * @author Pascal,Gustavo,bhavik,Esteban,Diego
 *
 */

public class BrownCard extends Card {
	
	private String color;
	private int number;
	
	/**
	 * Constructor
	 * @param i
	 */
	public BrownCard(int i) {
		//City Cards are always Visible!
		super(false,false);
		
		this.color="BROWN";
		this.number=i;
		//Handle 53 cases:
		switch(i) {
			
		}
	}
	
	/**
	 * Getter
	 * @return
	 */
	public int getNumber()
	{
		return this.number;
	}
}
