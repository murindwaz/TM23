package ca.concordia.game.main;

import java.util.Scanner;

/**
 * Run Test Cases. Controls The game activities.
 * @author Pascal,Gustavo,bhavik,Esteban,Diego
 *
 */

public class Controller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Create 
		Game game;
		game= Game.getInstance();
		
		game.init();
		game.loadGame();
		game.printCurrentState();
		
		game.saveGame();
		
		
	}

}
