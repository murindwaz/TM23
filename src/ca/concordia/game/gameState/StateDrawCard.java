package ca.concordia.game.gameState;

import ca.concordia.game.main.Game;
import ca.concordia.game.model.Player;
import ca.concordia.game.util.Configuration;

public class StateDrawCard implements StateLike{
	
	private String Status;
	
	
	/**
	 * Controls actions for current state.
	 */
	@Override
	public void performAction(StateContext context,Player player, Game game)
	{
		context.setState(new StateWait());
	}

	/**
	 * Return the current status.
	 * @return String
	 */
	@Override
	public String getStatus(){
		/**
		 * Not needed, the static variable makes it easy for Unit testing 
		 * this.Status="Drawing State.";
		 */
		return Configuration.STATE_DRAWING;
	}
}
