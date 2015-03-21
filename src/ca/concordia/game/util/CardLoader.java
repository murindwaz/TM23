package ca.concordia.game.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;


public class CardLoader {
	private static CardLoader instance = null;
	private ArrayList<String> cards;
	
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
	 */
	public CardLoader() {
		//Open Resource file:
		cards = new ArrayList<String>();
		for( Entry<Object, Object> property : Configuration.PLAYER_PROPERTIES.entrySet() ){
			System.out.println( property.getValue().toString() );
			cards.add( property.getValue().toString() );
		}
	}
	
	public String nameForCard(int i,String type) {
		String tmp = "";
		switch(type) {
			case "B":
				if(i > 0) {
					tmp = cards.get(i-1);
				}
				break;
			case "G":
				if(i < 49) {
					tmp = cards.get(i+52);
				} else {
					return "NO NAME FOR CARD WITH INDEX " + i;
				}
				break;
			default:
				break;
		}
		
		String resp = "NO NAME AVAILABLE";
		String[] arr = tmp.split("\\|");
		if(arr.length > 2)
			resp = arr[2]; 

		return resp;
	}
	
	public String abilityForCard(int i,String type) {
		
		String tmp = "";
		switch(type) {
			case "B":
				if (i > 0) {
					tmp = cards.get(i-1);
				}
				break;
			case "G":
				if(i < 49) {
					tmp = cards.get(i+52);
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
