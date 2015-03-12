package ca.concordia.game;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import ca.concordia.game.model.*;


public class GameModelTest {
	
	/**
	 * Testing the deck initialization 
	 */
	@Test public void deck(){
		assertNotNull( "The discard deck is empty to start with", new Deck(new String("D"), 2) );
		assertNotNull( "The personality deck has 7 cards:", new Deck(new String("P"), 2) );
		/**@link http://stackoverflow.com/questions/4936320/cannot-find-problems-with-array-based-deque-but-getting-outofbounds-exception*/
		assertNotNull( "The city deck has 12 cards:", new Deck(new String("C"), 2) );
		assertNotNull( "The event deck has 12 cards:", new Deck(new String("E"), 2) );
		assertNotNull( "The green-bordered player deck has 53 cards:", new Deck(new String("G"), 2) );
		assertNotNull( "The brown-bordered player deck has 48 cards:", new Deck(new String("B"), 2) );
	}
	
	
	
	@Test
	public void canInitializeModels(){
	
		assertNotNull( new Area(null) ); 
		assertNotNull( new Bank());
		assertNotNull( new Card(false, false) ); 
		assertNotNull(new Deck(new String("D"), 4));
		
	}

}
