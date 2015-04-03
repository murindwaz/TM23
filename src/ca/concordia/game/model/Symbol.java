package ca.concordia.game.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import ca.concordia.game.common.common.Colors;
import ca.concordia.game.main.Game;

public class Symbol {

	private int symbolId;
	private boolean isMandatory;
	private String description;

	private int moneyToTake;
	/**
	 * Constructor: initilializes a symbol according to it's ID. If it is case 5(take money from bank) then the amount of money to take will be set. Otherwise money to take will always be negative.
	 * @param symbolId
	 */
	public Symbol(int symbolId,int money){
		this.symbolId		=	symbolId;
		this.isMandatory	=	false;
		this.moneyToTake	=	-1;
		switch(symbolId) {
			case 1:
				this.description="Place a minion in adjacent are where one possesses a minion.";
				break;
			case 2:
				this.description="Place a building, where one possesses a minion and there's no trouble markers in the area.";
				break;
			case 3:
				this.description="Remove a minion where there's a trouble marker.Remove the trouble marker as well.";
				break;
			case 4:
				this.description="Remove a trouble marker from the board.";
				break;
			case 5:
				this.description="Take money indicated in the card.";
				this.moneyToTake=money;
				break;
			case 6:
				this.description="Perform the action described in the text at the bottom of the card.";
				break;
			case 7:
				this.description="Take a random event card and see effects.";
				this.isMandatory=true;
				break;
			case 8:
				this.description="Play another card";
				break;
			case 9:
				this.description="Iterrupt Card(Can be used at any point in the game.)";
				break;
			default:
				System.out.println("Symbol Id doesn't exist.");
				break;
		}
	}

	/**
	 * Function will redirect to the correct action depending on the ID of the
	 * symbol being called.
	 * 
	 * @param currentPlayer
	 * @param game
	 */
	public boolean useSymbol(Player currentPlayer, Game game, int cardID) {
		boolean playAnotherCard = false;
		switch (this.symbolId) {
		case 1:
			placeMinionAction(currentPlayer, game);
			break;
		case 2:
			placeBuilding(currentPlayer, game);

			break;
		case 3:
			removeMinion(currentPlayer, game);
			break;
		case 4:
			removeTroubleMarker(currentPlayer, game);
			break;
		case 5:
			takeMoneyFromBank(currentPlayer, game);
			break;
		case 6:
			new Action(currentPlayer, game, cardID);
			break;
		case 7:
			// Random event.
			randomEvent(currentPlayer, game);
			break;
		case 8:
			// Play another card.
			playAnotherCard = playAnotherCard(currentPlayer, game);
			break;
		case 9:
			// Interrupt card.
			interruptCard(currentPlayer, game, cardID);
			break;
		default:
			break;
		}

		return playAnotherCard;
	}

	/**
	 * Return the symbol's ID.
	 * @return int
	 */
	public int getSymbolId()
	{
		return this.symbolId;
	}
	/**
	 * Returns the symbol's description.
	 * @return String
	 */
	public String getDescription() {
		return this.description;
	}

	/*
	 * TODO: make the amount to take a variable that changes depending to which
	 * card it belongs to.
	 */
	/**
	 * Take a certain amount of moeny from the bank, if the bank has enough funds.
	 * @param currentPlayer
	 * @param currentPlayer (Player)
	 * @param game (Game)
	 */
	private boolean takeMoneyFromBank(Player currentPlayer, Game game) {
		
		
		Bank bank = Bank.getInstance();
		// Check if bank has enough money.
		boolean check = bank.hasEnoughFunds(this.moneyToTake);
		if (check)
		{
			bank.transferFunds(currentPlayer, this.moneyToTake);
			System.out.println("Player: "+currentPlayer.getColor()+" got: "+this.moneyToTake+" from the bank.");
		}
		else
		{//If there's not enough funds, give the player the avialable funds.
			System.out.println("Bank Doesn't have enough funds...Sorry; Bank funds:" + bank.getTotal());
			System.out.println("You'll get the money available to the bank.");
			bank.transferFunds(currentPlayer, bank.getTotal());
			
		}
		return true;
	}

	/**
	 * Removes a trouble marker from area depending on player's input. Can only remove a trouble marker if the area contain one.
	 * Function will display all areas that currently contain a trouble marker.
	 * @param currentPlayer
	 * @param currentPlayer(Player)
	 * @param game (Game)
	 */
	private boolean removeTroubleMarker(Player currentPlayer,Game game)
	{
		Gameboard gameBoard=game.getGameBoard();
		//Get all the areas that contain a trouble marker.
		ArrayList<Area> troubleMarkersAreas=gameBoard.troubleMarkersAreas();
		
		System.out.println("The following are areas with trouble markers:");
		for(int i=0;i<troubleMarkersAreas.size();i++)
		{
			System.out.println("Area: "+troubleMarkersAreas.get(i).getCityCard().getName()+"("+troubleMarkersAreas.get(i).getCityCard().getCardNumber()+"):");
		}
		
		
		System.out.println("Please select the area code(Integer) from where you wish to remove a trouble marker:" );
		int selectedCardNumber = game.keyIn.nextInt();
		
		
		boolean check=gameBoard.getAreas().get(selectedCardNumber-1).removeTroubleMarker();
		if(!check)
			System.out.println("An error has occured(While removing a trouble marker from the Board): Can't Remove it(Check Logic).");
		
		
		return true;
	}
	
	/**
	 * Remove a minion,troll or demon from gameboard, depending on user input. Can only remove a minion if there's a trouble marker in  the area
	 * Further, player can not remove one of their minions.
	 * @param currentPlayer
	 * @param currentPlayer(Player)
	 * @param game (Game)
	 */
	private boolean removeMinion(Player currentPlayer,Game game)
	{
		//Get gameboard areas.
		Gameboard gameBoard=game.getGameBoard();
		//ArrayList<Area> areas= gameBoard.getAreas();
		//Get all the areas that contain a trouble marker.
		ArrayList<Area> troubleMarkersAreas=gameBoard.troubleMarkersAreas();
		
		ArrayList<Piece> possibleVictims;//will contain the minions that can be assassinated per area.
		
		ArrayList<Area> areas= gameBoard.getAreas();
		
		System.out.println("The following are areas with trouble markers:");
		//Display minions,trolls and demosn in an area
		for(int i=0;i<troubleMarkersAreas.size();i++)
		{
			System.out.println("Area: "+troubleMarkersAreas.get(i).getCityCard().getName()+"("+troubleMarkersAreas.get(i).getCityCard().getCardNumber()+"):");
			possibleVictims=troubleMarkersAreas.get(i).getMinions();
			//Display all minions that do not belong to the player.
			System.out.print("Minions on area:");
			for(int j=0;j<possibleVictims.size();j++)
			{
				if(!possibleVictims.get(j).getColor().equals(currentPlayer.getColor()) )//Only display minions that belong to a different player.
					if(j==possibleVictims.size()-1)//last item
						System.out.print(possibleVictims.get(j).getColor()+"\n");
					else
						System.out.print(possibleVictims.get(j).getColor()+",");
			}
			//Display number of trolls or demons if they exist.
			if(troubleMarkersAreas.get(i).getTroll()>=1)
				System.out.println("This area contains:"+troubleMarkersAreas.get(i).getTroll()+" trolls.");
			if(troubleMarkersAreas.get(i).getDemon()>=1)
				System.out.println("This area contains:"+troubleMarkersAreas.get(i).getDemon()+" demons.");
			
			System.out.println();//Blank Line
		}
		
		
		
		System.out.println("Please select the area code(Integer) from where you wish to remove a minion,demon or troll:" );
		int selectedCardNumber = game.keyIn.nextInt();
		System.out.println("Enter 1 to remove a minion,2 to remove a troll and 3 to remove a demon:" );
		int type = game.keyIn.nextInt();
		
		
		if(type==1)//Remove a minion 
		{
			System.out.println("Select the color of minion you wish to remove:" );
			String color = game.keyIn.next();
			color=color.toUpperCase();
			//Get respective color from enum.
			Colors Ccolor=Colors.colorForString(color);
			//Remove a minion of the color specified by the player.
			gameBoard.getAreas().get(selectedCardNumber-1).removeMinion(Ccolor);
			gameBoard.getAreas().get(selectedCardNumber-1).removeTroubleMarker();
			//Update the status of the player to whom the minion belonged to.
			Player player=game.getPlayerByColor(Ccolor);
			player.removeMinionOnBoard(selectedCardNumber);

			
		}
		else if(type==2)//remove a troll
		{
			boolean check=gameBoard.getAreas().get(selectedCardNumber-1).addRemoveDemon(2);//2 for removing
			if(!check)
				System.out.println("An error has occured(While removing a troll from the Board): Can't Remove it(Check Logic).");

			
		}
		else if(type==3)//remove a demon
		{
			boolean check=gameBoard.getAreas().get(selectedCardNumber-1).addRemoveDemon(2);//2 for removing
			if(!check)
				System.out.println("An error has occured(While removing a demon from the Board): Can't Remove it(Check Logic).");

		}
		
			
		
		return true;
	}
	
	/*
	 * To do: let the player choose the option not to place a building. 
	 */
	/**
	 * Performs the place a building symbol. A player is allowed to place a building in an area  where he possesses a least one minion and the area
	 * does not contain a trouble marker. Once a Building is placed the player get's the area's city card. further a player has to have enough money
	 * to place a building, depending on the cost that depends on the area the player wishes to place a building on. Further this updates the player,the
	 * gameboard, the area and the bank classes; after a building is purchased and built on the area chosen by the player.
	 * @param currentPlayer
	 * @param currentPlayer(Player)
	 * @param game (Game)
	 */
	private boolean placeBuilding(Player currentPlayer,Game game)
	{
		ArrayList<Integer> possibleAreas=new ArrayList<Integer>();//Will contain the possible areas a player can put a bulding on.
		//Get the minion on the board for the calling player.
		int [] playerMinionsOnBoard;
		playerMinionsOnBoard=currentPlayer.getMinionsOnArea();
		
		//Get gameboard areas.
		Gameboard gameBoard=game.getGameBoard();
		ArrayList<Area> areas= gameBoard.getAreas();
		
		System.out.println("Player: "+currentPlayer.getColor()+" you can put a building in the following areas:");
		
		//Find possible areas to put a Building on.
		for(int i=0;i<playerMinionsOnBoard.length;i++)
		{
			if(playerMinionsOnBoard[i]>0 && areas.get(i).getTroubleMarker() != true && areas.get(i).getBuilding()!=true)//player has at least one minion in the area and the area doesn't have a trouble maker or another Building.
			{
				Area areaWithminion=areas.get(i);
				//Add this area as a possible option to put bulding on.
				possibleAreas.add(areaWithminion.getCityCard().getCardNumber());
			}
								
		}
		
		
		if(possibleAreas.size()>0)
		{
			//Display possible areas where player can place a building on and the corresponding price to place a building on that area.
			String display="";
			for(int i=0;i<possibleAreas.size();i++)
			{
				int cardNumber=possibleAreas.get(i);
				display=display+areas.get(cardNumber-1).getCityCard().getName()+"("+cardNumber+")"+" Price to place Building: "+areas.get(cardNumber-1).getCityCard().getBuldingCost()+"$" +", ";
			}
			//Remove last coma for displaying purposes.
			display=removeLastChar(display);
			System.out.println(display);

			//Ask player where he wishes to put a building on and check if the player has the money to do it.

			int selectedCardNumber;
			while(true)
			{
				//Ask player for input.
				System.out.println("Please select the cardNumber(Integer) where you wish to put your Building, otherwise enter -1 not place one:");
				selectedCardNumber = game.keyIn.nextInt();
				//Check if the area the player chooses is valid to put a building and if he has enough money to do it and if he has enough buildings on hand.
				if(possibleAreas.contains(selectedCardNumber) && currentPlayer.getMoney()>=areas.get(selectedCardNumber-1).getCityCard().getBuldingCost() && currentPlayer.getBuildingOnHand()>=1  )
					break;
				else if(selectedCardNumber<0)
					break;

				else
					System.out.println("You can't put a Building there or you don't have enough money or you are out of buildings.");
			}

			if(selectedCardNumber>0)
			{
				//update Player,Bank,City Card Deck and Gameboard status.
				//Player has to be given the corresponding city card,a building has to be taken from his/her hand, money has to be updated.
				currentPlayer.getPlayerCityCard().add(gameBoard.deleteCardFromDeck(areas.get(selectedCardNumber-1).getCityCard()));//Add city card to player and delete it from the gameBoard

				int buildingOnHand=currentPlayer.getBuildingOnHand();
				buildingOnHand--;
				currentPlayer.setBuildingOnHand(buildingOnHand);
				//Update player's money.
				boolean check=currentPlayer.payMoney(areas.get(selectedCardNumber-1).getCityCard().getBuldingCost());

				if(check!= true)
					System.out.println("An error has occured(While Placing a Building on the Board): Player doesn't have enough money.");

				//Deposit into Bank the cost of the Building.
				Bank bank=Bank.getInstance();
				bank.deposit(areas.get(selectedCardNumber-1).getCityCard().getBuldingCost());

				//Update Area On GameBoard. now it has a Building and it needs to know the color to see which player it belongs to.
				check=areas.get(selectedCardNumber-1).addBuilding(currentPlayer);
				if(check!= true)
					System.out.println("An error has occured(While Placing a Building on the Board): The Area already has a Building.");
			}else
				System.out.println("You did not place any buildings on the board.");
			
			return true;
		}else
		{
			System.out.println("Player: "+currentPlayer.getColor()+" you can not place any buildings anywhere.");
			return false;
		}
		
	}
	
	/*
	 * to do: add a trouble marker if area contains one or more minions on the area.
	 * let player choose if he decides not to place a minion.
	 */
	
	/**
	 * Performs the place Minion on Board symbol. If it's successful it will return true.
	 * Displays possible areas where the player can place a minion if he has 1 or more minions on hand. 
	 * The player selects an area and the Player's and gameboard's status get's updated.
	 * @param currentPlayer(Player)
	 * @param game (Game)
	 * @return boolean
	 */
	private boolean placeMinionAction(Player currentPlayer,Game game)
	{
		ArrayList<Integer> possibleAreas=new ArrayList<Integer>();//Will contain the possible areas a player can put a minion on.
		//Get the minion on the board for the calling player.
		int [] playerMinionsOnBoard;
		playerMinionsOnBoard=currentPlayer.getMinionsOnArea();
		//Get gameboard areas.
		Gameboard gameBoard=game.getGameBoard();
		ArrayList<Area> areas= gameBoard.getAreas();
		
		System.out.println("Player: "+currentPlayer.getColor()+" you can put a minion in the following areas:");
		//Find possible areas to put a minion on.
		for(int i=0;i<playerMinionsOnBoard.length;i++)
		{
			if(playerMinionsOnBoard[i]>0)//player has at least one minion on the area.
			{
				Area areaWithminion=areas.get(i);
				//Add this area as a possible option to put minion on; Also add the adjacent areas.
				possibleAreas.add(areaWithminion.getCityCard().getCardNumber());
				possibleAreas.addAll(areaWithminion.getCityCard().getAdjacentAreas());
			}
						
		}
		//Delete repeated areas.
		possibleAreas=deleteAllRepetisions(possibleAreas);
		//Display possible areas where player can place a minion on.
		String display="";
		for(int i=0;i<possibleAreas.size();i++)
		{
			int cardNumber=possibleAreas.get(i);
			display=display+areas.get(i).getCityCard().getName()+"("+cardNumber+")"+", ";
		}
		//Remove last coma for displaying purposes.
		display=removeLastChar(display);
		System.out.println(display);
		
		
		int selectedCardNumber;
		while(true)
		{
			//Ask player for input.
			System.out.println("Please select the cardNumber(Integer) where you wish to put your minion:");
			selectedCardNumber = game.keyIn.nextInt();
			if(possibleAreas.contains(selectedCardNumber))
				break;
			else
				System.out.println("You can't put a minion there.");
		}
		
		
		//Update GameState.(Player and Boardgame)
		//update player.
		boolean success=currentPlayer.putNewMinionOnBoard(selectedCardNumber,false);//update player's minions.
		if(!success)
			System.out.println("Sorry you do not have any more minions on your hand.");
		//update Gameboard
		areas.get(selectedCardNumber-1).addMinion(new Piece(currentPlayer.getColor()),false);
		
		return true;
	}

	/**
	 * randomEvent function makes the player draw an event card and execute the
	 * proper action depending on the card drawn. It calls the function
	 * useEventCard() which calls the right event inside class eventCard.
	 *s 
	 * @param currentPlayer - (Player)
	 * @param game -  (Game)
	 */
	private boolean randomEvent(Player currentPlayer, Game game) {
		// make player draw an event Card.
		Deck events = game.getEspecificDeck("events");
		if (events.getSizeDeck() <= 0) {
			System.out.println("Event Card is empty.");
			return false;
		} else {
			// Get top event card.
			EventCard eventCard = (EventCard) events.getCard();
			System.out.println("Player:" + currentPlayer.getColor() + " draw event card: " + eventCard.getEventCardName() + ".");
			System.out.println("Description: " + eventCard.getInstruction());
			// Execute event card function depending on the card drawn.
			eventCard.useEventCard(currentPlayer, game);
		}
		return true;
	}

	/**
	 * Function returns a boolean to signal that this event has been called. The
	 * action will be handled in the player's play state.
	 * 
	 * @param currentPlayer
	 *            (Player)
	 * @param game
	 *            (Game)
	 */
	private boolean playAnotherCard(Player currentPlayer, Game game) {
		System.out.println("Play another card...");
		return true;
	}

	/**
	 * Function calls class action with the interrupt card's ID so the right
	 * action can be perform.
	 * 
	 * @param currentPlayer
	 *            (Player)
	 * @param game
	 *            (Game)
	 * @param cardID
	 *            (int)
	 */
	private void interruptCard(Player currentPlayer, Game game, int cardID) {
		//new Action(currentPlayer,game,cardID);// Perform the corresponding action from the card.
	}

	/**
	 * Delete every cardNumber that is repeated.
	 * 
	 * @param arrayList
	 * @return ArrayList<Integer>
	 */
	private ArrayList<Integer> deleteAllRepetisions(ArrayList<Integer> arrayList) {
		HashSet<Integer> tempHashSet = new HashSet<Integer>();
		tempHashSet.addAll(arrayList);
		arrayList.clear();
		arrayList.addAll(tempHashSet);
		return arrayList;
	}

	/**
	 * Remove last character of a string if it's a coma.
	 * 
	 * @param str
	 * @return
	 */
	private String removeLastChar(String str) {
		if (str.length() > 0 && str.charAt(str.length() - 1) == ',') {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}
	/**
	 * Getter: for boolean isMandatory. Tells whether a symbol is mandatory to play.
	 * @return boolean
	 */
	public boolean getIsMandatory()
	{
		return this.isMandatory;
	}
	
	
	

}
