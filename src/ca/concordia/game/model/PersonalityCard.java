package ca.concordia.game.model;

/**
 *PersonalityCard is a card that contains seven possible personalities that players could get. 
 *Each PersonalityCard has a specific winning condition for each personality it represents.
 *
 *
 *@author Pascal Maniraho 
 *@author Gustavo Pereira
 *@author Bhavik Desai 
 *@author Jesus Esteban Garro Matamoros 
 *@author Diego Pizarro
 */

public class PersonalityCard extends Card {
	
	public String winningCondition = null;
	public String winningConditionDescription = null;
	private int cardId;
	private int numberOfPlayers;
	private boolean numPlayActive;
	private int numMinionsOnAreas;
	private int controlAreas;
	private int numTroubleMarkers;
	private int netWorth;
	private int drawPile;
	/**
	 * Constructor deending on the integer sent it creates one of the seven different personality cards.
	 * @param int i - Lord Identifier 
	 */
	public PersonalityCard(int i,int numberOfPlayers) {
		//Personality Cards are not Playable or Visible!
		super(false,false);
		
		this.numberOfPlayers=numberOfPlayers;//set number of players for winning conditions.
		//not all winning conditions require the same variables.
		this.numPlayActive=false;
		this.numMinionsOnAreas=-1; 
		this.controlAreas=-1;
		this.numTroubleMarkers=-1;
		this.netWorth=-1;
		this.drawPile=-1;
		
		switch(i) {
			case 0:
				this.setName("Lord Vetinari");
				this.cardId=1;
				this.winningConditionDescription = "Has minions on different areas that don't have demons on them.2 players-11 areas,3 players-10 areas,4 players-9 areas";
				this.numPlayActive=true;
				//Set winning condition depending on number of players.
				if(numberOfPlayers ==2)
					this.numMinionsOnAreas=11;
				else if(numberOfPlayers ==3)
					this.numMinionsOnAreas=10;
				else if(numberOfPlayers ==4)
					this.numMinionsOnAreas=9;
				
				break;
			case 1:
				this.setName("Lord Selachii");
				this.cardId=2;
				this.numPlayActive=true;
				//Set winning condition depending on number of players.
				if(numberOfPlayers ==2)
					this.controlAreas=7;
				else if(numberOfPlayers ==3)
					this.controlAreas=5;
				else if(numberOfPlayers ==4)
					this.controlAreas=4;
				
				break;
			case 2:
				this.setName("Dragon King of Arms");
				this.cardId=3;
				this.winningConditionDescription = "If at the start of your turn there are 8 or more trouble markers on the board then you win the game immediately";
				this.numTroubleMarkers=8;
				break;
			case 3:
				this.setName("Lord Rust");
				this.cardId=4;
				this.numPlayActive=true;
				//Set winning condition depending on number of players.
				if(numberOfPlayers ==2)
					this.numMinionsOnAreas=11;
				else if(numberOfPlayers ==3)
					this.numMinionsOnAreas=10;
				else if(numberOfPlayers ==4)
					this.numMinionsOnAreas=9;
				
				break;
			case 4:
				this.setName("Commander Vimes");
				this.cardId=5;
				this.winningConditionDescription="Draw Pile Exhausted.";
				this.drawPile=0;
				break;
			case 5:
				this.setName("Lord de Worde");
				this.cardId=6;
				this.numPlayActive=true;
				//Set winning condition depending on number of players.
				if(numberOfPlayers ==2)
					this.numMinionsOnAreas=11;
				else if(numberOfPlayers ==3)
					this.numMinionsOnAreas=10;
				else if(numberOfPlayers ==4)
					this.numMinionsOnAreas=9;
				
				break;
			case 6:
				this.setName("Chrysoprase");
				this.cardId=7;
				this.netWorth=50;
				break;
			default:
				System.out.println("Initializing Personality Card with the wrong index");
				break;
		}
	}
	

	/**
	 * Constructor when loading a game state.
	 * @param name
	 */
	public PersonalityCard(String name)
	{
		//Personality Cards are not Playable or Visible!
		super(false,false);
		
		this.setName(name);
	}
	
	/**
	 * Getter : returns the number of trouble markers in the board required to win a game.
	 * @return int
	 */
	public int getNumTroubleMarkers()
	{
		return this.numTroubleMarkers;
	}
	
	/**
	 * Getter: returns the number of controlled areas required to win the game.
	 * @return int
	 */
	public int getControlledAreas()
	{
		return this.controlAreas;
	}
	
	/**
	 * Getter: returns the number of areas with a minion that are required to win.
	 * @return int
	 */
	public int getNumMinionsOnAreas()
	{
		return this.numMinionsOnAreas;
	}
	
	/**
	 * Getter: cardID
	 * @return int
	 */
	public int getCardId()
	{
		return this.cardId;
	}
	
	
}
