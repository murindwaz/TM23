package ca.concordia.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.concordia.game.main.*;

public class GameTest2 {
	
	/**
	 * Intialize a new game(Initialize all structures) and load a new game specified by the user.
	 */
	@Test
	public void testLoadGame()
	{
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
