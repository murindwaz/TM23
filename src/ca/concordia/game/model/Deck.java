package ca.concordia.game.model;

import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Random;

/**
 * Class Deck Creates the decks of cards.
 * A deck is a double-ended queue of cards: We can take from the front and put in the back 
 * 
 * @author Pascal Maniraho 
 * @author Gustavo Pereira
 * @author Bhavik Desai 
 * @author Jesus Esteban Garro Matamoros 
 * @author Diego Pizarro
 */

//A deck is a double-ended queue of cards: We can take from the front and put in the back 
public class Deck {
	
	private int numPlayers;
	private Deque<Card> cards;
	private ArrayList<Card> arrayCards;//Will serve for some of the decks.
	
	/**
	 * Constructor: Creates an Array of cards depending on the type of the card which is specified as an argument.
	 * @param type
	 */
	public Deck(String type,int numPlayers){
		switch(type) {
			case "D":
				//The discard deck is empty to start with
				this.cards = new ArrayDeque<Card>();
				break;
			case "P":
				//The personality deck has 7 cards:
				this.cards = new ArrayDeque<Card>();
				this.arrayCards = new ArrayList<Card>();
				PersonalityCard temp;
				for (int i = 0; i < 7; i++) {
					temp=new PersonalityCard(i,numPlayers);
					this.cards.add(temp);
					this.arrayCards.add(temp);
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
				for (int i = 49; i <= 101; i++) {
					this.cards.add(new GreenCard(i));
				}
				break;
			case "B":
				//The brown-bordered player deck has 48 cards:
				this.cards = new ArrayDeque<Card>();
				for (int i = 1; i <= 48; i++) {
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
	 * Shuffle Deck
	 */
	public void shuffle() {
		
		//Shuffles the cards in this deck
		int max = this.cards.size();

		Card[] tmp = new Card[max];
		//Fill in the array:
		for(int i = 0; i < max; i++) {
			tmp[i] = this.cards.pop();
		}
				
		//Fisher-Yates shuffle:
		Random rnd = new Random();
		for(int i = max - 1; i > 0; i-- ){
			int idx = rnd.nextInt(i+1);	
			//Perform swap:
			Card a = tmp[idx];
			tmp[idx] = tmp[i];
			tmp[i] = a;
		}
		
		//Put back the cards:
		for(int i = 0; i < max; i++) {
			this.cards.add(tmp[i]);
		}
	}
	
	/**
	 * Remove A Card Object.
	 * @param card(Card)
	 * @return Card
	 */
	/*
	public Card deleteCardFromDeck(Card card)
	{
		int index=this.arrayCards.indexOf(card);
		this.cards.remove(card);
		return this.arrayCards.remove(index);
	}*/
	
	/**
	 * Get first card of a deck; if the deck still has cards otherwise it will return null.
	 * @return Card
	 */
	public Card getCard() {
		if( this.cards.size() > 0 ){
			return this.cards.pop();
		}else{ 
			return null;
		}
	}
	
	/**
	 * Return ArrayList for the Deck.
	 * @return 
	 */
	public ArrayList<Card> getArrayDeck()
	{
		return this.arrayCards;
	}
	/**
	 * put Card in a deck.
	 * @param aCard
	 */
	public void putCard(Card aCard) {
		cards.addLast(aCard);
	}
	
	/**
	 * return the size of the Deck.
	 * @return int
	 */
	public int getSizeDeck()
	{
		return this.cards.size();
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
