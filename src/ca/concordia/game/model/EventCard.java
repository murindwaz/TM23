package ca.concordia.game.model;

/**
 * 
 * Event Card is a type of card it contains the Twelve different types of possible events the game offers.
 * *@author Pascal Maniraho 
 *@author Gustavo Pereira
 *@author Bhavik Desai 
 *@author Jesus Esteban Garro Matamoros 
 *@author Diego Pizarro

 */
public class EventCard extends Card {
	public String instruction = null;
	
	public EventCard(int i) {
		//City Cards are always Visible!
		super(false,false);
		switch(i) {
			case 0:
				this.setName("Fog");
				this.instruction = "Draw and discard the top five cards from the deck.";
				break;
			case 1:
				this.setName("Mysterious Murders");
				this.instruction = "Each player takes it in turn to roll the die and remove one minion from the area rolled, even if it does not contain a trouble marker.";
				break;
			case 2:
				this.setName("Riots");
				this.instruction = "If there are eight or more trouble markers on the board then the game ends immediately.";
				break;
			case 3:
				this.setName("Subsidence");
				this.instruction = "All players must pay $2 for each building they have or remove it instead.";
				break;
			case 4:
				this.setName("Trolls");
				this.instruction = "Roll die three times and place one troll minion piece in each area.";
				break;
			case 5:
				this.setName("Bloody Stupid Johnson");
				this.instruction = "Roll the die. If the City Area card of the same value is in play then that card is placed to one side. The owner of the card must also remove one of his minions from the same area as the card.";
				break;
			case 6:
				this.setName("Demons from the Dungeon Dimensions");
				this.instruction = "Roll the die four times and place one demon minion piece in each area rolled. See rules for effect.";
				break;
			case 7:
				this.setName("The Dragon");
				this.instruction = "Roll the die to see where it lands. Remove all pieces from the area.";
				break;
			case 8:
				this.setName("Earthquake");
				this.instruction = "Roll the die twice and remove any buildings in those areas.";
				break;
			case 9:
				this.setName("Explosion");
				this.instruction = "Roll one die and remove any building in that area.";
				break;
			case 10:
				this.setName("Flood");
				this.instruction = "Roll the die twice to see which areas are affected. If the area rolled is adjacent to the river then players must move their minions to adjacent areas. Trolls and demons remain in the area.";
				break;
			case 11:
				this.setName("Fire");
				this.instruction = "Roll the die to see where it starts. You then continue rolling to see if it spreads (see rules)";
				break;
			default:
				System.out.println("Initializing City Card with the wrong index");
				break;
		}
	}
}
