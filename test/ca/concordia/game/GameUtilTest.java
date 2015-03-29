package ca.concordia.game;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.concordia.game.gameState.*;
import ca.concordia.game.util.Configuration;

public class GameUtilTest {
	
	
	
	@Test 
	public void canInitializeStates(){
		
		StatePlay statePlay = new StatePlay();
		StateDrawCard stateDrawCard = new StateDrawCard();
		StateWait stateWait = new StateWait();
		assertTrue( "StatePlay is type of StateLike ", statePlay instanceof StateLike ); 
		assertTrue( "StateDrawCard is type of StateLike ", stateDrawCard instanceof StateLike ); 
		assertTrue( "StateWait is type of StateLike ", stateWait instanceof StateLike ); 
		assertEquals("StatePlay has Playing status", statePlay.getStatus(), Configuration.STATE_PLAY);
		assertEquals("StateDrawingCard has Drawing status", stateDrawCard.getStatus(), Configuration.STATE_DRAWING);
		assertEquals("StateWait has waiting status", stateWait.getStatus(), Configuration.STATE_WAIT);
	}


}