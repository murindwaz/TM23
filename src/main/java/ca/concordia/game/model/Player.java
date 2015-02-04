package ca.concordia.game.model;

import java.awt.Color;
import java.util.ArrayList;

public class Player {
	private int money;
	private PersonalityCard personality;
	public Color color;
	
	private ArrayList<Card> playerCards;
	
	public Player(PersonalityCard aPersonality, Color aColor){
		this.money = 0;
		this.personality = aPersonality;
		this.color = aColor;
		
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
	
	public void receiveCard(Card aCard) {
		this.playerCards.add(aCard);
	}
}
