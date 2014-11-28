import java.awt.Color;
import java.io.*;
import java.util.*;
public class LevelLoader {
	
	private Vector<String> lines = new Vector<String>(); 
	private Brick[][] levelLayout =new Brick[8][16];
	private Scanner s = null;
	
	public void openLevelFile(String FileName){
		File levelFile = new File(FileName);
		
		try {
		    s = new Scanner(levelFile);
		}
		catch(Exception noFile) { 
		    System.out.println("No file found");
		}
		while(s.hasNext()){
		   lines.add(s.nextLine());
		}
	}
	
	public void createLevelGrid(){
		int space = 30;
		
		for(int x=0;x<lines.size();x++){
			String lineOfBricks=lines.elementAt(x);
			for(int y=0;y<16;y++){
				
				if (lineOfBricks.charAt(y)=='O')
					levelLayout[x][y]=null;
				else if (lineOfBricks.charAt(y)=='R')
						levelLayout[x][y]=new Brick(y*61,100 + (space*x),Color.red);
				else if (lineOfBricks.charAt(y)=='G' )
						levelLayout[x][y]=new Brick(y*61,100 + (space*x),Color.green);
				else if (lineOfBricks.charAt(y)=='B')
						levelLayout[x][y]=new Brick(y*61,100 + (space*x),Color.blue);
				
			}
		}
	}
	
	public void closeFile(){
		s.close();
	}
	
	public Brick[][] getBrickGrid(){
		return levelLayout;
	}
		
}
