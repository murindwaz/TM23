package ca.concordia.game.model;

import java.util.ArrayList;
import ca.concordia.game.util.*;

/**
 * Brown card is a type of Card. for this version of the system it contains a unique number and the color Brown for all cards of this type.
*@author Pascal Maniraho 
 *@author Gustavo Pereira
 *@author Bhavik Desai 
 *@author Jesus Esteban Garro Matamoros 
 *@author Diego Pizarro

 *
 */

public class BrownCard extends Card {
	
	private String color;
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
		
		this.color="BROWN";
		this.number=i;

		this.name = CardLoader.getInstance().nameForCard(i,"B");		
		this.especialAbility = new Action(CardLoader.getInstance().abilityForCard(i,"B"));
	}
	
	/**
	 * Getter
	 * @return
	 */
	public int getNumber()
	{
		return this.number;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String getAbility()
	{
		return this.especialAbility.toString();
	}
}
