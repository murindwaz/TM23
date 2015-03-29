package ca.concordia.game.gameState;

import ca.concordia.game.main.Game;

/**
 * This interface will be implemented by state classes. 
 * The function performAction implemented in all StateLike classes will implement actions.
 */
import ca.concordia.game.model.Player;

public interface StateLike {
	/**
	 * Controls actions for current state.
	 */
	void performAction(StateContext context,Player player, Game game);
	
	/**
	 * @deprecated getters should be kept for models only. 
	 * Return the current status.
	 * @return String
	 */
	public String getStatus();
}