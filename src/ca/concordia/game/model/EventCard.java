package ca.concordia.game.model;

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
				this.setName( Configuration.EVENT_FOG_NAME );
				this.instruction = Configuration.EVENT_FOG_DESCRIPTION;
				break;
			case Configuration.EVENT_MYSTERIOUS_MURDERS:
				this.eventCardId=Configuration.EVENT_MYSTERIOUS_MURDERS;
				this.setName( Configuration.EVENT_MYSTERIOUS_MURDERS_NAME );
				this.instruction = Configuration.EVENT_MYSTERIOUS_MURDERS_DESCRIPTION;
				break;
			case Configuration.EVENT_RIOTS:
				this.eventCardId=Configuration.EVENT_RIOTS;
				this.setName( Configuration.EVENT_RIOTS_NAME );
				this.instruction = Configuration.EVENT_RIOTS_DESCRIPTION;
				break;
			case Configuration.EVENT_SUBSIDENCE:
				this.eventCardId=Configuration.EVENT_SUBSIDENCE;
				this.setName( Configuration.EVENT_SUBSIDENCE_NAME );
				this.instruction = Configuration.EVENT_SUBSIDENCE_DESCRIPTION;
				break;
			case Configuration.EVENT_TROLLS:
				this.eventCardId=Configuration.EVENT_TROLLS;
				this.setName( Configuration.EVENT_TROLLS_NAME );
				this.instruction = Configuration.EVENT_TROLLS_DESCRIPTION;
				break;
			case Configuration.EVENT_BLOODY_STUPID_JOHNSON:
				this.eventCardId=Configuration.EVENT_BLOODY_STUPID_JOHNSON;
				this.setName( Configuration.EVENT_BLOODY_STUPID_JOHNSON_NAME );
				this.instruction = Configuration.EVENT_BLOODY_STUPID_JOHNSON_DESCRIPTION; 
				break;
			case Configuration.EVENT_DEMONS_FROM_THE_DUNGEON_DIMENSIONS:
				this.eventCardId=Configuration.EVENT_DEMONS_FROM_THE_DUNGEON_DIMENSIONS;
				this.setName( Configuration.EVENT_DEMONS_FROM_THE_DUNGEON_DIMENSIONS_NAME );
				this.instruction = Configuration.EVENT_BLOODY_STUPID_JOHNSON_DESCRIPTION; 
				break;
			case Configuration.EVENT_DRAGON:
				this.eventCardId=Configuration.EVENT_DRAGON;
				this.setName( Configuration.EVENT_DRAGON_NAME );
				this.instruction = Configuration.EVENT_DRAGON_DESCRIPTION; 
				break;
			case Configuration.EVENT_EARTHQUAKE:
				this.eventCardId=Configuration.EVENT_EARTHQUAKE;
				this.setName( Configuration.EVENT_EARTHQUAKE_NAME );
				this.instruction = Configuration.EVENT_EARTHQUAKE_DESCRIPTION;
				break;
			case Configuration.EVENT_EXPLOSION:
				this.eventCardId=Configuration.EVENT_EXPLOSION;
				this.setName( Configuration.EVENT_EXPLOSION_NAME );
				this.instruction = Configuration.EVENT_EXPLOSION_DESCRIPTION;
				break;
			case Configuration.EVENT_FLOOD:
				this.eventCardId=Configuration.EVENT_FLOOD;
				this.setName( Configuration.EVENT_FLOOD_NAME );
				this.instruction = Configuration.EVENT_FLOOD_DESCRIPTION; 
				break;
			case Configuration.EVENT_FIRE:
				this.eventCardId=Configuration.EVENT_FIRE;
				this.setName( Configuration.EVENT_FIRE_NAME );
				this.instruction = Configuration.EVENT_FIRE_DESCRIPTION; 
				break;
			default:
				System.out.println( "Initializing City Card with the wrong index" );
			break;
		}
	}
	
	public void useEventCard(Player currentPlayer,Game game, int cardID)
	{
		switch(this.eventCardId) {
			case 0:
				fog(currentPlayer,game);
				break;
			case 1:
				misteriousMurders(currentPlayer,game);
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				
				break;				
			case 5:
			new Action(cardID);
				break;
			case 6:
				//Random event.
			
				break;
			case 7:
				//Play another card.
				
				break;
			case 8:
				//Interrupt card.
				
				break;
			case 9:
				//Interrupt card.
				
				break;
			case 10:
				//Interrupt card.
				
				break;
			case 11:
				//Interrupt card.
				
				break;
			case 12:
				//Interrupt card.
				
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
	public void fog(Player currentPlayer,Game game)
	{
		Deck deck;
		GreenCard gCard;
		BrownCard bCard;
		for(int i=0;i<5;i++)
		{
			
			//Get the green deck or brown deck depending which one is in use.
			if(game.getEspecificDeck("G").getSizeDeck()>0)
			{
				//Green deck is not empty yet.
				deck=game.getEspecificDeck("G");
				gCard=(GreenCard) deck.getCard();
				//Display the card that's being discarded.
				gCard.toString();
			
			}else if(game.getEspecificDeck("B").getSizeDeck()>0)
			{
				//Brown deck is not empty yet but green deck is.
				deck=game.getEspecificDeck("B");
				bCard=(BrownCard) deck.getCard();
				//Display the card that's being discarded.
				bCard.toString();
			}
		}
	}
	
	public void misteriousMurders(Player currentPlayer,Game game)
	{
		
	}
	
	
	/**
	 * Setter: set name for event card.
	 */
	public void setName(String name)
	{
		this.eventCardName=name;
	}
}
