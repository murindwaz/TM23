package ca.concordia.game.model;

public class Player {
	private int money;
	
	public Player(){
		this.money = 0;
	}
	
	public void transferMoney(int amount) {
		this.money += amount;
	}	
}
