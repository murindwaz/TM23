package ca.concordia.game.model;

/**
 * This class will only contain the color of the Piece.
 * @author Pascal Maniraho 
 * @author Gustavo Pereira
 * @author Bhavik Desai 
 * @author Jesus Esteban Garro Matamoros 
 * @author Diego Pizarro
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
	@Override
	public String toString()
	{
		return "Color: "+ this.color;
	}

}
