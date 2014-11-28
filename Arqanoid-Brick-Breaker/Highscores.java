//Name: William Granados
//Date: Tuesday, June 4th, 2013
//Class description: gets highscores from highscores textfile

import java.io.*;
import java.util.*;


public class Highscores {

	private Vector<String> highscores = new Vector<String>();
	private int[] highscoresList = new int[10];
	private Scanner s = null;
	
	public void openHighscoresFile(String FileName){
		File highScoresFile = new File(FileName);
		
		try {
		    s = new Scanner(highScoresFile);
		}
		catch(Exception noFile) { 
		    System.out.println("No file found");
		}
		
		while(s.hasNext()){
		   highscores.add(s.nextLine());
		}
	}
	
	public void updateHighscoresFile( int score ){
		File fileName = new File("Highscores/Highscores.txt"); //specifies the name of the file and location (default is project folder)
		FileWriter f = null;
		
		for(int i = 0;i < 10;i++){
			if(score > highscoresList[i]){
				for(int j = 9; j > i;j--){
					highscoresList[j] = highscoresList[j-1];
				}
				highscoresList[i] = score;
				break;
			}
			
		}
		
		try {
		    f = new FileWriter(fileName); //file writers allow you to write to a file
		    for(int i = 0;i < 10;i++){
		    	 f.write(highscoresList[i] + "\n"); //writes "Hey Bob" to the file
				    	
		    }
		    
		    f.close(); //close the file when you're done with it otherwise the contents may not save to the file
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public void createHighscoresList(){
		
		for(int x=0;x<highscores.size();x++){
			highscoresList[x] = Integer.parseInt(highscores.elementAt(x));
		}
	}

	public void closeFile(){
		s.close();
	}
	
	public int[] getHighScores(){
		return highscoresList;
	}
}
