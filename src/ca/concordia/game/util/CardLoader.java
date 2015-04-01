package ca.concordia.game.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import ca.concordia.game.model.BrownCard;
import ca.concordia.game.model.GreenCard;


public class CardLoader {
	private static CardLoader instance = null;
	private ArrayList<String> cards;
	private String temp;
	/**
	 * The first card, works with raw string, and the second works with real objects
	 * Using the Map<Integer, String>
	 * Using the Map<Integer, Card>
	 */
	private Map<Integer, String> mcards;
	private Map<Integer, BrownCard> brownCards;
	private Map<Integer, GreenCard> greenCards;
	
	
	
	/**
	 * Implements CardLoader as a singleton.
	 * @return CardLoader
	 */
	public static CardLoader getInstance() {
		if(instance == null) {
			instance = new CardLoader();
		}
		return instance;
	}
	
	/**
	 * Constructor, initializes Cards.
	 * CardLoader is a singleton, and is made un-accessible unless to Children. 
	 */
	protected CardLoader() {
		String data = "";
		int index = -1;
		cards = new ArrayList<String>();
		mcards = new HashMap<Integer, String>();//
		greenCards = new HashMap<Integer, GreenCard>(); 
		brownCards = new HashMap<Integer, BrownCard>();
		cards.ensureCapacity( Configuration.PLAYER_PROPERTIES.size() );
		for( Entry<Object, Object> property : Configuration.PLAYER_PROPERTIES.entrySet() ){
			data = property.getValue().toString();
			String[] extracted = data.split("\\|");
			index = Integer.parseInt( extracted[0] );
			/**
			 * @todo parse and add brownCards
			 * @todo parse and add greenCards
			 */
			mcards.put( new Integer(index) , data );
			//System.out.println("Index: "+index+" Data:"+data);
		}
		
	}
	
	public String nameForCard(int i, char type) 
	{
		temp = "";
		
		getCard(i,type);//Get card.
		//System.out.println(temp);
		if(temp.contains("Index doesn't exist:"))
			return temp;
		
		String resp = "NO NAME AVAILABLE";
		if( temp.isEmpty() ){
			return resp;
		}
		String[] arr = temp.split("\\|");
		if( arr.length > 2 ){
			resp = arr[2]; 
		}
		
		return resp;
	}
	
	public String abilityForCard(int i,char type) 
	{
		
		temp = "";
		
		getCard(i,type);//Get card.
		if(temp.contains("Index doesn't exist:"))
			return temp;
		
		String resp = "NO ABILITY AVAILABLE";
		String[] arr = temp.split("\\|");
		if(arr.length > 5)
			resp = arr[5]; 

		return resp;
	}
	
	public String symbolsForCard(int i,char type)
	{
		temp = "";
		
		getCard(i,type);//Get card.
		if(temp.contains("Index doesn't exist:"))
			return temp;
		
		String resp = "NO Symbols AVAILABLE";
		String[] arr = temp.split("\\|");
		if(arr.length > 4)
			resp = arr[4]; 
		//System.out.println(resp);
		return resp;
	}
	
	/**
	 * GetCard returns the respective card information based on the card id(i) and the type of the card(type).
	 * @param i(int)
	 * @param type(String)
	 * @return String
	 */
	private void getCard(int i,char type)
	{
		temp = "";
		switch(type) 
		{
			case 'B':
				if (i > 0) {
					temp = mcards.get(i);
				}
				break;
			case 'G':
				if(i >= 54) {
					temp = mcards.get(i);
				} else {
					temp= "Index doesn't exist:  " + i;
				}
				break;
			default:
				break;
		}
		
	}

}
