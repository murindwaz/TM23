package ca.concordia.game;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.concordia.Game;
import ca.concordia.game.model.*;
import ca.concordia.game.util.*;

import java.awt.Color;

public class GameTest {
	
	
	/**
	 * Model Declarations 
	 */
	Die die; 
	Area area; 
	Card card; 
	Piece piece; 
	Player player; 
	Gameboard gameboard; 
	Game gameState;
	
	/**
	 * Utility class declaration
	 */
	GameStateWriter gameStateWriter; 
	GameStateReader gameStateReader; 
	
	@Before 
	public void setUp(){
		die = new Die();
		area = new Area();
		card = new Card(false,false); 
		piece = new Piece();
		player = new Player(new PersonalityCard(1),Color.RED); 
		gameboard = new Gameboard(); 
		gameState = Game.getInstance();
		//@todo initialization of players in a game 
		//@todo initialization of cards per player 
	}
	
	@After 
	public void tearDown(){
		//@todo remove the file 
		new File(gameStateWriter.getFilePath()).delete();
	}
	
	/**
	 * @todo prior game encoding, the game engine has to encode the game
	 */
	@Test public void canEncodeGameState(){
		fail("GameStateEncoding not yet implemented");
	}
	/**
	 * @todo prior to re-loading a game state, the system has to decode the JSON file 
	 */
	@Test public void canDecodeGameState(){
		fail( "GameStateDecoding not yet implemented");
	}
	
	
	
	
	@Test 
	public void canSaveGameState(){
		//@todo add some moves to the game + save the state --- 
		gameStateWriter = new GameStateWriter(gameState); 
		File gameFile = new File(gameStateWriter.getFilePath());
		assertFalse("GameStateTest - file doesn't exist ", gameFile.exists() );
		assertFalse( "GameStateTest - file is empty empty",  gameFile.length() > 0 );
		gameStateWriter.write();
		assertTrue("GameStateTest - file exists", gameFile.exists() );
		assertTrue( "GameStateTest - file is not empty",  gameFile.length() > 0 );
	}
	
	@Test 
	public void canReadGameState(){
		gameStateReader = new GameStateReader( gameStateWriter.getFilePath() );
		Game rgameState = gameStateReader.read();
		assertTrue( "GameStateTest - has the same game",  rgameState.equals(gameState));
	}
	
	
}