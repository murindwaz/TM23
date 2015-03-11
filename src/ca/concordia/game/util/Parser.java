package ca.concordia.game.util;


/**
 * Parsers will take a string and return Objects 
 * @author murindwaz
 */
public interface Parser {
	public <T> T parse(String argument);
}
