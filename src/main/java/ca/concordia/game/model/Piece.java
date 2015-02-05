package ca.concordia.game.model;

import java.awt.Color;
import javax.swing.ImageIcon;

//This class will only contain the color of the Piece.

public class Piece {
	
	private Color color;
	private ImageIcon icon;
	
	public Piece(Color color)
	{
		this.color=color;
		this.icon = null;
	}

	public ImageIcon getIcon() {
		return this.icon;
	}
	public void setIcon(ImageIcon anIcon) {
		this.icon = anIcon;
	}
}
