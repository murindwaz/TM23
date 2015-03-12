package ca.concordia.game.model;


import ca.concordia.game.common.common.Colors;

/**
 * This class will only contain the color of the Piece.
 * @author Pascal Maniraho 
 * @author Gustavo Pereira
 * @author Bhavik Desai 
 * @author Jesus Esteban Garro Matamoros 
 * @author Diego Pizarro
 */
public class Piece {
	
	private Colors color;
	

	/**
	 * Contructor
	 * @param color
	 */
	public Piece(Colors color)
	{
		this.color=color;
		
	}
	

	/**
	 * Getter color of current piece(Minion).
	 * @return
	 */
	public Colors getColor()
	{
		return this.color;
	}
	

	/**
	 * toString method for class Color.
	 */
	@Override
	public String toString()
	{
		return "Color: " + this.color.toString();
	}

}
