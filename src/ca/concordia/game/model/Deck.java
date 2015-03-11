package ca.concordia.game.model;

import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Random;
<<<<<<< HEAD
<<<<<<< HEAD
=======

import ca.concordia.game.model.Card;
>>>>>>> origin/master
=======

import ca.concordia.game.model.Card;
>>>>>>> 199818afe8100991ffae794dbcebaff5513bca99

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
<<<<<<< HEAD
	public Deck(String type,int numPlayers){
=======
	public Deck(String type){
>>>>>>> 199818afe8100991ffae794dbcebaff5513bca99
		switch(type) {
			case "D":
				//The discard deck is empty to start with
				this.cards = new ArrayDeque<Card>();
				break;
			case "P":
				//The personality deck has 7 cards:
				this.cards = new ArrayDeque<Card>();
				for (int i = 0; i < 7; i++) {
<<<<<<< HEAD
					this.cards.add(new PersonalityCard(i,numPlayers));
=======
					this.cards.add(new PersonalityCard(i));
>>>>>>> 199818afe8100991ffae794dbcebaff5513bca99
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
<<<<<<< HEAD
<<<<<<< HEAD
		
=======
>>>>>>> origin/master
=======
>>>>>>> 199818afe8100991ffae794dbcebaff5513bca99
		//Shuffles the cards in this deck
		int max = this.cards.size();

		Card[] tmp = new Card[max];
		//Fill in the array:
		for(int i = 0; i < max; i++) {
			tmp[i] = this.cards.getFirst();
		}
<<<<<<< HEAD
<<<<<<< HEAD
				
		//Fisher-Yates shuffle:
		Random rnd = new Random();
		for(int i = max-1; i>0; i++) {
			int idx = rnd.nextInt(i+1);	
=======
=======
>>>>>>> 199818afe8100991ffae794dbcebaff5513bca99
		
		//Fisher-Yates shuffle:
		Random rnd = new Random();
		for(int i = max-1; i>0; i++) {
			int idx = rnd.nextInt(i+1);
	
<<<<<<< HEAD
>>>>>>> origin/master
=======
>>>>>>> 199818afe8100991ffae794dbcebaff5513bca99
			//Perform swap:
			Card a = tmp[idx];
			tmp[idx] = tmp[i];
			tmp[i] = a;
		}
<<<<<<< HEAD
<<<<<<< HEAD
				
=======
		
>>>>>>> origin/master
=======
		
>>>>>>> 199818afe8100991ffae794dbcebaff5513bca99
		//Put back the cards:
		for(int i = 0; i < max; i++) {
			this.cards.add(tmp[i]);
		}
	}
	
	/**
<<<<<<< HEAD
	 * Get first card of a deck; if the deck still has cards otherwise it will return null.
	 * @return Card
	 */
	public Card getCard() {
		if(cards.size()>0)
			return cards.pop();
		else 
			return null;
=======
	 * Get first card of a deck
	 * @return
	 */
	public Card getCard() {
		return cards.pop();
>>>>>>> 199818afe8100991ffae794dbcebaff5513bca99
	}
	/**
	 * put Card in a deck.
	 * @param aCard
	 */
	public void putCard(Card aCard) {
		cards.addLast(aCard);
	}
	
	/**
<<<<<<< HEAD
	 * return the size of the Deck.
	 * @return int
	 */
	public int getSizeDeck()
	{
		return this.cards.size();
	}
	
	/**
=======
>>>>>>> 199818afe8100991ffae794dbcebaff5513bca99
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
