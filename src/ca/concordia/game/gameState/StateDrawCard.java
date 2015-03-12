package ca.concordia.game.gameState;

import ca.concordia.game.main.Game;
import ca.concordia.game.model.Player;

public class StateDrawCard implements StateLike{
	
	@Override
	public void performAction(StateContext context,Player player, Game game)
	{
		context.setState(new StateWait());
	}

}
