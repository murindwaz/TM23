package ca.concordia.game;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
	
	int MINIONS = 12; 
	int MONEY	= 10; 
	int BUILDINGS = 6; 
	
	
	
	@BeforeClass 
	public static void setup(){ 
		}
	
	@Before 
	public void starts(){ 
		game = Game.getInstance();
		gameboard = new Gameboard();
		PersonalityCard lordVetinari = new PersonalityCard(0, 2);
		PersonalityCard lordSelachii = new PersonalityCard(1, 2);
		dpizar = new Player( lordVetinari,  "Green",  MINIONS, BUILDINGS); 
		gamest = new Player( lordSelachii, "Green",  MINIONS, BUILDINGS);
	}
	
	
	
	@Test 
	public void playerInitialState(){
		assertEquals( "10 bucks in bank", dpizar.calculateNetWorth(),  gamest.calculateNetWorth() );
		gamest.transferMoney(MONEY);
		assertTrue( gamest.getMoney() == MONEY ); 
		dpizar.transferMoney(MONEY);
		assertTrue( dpizar.getMoney() == MONEY ); 
		assertTrue("Player has minion in Dolly Sisters(1)", dpizar.putNewMinionOnBoard(1) ); 
		assertTrue("Player has minion in The Scours(5)",  dpizar.putNewMinionOnBoard(5) ); 
		assertTrue("Player has minion in The Shades(7)",  dpizar.putNewMinionOnBoard(7) ); 
		assertTrue("Player2 has minion in Dolly Sisters(1)",  gamest.putNewMinionOnBoard(1) ); 
		assertTrue("Player has minion in The Scours(5)",  gamest.putNewMinionOnBoard(5) ); 
		assertTrue("Player has minion in The Shades(7)",  gamest.putNewMinionOnBoard(7) ); 
		assertTrue( gamest.toString().length() > 0 ); 
		assertTrue( dpizar.toString().length() > 0 ); 
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
