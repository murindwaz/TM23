package ca.concordia.game.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//Packages
import ca.concordia.game.main.Game;


/**
 * Class Gameboard handles and contains the twelve game areas of the game.
*@author Pascal Maniraho 
 *@author Gustavo Pereira
 *@author Bhavik Desai 
 *@author Jesus Esteban Garro Matamoros 
 *@author Diego Pizarro

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
