package ca.concordia.game.model;

import java.util.ArrayList;

import ca.concordia.game.common.common.Colors;
import ca.concordia.game.util.*;

/**
 * Brown card is a type of Card. 
 * The brown card contains a unique number and the color brown.
 *	@author Pascal Maniraho 
 *	@author Gustavo Pereira
 *	@author Bhavik Desai 
 *	@author Jesus Esteban Garro Matamoros 
 *	@author Diego Pizarro
 */
public class BrownCard extends Card {
	
	private Colors color;
	private int number;
	private String name;
	private ArrayList<Symbol> symbols;
	private Action especialAbility;
	
	/**
	 * Constructor
	 * @param i
	 */
	public BrownCard(int i) {
		//City Cards are always Visible!
		super(false,false);
		this.color = Colors.BROWN;
		this.number = i;
		this.name = CardLoader.getInstance().nameForCard(i,"B");		
		//this.especialAbility = new Action(i);
	}
	
	/**
	 * This constructor will be used with CardLoader to initialze the cards in a Map 
	 * @param number
	 * @param name
	 * @param color
	 */
	public BrownCard( int number, String name, Colors color ){
		this( number ); 
		this.number = number; 
		this.name = name; 
		this.color = color; 
	}
	
	/**
	 * Getter
	 * @return
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