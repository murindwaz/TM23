package ca.concordia.game.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class Saver is in charge of Saving the contents of an array list into a file whose name is chosen by the user.
*@author Pascal Maniraho 
 *@author Gustavo Pereira
 *@author Bhavik Desai 
 *@author Jesus Esteban Garro Matamoros 
 *@author Diego Pizarro

 *
 */
public final class Saver {
	

	/**
	 * Constructor: Private since It's a final class with static functions.
	 */
	private Saver()
	{
		
	}
	
	/**
	 * save the contents of the array list argument into a file whose name is specified by the user.
	 * @param contentToWrite
	 */
	public static void saveGameState(ArrayList<String> contentToWrite) 
	{
		//Create Scanner Object
		Scanner keyIn=new Scanner(System.in);
		System.out.println("Please enter the saved game file name you wish to use:");
		String saveGame=keyIn.next();
		
		try {
		File fout = new File(saveGame);
		FileOutputStream fos = new FileOutputStream(fout);
	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	 
		for (int i = 0; i < contentToWrite.size(); i++) {
			bw.write(contentToWrite.get(i));
			if(i!=contentToWrite.size()-1)//Don't make a new line after writing the last line.
				bw.newLine();
		}
	 
		bw.close();
		}catch (FileNotFoundException ex) {
        	System.out.println("File Not Found.");
            
        } catch (IOException ex) {
            
        	System.out.println("IO Exception.");
        } 
		
		System.out.println("Game Was Successfully Saved.");
	}

}
