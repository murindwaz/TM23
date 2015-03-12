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

import ca.concordia.game.common.common.Colors;

/**
 * Game class creates a new game, loads and saves a game state. Further it specifies and controls the logic required to play a game.
*@author Pascal Maniraho 
 *@author Gustavo Pereira
 *@author Bhavik Desai 
 *@author Jesus Esteban Garro Matamoros 
 *@author Diego Pizarro

 */
public class Game {
	
	private static Game instance = null;
	
	private Player[] players; 
	private ArrayList<StateContext> playerStatus; //This will contain the state each player is at the moment.And the actions he can perform.
	private Gameboard gameboard;
	private Map<String,Deck> decks;
	private Bank bank;
	private Die die;
	private ArrayList<Symbol> symbols;
	public Scanner keyIn;
	
	public int currentPlayer;
	public int numberOfPlayers;
	
	
	/**
	 * 
	 * Start New Game
	 */
	public String init() {
				
		this.bank = Bank.getInstance();
		
		
		currentPlayer=-1;//current player has not been determined yet.
		//Set bank to full since it's a new game.
		int newBalance = 120;
		AtomicInteger aNewBalance= new AtomicInteger(newBalance);
		bank.setBankMoney(aNewBalance);
		
		
		//Select number of players
		keyIn=new Scanner(System.in);
		System.out.println("Please select number of players(Maximun => 4):");
		numberOfPlayers = keyIn.nextInt();
		
		this.symbols= new ArrayList<Symbol>();
		//Initialize all Symbol Actions.
		for(int i=1;i<=9;i++)
		{
			
		}
		
		//Close Scanner object
		//keyIn.close();//Don't close until done using in whole proyect.
		
		this.decks = new HashMap<String,Deck>();
		//There is exactly 5 decks per game + a Discard Deck
		for(int i = 0 ; i <= 5 ; i++) {
			switch(i) {
				case 0:
					this.decks.put("discard", new Deck("D",numberOfPlayers));
					break;
					
				case 1:
					this.decks.put("personalities", new Deck("P",numberOfPlayers));
					break;
					/*
				case 2:
					this.decks.put("cities", new Deck("C",numberOfPlayers));
					break;
					*/
				case 3:
					this.decks.put("events", new Deck("E",numberOfPlayers));
					break;
				case 4:
					this.decks.put("green", new Deck("G",numberOfPlayers));
					break;
				case 5:
					this.decks.put("brown", new Deck("B",numberOfPlayers));
					break;
			}
		}
		
		//Select number of players and their colors.
		//For now we use four players of fixed colors:
		this.players = new Player[numberOfPlayers];
		
		for(int i=0; i<numberOfPlayers; i++) {
			this.players[i] = new Player((PersonalityCard)decks.get("personalities").getCard(),Colors.colorForIndex(i),12,6);
			//Deal 5 green cards to each player:
			decks.get("green").dealCardsToPlayer(players[i],5);
			//Give $10 to each player:
			bank.transferFunds(players[i], 10);
		}
		
		//Depending on the number of players initialize their States(to wait).
		playerStatus=new ArrayList<StateContext> ();//put on heap.
				
		for(int i=0;i<numberOfPlayers;i++)
		{
			playerStatus.add(new StateContext());
			System.out.println("Player:"+players[i].getColor()+ " Current State:"+playerStatus.get(i).getState());
			playerStatus.get(i).performAction(players[i], this);//Set to next state(Play State.)So players are ready to play when their turn comes up.
			
		}
		
		//Initialize Gameboard:
		this.gameboard = new Gameboard(this.players);
		this.die=new Die();
			
		return "Initialization was succssessfull";
	}
	
	//Start playing game instance.
	public void play()
	{
		Map<Integer,Colors> playerDieRollMap=new HashMap<Integer,Colors>();//Will store the value rolled depending on the player's color.
		ArrayList<Integer> playerDieRoll=new ArrayList<Integer>();
		int rollValue=-1;
		//roll dice for each player to pick first player.
		for(int i=0;i<this.numberOfPlayers;i++)
		{
			rollValue=die.roll();//roll dice for player.
			System.out.println("Player with color: "+this.players[i].getColor()+" rolled:"+rollValue);
			playerDieRoll.add(rollValue);//store value gotten by player.
			playerDieRollMap.put(rollValue, this.players[i].getColor());//Store  Player color,roll value pair.
		}
		
		System.out.println();
		
		int highestRoll=highestValue(playerDieRoll);
		Colors startingColor=playerDieRollMap.get(highestRoll);
		
		System.out.println("The player with the color:"+startingColor+" starts the game.");
		//Set the pointer to the starting player in the array.
		for(int i=0;i<this.numberOfPlayers;i++)
		{
			if(this.players[i].getColor().equals(startingColor))//Found a match
			{
				currentPlayer=i;
				break;
			}
		}
		
		while(true)//Keep going until a player wins the game.
		{
			System.out.println("Game has begun!!!!!!");
			//Start Playing select the player who's turn it is.
			this.playerStatus.get(currentPlayer).performAction(players[currentPlayer], this);
			System.out.println("Player:"+players[currentPlayer].getColor()+ " Current State:"+playerStatus.get(currentPlayer).getState());
			//Draw Cards State
			this.playerStatus.get(currentPlayer).performAction(players[currentPlayer], this);
			
			System.out.println("Player:"+players[currentPlayer].getColor()+ " Current State:"+playerStatus.get(currentPlayer).getState());
			
			this.playerStatus.get(currentPlayer).performAction(players[currentPlayer], this);
			System.out.println("Player:"+players[currentPlayer].getColor()+ " Current State:"+playerStatus.get(currentPlayer).getState());
			
			currentPlayer=nextPlayer();
			
			System.out.println("Do you wish to exit?? Enter -1, otherwise enter 1.");
			int exit =this.keyIn.nextInt();
			if(exit < 1)
				break;
			//break;
		}
		
		this.keyIn.close();
		
	}
	
	/**
	 * Return the highest value in an Arraylist of integers.
	 * @param arrayList(ArrayList<Integer>)
	 * @return int
	 */
	public int highestValue(ArrayList<Integer> arrayList)
	{
		Collections.sort(arrayList); // Sort the arraylist
		int highest=arrayList.get(arrayList.size() - 1); //gets the last item, largest for an ascending sort
		
		return highest;
	}
	/**
	 * Implements Game as a singleton, as there will always be a single game per run.
	 * @return Game
	 */
	public static Game getInstance() {
		if(instance == null) {
			instance = new Game();
		}
		return instance;
	}
	
	/**
	 * Constructor, initializes a new game.
	 */
	public Game() {
		//init();
	}
	
	/**
	 * 
	 * @param gameState
	 * @return
	 */
	public boolean equals(Game gameState){
		//@todo check the number of players
		//@todo check occupied areas
		//@todo check the bank
		return false;
	}
	
	/**
	 * Returns the player who's turn is up.
	 * @return
	 */
	public int nextPlayer() {
		currentPlayer = (currentPlayer + 1)%numberOfPlayers;
		return currentPlayer;
	}
	
	/**
	 * Save game State.In the correct format.
	 */
	public String saveGame() 
	{
		String temp="";
		ArrayList<String> content= new ArrayList<String>();
		
		//Store GameBoard's  Info.
		for(int i=0;i<12;i++)
		{
			temp=temp+this.gameboard.getAreas().get(i).getCityCard().getName()+",";
			temp=temp+this.gameboard.getAreas().get(i).getTroubleMarker()+",";
			temp=temp+this.gameboard.getAreas().get(i).getBuilding()+",";
			temp=temp+this.gameboard.getAreas().get(i).getDemon()+",";
			temp=temp+this.gameboard.getAreas().get(i).getTroll()+",";
			
			//Get Minions of each class
			for(int j=0;j<this.gameboard.getAreas().get(i).getMinions().size();j++)
			{
				temp=temp+this.gameboard.getAreas().get(i).getMinions().get(j).getColor()+",";
			}
			//remove last coma
			temp=removeLastChar(temp);
			//Store new Area info into the content array
			content.add(temp);
			temp="";
		}
		
		
		//Store Player's Info.
		temp=temp+this.players.length;//Get totalNumber of players.
		content.add(temp);
		temp="";
		
		for(int i=0;i<this.players.length;i++)
		{
			temp=temp+ this.players[i].getPersonality().getName()+",";
			temp=temp+ this.players[i].getColor()+",";
			temp=temp+this.players[i].getMinionsOnHand()+",";
			temp=temp+this.players[i].getBuildingOnHand()+",";
			temp=temp+this.players[i].getMoney()+",";
			
			temp=temp+this.players[i].getPlayerCards().size()+",";
			for(int j=0; j<this.players[i].getPlayerCards().size();j++)
			{
				BrownCard bCard = new BrownCard(0);
				GreenCard gCard= new GreenCard(0);
				if(this.players[i].getPlayerCards().get(j).getClass().equals(bCard.getClass())){//The card is of type BrownCard, convert to brown card.
					bCard = (BrownCard) this.players[i].getPlayerCards().get(j);
					temp=temp+bCard.getNumber()+",";
				}else//The card is of type GreenCard, convert to green card.
				{
					gCard=(GreenCard) this.players[i].getPlayerCards().get(j);;
					temp=temp+gCard.getNumber()+",";
				}
				
			}
			temp=temp+this.players[i].getPlayerCityCard().size()+",";
			for(int j=0; j<this.players[i].getPlayerCityCard().size();j++)
			{
				temp=temp+this.players[i].getPlayerCityCard().get(j).getCardNumber()+",";
			}
			//remove last character(the coma)
			temp=removeLastChar(temp);
			//Add new player line to ArrayList.
			content.add(temp);
			temp="";
		}
		temp="";
		//Add the banks current bank balance to arraylist content.
		temp=temp+this.bank.getTotal();
		content.add(temp);
		
		//Write current game's state to 
		Saver.saveGameState(content);
		
		return "Save Was Successfull";
	}
	

	/**
	 * Remove last character of a string if it's a coma.
	 * @param str
	 * @return
	 */
	public String removeLastChar(String str) {
	    if (str.length() > 0 && str.charAt(str.length()-1)==',') {
	      str = str.substring(0, str.length()-1);
	    }
	    return str;
	}
	
	/**
	 * Load a Game State from a txt. file.
	 */
	public String loadGame()
	{
		ArrayList<String> content = new ArrayList<String>();
		
		//First set the game instance to null since there could already be another game running.
		Game.instance=null;
		
		//Load SavedGame
		//Create Scanner Object
		Scanner input=new Scanner(System.in);
		String savedGame="";
		
		System.out.println("Please enter the name of the file you wish to load:");
		savedGame = input.next();
		//input.close();
		//Load new gameState into arraylist.
		content=Loader.loadGameState(savedGame);
		
		//Parse Data and create new gameState.
		while(true)
		{
			//Parse Areas from gameboard (Total of 12 Areas).
			this.gameboard.resetAreas();//erase last state of the areas.
			
			//temporary variables.
			String areaName = null;
			boolean troubleMarker = false;
			boolean building = false;

			Colors buildingColor=Colors.NONE;
			int demon = 0;
			int troll = 0;
			
			
			for(int i=0;i<12;i++)
			{
				ArrayList<Colors> minions = new ArrayList<Colors>();//Array that will contain the color of the minions on a certain area.
				String[] parts = content.get(i).split(",");
				for(int j=0;j<parts.length;j++)
				{
					if(j==0)
						areaName=parts[j];
					else if(j==1)
						troubleMarker=Boolean.valueOf(parts[j]);
					else if(j==2)
						building=Boolean.valueOf(parts[j]);
					else if(j==3)
						buildingColor= Colors.colorForString(parts[j]);
					else if(j==4)
						demon=Integer.parseInt(parts[j]);
					else if(j==5)
						troll=Integer.parseInt(parts[j]);
					else 
						minions.add(Colors.colorForString(parts[j]));
							
				}
				//Create new city card with the name extracted.
				CityCard cityCard=new CityCard(areaName);
				//Create Area and add to gameboard.
				Area area=new Area(cityCard,troubleMarker,building,buildingColor,demon,troll);
				this.gameboard.addArea(area);//(cityCard,troubleMarker,building,demon,troll) ==> Constructor parameters.
				//Add the minions that where in the current area.
				for(int j=0 ; j<minions.size();j++)
					area.addMinion(new Piece(minions.get(j)));
			}
				
			//savedGame="Test.txt";
			//Get Number of Players(Always at line 12(in array))
			int numberPlayers=Integer.parseInt(content.get(12));
			this.numberOfPlayers=numberPlayers;
			
			//Parse PLayers information.
			this.players=null; //Reset Last state of players.
			this.players = new Player[numberPlayers];//Set new number players.
			
			PersonalityCard perCard = null;
			Colors color = null;
			int minionOnHand = 0;
			int buildingOnHand = 0;
			int money = 0;
			
			int NumplayerCards = 0;
			int NumcityCards = 0;
			//Start at position 13 after areas and # of players.
			for(int i=13 ; i< 13 +numberPlayers;i++)
			{	
				String[] parts = content.get(i).split(",");
				int playerIndex= i%13;//Index for array players, it starts at 0.
				for(int j=0;j<parts.length;j++)
				{
					if(j==0)
						perCard=new PersonalityCard(parts[j]);
					else if(j==1)
						color=Colors.colorForString(parts[j]);
					else if(j==2)
						minionOnHand=Integer.parseInt(parts[j]);
					else if(j==3)
						buildingOnHand=Integer.parseInt(parts[j]);
					else if(j==4)
						money=Integer.parseInt(parts[j]);
					else if(j==5)
					{
						 NumplayerCards=Integer.parseInt(parts[j]);
						
					}else if(j==(5+NumplayerCards)+1)
					{
						 NumcityCards=Integer.parseInt(parts[j]);
					}
					 
				}
				//Create and add new Player
				this.players[playerIndex]= new Player(perCard,color,minionOnHand,buildingOnHand,money);
				//Add player'sCards
				for(int j=0 ; j<NumplayerCards;j++)
				{
					//Brown cards have int values of 1-48 and green cards have int values of 49-101.
					int checkColor= Integer.parseInt(parts[(5+j)+1]);
					
					Card card= new Card(false,false); //City cards are always visible.
					if(checkColor <49)//This is a Brown card
						 card=new BrownCard(checkColor); 
					else if (checkColor >48)
						card = new GreenCard(checkColor);
					
					this.players[playerIndex].receiveCard(card);
				}
				
				//Add CityCards.
				for(int j=0 ; j<NumcityCards;j++)
				{
					//Get CityCard number.
					int cardNumber= Integer.parseInt(parts[(5+NumplayerCards+(j+1))+1]);
					
					CityCard cC=new CityCard(cardNumber);
					
					this.players[playerIndex].receiveCityCard(cC);
				}
				
				
			}//PLayers
			
			//Parse BankMoney
			for(int i=14+numberPlayers ; i< (13 +numberPlayers)+1;i++)//Get money bank has.
			{
				int bankMoney=Integer.parseInt(content.get(i));
				AtomicInteger aInt= new AtomicInteger(bankMoney);
				this.bank.setBankMoney(aInt);//Set new bank balance.
			}
			
			break; //Exit while loop.
		}//While
		
		return "Load Was Successfull";
	}
	
	/**
	 * Getter: Returns number of players
	 * @return int
	 */
	public int getNumberOfPlayers()
	{
		return this.numberOfPlayers;
	}
	
	/**
	 * returns the size of the brown deck.
	 * @return int
	 */
	public int getSizeDrawDeck()
	{
		Deck brownDeck=this.decks.get("Brown");//Get the size of the brown deck since it's on the bottom and can give us the status of the draw deck is empty.
		return brownDeck.getSizeDeck();
	}
	
	/**
	 * Returns the game's current GameBoard.
	 * @return Gameboard
	 */
	public Gameboard getGameBoard()
	{
		return this.gameboard;
	}
	

	
	/**
	 * Get a Player using a color.
	 * @param color(String)
	 * @return Player
	 */
	public Player getPlayerByColor(Colors color)
	{
		
		//Select player with passed color.
		for(int i=0;i<this.players.length;i++)
		{
			if(players[i].getColor().equals(color))
			{
				return players[i];
				
			}
		}
		return null;
		
	}
	/**
	 * Prints Information about current game.
	 */
	public void printCurrentState()
	{	
		System.out.println("Current Game Status:"+"\n");
		System.out.println("Number Of Players:"+this.numberOfPlayers);
		System.out.println("Bank balance:"+this.bank.getTotal()+"\n");
		
		System.out.println("Players:");
		for(int i=0;i<this.players.length;i++)
		{
			System.out.println("Player"+(i+1)+":");
			System.out.println(this.players[i].toString());
		}
		
		System.out.println("Game Board State:");
		System.out.println(this.gameboard.toString());
		
	}

}
