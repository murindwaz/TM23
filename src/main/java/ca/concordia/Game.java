package ca.concordia;

import java.util.HashMap;
import java.util.Map;
import java.awt.Color;

import ca.concordia.game.model.*;


/**
 * This is temporal entry point. 
 * @author root
 */
public class Game {
	
	private static Game instance = null;
	
	private Player[] players; 
	private Gameboard gameboard;
	private Map<String,Deck> decks;
	private Bank bank;
	private Die die;
	
	public int currentPlayer;
	public int numberOfPlayers;
	
	
	private void init() {
		this.bank = Bank.getInstance();
		this.decks = new HashMap<String,Deck>();
		//There is exactly 5 decks per game + a Discard Deck
		for(int i = 0 ; i <= 5 ; i++) {
			switch(i) {
				case 0:
					this.decks.put("discard", new Deck("D"));
					break;
				case 1:
					this.decks.put("personalities", new Deck("P"));
					break;
				case 2:
					this.decks.put("cities", new Deck("C"));
					break;
				case 3:
					this.decks.put("events", new Deck("E"));
					break;
				case 4:
					this.decks.put("green", new Deck("G"));
					break;
				case 5:
					this.decks.put("brown", new Deck("B"));
					break;
			}
		}
		
		//Select number of players and their colors.
		//For now we use four players of fixed colors:
		
		numberOfPlayers = 4;
		
		this.players = new Player[numberOfPlayers];
		
		for(int i=0; i<numberOfPlayers; i++) {
			this.players[i] = new Player((PersonalityCard)decks.get("personalities").getCard(),Color.RED);
			//Deal 5 green cards to each player:
			decks.get("green").dealCardsToPlayer(players[i],5);
			//Give $10 to each player:
			bank.transferFunds(players[i], 10);
		}
		
		//Initialize Gameboard:
		
		//Prompt each player to roll dice to pick first player.
		//For now:
		currentPlayer = 0;
	}
	
	//Implements Game as a singleton, as there will always be a single game per run.
	public static Game getInstance() {
		if(instance == null) {
			instance = new Game();
		}
		return instance;
	}
	
	public Game() {
		init();
	}
	
	public boolean equals(Game gameState){
		//@todo check the number of players
		//@todo check occupied areas
		//@todo check the bank
		return false;
	}
	
	public int nextPlayer() {
		currentPlayer = (currentPlayer + 1)%numberOfPlayers;
		return currentPlayer;
	}

}
