package ca.concordia.game.gameState;

import ca.concordia.game.main.Game;
import ca.concordia.game.model.Gameboard;
import ca.concordia.game.model.Player;

public class StatePlay  implements StateLike{
	
	private String Status;
	
	
	/**
	 * Controls actions for current state.
	 */
	@Override
	public void performAction(StateContext context,Player player, Game game)
	{
		System.out.println();
		
		//Display Gameboard Status.
		Gameboard gameBoard=game.getInstance().getGameBoard();
		System.out.println(gameBoard.toString());
		
		System.out.println();
		
		//Display the players hand.
		System.out.println(player.toString());
		
		//Play one of the cards
		System.out.println("Please enter the card number you wish to play(Select the number for 'Position in Player Hand'):");
		
		context.setState(new StateDrawCard());
	}
	
	
	/**
	 * Return the current status.
	 * @return String
	 */
	@Override
	public String getStatus()
	{
		this.Status="Play State.";
		return this.Status;
	}

}
