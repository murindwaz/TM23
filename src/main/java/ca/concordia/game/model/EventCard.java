package ca.concordia.game.model;

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
				break;
			case 2:
				this.setName("Riots");
				break;
			case 3:
				this.setName("Subsidence");
				break;
			case 4:
				this.setName("Trolls");
				break;
			case 5:
				this.setName("Bloody Stupid Johnson");
				break;
			case 6:
				this.setName("Demons from the Dungeon Dimensions");
				break;
			case 7:
				this.setName("The Dragon");
				break;
			case 8:
				this.setName("Earthquake");
				break;
			case 9:
				this.setName("Explosion");
				break;
			case 10:
				this.setName("Flood");
				break;
			case 11:
				this.setName("Fire");
				break;
			default:
				System.out.println("Initializing City Card with the wrong index");
				break;
		}
	}
}
