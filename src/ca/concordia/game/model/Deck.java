package ca.concordia.game.model;

import java.util.Deque;
import java.util.ArrayDeque;

/**
 * Class Deck Creates the decks of cards.
 * @author Pascal,Gustavo,bhavik,Esteban,Diego
 *
 */

//A deck is a double-ended queue of cards: We can take from the front and put in the back 
public class Deck {
	
	private Deque<Card> cards;
	
	/**
	 * Constructor: Creates an Array of cards depending on the type of the card which is specified as an argument.
	 * @param type
	 */
	public Deck(String type){
		switch(type) {
			case "D":
				//The discard deck is empty to start with
				this.cards = new ArrayDeque<Card>();
				break;
			case "P":
				//The personality deck has 7 cards:
				this.cards = new ArrayDeque<Card>();
				for (int i = 0; i < 7; i++) {
					this.cards.add(new PersonalityCard(i));
				}
				break;
			case "C":
				//The city deck has 12 cards:
				this.cards = new ArrayDeque<Card>();
				for (int i = 0; i < 12; i++) {
					this.cards.add(new CityCard(i));
				}
				break;
			case "E":
				//The event deck has 12 cards:
				this.cards = new ArrayDeque<Card>();
				for (int i = 0; i < 12; i++) {
					this.cards.add(new EventCard(i));
				}
				break;
			case "G":
				//The green-bordered player deck has 53 cards:
				this.cards = new ArrayDeque<Card>();
				for (int i = 49; i < 102; i++) {
					this.cards.add(new GreenCard(i));
				}
				break;
			case "B":
				//The brown-bordered player deck has 48 cards:
				this.cards = new ArrayDeque<Card>();
				for (int i = 0; i < 48; i++) {
					this.cards.add(new BrownCard(i));
				}
				break;
			default:
				System.out.println("Error initializing deck of unknown type");
				break;
		}
		
		//After creating a deck of cards, shuffle the cards:
		shuffle();
	}
	
	/**
	 * Suffle Deck
	 */
	public void shuffle() {
		//Shuffles the cards in this deck (Reorder queue)
	}
	
	/**
	 * Get first card of a deck
	 * @return
	 */
	public Card getCard() {
		return cards.pop();
	}
	/**
	 * put Card in a deck.
	 * @param aCard
	 */
	public void putCard(Card aCard) {
		cards.addLast(aCard);
	}
	
	/**
	 * deal card to players.
	 * @param aPlayer
	 * @param num
	 */
	public void dealCardsToPlayer(Player aPlayer, int num) {
		for(int i=0; i<num; i++) {
			aPlayer.receiveCard(cards.pop());
		}
	}
}
