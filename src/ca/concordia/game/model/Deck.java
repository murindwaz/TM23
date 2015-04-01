package ca.concordia.game.model;

import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Random;

/**
 * Class Deck Creates the decks of cards. A deck is a double-ended queue of
 * cards: We can take from the front and put in the back
 * A deck is a double-ended queue of cards: We can take from the front and put in the back
 * @author Pascal Maniraho
 * @author Gustavo Pereira
 * @author Bhavik Desai
 * @author Jesus Esteban Garro Matamoros
 * @author Diego Pizarro
 */
 //A deck is a double-ended queue of cards: We can take from the front and put in the back 
public class Deck {

	private int players;
	private Deque<Card> cards;
	private ArrayList<Card> arrayCards;// Will serve for some of the decks.
	
	/**
	 * Private Constants 
	 */
	private static final String CARD_DECK = "D";
	private static final String PERSONALITY_DECK = "P";
	private static final String EVENT_DECK = "E";
	private static final String GREEN_CARDS_DECK = "G";
	private static final String BROWN_CARDS_DECK = "B";
	
	/**
	 * A Deck should have following features, depending on type : 
	 * <ul>	
	 * 		<li>D  - Initialiazation of empty cards </li>
	 * 		<li>P  - Initialization of Personality card. The personality deck has 7 cards:</li>
	 * 		<li>E  - The event deck has 12 cards: </li>
	 * 		<li>G  - The green-bordered player deck has 53 cards: </li>
	 * 		<li>B  - The brown-bordered player deck has 48 cards:</li>
	 * </ul> 
	 * Constructor: Creates an Array of cards depending on the type of the card
	 * which is specified as an argument.
	 * @param String type - deck type  
	 * @param int players - the number of players for a deck. 
	 */
	public Deck(String type, int players) {
		this.players = players;
		switch (type) {
		//The discard deck is empty to start with
		case CARD_DECK:
			this.cards = new ArrayDeque<Card>();
			break;
		case PERSONALITY_DECK:
		//The personality deck has 7 cards:
			this.cards = new ArrayDeque<Card>();
			this.arrayCards = new ArrayList<Card>();
			PersonalityCard temp;
			for (int i = 0; i < 7; i++) {
				temp = new PersonalityCard(i, players);
				this.cards.add(temp);
				this.arrayCards.add(temp);
			}
			break;
		case EVENT_DECK:
			//The event deck has 12 cards:
			this.cards = new ArrayDeque<Card>();
			for (int i = 0; i < 12; i++) {
				this.cards.add(new EventCard(i));
			}
			break;
		case GREEN_CARDS_DECK:
			//The green-bordered player deck has 48 cards:
			this.cards = new ArrayDeque<Card>();
			for (int i = 54; i <= 101; i++) {
				this.cards.add(new GreenCard(i));
			}
			break;
		case BROWN_CARDS_DECK:
			//The brown-bordered player deck has 53 cards:
			this.cards = new ArrayDeque<Card>();
			for (int i = 1; i <= 53; i++) {
				this.cards.add(new BrownCard(i));
			}
			break;
		default:
			System.out.println("Error initializing deck of unknown type");
			break;
		}
		// After creating a deck of cards, shuffle the cards:
		shuffle();
	}

	/**
	 * Shuffle Deck 
	 * The shuffle function uses Fisher-Yates shuffle algorithm 
	 */
	public void shuffle() {
		// Shuffles the cards in this deck
		int max = this.cards.size();
		Card[] tmp = new Card[max];
		// Fill in the array:
		for (int i = 0; i < max; i++) {
			tmp[i] = this.cards.pop();
		}
		Random rnd = new Random();
		for (int i = max - 1; i > 0; i--) {
			int idx = rnd.nextInt(i + 1);
			Card a = tmp[idx];
				tmp[idx] = tmp[i];
				tmp[i] = a;
		}
		// Put back the cards:
		for (int i = 0; i < max; i++) {
			this.cards.add(tmp[i]);
		}
	}


	/**
	 * Get first card of a deck; if the deck still has cards otherwise it will
	 * return null.
	 * @return Card
	 */
	public Card getCard() {
		if (this.cards.size() > 0) {
			return this.cards.pop();
		} else {
			return null;
		}
	}

	/**
	 * Return ArrayList for the Deck.
	 * @return
	 */
	public ArrayList<Card> getArrayDeck() {
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
	public int getSizeDeck() {
		return this.cards.size();
	}

	/**
	 * deal card to players.
	 * @param aPlayer
	 * @param num
	 */
	public void dealCardsToPlayer(Player aPlayer, int num) {
		for (int i = 0; i < num; i++) {
			aPlayer.receiveCard(cards.pop());
		}
	}
	
	/**
	 * Displays a number of cards in the deck, the number is specified sent as an argument.
	 * @param howMany(int)
	 */
	public void displayCardsinDeck(int howMany)
	{
		int counter=1;
		for(Iterator itr = this.cards.iterator();itr.hasNext();)  {
			
			System.out.println(itr.next().toString());
			if(counter==howMany)
				break;
	        counter++;
	      }
	}
}
