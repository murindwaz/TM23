package ca.concordia.game;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.concordia.game.gameState.*;

public class GameUtilTest {
	
	
	
	@Test 
	public void canInitializeStates(){
		
		StatePlay statePlay = new StatePlay();
		StateDrawCard stateDrawCard = new StateDrawCard();
		StateWait stateWait = new StateWait();
		//StateContext stateContext = new StateContext();
		assertTrue( "StatePlay is type of StateLike ", statePlay instanceof StateLike ); 
		assertTrue( "StateDrawCard is type of StateLike ", stateDrawCard instanceof StateLike ); 
		assertTrue( "StateWait is type of StateLike ", stateWait instanceof StateLike ); 
		//assertTrue( "StateContext is type of StateLike ", stateContext instanceof StateLike ); 
	}


}