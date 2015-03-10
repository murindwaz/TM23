package ca.concordia.game;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.concordia.game.main.*;

public class GameTest4 {
	
	/**
	 * Initialize a new game load a game and save the game.
	 */
	@Test
	public void testSaveGame2()
	{
		Game test = new Game(); //Game Object(Main Functions init(),loadGame(),saveGame())
		//Initialialize all structures.
		String resultInit= test.init();
		assertEquals("Run was Successfull",resultInit);
		//Load Game
		String resultLoad= test.loadGame();
		assertEquals("Load Was Successfull",resultLoad);
		//save Game
		String resultSave= test.saveGame();
		assertEquals("Save Was Successfull",resultSave);
		
		test.printCurrentState();
	}

}
