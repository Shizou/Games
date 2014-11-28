//Name: William Granados
//Date: Tuesday, June 4th, 2013
//Class description: Main class handles how game runs

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class Bomberman extends Canvas implements Runnable{
	//screen dimensions and variables
	static final int WIDTH = 800;//1024
	static final int HEIGHT = WIDTH / 4 * 3; //4:3 aspect ratio // WIDTH / 4 * 3
	private JFrame frame;
	
	//game updates per second
	static final int UPS = 60;
	
	//variables for the thread
	private Thread thread;
	private boolean running;
	
	//used for drawing items to the screen
	private Graphics2D graphics;
	
	// objects
	private Misceallanous misceallanous;
	private Player player;
	private Artificial player2;
	//private Player player2;
	private Navigation navigation = new Navigation();
	private Mouse mouse = new Mouse();
	private Tiles tiles ;
	private Sprite sprites;
	private Music music;
	private Tiles[][] levelLayout ;
	private Bomb[][] bombLayout;
	
	//player locatios on grid
	private int[] playerLocation, player2Location;
		
	//score related
	private int blackScore = 0, whiteScore;
	private int amountOfPeople;
	private int level = 1;
	private double currentRating; 
	private boolean ratingUpdate = false;
	private long gameTimer;
	private long ratingTimer;
		
	private int playerHit = level*1000;
	private int snowballHit = level*100;
		
		
	//game states
	private int gameState ;
	private final int GAME_TITLE = 0;
	private final int GAME_MENU = 1;
	private final int GAME_PLAY = 2;
	private final int GAME_INSTRUCTION = 3;
	private final int GAME_RATING = 4;
	private final int GAME_HIGHSCORE = 5;
	private final int GAME_CREDENTIALS = 6;
	private final int GAME_POPUP = 7;
	
	
	//initialize game objects, load media(pics, music, etc)
	public void init() {
		initializeObjects();
		initializeMisceallanousItems();
		initializeGameState();
		
		initializeIngamePlayers();
		menuMusic();
	}
	public void initializeObjects(){
		initializePlayers();
		initializeBombLayout();
		initializeMisceallanous();
		initializeMusic();
		initializeTiles();
		initializeSprites();
		
	}
	public void initializePlayers(){
		player = new Player();
		player2 = new Artificial();
	}
	public void initializeBombLayout(){
		bombLayout = new Bomb[13][15];
	}
	public void initializeMisceallanous(){
		misceallanous = new Misceallanous();
	}
	public void initializeMusic(){
		music = new Music();
	}
	public void initializeTiles(){
		tiles = new Tiles();
		
	}
	public void initializeSprites(){
		sprites = new Sprite();
	}
	
	public void initializeMisceallanousItems(){
		initializeRatings();
		initializeHighscores();
		initializeLevelLayout();
	}
	public void initializeHighscores(){
		misceallanous.openHighscoresNameFile();
		misceallanous.openHighscoresFile();
		misceallanous.createHighscoresList();
	}
	public void initializeLevelLayout(){
		misceallanous.openLevelFile("Resources/Levels/level" + level +".txt");
		misceallanous.createLevelGrid();
		levelLayout = misceallanous.getSnowballGrid();	
	}
	public void initializeRatings(){
		misceallanous.openRatingFile();
		currentRating = misceallanous.getCurrentRating();
		amountOfPeople = misceallanous.getAmountOfPeople();
	}

	public void initializeGameState(){
		updateGameState(GAME_TITLE);	
	}
	
	public void initializeIngamePlayers(){
		
		initializeScore();
		initializePlayerLocations();
		initializePastPlayerActions();

	}
	public void initializeScore(){
		blackScore = 0;
		whiteScore = 0;
	}
	public void initializePlayerLocations(){
		playerLocation = new int[2];
		player2Location = new int[2];
	}
	public void initializePastPlayerActions(){
		navigation.setPastPlayerAction(navigation.getPlayerLookDown());
		navigation.setPastPlayer2Action(navigation.getPlayerLookDown());
	}
	
	public void menuMusic(){
		//music.menuOpen();
	}
	public void gameMusic(){
		//music.gameOpen();
	}
	public void bombSoundFX(){
		//music.bombOpen();
	}
	
	// reset game/restart/update game level
	public void restart(){
		resetPlayers();
		resetBombLayout();
		resetScores();
		resetPastPlayerActions();
		resetLevelLayout();
	}
	public void resetPlayers(){
		player = new Player();
		player2 = new Artificial();
	}
	public void resetBombLayout(){
		bombLayout = new Bomb[13][15];
	}
	public void resetScores(){
		this.initializeScore();
	}
	public void resetPastPlayerActions(){
		this.initializePastPlayerActions();
	}
	public void resetLevelLayout(){
		level = 1;
		this.initializeLevelLayout();
	}
	
	public void levelUp(){
		resetPlayerPositions();
		resetPlayerPastActions();
		resetBombLayout();
		updateScoring();
		updateLevel();	
		updateLevelLayout();
	}
	
	public void resetPlayerPositions(){
		player.setX(250);
		player.setY(69);
		
		player2.setX(725);
		player2.setY(469);
		
	}
	public void resetPlayerPastActions(){
		navigation.setPastPlayerAction(navigation.getPlayerLookDown());
		navigation.setPastPlayer2Action(navigation.getPlayerLookDown());
	}
	public void updateLevel(){
		level += 1;
	}
	public void updateLevelLayout(){
		misceallanous.openLevelFile("Resources/Levels/level" + level +".txt");
		misceallanous.createLevelGrid();
		levelLayout = null;
		levelLayout = misceallanous.getSnowballGrid();
	}
	public void updateScoring(){
		playerHit = level*1000;
		snowballHit = level*100;
	}
	
	//main updates
	public void update() {
		
		if(gameState == GAME_PLAY)
		{
			updateGame();
		}	
		
		checkRatingUpdate();
		mouse.setMouseClick(false);
	}
	public void updateGame(){	
		updatePlayer();
		updatePlayer2();
		updateField();
		
		music.bombClose();
		if(checkGrid())
		{
			levelUp();
		}
	}
	
	//update game
	public void updatePlayer(){
		locationGetter();
		
		if(checkUp(playerLocation[0],playerLocation[1],player.sprite2Boundary()) && navigation.getPlayerUp() == true)
			player.setY(player.getY() - player.getSpeed());
		
		else if(checkDown(playerLocation[0],playerLocation[1],player.sprite2Boundary()) && navigation.getPlayerDown() == true)
				player.setY(player.getY() + player.getSpeed());
		
		else if(checkLeft(playerLocation[0],playerLocation[1],player.sprite2Boundary()) && navigation.getPlayerLeft() == true)
				player.setX(player.getX() - player.getSpeed());
		
		else if(checkRight(playerLocation[0],playerLocation[1],player.sprite2Boundary()) && navigation.getPlayerRight() == true)
				player.setX(player.getX() + player.getSpeed());
		
		
	}
	public void updatePlayer2(){
		locationGetter2();
		
		if(player2.getPlayerObjective() == player2.getClearBoxObjective()){
			
		}
		else if(player2.getPlayerObjective() == player2.dodgeBombObjective()){
			
		}
		else if(player2.getPlayerObjective() == player2.seekPlayerObjective()){
			
		}
		
		if(checkUp(player2Location[0],player2Location[1],player2.sprite2Boundary()))
			player2.setY(player2.getY() - player2.getSpeed());
		
		else if(checkDown(player2Location[0],player2Location[1],player2.sprite2Boundary()))
			player2.setY(player2.getY() + player2.getSpeed());
		
		else if(checkLeft(player2Location[0],player2Location[1],player2.sprite2Boundary()) )
			player2.setX(player2.getX() - player2.getSpeed());
		
		else if(checkRight(player2Location[0],player2Location[1],player2.sprite2Boundary()) )
			player2.setX(player2.getX() + player2.getSpeed());
		
	}
	public void updateField(){
		//player 1
		if(navigation.getPlayerBomb() == true)
		{
			navigation.setPlayerBomb(false);
				
				if(player.getTempBombCount() > 0)
				{
				   levelLayout[playerLocation[0]][playerLocation[1]].setBomb(true);
			       bombLayout[playerLocation[0]][playerLocation[1]] = new Bomb(1);
		           player.setTempBombCount(player.getTempBombCount()-1);
				}
			
		}
		//player 2
		if(navigation.getPlayer2Bomb() == true)
		{
			navigation.setPlayer2Bomb(false);
				
				if(player2.getTempBombCount() > 0)
				{
				   levelLayout[player2Location[0]][player2Location[1]].setBomb(true);
				   bombLayout[player2Location[0]][player2Location[1]] = new Bomb(2);
			       player2.setTempBombCount(player2.getTempBombCount()-1);
				}
			
		}
		
		// power ups
		checkFirePower();
		checkSpeedPower();
		checkBombPower();
		checkIsCool();
	}
		
	//collision detection for players
	public boolean checkUp(int x , int y, Rectangle2D sprite){
			boolean upSolid = levelLayout[x-1][y].getIsSolid();
			boolean upTouch = levelLayout[x-1][y].tileBoundary().intersects(sprite);
			boolean bomb = levelLayout[x-1][y].isBomb();
			
			
			if(upSolid == false && bomb == false)
				return true;
			
			else if(upSolid == true && upTouch == false && bomb == false)
				return true;
			
			else if(upSolid == false && bomb == true && upTouch == true)
				return false;
			
			else
				return false;
			
	}
	public boolean checkDown(int x , int y, Rectangle2D sprite){
			boolean downSolid = levelLayout[x+1][y].getIsSolid();
			boolean downTouch = levelLayout[x+1][y].tileBoundary().intersects(sprite);
			boolean bomb = levelLayout[x+1][y].isBomb();
			
			
			if(downSolid == false && bomb == false)
				return true;
			
			else if(downSolid == true && downTouch == false)
				return true;
			
			else if(downSolid == false && bomb == true && downTouch == true)
				return false;
			
			else
				return false;
			
	}
	public boolean checkLeft(int x , int y, Rectangle2D sprite){
			boolean leftSolid = levelLayout[x][y-1].getIsSolid();
			boolean leftTouch = levelLayout[x][y-1].tileBoundary().intersects(sprite);
			boolean bomb = levelLayout[x][y-1].isBomb();
			
			
			if(leftSolid == false && bomb == false)
				return true;
			
			else if(leftSolid == true && leftTouch == false)
				return true;
			
			else if(leftSolid == false && bomb == true && leftTouch == true)
				return false;
			
			else
				return false;
		
	}
	public boolean checkRight(int x , int y, Rectangle2D sprite){
			boolean rightSolid = levelLayout[x][y+1].getIsSolid();
			boolean rightTouch = levelLayout[x][y+1].tileBoundary().intersects(sprite);
			boolean bomb = levelLayout[x][y+1].isBomb();
			
			if(rightSolid == false && bomb == false)
				return true;
			
			else if(rightSolid == true && rightTouch == false)
				return true;
			
			else if(rightSolid == false && bomb == true && rightTouch == true)
				return false;
			else
				return false;
			
			
	}
		
	//powerups
	public void checkFirePower(){
		
		if(levelLayout[playerLocation[0]][playerLocation[1]].isFirePower())
		{
			if(levelLayout[playerLocation[0]][playerLocation[1]].tileBoundary().intersects(player.spriteBoundary()))
			{
				levelLayout[playerLocation[0]][playerLocation[1]].setFirePower(false);
				player.setBombRadius(player.getBombRadius()+1);
			}
		}
		
		if(levelLayout[player2Location[0]][player2Location[1]].isFirePower())
		{
			if(levelLayout[player2Location[0]][player2Location[1]].tileBoundary().intersects(player2.spriteBoundary()))
			{
				levelLayout[player2Location[0]][player2Location[1]].setFirePower(false);
				player2.setBombRadius(player2.getBombRadius()+1);
			}
		}
		
	}
	public void checkSpeedPower(){
		
		 if(levelLayout[playerLocation[0]][playerLocation[1]].isBombPower())
		 {
			if(levelLayout[playerLocation[0]][playerLocation[1]].tileBoundary().intersects(player.spriteBoundary()))
			{
				levelLayout[playerLocation[0]][playerLocation[1]].setBombPower(false);
				player.setBombCount(player.getBombCount()+1);
				player.setTempBombCount(player.getTempBombCount()+1);
			}
		 }
		 
		 if(levelLayout[player2Location[0]][player2Location[1]].isBombPower())
		 {
				if(levelLayout[player2Location[0]][player2Location[1]].tileBoundary().intersects(player2.spriteBoundary()))
				{
					levelLayout[player2Location[0]][player2Location[1]].setBombPower(false);
					player2.setBombCount(player2.getBombCount()+1);
					player2.setTempBombCount(player2.getTempBombCount()+1);
				}
		 }
		 
	}
	public void checkBombPower(){
		 if(levelLayout[playerLocation[0]][playerLocation[1]].isSpeedPower())
		 {
			if(levelLayout[playerLocation[0]][playerLocation[1]].tileBoundary().intersects(player.spriteBoundary()))
			{
				levelLayout[playerLocation[0]][playerLocation[1]].setSpeedPower(false);
				player.setSpeed(player.getSpeed()+1);
			}
		 }
		 
		 if(levelLayout[player2Location[0]][player2Location[1]].isSpeedPower())
		 {
				if(levelLayout[player2Location[0]][player2Location[1]].tileBoundary().intersects(player2.spriteBoundary()))
				{
					levelLayout[player2Location[0]][player2Location[1]].setSpeedPower(false);
					player2.setSpeed(player2.getSpeed()+1);
				}
		}
		 
	}
	public void checkIsCool(){
			 if(levelLayout[playerLocation[0]][playerLocation[1]].isCool())
			 {
				if(levelLayout[playerLocation[0]][playerLocation[1]].tileBoundary().intersects(player.spriteBoundary()))
				{
					levelLayout[playerLocation[0]][playerLocation[1]].setIsCool(false);
					updateBlackScore(5000);
				}
			 }
			 
			 if(levelLayout[player2Location[0]][player2Location[1]].isCool())
			 {
					if(levelLayout[player2Location[0]][player2Location[1]].tileBoundary().intersects(player2.spriteBoundary()))
					{
						levelLayout[player2Location[0]][player2Location[1]].setIsCool(false);
						updateWhiteScore(5000);
					}
			}
			 
	}
		
	//bomb
	public void drawExplosions(int x, int y){
			//draws central explosion
			graphics.drawImage(sprites.getExplosion(),levelLayout[x][y].getX(),levelLayout[x][y].getY(),40,40,null);
			
			bombSoundFX();
			drawBombUp(x,y);
			drawBombDown(x,y);
			drawBombLeft(x,y);
			drawBombRight(x,y);
		
	}
		
	public void drawBombUp(int x, int y){
		
		int bombRadius;
		
		if(bombLayout[x][y].getID() == 1)
			bombRadius = player.getBombRadius();
		else
			bombRadius = player2.getBombRadius();
		
			for(int i = 1;x-i > -1 && i <= bombRadius;i++)
			{
				if(bombCheck(x-i,y) == true && levelLayout[x-i][y].isSnowball() == false)
					graphics.drawImage(sprites.getExplosion(),levelLayout[x-i][y].getX(),levelLayout[x-i][y].getY(),40,40,null);
				
				else if(levelLayout[x-i][y].isSnowball() == true)
				{
					graphics.drawImage(sprites.getExplosion(),levelLayout[x-i][y].getX(),levelLayout[x-i][y].getY(),40,40,null);
					return;
				}
				else
					return;
			}	
			
	}
	public void updateBombUp(int x, int y, int bombRadius){
		
			for(int i = 1;x-i > -1 && i <= bombRadius;i++)
			{
				 if(levelLayout[x-i][y].isSnowball() == true){
					levelLayout[x-i][y].setSnowball(false);
					levelLayout[x-i][y].setIsSolid(false);
					
						if(bombLayout[x][y].getID() == 1)
							updateBlackScore(snowballHit);
						else
							updateWhiteScore(snowballHit);
					
					return;
				}
				else if(levelLayout[x-i][y].tileBoundary().contains(player.spriteBoundary()) )
				{		
						updateWhiteScore(playerHit);
						return;		
				}
				 
				else if(levelLayout[x-i][y].tileBoundary().contains(player2.spriteBoundary()) )
				{	
					updateBlackScore(playerHit);
					return;	
				}
				else if(levelLayout[x-i][y].isBombPower())
					levelLayout[x-i][y].setBombPower(false);
				
				else if(levelLayout[x-i][y].isFirePower())
					levelLayout[x-i][y].setFirePower(false);
				
				else if(levelLayout[x-i][y].isSpeedPower())
					levelLayout[x-i][y].setBombPower(false);
				
				else if(levelLayout[x-i][y].getIsSolid() == true)
					 return;
				
			}
	}
				
	public void drawBombDown(int x, int y){
		
		int bombRadius;
		
		if(bombLayout[x][y].getID() == 1)
			bombRadius = player.getBombRadius();
		else
			bombRadius = player2.getBombRadius();
		
		
			for(int i = 1; x+i < 12 && i <= bombRadius;i++)
			{
				if(bombCheck(x+i,y) == true && levelLayout[x+i][y].isSnowball() == false)
					graphics.drawImage(sprites.getExplosion(),levelLayout[x+i][y].getX(),levelLayout[x+i][y].getY(),40,40,null);
				
				else if(levelLayout[x+i][y].isSnowball() == true)
				{
					graphics.drawImage(sprites.getExplosion(),levelLayout[x+i][y].getX(),levelLayout[x+i][y].getY(),40,40,null);
					return;
				}
				else
					return;
			}	
	}
	public void updateBombDown(int x , int y,int bombRadius){
		
			for(int i = 1; x+i < 12 && i <= bombRadius;i++)
			{
				 if(levelLayout[x+i][y].isSnowball() == true)
				 {
					levelLayout[x+i][y].setSnowball(false);
					levelLayout[x+i][y].setIsSolid(false);
					
						if(bombLayout[x][y].getID() == 1)
							updateBlackScore(snowballHit);
						else
							updateWhiteScore(snowballHit);
					
					return;
				}
				else if(levelLayout[x+i][y].tileBoundary().contains(player.spriteBoundary()) )
				{		
						updateWhiteScore(playerHit);
						return;		
				}
				else if(levelLayout[x+i][y].tileBoundary().contains(player2.spriteBoundary()) )
				{
					updateBlackScore(playerHit);
					return;	
				}
				else if(levelLayout[x+i][y].isBombPower())
					levelLayout[x+i][y].setBombPower(false);
				
				else if(levelLayout[x+i][y].isFirePower())
					levelLayout[x+i][y].setFirePower(false);
				
				else if(levelLayout[x+i][y].isSpeedPower())
					levelLayout[x+i][y].setBombPower(false);
				
				else if(levelLayout[x+i][y].getIsSolid() == true)
					 return;
				
			}
			
	}
			
	public void drawBombLeft(int x, int y){
		
		int bombRadius;
		
		if(bombLayout[x][y].getID() == 1)
			bombRadius = player.getBombRadius();
		else
			bombRadius = player2.getBombRadius();
		
			for(int i = 1;y-i > -1 && i <= bombRadius;i++){
				if(bombCheck(x,y-i) == true && levelLayout[x][y-i].isSnowball() == false)
					graphics.drawImage(sprites.getExplosion(),levelLayout[x][y-i].getX(),levelLayout[x][y-i].getY(),40,40,null);
				
				else if(levelLayout[x][y-i].isSnowball() == true){
					graphics.drawImage(sprites.getExplosion(),levelLayout[x][y-i].getX(),levelLayout[x][y-i].getY(),40,40,null);
					return;
				}
				else
					return;
			}
	}
	public void updateBombLeft(int x , int y, int bombRadius){
			for(int i = 1;y-i > -1 && i <= bombRadius;i++)
			{
				if(levelLayout[x][y-i].isSnowball() == true)
				{
				   levelLayout[x][y-i].setSnowball(false);
				   levelLayout[x][y-i].setIsSolid(false);
				 	
				 	   if(bombLayout[x][y].getID() == 1)
				 		  updateBlackScore(snowballHit);
				 	   else
				 		  updateWhiteScore(snowballHit);
				 	
				 	return;
				 }
				else if(levelLayout[x][y-i].tileBoundary().contains(player.spriteBoundary()) )
				{		
						updateWhiteScore(playerHit);
						return;	
				}
				else if(levelLayout[x][y-i].tileBoundary().contains(player2.spriteBoundary()) )
				{	
					updateBlackScore(playerHit);
					return;	
				}
				else if(levelLayout[x][y-i].isBombPower())
					levelLayout[x][y-i].setBombPower(false);
				
				else if(levelLayout[x][y-i].isFirePower())
					levelLayout[x][y-i].setFirePower(false);
				
				else if(levelLayout[x][y-i].isSpeedPower())
					levelLayout[x][y-i].setBombPower(false);
				
				else if(levelLayout[x][y-i].getIsSolid() == true)
					 return;
				
			}
		}
			
	public void drawBombRight(int x, int y){
		
		int bombRadius;
		
		if(bombLayout[x][y].getID() == 1)
			bombRadius = player.getBombRadius();
		else
			bombRadius = player2.getBombRadius();
		
		
			for(int i = 1;y+i < 14 && i <= bombRadius;i++)
			{
				if(bombCheck(x,y+i) == true && levelLayout[x][y+i].isSnowball() == false)
					graphics.drawImage(sprites.getExplosion(),levelLayout[x][y+i].getX(),levelLayout[x][y+i].getY(),40,40,null);
			
				else if(levelLayout[x][y+i].isSnowball() == true){
					graphics.drawImage(sprites.getExplosion(),levelLayout[x][y+i].getX(),levelLayout[x][y+i].getY(),40,40,null);
					return;
				}
				else if(levelLayout[x][y+i].getIsSolid() == true)
					return;
			
				else
					return;
		
			}
			
	}
	public void updateBombRight(int x , int y, int bombRadius){
			for(int i = 1;y+i < 14 && i <= bombRadius;i++)
			{
				
				 if(levelLayout[x][y+i].isSnowball() == true){
					levelLayout[x][y+i].setSnowball(false);
					levelLayout[x][y+i].setIsSolid(false);
					
						if(bombLayout[x][y].getID() == 1)
							updateBlackScore(snowballHit);
						else
							updateWhiteScore(snowballHit);
					
					return;
				}
				else if(levelLayout[x][y+i].tileBoundary().contains(player.spriteBoundary()) )
				{
						updateWhiteScore(playerHit);
						return;	
				}
				else if(levelLayout[x][y+i].tileBoundary().contains(player2.spriteBoundary()) )
				{	
					updateBlackScore(playerHit);
					return;	
				}
				else if(levelLayout[x][y+i].isBombPower())
					levelLayout[x][y+i].setBombPower(false);
				
				else if(levelLayout[x][y+i].isFirePower())
					levelLayout[x][y+i].setFirePower(false);
				
				else if(levelLayout[x][y+i].isSpeedPower())
					levelLayout[x][y+i].setBombPower(false);
				
				else if(levelLayout[x][y+i].getIsSolid() == true)
					 return;
				
			}
	}
	
	//update values
	public void locationGetter(){
		
		for(int x = 0;x < 13 ;x++)
		{
			for(int y = 0;y < 15  ;y++)
			{
					if(levelLayout[x][y].tileBoundary().intersects(player.spriteBoundary()))
					{	
							if(levelLayout[x][y].getIsSolid() == false )
							{	
								playerLocation[0] = x;
								playerLocation[1] = y;
								return;
							}
					}
			}
		}
		
	}
	public void locationGetter2(){
		
		for(int x = 0;x < 13 ;x++)
		{
			for(int y = 0;y < 15  ;y++)
			{
					if(levelLayout[x][y].tileBoundary().intersects(player2.spriteBoundary()))
					{	
							if(levelLayout[x][y].getIsSolid() == false )
							{	
								player2Location[0] = x;
								player2Location[1] = y;
								return;
							}
					}
			}
		}
		
	}
	
	public void updateGameState(int GAME_STATE){
		this.gameState = GAME_STATE;
	}
	public void updateBlackScore(int blackScore){
		this.blackScore += blackScore;
	}
	public void updateWhiteScore(int whiteScore){
		this.whiteScore += whiteScore;
	}
	
	public boolean checkMouseClick(){
		if(mouse.getMouseClick() == true)
			return true;
		
		else 
			return false;
	}
	public void checkRatingUpdate(){
		if(ratingUpdate == true)
		{
			if((System.currentTimeMillis()-ratingTimer)/1000 >= 60)
			{
				ratingTimer = System.currentTimeMillis();
				ratingUpdate = false;
			}
		}
	}
	
	public boolean checkGrid(){
		
		for(int x = 0;x < 13 ;x++)
		{
			for(int y = 0;y < 15  ;y++)
			{
					if(levelLayout[x][y].isSnowball() )	
						return false;
					
			}
		}
		
		return true;
		
	}
	public boolean bombCheck(int x , int y){
		

		if(levelLayout[x][y].getIsSolid() && levelLayout[x][y].isSnowball() == false )
			return false;
		
		else if(levelLayout[x][y].isSnowball())
			return true;
		
		else if(levelLayout[x][y].tileBoundary().contains(player.spriteBoundary()) )
			return true;
			
		else 
			return true;
		
	}

	//draw things to the screen
	public void draw() {
		drawBackground();
		
		if(gameState == GAME_TITLE)
			drawTitle();
		else if(gameState != GAME_PLAY)
				drawMenuScreen();
		else if(gameState == GAME_PLAY)
				drawGameScreen();
		
		if(gameState == GAME_POPUP)
			drawPopup();
		
	}
	public void drawMenuScreen(){
		if(gameState == GAME_MENU)
			drawMainMenu();
		else if(gameState == GAME_INSTRUCTION)
			drawInstruction();
		else if(gameState == GAME_RATING)
			drawRatings();
		else if(gameState == GAME_HIGHSCORE)
			drawHighscoresScreen();
		else if(gameState == GAME_CREDENTIALS)
			drawCredentialsScreen();
	}
	public void drawGameScreen(){
		drawLayout();
		drawGrid();
		drawPlayers();
		drawBomb();
		drawMenuReturn();
		drawScoreboard();
		drawGameTimer();
		drawScore();

	}
	
	//title related
	public void drawTitle(){
		checkTitle();
		graphics.drawImage(sprites.getTitle(),0,0,800,600,null);
	}
	public void checkTitle(){
		if(mouse.getMouseClick() == true)
		{
			updateGameState(GAME_MENU);
			mouse.setMouseClick(false);
		}
	}
	
	// game related
	public void drawScoreboard(){
		graphics.drawImage(sprites.getGameScore(),-106,-10,400,400,null);
	}
	public void drawLayout(){
		graphics.drawImage(sprites.getLayout(),201,25,600,520,null);
	}
	public void drawPlayers(){
			drawPlayer1();
			drawPlayer2();
	}
	public void drawPlayer1(){
		if(navigation.getPlayerUp() == true)
			graphics.drawImage(sprites.getBlackBombermanSprite(1), player.getX(),player.getY(), sprites.getWidth(),sprites.getHeight(),null);
		
		else if(navigation.getPlayerDown() == true)
			graphics.drawImage(sprites.getBlackBombermanSprite(2), player.getX(),player.getY(), sprites.getWidth(),sprites.getHeight(),null);
		
		else if(navigation.getPlayerLeft() == true)
			graphics.drawImage(sprites.getBlackBombermanSprite(3), player.getX(),player.getY(), sprites.getWidth(),sprites.getHeight(),null);
		
		else if(navigation.getPlayerRight() == true)
			graphics.drawImage(sprites.getBlackBombermanSprite(4), player.getX(),player.getY(), sprites.getWidth(),sprites.getHeight(),null);
		
		else {
			
			if(navigation.getPastPlayerAction() == navigation.getPlayerLookUp())
				graphics.drawImage(sprites.getBlackBombermanSprite(6), player.getX(),player.getY(), sprites.getWidth(),sprites.getHeight(),null);
			
			else if(navigation.getPastPlayerAction() == navigation.getPlayerLookDown())
				graphics.drawImage(sprites.getBlackBombermanSprite(5), player.getX(),player.getY(), sprites.getWidth(),sprites.getHeight(),null);
			
			else if(navigation.getPastPlayerAction() == navigation.getPlayerLookLeft())
				graphics.drawImage(sprites.getBlackBombermanSprite(7), player.getX(),player.getY(), sprites.getWidth(),sprites.getHeight(),null);
			
			else if(navigation.getPastPlayerAction() == navigation.getPlayerLookRight())
				graphics.drawImage(sprites.getBlackBombermanSprite(8), player.getX(),player.getY(), sprites.getWidth(),sprites.getHeight(),null);
		}
	}
	public void drawPlayer2(){
		if(navigation.getPlayer2Up() == true)
			graphics.drawImage(sprites.getWhiteBombermanSprite(1), player2.getX(),player2.getY(), sprites.getWidth(),sprites.getHeight(),null);	
		
		else if(navigation.getPlayer2Down() == true)
			graphics.drawImage(sprites.getWhiteBombermanSprite(2), player2.getX(),player2.getY(), sprites.getWidth(),sprites.getHeight(),null);	
		
		else if(navigation.getPlayer2Left() == true)
			graphics.drawImage(sprites.getWhiteBombermanSprite(3), player2.getX(),player2.getY(), sprites.getWidth(),sprites.getHeight(),null);	
		
		else if(navigation.getPlayer2Right() == true)
			graphics.drawImage(sprites.getWhiteBombermanSprite(4), player2.getX(),player2.getY(), sprites.getWidth(),sprites.getHeight(),null);	
		
		else{
			
			if(navigation.getPastPlayer2Action() == navigation.getPlayerLookUp())
				graphics.drawImage(sprites.getWhiteBombermanSprite(6), player2.getX(),player2.getY(), sprites.getWidth(),sprites.getHeight(),null);
			
			else if(navigation.getPastPlayer2Action() == navigation.getPlayerLookDown())
				graphics.drawImage(sprites.getWhiteBombermanSprite(5), player2.getX(),player2.getY(), sprites.getWidth(),sprites.getHeight(),null);
			
			else if(navigation.getPastPlayer2Action() == navigation.getPlayerLookLeft())
				graphics.drawImage(sprites.getWhiteBombermanSprite(7), player2.getX(),player2.getY(), sprites.getWidth(),sprites.getHeight(),null);
			
			else if(navigation.getPastPlayer2Action() == navigation.getPlayerLookRight())
				graphics.drawImage(sprites.getWhiteBombermanSprite(8), player2.getX(),player2.getY(), sprites.getWidth(),sprites.getHeight(),null);
			
		}
	}
	
	public void drawGrid(){
		
		for(int x = 0;x < 13;x++)
		{
			for(int y = 0;y < 15;y++)
			{
				if(levelLayout[x][y].getIsSolid() == true)
				{
					if(levelLayout[x][y].isSnowball())
					{
						graphics.drawImage(sprites.getSnowball(), levelLayout[x][y].getX(),levelLayout[x][y].getY(),tiles.getWidth(),tiles.getHeight(),null);
					
					}	
				
				}
				else if(levelLayout[x][y].isFirePower())
					graphics.drawImage(sprites.getFirePower(), levelLayout[x][y].getX()+1,levelLayout[x][y].getY()+1,38,38,null);
				
				else if(levelLayout[x][y].isBombPower())
					graphics.drawImage(sprites.getBombPower(), levelLayout[x][y].getX()+1,levelLayout[x][y].getY()+1,38,38,null);
				
				else if(levelLayout[x][y].isSpeedPower())
					graphics.drawImage(sprites.getSpeedPower(), levelLayout[x][y].getX()+1,levelLayout[x][y].getY()+1,38,38,null);
				
				else if(levelLayout[x][y].isCool())
					graphics.drawImage(sprites.getExtraPowerUp(), levelLayout[x][y].getX()+1,levelLayout[x][y].getY()+1,38,38,null);
					
			}
		}
		
	}
	public void drawBomb(){
		
		BufferedImage bomb = sprites.getBomb(1);;
		
		for(int x = 0;x < 13;x++)
		{
			for(int y = 0;y < 15;y++)
			{
				if(levelLayout[x][y].isBomb() == true)
				{	
					if(bombLayout[x][y].getCounter(System.currentTimeMillis()) == 0 )
						bomb = sprites.getBomb(1);
					
					else if(bombLayout[x][y].getCounter(System.currentTimeMillis()) == 1 )
						bomb = sprites.getBomb(2);
				
					else if(bombLayout[x][y].getCounter(System.currentTimeMillis()) >= 2 )
					{
						bomb = sprites.getBomb(3);
						
						if(bombLayout[x][y].getCounter(System.currentTimeMillis()) >=3 )
						{						
							drawExplosions(x,y);
							
								if(bombLayout[x][y].getCounter(System.currentTimeMillis()) >3 )
								{
									int ID = 1;
										if(bombLayout[x][y].getID() == 1){
											player.setTempBombCount(player.getTempBombCount()+1);
											ID = player.getBombRadius();
										}
										else if(bombLayout[x][y].getID() == 2){
											player2.setTempBombCount(player2.getTempBombCount()+1);	
											ID = player2.getBombRadius();
										}
									levelLayout[x][y].setBomb(false);
								    drawExplosions(x,y);
							
								    updateBombUp(x,y,ID);
								    updateBombDown(x,y,ID);
								    updateBombLeft(x,y,ID);
								    updateBombRight(x,y,ID);
							   
							   }
						   continue;
					   }
						
					}
					 
					graphics.drawImage(bomb,levelLayout[x][y].getX()+10,levelLayout[x][y].getY()+10,20,20,null);
				
				}
				
			}
			
		}
		
	}
	public void drawScore(){
		
		String test = Integer.toString(blackScore);
		String test1 = Integer.toString(whiteScore);
		
		Font serif = new Font("Serif",Font.PLAIN,25) ;
		graphics.setFont(serif);
		graphics.setColor(new Color(0,255,205));
		graphics.drawString(test,125, 90);
		graphics.drawString(test1,125,130);
		
	}
	public void drawGameTimer(){
		
		int timer = (int)(this.gameTimer/1000) + 180;
		String test = Integer.toString(timer - (int)(System.currentTimeMillis()/1000));
		
		Font serif = new Font("Serif",Font.PLAIN,25) ;
		graphics.setFont(serif);
		graphics.setColor(new Color(0,255,205));
		graphics.drawString(test,125, 304);
		
		int value = Integer.parseInt(test);
		
			if(value <= 0)
			{
				graphics.drawImage(sprites.getRedirect(),150,50,500,500,null);
			
					if(value < 0)
					{
						misceallanous.updateHighscoresFile(blackScore, whiteScore);
						restart();
						music.gameClose();
						menuMusic();
						updateGameState(GAME_RATING);
					}
			
			}
			
	}
	
	// menu related
	public void drawMainMenu(){
		drawStockScreen();
		drawFanwork();
		drawMainMenuPlayButton();
		drawMainMenuInstructionButton();
		drawMainMenuRatingsButton();
		drawMainMenuHighscoresButton();
		drawMainMenuCredentialsButton();
		drawMainMenuExitButton();	
	}	
	public void drawStockScreen(){
		graphics.drawImage(sprites.getMainMenuNormal(),250,-50,500,625,null);
	}
	public void drawFanwork(){
		graphics.drawImage(sprites.getFanwork(),-190,0,820,586,null);
	}
	
	public boolean checkMainMenuPlayButton(){
		
		Rectangle2D playButton = new Rectangle2D.Double(355,153,300,40);
		
		if(playButton.contains(mouse.getMouse()))
		{
			gameTimer =  System.currentTimeMillis();
			return true;
		}
		else
			return false;
		
	}
	public void drawMainMenuPlayButton(){
		
		if(checkMainMenuPlayButton())
		{
			graphics.drawImage(sprites.getMainMenuPlay(),250,-50,500,625,null);
			
				if(checkMouseClick())
				{
					music.menuClose();
					gameMusic();
					updateGameState(GAME_PLAY);
				}
		}
	}
	
	public boolean checkMainMenuInstructionsButton(){
		
		Rectangle2D instructionsButton = new Rectangle2D.Double(355,194,300,42);
		
		if(instructionsButton.contains(mouse.getMouse()))
			return true;
		
		else
			return false;
		
	}
	public void drawMainMenuInstructionButton(){
		
		if(checkMainMenuInstructionsButton())
		{
			graphics.drawImage(sprites.getMainMenuInstructions(),250,-50,500,625,null);
			
			if(checkMouseClick())
				updateGameState(GAME_INSTRUCTION);
		}
		
	}
	
	public boolean checkMainMenuRatingsButton(){
		
		Rectangle2D ratingsButton = new Rectangle2D.Double(355,237,300,46);
		
		if(ratingsButton.contains(mouse.getMouse()))
			return true;
		
		else
			return false;
		
	}
	public void drawMainMenuRatingsButton(){
		
		if(checkMainMenuRatingsButton())
		{
			graphics.drawImage(sprites.getMainMenuRatings(),250,-50,500,625,null);
			
				if(checkMouseClick())
					updateGameState(GAME_RATING);
		}
		
	}	
	
	public boolean checkMainMenuHighscoresButton(){
		
		Rectangle2D highscoresButton = new Rectangle2D.Double(355,284,300,44);
		
		if(highscoresButton.contains(mouse.getMouse()))
			return true;
		
		else
			return false;
		
	}
	public void drawMainMenuHighscoresButton(){
		
		if(checkMainMenuHighscoresButton())
		{
			graphics.drawImage(sprites.getMainMenuHighscores(),250,-50,500,625,null);
			
			if(checkMouseClick())
				updateGameState(GAME_HIGHSCORE);
		}
		
	}
	
	public boolean checkMainMenuCredentialsButton(){
		
		Rectangle2D credentialsButton = new Rectangle2D.Double(355,329,300,44);
		
		if(credentialsButton.contains(mouse.getMouse()))
			return true;
		
		else
			return false;
		
	}
	public void drawMainMenuCredentialsButton(){
		
		if(checkMainMenuCredentialsButton())
		{
			graphics.drawImage(sprites.getMainMenuCredentials(),250,-50,500,625,null);
			
			if(checkMouseClick())
				updateGameState(GAME_CREDENTIALS);
		}
		
	}
	
	public boolean checkMainMenuExitButton(){
		
		Rectangle2D exitButton = new Rectangle2D.Double(355,374,300,44);
		
		if(exitButton.contains(mouse.getMouse()))
			return true;
		
		else
			return false;
		
	}
	public void drawMainMenuExitButton(){
		
		if(checkMainMenuExitButton())
		{
			graphics.drawImage(sprites.getMainMenuExit(),250,-50,500,625,null);
			
			if(checkMouseClick())
				System.exit(0);
		}
		
	}

	public void drawInstruction(){
		drawInstructionsScreenBackground();
		drawMenuReturn();
	}
	public void drawInstructionsScreenBackground(){
		graphics.drawImage(sprites.getInstructionsScreen(),100,0,650,550,null);
	}
	
	public void drawRatings(){
		
		Rectangle2D test1 = new Rectangle2D.Double(250,325,70,70);
		Rectangle2D test2 = new Rectangle2D.Double(320,325,70,70);
		Rectangle2D test3 = new Rectangle2D.Double(390,325,70,70);
		Rectangle2D test4 = new Rectangle2D.Double(460,325,70,70);
		Rectangle2D test5 = new Rectangle2D.Double(530,325,70,70);
		
		
		if(test2.contains(mouse.getMouse())){
			graphics.drawImage(sprites.getStars(2),100,0,650,550,null);
			if(mouse.getMouseClick() && ratingUpdate == false){
				currentRating += 2;
				amountOfPeople += 1;
				misceallanous.updateRatingFile(currentRating, amountOfPeople);
				ratingUpdate = true;
				ratingTimer = System.currentTimeMillis();
			}
			
		}
		else if(test3.contains(mouse.getMouse())){
			graphics.drawImage(sprites.getStars(3),100,0,650,550,null);
			if(mouse.getMouseClick() && ratingUpdate == false){
				currentRating += 3;
				amountOfPeople += 1;
				misceallanous.updateRatingFile(currentRating, amountOfPeople);
				ratingUpdate = true;
				ratingTimer = System.currentTimeMillis();
			}
		}
		else if(test4.contains(mouse.getMouse())){
			graphics.drawImage(sprites.getStars(4),100,0,650,550,null);
			if(mouse.getMouseClick() && ratingUpdate == false){
				currentRating += 4;
				amountOfPeople += 1;
				misceallanous.updateRatingFile(currentRating, amountOfPeople);
				ratingUpdate = true;
				ratingTimer = System.currentTimeMillis();
			}
			
		}
		else if(test5.contains(mouse.getMouse())){
			graphics.drawImage(sprites.getStars(5),100,0,650,550,null);
			if(mouse.getMouseClick() && ratingUpdate == false){
				currentRating += 5;
				amountOfPeople += 1;
				misceallanous.updateRatingFile(currentRating, amountOfPeople);
				ratingUpdate = true;
				ratingTimer = System.currentTimeMillis();
			}
			
		}
		else{
		graphics.drawImage(sprites.getStars(1),100,0,650,550,null);
			if(test1.contains(mouse.getMouse())){
				if(mouse.getMouseClick() && ratingUpdate == false){
					currentRating += 1;
					amountOfPeople += 1;
					misceallanous.updateRatingFile(currentRating, amountOfPeople);
					ratingUpdate = true;
					ratingTimer = System.currentTimeMillis();
				}
			}
		}
	
		
		drawCurrentRating();
		drawMenuReturn();
	}
	public void drawCurrentRating(){
		graphics.setColor(new Color(0,255,205));
		Font serif = new Font("Serif", Font.PLAIN, 40); //creates a font object (type, style, size)
		graphics.setFont(serif); //sets the font to serif
		graphics.drawString(currentRating/amountOfPeople + "/5",WIDTH/2-60,100 + 40);
	}
	
	public void drawHighscoresScreen(){
		drawHighscoresScreenBackground();
		drawHighscoresList();
		drawMenuReturn();
	}
	public void drawHighscoresScreenBackground(){
		graphics.drawImage(sprites.getHighscoresScreen(),100,0,650,550,null);
	}
	public void drawHighscoresList(){
		String[] highscoresNameList = misceallanous.getHighScoresNameList();
		int[] highscoresList = misceallanous.getHighScores();
		
			for(int i = 0;i < 10;i++)
			{
				graphics.setColor(new Color(0,255,205));
				Font serif = new Font("Serif", Font.PLAIN, 40); //creates a font object (type, style, size)
				graphics.setFont(serif); //sets the font to serif
				graphics.drawString(highscoresNameList[i] +"  "+  highscoresList[i],WIDTH/4,105 + i*41);
			}
			
	}
	
	public void drawCredentialsScreen(){
		drawCredentialsScreenBackground();
		drawMenuReturn();
	}
	public void drawCredentialsScreenBackground(){
		graphics.drawImage(sprites.getCredentialsScreen(),100,0,650,550,null);
	}
	
	public void drawMenuReturn(){
		
		Rectangle2D menuReturn = new Rectangle2D.Double(680,560,120,50);
		
		if(menuReturn.contains(mouse.getMouse()) == true)
		{
			graphics.drawImage(sprites.getMenuReturnImage(true),670,560,120,50,null);
			
			if(mouse.getMouseClick() == true)
			{
				if(gameState == GAME_PLAY)
				{
					updateGameState(GAME_POPUP);
					return;
				}
				gameState = GAME_MENU;
			}
		}
		else 
			graphics.drawImage(sprites.getMenuReturnImage(false),670,560,120,50,null);
		
	}
	
	public void drawPopup(){
		
		Rectangle2D yesBox = new Rectangle2D.Double(250,352,115,20);
		Rectangle2D noBox = new Rectangle2D.Double(432,352,115,20);	
		
		
		if(yesBox.contains(mouse.getMouse()))
		{
			graphics.drawImage(sprites.getPopup(2),150,50,500,500,null);
				if(mouse.getMouseClick())
				{
					updateGameState(GAME_MENU);
					music.gameClose();
					menuMusic();
					restart();
				}
		}
		else if(noBox.contains(mouse.getMouse()))
		{
				graphics.drawImage(sprites.getPopup(3),150,50,500,500,null);
					if(mouse.getMouseClick())
						updateGameState(GAME_PLAY);
		}
		else
			graphics.drawImage(sprites.getPopup(1),150,50,500,500,null);
		
	}

	// general
	public void drawBackground(){
		graphics.drawImage(sprites.getBackground(),0,0,800,600,null);
	}
			
	// run methods	
	public static void main(String[] args) {
		Bomberman game = new Bomberman();
		game.frame.setResizable(false);
		game.frame.add(game); //game is a component because it extends Canvas
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);		
		game.start();
	}
	
	public Bomberman() {
		Dimension size = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(size);
		frame = new JFrame();
		//KEYBOARD and MOUSE handling code goes here
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addKeyListener(navigation);
	}

	//starts a new thread for the game
	public synchronized void start() {
		thread = new Thread(this, "Game");
		running = true;
		thread.start();
	}
	
	//main game loop
	 public void run() {
		   init();
		   long startTime = System.nanoTime();
		   double ns = 1000000000.0 / UPS;
		   double delta = 0;
		   int frames = 0;
		   int updates = 0;
		   
		   long secondTimer = System.nanoTime();
		   while(running) {
		    long now = System.nanoTime();
		    delta += (now - startTime) / ns;
		    startTime = now;
		    while(delta >= 1) {
		     update();
		     delta--;
		     updates++;
		    }
		    render();
		    frames++;
		    
		    if(System.nanoTime() - secondTimer > 1000000000) {
		     this.frame.setTitle(updates + " ups  ||  " + frames + " fps");
		     secondTimer += 1000000000;
		     frames = 0;
		     updates = 0;
		    }
		   }
		   running = false;
		   misceallanous.closeFile();
		   System.exit(0);
		  }

	
	public void render() {
		BufferStrategy bs = getBufferStrategy(); //method from Canvas class
		
		if(bs == null) {
			createBufferStrategy(3); //creates it only for the first time the loop runs (trip buff)
			return;
		}
		
		graphics = (Graphics2D)bs.getDrawGraphics();
		draw();
		graphics.dispose();
		bs.show();
	}
	
	//stops the game thread and quits
		public synchronized void stop() {
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
}
