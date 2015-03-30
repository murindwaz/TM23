package ca.concordia.game.model;

import java.util.ArrayList;

import ca.concordia.game.main.Game;
import ca.concordia.game.util.Configuration;

/**
 * Event Card is a type of card it contains the Twelve different types of possible events the game offers.
 *	@author Pascal Maniraho 
 *	@author Gustavo Pereira
 *	@author Bhavik Desai 
 *	@author Jesus Esteban Garro Matamoros 
 *	@author Diego Pizarro
 */
public class EventCard extends Card {
	private int eventCardId;
	private String eventCardName;
	private String instruction;
	
	
	/**
	 * Constructor: Depending on the integer sent as argument initialize the card's information.
	 * @param i(integer)
	 */
	public EventCard(int i) {
		//City Cards are always Visible!
		super(false,false);
		switch(i) {
			case Configuration.EVENT_FOG:
				this.eventCardId=Configuration.EVENT_FOG;
				this.setName( Configuration.EVENT_FOG_NAME );
				this.instruction = Configuration.EVENT_FOG_DESCRIPTION;
				break;
			case Configuration.EVENT_MYSTERIOUS_MURDERS:
				this.eventCardId=Configuration.EVENT_MYSTERIOUS_MURDERS;
				this.setName( Configuration.EVENT_MYSTERIOUS_MURDERS_NAME );
				this.instruction = Configuration.EVENT_MYSTERIOUS_MURDERS_DESCRIPTION;
				break;
			case Configuration.EVENT_RIOTS:
				this.eventCardId=Configuration.EVENT_RIOTS;
				this.setName( Configuration.EVENT_RIOTS_NAME );
				this.instruction = Configuration.EVENT_RIOTS_DESCRIPTION;
				break;
			case Configuration.EVENT_SUBSIDENCE:
				this.eventCardId=Configuration.EVENT_SUBSIDENCE;
				this.setName( Configuration.EVENT_SUBSIDENCE_NAME );
				this.instruction = Configuration.EVENT_SUBSIDENCE_DESCRIPTION;
				break;
			case Configuration.EVENT_TROLLS:
				this.eventCardId=Configuration.EVENT_TROLLS;
				this.setName( Configuration.EVENT_TROLLS_NAME );
				this.instruction = Configuration.EVENT_TROLLS_DESCRIPTION;
				break;
			case Configuration.EVENT_BLOODY_STUPID_JOHNSON:
				this.eventCardId=Configuration.EVENT_BLOODY_STUPID_JOHNSON;
				this.setName( Configuration.EVENT_BLOODY_STUPID_JOHNSON_NAME );
				this.instruction = Configuration.EVENT_BLOODY_STUPID_JOHNSON_DESCRIPTION; 
				break;
			case Configuration.EVENT_DEMONS_FROM_THE_DUNGEON_DIMENSIONS:
				this.eventCardId=Configuration.EVENT_DEMONS_FROM_THE_DUNGEON_DIMENSIONS;
				this.setName( Configuration.EVENT_DEMONS_FROM_THE_DUNGEON_DIMENSIONS_NAME );
				this.instruction = Configuration.EVENT_BLOODY_STUPID_JOHNSON_DESCRIPTION; 
				break;
			case Configuration.EVENT_DRAGON:
				this.eventCardId=Configuration.EVENT_DRAGON;
				this.setName( Configuration.EVENT_DRAGON_NAME );
				this.instruction = Configuration.EVENT_DRAGON_DESCRIPTION; 
				break;
			case Configuration.EVENT_EARTHQUAKE:
				this.eventCardId=Configuration.EVENT_EARTHQUAKE;
				this.setName( Configuration.EVENT_EARTHQUAKE_NAME );
				this.instruction = Configuration.EVENT_EARTHQUAKE_DESCRIPTION;
				break;
			case Configuration.EVENT_EXPLOSION:
				this.eventCardId=Configuration.EVENT_EXPLOSION;
				this.setName( Configuration.EVENT_EXPLOSION_NAME );
				this.instruction = Configuration.EVENT_EXPLOSION_DESCRIPTION;
				break;
			case Configuration.EVENT_FLOOD:
				this.eventCardId=Configuration.EVENT_FLOOD;
				this.setName( Configuration.EVENT_FLOOD_NAME );
				this.instruction = Configuration.EVENT_FLOOD_DESCRIPTION; 
				break;
			case Configuration.EVENT_FIRE:
				this.eventCardId=Configuration.EVENT_FIRE;
				this.setName( Configuration.EVENT_FIRE_NAME );
				this.instruction = Configuration.EVENT_FIRE_DESCRIPTION; 
				break;
			default:
				System.out.println( "Initializing City Card with the wrong index" );
			break;
		}
	}
	
	public void useEventCard(Player currentPlayer,Game game, int cardID)
	{
		switch(this.eventCardId) {
			case 0:
				fog(currentPlayer,game);
				break;
			case 1:
				misteriousMurders(currentPlayer,game);
				break;
			case 2:
				riots(currentPlayer,game);
				break;
			case 3:
				subsidence(currentPlayer,game);
				break;
			case 4:
				trolls(currentPlayer,game);
				break;				
			case 5:
				bloodyStupidJohnson(currentPlayer,game);
				break;
			case 6:
				demonsFromDungeonDimensions(currentPlayer,game);
			
				break;
			case 7:
				//Play another card.
				
				break;
			case 8:
				//Interrupt card.
				
				break;
			case 9:
				//Interrupt card.
				
				break;
			case 10:
				//Interrupt card.
				
				break;
			case 11:
				//Interrupt card.
				
				break;

			default:
				break;
		}
	}
	
	//TODO: Make sure to implement the toString method for green and brown cards.
	/**
	 * Fucntion Fog discards and displays five cards from the draw pile. If there are no more green cards in the draw pile then it will start discarding brown cards.
	 * @param currentPlayer
	 * @param game
	 */
	private void fog(Player currentPlayer,Game game)
	{
		Deck deck;
		GreenCard gCard;
		BrownCard bCard;
		for(int i=0;i<5;i++)
		{
			
			//Get the green deck or brown deck depending which one is in use.
			if(game.getEspecificDeck("green").getSizeDeck()>0)
			{
				//Green deck is not empty yet.
				deck=game.getEspecificDeck("green");
				gCard=(GreenCard) deck.getCard();
				//Display the card that's being discarded.
				gCard.toString();
			
			}else if(game.getEspecificDeck("brown").getSizeDeck()>0)
			{
				//Brown deck is not empty yet but green deck is.
				deck=game.getEspecificDeck("brown");
				bCard=(BrownCard) deck.getCard();
				//Display the card that's being discarded.
				bCard.toString();
			}
		}
	}
	
	
	/**
	 * Function makes all players, starting with the current player, roll a die and then remove a minion from the rolled area. If an area only contains minions from the rolling player
	 * then he must remove one of his minions.
	 * @param currentPlayer
	 * @param game
	 */
	private void misteriousMurders(Player currentPlayer,Game game)
	{
		int selectedIndex=-1;
		
		//Make current player roll the die
		Die die=game.getDie();
		//Get players
		Player [] players=game.getPlayers();
		
		int currentPlayerIndex=game.getCurrentPlayer();//Get index of current player.
		
		//Make all players roll.
		for(int index=0;index<game.getNumberOfPlayers();index++)
		{
			int rolledValue=die.roll();
			
			System.out.println("Player: "+players[currentPlayerIndex].getColor()+" rolled:"+rolledValue+" .");
			//Get area rolled by player
			Area rolledArea=game.getGameBoard().getAreas().get(rolledValue);
		
			int numMinions=rolledArea.getMinions().size();//Get number of minions on that area.
			ArrayList<Piece> minions=rolledArea.getMinions();//Get minions on rolled area.
		
		
			//Check if the area has any minion on it	
			if(numMinions>0)
			{
				//Display minions and ask player to remove one.
				System.out.println("The area rolled is: "+rolledArea.getCityCard().getName());
				System.out.println("Please select the index next to the minions you wish to remove(If area contains only player's minions one has to be removed anyways):");
				for(int i=0;i<numMinions;i++)
				{
					System.out.println("Color:"+minions.get(i).getColor()+" (index: "+i+")  ");
				}
				selectedIndex = game.keyIn.nextInt();
				//Update Gameboard by removing the selected minion and removing a trouble marker if one is in the area.
				rolledArea.removeMinion(players[currentPlayerIndex].getColor());
				rolledArea.removeTroubleMarker();
				//Update current player's hand. Remove minion from player depending on the areaCode(Card Number).
				players[currentPlayerIndex].removeMinionOnBoard(rolledArea.getCityCard().getCardNumber());
			
			}else
			{
				System.out.println("The rolled area doesn't contain any minions on it.");
			}
			//Get next Player: The one to the left.
			currentPlayerIndex=nextPlayer(currentPlayerIndex,game.getNumberOfPlayers());
		}
	}
	
	
	
	/**
	 * Function check if the board has eight or more trouble markers; if so the game ends and a winner is chosen by calculating  the player's hands.
	 * @param currentPlayer(currentPlayer)
	 * @param game(Game)
	 */
	private void riots(Player currentPlayer,Game game)
	{
		//Check if the board has 8 trouble makers.If so end the game.
		Gameboard gameBoard=game.getGameBoard();
		if(gameBoard.troubleMarkers().size()>=8)
		{
			System.out.println("There are eight or more trouble markers on the gameBoard. The Game is over");
			//TODO: call end of game function that has not been written yet.
		}else
		{
			System.out.println("The board does not contain eight or more trouble markers.The card does not apply. Keep Playing!!!");
		}
	}
	
	/**
	 * Function goes through each player that has at least one building on the board and makes them pay 2$ for each one. If the player doesn't have enough funds or
	 * he doesn't want to pay. The building is taken out of the gameboard, further the player returns the respective city card for that area.
	 * @param currentPlayer(Player)
	 * @param game(Game)
	 */
	private void subsidence(Player currentPlayer,Game game)
	{
		Area removeArea;
		int affordToPay=-1;
		String playerInput="";
		CityCard returnCityCard;
		//Get players
		Player [] players=game.getPlayers();
		
		int currentPlayerIndex=game.getCurrentPlayer();//Get index of current player.
		
		//Make all players pay 2$ for each building they have on the board.
		for(int index=0;index<game.getNumberOfPlayers();index++)
		{
			//Get players city cards. Since this would mean they have a building on that area.
			ArrayList<CityCard> cityCards=players[currentPlayerIndex].getPlayerCityCard();
			//Check If player has any cards on his/her hand.
			if(cityCards.size()>0)
			{
				System.out.println("Player: "+players[currentPlayerIndex].getColor()+" you have:"+cityCards.size()+" building on the board. And have: "+players[currentPlayerIndex].getMoney()+" dollars.");
				//Calculate how many buildings the player can pay for.
				affordToPay= (int) Math.floor(players[currentPlayerIndex].getMoney()/cityCards.size());
				System.out.println("Player: "+players[currentPlayerIndex].getColor()+"you can afford to pay for: "+affordToPay+" buildings." );
				//Display all the areas the player currently has buildings on and ask if he wishes to pay for that bulding.
				//If not remove building and take city card from player.
				for(int j=0;j<cityCards.size();j++)
				{
					System.out.println("You have a bulding on Area: "+cityCards.get(j).getName()+" do you wish to pay for it? yes/no.");
					playerInput=game.keyIn.next();
					//Clean up input.
					playerInput=playerInput.toLowerCase();
					
					if(playerInput.contains("y") && affordToPay>0)//If player entered 'yes' and he has enough funds.
					{
						//Take two dollars from player.
						players[currentPlayerIndex].payMoney(2);
						//Store two dollars in bank
						Bank.getInstance().deposit(2);
					}else if(affordToPay<=0 || playerInput.contains("n")) //Remove building and remove city card and update player's hand.
					{
						System.out.println("You can not afford to pay for this building or you don't want to. Building will be removed with the respective city card.");
						//update Gameboard.
						int cityCardAreaCode=cityCards.get(j).getCardNumber()-1;//-1 since city cards area codes start at 1 and we want to get the array index of areas.
						removeArea=game.getGameBoard().getAreas().get(cityCardAreaCode);
						removeArea.removeBuilding();
						//update Player's hand.
						players[currentPlayerIndex].setBuildingOnHand(players[currentPlayerIndex].getBuildingOnHand()-1);
						
						returnCityCard=players[currentPlayerIndex].returnCityCard(cityCards.get(j));//Remove city card from player.
						game.getGameBoard().addCityCard(returnCityCard);//Add city card back to the gameBoard.  //TODO:Check if returns true.
					}
					//Calculate how many buildings the player can pay for.
					affordToPay= (int) Math.floor(players[currentPlayerIndex].getMoney()/cityCards.size());
					System.out.println("Player: "+players[currentPlayerIndex].getColor()+"you can afford to pay for: "+affordToPay+" buildings." );
				}
				
				
			}else//get next player.
			{
				System.out.println("Player: "+players[currentPlayerIndex].getColor()+" you have no buildings, you don't have to pay anything.");
				currentPlayerIndex=nextPlayer(currentPlayerIndex,game.getNumberOfPlayers());//Get next player.
				continue;
			}
			//Transaction finished get the next building.
			currentPlayerIndex=nextPlayer(currentPlayerIndex,game.getNumberOfPlayers());//Get next player.
		}
		
	}
	
	/**
	 * Function will make the requesting player roll the die three times. After it will add three trolls to the values rolled.
	 * Since Trolls are a consider as minions trouble markers will be added to the areas if required.
	 * @param currentPlayer (Player)
	 * @param game (Game)
	 */
	private void trolls(Player currentPlayer,Game game)
	{
		//Make current player roll the die
		Die die=game.getDie();
		int [] rolledDie=new int[3];
		//Roll die three times and store results.
		for(int i=0;i<rolledDie.length;i++)
		{
			rolledDie[i]=die.roll();
			System.out.print("Rolled Value: "+rolledDie[i]+"  ");
		}
		
		ArrayList<Area> areas=game.getGameBoard().getAreas();
		
		System.out.println();
		//Add trolls to the required areas.
		for(int i=0;i<rolledDie.length;i++)
		{
			areas.get(rolledDie[i]-1).addRemoveTroll(1);//-1 since the array list starts at 0;
			System.out.print("Troll added to area : "+areas.get(rolledDie[i]-1).getCityCard().getName()+"  ");
		}
		
		
	}
	
	/**
	 * Function has invoking player to roll a die. The die represents an area, if a player has a building on it then the city card is taken away from the player and a minion belonging to 
	 * that player is also removed from the gameboard; if one exists.
	 * @param currentPlayer (Player)
	 * @param game (Game)
	 */
	private void bloodyStupidJohnson(Player currentPlayer,Game game)
	{
		//Make current player roll the die
		Die die=game.getDie();
		
		Player[] players=game.getPlayers();
		int currentPlayerIndex=game.getCurrentPlayer();//Get index of current player.
		
		int valueDie=die.roll();
		
		System.out.println("Player: "+players[currentPlayerIndex].getColor()+" rolled: "+valueDie);
		
		//temp varialbes
		Player selectedPlayer;
		CityCard cityCard=null;
		
		//check if rolled card is in play(A player has it)
		for(int i=0;i<players.length;i++)
		{
			cityCard=players[currentPlayerIndex].getCCByCardNumber(valueDie);
			if(cityCard != null)
				break;
			//else keep looking in other players hand.
			currentPlayerIndex=nextPlayer(currentPlayerIndex,game.getNumberOfPlayers());
		}
		
		//check if the card was found in one of the players hand if not card is not valid.
		if(cityCard==null)
		{
			System.out.println("The rolled City Card Area Code is not currently in play. Bloddy Stupid Johnson does not apply.");
			return;
		}
		
		//Remove a minion belonging to the player that had the rolled city card.
		Gameboard gameBoard=game.getGameBoard();
		Area rolledArea=gameBoard.getAreaByCityCard(valueDie);//Get respective area.
		
		//Try to remove minion. 
		if(rolledArea.removeMinion(players[currentPlayerIndex].getColor()))
		{
			System.out.println("A minion belonging to Player: "+players[currentPlayerIndex].getColor()+" on area: "+rolledArea.getCityCard().getName()+" has been removed.");
			//update players hand.
			players[currentPlayerIndex].removeMinionOnBoard(valueDie-1);//-1 since it's using an array that start on 0.
		}
		else
			System.out.println("Player: "+players[currentPlayerIndex].getColor()+" didn't have any minions on area: "+rolledArea.getCityCard().getName()+ " nothing was removed.");
		
		//Update player and gameboard
		CityCard returnCityCard=players[currentPlayerIndex].returnCityCard(cityCard);//Remove city card from player.
		game.getGameBoard().addCityCard(returnCityCard);//Add city card back to the gameBoard.  //TODO:Check if returns true.
		
		System.out.println("Players city card was taken away....");
		
	}
	
	public void demonsFromDungeonDimensions(Player currentPlayer,Game game)
	{
		//Make current player roll the die
		Die die=game.getDie();
		int [] rolledDie=new int[4];
		//Roll die four times and store results.
		for(int i=0;i<rolledDie.length;i++)
		{
			rolledDie[i]=die.roll();
			System.out.print("Rolled Value: "+rolledDie[i]+"  ");
		}
		ArrayList<Area> areas=game.getGameBoard().getAreas();

		//Add trolls to the required areas.
		for(int i=0;i<rolledDie.length;i++)
		{
			areas.get(rolledDie[i]-1).addRemoveDemon(1);//-1 since the array list starts at 0;
		}
	}
	
	/**
	 * Returns the next player depending on the parameters sent. Getting the next player  depends on the player that called the function.
	 * @param currentPlayer(int)
	 * @param numberOfPlayers(int)
	 * @return int
	 */
	private  int nextPlayer(int currentPlayer,int numberOfPlayers) {
		currentPlayer = (currentPlayer + 1) % numberOfPlayers;
		return currentPlayer;
	}
	
	/**
	 * Setter: set name for event card.
	 */
	public void setName(String name)
	{
		this.eventCardName=name;
	}
}
