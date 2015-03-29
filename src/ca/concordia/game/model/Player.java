package ca.concordia.game.model;

import java.util.ArrayList;

import ca.concordia.game.main.Game;
import ca.concordia.game.common.common.Colors;

/**
 * Class PLayer handles and contains the different players on the current game.
*@author Pascal Maniraho 
 *@author Gustavo Pereira
 *@author Bhavik Desai 
 *@author Jesus Esteban Garro Matamoros 
 *@author Diego Pizarro
 */
public class Player {
	private int money;
	private PersonalityCard personality;
	public Colors color;
	private int netWorth;
	private int loans;
	
	private ArrayList<Card> playerCards;
	private ArrayList<CityCard> playerCityCard;
	private int minionsOnHand;
	private int buildingOnHand;
	private boolean winningCondition;
	private int controledAreas;
	private int [] minionsOnAreas;
	/**
	 * Constructor for initializing a game.
	 * @param aPersonality
	 * @param aColor
	 * @param minionOnHand
	 * @param buildingOnHand
	 */
	public Player(PersonalityCard aPersonality, Colors aColor,int minionOnHand, int buildingOnHand){
		this.controledAreas=0;
		this.money = 0;	
		this.loans=0;
		this.personality = aPersonality;
		this.color = aColor;
		this.minionsOnHand=minionOnHand;
		this.buildingOnHand=buildingOnHand;
		
		this.minionsOnAreas= new int[12]; //12  for the twelve areas on the board.
		//set one minion on the three initial areas as required by the game(Areas:Dolly Sisters(1),The Scours(5),The Shades(7))
		this.minionsOnAreas[0]=this.minionsOnAreas[0]+1;
		this.minionsOnAreas[4]=this.minionsOnAreas[4]+1;
		this.minionsOnAreas[6]=this.minionsOnAreas[6]+1;
		//Update number of minions on hand
		this.setMinionsOnHand(this.minionsOnHand-3);
		
		//Todo update GameBoard when adding minnions.
		
		this.playerCards = new ArrayList<Card>();
		this.playerCityCard = new ArrayList<CityCard>();
	}
	
	

	/**
	 * Constructor for loading a game State.
	 * @param aPersonality
	 * @param aColor
	 * @param minionOnHand
	 * @param buildingOnHand
	 * @param money
	 */
	public Player(PersonalityCard aPersonality, Colors aColor,int minionOnHand, int buildingOnHand,int money){
		//this.loans=loans;  to do
		this.money = money;
		this.personality = aPersonality;
		this.color = aColor;
		this.minionsOnHand=minionOnHand;
		this.buildingOnHand=buildingOnHand;
		
		this.playerCards = new ArrayList<Card>();
		this.playerCityCard = new ArrayList<CityCard>();
	}
	
	/**
	 * Calculates and returns the player's networth. By adding the money he/she has, the money invested in buildings and substracting any loans the
	 * player may have.
	 * @return int
	 */
	public int calculateNetWorth()
	{
		this.netWorth= this.money;
		//calculate the money invested in buildings so far, and add it to the networth.
		for(int i=0;i<this.playerCityCard.size();i++)
		{
			this.netWorth=this.netWorth+this.playerCityCard.get(i).getBuldingCost();
		}
		//If the player has any loans substract that amount to networth
		this.netWorth=this.netWorth-loans;
		
		return netWorth;
	}
	
	/**
	 * Add a loan if the player takes one.
	 * @param amountLoan(int)
	 */
	public void takeLoan(int amountLoan)
	{
		this.loans=this.loans-amountLoan;
	}
	
	/**
	 * If player paid a loan update the loan variable.
	 * @param amountPaid(int)
	 */
	public void payLoan(int amountPaid)
	{
		this.loans=this.loans+amountPaid;
	}

	/**
	 * Add Money into players account.
	 * @param amount
	 */
	public void addMoney(int amount) {
		this.money += amount;
	}
	
	/**
	 * Transfer Money to another player's account.
	 * @param amount
	 */
	public void transferMoneyto(int amount, Player aPlayer) {
		this.money -= amount;
		aPlayer.addMoney(amount);
		
	}

	/**
	 * Transfer Card to another Player
	 * @param cardNb
	 * @param aPlayer
	 */
	public void transferCard(int cardNb, Player aPlayer) {
		aPlayer.receiveCard(this.playerCards.get(cardNb));
		this.playerCards.remove(cardNb);
	}
	
	/**
	 * Subtracts an amount of money from the player. Returns true if successfull otherwise return false;
	 * @param amount
	 * @return boolean
	 */
	public boolean payMoney(int amount)
	{
		if(money>amount)
		{
			this.money=this.money-amount;
			return true;
		}else
			return false;
	}
	

	/**
	 * Add card to player playing cards.
	 * @param aCard
	 */
	public void receiveCard(Card aCard) {
		this.playerCards.add(aCard);
	}

	/**
	 * Add card to player's city Cards(Player put building in board.)
	 * @param card
	 */
	public boolean receiveCityCard(CityCard card){
		int oldSize=this.playerCityCard.size();
		this.playerCityCard.add(card);
		int newSize=this.playerCityCard.size();
		
		if(newSize == oldSize+1)
			return true;
		else
			return false;
	}
	
	/**
	 * Remove card from player's city Cards(Player removed or moved a building to another area.)
	 * @param card
	 * @return
	 */
	public boolean returnCityCard(CityCard card)
	{
		int oldSize=this.playerCityCard.size();
		this.playerCityCard.remove(card);
		int newSize=this.playerCityCard.size();
		
		if(oldSize == newSize-1)
			return true;
		else
			return false;
	}
	
	/**
	 * Lose all Player Cards
	 */
	public void loseAllCards( )
	{
		this.playerCards.clear();
	}

	/**
	 * Receive all Player Cards
	 * @param allCards
	 */
	public void receiveAllCards( ArrayList<Card> allCards )
	{
		this.playerCards = allCards;
	}

	/**
	 * Add a minion that was on hand to an area. Reduces by one the number of minion on hand and adds one minion to the respective area.
	 * @param areaCode
	 * @return boolean
	 */
	public boolean putNewMinionOnBoard(int areaCode)
	{
		if(this.minionsOnHand>=1)
		{
			this.minionsOnHand--;
			this.minionsOnAreas[areaCode-1]=this.minionsOnAreas[areaCode-1]+1;
			return true;
		}else
			return false;
	}
	
	/**
	 * Remove a minion from an area and add it to player's hand.
	 * @param areaCode
	 * @return boolean
	 */
	public boolean removeMinionOnBoard(int areaCode)
	{
		if(this.minionsOnAreas[areaCode]>0)
		{
			this.minionsOnHand++;
			this.minionsOnAreas[areaCode-1]=this.minionsOnAreas[areaCode-1]-1;
			return true;
		}else
			return false;
	}
	
	/**
	 * Move a minion from one area to another.
	 * @param oldAreaCode
	 * @param newAreaCode
	 * @return boolean
	 */
	public boolean moveMinionToNewArea(int oldAreaCode, int newAreaCode)
	{
		if(this.minionsOnAreas[oldAreaCode-1] >0)
		{
			this.minionsOnAreas[oldAreaCode-1]=this.minionsOnAreas[oldAreaCode-1]-1;
			this.minionsOnAreas[newAreaCode-1]=this.minionsOnAreas[newAreaCode-1]+1;
			return true;
		}else
			return false;
	}
	
	/**
	 * Checks if player has reached a winning condition. Called at the beginning of each player's turn
	 * with the exception if the player possesses Commander Vimes; in which case it has to be checked every time a 
	 * player draws a card from the drawpile.
	 * @param gameBoard
	 * @return boolean
	 */
	public boolean checkWinningCondition(Gameboard gameBoard)
	{
		//Get card ID to check which condition need to be fulfilled.
		int cardId=this.personality.getCardId();
		
		//Check if winning condition has been reached depending on the personality card.
		if(cardId == 1)//Lord Vetinari
		{
			int numMinWinCond=gameBoard.numberMinionsAreas(this);
			if(numMinWinCond>=this.personality.getNumMinionsOnAreas())//If player has minions in a number of areas which contain no demons.
				return true;
			else return
					false;
		}
		else if(cardId== 2 || cardId == 4 || cardId == 6)//Lord Selachii, Lord Rust, Lord Worde.
		{
			//Check if player has the amount of controlled areas to win the game.
			ArrayList<String> controlledAreas= new ArrayList<String>();
			controlledAreas=gameBoard.controlledAreas(this); //Get a list of the areas controlled by the player.
			if(controlledAreas.size()>= this.personality.getControlledAreas())
				return true;
			else
				return false;
		}
		else if(cardId==3)// Dragon King of Arms.
		{
			//Check if the board has the required number of touble markes for player to win.
			ArrayList<String> troubleMarkersArea= new ArrayList<String>();
			troubleMarkersArea= gameBoard.troubleMarkers();//Get a list of the areas which contain a trouble marker.
			if(troubleMarkersArea.size() >= this.personality.getNumTroubleMarkers())
				return true;
			else
				return false;
		}
		else if(cardId==5)//Commander Vimes
		{
			//Check if the draw deck is empty by chequin the size of the brown deck since it's the one at the bottom.
			Game game= Game.getInstance();//Get the current game to get the status of the draw pile.
			if(game.getSizeDrawDeck()==0)//Draw deck is empty
				return true;
			else
				return false; //else
		}
		else if (cardId==7)//Chrysoprase
		{
			int playerNetWorth=this.calculateNetWorth();
			if(playerNetWorth>=50)
				return true;
			else
				return false;
		}
		else{
			System.out.println("The card being chequed doesn't contain a valid ID(function 'checkWinningCondition').");
			return false;
		}
				
	}

	/**
	 * ToString Method for class Player.
	 */
	@Override
	public String toString()
	{
		String info="Money: "+this.money+ " Playing as: "+ this.personality.getName()+ " Using Color:"+this.color +"." +"\n";
		String info2= "Currently Holding "+ this.minionsOnHand+" minions and "+this.buildingOnHand+ " Buildings on hand."+"\n";
		String info3="Playing Cards:"+"\n";
		String info4="City Playing Cards:"+"\n";
		//player Cards
		for(int i=0 ; i<playerCards.size();i++)
		{
			Card card = playerCards.get(i);
			BrownCard bCard = new BrownCard(0);
			GreenCard gCard= new GreenCard(0);
			if(card.getClass().equals(bCard.getClass())){//The card is of type BrownCard, convert to brown card.
				bCard = (BrownCard) card;
				info3=info3+ "Brown-"+bCard.getNumber()+" ";
			}else//The card is of type GreenCard, convert to green card.
			{
				gCard=(GreenCard) card;
				info3=info3+ "Green-"+gCard.getNumber()+" "+"Position in Player Hand:"+"("+i+")"+"\n";
			}
				
		}
		info3=info3+"\n";
		
		//player City Cards.
		for(int i=0 ; i<playerCityCard.size();i++)
		{
			CityCard card = playerCityCard.get(i);
			info4=info4+ " "+card.getName()+" ";
		}
		info4=info4+"\n";
		
		return info+info2+info3+info4;
		
	}
	
	
	/**
	 * Getter money
	 * @return
	 */
	public int getMoney() {
		return this.money;
	}	
	/**
	 * Getter color
	 * @return
	 */
	public Colors getColor()
	{
		return this.color;
	}
	/**
	 * Getter minons currently on Players hand.
	 * @return
	 */
	public int getMinionsOnHand()
	{
		return this.minionsOnHand;
	}
	/**
	 * Getter building currently on players hand
	 * @return
	 */
	public int getBuildingOnHand()
	{
		return this.buildingOnHand;
	}
	/**
	 * Getter player Cards(Brown or Green Cards)
	 * @return
	 */
	public ArrayList<Card> getPlayerCards()
	{
		return this.playerCards;
	}
	/**
	 * Getter City Cards Currently hold by a player.
	 * @return
	 */
	public ArrayList<CityCard> getPlayerCityCard()
	{
		return this.playerCityCard;
	}
	
	/**
	 * Getter get Player's Personality Card.
	 * @return
	 */
	public Card getPersonality() {
		return this.personality;
	}	
	
	/**
	 * Getter: array of minions per area.
	 * @return int[]
	 */
	public int[] getMinionsOnArea()
	{
		return this.minionsOnAreas;
	}
	
	/**
	 * Setter for buildingsOnHand.
	 * @param newNum(int)
	 */
	public void setBuildingOnHand(int newNum)
	{
		this.buildingOnHand=newNum;
	}
	
	/**
	 * Setter for minionOnHand.
	 * @param newNum(int)
	 */
	public void setMinionsOnHand(int newNum)
	{
		this.minionsOnHand=newNum;
	}
	
}
