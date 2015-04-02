package ca.concordia.game.model;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

import ca.concordia.game.common.common.Colors;

/**
 * Class Area contains the possible object that could be available to an area.
 * 
 * @author Pascal Maniraho 
 * @author Gustavo Pereira
 * @author Bhavik Desai 
 * @author Jesus Esteban Garro Matamoros 
 * @author Diego Pizarro
 */

//TODO: Come up with a way to set restrictions on area when a demon is on it.

public class Area {
	
	private CityCard cityCard;//A city card belongs to an Area.
	private ArrayList<Piece> minions;
	private boolean troubleMarker;
	private boolean building;
	private Colors buildingColor;
	private int demon;
	private int troll;
	
	

	/**
	 * Constructor for new game.
	 * @param cityCard
	 */
	public Area(CityCard cityCard)
	{
		this.cityCard = cityCard;
		this.troubleMarker = false;
		this.building = false;
		this.buildingColor = Colors.NONE;
		this.demon = 0;
		this.troll = 0;
		minions= new ArrayList<Piece>();
	}
	

	/**
	 * Constructor for loaded game. Reset everything.
	 * @param cityCard
	 * @param troubleMarker
	 * @param building
	 * @param demon
	 * @param troll
	 */
	public Area(CityCard cityCard,boolean troubleMarker,Colors buildingColor,int demon,int troll)
	{
		this.cityCard=cityCard;
		this.troubleMarker=troubleMarker;
		//Modify this.building=building;
		this.buildingColor=buildingColor;
		if(this.buildingColor.color().equals(Colors.NONE))
			this.building=false;
		else
			this.building=true;
			

		this.demon=demon;
		this.troll=troll;
		
		
		minions= new ArrayList<Piece>();
		
	}
	
	
	
	
	/**
	 * Checks if the color of a player controls the area.Returns true if it does otherwise it returns false.
	 * @param color(Colors)
	 * @return boolean
	 */
	public boolean controlsArea(Colors color)
	{
		//Map will contain the number of minions from each player currently on this area.
		Map<Colors,Integer> playerMinions=new HashMap<Colors,Integer>();
		//All possible players.
		playerMinions.put(Colors.RED, 0);
		playerMinions.put(Colors.BLUE, 0);
		playerMinions.put(Colors.YELLOW, 0);
		playerMinions.put(Colors.GREEN, 0);
		
		Colors currentColor= Colors.NONE;
		for(int i=0;i<this.minions.size();i++)
		{
			currentColor=this.minions.get(i).getColor();
			playerMinions.put(currentColor,playerMinions.get(currentColor)+1);
		}
		
		if(this.building)//if true:a building exists
			playerMinions.put(buildingColor, playerMinions.get(buildingColor)+1);
		
		//Get the entry with the maximum value in the HashMap.				
		Entry<Colors,Integer> maxEntry = null;
		for(Entry<Colors,Integer> entry : playerMinions.entrySet()) {
		    if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
		        maxEntry = entry;
		    }
		}
		
		//check if there's a player with the same number of playing pieces.
		playerMinions.remove(maxEntry.getKey());
		
		Entry<Colors,Integer> maxEntry2 = null;
		for(Entry<Colors,Integer> entry : playerMinions.entrySet()) {
		    if (maxEntry2 == null || entry.getValue() > maxEntry2.getValue()) {
		        maxEntry2 = entry;
		    }
		}
		//If two or more players have the same number of playing pieces then no one controls the area. Or if there is an equal or greater number of trolls 
		//than player pieces in the current area then there's no control of the area.
		if(maxEntry.getValue() == maxEntry2.getValue() || maxEntry.getValue()< this.demon) 
		{
			return false;
		}
		//Check if the color of the player being checked is the same as the one that has the most playing cards on the area.
		if(maxEntry.getKey()==color)
			return true;
		else
			return false;
		
	}
	

	/**
	 * Getter
	 * @return CityCard
	 */
	public CityCard getCityCard()
	{
		return this.cityCard;
	}
	/**
	 * Getter
	 * @return boolean
	 */
	public boolean getTroubleMarker()
	{
		return this.troubleMarker;
	}
	/**
	 * Getter
	 * @return boolean
	 */
	public boolean getBuilding()
	{
		return this.building;
	}
	/**
	 * Getter
	 * @return int
	 */
	public int getDemon()
	{
		return this.demon;
	}
	/**
	 * Getter
	 * @return int
	 */
	public int getTroll()
	{
		return this.troll;
	}
	/**
	 * Getter
	 * @return ArrayList<Piece>
	 */
	public ArrayList<Piece> getMinions()
	{
		return this.minions;
	}

	/**
	 * Getter
	 * @return Colors
	 */
	public Colors getBuildingColor()
	{
		return this.buildingColor;
	}

	/**
	 * 
	 * toString method for Area Class.
	 */
	@Override
	public String toString()
	{
		String info="Area Name: "+ this.cityCard.getName()+" ." + " Trouble Marker: "+this.troubleMarker+". Building: "+this.building+" .";
		info= info+ " Demons: "+this.demon+ ". Trolls: "+this.troll +" .\n";
		String info2="Minions in current area: \n";
		
		for(int i=0;i<this.minions.size();i++)
		{
			info2=info2+ this.minions.get(i).toString()+", ";
		}
		info2=info2+"\n";
		return info+info2;
	}
	

	/**
	 * Add a minion to area; if the area already contains one or more minions add a trouble marker.
	 * @param minion
	 */
	public void addMinion(Piece minion)
	{
		if(this.getMinions().size()>=1 || this.getDemon()>=1 || this.getTroll()>=1)//There is already at least one minion in the area or at least one demon or at least one troll.
			this.troubleMarker=true;//add a trouble marker.
		
		this.minions.add(minion);
		
	}
	
	//TODO: Check if we can remove the trouble marker from here as well.
	/**
	 * Add a minion to area. Returns true if successful otherwise it returns false.
	 * @param minion
	 * @return boolean
	 */
	public Piece removeMinion(Colors color)
	{
		//this.minions.remove(minion);
		for(int i=0;i<minions.size();i++)
		{
			if(minions.get(i).getColor() == color)
				return this.minions.remove(i);	
		}
		
		return null;
		
	}
	
	/**
	 * Add troubleMarker to area if possible(Only one trouble marker is allowed per area). Return true if successful else return false.
	 * @return boolean
	 */
	public boolean addTroubleMarker(){
		if(this.troubleMarker==false ){
			//There's no trouble marker on this area.
			this.troubleMarker=true;
			return true;
		}else{
			//A trouble marker already exists on this area.
			return false;
		}
	}
	

	/**
	 * remove troubleMarker to area if possible(Only one trouble marker is allowed per area). Return true if successful else return false.
	 * @return boolean
	 */
	public boolean removeTroubleMarker()
	{
		if(this.troubleMarker==true )//There's a trouble marker on this area.
		{
			this.troubleMarker=false;
			return true;
		}else
		{//A trouble marker already exists on this area.
			return false;
		}
	}
	

	/**
	 * Add Building to area if possible(Only one Bulding  is allowed per area). Also set the color of the building.
	 * @return boolean
	 */
	public boolean addBuilding(Player player)
	{
		if(this.building==false)//There's no trouble marker on this area.
		{
			this.building=true;
			this.buildingColor=player.getColor();
			return true;
		}else
		{//A trouble marker already exists on this area.
				return false;
		}
	}
	

	/**
	 * Add Building to area if possible(Only one Building is allowed per area).Also remove color from building.
	 *  Return true if successful else return false.
	 * @return boolean
	 */
	public boolean removeBuilding()
	{
		if(this.building==true)//There's no trouble marker on this area.
		{
			this.building=false;
			this.buildingColor=Colors.NONE;
			return true;
		}else
		{//A trouble marker already exists on this area.
				return false;
		}
	}
	

	/**
	 * Add or remove demon to area. If the argument is 1 then it will add a demon else if it is 2 it will remove a demon if possible.
	 * Will return a boolean depending on weather the action was successful.further it will add a trouble marker even if there are no minions or trolls
	 * in the area.
	 * @param addRemove
	 * @return boolean
	 */
	public boolean addRemoveDemon(int addRemove)
	{
		if(addRemove==1)//add demon
		{
			this.demon++;
			boolean check=this.addTroubleMarker();
			if(check)
				System.out.println("Added a trouble marker.");
			return true;
		}else if (addRemove==2)//remove demon if possible
		{
			if(this.demon >0)
			{
				this.demon--;
				return true;
			}else
				return false;
		}
		return false;
	}
	

	/**
	 * Add or remove troll to area. If the argument is 1 then it will add a troll else if it is 2 it will remove a troll if possible.
	 * Will return a boolean depending on weather the action was successful. further it will add a trouble marker if there's already a 
	 * minion,demon or another troll in the area.
	 * @param addRemove
	 * @return boolean
	 */
	public boolean addRemoveTroll(int addRemove){
		if( addRemove == 1 ){
			//add troll
			this.troll++;
			//Add trouble marker of applicable.
			if(this.demon>0 || this.minions.size()>0 || this.troll>0)
			{
				boolean check=this.addTroubleMarker();
				if(check)
					System.out.println("Added a trouble marker.");
			}
			return true;
			//remove troll if possible
		}else if ( addRemove == 2 ){
			if( this.troll > 0 ){
				this.troll--;
				return true;
			}else{
				return false;
			} 
		}
		return false;
	}

}
