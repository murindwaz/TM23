package ca.concordia.game.util;

/**
 * This class will be used for Configuration purposes.
 */
public class Configuration {

	public static String GAME = "Diskworld: Ankh-Morpork";
	public static String PATH = "diskworld";

	/**
	 * Areas
	 */

	public static String AREA_DOLLY_SISTERS = "Dolly Sisters";
	public static String AREA_UNREAL_ESTATE = "Unreal Estate";
	public static String AREA_DRAGON_S_LANDING = "Dragon's Landing";
	public static String AREA_SMALL_GODS = "Small Gods";
	public static String AREA_THE_SCOURS = "The Scours";
	public static String AREA_THE_HIPPO = "The Hippo";
	public static String AREA_THE_SHADES = "The Shades";
	public static String AREA_DIMWELL = "Dimwell";
	public static String AREA_LONGWALL = "Longwall";
	public static String AREA_ISLE_OF_GODS = "Isle of Gods";
	public static String AREA_SEVEN_SLEEPERS = "Seven Sleepers";
	public static String AREA_NAP_HILL = "Nap Hill";

	
	
	/**
	 * Following configuration goes to EventCard and EventCards usage. 
	 */
	public static final int EVENT_FOG = 0; 
	public static final String EVENT_FOG_NAME = "Fog"; 
	public static final String EVENT_FOG_DESCRIPTION = "Draw and discard the top five cards from the deck."; 
	public static final int EVENT_MYSTERIOUS_MURDERS = 1; 
	public static final String EVENT_MYSTERIOUS_MURDERS_NAME = "Mysterious Murders"; 
	public static final String EVENT_MYSTERIOUS_MURDERS_DESCRIPTION = "Each player takes it in turn to roll the die and remove one minion from the area rolled, even if it does not contain a trouble marker."; 
	public static final int EVENT_RIOTS = 2; 
	public static final String EVENT_RIOTS_NAME = "Riots"; 
	public static final String EVENT_RIOTS_DESCRIPTION = "If there are eight or more trouble markers on the board then the game ends immediately."; 
	public static final int EVENT_SUBSIDENCE = 3; 
	public static final String EVENT_SUBSIDENCE_NAME = "Subsidence"; 
	public static final String EVENT_SUBSIDENCE_DESCRIPTION = "All players must pay $2 for each building they have or remove it instead."; 
	public static final int EVENT_TROLLS = 4; 
	public static final String EVENT_TROLLS_NAME = "Trolls"; 
	public static final String EVENT_TROLLS_DESCRIPTION = "Roll die three times and place one troll minion piece in each area."; 
	public static final int EVENT_BLOODY_STUPID_JOHNSON = 5; 
	public static final String EVENT_BLOODY_STUPID_JOHNSON_NAME = "Bloody Stupid Johnson"; 
	public static final String EVENT_BLOODY_STUPID_JOHNSON_DESCRIPTION = "Roll the die. If the City Area card of the same value is in play then that card is placed to one side. The owner of the card must also remove one of his minions from the same area as the card."; 
	public static final int EVENT_DEMONS_FROM_THE_DUNGEON_DIMENSIONS = 6; 
	public static final String EVENT_DEMONS_FROM_THE_DUNGEON_DIMENSIONS_NAME = "Demons from the Dungeon Dimensions"; 
	public static final String EVENT_DEMONS_FROM_THE_DUNGEON_DIMENSIONS_DESCRIPTION = "Roll the die four times and place one demon minion piece in each area rolled. See rules for effect."; 
	public static final int EVENT_DRAGON = 7; 
	public static final String EVENT_DRAGON_NAME = "The Dragon"; 
	public static final String EVENT_DRAGON_DESCRIPTION = "Roll the die to see where it lands. Remove all pieces from the area."; 
	public static final int EVENT_EARTHQUAKE = 8; 
	public static final String EVENT_EARTHQUAKE_NAME = "Earthquake"; 
	public static final String EVENT_EARTHQUAKE_DESCRIPTION = "Roll the die twice and remove any buildings in those areas."; 
	public static final int EVENT_EXPLOSION = 9; 
	public static final String EVENT_EXPLOSION_NAME = "Explosion"; 
	public static final String EVENT_EXPLOSION_DESCRIPTION = "Roll one die and remove any building in that area."; 
	public static final int EVENT_FLOOD = 10; 
	public static final String EVENT_FLOOD_NAME = "Flood"; 
	public static final String EVENT_FLOOD_DESCRIPTION = "Roll the die twice to see which areas are affected. If the area rolled is adjacent to the river then players must move their minions to adjacent areas. Trolls and demons remain in the area."; 
	public static final int EVENT_FIRE = 11; 
	public static final String EVENT_FIRE_NAME = "Fire"; 
	public static final String EVENT_FIRE_DESCRIPTION = "Roll the die to see where it starts. You then continue rolling to see if it spreads (see rules)"; 
	
	
	
	/**
	 * The balance to be used at Game initialization
	 */
	public static int DEFAULT_BALANCE = 120;

}