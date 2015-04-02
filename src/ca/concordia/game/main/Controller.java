package ca.concordia.game.main;

import ca.concordia.game.model.*;
/**
 * Run Test Cases. Controls The game activities.
 * @author Pascal Maniraho 
 * @author Gustavo Pereira
 * @author Bhavik Desai 
 * @author Jesus Esteban Garro Matamoros 
 * @author Diego Pizarro
 */
public class Controller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Create 
		boolean gameLoaded=false;

		Game game;
		game= Game.getInstance();
		
		//game.init();
		//game.play(gameLoaded);
		
		
		gameLoaded=true;
		game.loadGame();
		game.play(gameLoaded);
				 
		//game.loadGame();
		//game.printCurrentState();
		
		//game.saveGame();
	}

}