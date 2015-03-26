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
	private Scanner keyIn;
	private Scanner input;
	private Player[] players;
	private Die die;
	
	Bank bank=Bank.getInstance();

	private int choosenPlayer;
	private int choosenArea;
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
			this.choosenPlayer = 0;
			this.die = new Die();
			rollValue = -1;

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
				//9 : Exchange minions position
			case 9: 
				exchangeMinions( );	
				break;				
				//10 Look other player's cards and discard one
			case 10:
				discardOthersCard( );
				//Group4: Move a minion belonging to another player from one area to an adjacent area
				//11,62,72
			case 11: case 62: case 72:
				moveMinion( );	
				break;
				
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
		for(int i=0; i<game.numberOfPlayers; i++)
		{
			if (game.currentPlayer != i)
			{
				System.out.println(i + "." + players[i].getPersonality().getName() + " " + players[i].getColor() + ",");
			}
		}
		choosenPlayer = keyIn.nextInt();

		return this.choosenPlayer;

	}

	/**
	 * Choose an Area
	 * @return choosenArea
	 */
	private int chooseArea( boolean hasMinions ){ 
		for(int i=0; i<12; i++)
		{
			if (hasMinions)
				if (gameBoard.getAreas().get(i).getMinions().isEmpty())
					continue;
/*			if (adjacentTo.)
				if (gameBoard.getAreas().get(i).adjacentTo())
					continue;
*/				
			System.out.println(i + "." + gameBoard.getAreas().get(i).toString());				
		}		
		choosenArea = keyIn.nextInt();
		return this.choosenArea;

	}
	/**
	 * Add card to player playing cards.
	 * Perform Actions
	 * @param nbCards
	 */
	private void getCards(int nbCards){
		for( int i = 0; i < nbCards; i++ ){
			player.receiveCard(game.getDecks().get("green").getCard());
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
		System.out.println("Choose a player to receive $"+amount+" dollars:");
		player.transferMoneyto(amount,players[choosePlayer()]);
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
			fromArea = this.chooseArea(true);

			while(true) {
				System.out.println("Choose an area to move one minion to:");
				toArea = this.chooseArea(true);

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
	 * Move a minion belonging to another player from one area to an adjacent area.
	 */
	private void moveMinion( )
	{
		int fromArea, toArea;		

		while (true) 
		{

			System.out.println("Select the color of minion you wish to move:" );
			String tmpColor = input.next();
			Colors color = Colors.colorForString(tmpColor);

			Player playerbycolor = game.getPlayerByColor(color);

			if (color.equals(player.color)) {
				System.out.println("Minion should belong to another player.");
				continue;
			}

			while(true) {

				System.out.println("Choose an area to move this minion from:");
				fromArea = this.chooseArea(true);

				if (gameBoard.getAreas().get(fromArea).getMinions().isEmpty()) {
					System.out.println("This Area has no Minions");
					continue;
				}
				else
					break;
			}

			System.out.println("Choose an adjacent area to move this minion to:");
			toArea = this.chooseArea(true); //should be adjacent area

			if (playerbycolor.moveMinionToNewArea( fromArea, toArea ))
				break;
			else {
				System.out.println("Not possible to move to this Area");
				continue;
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
        
		
		System.out.println("Select a player to exchange all your Cards:");
        ArrayList<Card> playerCards = players[choosePlayer()].getPlayerCards();
        
        players[choosenPlayer].loseAllCards();
        players[choosenPlayer].receiveAllCards( players[game.currentPlayer].getPlayerCards());
        
        players[game.currentPlayer].loseAllCards();
        players[game.currentPlayer].receiveAllCards(playerCards);
	}	
}

