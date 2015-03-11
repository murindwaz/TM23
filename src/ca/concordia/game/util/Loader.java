package ca.concordia.game.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



//Import Packages
import ca.concordia.game.model.*;

/**
 * Class Loader is in charge of Loading and coping the contents of a file into an array.
*@author Pascal Maniraho 
 *@author Gustavo Pereira
 *@author Bhavik Desai 
 *@author Jesus Esteban Garro Matamoros 
 *@author Diego Pizarro

 *
 */
public final class Loader {
	

	/**
	 * Private Constructor. Private since It's a final class with static functions.
	 */
	private Loader()
	{
		
	}
	
	/**
	 * Load a game state from a txt. file with the name specified in the argument.Return an arraylist with all the content from the file.
	 * @param fileName
	 * @return
	 */
	public static ArrayList<String> loadGameState(String fileName)
	{
		//reading file line by line in Java using BufferedReader       
        FileInputStream fis = null;
        BufferedReader reader = null;
        
        ArrayList<String> content = new ArrayList<String>();//Array list that will contain in each string one line from the file.
        

        //Read and copy content from file.
        try {
            fis = new FileInputStream(fileName);
            reader = new BufferedReader(new InputStreamReader(fis));
          
            System.out.println("Starting to read File........");
          
            //Read First line(First Gameboard Area).
            String line = reader.readLine();
            content.add(line);//Copy file content to array.
            while(line != null){
                //System.out.println(line);
                line = reader.readLine();
                if(line!=null)
                	content.add(line);//Copy file content to array.
            }           
          
        } catch (FileNotFoundException ex) {
        	System.out.println("File Not Found.");
            
        } catch (IOException ex) {
            
        	System.out.println("IO Exception.");
        } finally {//Close readers.
            try {reader.close(); fis.close();} 
            catch (IOException ex) {System.out.println("IO Exception(Trying to close readers)."); }
        }

        return 	content;
	}

}
