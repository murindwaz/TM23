package ca.concordia.game.gameState;

import ca.concordia.game.common.common;
import ca.concordia.game.main.Game;
import ca.concordia.game.model.Deck;
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
		//Draw cards so the Player has 5 cards on his playing hand. If he already has five or more then he can`t draw more cards.
		
		int cardsInplayerHands=player.getPlayerCards().size();
		
		if(cardsInplayerHands<common.numberOfCard)
		{
			for(int i=0;i<(common.numberOfCard-cardsInplayerHands);i++)
			{
				//Choose a deck to draw cards from. If the green deck is empty draw cards from the brown deck otherwise draw cards from the green deck.
				Deck drawDeck=game.getEspecificDeck("green");
				if(drawDeck.getSizeDeck()>0)
				{
					//give player one card from greenDeck.
					drawDeck.dealCardsToPlayer(player, 1);
					//Display last card added to player.
					System.out.println("Player: "+player.getColor()+" Draw the card:"+player.getPlayerCards().get(player.getPlayerCards().size()-1).toString());
				}else //Draw from brown deck
				{
					drawDeck=game.getEspecificDeck("brown");
					if(drawDeck.getSizeDeck()>0)
					{
						//give player one card from brownDeck.
						drawDeck.dealCardsToPlayer(player, 1);
						//Display last card added to player.
						System.out.println("Player: "+player.getColor()+" Draw the card:"+player.getPlayerCards().get(player.getPlayerCards().size()-1).toString());
					}

					//If the brown deck is empty then the game is over!!!!!
					if(drawDeck.getSizeDeck()<=0)
					{
						System.out.println("*****************************************The Game is Over*********************************************************");
						//TODO: Call Function to calculate who won the game and end Game.
					}

				}
			}
		}else
		{
			System.out.println("Player has 5 or more cards already. He can't draw more cards.");
		}
		
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
