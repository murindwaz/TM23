package ca.concordia.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import ca.concordia.game.common.common.Colors;
import ca.concordia.game.model.Area;
import ca.concordia.game.model.Bank;
import ca.concordia.game.model.BrownCard;
import ca.concordia.game.model.Card;
import ca.concordia.game.model.CityCard;
import ca.concordia.game.model.Deck;
import ca.concordia.game.model.Demon;
import ca.concordia.game.model.Die;
import ca.concordia.game.model.EventCard;
import ca.concordia.game.model.GreenCard;
import ca.concordia.game.model.Minion;
import ca.concordia.game.model.PersonalityCard;
import ca.concordia.game.model.Piece;
import ca.concordia.game.model.Player;
import ca.concordia.game.util.CardLoader;


public class GameModelTest {
	
	
	
	
	
	@Test public void cardLoaderCanInitializeCards(){
		char BROWN = 'B'; 
		assertEquals("The Card loader can get a card at position 1 ::", "Sergeant Cheery Littlebottom",CardLoader.getInstance().nameForCard(1, BROWN).trim());
		assertEquals("The Card loader can get a card at position 2 ::", "Otto Chriek",CardLoader.getInstance().nameForCard(2, BROWN).trim());
		assertEquals("The Card loader can get a card at position 3 ::", "The Clacks",CardLoader.getInstance().nameForCard(3, BROWN).trim());
		assertEquals("The Card loader can get a card at position 4 ::", "Sergeant Colon",CardLoader.getInstance().nameForCard(4, BROWN).trim());
		assertEquals("The Card loader can get a card at position 5 ::", "Cosmo Lavish",CardLoader.getInstance().nameForCard(5, BROWN).trim());
		assertEquals("The Card loader can get a card at position 6 ::", "The Dean",CardLoader.getInstance().nameForCard(6, BROWN).trim());
		assertEquals("The Card loader can get a card at position 7 ::", "HELLO",CardLoader.getInstance().nameForCard(7, BROWN).trim());
		assertEquals("The Card loader can get a card at position 8 ::", "Burleigh & Stronginthearm",CardLoader.getInstance().nameForCard(8, BROWN).trim());
		assertEquals("The Card loader can get a card at position 9 ::", "The Bursar",CardLoader.getInstance().nameForCard(9, BROWN).trim());
		assertEquals("The Card loader can get a card at position 10 ::", "Cable Street Particulars",CardLoader.getInstance().nameForCard(10, BROWN).trim());
		assertEquals("The Card loader can get a card at position 11 ::", "Canting Crew",CardLoader.getInstance().nameForCard(11, BROWN).trim());
		assertEquals("The Card loader can get a card at position 12 ::", "Carcer",CardLoader.getInstance().nameForCard(12, BROWN).trim());
		assertEquals("The Card loader can get a card at position 13 ::", "The Chair of Indefinite Studies",CardLoader.getInstance().nameForCard(13, BROWN).trim());
		assertEquals("The Card loader can get a card at position 14 ::", "Sir Charles Lavatory",CardLoader.getInstance().nameForCard(14, BROWN).trim());
		assertEquals("The Card loader can get a card at position 15 ::", "Dorfl",CardLoader.getInstance().nameForCard(15, BROWN).trim());
		assertEquals("The Card loader can get a card at position 16 ::", "Sergeant Detritus",CardLoader.getInstance().nameForCard(16, BROWN).trim());
		assertEquals("The Card loader can get a card at position 17 ::", "Deep Dwarves",CardLoader.getInstance().nameForCard(17, BROWN).trim());
		assertEquals("The Card loader can get a card at position 18 ::", "Adora Belle Dearheart",CardLoader.getInstance().nameForCard(18, BROWN).trim());
		assertEquals("The Card loader can get a card at position 19 ::", "The Alchemists' Guild",CardLoader.getInstance().nameForCard(19, BROWN).trim());
		assertEquals("The Card loader can get a card at position 20 ::", "The Auditors",CardLoader.getInstance().nameForCard(20, BROWN).trim());
		assertEquals("The Card loader can get a card at position 21 ::", "Buggy Swires",CardLoader.getInstance().nameForCard(21, BROWN).trim());
		assertEquals("The Card loader can get a card at position 22 ::", "Susan",CardLoader.getInstance().nameForCard(22, BROWN).trim());
		assertEquals("The Card loader can get a card at position 23 ::", "Sybil Vimes",CardLoader.getInstance().nameForCard(23, BROWN).trim());
		assertEquals("The Card loader can get a card at position 24 ::", "Mr Teatime",CardLoader.getInstance().nameForCard(24, BROWN).trim());
		assertEquals("The Card loader can get a card at position 25 ::", "The Watch",CardLoader.getInstance().nameForCard(25, BROWN).trim());
		assertEquals("The Card loader can get a card at position 26 ::", "Wee Mad Arthur",CardLoader.getInstance().nameForCard(26, BROWN).trim());
		assertEquals("The Card loader can get a card at position 27 ::", "William de Worde",CardLoader.getInstance().nameForCard(27, BROWN).trim());
		assertEquals("The Card loader can get a card at position 28 ::", "Willikins",CardLoader.getInstance().nameForCard(28, BROWN).trim());
		assertEquals("The Card loader can get a card at position 29 ::", "Archchancellor Ridcully",CardLoader.getInstance().nameForCard(29, BROWN).trim());
		assertEquals("The Card loader can get a card at position 30 ::", "Ruby",CardLoader.getInstance().nameForCard(30, BROWN).trim());
		assertEquals("The Card loader can get a card at position 31 ::", "The Senior Wrangler",CardLoader.getInstance().nameForCard(31, BROWN).trim());
		assertEquals("The Card loader can get a card at position 32 ::", "Mr Shine",CardLoader.getInstance().nameForCard(32, BROWN).trim());
		assertEquals("The Card loader can get a card at position 33 ::", "Mr Slant",CardLoader.getInstance().nameForCard(33, BROWN).trim());
		assertEquals("The Card loader can get a card at position 34 ::", "The Smoking Gnu",CardLoader.getInstance().nameForCard(34, BROWN).trim());
		assertEquals("The Card loader can get a card at position 35 ::", "Stanley",CardLoader.getInstance().nameForCard(35, BROWN).trim());
		assertEquals("The Card loader can get a card at position 36 ::", "Moist von Lipwig",CardLoader.getInstance().nameForCard(36, BROWN).trim());
		assertEquals("The Card loader can get a card at position 37 ::", "Doctor Mossy Lawn",CardLoader.getInstance().nameForCard(37, BROWN).trim());
		assertEquals("The Card loader can get a card at position 38 ::", "Patrician's Palace",CardLoader.getInstance().nameForCard(38, BROWN).trim());
		assertEquals("The Card loader can get a card at position 39 ::", "Ponder Stibbons",CardLoader.getInstance().nameForCard(39, BROWN).trim());
		assertEquals("The Card loader can get a card at position 40 ::", "The Post Office",CardLoader.getInstance().nameForCard(40, BROWN).trim());
		assertEquals("The Card loader can get a card at position 41 ::", "Reacher Gilt",CardLoader.getInstance().nameForCard(41, BROWN).trim());
		assertEquals("The Card loader can get a card at position 42 ::", "Professor of Recent Runes",CardLoader.getInstance().nameForCard(42, BROWN).trim());
		assertEquals("The Card loader can get a card at position 43 ::", "Doctor Hix",CardLoader.getInstance().nameForCard(43, BROWN).trim());
		assertEquals("The Card loader can get a card at position 44 ::", "Hobsons's Livery Stable",CardLoader.getInstance().nameForCard(44, BROWN).trim());
		assertEquals("The Card loader can get a card at position 45 ::", "Hubert",CardLoader.getInstance().nameForCard(45, BROWN).trim());
		assertEquals("The Card loader can get a card at position 46 ::", "Igor",CardLoader.getInstance().nameForCard(46, BROWN).trim());
		assertEquals("The Card loader can get a card at position 47 ::", "The Luggage",CardLoader.getInstance().nameForCard(47, BROWN).trim());
		assertEquals("The Card loader can get a card at position 48 ::", "The Mob",CardLoader.getInstance().nameForCard(48, BROWN).trim());
		assertEquals("The Card loader can get a card at position 49 ::", "Lord Downey",CardLoader.getInstance().nameForCard(49, BROWN).trim());
		assertEquals("The Card loader can get a card at position 50 ::", "Dwarves",CardLoader.getInstance().nameForCard(50, BROWN).trim());
		assertEquals("The Card loader can get a card at position 51 ::", "Edward d'Eath",CardLoader.getInstance().nameForCard(51, BROWN).trim());
		assertEquals("The Card loader can get a card at position 52 ::", "Errol",CardLoader.getInstance().nameForCard(52, BROWN).trim());
		assertEquals("The Card loader can get a card at position 53 ::", "Gargoyles",CardLoader.getInstance().nameForCard(53, BROWN).trim());
	}
	
	
	
	
	
	
	/**
	 * Testing the deck initialization 
	 * Throws ArrayIndexOutOfBoundsException on shuffle() function. 
	 */
	@Test public void deck(){
		assertNotNull( "The discard deck is empty to start with", new Deck(new String("D"), 2) );
		assertNotNull( "The personality deck has 7 cards:", new Deck(new String("P"), 2) );
		assertNotNull( "The event deck has 12 cards:", new Deck(new String("E"), 2) );
		//assertNotNull( "The brown-bordered player deck has 48 cards:", new Deck(new String("B"), 2) );
		//this assertion fails because of BrownCard.name = CardLoader.getInstance().nameForCard(1,"B")
		//@todo test what's wrong with that line 
		//assertNotNull( "The green-bordered player deck has 53 cards:", new Deck(new String("G"), 2) );
	}
	
	
	@Test
	public void canInitializeModels(){
		Player[] players = null;
		Card dollySisters = new CityCard(0);
		assertNotNull("Can create area", new Area( (CityCard)dollySisters ) ); 
		assertNotNull("Can create a bank", new Bank());
		assertNotNull("Can create a card", new Card(false, false) ); 
		assertNotNull("Can Create a deck", new Deck(new String("D"), 4));
		//this assertion fails because of BrownCard.name = CardLoader.getInstance().nameForCard(1,"B")
		//@todo test what's wrong with that line 
		//assertNotNull("Can create city card", new CityCard( 1 ) );
		assertNotNull("Have deamons", new Demon( Colors.YELLOW ) );
		assertNotNull("Have a die", new Die( ) );
		assertNotNull("Can create event card", new EventCard( 0 ) );
		assertNotNull("Can create browncard", new BrownCard( 1 ) );
		assertNotNull("Can Create a green card", new GreenCard( 54 ) );
		assertNotNull("Can create a minion", new Minion( Colors.GREEN ) );
		assertNotNull("Can create personalities", new PersonalityCard( "A" ) );
		assertNotNull("Can create a piece", new Piece( Colors.RED ) );
		assertNotNull("Can create a player", new Player( new PersonalityCard("N"), Colors.BLUE, 4, 2 ) );
	}

}