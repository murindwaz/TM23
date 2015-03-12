package ca.concordia.game.gameState;

import ca.concordia.game.main.Game;
import ca.concordia.game.model.Player;
/**
 * When the player is in this state he will have the ability to only play iterrupt Cards if he posses one.Otherwise he has to wait for his/her turn to move to the play state..
 * Next state==>StatePlay();
 * @author Diego
 *
 */

public class StateWait implements StateLike{
	private String Status;

	/**
	 * Controls actions for current state.
	 */
	@Override
	public void performAction(StateContext context,Player player, Game game)
	{
		context.setState(new StatePlay());
		
	}
	
	/**
	 * Return the current status.
	 * @return String
	 */
	@Override
	public String getStatus()
	{
		this.Status="Wait State.";
		return this.Status;
	}
}
