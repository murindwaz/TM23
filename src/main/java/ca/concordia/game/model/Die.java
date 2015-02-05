package ca.concordia.game.model;

import java.util.Random;

public class Die {
	
	private Random randomGenerator;
	
	public Die() {
		randomGenerator = new Random();
	}
	
	public int roll() {
		return randomGenerator.nextInt(11)+1;
	}
}
