package ca.concordia.game.model;

import javax.swing.ImageIcon;

/**
 * Cards are immutable: In the game, there are exactly 132 cards, each one of them different and immutable
 * Each card belongs to a specific Deck:
 * @author Pascal,Gustavo,bhavik,Esteban,Diego
 *
 */

public class Card {

	public boolean isPlayable;
	public boolean isVisible;
	
	private String name;
	private ImageIcon icon;
	
	/**
     * Constructor
     * @param visible
     * @param playable
     */
	public Card(boolean visible, boolean playable) {
		this.isVisible = visible;
		this.isPlayable = playable;
		this.name = null;
		this.icon = null;
	}
	
	/**
	 * Getter Name
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Setter Name
	 * @param aName
	 */
	public void setName(String aName) {
		this.name = aName;
	}
	/**
	 * Getter Icon
	 * @return
	 */
	public ImageIcon getIcon() {
		return this.icon;
	}
	/**
	 * Setter Icon
	 * @param anIcon
	 */
	public void setIcon(ImageIcon anIcon) {
		this.icon = anIcon;
	}
    
}
