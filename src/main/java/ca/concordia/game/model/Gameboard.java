package ca.concordia.game.model;

import java.util.ArrayList;


public class Gameboard {
	
	private String gameName;
	private ArrayList<Area> areas;
	
	//Contructor for new game.
	public Gameboard()
	{
		for(int i=0;i<=12;i++)//Populate the gameboard with the twelve areas.
		{
			
			this.areas.add(new Area(new CityCard(i))); //Initialize new cityAreas
		}
	}
	
	//Constructor.
	public Gameboard(String gameName)
	{
		this.gameName=gameName;
	}

}
