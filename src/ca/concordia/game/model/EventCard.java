package ca.concordia.game.model;

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
	public String instruction = null;
	
	public EventCard(int i) {
		//City Cards are always Visible!
		super(false,false);
		switch(i) {
			case Configuration.EVENT_FOG:
				this.setName( Configuration.EVENT_FOG_NAME );
				this.instruction = Configuration.EVENT_FOG_DESCRIPTION;
				break;
			case Configuration.EVENT_MYSTERIOUS_MURDERS:
				this.setName( Configuration.EVENT_MYSTERIOUS_MURDERS_NAME );
				this.instruction = Configuration.EVENT_MYSTERIOUS_MURDERS_DESCRIPTION;
				break;
			case Configuration.EVENT_RIOTS:
				this.setName( Configuration.EVENT_RIOTS_NAME );
				this.instruction = Configuration.EVENT_RIOTS_DESCRIPTION;
				break;
			case Configuration.EVENT_SUBSIDENCE:
				this.setName( Configuration.EVENT_SUBSIDENCE_NAME );
				this.instruction = Configuration.EVENT_SUBSIDENCE_DESCRIPTION;
				break;
			case Configuration.EVENT_TROLLS:
				this.setName( Configuration.EVENT_TROLLS_NAME );
				this.instruction = Configuration.EVENT_TROLLS_DESCRIPTION;
				break;
			case Configuration.EVENT_BLOODY_STUPID_JOHNSON:
				this.setName( Configuration.EVENT_BLOODY_STUPID_JOHNSON_NAME );
				this.instruction = Configuration.EVENT_BLOODY_STUPID_JOHNSON_DESCRIPTION; 
				break;
			case Configuration.EVENT_DEMONS_FROM_THE_DUNGEON_DIMENSIONS:
				this.setName( Configuration.EVENT_DEMONS_FROM_THE_DUNGEON_DIMENSIONS_NAME );
				this.instruction = Configuration.EVENT_BLOODY_STUPID_JOHNSON_DESCRIPTION; 
				break;
			case Configuration.EVENT_DRAGON:
				this.setName( Configuration.EVENT_DRAGON_NAME );
				this.instruction = Configuration.EVENT_DRAGON_DESCRIPTION; 
				break;
			case Configuration.EVENT_EARTHQUAKE:
				this.setName( Configuration.EVENT_EARTHQUAKE_NAME );
				this.instruction = Configuration.EVENT_EARTHQUAKE_DESCRIPTION;
				break;
			case Configuration.EVENT_EXPLOSION:
				this.setName( Configuration.EVENT_EXPLOSION_NAME );
				this.instruction = Configuration.EVENT_EXPLOSION_DESCRIPTION;
				break;
			case Configuration.EVENT_FLOOD:
				this.setName( Configuration.EVENT_FLOOD_NAME );
				this.instruction = Configuration.EVENT_FLOOD_DESCRIPTION; 
				break;
			case Configuration.EVENT_FIRE:
				this.setName( Configuration.EVENT_FIRE_NAME );
				this.instruction = Configuration.EVENT_FIRE_DESCRIPTION; 
				break;
			default:
				System.out.println( "Initializing City Card with the wrong index" );
			break;
		}
	}
}
