package ca.concordia.game.util;

import java.util.ArrayList;

import ca.concordia.game.main.Game;
import ca.concordia.game.model.*;

import java.io.File;
import java.io.IOException;
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
		ClassLoader classLoader = getClass().getClassLoader();
		String fileName = "Players.txt";
		File file = new File(classLoader.getResource(fileName).getFile());
	 
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				cards.add(line);
			}
			scanner.close();
	 
		} catch (IOException e) {
			e.printStackTrace();
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
