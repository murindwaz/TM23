package ca.concordia.game;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.concordia.game.main.*;

public class GameTest3 {
	
	/**
	 * initialize a new game and save it.
	 */
	@Test
	public void testSaveGame1()
	{
		Game test = new Game(); //Game Object(Main Functions init(),loadGame(),saveGame())
		//Initialialize all structures.
		String resultInit= test.init();
		assertEquals("Run was Successfull",resultInit);
		//Save Game
		String result= test.saveGame();
		assertEquals("Save Was Successfull",result);
		
		test.printCurrentState();
	}

}
