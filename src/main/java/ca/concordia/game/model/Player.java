package ca.concordia.game.model;

import java.awt.Color;
import java.util.ArrayList;

public class Player {
	private int money;
	private PersonalityCard personality;
	public Color color;
	
	private ArrayList<Card> playerCards;
	private ArrayList<CityCard> playerCityCards;
	private int minionsOnHand;
	private int buildingOnHand;
	
	public Player(PersonalityCard aPersonality, Color aColor,int minionOnHand, int buildingOnHand){
		this.money = 0;
		this.personality = aPersonality;
		this.color = aColor;
		this.minionsOnHand=minionOnHand;
		this.buildingOnHand=buildingOnHand;
		
		this.playerCards = new ArrayList<Card>();
	}
	
	public int getMoney() {
		return this.money;
	}	
	
	public Card getPersonality() {
		return this.personality;
	}	
	
	public void transferMoney(int amount) {
		this.money += amount;
	}
	//Add card to player playing cards.
	public void receiveCard(Card aCard) {
		this.playerCards.add(aCard);
	}
	//Add card to player's city Cards(Player put building in board.)
	public void receiveCityCard(CityCard card){
		this.playerCityCards.add(card);
	}
}
