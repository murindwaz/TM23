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
			
				break;
			case 3:
			
				break;
			case 4:
			
				break;
			case 5:
			
				break;
			case 6:
			
				break;
			case 7:
			
			
				break;
			case 8:
			
				break;
			case 9:
			
				break;
			default:
				break;
		}
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

}
