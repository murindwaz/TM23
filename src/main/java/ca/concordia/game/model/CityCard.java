package ca.concordia.game.model;

public class CityCard extends Card {
	public String ability = null;
	
	public CityCard(int i) {
		//City Cards are always Visible!
		super(true,false);
		switch(i) {
			case 0:
				this.setName("The Shades");
				this.ability = "Once per turn you can place one trouble marker in the Shades or an adjacent area.";
				break;
			case 1:
				this.setName("Dolly Sisters");
				this.ability = "Once per turn you can pay $3 and place one of your minions in Dolly Sisters or an adjacent area.";
				break;
			case 2:
				this.setName("The Scours");
				this.ability = "Once per turn you can discard one card and take $2 from the bank.";
				break;
			case 3:
				this.setName("Small Gods");
				break;
			case 4:
				this.setName("The Hippo");
				break;
			case 5:
				this.setName("Longwall");
				break;
			case 6:
				this.setName("Seven Sleepers");
				break;
			case 7:
				this.setName("Nap Hill");
				break;
			case 8:
				this.setName("Dimwell");
				break;
			case 9:
				this.setName("Isle of Gods");
				break;
			case 10:
				this.setName("Dragon's Landing");
				break;
			case 11:
				this.setName("Unreal Estate");
				break;
			default:
				System.out.println("Initializing City Card with the wrong index");
				break;
		}
	}
}
