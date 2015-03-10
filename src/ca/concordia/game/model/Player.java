package ca.concordia.game.model;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Class PLayer handles and contains the different players on the current game.
 * @author Pascal,Gustavo,bhavik,Esteban,Diego
 *
 */
public class Player {
	private int money;
	private PersonalityCard personality;
	public String color;
	
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
	public Player(PersonalityCard aPersonality, String aColor,int minionOnHand, int buildingOnHand){
		this.controledAreas=0;
		this.money = 0;		
		this.personality = aPersonality;
		this.color = aColor;
		this.minionsOnHand=minionOnHand;
		this.buildingOnHand=buildingOnHand;
		
		this.minionsOnAreas= new int[12]; //12  for the twelve areas on the board.
		//set one minion on the three initial areas as required by the game(Areas:Dolly Sisters(1),The Scours(5),The Shades(7))
		this.minionsOnAreas[0]=this.minionsOnAreas[0]+1;
		this.minionsOnAreas[4]=this.minionsOnAreas[4]+1;
		this.minionsOnAreas[6]=this.minionsOnAreas[6]+1;
		
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
	public Player(PersonalityCard aPersonality, String aColor,int minionOnHand, int buildingOnHand,int money){
		this.money = money;
		this.personality = aPersonality;
		this.color = aColor;
		this.minionsOnHand=minionOnHand;
		this.buildingOnHand=buildingOnHand;
		
		this.playerCards = new ArrayList<Card>();
		this.playerCityCard = new ArrayList<CityCard>();
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
	public String getColor()
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
	 * Transfer Money into players account.
	 * @param amount
	 */
	public void transferMoney(int amount) {
		this.money += amount;
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
	public void receiveCityCard(CityCard card){
		this.playerCityCard.add(card);
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
	 * ToString Method for class Player.
	 */
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
				info3=info3+ "Green-"+gCard.getNumber()+" ";
			}
				
		}
		info3=info3+"\n";
		
		//player City Cards.
		for(int i=0 ; i<playerCityCard.size();i++)
		{
			CityCard card = playerCityCard.get(i);
			info4=info4+ " "+card.getName()+" ";
		}
		info4=info4+"\n\n";
		
		return info+info2+info3+info4;
		
	}
}
