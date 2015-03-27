package ca.concordia.game.model;

import java.util.ArrayList;
import java.util.Scanner;

import ca.concordia.game.common.common.Colors;
import ca.concordia.game.main.Game;

public class Action {
	private Game game;
	private Gameboard gameBoard;
	private Player player;
	private Scanner keyIn;
	private Scanner input;
	private Player[] players;
	private Die die;

	Bank bank=Bank.getInstance();

	private int rollValue;

	/**
	 * Constructor: initilializes an action according to Card's ID.
	 * @param cardId
	 */
	public Action(int cardId){	
		this.game = Game.getInstance();

		if (game.currentPlayer != -1)
		{
			this.gameBoard = game.getGameBoard( );
			this.players = game.getPlayers();
			this.player = players[(game.currentPlayer)];
			this.die = new Die();
			rollValue = -1;

			switch(cardId){

			//Group1: Get Cards from DrawDeck
			//1,3,36,42 - Get 2
			//77 - Get 3
			//87,88 - Get 4
			case 1: case 3: case 36: case 42 :
				getCards( 2 );	
				break;
			case 77:
				getCards( 3 );	
				break;
			case 87: case 88:	
				getCards( 4 );	
				break;

				//Group2: Earn Money
				//2,27,90  1 for each TroubleMarker on Board
				//14,40    2 for each Building on Board
				//61,83    3 for Minions on Isle of God
				//54,101   4 from all Players
				//84       5 from one Player
			case 2: case 27: case 90: 
				earnMoney( 1 , 1 );
				break;
			case 14: case 40: 
				earnMoney( 1, 2 );	
				break;
			case 61: case 83: 
				earnMoney( 1, 3 );	
				break;	
			case 54: case 101: 
				earnMoney( 2, 4 );	
				break;	
			case 84: 
				earnMoney( 3, 5 );	
				break;		
				//Group3: Pay another player then move minion
				//5,8,44
			case 5: case 8: case 44:
				paynRemoveMinion( 2 , cardId);	
				break;

				//Group4: Move a minion belonging to another player from one area to an adjacent area
				//11,62,72
				//15,18,92 Your own minion, not required to be adjacent
				//92 Your own minion and adjacent
			case 11: case 62: case 72:
				moveMinion(false, true, false );	
				break;
			case 15: case 18:
				moveMinion( true, false, false);	
				break;
			case 92:
				moveMinion( true, true, true );	
				break;

				//6 : Remove one minion from Unreal Estate.
			case 6: 
				removeUnrealEstateMinion( );	
				break;

				//9 : Exchange minions position
			case 9: 
				exchangeMinions( );	
				break;			

				//10 Look other player's cards and discard one
			case 10:
				discardOthersCard( );

				//12: Roll the die twice and remove one minion of your choice from those areas.
			case 12:
				rollnRemoveMinion( );	
				break;
				//13: Exchange your hand with that of another player.
			case 13:
				exchangeCards( );	
				break;


			default:
				System.out.println("Action Id doesn't exist.");
				break;
			}
		}		
	}

	/**
	 * Choose a Player
	 * @return choosenPlayer
	 */
	private int choosePlayer( ){ 
		int choosenPlayer = 0;
		for(int i=0; i<game.numberOfPlayers; i++)
		{
			if (game.currentPlayer != i)
			{
				System.out.println(i + "." + players[i].getPersonality().getName() + " " + players[i].getColor() + ",");
			}
		}
		choosenPlayer = keyIn.nextInt();

		return choosenPlayer;
	}

	/**
	 * Choose an Area
	 * @param hasMinions
	 * @param hasTroubleMarker
	 * @param adjacentArea
	 * @return choosenArea
	 */
	private int chooseArea( boolean hasMinions, boolean hasTroubleMarker, Area aArea ){ 

		for(int i=0; i<12; i++)
		{
			if (hasMinions)
				if (gameBoard.getAreas().get(i).getMinions().isEmpty())
					continue;
			if (aArea != null)
				if (aArea.getCityCard().getAdjacentAreas().get(i) == null)
					continue;
			if (hasTroubleMarker)
				if ( gameBoard.troubleMarkersAreas().get(i) == null )
					continue;
			System.out.println(i + "." + gameBoard.getAreas().get(i).toString());				
		}		
		return keyIn.nextInt();

	}
	/**
	 * Add card to player playing cards.
	 * @param nbCards
	 */
	private void getCards(int nbCards){
		for( int i = 0; i < nbCards; i++ ){
			if (game.getDecks().get("brown").getSizeDeck() > 0)
				player.receiveCard(game.getDecks().get("brown").getCard());
			else
				player.receiveCard(game.getDecks().get("green").getCard());
		}   		
	}


	/**
	 * Earn money for each piece on the board.
	 * @param amount
	 * @param mode :  1 for each TroubleMarker
	 *                2 for each Building
	 *                3 for Minions on Isle of God )
	 *                4 from all Players
	 *                5 from one Player
	 */
	private void earnMoney(int amount, int mode) {
		for(int i=1; i<= 12 ;i++){
			switch(mode){
			case 1:
				if(gameBoard.getAreas().get(i).getTroubleMarker())
					bank.transferFunds(player, amount);
				break;
			case 2:
				if(gameBoard.getAreas().get(i).getBuilding())
					bank.transferFunds(player, amount);
				break;
			case 3:
				amount = amount * gameBoard.getAreas().get(10).getMinions().size();
				bank.transferFunds(player, amount);
				break;
			case 4:
				for( int p=1; p<= game.getPlayers().length; p++)
					players[p].transferMoneyto(amount,player);
				break;
			case 5:
				System.out.println("Choose a player to take $"+amount+" dollars from:");
				players[choosePlayer()].transferMoneyto(amount,player);
				break;
			}			

		}
	}

	/**
	 * Pay another player then remove one minion
	 * @param amount
	 * @param cardId
	 */
	private void paynRemoveMinion(int amount, int cardId) {
		int choosenPlayer = 0;
		int choosenArea = 0;
		System.out.println("Choose a player to receive $"+amount+" dollars:");
		choosenPlayer = choosePlayer();
		player.transferMoneyto(amount,players[choosenPlayer]);
		switch(cardId) 
		{
		case 44:
			moveMinion( true , false, false);	
			return;
		case 5:
			System.out.println("Player "+choosenPlayer+"must choose an area with trouble marker to remove one minion (not your choice)");
			break;		
		case 8:
			System.out.println("Choose an area to remove one minion:");
			break;	
		}
		choosenArea = chooseArea(true, true, null);
		System.out.println("Select the color of minion you wish to remove:" );
		String tmpColor = input.next();
		Colors color = Colors.colorForString(tmpColor);
		//Remove a minion of the color specified by the player.
		gameBoard.getAreas().get(choosenArea).removeMinion(color);
		//Update the status of the player to whom the minion belonged to.
		Player playerbycolor=game.getPlayerByColor(color);
		playerbycolor.removeMinionOnBoard(choosenArea);
	}

	/**
	 * Remove one minion from Unreal Estate.
	 */
	private void removeUnrealEstateMinion( )
	{
		gameBoard.getAreas().get(2).getMinions().remove(1);
	}


	/**
	 * Exchange the positions of any two minions on the board.
	 */
	private void exchangeMinions( )
	{
		int fromArea, toArea;		
		while (true) 
		{

			System.out.println("Select the color of minion you wish to move:" );
			String tmpColor = input.next();
			Colors color = Colors.colorForString(tmpColor);

			Player playerbycolorfrom = game.getPlayerByColor(color);

			System.out.println("Choose an area to move one minion from:");
			fromArea = this.chooseArea(true, false, null);

			while(true) {
				System.out.println("Choose an area to move one minion to:");
				toArea = this.chooseArea(true, false, null);

				if (gameBoard.getAreas().get(toArea).getMinions().isEmpty()) {
					System.out.println("This Area has no Minions");
					continue;
				}

				while(true) {
					System.out.println("Select the color of minion you wish to move out:" );
					tmpColor = input.next();
					color = Colors.colorForString(tmpColor);

					Player playerbycolorto = game.getPlayerByColor(color);	

					if (playerbycolorto.moveMinionToNewArea( toArea, fromArea )){ 
						if (playerbycolorfrom.moveMinionToNewArea( fromArea, toArea ))
							break;
					}
					else {
						System.out.println("This Area has no Minions.");
						continue;
					}
				}

			}
		}
	}

	/**
	 * Look other player's cards and discard one
	 */
	private void discardOthersCard( )
	{

		ArrayList<Card> playerCards;
		int cardNb;
		System.out.println("Select a Player to loose a card:" );    
		playerCards = players[choosePlayer()].getPlayerCards();
		System.out.println("Select a card to discard:" );  
		for( int i=0; i<playerCards.size(); i++ ) 
		{
			System.out.println(i+playerCards.get(i).toString());
		}
		cardNb = keyIn.nextInt();
		playerCards.remove(cardNb);
	}

	/**
	 * Move a minion from one area to another area.
	 * @param isYour
	 * @param isAdjacent
	 * @param hasTroubleMarker
	 */
	private void moveMinion( boolean isYour, boolean isAdjacent, boolean hasTroubleMarker)
	{
		int fromArea, toArea;	
		String tmpColor;
		Colors color = null ;
		Player playerbycolor = null;
		while (true) 
		{

			if (!isYour)
			{
				System.out.println("Select the color of minion you wish to move:" );
				tmpColor = input.next();
				color = Colors.colorForString(tmpColor);
				playerbycolor = game.getPlayerByColor(color);

				if (color.equals(player.color)) {
					System.out.println("Minion should belong to another player.");
					continue;
				}
			}
			else
				if (color.equals(player.color) != isYour) {
					System.out.println("Minion should belong to you.");
					continue;				}

			while(true) {

				System.out.println("Choose an area to move this minion from:");
				fromArea = this.chooseArea(true, hasTroubleMarker, null);

				if (gameBoard.getAreas().get(fromArea).getMinions().isEmpty()) {
					System.out.println("This Area has no Minions");
					continue;
				}
				else
					break;
			}

			System.out.println("Choose an area to move this minion to:");
			toArea = this.chooseArea(true, false, gameBoard.getAreas().get(fromArea) );

			if (!isYour){
				if (playerbycolor.moveMinionToNewArea( fromArea, toArea ))
					break;
				else {
					System.out.println("Not possible to move to this Area");
					continue;
				}
			}
			else{
				if (player.moveMinionToNewArea( fromArea, toArea ))
					break;
				else {
					System.out.println("Not possible to move to this Area");
					continue;
				}
			}
		}

	}


	/**
	 * Roll the die twice and remove one minion of your choice from those areas, even if there is no trouble there.
	 */
	private void rollnRemoveMinion( ){ 
		for(int i=0; i==2; i++)
		{

			System.out.println("Rolling the die..." );			
			rollValue = die.roll();

			System.out.println("Select the minion from Area "+rollValue+":");

			if (gameBoard.getAreas().get(rollValue).getMinions().isEmpty()) {
				System.out.println("Area has no Minions");
				continue;
			}

			for (int count=0; count==gameBoard.getAreas().get(rollValue).getMinions().size();count++)
			{
				System.out.println(count+". "+gameBoard.getAreas().get(rollValue).getMinions().get(count).toString());
			}

			int pieceChoosen = keyIn.nextInt();

			gameBoard.getAreas().get(rollValue).getMinions().remove(pieceChoosen);

		}
	}


	/**
	 * Exchange your hand with that of another player.
	 */
	private void exchangeCards( )
	{ 
		int choosenPlayer = 0;
		ArrayList<Card> playerCards;

		System.out.println("Select a player to exchange all your Cards:");
		playerCards = players[choosePlayer()].getPlayerCards();

		players[choosenPlayer].loseAllCards();
		players[choosenPlayer].receiveAllCards( players[game.currentPlayer].getPlayerCards());

		players[game.currentPlayer].loseAllCards();
		players[game.currentPlayer].receiveAllCards(playerCards);
	}	

}

