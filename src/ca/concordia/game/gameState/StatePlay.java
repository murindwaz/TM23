package ca.concordia.game.gameState;

import java.util.ArrayList;
import java.util.Scanner;

import ca.concordia.game.main.Game;
import ca.concordia.game.model.BrownCard;
import ca.concordia.game.model.Gameboard;
import ca.concordia.game.model.GreenCard;
import ca.concordia.game.model.Player;
import ca.concordia.game.model.Symbol;

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
		
		//Play one of the cards, ask user for input.
		Scanner keyIn=new Scanner(System.in);
		int cardNumberInPlayerHand;
		while(true)
		{
			System.out.println("Please enter the card number you wish to play(Select the number for 'Position in Player Hand'):");
			cardNumberInPlayerHand = keyIn.nextInt();
			if(cardNumberInPlayerHand > player.getPlayerCards().size()-1)
				System.out.println("Incorrect input please select a number between 0 and "+(player.getPlayerCards().size()-1));
			else
				break;
		}
		
		//Get card chosen by player.
		GreenCard chosenCard=(GreenCard)player.getPlayerCards().get(cardNumberInPlayerHand);
		//Get the actions for the chosen card.
		ArrayList<Symbol> actionSymbols= chosenCard.getActionsSymbols();
		//Execute each Symbol action sequentially.
		for(int i=0;i<actionSymbols.size();i++)
		{
			//If symbol is mandatory don't ask Player if he want's to use the symbol.
			System.out.println(actionSymbols.get(i).getDescription());//Print out symbol description.
			if(actionSymbols.get(i).isMandatory())
			{
				System.out.println("This action is mandatory.");
				actionSymbols.get(i).useSymbol(player, game);
				
			}else
			{
				String userAwnser;
				System.out.println("Would you like to use this action(yes/no)?");
				userAwnser = keyIn.next();
				if(userAwnser.contains("y"))
				{
					actionSymbols.get(i).useSymbol(player, game);
				}else
				{
					System.out.println("Action Skipped.");
				}
				
			}
		}
		
		keyIn.close();
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
