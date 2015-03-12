package ca.concordia.game.gameState;

import ca.concordia.game.main.Game;
import ca.concordia.game.model.Player;
/**
 * When the player is in this state he will have the ability to only play iterrupt Cards.Otherwise he has to wait for his/her turn.
 * Next state==>StatePlay();
 * @author Diego
 *
 */

public class StateWait implements StateLike{

	@Override
	public void performAction(StateContext context,Player player, Game game)
	{
		context.setState(new StatePlay());
	}
}
