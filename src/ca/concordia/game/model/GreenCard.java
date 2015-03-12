package ca.concordia.game.model;

import java.util.ArrayList;

/**
 * Green card is a type of Card. for this version of the system it contains a unique number and the color Green for all cards of this type.
*@author Pascal Maniraho 
 *@author Gustavo Pereira
 *@author Bhavik Desai 
 *@author Jesus Esteban Garro Matamoros 
 *@author Diego Pizarro

 *
 */
public class GreenCard extends Card {
	
	private String color;
	private int number;
	private ArrayList<Symbol> actionSymbols;
	
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
	 * Getter: Returns the array of symbols which represent the different actions the card can perform sequentially.
	 * @return ArrayList<Symbol>
	 */
	public ArrayList<Symbol> getActionsSymbols()
	{
		return this.actionSymbols;
	}
	
	/**
	 * Getter unique Number
	 * @return int
	 */
	public int getNumber()
	{
		return this.number;
	}
}
