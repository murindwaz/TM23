package ca.concordia.game.util;

import java.io.BufferedReader;
import java.io.File;



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
		//read the content of filePath, and parse the content using GameStateParser
		//BufferedString buffer = new BufferedReader( new File(filePath) );
		return new Game();
	}

}