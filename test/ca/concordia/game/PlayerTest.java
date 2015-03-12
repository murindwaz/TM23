package ca.concordia.game;

import static org.junit.Assert.*;

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
	Player dpizar;
	Player gamest;
	
	@BeforeClass 
	public void setup(){ 
		game = new Game();
		
		//PersonalityCard lordVetinari = new PersonalityCard(0, 2);
		//PersonalityCard lordSelachii = new PersonalityCard(1, 2);
			
		
		//dpizar = new Player( lordVetinari,  ); 
		//gamest = new Player( );
	}
	
	@Before 
	public void starts(){ 
		
	}
	
	
	
	@After 
	public void stops(){ 
		
	}
	
	@AfterClass 
	public void teardown(){
		
	}
	
	

	@Test
	public void testLoadGame(){
		Game test = new Game(); //Game Object(Main Functions init(),loadGame(),saveGame())
		//Initialialize all structures.
		String resultInit= test.init();
		assertEquals("Run was Successfull",resultInit);
		//Load Game
		String result= test.loadGame();
		assertEquals("Load Was Successfull",result);
		test.printCurrentState();
	}

}
