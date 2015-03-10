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
 * @author Pascal,Gustavo,bhavik,Esteban,Diego
 *
 */
public class Gameboard {
	
	private String gameName;
	private ArrayList<Area> areas;
	
	/**
	 * Contructor for new game
	 */
	public Gameboard()
	{
		this.areas = new ArrayList<Area>();
		for(int i=0;i<12;i++)//Populate the gameboard with the twelve areas.
		{
			
			this.areas.add(new Area(new CityCard(i))); //Initialize new cityAreas
		}
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
		this.areas=null;
		this.areas=new ArrayList<Area>();
	}
	
	/**
	 * Returns the names of the areas being control by a player.
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
	 * toString method for Gameboard
	 */
	public String toString()
	{
		String info = "";
		for(int i=0;i<areas.size();i++)
		{
			info=info+areas.get(i).toString();
		}
		
		return info;
	}
	
	//public getArea()

}
