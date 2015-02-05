
package ca.concordia.test;

import static org.junit.Assert.*;
import org.junit.Test;

import ca.concordia.game.main.*;
/**
 * 
 * Class GameTest JUnit. Test functionality of initializing a new game.
 *
 */

public class GameTest {
	
	/**
	 * Create a new Game
	 */
	@Test
	public void testNewGame()
	{
		Game test = new Game(); //Game Object(Main Functions init(),loadGame(),saveGame())
		String result= test.init();
		assertEquals("Run was Successfull",result);
		
		test.printCurrentState();
	}
	
	
}
