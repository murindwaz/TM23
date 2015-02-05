package ca.concordia.game.model;

public class PersonalityCard extends Card {
	public String winningCondition = null;
	
	public PersonalityCard(int i) {
		//Personality Cards are not Playable or Visible!
		super(false,false);
		switch(i) {
			case 0:
				this.setName("Lord Vetinari");
				this.winningCondition = "If at the start of your turn you have minions in a certain number of areas then you win the game immediately.";
				this.winningCondition += "With two players you need 11 areas, with three players you need 10 areas, and with four players you need 9 areas.";
				break;
			case 1:
				this.setName("Lord Selachii");
				break;
			case 2:
				this.setName("Dragon King of Arms");
				this.winningCondition = "If at the start of your turn there are 8 or more trouble markers on the board then you win the game immediately";
				break;
			case 3:
				this.setName("Lord Rust");
				break;
			case 4:
				this.setName("Commander Vimes");
				break;
			case 5:
				this.setName("Lord de Worde");
				break;
			case 6:
				this.setName("Chrysoprase");
				break;
			default:
				System.out.println("Initializing Personality Card with the wrong index");
				break;
		}
	}
}
