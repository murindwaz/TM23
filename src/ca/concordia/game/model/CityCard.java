package ca.concordia.game.model;

import java.util.ArrayList;
import java.util.Arrays;

import ca.concordia.game.main.Game;

/**
 * Calss City card is a type of Card. It contains the 12 different Area Cards a
 * unique card number and it's ability.
 *
 * @author Pascal Maniraho
 * @author Gustavo Pereira
 * @author Bhavik Desai
 * @author Jesus Esteban Garro Matamoros
 * @author Diego Pizarro
 *
 */

public class CityCard extends Card {
	public String ability = null;
	private int cardNumber;
	private ArrayList<Integer> adjacentAreas; // Contains adjacent area codes
												// referencing an area code.
	private int buildingCost;
	private boolean doesFlood = false;
	private int moneyTotake;
	private int moneyToPay;
	private boolean isActive;

	/**
	 * Constructor: Depending on the integer sent it sets the name and ability
	 * of a card and the adjacent areas to the city card.
	 * 
	 * @param i
	 */
	public CityCard(int i) {
		// City Cards are always Visible!
		super(true, false);
		
		this.isActive=true;//Card will always start as active(Can be used by player).
		this.moneyTotake=-1;
		this.moneyToPay=-1;
		this.adjacentAreas = new ArrayList<Integer>();
		Integer[] temp;
		switch (i) {
		case 0:
			this.setName("Dolly Sisters");
			this.cardNumber = 1;
			this.buildingCost = 6;
			this.ability = "Once per turn you can pay $3 and place one of your minions in Dolly Sisters or an adjacent area.";
			temp = new Integer[] { 2, 3, 12 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			this.doesFlood = true;
			this.moneyToPay=3;
			break;
		case 1:
			this.setName("Unreal Estate");
			this.cardNumber = 2;
			this.buildingCost = 18;
			this.ability = "At any point in your turn you can take one card from the deck and then discard one.";
			temp = new Integer[] { 1, 3, 4, 10, 11, 12 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			this.doesFlood = true;
			break;
		case 2:
			this.setName("Dragon's Landing");
			this.cardNumber = 3;
			this.buildingCost = 12;
			this.ability = "Once per turn you can take $2 from the bank.";
			temp = new Integer[] { 1, 2, 4 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			this.moneyToPay=2;
			break;
		case 3:
			this.setName("Small Gods");
			this.cardNumber = 4;
			this.buildingCost = 18;
			temp = new Integer[] { 2, 3, 5, 6, 10 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			this.ability="Whenever one of your pieces is affected by a random event you can pay $3 to ignore it.";
			this.doesFlood = true;
			break;
		case 4:
			this.setName("The Scours");
			this.cardNumber = 5;
			this.buildingCost = 6;
			this.ability="Once per turn you can discard one card and take $2 from the bank.";
			temp = new Integer[] { 4, 6, 7, 8, 10 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			this.moneyTotake=2;
			this.doesFlood = true;
			break;
		case 5:
			this.setName("The Hippo");
			this.cardNumber = 6;
			this.buildingCost = 12;
			temp = new Integer[] { 4, 5, 7 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			this.ability="Once per turn you can take $2 from the bank.";
			this.moneyTotake=2;
			break;
		case 6:
			this.setName("The Shades");
			this.cardNumber = 7;
			this.buildingCost = 6;
			temp = new Integer[] { 5, 6, 8 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			this.ability = "Once per turn you can place one trouble marker in the Shades or an adjacent area.";
			this.doesFlood = true;
			break;
		case 7:
			this.setName("Dimwell");
			this.cardNumber = 8;
			this.buildingCost = 6;
			temp = new Integer[] { 5, 7, 9 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			this.doesFlood = true;
			this.ability="Once per turn you can pay $3 and place one of your minions in Dimwell or an adjacent area.";
			this.moneyToPay=3;
			break;
		case 8:
			this.setName("Longwall");
			this.cardNumber = 9;
			this.buildingCost = 12;
			temp = new Integer[] { 8, 10, 11 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			this.doesFlood = true;
			this.ability="Once per turn you can take $1 from the bank.";
			this.moneyTotake=1;
			break;
		case 9:
			this.setName("Isle of Gods");
			this.cardNumber = 10;
			this.buildingCost = 12;
			temp = new Integer[] { 2, 4, 5, 9, 11 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			this.doesFlood = true;
			this.ability="Once per turn you can pay $2 and remove one trouble marker from the board.";
			this.moneyToPay=2;
			break;
		case 10:
			this.setName("Seven Sleepers");
			this.cardNumber = 11;
			this.buildingCost = 18;
			temp = new Integer[] { 2, 9, 10, 12 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			this.doesFlood = true;
			this.ability="Once per turn you can take $3 from the bank.";
			this.moneyTotake=3;
			break;
		case 11:
			this.setName("Nap Hill");
			this.cardNumber = 12;
			this.buildingCost = 12;
			temp = new Integer[] { 1, 2, 11 };
			this.adjacentAreas.addAll(Arrays.asList(temp));
			this.doesFlood = true;
			this.ability="Once per turn you can take $1 from the bank.";
			this.moneyTotake=1;
			break;
		default:
			System.out.println("Initializing City Card with the wrong index");
			break;
		}
	}

	
	/**
	 * Executes the correct city Card' action. The player who possess it executes it.
	 * @param player
	 * @param game
	 */
	public void useCityCard(Player player,Game game)
	{
		boolean check;
		switch(this.cardNumber-1) {
		case 0:
			//Pay 3 dollars to place a minion.
			check=this.paymoneyToBank(player, game);
			if(check)
				this.placeMinionAreaOrAdjacentArea(player, game);//Place minion.
			
			break;
		case 1:
			check=this.takeCard(player, game);
			if(check)
				this.discardCard(player, game);
			break;
		case 2:
			//take 2 money from bank.
			this.takeMoneyFromBank(player, game);
			break;
		case 3:
			
				
			break;
		case 4:
			check=this.discardCard(player, game);
			if(check)
				this.takeMoneyFromBank(player, game);
			break;				
		case 5:
			this.takeMoneyFromBank(player, game);
			break;
		case 6:
			this.placetroubleMarkerInArea(player, game);
			break;
		case 7:
			check=this.paymoneyToBank(player, game);
			if(check)
				this.placeMinionAreaOrAdjacentArea(player, game);
			break;
		case 8:
			this.takeMoneyFromBank(player, game);
			break;
		case 9:
			check=this.paymoneyToBank(player, game);
			if(check)
				this.removeTroubleMarker(player, game);
			break;
		case 10:
			this.takeMoneyFromBank(player, game);
			break;
		case 11:
			this.takeMoneyFromBank(player, game);
			break;
		default:
			break;
		}
	}
	
	
	/**
	 * Place a trouble marker in an area or adjacent areas to that area, only if there aren't any trouble markers on them.
	 * @param player
	 * @param game
	 * @return
	 */
	private boolean placetroubleMarkerInArea(Player player,Game game)
	{
		ArrayList<Integer> possibleAreas=new ArrayList<Integer>();//Will contain the possible areas a player can put a minion on.

		possibleAreas.add(this.getCardNumber());//Add current Area.
		//Add adjacent areas.
		possibleAreas.addAll(this.getAdjacentAreas());


		System.out.println("Player: "+player.getColor()+" you can put a trouble marker in the following areas, please select one:");
		//Display possible areas where player can place a minion on.
		String display="";
		for(int i=0;i<possibleAreas.size();i++)
		{
			int cardNumber=possibleAreas.get(i);
			//Check if area has trouble marker
			if(game.getGameBoard().getAreas().get(cardNumber-1).getTroubleMarker())
				display=display+game.getGameBoard().getAreas().get(cardNumber-1).getCityCard().getName()+"("+cardNumber+")"+", ";
			else
				possibleAreas.remove(i);
		}
		System.out.println(display);
		//Check if the there's at least one area where the player can set a trouble marker.
		if(possibleAreas.size()>0)
		{
			int selectedCardNumber;
			while(true)
			{
				//Ask player for input.
				System.out.println("Please select the cardNumber(Integer) where you wish to put the trouble marker:");
				selectedCardNumber = game.keyIn.nextInt();
				if(possibleAreas.contains(selectedCardNumber))
					break;
				else
					System.out.println("You can't put a trouble marker there.");
			}

			//Add trouble marker to selected area.
			game.getGameBoard().getAreas().get(selectedCardNumber-1).addTroubleMarker();
			return true;
		}else
		{
			System.out.println("You can't put any trouble marker in the current area or adjacent areas to it:");
			return false;
		}
		
		
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
	 * Function makes player take a card from either the greenDeck or the brown deck if the green deck is empty. further if the brown deck is emty or gets empty 
	 * after taking a card the game ends and a winning is calculated.
	 * @param currentPlayer (Player)
	 * @param game (Game)
	 * @return boolean
	 */
	private boolean takeCard(Player currentPlayer, Game game)
	{
		//Choose a deck to draw cards from. If the green deck is empty draw cards from the brown deck otherwise draw cards from the green deck.
		Deck drawDeck=game.getEspecificDeck("green");
		if(drawDeck.getSizeDeck()>0)
		{
			//give player one card from greenDeck.
			drawDeck.dealCardsToPlayer(currentPlayer, 1);
			//Display last card added to player.
			System.out.println("Player: "+currentPlayer.getColor()+" Draw the card:"+currentPlayer.getPlayerCards().get(currentPlayer.getPlayerCards().size()-1).toString());
			return true;
		}else //Draw from brown deck
		{
			drawDeck=game.getEspecificDeck("brown");
			if(drawDeck.getSizeDeck()>0)
			{
				//give player one card from brownDeck.
				drawDeck.dealCardsToPlayer(currentPlayer, 1);
				//Display last card added to player.
				System.out.println("Player: "+currentPlayer.getColor()+" Draw the card:"+currentPlayer.getPlayerCards().get(currentPlayer.getPlayerCards().size()-1).toString());
				return true;
			}

			//If the brown deck is empty then the game is over!!!!!
			if(drawDeck.getSizeDeck()<=0)
			{
				System.out.println("*****************************************The Game is Over*********************************************************");
				//TODO: Call Function to calculate who won the game and end Game.
				return false;
			}
		
			return true;
		}
		
	}
	
	//TODO: make function work with Brown cards.
	/**
	 * Function discards a card that is selected by the player. Returns true if successful otherwise returns false.
	* @param currentPlayer (Player)
	 * @param game (Game)
	 * @return boolean
	 */
	private boolean discardCard(Player currentPlayer, Game game)
	{
		
		int cardNumberInPlayerHand;
		
		System.out.println(currentPlayer.toString());
		
		while (true) {
			System.out.println("Please enter the card number you wish to discard(Select the number for 'Position in Player Hand'):");
			cardNumberInPlayerHand = game.keyIn.nextInt();
			if (cardNumberInPlayerHand > currentPlayer.getPlayerCards().size() - 1) {
				System.out.println("Incorrect input please select a number between 0 and "+ (currentPlayer.getPlayerCards().size() - 1));
			} else {
				break;
			}
		}
		
		// Get card chosen by player.
		// Get the actions for the chosen card.
		GreenCard chosenCard = (GreenCard) currentPlayer.getPlayerCards().get(cardNumberInPlayerHand);
		
		//PutCard in discardDeck.
		Deck discardDeck=game.getEspecificDeck("discard");
		discardDeck.putCard(chosenCard);
		boolean check=currentPlayer.removePlayerCard(chosenCard);//Remove card from player's hand.
		if(check)
		{
			System.out.println("Card Was put to discard deck and removed from player's hand.");
			return true;
		}
		return false;
	}
	
	/**
	 * Take a certain amount of moeny from the bank, if the bank has enough funds.
	 * @param currentPlayer (Player)
	 * @param game (Game)
	 * @return boolean
	 */
	private boolean takeMoneyFromBank(Player currentPlayer, Game game) {
		
		
		Bank bank = Bank.getInstance();
		// Check if bank has enough money.
		boolean check = bank.hasEnoughFunds(this.moneyTotake);
		if (check)
		{
			bank.transferFunds(currentPlayer, this.moneyTotake);
			System.out.println("You just got: "+this.moneyTotake+ "From the bank.");
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
	 * Asks player to place a minion on the current area or any area adjacent to it. Only works if the player has enough minions on his hand.
	 * Returns true if successful otherwise returns false.
	 * @param player(Player)
	 * @param game(Game)
	 * @return boolean
	 */
	private boolean placeMinionAreaOrAdjacentArea(Player player, Game game)
	{
		//Only execute if the player has enough minions on his/her hand.
		if(player.getMinionsOnHand()>0)
		{
			ArrayList<Integer> possibleAreas=new ArrayList<Integer>();//Will contain the possible areas a player can put a minion on.

			possibleAreas.add(this.getCardNumber());//Add current Area.
			//Add adjacent areas.
			possibleAreas.addAll(this.getAdjacentAreas());


			System.out.println("Player: "+player.getColor()+" you can put a minion in the following areas, please select one:");
			//Display possible areas where player can place a minion on.
			String display="";
			for(int i=0;i<possibleAreas.size();i++)
			{
				int cardNumber=possibleAreas.get(i);
				display=display+game.getGameBoard().getAreas().get(cardNumber-1).getCityCard().getName()+"("+cardNumber+")"+", ";
			}
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
			player.putNewMinionOnBoard(selectedCardNumber,false);//update player's minions.
			
			//update Gameboard
			game.getGameBoard().getAreas().get(selectedCardNumber-1).addMinion(new Piece(player.getColor()),false);
			System.out.println("Added minion");
			return true;
		}else
		{
			System.out.println("You don't have enough minions on hand. You can't place any minions.");
			return false;
		}
		
	}
	
	
	/**
	 * Function checks if player has enough money, if so it pays the bank the amount sent as an argument.Returns true if player has enough funds otherwise it returns false.
	 * @param player(Player)
	 * @param game(Game)
	 * @param amountToPay(int)
	 * @return boolean
	 */
	private boolean paymoneyToBank(Player player, Game game)
	{
		//first check if the player has enough money.
		if(player.getMoney()>= this.moneyToPay)
		{
			Bank bank=Bank.getInstance();
			bank.deposit(this.moneyToPay);
			player.payMoney(this.moneyToPay);
			System.out.println("Player: "+player.getColor()+" paid the bank: "+this.moneyToPay);
		}else
		{
			System.out.println("You don't have enough funds!!!");
			return false;
		}
		
		
		
		return true;
	}
	

	/**
	 * getter: Unique card Number.
	 * 
	 * @return int
	 */
	public int getCardNumber() {
		return this.cardNumber;
	}

	/**
	 * getter: cost of putting a building.
	 * 
	 * @return int
	 */
	public int getBuldingCost() {
		return this.buildingCost;
	}
	
	/**
	 * getter: returns of the  the area can get flooded.
	 * @return boolean
	 */
	public boolean getDoesFlood()
	{
		return this.doesFlood;
	}
	
	/**
	 * getter: returns cityCard's ability.
	 * @return
	 */
	public String getAbility()
	{
		return this.ability;
	}

	/**
	 * checks whether an area is adjacent to the current city area.
	 * 
	 * @param areaCode
	 * @return boolean
	 */
	public boolean isAdjacent(int areaCode) {
		return this.adjacentAreas.contains(areaCode);
	}

	/**
	 * Return all adjacent areas to the one being called.
	 * 
	 * @return ArrayList<Integer>
	 */
	public ArrayList<Integer> getAdjacentAreas() {
		return this.adjacentAreas;
	}
	
	/**
	 * Getter: Will return whether a city card is active(can be used).
	 * @return boolean
	 */
	public boolean getIsActive()
	{
		return this.isActive;
	}
	
	/**
	 * Setter: Set's weather a city card can be played.
	 * @param active(boolean)
	 */
	public void setIsActive(boolean active)
	{
		this.isActive=active;
	}
	

}
