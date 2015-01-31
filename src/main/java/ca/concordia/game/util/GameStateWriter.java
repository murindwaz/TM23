package ca.concordia.game.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.*;
import java.util.Map.Entry;

import ca.concordia.GameState;
import ca.concordia.game.model.*;




/**
 * This class will write response to a file. This class is designed to be shipped to clients 
 * @author murindwaz
 */
public class GameStateWriter {
	
	private String filepath;
	private GameState gameState;
	public GameStateWriter( GameState game ){
		gameState = game;
		Calendar now = Calendar.getInstance(); 
		now.setTime(new Date());
		String filename = String.format( "%d-%d-%d.json", now.get(Calendar.YEAR) , now.get(Calendar.MONTH) , now.get(Calendar.DATE));
		String path = System.getProperty("os.name").startsWith("Windows") ? "C:/" + Configuration.PATH : "/tmp/" + Configuration.PATH;
		if(!new File(path).isDirectory() ){
			new File(path).mkdirs();
		}
		filepath = path + "/" +filename;
	}
	
	
	/**
	 * 
	 * @param GameState
	 */
	public void write(){
		Writer writer =  null;
		synchronized (this) {
			try {
				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream( new File(filepath) , true) , "utf-8" ));
				//for (Entry<String, Reservation> entry : reservations.entrySet()) {
				//	writer.write(String.format( "%s%n", entry.getValue().toString() ));
				//}
			} catch (IOException ex) {
				System.out.println( " IOException " + filepath );
			} finally {
			   try {
				   writer.close();
				 } catch (Exception ex) {
					System.out.println( " IOException " + filepath );
				 }
			}
		}
	}
	
	public String getFilePath(){
		return filepath;
	}
}