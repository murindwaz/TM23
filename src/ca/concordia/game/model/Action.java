package ca.concordia.game.model;

public class Action {
	public String description;
	
	public Action(String des) {
		this.description = des;
	}
	
	@Override public String toString() {
		return this.description;
	}
}
