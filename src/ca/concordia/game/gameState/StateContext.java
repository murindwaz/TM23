package ca.concordia.game.gameState;

import ca.concordia.game.main.Game;
import ca.concordia.game.model.Player;

/**
 * Class StateContext controls the starting state of a player and helps serialize the states a player will have troughout the game.
 * All player start in the waiting status,once a player's turns is up it goes to the playing state and then passes to the drawing card
 * state and finally goes back to the waiting state which signifies the player's end of turn.
 * Flow==> Start at StateWait==>StatePlay==>StateDraw==>StateWait
 * @author Diego
 *
 */

public class StateContext {
	private StateLike playerState;
	
	/**
	 * Constructor Starts the the player's state to wait. 
	 */
    public StateContext() {
        setState(new StateWait());
    }
	/**
     * Setter method for the state.
     * Normally only called by classes implementing the State interface.
     * Further used to determined flow of states.
     * @param newState the new state of this context
     */
    public void setState(final StateLike newState) {
        playerState = newState;
    }
 
    public void performAction(Player player, Game game) {
    	playerState.performAction(this, player, game);
    }

}
