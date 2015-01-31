package ca.concordia.game;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.concordia.GameState;
import ca.concordia.game.model.*;
import ca.concordia.game.util.*;

public class GameTest {
	
	
	
	Die die; 
	Area area; 
	Card card; 
	Piece piece; 
	Player player; 
	Gameboard gameboard; 
	GameState gameState;
	
	
	GameStateWriter gameStateWriter; 
	GameStateReader gameStateReader; 
	
	@Before 
	public void setUp(){
		die = new Die();
		area = new Area();
		card = new Card(); 
		piece = new Piece();
		player = new Player(); 
		gameboard = new Gameboard(); 
		gameState = new GameState();
		//@todo initialization of players in a game 
		//@todo initialization of cards per player 
	}
	
	@After 
	public void tearDown(){
		//@todo remove the file 
		
	}
	

	@Test 
	public void canSaveGameState(){
		//@todo add some moves to the game + save the state --- 
		gameStateWriter = new GameStateWriter(gameState); 
		assertTrue("GameStateTest - has a filepath ", new File(gameStateWriter.getFilePath()).exists() );
		assertTrue( "GameStateTest - has a filepath is not empty",  new File(gameStateWriter.getFilePath()).length() > 0 );
	}
	
	@Test 
	public void canReadGameState(){
		gameStateReader = new GameStateReader( gameStateWriter.getFilePath() );
		GameState rgameState = gameStateReader.read();
		assertTrue( "GameStateTest - has the same game",  rgameState.equals(gameState));
	}
	
	
}