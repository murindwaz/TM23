package ca.concordia.game.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import ca.concordia.game.gameState.StateContext;
import ca.concordia.game.model.*;
import ca.concordia.game.util.*;
import ca.concordia.game.common.common;
import ca.concordia.game.common.common.Colors;

/**
 * Game class creates a new game, loads and saves a game state. Further it
 * specifies and controls the logic required to play a game.
 *
 * @author Pascal Maniraho
 * @author Gustavo Pereira
 * @author Bhavik Desai
 * @author Jesus Esteban Garro Matamoros
 * @author Diego Pizarro
 */
public class Game {

	private static Game instance = null;

	/**
	 * This will contain the state each player is at the moment.And the actions
	 * he can perform.
	 */
	private ArrayList<StateContext> playerStatus;
	private Gameboard gameboard;
	private Map<String, Deck> decks;
	private Bank bank;
	private Die die;
	private ArrayList<Symbol> symbols;
	public  Scanner keyIn= new Scanner(System.in);;
	public int currentPlayer;
	public int numberOfPlayers;
	private Player[] players;

	/**
	 * This function initialiazes a new Game - Since the current player is not
	 * yet detemined, it sets currentPlayer to -1 - Creates a new Instance of
	 * Bank. Note the Bank has to be unique. - Initializes the balance to
	 * Initial Balance of 120. - Initializes the Scanner instance to be used in
	 * the game - Initializes the number of players via CLI
	 */
	public String init() {

		currentPlayer = -1;
		this.bank = Bank.getInstance();
		bank.setBankMoney(new AtomicInteger(Configuration.DEFAULT_BALANCE));
		
		System.out.println("Please select number of players(Maximun => 4):");
		numberOfPlayers = keyIn.nextInt();
		this.symbols = new ArrayList<Symbol>();
		
		
		//All symbols are initialize in the Green or Brown card classes.
		

		// Close Scanner object
		// keyIn.close();//Don't close until done using in whole proyect.
		this.decks = new HashMap<String, Deck>();
		this.decks.put("discard", new Deck("D", numberOfPlayers));
		this.decks.put("personalities", new Deck("P", numberOfPlayers));
		this.decks.put("events", new Deck("E", numberOfPlayers));
		this.decks.put("green", new Deck("G", numberOfPlayers));
		this.decks.put("brown", new Deck("B", numberOfPlayers));

		// Select number of players and their colors.
		// For now we use four players of fixed colors:
		this.players = new Player[numberOfPlayers];
		for (int i = 0; i < numberOfPlayers; i++) {
			this.players[i] = new Player((PersonalityCard) decks.get("personalities").getCard(),
					Colors.colorForIndex(i), 12, 6);
			// Deal 5 green cards to each player:
			decks.get("green").dealCardsToPlayer(players[i], 5);
			// Give $10 to each player:
			bank.transferFunds(players[i], 10);
		}

		// Depending on the number of players initialize their States(to wait).
		playerStatus = new ArrayList<StateContext>();// put on heap.

		for (int i = 0; i < numberOfPlayers; i++) {
			playerStatus.add(new StateContext());
			System.out.println("Player:" + players[i].getColor() + " Current State:" + playerStatus.get(i).getState());
			/**
			 * Set to next state(Play State.)So players are ready to play when
			 * their turn comes up.
			 */
			playerStatus.get(i).performAction(players[i], this);
		}
		// Initialize Gameboard:
		this.gameboard = new Gameboard(this.players);
		this.die = new Die();
		return "Initialization was succssessfull";
	}

	// Start playing game instance.
	public void play(boolean loadedGame) {
		
		if(!loadedGame)//If the game was not loaded.
		{
			Map<Integer, Colors> playerDieRollMap = new HashMap<Integer, Colors>();
			ArrayList<Integer> playerDieRoll = new ArrayList<Integer>();
			int rollValue = -1;
			// roll dice for each player to pick first player.
			for (int i = 0; i < this.numberOfPlayers; i++) {
				rollValue = die.roll();// roll dice for player.
				System.out.println("Player with color: " + this.players[i].getColor() + " rolled:" + rollValue);
				playerDieRoll.add(rollValue);// store value gotten by player.
				/**
				 * Store Player color,roll value pair.
				 */
				playerDieRollMap.put(rollValue, this.players[i].getColor());
			}

			int highestRoll = highestValue(playerDieRoll);
			Colors startingColor = playerDieRollMap.get(highestRoll);
			System.out.println("The player with the color:" + startingColor + " starts the game.");
			// Set the pointer to the starting player in the array.
			for (int i = 0; i < this.numberOfPlayers; i++) {
				// Found a match
				if (this.players[i].getColor().equals(startingColor)) {
					currentPlayer = i;
					break;
				}
			}
		}
		// Keep going until a player wins the game.
		while (true){
			System.out.println();
			System.out.println("**************************************Game has begun!!!!!!************************************");
			System.out.println();
			// Start Playing select the player who's turn it is.
			this.playerStatus.get(currentPlayer).performAction(players[currentPlayer], this);
			System.out.println("Player:" + players[currentPlayer].getColor() + " Current State:"
					+ playerStatus.get(currentPlayer).getState());
			// Draw Cards State
			this.playerStatus.get(currentPlayer).performAction(players[currentPlayer], this);
			System.out.println("Player:" + players[currentPlayer].getColor() + " Current State:"
					+ playerStatus.get(currentPlayer).getState());
			this.playerStatus.get(currentPlayer).performAction(players[currentPlayer], this);
			System.out.println("Player:" + players[currentPlayer].getColor() + " Current State:"
					+ playerStatus.get(currentPlayer).getState());
			currentPlayer = nextPlayer();
			System.out.println("Do you wish to exit?? Enter -1, otherwise enter 1.");
			int exit = this.keyIn.nextInt();
			if (exit < 1) {
				break;
			}
		}
		
		System.out.println("Do you wish to save the current game?");
		String save=this.keyIn.next();
		if(save.contains("y"))
		{
			System.out.println("Preparing to save game.");
			this.saveGame();
		}else
		{
			System.out.println("You chose not to save the game.The game state has been lost.");
		}
		//don't close beacue a game might be loaded.
		//this.keyIn.close();
	}

	/**
	 * Return the highest value in an Arraylist of integers.
	 * @param arrayList (ArrayList<Integer>)
	 * @return int
	 */
	public int highestValue(ArrayList<Integer> arrayList) {
		Collections.sort(arrayList); // Sort the arraylist
		int highest = arrayList.get(arrayList.size() - 1); 
		return highest;
	}

	/**
	 * Implements Game as a singleton, as there will always be a single game per run.
	 * @return Game
	 */
	public static Game getInstance() {
		if( instance == null ){
			instance = new Game();	
		}
		return instance;
	}

	/**
	 * Constructor, initializes a new game.
	 */
	protected Game() { }

	/**
	 * @deprecated - this object comparator has not been used, and should retire 
	 * @param gameState
	 * @return
	 */
	public boolean equals(Game gameState) {
		return false;
	}

	/**
	 * This function - Sets currentPlayer - Returns the player who's turn is up.
	 * 
	 * @return int currentPlayer
	 */
	public int nextPlayer() {
		currentPlayer = (currentPlayer + 1) % numberOfPlayers;
		return currentPlayer;
	}

	/**
	 * Save game State.In the correct format.
	 */
	public String saveGame() {
		
		//temporal variables.
		String temp = "";
		ArrayList<String> content = new ArrayList<String>();
		BrownCard bCard;
		GreenCard gCard;
		
		// Store GameBoard's Info.
		for (int i = 0; i < 12; i++) {
			temp = temp + this.gameboard.getAreas().get(i).getCityCard().getName() + ",";
			temp = temp + this.gameboard.getAreas().get(i).getTroubleMarker() + ",";
			temp = temp + this.gameboard.getAreas().get(i).getBuildingColor() + ",";
			temp = temp + this.gameboard.getAreas().get(i).getDemon() + ",";
			temp = temp + this.gameboard.getAreas().get(i).getTroll() + ",";

			// Get Minions of each class
			for (int j = 0; j < this.gameboard.getAreas().get(i).getMinions().size(); j++) {
				temp = temp + this.gameboard.getAreas().get(i).getMinions().get(j).getColor() + ",";
			}
			// remove last coma
			temp = removeLastChar(temp);
			// Store new Area info into the content array
			content.add(temp);
			temp = "";
		}

		// Store Player's Info.
		temp = temp + this.players.length;// Get totalNumber of players.
		content.add(temp);
		temp = "";

		for (int i = 0; i < this.players.length; i++) {
			int personalityId=((PersonalityCard) this.players[i].getPersonality()).getCardId() -1;
			temp = temp + personalityId + ",";
			temp = temp + this.players[i].getColor() + ",";
			temp = temp + this.players[i].getMinionsOnHand() + ",";
			temp = temp + this.players[i].getBuildingOnHand() + ",";
			temp = temp + this.players[i].getMoney() + ",";
			temp = temp + this.players[i].getPlayerCards().size() + ",";

			for (int j = 0; j < this.players[i].getPlayerCards().size(); j++) {
				
				if (this.players[i].getPlayerCards().get(j).getClass().toString().contains("BrownCard")) {
					// The card is of type BrownCard, convert to brown card.
					bCard = (BrownCard) this.players[i].getPlayerCards().get(j);
					temp = temp + bCard.getNumber() + ",";
				} else if (this.players[i].getPlayerCards().get(j).getClass().toString().contains("GreenCard")){
					// The card is of type GreenCard, convert to green card.
					gCard = (GreenCard) this.players[i].getPlayerCards().get(j);
					temp = temp + gCard.getNumber() + ",";
				}else
				{
					System.out.println("ERROR(Class Game,function saveGame):The card is not of either Brown or green color.");
				}

			}
			temp = temp + this.players[i].getPlayerCityCard().size() + ",";
			for (int j = 0; j < this.players[i].getPlayerCityCard().size(); j++) {
				temp = temp + this.players[i].getPlayerCityCard().get(j).getCardNumber() + ",";
			}
			// remove last character(the coma)
			temp = removeLastChar(temp);
			// Add new player line to ArrayList.
			content.add(temp);
			temp = "";
		}
		temp = "";
		// Add the banks current bank balance to arraylist content.
		temp = temp + this.bank.getTotal();
		content.add(temp);

		// Write current game's state to
		Saver.saveGameState(content);

		return "Save Was Successfull";
	}

	/**
	 * Remove last character of a string if it's a coma.
	 * 
	 * @param str
	 * @return
	 */
	public String removeLastChar(String str) {
		if (str.length() > 0 && str.charAt(str.length() - 1) == ',') {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	/**
	 * Load a Game State from a txt. file.
	 */
	public String loadGame() {
		ArrayList<String> content = new ArrayList<String>();

		// Load SavedGame
		// Create Scanner Object
		
		String savedGame = "";

		System.out.println("Please enter the name of the file you wish to load:");
		savedGame = this.keyIn.next();
		// input.close();
		// Load new gameState into arraylist.
		content = Loader.loadGameState(savedGame);
		this.gameboard=new Gameboard();
		this.gameboard.resetAreas();// erase last state of the areas.
		
		// Parse Data and create new gameState.
		
			
		// temporary variables.
		String areaName = null;
		boolean troubleMarker = false;
		//boolean building = false;

		Colors buildingColor = Colors.NONE;
		int demon = 0;
		int troll = 0;

		//Load all areas.
		for (int i = 0; i < 12; i++) {
			// Array that will contain the color of the minions on a certain
			// area.
			ArrayList<Colors> minions = new ArrayList<Colors>();
			String[] parts = content.get(i).split(",");
			for (int j = 0; j < parts.length; j++) {
				if (j == 0)
					areaName = parts[j];
				else if (j == 1)
					troubleMarker = Boolean.valueOf(parts[j]);
				else if (j == 2)
					buildingColor = Colors.valueOf(parts[j]);
				else if (j == 3)
					demon = Integer.parseInt(parts[j]);
				else if (j == 4)
					troll = Integer.parseInt(parts[j]);
				else
					minions.add(Colors.colorForString(parts[j]));

			}
			// Create new city card with the name extracted.
			CityCard cityCard = new CityCard(i);
			// Create Area and add to gameboard.
			Area area = new Area(cityCard, troubleMarker,buildingColor, demon, troll);
			// (cityCard,troubleMarker,building,demon,troll) ==> Constructor
			// parameters.
			this.gameboard.addArea(area);
			this.gameboard.addCityCard(cityCard);
			// Add the minions that where in the current area.
			for (int j = 0; j < minions.size(); j++)
				area.addMinion(new Piece(minions.get(j)),true);
		}

		// savedGame="Test.txt";
		// Get Number of Players(Always at line 12(in array))
		int numberPlayers = Integer.parseInt(content.get(12));
		this.numberOfPlayers = numberPlayers;

		/**
		 * Parse PLayers information. Reset Last state of players.
		 */
		this.players = null;
		// Set new number players.
		this.players = new Player[numberPlayers];

		PersonalityCard perCard = null;
		Colors color = null;
		int minionOnHand = 0;
		int buildingOnHand = 0;
		int money = 0;

		int NumplayerCards = 0;
		int NumcityCards = 0;

		this.decks = new HashMap<String, Deck>();
		this.decks.clear();//erase all previous decks.
		//Initialize all decks for this loaded game.
		this.decks.put("discard", new Deck("D", this.numberOfPlayers));
		this.decks.put("personalities", new Deck("P", this.numberOfPlayers));
		this.decks.put("events", new Deck("E", this.numberOfPlayers));
		this.decks.put("green", new Deck("G", this.numberOfPlayers));
		this.decks.put("brown", new Deck("B", this.numberOfPlayers));

		// Start at position 13 after areas and # of players.
		for (int i = common.beginingOfPlayersLoadGame; i < common.beginingOfPlayersLoadGame + numberPlayers; i++) 
		{
			String[] parts = content.get(i).split(",");
			int playerIndex = i % common.beginingOfPlayersLoadGame;// Index for array players, it starts at 0.

			for (int j = 0; j < parts.length; j++) {
				if (j == 0)
					perCard = new PersonalityCard(Integer.parseInt(parts[j]),this.numberOfPlayers);
				else if (j == 1)
					color = Colors.colorForString(parts[j]);
				else if (j == 2)
					minionOnHand = Integer.parseInt(parts[j]);
				else if (j == 3)
					buildingOnHand = Integer.parseInt(parts[j]);
				else if (j == 4)
					money = Integer.parseInt(parts[j]);
				else if (j == 5) {
					NumplayerCards = Integer.parseInt(parts[j]);

				} else if (j == (5 + NumplayerCards) + 1) {
					NumcityCards = Integer.parseInt(parts[j]);
				} 

			}

			//Remove Personality card from City card deck.
			Deck personalities=this.decks.get("personalities");
			boolean success=personalities.deleteCard(perCard);
			if(!success)
				System.out.println("Personality Card couldn't be removed from the personality deck.(Class Game-Fucntion LoadGame)");

			//System.out.println(personalities.getSizeDeck());
			//personalities.displayCardsinDeck(personalities.getSizeDeck());

			// Create and add new Player
			this.players[playerIndex] = new Player(perCard, color, minionOnHand, buildingOnHand, money);
			// Add player'sCards
			for (int j = 0; j < NumplayerCards; j++) {
				// Brown cards have int values of 1-53 and green cards have
				// int values of 54-101.
				int checkColor = Integer.parseInt(parts[(5 + j) + 1]);

				Card card = new Card(false, false); // City cards are always
				// visible.
				if (checkColor < 54)// This is a Brown card
					card = new BrownCard(checkColor);
				else if (checkColor >= 54)
					card = new GreenCard(checkColor);

				this.players[playerIndex].receiveCard(card);
			}
			Deck deck=null;
			//remove all the cards the player just received from the correct deck.
			for(int j = 0;j<this.players[playerIndex].getPlayerCards().size();j++)
			{
				if(this.players[playerIndex].getPlayerCards().get(j).getClass().toString().contains("GreenCard"))
				{
					deck=this.getEspecificDeck("green");
					success=deck.deleteCard(this.players[playerIndex].getPlayerCards().get(j));//TODO:check if it returns true.
					if(!success)
						System.out.println("Green Card couldn't be removed from the Green deck.(Class Game-Fucntion LoadGame)");
				}else if(this.players[playerIndex].getPlayerCards().get(j).getClass().toString().contains("BrownCard"))
				{
					deck=this.getEspecificDeck("brown");
					deck.deleteCard(this.players[playerIndex].getPlayerCards().get(j));//TODO:check if it returns true.
				}
			}


			// Add CityCards.
			for (int j = 0; j < NumcityCards; j++) {
				// Get CityCard number.
				int cardNumber = Integer.parseInt(parts[(5 + NumplayerCards + (j + 1)) + 1]);
				//Get corresponding cityCard
				CityCard cityCard=this.gameboard.getAreaByCityCard(cardNumber).getCityCard();
				//delete city card from beard and give it to the player.
				this.gameboard.deleteCardFromDeck(cityCard);
				this.players[playerIndex].receiveCityCard(cityCard);
			}

		}// PLayers

		/** Parse BankMoney Get money bank has. **/
		this.bank = Bank.getInstance();
		int bankMoney = Integer.parseInt(content.get(13+this.numberOfPlayers));
		AtomicInteger aInt = new AtomicInteger(bankMoney);
		this.bank.setBankMoney(aInt);// Set new bank balance.

		this.currentPlayer=0;
			
		
		//Create new die.
		this.die = new Die();
		
		// Depending on the number of players initialize their States(to wait).
		playerStatus = new ArrayList<StateContext>();// put on heap.
		this.playerStatus.clear();
		this.playerStatus = new ArrayList<StateContext>();// put on heap.

		for (int i = 0; i < numberOfPlayers; i++) {
			playerStatus.add(new StateContext());
			System.out.println("Player:" + players[i].getColor() + " Current State:" + playerStatus.get(i).getState());
			/**
			 * Set to next state(Play State.)So players are ready to play when
			 * their turn comes up.
			 */
			playerStatus.get(i).performAction(players[i], this);
		}
		
		//Update all the player's hand(minions on play). Put all the minions a player has on the board.
		ArrayList<Area> areas=this.gameboard.getAreas();
		ArrayList<Piece>pieces;
		Player selectPlayer;
		for(int i=0;i<areas.size();i++ )
		{
			pieces = areas.get(i).getMinions();
			for(int j=0;j<pieces.size();j++)
			{
				selectPlayer= this.getPlayerByColor(pieces.get(j).getColor());
				selectPlayer.putNewMinionOnBoard(areas.get(i).getCityCard().getCardNumber(),true);
			}
		}
		
		return "Load Was Successfull";
	}

	/**
	 * Get a Player using a color.
	 * 
	 * @param color
	 *            (Color)
	 * @return Player
	 */
	public Player getPlayerByColor(Colors color) {

		// Select player with passed color.
		for (int i = 0; i < this.players.length; i++) {
			if (players[i].getColor().equals(color)) {
				return players[i];

			}
		}
		return null;

	}

	/**
	 * Prints Information about current game.
	 */
	public void printCurrentState() {
		System.out.println("Current Game Status:" + "\n");
		System.out.println("Number Of Players:" + this.numberOfPlayers);
		System.out.println("Bank balance:" + this.bank.getTotal() + "\n");
		System.out.println("Players:");
		for (int i = 0; i < this.players.length; i++) {
			System.out.println();
			System.out.println("Player" + (i + 1) + ":");
			System.out.println("Money: "+this.players[i].getMoney());
			System.out.println("NetWorth: "+this.players[i].calculateNetWorth(this.gameboard));
			
			System.out.println("Player: "+this.players[i].getColor()+" has buldings in the following areas:");
			for(int j=0;j<this.gameboard.getAreas().size();j++)
			{
				if(this.players[i].getColor().equals(this.gameboard.getAreas().get(j).getBuildingColor()))
					System.out.println("Area: "+this.gameboard.getAreas().get(j).getCityCard().getName()+" ;Building Cost:"+this.gameboard.getAreas().get(j).getCityCard().getBuldingCost());
			}
			
			//System.out.println(this.players[i].toString());
		}/*
		System.out.println("Game Board State:");
		System.out.println(this.gameboard.toString());
		*/
	}

	// Getters
	/**
	 * Getter: array with players.
	 * 
	 * @return Player[]
	 */
	public Player[] getPlayers() {
		return this.players;
	}

	/**
	 * Getter: the decks from the game.
	 * 
	 * @return Map<String, Deck>
	 */
	public Map<String, Deck> getDecks() {
		return this.decks;
	}

	/**
	 * Getter: the requested deck.
	 * 
	 * @param whichDeck
	 * @return Deck
	 */
	public Deck getEspecificDeck(String whichDeck) {
		return this.decks.get(whichDeck);
	}

	/**
	 * Getter: Game Die.
	 * 
	 * @return Die
	 */
	public Die getDie() {
		return this.die;
	}

	/**
	 * Getter: Returns number of players
	 * 
	 * @return int
	 */
	public int getNumberOfPlayers() {
		return this.numberOfPlayers;
	}

	/**
	 * Getter: the size of the brown deck.
	 * 
	 * @return int
	 */
	public int getSizeDrawDeck() {
		/**
		 * Get the size of the brown deck since it's on the bottom and can give
		 * us the status of the draw deck is empty.
		 */
		Deck brownDeck = this.decks.get("Brown");
		return brownDeck.getSizeDeck();
	}

	/**
	 * Getter: the game's current GameBoard.
	 * 
	 * @return Gameboard
	 */
	public Gameboard getGameBoard() {
		return this.gameboard;
	}

	/**
	 * Getter: Get index of current player.
	 * 
	 * @return int
	 */
	public int getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	/**
	 * Calculate who won the Game. Called when the the brown deck is empty or when the event card Riots is activated and there are 8 or more trouble markers on the gameboard.
	 */
	public void CalculateAWinner()
	{
		double canPay=0;
		for(int i=0;i< this.players.length;i++)
		{
			if(players[i].getLoan()>0)
			{//Player has loans
				canPay=Math.floor(players[i].getLoan()/players[i].getMoney());
				System.out.println("Player: "+players[i].getColor()+ " can pay for: "+canPay +" of the loans he took.");
				players[i].payLoan((int)canPay*12);//Pay available loans
				
				if(players[i].getLoan()>0)//If he still has loans but can't pay substract 15 points
				{
					players[i].setMoney(players[i].getMoney()-15);
					players[i].setloan(0);
				}
				
				//Calculate networth
				int netWorth=players[i].calculateNetWorth(this.gameboard);
				
				//Calculate minions on the board.
				int numMinions=12-players[i].getMinionsOnHand();
				
				int points=netWorth+numMinions;
				
				System.out.println("Player: "+players[i].getColor()+" has a total of: "+points+ "points.");
			}
			
			System.out.println("The player with the highest points is the winner. Congratulations.");
		}
	}

}
