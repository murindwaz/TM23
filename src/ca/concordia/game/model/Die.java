package ca.concordia.game.model;

import java.util.Random;
/**
 * Class Die handles the function of the die for this game.
 * @author Pascal,Gustavo,bhavik,Esteban,Diego
 */

public class Die {
	
	private Random randomGenerator;
	/**
	 * Creates a random number generator.
	 * Constructor
	 */
	public Die() {
		randomGenerator = new Random();
	}
	
	/**
	 * Generates a random number for a roll.
	 * @return
	 */
	public int roll() {
		return randomGenerator.nextInt(11)+1;
	}
}
