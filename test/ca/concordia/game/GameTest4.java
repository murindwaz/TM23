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
		Game test = new Game(); 
		String resultInit= test.init();
		assertEquals("Initialialize all structures - Run was Successfull",resultInit);
		String resultLoad= test.loadGame();
		assertEquals("Load Game - Loading the game passed",resultLoad);
		String resultSave = test.saveGame();
		assertEquals("Save Game - Saving the game passed",resultSave);
		test.printCurrentState();
	}

}
