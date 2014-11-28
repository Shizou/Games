//Name: William Granados
//Date: Tuesday, June 4th, 2013
//Class description: works with anything text file related

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;



public class Misceallanous {

	private Vector<String> highscoresNamesLines = new Vector<String>();
	private Vector<String> highscoresLines = new Vector<String>();
	private Vector<String> ratingLines = new Vector<String>();
	private Vector<String> levelLines = new Vector<String>(); 
	
	private Tiles[][] levelLayout = new Tiles[13][15];
	private String[] highscoresNameList = new String[10];
	private int[] highscoresList = new int[10];
	private double currentRating;
	private int amountOfPeople;
	private Scanner s = null;
	
	//file related
	public void openLevelFile(String FileName){
		File levelFile = new File(FileName);
		levelLines = new Vector<String>();
	
		try {
		    s = new Scanner(levelFile);
		}
		catch(Exception noFile) { 
		    System.out.println("No file found");
		}
		while(s.hasNext()){
		   levelLines.add(s.nextLine());
		}
		
	}
	public void openHighscoresNameFile(){
		File highScoresFile = new File("Resources/Highscores/HighscoresName.txt");
		
		try {
		    s = new Scanner(highScoresFile);
		}
		catch(Exception noFile) { 
		    System.out.println("No file found");
		}
		
		while(s.hasNext()){
		   highscoresNamesLines.add(s.nextLine());
		}
		
	}
	public void openHighscoresFile(){
		File highScoresFile = new File("Resources/Highscores/Highscores.txt");
		
		try {
		    s = new Scanner(highScoresFile);
		}
		catch(Exception noFile) { 
		    System.out.println("No file found");
		}
		
		while(s.hasNext()){
		   highscoresLines.add(s.nextLine());
		}
	
	}
	public void openRatingFile(){
		File ratingFile = new File("Resources/Rating/rating.txt");
		
		try {
		    s = new Scanner(ratingFile);
		}
		catch(Exception noFile) { 
		    System.out.println("No file found");
		}
		
		while(s.hasNext()){
		   ratingLines.add(s.nextLine());
		}
		
		this.setCurrentrating(Double.parseDouble(ratingLines.elementAt(0)));
		this.setAmountOfPeople(Integer.parseInt(ratingLines.elementAt(1)));
	
	}
	public void createLevelGrid(){
		
		for(int x=0;x<13;x++){
			String lineOfBricks=levelLines.elementAt(x);
			for(int y=0;y<15;y++){
				
				if(lineOfBricks.charAt(y) == 'B')
						levelLayout[x][y]= new Tiles(201+(40*y),25+(40*x),true,false);
				else if (lineOfBricks.charAt(y)=='O')
						levelLayout[x][y]=new Tiles(201+(40*y),25+(40*x),false,false);
				else if (lineOfBricks.charAt(y)=='S' )
						levelLayout[x][y]=new Tiles(201+(40*y),25+(40*x),true,true);
			
				//powerups
				else if (lineOfBricks.charAt(y)=='f' )
					levelLayout[x][y]=new Tiles(201+(40*y),25+(40*x),true,true,false,false,false);
			
				else if (lineOfBricks.charAt(y)=='b' )
					levelLayout[x][y]=new Tiles(201+(40*y),25+(40*x),true,false,true,false,false);
			
				else if (lineOfBricks.charAt(y)=='s' )
					levelLayout[x][y]=new Tiles(201+(40*y),25+(40*x),true,false,false,true,false);
				else if (lineOfBricks.charAt(y)=='c' )
					levelLayout[x][y]=new Tiles(201+(40*y),25+(40*x),true,false,false,false,true);
			
				
			}
		}
		
		
	}

	public void updateHighscoresFile(int score, int score2){
		//straight search since I know the file
		File fileName = new File("Resources/Highscores/Highscores.txt");
		File fileName2 = new File("Resources/Highscores/HighscoresName.txt");
		FileWriter f = null;
		
		
		for(int i = 0;i < 10;i++)
		{
			if(score > highscoresList[i])
			{
				
				String name = JOptionPane.showInputDialog(null, "Please enter your name player one: ");
				if(name == null){
					name = "Anonymous";
				}
				for(int j = 9;j > i;j--){
					highscoresList[j] = highscoresList[j-1];
				}
				for(int j = 9;j > i;j--){
					highscoresNameList[j] = highscoresNameList[j-1];
				}
				highscoresList[i] = score;
				highscoresNameList[i] = name+" -";
				break;
				
			}
		}
		
		for(int i = 0;i < 10;i++)
		{
			if(score2 > highscoresList[i])
			{
				String name = JOptionPane.showInputDialog(null, "Please enter your name player two: ");
				if(name == null){
					name = "Anonymous";
				}
				for(int j = 9;j > i;j--){
					highscoresList[j] = highscoresList[j-1];
				}
				for(int j = 9;j > i;j--){
					highscoresNameList[j] = highscoresNameList[j-1];
				}
				highscoresList[i] = score2;
				highscoresNameList[i] = name+" -";
				
				break;
			}
		}
	
		
		
		
		try {
		    f = new FileWriter(fileName); 
		    
		    for(int i = 0;i < 10;i++){
		    	  f.write(highscoresList[i]+"\n"); //writes highscores to file
		    }
		    
		  
		    f.close(); 
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		try {
		    f = new FileWriter(fileName2); 
		    
		    for(int i = 0;i < 10;i++)
		    {
		    	  f.write(highscoresNameList[i]+"\n"); //writes names to text file
		    }
		    
		  
		    f.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	public void updateRatingFile(double currentRating,int amountOfPeople){
		File fileName = new File("Resources/Rating/rating.txt"); //straight search since I know the file
		
		FileWriter f = null;
		
		try {
		    f = new FileWriter(fileName); 
		    f.write( currentRating +"\n" + amountOfPeople + "\n");
		    f.close(); 
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	public void createHighscoresList(){
		
		for(int x=0;x<highscoresLines.size();x++){
			highscoresNameList[x] = highscoresNamesLines.elementAt(x);
			highscoresList[x] = Integer.parseInt(highscoresLines.elementAt(x));
		}
	}
	
	//getters & setters
	public Tiles[][] getSnowballGrid(){
		return levelLayout;
	}
	public String[] getHighScoresNameList(){
		return highscoresNameList;
	}
	public int[] getHighScores(){
		return highscoresList;
	}
	
	public double getCurrentRating(){
		return currentRating;
	}
	private void setCurrentrating(double currentRating){
		this.currentRating = currentRating;
	}
	
	public int getAmountOfPeople(){
		return amountOfPeople;
	}
	private void setAmountOfPeople(int amountOfPeople){
		this.amountOfPeople = amountOfPeople;
	}
	
	public void closeFile(){
		s.close();
	}
}
