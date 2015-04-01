package ca.concordia.game.util;

import ca.concordia.game.main.Game;



/**
 *  This reader will read data  from a previous game state, and re-initialize the game 
 * @author root
 */
public class GameStateReader {

	
	
	private String filePath;
	public GameStateReader(String filePath) {
		this.filePath = filePath;
	}

	
	
	/**
	 * @todo - do the read, and re-initialize the game 
	 * @return
	 */
	public Game read(){
		return Game.getInstance();
	}

}