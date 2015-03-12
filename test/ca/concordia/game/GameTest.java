package ca.concordia.game;




public class GameTest {
	
	
	/**
	 * Model Declarations 
	Die die; 
	Area area; 
	Card card; 
	Piece piece; 
	Player player; 
	Gameboard gameboard; 
	Game gameState;
	 * Utility class declaration
	GameStateWriter gameStateWriter = null; 
	GameStateReader gameStateReader = null; 
	
	Before 
	public void setUp(){
		die = new Die();
		area = new Area(null);
		card = new Card(false,false); 
		piece = new Piece(null);
		gameboard = new Gameboard(); 
		gameState = Game.getInstance();
		gameStateWriter = new GameStateWriter(gameState);
		gameStateReader = new GameStateReader(gameStateWriter.getFilePath());
	}
	
	After 
	public void tearDown(){
		//@todo remove the file 
		new File(gameStateWriter.getFilePath()).delete();
	}
	
	 * @todo prior game encoding, the game engine has to encode the game
	Test 
	public void canEncodeGameState(){
		fail("GameStateEncoding not yet implemented");
	}
	 * @todo prior to re-loading a game state, the system has to decode the JSON file 
	Test 
	public void canDecodeGameState(){
		fail( "GameStateDecoding not yet implemented");
	}
	 * Each player should select a set of playing pieces of the same color
	@Test 
	public void playerChoosesOnlyOneTypeOfColor(){
		fail( "Each player should select a set of playing pieces of the same color" );
	}
	
	
	Test 
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
	
	
	
	Test
	public void canSaveAndLoadGame(){
		Game test = new Game(); 
		String resultInit = test.init();
		assertEquals("Run was Successfull", resultInit);
		String savedGame = test.saveGame();
		String loadedGame = test.loadGame();
		assertEquals("Save Was Successfull",savedGame, loadedGame );
		test.printCurrentState();
	}

	
	Test 
	public void canReadGameState(){
		gameStateReader = new GameStateReader( gameStateWriter.getFilePath() );
		Game rgameState = gameStateReader.read();
		assertTrue( "GameStateTest - has the same game",  rgameState.equals(gameState));
	}
	
	Test
	public void testNewGame(){
		Game test = new Game(); 
		String result = test.init();
		assertEquals("Run was Successfull",result);
		test.printCurrentState();
	}
**/	
}