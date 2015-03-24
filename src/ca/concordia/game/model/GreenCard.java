package ca.concordia.game.model;

import java.util.ArrayList;

import ca.concordia.game.util.*;

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
	private String name;
	private ArrayList<Symbol> symbols;
	private Action especialAbility;

	/**
	 * Constructor
	 * @param i
	 */
	public GreenCard(int i) {
		//City Cards are always Visible!
		super(false,false);
		this.symbols = new ArrayList<Symbol>();
		this.color="GREEN";
		this.number=i;
		this.name = CardLoader.getInstance().nameForCard(i,"G");		
		this.especialAbility = new Action(i);
	}	
	
	/**
	 * The GreenCard will be used to initialize cards CardLoader
	 * @param number
	 * @param name
	 * @param color
	 */
	public GreenCard( int number, String name, String color){
		this( number ); 
		this.number = number; 
		this.name = name;
		this.color = color;
	}
	
	
	/**
	 * Getter: Returns the array of symbols which represent the different actions the card can perform sequentially.
	 * @return ArrayList<Symbol>
	 */
	public ArrayList<Symbol> getActionsSymbols(){
		return this.symbols;
	}
	
	/**
	 * Getter unique Number
	 * @return int
	 */
	public int getNumber(){
		return this.number;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String getAbility(){
		return this.especialAbility.toString();
	}
}