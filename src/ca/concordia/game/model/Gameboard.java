package ca.concordia.game.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


//Packages
import ca.concordia.game.main.Game;
import ca.concordia.game.common.common;



/**
 * Class Gameboard handles and contains the twelve game areas of the game.
 * 
 * @author Pascal Maniraho 
 * @author Gustavo Pereira
 * @author Bhavik Desai 
 * @author Jesus Esteban Garro Matamoros 
 * @author Diego Pizarro
 */
public class Gameboard {
	
	private ArrayList<Area> areas;
	private ArrayList<CityCard> cityCards;//Initially all city cards belong to the Deck.
	
	/**
	 * Contructor for new game; set's gameboard initial conditions.
	 * set one minion on the three initial areas as required by the game(Areas:Dolly Sisters(1),The Scours(5),The Shades(7))
	 */

	public Gameboard(Player [] players)
	{
		//Initialize instance ArrayLists.

		this.areas = new ArrayList<Area>();
		this.cityCards =new ArrayList<CityCard>();
		CityCard temp;
		for(int i=0;i<12;i++)//Populate the gameboard with the twelve areas.
		{
			//Initialize city Cards.
			 temp= new CityCard(i);
			this.cityCards.add(temp);
			this.areas.add(new Area(temp)); //Initialize new cityAreas
		}
		
		//Add one minion to the three required areas. One minion per player.
		for(int i=0;i<players.length;i++)
		{
			//add to Dolly Sisters one minion per player.
			this.areas.get(0).addMinion(new Piece(players[i].getColor()),false);
			//add to The Scours one minion per player.
			this.areas.get(4).addMinion(new Piece(players[i].getColor()),false);
			//add to The Shades one minion per player.
			this.areas.get(6).addMinion(new Piece(players[i].getColor()),false);
		}

	}
	
	/**
	 * Default constructor. Used when loading a game.
	 */
	public Gameboard(){
		this.areas=new ArrayList<Area>();
		this.cityCards=new ArrayList<CityCard>();
	}
	
	//TODO: Check if it works.Check indexes.
	/**
	 * Displays all the areas that are adjacent to the area sent as an argument.
	 * @param area(Area)
	 */
	public void displayAdjacentAreas(Area area)
	{
		int cardCode;
		Area adjArea;
		System.out.println("Area: "+area.getCityCard().getName()+" is adjacent to:");
		//Go trough all adjacent areas.
		for(int i=0; i<area.getCityCard().getAdjacentAreas().size();i++)
		{
			cardCode=area.getCityCard().getAdjacentAreas().get(i);
			adjArea=this.getAreaByCityCard(cardCode-1);//-1 since we are accesing an array list that starts at 0;
			System.out.print(adjArea.getCityCard().getName()+"("+cardCode+")"+" ");
		}
	}
	
	/**
	 * Function returns an area depending on it's city card area code. If not found returns null.
	 * @param areaCode(int)
	 * @return Area
	 */
	public Area getAreaByCityCard(int areaCode)
	{
		for(int i=0;i<this.areas.size();i++)
		{
			if(this.areas.get(i).getCityCard().getCardNumber()==areaCode)
				return this.areas.get(i);
		}
		
		return null;
	}
	
	/**
	 * Remove A Card Object and return it.(When a player takes possession of a city card)
	 * @param card(CityCard)
	 * @return Card
	 */
	public CityCard deleteCardFromDeck(CityCard card)
	{
		int index=this.cityCards.indexOf(card);
		return this.cityCards.remove(index);
	}
	
	/**
	 * Add city card to game board. When player returns one of it's city cards.
	 * @param card(CityCard)
	 * @return boolean
	 */
	public boolean addCityCard(CityCard card)
	{
		int oldSize=this.cityCards.size();
		this.cityCards.add(card);
		int newSize=this.cityCards.size();
		
		if(newSize == oldSize+1)
			return true;
		else
			return false;
	}

	/**
	 * getter Twelve areas.
	 * @return
	 */
	public ArrayList<Area> getAreas()
	{
		return this.areas;
	}
	

	/**
	 * Used only when loading a new GameState. Adds Areas to gameboard.
	 * @param area
	 */
	public void addArea(Area area)
	{
		this.areas.add(area);
	}

	/**
	 * Resets Areas information from Gameboard.
	 */
	public void resetAreas()
	{
		this.areas.clear();;
		//this.areas=new ArrayList<Area>();
		this.cityCards.clear();
	}
	
	/**
	 * Returns the names of the areas being control by a player.Called each time a player starts his/her turn.
	 * It's also used to check winning condition for Lord Selachii,Lord Rust and Lord Worde
	 * @param currentPlayer
	 * @return ArrayList<String>
	 */
	public ArrayList<String> controlledAreas(Player currentPlayer)
	{
		ArrayList<String> controlledAreasPlayer=new ArrayList<String>();
		Area tempArea;
		boolean controls=false;
		for(int i=0;i< this.areas.size();i++)
		{
			//Check if the are any demons in the current area.If there is 1 or more demons the area is not controlled.
			tempArea= this.areas.get(i);
			if(tempArea.getDemon()>0)
				continue;
			//Check if the player has control of the area being processed.
			controls=tempArea.controlsArea(currentPlayer.getColor());
			
			if(controls)
				controlledAreasPlayer.add(tempArea.getCityCard().getName());
		}
		return controlledAreasPlayer;
	}
	
	/**
	 * For Lord Vetani winnning Condition: It checks how many areas the player has minions on.
	 * @param currentPlayer(Player)
	 * @return int
	 */
	public int numberMinionsAreas(Player currentPlayer){
		int [] minionsInArea=new int [12];
		int numberOfMinionsInAreas=0;
		minionsInArea= currentPlayer.getMinionsOnArea();
		for(int i=0;i<minionsInArea.length;i++){
			if(this.areas.get(i).getDemon()>0)//If there are demoan in the area then the area doesn't count for winning condition.
				continue;
			//the player possess at least one minion on the current Area.
			if(minionsInArea[i]>0)
				numberOfMinionsInAreas++;
		}
		return numberOfMinionsInAreas;
	}
	
	/**
	 * Returns the names of areas that currently contain a trouble marker on it.
	 * @return ArrayList<String>
	 */
	public ArrayList<String> troubleMarkers(){
		ArrayList<String> troubleMarkersAreas=new ArrayList<String>();
		Area tempArea;
		for(int i=0; i< this.areas.size();i++){
			tempArea=this.areas.get(i);
			if(tempArea.getTroubleMarker()) {
				troubleMarkersAreas.add(tempArea.getCityCard().getName());
			}
		}
		return troubleMarkersAreas;
	}
	
	/**
	 * Returns the  areas that currently contain a trouble marker on it.
	 * @return ArrayList<Area>
	 */
	public ArrayList<Area> troubleMarkersAreas(){
		ArrayList<Area> troubleMarkersAreas=new ArrayList<Area>();
		Area tempArea;
		for(int i=0; i< this.areas.size();i++){
			tempArea=this.areas.get(i);
			if(tempArea.getTroubleMarker()) {
				troubleMarkersAreas.add(tempArea);
			}
		}
		return troubleMarkersAreas;
	}

	/**
	 * toString method for Gameboard
	 */
	@Override
	public String toString(){
		String info = "";
		info=info+"****************************GameBoard****************************************\n";
		for(int i=0;i<areas.size();i++)
		{
			info=info+areas.get(i).toString();
		}
		info=info+"****************************GameBoard END****************************************\n";
		return info;
	}
	

}
