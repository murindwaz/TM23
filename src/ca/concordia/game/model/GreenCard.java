package ca.concordia.game.model;

/**
 * Green card is a type of Card. for this version of the system it contains a unique number and the color Green for all cards of this type.
 * @author Pascal,Gustavo,bhavik,Esteban,Diego
 *
 */
public class GreenCard extends Card {
	
	private String color;
	private int number;
	
	/**
	 * Constructor
	 * @param i
	 */
	public GreenCard(int i) {
		//City Cards are always Visible!
		super(false,false);
		
		this.color="GREEN";
		this.number=i;
		//Handle 48 cases:
		switch(i) {
			
		}
	}	
	
	/**
	 * Getter unique Number
	 * @return
	 */
	public int getNumber()
	{
		return this.number;
	}
}
