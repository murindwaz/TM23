package ca.concordia.game.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import ca.concordia.game.common.common.Colors;
import ca.concordia.game.main.Game;

public class Action {
	
	private Game game;
	private Gameboard gameBoard;
	private Player player;
	private Map<String,Deck> decks;
	private Scanner keyIn;
	private Scanner input;
	private Player[] players;
	Bank bank=Bank.getInstance();
	
	/**
	 * Constructor: initilializes an action according to Card's ID.
	 * @param cardId
	 */
	public Action(int cardId){	
		this.game = Game.getInstance();
		this.gameBoard = game.getGameBoard( );
		this.player = game.getPlayers()[game.currentPlayer];
		this.players = game.getPlayers();
		switch(cardId){
		//Group1: Get Cards from DrawDeck
		//1,3,36,42,77,87,88
		case 1: case 3: case 36: case 42 :
			getCards( 2 );	
			break;
		case 77:
			getCards( 3 );	
			break;
		case 87: case 88:	
			getCards( 4 );	
			break;
		//Group2: Get Money for pieces on Board
	    //2,14,27,40,54,61,83,84,90,101					
		case 2: case 27: case 90: 
			getforTroubleMarkers( 1 );	
			break;
		case 61: case 83: 
			getforMinioninTIG( 1 );	
			break;
		case 14: 
			getforBuilding( 1 );	
			break;	
	     //Group3: Pay another player then move minion
	     //5,8,44
			case 5: case 8: case 44:
			paynRemoveMinion( 2 , cardId);	
		    break;
    		//6 : Remove one minion from Unreal Estate.
	    	case 6: 
			removeUnrealEstateMinion( );	
			break;
	    default:
			System.out.println("Action Id doesn't exist.");
			break;
		}
	}

	/**
	 * Add card to player playing cards.
	 * Perform Actions
	 * @param nbCards
	 */
	private void getCards(int nbCards){
		for( int i = 0; i < nbCards; i++ ){
			decks.get("green").dealCardsToPlayer(player, nbCards);  //??
		}   		
	}

	/**
	 * Earn money for each trouble marker on the board.
	 * @param amount of money
	 */
	private void getforTroubleMarkers(int amount) {
			for(int i=1; i< 12 ;i++){
				if(gameBoard.getAreas().get(i).getTroubleMarker()) {
					bank.transferFunds(player, amount);
				}
			}
	}
	
	/**
	 * Earn money for each building on the board (any colour). 
	 * @param amount of money
	 */
	private void getforBuilding(int amount) {
		for(int i=1; i< 12 ;i++)
		{
			if(gameBoard.getAreas().get(i).getBuilding()) {
				bank.transferFunds(player, amount);
			}
		}
	}
	
	/**
	 * Earn money for each minion in The Isle of Gods.
	 * @param amount of money
	 */
	private void getforMinioninTIG(int amount) {
	for(int i=0; i<gameBoard.getAreas().get(10).getMinions().size();i++)
		{
		bank.transferFunds(player, amount);
		}
	}

	/**
	 * Pay another player then remove one minion
	 */
	private void paynRemoveMinion(int amount, int cardId) {
		int choosenPlayer, choosenArea;
		choosenPlayer = 0;
		System.out.println("Choose a player to receive $"+amount+" dollars:");
		for(int i=0; i<game.numberOfPlayers; i++)
		{
			if (game.currentPlayer != i)
			{
			System.out.println(i + "." + players[i].getPersonality().getName() + " " + players[i].getColor() + ",");
			}
			choosenPlayer = keyIn.nextInt();
			bank.deposit(amount);
			player.transferMoneyto(amount,players[choosenPlayer]);
			bank.transferFunds(player, amount);
		}
		switch(cardId) 
		{			
			case 5:
				ArrayList<Area> troubleMarkersAreas = gameBoard.troubleMarkersAreas();
				System.out.println("Player "+choosenPlayer+"must choose an area to remove one minion (not your choice), an area with a trouble marker in it");
				for(int i=0; i<troubleMarkersAreas.size(); i++)
				{
					System.out.println(i + "." + troubleMarkersAreas.get(i).toString());
				}
				choosenArea = keyIn.nextInt();
				
				System.out.println("Select the color of minion you wish to remove:" );
				String tmpColor = input.next();
				Colors color = Colors.colorForString(tmpColor);
				//Remove a minion of the color specified by the player.
				troubleMarkersAreas.get(choosenArea).removeMinion(color);
				//Update the status of the player to whom the minion belonged to.
				Player playerbycolor=game.getPlayerByColor(color);
				playerbycolor.removeMinionOnBoard(choosenArea);
						
				break;		
			case 8:
				
				break;	
			case 44:
			paynRemoveMinion( 2 , cardId);	
		    break;
		    
		}
		
	}
	
	/**
	 * Remove one minion from Unreal Estate.
	 */
	private void removeUnrealEstateMinion( )
	{
		gameBoard.getAreas().get(2).getMinions().remove(1);
	}
}
