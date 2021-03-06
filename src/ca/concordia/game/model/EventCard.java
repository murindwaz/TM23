package ca.concordia.game.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ca.concordia.game.common.common.Colors;
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
				this.setEventName( "FOG" );
				this.instruction = Configuration.EVENT_FOG_DESCRIPTION;
				break;
			case Configuration.EVENT_MYSTERIOUS_MURDERS:
				this.eventCardId=Configuration.EVENT_MYSTERIOUS_MURDERS;
				this.setEventName("Mysterious Muerders" );
				this.instruction = Configuration.EVENT_MYSTERIOUS_MURDERS_DESCRIPTION;
				break;
			case Configuration.EVENT_RIOTS:
				this.eventCardId=Configuration.EVENT_RIOTS;
				this.setEventName( "Riots" );
				this.instruction = Configuration.EVENT_RIOTS_DESCRIPTION;
				break;
			case Configuration.EVENT_SUBSIDENCE:
				this.eventCardId=Configuration.EVENT_SUBSIDENCE;
				this.setEventName( "Subsidence" );
				this.instruction = Configuration.EVENT_SUBSIDENCE_DESCRIPTION;
				break;
			case Configuration.EVENT_TROLLS:
				this.eventCardId=Configuration.EVENT_TROLLS;
				this.setEventName( "Trolls" );
				this.instruction = Configuration.EVENT_TROLLS_DESCRIPTION;
				break;
			case Configuration.EVENT_BLOODY_STUPID_JOHNSON:
				this.eventCardId=Configuration.EVENT_BLOODY_STUPID_JOHNSON;
				this.setEventName( "Bloody Stupid Johnson");
				this.instruction = Configuration.EVENT_BLOODY_STUPID_JOHNSON_DESCRIPTION; 
				break;
			case Configuration.EVENT_DEMONS_FROM_THE_DUNGEON_DIMENSIONS:
				this.eventCardId=Configuration.EVENT_DEMONS_FROM_THE_DUNGEON_DIMENSIONS;
				this.setEventName(" Demons From dungeon dimension" );
				this.instruction = Configuration.EVENT_BLOODY_STUPID_JOHNSON_DESCRIPTION; 
				break;
			case Configuration.EVENT_DRAGON:
				this.eventCardId=Configuration.EVENT_DRAGON;
				this.setEventName( "Dragon" );
				this.instruction = Configuration.EVENT_DRAGON_DESCRIPTION; 
				break;
			case Configuration.EVENT_EARTHQUAKE:
				this.eventCardId=Configuration.EVENT_EARTHQUAKE;
				this.setEventName( "EarthQuake" );
				this.instruction = Configuration.EVENT_EARTHQUAKE_DESCRIPTION;
				break;
			case Configuration.EVENT_EXPLOSION:
				this.eventCardId=Configuration.EVENT_EXPLOSION;
				this.setEventName( "Explosion" );
				this.instruction = Configuration.EVENT_EXPLOSION_DESCRIPTION;
				break;
			case Configuration.EVENT_FLOOD:
				this.eventCardId=Configuration.EVENT_FLOOD;
				this.setEventName( "Event Flood" );
				this.instruction = Configuration.EVENT_FLOOD_DESCRIPTION; 
				break;
			case Configuration.EVENT_FIRE:
				this.eventCardId=Configuration.EVENT_FIRE;
				this.setEventName( "Fire" );
				this.instruction = Configuration.EVENT_FIRE_DESCRIPTION; 
				break;
			default:
				System.out.println( "Initializing City Card with the wrong index" );
			break;
		}
	}
	
	/**
	 * 
	 */
	public void setEventName(String name)
	{
		this.eventCardName=name;
	}
	
	/**
	 * Function executes the correct method depending on the event card that called it.
	 * @param currentPlayer(Player)
	 * @param game(Game)
	 * @param cardID(int)
	 */
	public void useEventCard(Player currentPlayer,Game game)
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
				dragon(currentPlayer,game);
				break;
			case 8:
				earthquake(currentPlayer,game);
				break;
			case 9:
				explosion(currentPlayer,game);
				break;
			case 10:
				flood(currentPlayer,game);
				break;
			case 11:
				fire(currentPlayer,game);	
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
	private void fog(Player currentPlayer,Game game){
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
				System.out.println("Discarted: "+gCard.toString());
			
			}else if(game.getEspecificDeck("brown").getSizeDeck()>0)
			{
				//Brown deck is not empty yet but green deck is.
				deck=game.getEspecificDeck("brown");
				bCard=(BrownCard) deck.getCard();
				//Display the card that's being discarded.
				System.out.println("Discarted: "+bCard.toString());
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
				rolledArea.removeMinion(minions.get(selectedIndex).getColor());
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
			game.CalculateAWinner();
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
				affordToPay= (int) Math.floor(players[currentPlayerIndex].getMoney()/(cityCards.size()*2));
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
					affordToPay= (int) Math.floor(players[currentPlayerIndex].getMoney()/(cityCards.size()*2));
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
		Piece removedminion=rolledArea.removeMinion(players[currentPlayerIndex].getColor());
		if(removedminion != null)
		{
			System.out.println("A minion belonging to Player: "+players[currentPlayerIndex].getColor()+" on area: "+rolledArea.getCityCard().getName()+" has been removed.");
			//update players hand.
			players[currentPlayerIndex].removeMinionOnBoard(valueDie);
		}
		else
			System.out.println("Player: "+players[currentPlayerIndex].getColor()+" didn't have any minions on area: "+rolledArea.getCityCard().getName()+ " nothing was removed.");
		
		//Update player and gameboard
		CityCard returnCityCard=players[currentPlayerIndex].returnCityCard(cityCard);//Remove city card from player.
		game.getGameBoard().addCityCard(returnCityCard);//Add city card back to the gameBoard.  //TODO:Check if returns true.
		
		System.out.println("Players city card was taken away....");
		
	}
	
	/**
	 * Function makes player roll the die four times and depending on the rolled value demons area added to the respective areas.
	 * @param currentPlayer
	 * @param game
	 */
	private void demonsFromDungeonDimensions(Player currentPlayer,Game game)
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
		System.out.println();
		ArrayList<Area> areas=game.getGameBoard().getAreas();
		

		//Add trolls to the required areas.
		for(int i=0;i<rolledDie.length;i++)
		{
			areas.get(rolledDie[i]-1).addRemoveDemon(1);//-1 since the array list starts at 0;
			System.out.println("Area: "+areas.get(rolledDie[i]-1).getCityCard().getName()+" has a demon in it.");
		}
	}
	
	/**
	 * Dragon makes a player roll a die. the area that corresponds to the rolled value will have to remove all minions,demons,trolls,
	 * trouble markers and building if they exist. The city card owned by the owner of the destroyed building will be returned to the
	 * gameboard as well.
	 * @param currentPlayer(Player)
	 * @param game(Game)
	 */
	private void dragon(Player currentPlayer,Game game)
	{
		
		Colors color;
		Player player;
		int [] valueRolled=new int[1];
		
		//Make current player roll the die
		Die die=game.getDie();
		valueRolled[0]=die.roll();
		//Get affected Area.
		Area area=game.getGameBoard().getAreaByCityCard(valueRolled[0]);//-1 it's an arraylist and index starts at 0;
		
		System.out.println("Player: "+currentPlayer.getColor()+" rolled:"+valueRolled[0]+". Area: "+area.getCityCard().getName()+" will get attacked by a dragon...");
		
		//Remove Trolls and demons from area.
		for(int i=0;i<area.getTroll();i++)
		{
			area.addRemoveTroll(1);
		}
		for(int i=0;i<area.getDemon();i++)
		{
			area.addRemoveDemon(1);
		}
		//Remove trouble marker if it exists.
		area.removeTroubleMarker();
		//Remove minions
		for(int i=0;i<area.getMinions().size();i++)
		{
			color=area.getMinions().get(i).getColor();
			//Update Area
			area.removeMinion(color); //TODO:Check if returned value is true.
			//Update Player
			player=game.getPlayerByColor(color);
			player.removeMinionOnBoard(valueRolled[0]);
		}
		
		//Remove building and make affected player return the respective city card to the board.
		this.removeBuildingFromArea(valueRolled, game);
		
	}
	
	/**
	 * Earthquake makes the calling player roll a die twice and depending on the values rolled two areas gets their buildings 
	 * removed and the affected player return their city cards to the board. If there are no buildings on the areas affected nothing happens.
	 * @param currentPlayer(Player)
	 * @param game(Game)
	 */
	private void earthquake(Player currentPlayer,Game game)
	{
		
		//Make current player roll the die
		Die die=game.getDie();
		int [] rolledDie=new int[2];
		//Roll die four times and store results.
		for(int i=0;i<rolledDie.length;i++)
		{
			rolledDie[i]=die.roll();
			System.out.print("Player: "+currentPlayer.getColor()+" Rolled Value: "+rolledDie[i]+"  ");
		}
		//Check if the areas affected have buildings on them, if so remove them along with the city card.
		removeBuildingFromArea(rolledDie,game);
	}
	
	/**
	 * Explosion makes the calling player roll a die once and depending on the values rolled one areas gets their building 
	 * removed and the affected player return their city cards to the board. If there are no buildings on the area affected nothing happens.
	 * @param currentPlayer(Player)
	 * @param game(Game)
	 */
	private void explosion(Player currentPlayer,Game game)
	{
		//Make current player roll the die
		Die die=game.getDie();
		int [] rolledDie=new int[1];
		//Roll die four times and store results.
		for(int i=0;i<rolledDie.length;i++)
		{
			rolledDie[i]=die.roll();
			System.out.print("Player: "+currentPlayer.getColor()+" Rolled Value: "+rolledDie[i]+"  ");
		}
		//Check if the areas affected have buildings on them, if so remove them along with the city card.
		removeBuildingFromArea(rolledDie,game);
	}
	
	/**
	 * Flood makes the calling player roll a die twice, depending on the values rolled the corresponding areas get flooded; only if it is adjacent to the river.
	 * All the minions have to be removed from this area and put on an adjacent area to the affected area.
	 * @param currentPlayer (Player)
	 * @param game (Game)
	 */
	private void flood(Player currentPlayer,Game game)
	{
		Area area;
		Area moveToArea;
		ArrayList<Piece>minions;
		Colors color;
		Player player;//AffectedPlayer
		Piece removedMinion;
		int areaMoveInt=-1;
		//Make current player roll the die
		Die die=game.getDie();
		int [] rolledDie=new int[2];
		//Roll die four times and store results.
		for(int i=0;i<rolledDie.length;i++)
		{
			rolledDie[i]=die.roll();
			System.out.print("Player: "+currentPlayer.getColor()+" Rolled Value: "+rolledDie[i]+"  ");
		}
		//Make players move their minions to adjacent areas, from the affected areas.
		for(int i=0;i<rolledDie.length;i++)
		{
			//Get affected Area.
			area=game.getGameBoard().getAreaByCityCard(rolledDie[i]);//-1 it's an arraylist and index starts at 0;
			if(area.getCityCard().getDoesFlood())//check if the area can be flooded.
			{
				System.out.println("Area: "+area.getCityCard().getName()+"  got flooded by the river...");
				
				minions=area.getMinions();
				//Move each minion to adjacent areas.
				for(int j=0;j < minions.size();j++)
				{
					color = minions.get(j).getColor();
					player=game.getPlayerByColor(color);
					System.out.println("Player:"+player.getColor()+" please select the index of the area you wish to move your minions to:");
					//Display adjacent areas to current affected area.
					game.getGameBoard().displayAdjacentAreas(area);
					areaMoveInt=game.keyIn.nextInt();//Get input from player.
					//Get area where player choose to move minion to.
					moveToArea=game.getGameBoard().getAreaByCityCard(areaMoveInt-1);//-1 Since we area accessing an array that starts at 0.
					
					//remove minion from flooded area and move it to new area.
					removedMinion=area.removeMinion(color);//Remove
					moveToArea.addMinion(removedMinion,false);//Add
					
					//Update Players hand.
					player.moveMinionToNewArea(area.getCityCard().getCardNumber(), areaMoveInt);
					
					System.out.println("Minion: "+removedMinion.getColor()+" was moved from: "+area.getCityCard().getName()+" to:"+moveToArea.getCityCard().getName());
				}
				
				
			}else
			{
				System.out.println("Area: "+area.getCityCard().getName()+" is not affected by the flood, nothing happens.");
			}
		}
	}
	
	/**
	 * Fire makes a player roll a die, if the area corresponding to the rolled value has a building then it is destroyed and the player can keep rolling.
	 * If not then the fire stops. If player keeps rolling he needs to roll the value of an area that is adjacent to the previous area rolled for the fire 
	 * to continue and that area has to contain a building, otherwise the fire stops.
	 * @param currentPlayer (Player)
	 * @param game (Game)
	 */
	private void fire(Player currentPlayer,Game game)
	{
		//Make current player roll the die
		Die die=game.getDie();
		int [] buildingToRemove;
		Area affectedArea=null;
		int rolledValue=-1;
		boolean keeprolling=true;
		//Temporal Arraylist
		ArrayList<Integer> temp= new ArrayList<Integer>();
		
		boolean isAdjacent=false;
		while(keeprolling)
		{
			//check if this is the first roll.
			if(temp.isEmpty())
			{//is first roll
				rolledValue=die.roll();//roll and store rolled Value.
				System.out.println("Player: "+currentPlayer.getColor()+" Rolled Value: "+rolledValue+".");
				affectedArea=game.getGameBoard().getAreaByCityCard(rolledValue);
				//System.out.println("Affected ");
			}else
			{//is not first roll.
				rolledValue=die.roll();//roll and store rolled Value.
				System.out.println("Player: "+currentPlayer.getColor()+" Rolled Value: "+rolledValue+".");
				//check if this area is adjacent to the last one.
				isAdjacent= affectedArea.getCityCard().isAdjacent(rolledValue);
				if(isAdjacent)
				{
					System.out.print("Area: "+affectedArea.getCityCard().getName()+" is adjacent to rolled area:");
					affectedArea=game.getGameBoard().getAreaByCityCard(rolledValue);
					System.out.print(affectedArea.getCityCard().getName()+"./n");
				}
				else
				{
					keeprolling=false;
					System.out.print("Area: "+affectedArea.getCityCard().getName()+" is NOT adjacent to rolled area:");
					affectedArea=game.getGameBoard().getAreaByCityCard(rolledValue);
					System.out.print(affectedArea.getCityCard().getName()+". END OF FIRE./n");
					break;//Exit loop.
				}
			}
			//check if area has a building.
			if(affectedArea.getBuilding())
			{
				//Add to arrayList
				temp.add(rolledValue);
				System.out.println("Area:"+affectedArea.getCityCard().getName()+" is affected by fire!!!!!!!"+ "  Player:"+affectedArea.getBuildingColor()+" has lost a building!!!");
				
			}else//If area doesn't have a building then stop rolling and exit while loop.
			{
				keeprolling=false;
				System.out.println("Area:"+affectedArea.getCityCard().getName()+"doesn't have a Building. Fire has no effect.");
			}
				
			
		}
		
		//Copy values of affected areas(ArrayList temp) to array.
		buildingToRemove=convertIntegers(temp);
		
		//Remove buildings in areas affected by the fire.
		removeBuildingFromArea(buildingToRemove,game);
	}

	/**
	 * Function removes a building and makes the affected player return his city card to the board depending on the values sent in the array.
	 * Each value in the array  represents an area that whose building needs to be removed; if it exists. Further the GameBoard and the hand from the Player affected
	 * are updated respectively.
	 * @param rolledDie (int [])
	 * @param game (Game)
	 */
	private void removeBuildingFromArea(int []rolledDie,Game game)
	{
		Area area;
		boolean check=false;
		Colors color;
		Player player;//AffectedPlayer
		//Check if the areas affected have buildings on them, if so remove them along with the city card.
		for(int i=0;i<rolledDie.length;i++)
		{
			//Get affected Area.
			area=game.getGameBoard().getAreaByCityCard(rolledDie[i]);//-1 it's an arraylist and index starts at 0;
			check=area.getBuilding();
			if(check)
			{//Area contains building.
				color=area.getBuildingColor();
				player=game.getPlayerByColor(color);//Get player affected(owner of building)

				area.removeBuilding();//Remove building and set color to none.

				//Add building to players hand.
				player.setBuildingOnHand(player.getBuildingOnHand()-1);
				
				CityCard cityCard=player.getCCByCardNumber(rolledDie[i]);

				//Take city Card from player and return it to the gameboard.
				CityCard returnCityCard=player.returnCityCard(cityCard);//Remove city card from player.
				game.getGameBoard().addCityCard(returnCityCard);//Add city card back to the gameBoard.  //TODO:Check if returns true.
				System.out.println("Player:"+player.getColor()+" had his building and city card removed, from area: "+area.getCityCard().getName());
			}
			else
			{
				System.out.println("There is no building on area: "+area.getCityCard().getName());
			}
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
	 * Converts a List of Integers to and array of int(primitive type)
	 * @param integers (List<Integer>)
	 * @return int[]
	 */
	private  int[] convertIntegers(List<Integer> integers)
	{
	    int[] ret = new int[integers.size()];
	    Iterator<Integer> iterator = integers.iterator();
	    for (int i = 0; i < ret.length; i++)
	    {
	        ret[i] = iterator.next().intValue();
	    }
	    return ret;
	}
	
	/**
	 * Setter: set name for event card.
	 */
	public void setName(String name)
	{
		this.eventCardName=name;
	}
	/**
	 * Getter: Event description or instruction.
	 * @return String
	 */
	public String getInstruction()
	{
		return this.instruction;
	}
	/**
	 * Getter: Event card NAme
	 * @return String
	 */
	public String getEventCardName()
	{
		return this.eventCardName;
	}
}
