package ca.concordia.game.gameState;

import java.util.ArrayList;

import ca.concordia.game.main.Game;
import ca.concordia.game.model.CityCard;
import ca.concordia.game.model.Deck;
import ca.concordia.game.model.Gameboard;
import ca.concordia.game.model.GreenCard;
import ca.concordia.game.model.Player;
import ca.concordia.game.model.Symbol;
import ca.concordia.game.util.Configuration;

public class StatePlay implements StateLike {

	/**
	 * Controls actions for current state.
	 */
	@Override
	public void performAction(StateContext context, Player player, Game game) {

		Gameboard gameBoard = game.getGameBoard();
		int cardNumberInPlayerHand;
		boolean playAnotherCard	=	true;

		
		Deck discardDeck=null;
		// Display Gameboard Status.
		System.out.println(gameBoard.toString());
		System.out.println(player.toString());
		
		
		//Get all cityCards and ask the player if he wishes to use them. A city card has to be active in order to be played.
		ArrayList<CityCard> cityCards=player.getPlayerCityCard();
		if(cityCards.size()>0)
		{
			for(int i=0;i<cityCards.size();i++)
			{
				//check that the card is active.
				if(cityCards.get(i).getIsActive())
				{
					System.out.println("Player: "+player.getColor()+" do you wish to use city card: "+cityCards.get(i).getName()+" with ability:"+cityCards.get(i).getAbility()+"Card Number: "+cityCards.get(i).getCardNumber());
					String input=game.keyIn.next();
					if(input.contains("y"))
					{
						System.out.println("Executing Card...");
						cityCards.get(i).useCityCard(player, game);//Use card if the player choose to use it.
					}
				}
			}
		}
		
		// Play one of the cards, ask user for input.
		
		while(playAnotherCard){
			
			playAnotherCard=false;
			
			while (true) {
				System.out.println("Please enter the card number you wish to play(Select the number for 'Position in Player Hand'):");
				cardNumberInPlayerHand = game.keyIn.nextInt();
				if (cardNumberInPlayerHand > player.getPlayerCards().size() - 1) {
					System.out.println("Incorrect input please select a number between 0 and "+ (player.getPlayerCards().size() - 1));
				} else {
					break;
				}
			}

			// Get card chosen by player.
			// Get the actions for the chosen card.
			GreenCard chosenCard = (GreenCard) player.getPlayerCards().get(cardNumberInPlayerHand);
			ArrayList<Symbol> actionSymbols = chosenCard.getActionsSymbols();
			// Execute each Symbol action sequentially.
			String userAwnser;
			for (int i = 0; i < actionSymbols.size(); i++) {
				Symbol symbol = actionSymbols.get(i);
				//Print out symbol description. 
				System.out.println(symbol.getDescription());
				 //If symbol is mandatory don't ask Player if he want's to use the symbol. 
				if( symbol.getIsMandatory() ){
					System.out.println("This action is mandatory.");
					playAnotherCard	= actionSymbols.get(i).useSymbol(player, game, chosenCard.getNumber());
				} else {
					System.out.println("Would you like to use this action(yes/no)?");
					userAwnser = game.keyIn.next();
					if (userAwnser.contains("y")) {
						playAnotherCard=actionSymbols.get(i).useSymbol(player, game, chosenCard.getNumber());	
						//Display board status.
						System.out.println(gameBoard.toString());
					} else {
						System.out.println("Action Skipped.");
					}
				}
			}//for
			//After using card. Discard it to the discard deck.
			//PutCard in discardDeck.
			discardDeck=game.getEspecificDeck("discard");
			discardDeck.putCard(chosenCard);
			boolean check=player.removePlayerCard(chosenCard);
			if(check)
			{
				System.out.println("Card Was put to discard deck and removed from player's hand.");
			}
			
		}//while
		
		//discardDeck.displayCardsinDeck(1);//Print Discard Deck(For debugging purposes).
		/** 
		 * Display board status.
		 * Display the players hand.
		 */
		System.out.println(gameBoard.toString());
		System.out.println(player.toString());
		context.setState(new StateDrawCard());
	}

	/**
	 * Return the current status.
	 * @return String
	 */
	@Override
	public String getStatus() {
		/**
		 * This status is not needed here, the static variable made it easy for unit testing 
		 * this.Status = "Play State.";
		 */
		return Configuration.STATE_PLAY;
	}

}
