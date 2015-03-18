package ca.concordia.game.model;

import java.util.ArrayList;
import java.util.Map;

public class Action {

	private Gameboard gameBoard;
	private Player player;
	private Map<String,Deck> decks;
	Bank bank=Bank.getInstance();
	
	/**
	 * Constructor: initilializes an action according to Card's ID.
	 * @param cardId
	 */
	public Action(Gameboard agameBoard, Player aplayer, int cardId)
	{	
		this.gameBoard = agameBoard;
		this.player = aplayer;
		
		switch(cardId) 
		{

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
			
			
	     //Group3 : Pay another player then move minion
	     //5,8,44
			case 5: case 8: case 44:
			paynRemoveMinion( 2 );	
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

	//Perform Actions
	
	/**
	 * Add card to player playing cards.
	 * @param nbCards
	 */
	private void getCards(int nbCards)
	{
		for (int i = 0; i < nbCards; i++) 
		{
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
	 * Pay another player then remove one minion of their choice (not one of yours) from an area with a trouble marker in it.
	 */
	private void paynRemoveMinion(int amount) {
    //??
	}
	
	/**
	 * Remove one minion from Unreal Estate.
	 */
	private void removeUnrealEstateMinion( )
	{
		gameBoard.getAreas().get(2).getMinions().remove(1);
	}
}
