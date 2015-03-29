package ca.concordia.game.util;

import java.util.Properties;

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
	
	/**
	 * These properties were loaded via CityAreas.txt
	 * CityCardProperties stores card Properties in a parsable format 
	 */
	Properties cityCardProperties = new Properties(){
		private static final long serialVersionUID = 2919423468649206636L;
			{
				put("Dolly Sisters", "1|Dolly Sisters|6| Once per turn you can pay $3 and place one of your minions in Dolly Sisters or an adjacent area."); 
				put("Unreal Estate", "2|Unreal Estate|18|At any point in your turn you can take one card from the deck and then discard one."); 
				put("Dragon's Landing", "3|Dragon's Landing|12|Once per turn you can take $2 from the bank."); 
				put("Small Gods", "4|Small Gods|18|Whenever one of your pieces is affected by a random event you can pay $3 to ignore it."); 
				put("The Scours", "5|The Scours|6|Once per turn you can discard one card and take $2 from the bank."); 
				put("The Hippo", "6|The Hippo|12|Once per turn you can take $2 from the bank."); 
				put("The Shades", "7|The Shades|6|Once per turn you can place one trouble marker in the Shades or an adjacent area."); 
				put("Dimwell", "8|Dimwell|6|Once per turn you can pay $3 and place one of your minions in Dimwell or an adjacent area."); 
				put("Longwall", "9|Longwall|12|Once per turn you can take $1 from the bank."); 
				put("Isle of Gods", "10|Isle of Gods|12|Once per turn you can pay $2 and remove one trouble marker from the board."); 
				put("Seven Sleepers", "11|Seven Sleepers|18|Once per turn you can take $3 from the bank."); 
				put("Nap Hill", "12|Nap Hill|12|Once per turn you can take $1 from the bank."); 
			}
		};
		
		/**
		 * These properties were loaded via Personality.txt file 
		 */
		Properties personalitiesProperties = new Properties(){
			private static final long serialVersionUID = 109935086079578524L;{
				put("Lord de Worde","1|Lord de Worde|Control If at the start of your turn you have clear control of a certain number of areas then  you win the game immediately. With two players you need to control seven areas, with three players you need to control five  areas, and with four players you need to control four areas.");
				put("Commander Vimes","2|Commander Vimes|If the game ends due to the cards running out then you win the game.");
				put("Lord Vetinari","3|Lord Vetinari|If at the start of your turn you have minions in a certain number of areas then you win  the game immediately. With two players you need eleven areas, with three players you need ten areas, and with four players you need nine areas.");
				put("Lord Selachii","4|Lord Selachii|If at the start of your turn you have clear control of a certain number of areas then  you win the game immediately. With two players you need to control seven areas, with three players you need to control five areas, and with four players you need to control four areas.");
				put("Lord Rust","5|Lord Rust|If at the start of your turn you have clear control of a certain number of areas then you win the game immediately.  With two players you need to control seven areas, with three players you need to control five areas, and with four players you need to control four areas.");
				put("Dragon King of Arms","6|Dragon King of Arms| If at the start of your turn there are eight or more trouble markers on the board then you win the game immediately.");
				put("Chrysoprase","7|Chrysoprase| If at the start of your turn you have a combined worth of $50 or more (money in hand plus buildings you have built), then you win the game. Each loan you have counts as $ 12 against your total.");
			}
		};

		
		/**
		 * The players action properties were initially loaded from Players_Actions.txt file. 
		 */
		Properties playersActionsProperties = new Properties(){
			private static final long serialVersionUID = 9048814407777279880L;{
			put("Place a minion","1|Place a minion|1");
			put("Place a building","2|Place a building|1");
			put("Assassination","3|Assassination|1");
			put("Remove one trouble marker","4|Remove one trouble marker|1");
			put("Take money","5X|Take money ( X = Amount )|1");
			put("Scroll","6|Scroll|1|Perform the action described in the text at the bottom of the card.");
			put("Random Event","7|Random Event ");
			put("Play another card","8|Play another card");
			put("Interrupt","9|Interrupt");
		}};
		
		/**
		 * These properties comes from Players_Events.txt
		 * Same events were used in RamonEvents.txt though
		 */
		Properties playersEventsProperties = new Properties(){
			private static final long serialVersionUID = -1576596337814074624L;{
			put("Fire","1|Fire| Roll the die to see where it starts. You then continue rolling to see if it spreads (see rules)");
			put("Fog","2|Fog|Draw and discard the top five cards from the deck.");
			put("Mysterious Murders","3|Mysterious Murders|Each player takes it in turn to roll the die and remove one minion from the area rolled, even if it does not contain a trouble marker.");
			put("Riots","4|Riots|If there are eight or more trouble markers on the board then the game ends immediately.");
			put("Subsidence","5|Subsidence|All players must pay $2 for each building they have or remove it instead.");
			put("Trolls","6|Trolls|Roll die three times and place one troll minion piece in each area.");
			put("Bloody Stupid Johnson","7|Bloody Stupid Johnson|Roll the die. If the City Area card of the same value is in play then that card is placed to one side. The owner of the card must also remove one of his minions from the same area as the card.");
			put("Demons from the Dungeon Dimensions","8|Demons from the Dungeon Dimensions|Roll the die four times and place one demon minion piece in each area rolled. See rules for effect.");
			put("The Dragon","9|The Dragon|Roll the die to see where it lands. Remove all pieces from the area.");
			put("Earthquake","10|Earthquake|Roll the die twice and remove any buildings in those areas.");
			put("Explosion","11|Explosion|Roll one die and remove any building in that area.");
			put("Flood","12|Flood|Roll the die twice to see which areas are affected. If the area rolled is adjacent to the river then players must move their minions to adjacent areas. Trolls and demons remain in the area.");
			
		}};
		
		/**
		 * These properties were loaded via Players.txt file. 
		 */
		public static final Properties PLAYER_PROPERTIES = new Properties(){
			private static final long serialVersionUID = 109935086079578524L;{
			put("Sergeant Cheery Littlebottom","1|B|Sergeant Cheery Littlebottom|2|64|Take two cards from the draw deck.");
			put("Otto Chriek","2|B|Otto Chriek|2|62|Earn $1 for each trouble marker on the board.");
			put("The Clacks","3|B|The Clacks|3|6M28|Take two cards from the draw deck.");
			put("Sergeant Colon","4|B|Sergeant Colon|2|41|");
			put("Cosmo Lavish","5|B|Cosmo Lavish |2|68|Pay another player $2. They must then remove one minion of their choice (not one of yours) from an area with a trouble marker in it.");
			put("The Dean","6|B|The Dean |3|768|Remove one minion from Unreal Estate.");
			put("HELLO","7|B|HELLO|3|332|");
			put("Burleigh & Stronginthearm","8|B|Burleigh & Stronginthearm |2|64|Pay a player of your choice $2. Then choose a minion to assassinate.");
			put("The Bursar","9|B|The Bursar|3|768|Exchange the positions of any two minions on the board.");
			put("Cable Street Particulars","10|B|Cable Street Particulars|2|61|Select one player. Look at his cards. Choose one of them to be discarded.");
			put("Canting Crew","11|B|Canting Crew |2|61|Move a minion belonging to another player from one area to an adjacent area.");
			put("Carcer","12|B|Carcer|2|68|Roll the die twice and remove one minion of your choice from those areas, even if there is no trouble there.");
			put("The Chair of Indefinite Studies","13|B|The Chair of Indefinite Studies |3|768|Exchange your hand with that of another player.");
			put("Sir Charles Lavatory","14|B|Sir Charles Lavatory|2|62|Earn $1 for each building on the board (any colour). ");
			put("Dorfl","15|B|Dorfl|2|68|Move one of your minions from one area to any other area on the board.");
			put("Sergeant Detritus","16|B|Sergeant Detritus|2|44|");
			put("Deep Dwarves","17|B|Deep Dwarves|2|68|Place a minion in any area and do not place a trouble marker.");
			put("Adora Belle Dearheart","18|B|Adora Belle Dearheart|3|612|Move one of your minions from one area to any other area on the board.");
			put("The Alchemists' Guild","19|B|The Alchemists' Guild|2|62|Discard up to three cards and refill your hand to five cards.");
			put("The Auditors","20|B|The Auditors|1|6|Every other player, in player order, must remove one of their minions from the board.");
			put("Buggy Swires","21|B|Buggy Swires|1|4|");
			put("Susan","22|B|Susan|1|9|Stop one of your minions from being removed from the board.");
			put("Sybil Vimes","23|B|Sybil Vimes|2|M36|Replace another player's building with one of your own. Pay the cost of the building to the original owner. It must be an area that does not have a trouble marker.");
			put("Mr Teatime","24|B|Mr Teatime|3|M338|");
			put("The Watch","25|B|The Watch|2|24|");
			put("Wee Mad Arthur","26|B|Wee Mad Arthur|1|6|You may build a building for half price.");
			put("William de Worde","27|B|William de Worde|2|16|Earn $1 for each trouble marker on the board.");
			put("Willikins","28|B|Willikins|1|6|Place one minion in an area that you have a building in.");
			put("Archchancellor Ridcully","29|B|Archchancellor Ridcully|2|76|Place one or two minions in or adjacent to Unreal Estate.");
			put("Ruby","30|B|Ruby|2|12|");
			put("The Senior Wrangler","31|B|The Senior Wrangler|3|768|Place one minion in or adjacent to Unreal Estate.");
			put("Mr Shine","32|B|Mr Shine|1|6|Place a minion in any area and do not place a trouble marker.");
			put("Mr Slant","33|B|Mr Slant|2|62|Choose an area containing a trouble marker and receive $2 for each minion there.");
			put("The Smoking Gnu","34|B|The Smoking Gnu|2|68|Place one minion in an area containing a trouble marker.");
			put("Stanley","35|B|Stanley|2|61|Select two cards randomly from one player and choose one to keep. Return the other card.");
			put("Moist von Lipwig","36|B|Moist von Lipwig|4|1M368|Take two cards from the draw deck.");
			put("Doctor Mossy Lawn","37|B|Doctor Mossy Lawn|1|9|Interrupt your own turn. Your turn finishes but do not discard the card you played before this one.");
			put("Patrician's Palace","38|B|Patrician's Palace|3|2M41|");
			put("Ponder Stibbons","39|B|Ponder Stibbons |2|76|Play any two other cards from your hand.");
			put("The Post Office","40|B|The Post Office |2|61|Take $1 for each building on the board.");
			put("Reacher Gilt","41|B|Reacher Gilt |1|6|You can replace another player's building with one of your own. Pay the cost of the building to the original owner. It must be an area that has a trouble marker in it.");
			put("Professor of Recent Runes","42|B|Professor of Recent Runes|3|768|Take two cards from the draw deck.");
			put("Doctor Hix","43|B|Doctor Hix|3|768|Place a trouble marker in an area of your choice.");
			put("Hobsons's Livery Stable","44|B|Hobsons's Livery Stable |2|62|Pay $2 to a player of your choice. Then move one of your minions to any area you wish.");
			put("Hubert","45|B|Hubert|2|61| Force one player to give another player $3 (you cannot choose yourself).");
			put("Igor","46|B|Igor|1|9|If you have a minion removed you can place him in a different area.");
			put("The Luggage","47|B|The Luggage|2|46| Discard one card.");
			put("The Mob","48|B|The Mob |3|618| Place one trouble marker in an area adjacent to one already containing a trouble marker.");
			put("Lord Downey","49|B|Lord Downey|3|4M32|");
			put("Dwarves","50|B|Dwarves|2|11|");
			put("Edward d'Eath","51|B|Edward d'Eath|3|4M32|");
			put("Errol","52|B|Errol|2|68| Roll the die. On a roll of '7' or more you remove a minion of your choice from an area containing a trouble marker. On a roll of a '1' you must remove one of your own minions.");
			put("Gargoyles","53|B|Gargoyles|2|62|Draw one card for each building you have on the board.");
			put("Mr Boggis","54|G|Mr Boggis|2|61|Take $2, if possible, from every other player.");
			put("Mr Bent","55|G|Mr Bent|2|68|Place this card in front of you and take a loan of $10 from the bank. At the end of the game you must pay back $12 or lose 15 points.");
			put("The Beggars' Guild","56|G|The Beggars' Guild|2|61| Select one player. They must give you two cards of their choice.");
			put("The Bank of Ankh-Morpork","57|G|The Bank of Ankh-Morpork|2|68|Place this card in front of you and take a loan of $10 from the bank. At the end of the game you must pay back $12 or lose 15 points.");
			put("The Ankh Morpork Sunshine Dragon Sanctuary","58|G|The Ankh Morpork Sunshine Dragon Sanctuary |2|68|Each player must give you either $1 or one of their cards.");
			put("Sergeant Angua","59|G|Sergeant Angua|2|48|");
			put("The Agony Aunts","60|G|The Agony Aunts|3|3M21|");
			put("The Dysk","61|G|The Dysk|2|26|Earn $1 for each minion in The Isle of Gods.");
			put("The Duckman","62|G|The Duckman|1|6|Move a minion belonging to another player from one area to an adjacent area.");
			put("Drumknott","63|G|Drumknott|1|6|Play any two other cards from your hand.");
			put("CMOT Dibbler","64|G|CMOT Dibbler|2|68|Roll the die. On a roll of '7' or more you take $4 from the bank. On a roll of' l' you must pay $2 to the bank or remove one of your minions from the board. All other results have no effect.");
			put("Dr Cruces","65|G|Dr Cruces|2|3M3|");
			put("Captain Carrot","66|G|Captain Carrot|3|14M1|");
			put("Mrs Cake","67|G|Mrs Cake|2|6M22|Look at all but one of the unused Personality cards.");
			put("Groat","68|G|Groat|1|1|");
			put("Gimlet's Dwarf Delicatessen","69|G|Gimlet's Dwarf Delicatessen |2|M31|");
			put("Gaspode","70|G|Gaspode|1|9|Stop a player from moving or removing one of your minions.");
			put("Fresh Start Club","71|G|Fresh Start Club|1|9|If you have a minion removed you can place him in a different area.");
			put("Foul Ole Ron","72|G|Foul Ole Ron|2|68|Move a minion belonging to another player from one area to an adjacent area.");
			put("The Fools' Guild","73|G|The Fools' Guild|2|61|Select another player. If they do not give you $5 then place this card in front of them. This card now counts towards their hand size of five cards when they come to refill their hand. They cannot get rid of this card.");
			put("The Fire Brigade","74|G|The Fire Brigade|2|68|Choose a player. If he does not pay you $5 then you can remove one of his buildings from the board.");
			put("Inigo Skimmer","75|G|Inigo Skimmer|2|3M2|");
			put("History Monks","76|G|History Monks|2|61|Shuffle the discard pile and draw four cards randomly. Place the remaining cards back as the discard pile.");
			put("Hex","77|G|Hex|2|62|Take three cards from the draw deck.");
			put("Here'n'Now","78|G|Here'n'Now|2|68|Roll the die. On a roll of '7' or more you take $3 from a player of your choice. On a roll of a '1' you must remove one of your minions from the board. All other results have no effect.");
			put("Harry King","79|G|Harry King|2|16|Discard as many cards as you wish and take $2 for each one discarded.");
			put("Harga's House of Ribs","80|G|Harga's House of Ribs|2|M31|");
			put("Mr Gryle","81|G|Mr Gryle|2|3M1|");
			put("The Peeled Nuts","82|G|The Peeled Nuts|0||");
			put("The Opera House","83|G|The Opera House|2|26|Earn $1 for each minion in The Isle of Gods.");
			put("Nobby Nobbs","84|G|Nobby Nobbs|2|68|Take $3 from a player of your choice.");
			put("Modo","85|G|Modo|2|61|Discard one card.");
			put("The Mended Drum","86|G|The Mended Drum|2|2M2|");
			put("Librarian","87|G|Librarian|1|6|Take four cards from the draw deck.");
			put("Leonard of Quirm","88|G|Leonard of Quirm|1|6|Take four cards from the draw deck.");
			put("Shonky Shop","89|G|Shonky Shop|2|62|Discard as many cards as you wish and take $1 for each one discarded.");
			put("Sacharissa Cripslock","90|G|Sacharissa Cripslock|2|61|Earn $1 for each trouble marker on the board.");
			put("Rosie Palm","91|G|Rosie Palm|2|16|Choose one player. Give them one of your cards. They must give you $2 in return.");
			put("Rincewind","92|G|Rincewind|3|768|Move one of your minions from an area containing a trouble marker to an adjacent area.");
			put("The Royal Mint","93|G|The Royal Mint|2|2M5|");
			put("Queen Molly","94|G|Queen Molly|2|16|Select one player. They must give you two cards of their choice.");
			put("Pink PussyCat Club","95|G|Pink PussyCat Club|2|M38|");
			put("Zorgo the Retro-phrenologist ","96|G|Zorgo the Retro-phrenologist |2|62|You may exchange your Personality card with one drawn randomly from those not in use.");
			put("Dr Whiteface","97|G|Dr Whiteface|2|61| Select another player. If they do not give you $5 then place this card in front of them. This card now counts towards their hand size of five cards when they come to refill their hand. They cannot get rid of this card.");
			put("Wallace Sonky","98|G|Wallace Sonky|1|9| You cannot be affected by the text on a card played by another player.");
			put("The Seamstresses' Guild","99|G|The Seamstresses' Guild |2|61| Choose one player. Give them one of your cards. They must give you $2 in return.");
			put("Mr Pin & Mr Tulip","100|G|Mr Pin & Mr Tulip |2|3M1|The New Firm.");
			put("The Thieves' Guild","101|G|The Thieves' Guild|2|61| Take $2, if possible, from every other player.");
		}};
		
}