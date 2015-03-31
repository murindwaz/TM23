package ca.concordia.game.model;

import java.util.ArrayList;

import ca.concordia.game.common.common.Colors;
import ca.concordia.game.util.*;

/**
 * Brown card is a type of Card. 
 * The brown card contains a unique number,the color brown, and array of the symbols that specify the actions that have to be followed sequentially,
 * and a description of the cards especialAbility(Action) if one exits(There area cards that don't have any especial action).For a description of the 
 * symbol actions see the Symbol class.
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
	private String especialAbility;

	/**
	 * Constructor will load the contents of the card from class Cardloader, Further it will set
	 * and initialize all the symbols that belong to a BrownCard.
	 * @param i(int)
	 */
	public BrownCard(int i) {
		//City Cards are always Visible!
		super(false,false);
		
		this.symbols=new ArrayList<Symbol>();
		
		String temp="";
		String symbolId="";//Declare as empty
		boolean takeMoney=false;
		int moneyIdSymbol=-1;
		
		this.color = Colors.BROWN;
		this.number = i;
		this.name = CardLoader.getInstance().nameForCard(i,'B');		
		this.especialAbility = CardLoader.getInstance().abilityForCard(i, 'B');
		temp = CardLoader.getInstance().symbolsForCard(i, 'B');
		//check that the card has content for symbols.And that the card has symbols.
		if(temp.length()>0 && !temp.equals("NO Symbols AVAILABLE"))
			for(int j=0;j<temp.length();j++)
			{
				//If the last symbol was 'M' then the next character will represent the amount of money the player can take.For symbol 5(Take money from bank)
				if(symbolId.equals("M"))
				{
					symbolId= Character.toString(temp.charAt(j));//Get money for player to take.
					this.symbols.add(new Symbol(moneyIdSymbol,moneyIdSymbol));
				}
				else
				{
					symbolId=Character.toString(temp.charAt(j));//Get Symbols ID.
					takeMoney=false;
				}
				
				
				if(symbolId.equals("M"))
				{//The symbol is of type 5(Taking money from the bank).
					moneyIdSymbol=5;
					takeMoney=true;
				}else if(takeMoney==false && !symbolId.equals("M"))
				{//The symbol has another type from 5(taking money), so add it to the symbol array.
					int symbol=Integer.parseInt(symbolId);
					this.symbols.add(new Symbol(symbol,-1));
				}
			}
		else
			System.out.println("Error the symbol Card is empty!!!!");
	}
	
	/**
	 * This constructor will be used with CardLoader to initialize the cards in a Map 
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