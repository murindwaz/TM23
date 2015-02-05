package ca.concordia.game.model;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;


public class Area {
	
	private CityCard cityCard;//A city card belongs to an Area.
	private ArrayList<Piece> minions;
	private boolean troubleMarker;
	private boolean building;
	private int demon;
	private int troll;
	
	//Constructor for new game.
	public Area(CityCard cityCard)
	{
		this.cityCard=cityCard;
		this.troubleMarker=false;
		this.building=false;
		this.demon=0;
		this.troll=0;
	}
	
	//Constructor
	public Area(CityCard cityCard,boolean troubleMarker,boolean building,int demon,int troll)
	{
		this.cityCard=cityCard;
		this.troubleMarker=troubleMarker;
		this.building=building;
		this.demon=demon;
		this.troll=troll;
	}
	
	//Add a minion to area
	public void addMinion(Piece minion)
	{
		this.minions.add(minion);
	}
	
	//Add a minion to area
	public void removeMinion(Piece minion)
	{
		this.minions.remove(minion);
	}
	
	//Add troubleMarker to area if possible(Only one trouble marker is allowed per area). Return true if successful else return false.
	public boolean addTroubleMarker()
	{
		if(this.troubleMarker==false )//There's no trouble marker on this area.
		{
			this.troubleMarker=true;
			return true;
		}else
		{//A trouble marker already exists on this area.
			return false;
		}
	}
	
	//remove troubleMarker to area if possible(Only one trouble marker is allowed per area). Return true if successful else return false.
	public boolean removeTroubleMarker()
	{
		if(this.troubleMarker==true )//There's no trouble marker on this area.
		{
			this.troubleMarker=false;
			return true;
		}else
		{//A trouble marker already exists on this area.
			return false;
		}
	}
	
	//Add troubleMarker to area if possible(Only one trouble marker is allowed per area). Return true if successful else return false.
	public boolean addBuilding()
	{
		if(this.building==false)//There's no trouble marker on this area.
		{
			this.building=true;
			return true;
		}else
		{//A trouble marker already exists on this area.
				return false;
		}
	}
	
	//Add troubleMarker to area if possible(Only one trouble marker is allowed per area). Return true if successful else return false.
	public boolean removeBuilding()
	{
		if(this.building==true)//There's no trouble marker on this area.
		{
			this.building=false;
			return true;
		}else
		{//A trouble marker already exists on this area.
				return false;
		}
	}
	
	//Add or remove demon to area. If the argument is 1 then it will add a demon else if it is 2 it will remove a demon if possible.
	//Will return a string with the status.
	public String addRemoveDemon(int addRemove)
	{
		if(addRemove==1)//add demon
		{
			this.demon++;
			return "success";
		}else if (addRemove==2)//remove demon if possible
		{
			if(this.demon >0)
			{
				this.demon++;
				return "success";
			}else
				return "failed";
		}
		return "error";
	}
	
	//Add or remove troll to area. If the argument is 1 then it will add a troll else if it is 2 it will remove a troll if possible.
	//Will return a string with the status.
	public String addRemoveTroll(int addRemove)
	{
		if(addRemove==1)//add troll
		{
			this.troll++;
			return "success";
		}else if (addRemove==2)//remove troll if possible
		{
			if(this.troll >0)
			{
				this.troll++;
				return "success";
			}else
				return "failed";
		}
		return "error";
	}

	
	

}
