package ca.concordia.game.model;

import javax.swing.ImageIcon;

//Cards are immutable: In the game, there are exactly 132 cards, each one of them different and immutable
//Each card belongs to a specific Deck:
public class Card {

	public boolean isPlayable;
	public boolean isVisible;
	
	private String name;
	private ImageIcon icon;
	
	public String getName() {
		return this.name;
	}
	public void setName(String aName) {
		this.name = aName;
	}
	public ImageIcon getIcon() {
		return this.icon;
	}
	public void setIcon(ImageIcon anIcon) {
		this.icon = anIcon;
	}

	public Card(boolean visible, boolean playable) {
		this.isVisible = visible;
		this.isPlayable = playable;
		this.name = null;
		this.icon = null;
	}
}
