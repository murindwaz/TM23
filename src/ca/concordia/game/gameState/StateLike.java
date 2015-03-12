package ca.concordia.game.gameState;

import ca.concordia.game.main.Game;
import ca.concordia.game.model.Player;

public interface StateLike {
	
	void performAction(StateContext context,Player player, Game game);

}
