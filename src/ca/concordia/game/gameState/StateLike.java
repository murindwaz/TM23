package ca.concordia.game.gameState;

import ca.concordia.game.main.Game;

/**
 * Interface for all state classes. Gives function performAction to be overwritten, this functions will contain the valid actions for a state.
 */
import ca.concordia.game.model.Player;

public interface StateLike {
	

	/**
	 * Controls actions for current state.
	 */
	void performAction(StateContext context,Player player, Game game);
	
	/**
	 * Return the current status.
	 * @return String
	 */
	public String getStatus();

	

}
