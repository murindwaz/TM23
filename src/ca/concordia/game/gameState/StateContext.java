package ca.concordia.game.gameState;

import ca.concordia.game.main.Game;
import ca.concordia.game.model.Player;

/**
 * Class StateContext controls the starting state of a player and helps serialize the states a player will have troughout the game.
 * <pre>
 * 	All players start in the "waiting status" 
 *  Once a player's turns is up, the Status changes from "waiting" to "playing". 
 *  The state then continues to the "drawing card" state.
 *	Finally, the state goes back to the "waiting", which means it is the player's end of turn.
 * </pre>
 * The flow is as following: 
 * <pre>	
 * 	1. Start at StateWait
 * 	2. Move to StatePlay
 * 	3. The to StateDraw
 * 	4. And back to StateWait
 * </pre>
 * @author Diego
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
    
    /**
     * 
     * @return String
     */
    public String getState()
    {
    	return playerState.getStatus();
    }
 
    public void performAction(Player player, Game game) {
    	playerState.performAction(this, player, game);
    }

}
