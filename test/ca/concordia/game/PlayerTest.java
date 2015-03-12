package ca.concordia.game;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.concordia.game.common.common.Colors;
import ca.concordia.game.main.*;
import ca.concordia.game.model.*;


/**
 * The Player will evaluate the winning condition 
 * 1. Each player must be given $10, 6 building pieces and 12 minions.
 * 2. The Player cards must be shuffled (the green-bordered cards and the brown-bordered cards must be shuffled independently, and then combined into a single deck with the green-bordered on top).
 * 3. The Personality cards must be shuffled.
 * 4. The Random Event cards must be shuffled.
 * 5. One Personality card and five (green-bordered) Player cards must be dealt to each player.
 * 6. Each player must place one minion in area 1 (Dolly Sisters), area 5 (The Scours) and area 7 (The Shades).
 * 7. A trouble marker must be placed in each of the same three areas.
 */
public class PlayerTest {

	Game game;
	Gameboard gameboard;
	Player dpizar;
	Player gamest;
	
	/**
	 * Adding areas to play against
	 */
	Area dollySisters; 
	Area theScours; 
	Area theShades; 
	
	int MINIONS = 12; 
	int MONEY	= 10; 
	int BUILDINGS = 6; 
	
	
	
	@BeforeClass 
	public static void setup(){ 
		}
	
	@Before 
	public void starts(){ 
		game = Game.getInstance();
		/**
		 * @todo - load game players by assigning them via a function rather than a console on terminal
		 * game.init();
		 */
		gameboard = new Gameboard();
		/**
		 * Initiaalization of areas
		 */
		int demon = 0;
		Colors buildingColor = Colors.BLUE;
		boolean building = false; 
		boolean troubleMarker = true; 
		//creating the areas
		dollySisters = new Area(new CityCard(1), troubleMarker, building, buildingColor, demon, 0); 
		theScours = new Area(new CityCard(5), troubleMarker, building, buildingColor, demon, 0); 
		theShades = new Area(new CityCard(7), troubleMarker, building, buildingColor, demon, 0); 
		//creating cards
		PersonalityCard lordVetinari = new PersonalityCard(0, 2);
		PersonalityCard lordSelachii = new PersonalityCard(1, 2);
		dpizar = new Player( lordVetinari,  Colors.GREEN,  MINIONS, BUILDINGS); 
		gamest = new Player( lordSelachii, Colors.GREEN,  MINIONS, BUILDINGS);
	}
	
	
	
	@Test 
	public void playerInitialState(){
		assertEquals( "10 bucks in bank", dpizar.calculateNetWorth(),  gamest.calculateNetWorth() );
		gamest.transferMoney(MONEY);
		assertTrue( gamest.getMoney() == MONEY ); 
		dpizar.transferMoney(MONEY);
		assertTrue( dpizar.getMoney() == MONEY ); 
		assertTrue("Player dpizar has minion in Dolly Sisters(1)", dpizar.putNewMinionOnBoard(1) ); 
		assertTrue("Player dpizar has minion in The Scours(5)",  dpizar.putNewMinionOnBoard(5) ); 
		assertTrue("Player dpizar has minion in The Shades(7)",  dpizar.putNewMinionOnBoard(7) ); 
		assertTrue("Player gamest has minion in Dolly Sisters(1)",  gamest.putNewMinionOnBoard(1) ); 
		assertTrue("Player gamest has minion in The Scours(5)",  gamest.putNewMinionOnBoard(5) ); 
		assertTrue("Player gamest has minion in The Shades(7)",  gamest.putNewMinionOnBoard(7) ); 
		//three regions have to have a trouble marker
		assertTrue("Dolly Sisters has a trouble marker",  dollySisters.getTroubleMarker()); 
		assertTrue("The Scours has a trouble marker",  theScours.getTroubleMarker()); 
		assertTrue("The Shades has a trouble marker",  theShades.getTroubleMarker()); 
		//
		assertTrue( gamest.toString().length() > 0 ); 
		assertTrue( dpizar.toString().length() > 0 ); 
		assertTrue( dollySisters.toString().length() > 0 ); 
		assertTrue( theScours.toString().length() > 0 ); 
		assertTrue( theShades.toString().length() > 0 ); 
	}
	
	
	
	@Test 
	public void winningConditions(){
		//@todo make game moves till a winning condition is met 
		assertFalse("Player 2 has not winning conditions", gamest.checkWinningCondition(gameboard) );
		assertFalse("Player 1 has not winning conditions", dpizar.checkWinningCondition(gameboard) );
	}
	
	
	@After 
	public void stops(){ 
		
	}
	
	@AfterClass 
	public static void teardown(){
		
	}

}
