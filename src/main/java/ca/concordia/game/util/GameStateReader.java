package ca.concordia.game.util;

import ca.concordia.GameState;




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
	public GameState read(){
		//read the content of filePath, and parse the content using GameStateParser
		return new GameState();
	}

}