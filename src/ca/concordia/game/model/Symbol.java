package ca.concordia.game.model;

public class Symbol {
	private int Id;
	private boolean mandatory;
	private String description;
	private boolean inTurn;
	
	public Symbol(int Id)
	{
		this.Id=Id;
		this.mandatory=false;
		this.inTurn=true;
		performSymbol(this.Id);
	}
	
	public boolean performSymbol(int Id)
	{
		switch(Id){
			case 1:
				this.description="Place a minion only in adjacent areas.";		
				break;
			case 2:
				this.description="Place a building.";	
				break;
			case 3:
				this.description="Assassination.";	
				break;
			case 4:
				this.description="Remove one trouble marker.";	
				break;
			case 5:
				this.description="Take money ( X = Amount )";	
				break;
			case 6:
				this.description="Scroll(Perform the action described in the text at the bottom of the card.).";	
				break;
			case 7:
				this.description="Random Event.";	
				this.mandatory=true;
				break;
			case 8:
				this.description="Play another card.";	
				break;
			case 9:
				this.description="Interrupt.";
				this.inTurn=false;
				break;
			default:
				System.out.println("The symbol Id is incorrect.")
				break;
		}
		return true;
	}
}
