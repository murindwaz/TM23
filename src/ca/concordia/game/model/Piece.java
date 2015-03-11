package ca.concordia.game.model;

/**
 * This class will only contain the color of the Piece.
 * @author Pascal,Gustavo,bhavik,Esteban,Diego
 *
 */

public class Piece {
	
	private String color;
	

	/**
	 * Contructor
	 * @param color
	 */
	public Piece(String color)
	{
		this.color=color;
		
	}
	

	/**
	 * Getter color of current piece(Minion).
	 * @return
	 */
	public String getColor()
	{
		return this.color;
	}
	

	/**
	 * toString method for class Color.
	 */
	public String toString()
	{
		return "Color: "+ this.color;
	}

}
