package ca.concordia.game.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import ca.concordia.game.main.Game;

public class Symbol {
	
	private int symbolId;
	private boolean isMandatory;
	private String description;
	
	/**
	 * Constructor: initilializes a symbol according to it's ID.
	 * @param symbolId
	 */
	public Symbol(int symbolId)
	{
		this.symbolId=symbolId;
		this.isMandatory=false;
		
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
	 * Function will redirect to the correct action depending on the ID of the symbol being called.
	 * @param currentPlayer
	 * @param game
	 */
	public void useSymbol(Player currentPlayer,Game game)
	{
		switch(this.symbolId) {
			case 1:
				placeMinionAction(currentPlayer,game);
				break;
			case 2:
				placeBuilding(currentPlayer,game);
				break;
			case 3:
				removeMinion(currentPlayer,game);
				break;
			case 4:
				removeTroubleMarker(currentPlayer,game);
				break;
			case 5:
				
				break;
			case 6:
				takeMoneyFromBank(currentPlayer,game);
				break;
			case 7:
				//Random event.
			
				break;
			case 8:
				//Play another card.
				
				break;
			case 9:
				//Interrupt card.
				
				break;
			default:
				break;
		}
	}
	
	/*
	 * To do: make the amount to take a variable that changes depending to which card it belongs to.
	 */
	/**
	 * Take a certain amount of moeny from the bank, if the bank has enough funds.
	 * @param currentPlayer
	 * @param currentPlayer(Player)
	 * @param game (Game)
	 */
	private boolean takeMoneyFromBank(Player currentPlayer,Game game)
	{
		//Amost all 
		int amountToTake=2;
		Bank bank=Bank.getInstance();
		
		//Check if bank has enough money.
		boolean check=bank.hasEnoughFunds(amountToTake);
		if(check)
			bank.transferFunds(currentPlayer, amountToTake);
		else
			System.out.println("Bank Doesn't have enough funds...Sorry; Bank funds:"+bank.getTotal());
		
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
		
		Scanner input=new Scanner(System.in);
		System.out.println("Please select the area code(Integer) from where you wish to remove a trouble marker:" );
		int selectedCardNumber = input.nextInt();
		input.close();
		
		boolean check=gameBoard.getAreas().get(selectedCardNumber).removeTroubleMarker();
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
				if(possibleVictims.get(i).getColor() != currentPlayer.getColor())//Only display minions that belong to a different player.
					if(j==possibleVictims.size()-1)//last item
						System.out.print(possibleVictims.get(i).getColor()+"\n");
					else
						System.out.print(possibleVictims.get(i).getColor()+",");
			}
			//Display number of trolls or demons if they exist.
			if(troubleMarkersAreas.get(i).getTroll()>=1)
				System.out.println("This area contains:"+troubleMarkersAreas.get(i).getTroll()+" trolls.");
			if(troubleMarkersAreas.get(i).getDemon()>=1)
				System.out.println("This area contains:"+troubleMarkersAreas.get(i).getDemon()+" demons.");
			
			System.out.println();//Blank Line
		}
		
		
		Scanner input=new Scanner(System.in);
		
		System.out.println("Please select the area code(Integer) from where you wish to remove a minion,demon or troll:" );
		int selectedCardNumber = input.nextInt();
		System.out.println("Enter 1 to remove a minion,2 to remove a troll and 3 to remove a demon:" );
		int type = input.nextInt();
		
		
		if(type==1)//Remove a minion 
		{
			System.out.println("Select the color of minion you wish to remove:" );
			String color = input.next();
			color=color.toUpperCase();
			//Remove a minion of the color specified by the player.
			gameBoard.getAreas().get(selectedCardNumber).removeMinion(new Piece(color));
			//Update the status of the player to whom the minion belonged to.
			Player player=game.getPlayerByColor(color);
			player.removeMinionOnBoard(selectedCardNumber);
			
		}
		else if(type==2)//remove a troll
		{
			boolean check=gameBoard.getAreas().get(selectedCardNumber).addRemoveDemon(2);//2 for removing
			if(!check)
				System.out.println("An error has occured(While removing a troll from the Board): Can't Remove it(Check Logic).");
			
		}
		else if(type==3)//remove a demon
		{
			boolean check=gameBoard.getAreas().get(selectedCardNumber).addRemoveDemon(2);//2 for removing
			if(!check)
				System.out.println("An error has occured(While removing a demon from the Board): Can't Remove it(Check Logic).");
		}
		
	
		
		input.close();
		
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
		
		//Find possible areas to put a Bulding on.
		for(int i=0;i<playerMinionsOnBoard.length;i++)
		{
			if(playerMinionsOnBoard[i]>0 && areas.get(i).getTroubleMarker() != true && areas.get(i).getBuilding()!=true)//player has at least one minion in the area and the area doesn't have a trouble maker or another Building.
			{
				Area areaWithminion=areas.get(i);
				//Add this area as a possible option to put bulding on.
				possibleAreas.add(areaWithminion.getCityCard().getCardNumber());
			}
								
		}
		
		//Display possible areas where player can place a building on and the corresponding price to place a building on that area.
		String display="";
		for(int i=0;i<possibleAreas.size();i++)
		{
			int cardNumber=possibleAreas.get(i);
			display=display+areas.get(i).getCityCard().getName()+"("+cardNumber+")"+" Price to place Building: "+areas.get(i).getCityCard().getBuldingCost()+"$" +", ";
		}
		//Remove last coma for displaying purposes.
		display=removeLastChar(display);
		System.out.println(display);
		
		//Ask player where he wishes to put a building on and check if the player has the money to do it.
		Scanner input=new Scanner(System.in);
		int selectedCardNumber;
		while(true)
		{
			//Ask player for input.
			System.out.println("Please select the cardNumber(Integer) where you wish to put your Building:");
			selectedCardNumber = input.nextInt();
			//Check if the area the player chooses is valid to put a building and if he has enough money to do it and if he has enough buildings on hand.
			if(possibleAreas.contains(selectedCardNumber) && currentPlayer.getMoney()>=areas.get(selectedCardNumber).getCityCard().getBuldingCost() && currentPlayer.getBuildingOnHand()>=1)
				break;
			else
				System.out.println("You can't put a Building there or you don't have enough money or you are out of buildings.");
		}
		input.close();
		
		//update Player,Bank,City Card Deck and Gameboard status.
		//Player has to be given the corresponding city card,a building has to be taken from his/her hand, money has to be updated.
		currentPlayer.getPlayerCityCard().add(gameBoard.deleteCardFromDeck(areas.get(selectedCardNumber).getCityCard()));//Add city card to player and delete it from the gameBoard
		
		int buildingOnHand=currentPlayer.getBuildingOnHand();
		buildingOnHand--;
		currentPlayer.setBuildingOnHand(buildingOnHand);
		//Update player's money.
		boolean check=currentPlayer.payMoney(areas.get(selectedCardNumber).getCityCard().getBuldingCost());
		
		if(check!= true)
			System.out.println("An error has occured(While Placing a Building on the Board): Player doesn't have enough money.");
		
		//Deposit into Bank the cost of the Building.
		Bank bank=Bank.getInstance();
		bank.deposit(areas.get(selectedCardNumber).getCityCard().getBuldingCost());
		
		//Update Area On GameBoard. now it has a Building and it needs to know the color to see which player it belongs to.
		check=areas.get(selectedCardNumber).addBuilding(currentPlayer);
		if(check!= true)
			System.out.println("An error has occured(While Placing a Building on the Board): The Area already has a Building.");
		
		return true;
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
		
		Scanner input=new Scanner(System.in);
		int selectedCardNumber;
		while(true)
		{
			//Ask player for input.
			System.out.println("Please select the cardNumber(Integer) where you wish to put your minion:");
			selectedCardNumber = input.nextInt();
			if(possibleAreas.contains(selectedCardNumber))
				break;
			else
				System.out.println("You can't put a minion there.");
		}
		input.close();
		
		//Update GameState.(Player and Boardgame)
		//update player.
		boolean success=currentPlayer.putNewMinionOnBoard(selectedCardNumber);//update player's minions.
		if(!success)
			System.out.println("Sorry you do not have any more minions on your hand.");
		//update Gameboard
		areas.get(selectedCardNumber).addMinion(new Piece(currentPlayer.getColor()));
		
		return true;
	}
	
	/**
	 * Delete every cardNumber that is repeated.
	 * @param arrayList
	 * @return ArrayList<Integer>
	 */
	private ArrayList<Integer> deleteAllRepetisions(ArrayList<Integer> arrayList)
	{
		HashSet tempHashSet = new HashSet();
		tempHashSet.addAll(arrayList);
		arrayList.clear();
		arrayList.addAll(tempHashSet);
		
		return arrayList;
	}
	
	/**
	 * Remove last character of a string if it's a coma.
	 * @param str
	 * @return
	 */
	private String removeLastChar(String str) {
	    if (str.length() > 0 && str.charAt(str.length()-1)==',') {
	      str = str.substring(0, str.length()-1);
	    }
	    return str;
	}

}
