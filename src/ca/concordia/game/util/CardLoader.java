package ca.concordia.game.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import ca.concordia.game.common.common.Colors;
import ca.concordia.game.model.BrownCard;
import ca.concordia.game.model.GreenCard;


public class CardLoader {
	private static CardLoader instance = null;
	private ArrayList<String> cards;
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
	 * This constructor loads Green and Blue cards into re-usable data structures
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
			int number = index; 
			String name = extracted[2]; 
			Colors color = extracted[1] == "B" ? Colors.BROWN : Colors.GREEN; 
			String ability = extracted[5];
			if( color == Colors.BROWN ){
				brownCards.put(new Integer(index), new BrownCard(index, name, color));
			}else{
				greenCards.put(new Integer(index), new GreenCard(index, name, color));
			}
			mcards.put( new Integer(index) , data );
		}
	}
	
	public String nameForCard(int i, String type) {
		String tmp = "";
		switch(type) {
			case "B":
				if(i > 0) {
					tmp = mcards.get( i - 1 );
				}
				break;
			case "G":
				if(i < 49) {
					tmp = mcards.get(i+53);
				} else {
					return "NO NAME FOR CARD WITH INDEX " + i;
				}
				break;
			default:
				break;
		}
		
		String resp = "NO NAME AVAILABLE";
		if( tmp.isEmpty() ){
			return resp;
		}
		String[] arr = tmp.split("\\|");
		if( arr.length > 2 ){
			resp = arr[2]; 
		}
		
		return resp;
	}
	
	public String abilityForCard(int i,String type) {
		
		String tmp = "";
		switch(type) {
			case "B":
				if (i > 0) {
					tmp = mcards.get( i - 1 );
				}
				break;
			case "G":
				if(i < 49) {
					tmp = mcards.get(i+52);
				} else {
					return "NO ABILITY FOR CARD WITH INDEX " + i;
				}
				break;
			default:
				break;
		}
		
		String resp = "NO ABILITY AVAILABLE";
		String[] arr = tmp.split("\\|");
		if(arr.length > 5)
			resp = arr[5]; 

		return resp;
	}

}
