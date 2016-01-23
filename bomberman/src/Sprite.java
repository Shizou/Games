//Name: William Granados
//Date: Tuesday, June 4th, 2013
//Class description: Handles sprite loading

import java.awt.image.BufferedImage;


public class Sprite {
	
	// dimensions
	private final int playerHeight = 31;
	private final int playerWidth = 24;
	private final int layoutHeight = 520;
	private final int layoutWidth = 600;
	
	// only one sheet to load all images
	SpriteSheet universalSheet = new SpriteSheet();
	
	//menu images
	private BufferedImage mainMenuNormal;
	private BufferedImage mainMenuPlay;
	private BufferedImage mainMenuInstructions;
	private BufferedImage mainMenuRatings;
	private BufferedImage mainMenuHighscores;
	private BufferedImage mainMenuCredentials;
	private BufferedImage mainMenuExit;
	private BufferedImage mainMenuFanwork;
	
	//title
	private BufferedImage title;
	
	//background
	private BufferedImage background;
	
	//menu states
	private BufferedImage highscoresScreen;
	private BufferedImage instructionScreen;
	private BufferedImage credentialScreen;
	private BufferedImage ratingScreen;
	
	//redirections
	private BufferedImage menuReturn, menuReturnHover;
	private BufferedImage menuPopup, menuPopupNo, menuPopupYes;
	private BufferedImage redirect;
	
	// stars
	private BufferedImage stars1,stars2,stars3,stars4,stars5;
	
	// ingame mechanics
	private BufferedImage snowball; 
	private BufferedImage layoutGrid;
	private BufferedImage gameScore;
	
	//bombs
	private BufferedImage bombStateOne, bombStateTwo, bombStateThree;
	
	//explosion
	private BufferedImage explosion;
	
	//power ups
	private BufferedImage firePowerUp, speedPowerUp, bombPowerUp, extraPowerUp;
	
	//black player sprites
	private BufferedImage blackLookNormal, blackPlayerDown;
	private BufferedImage blackLookBack, blackPlayerUp;
	private BufferedImage blackLookLeft, blackPlayerLeft;
	private BufferedImage blackLookRight, blackPlayerRight;
	
	//white player sprites
	private BufferedImage whiteLookNormal,whitePlayerDown;
	private BufferedImage whiteLookBack, whitePlayerUp;
	private BufferedImage whiteLookLeft,whitePlayerLeft;
	private BufferedImage whiteLookRight,whitePlayerRight;
	
	public Sprite(){
		initTitle();
		initMenu();
		initMenuScreens();
		initPlayerSprites();
		initGrid();
	}
	
	//intializers
	public void initTitle(){
		universalSheet.loadSpriteSheet("Resources/Title/title.png");
		title = universalSheet.getSprite(0, 0, 800, 600);
	}
	public void initMenu(){
		initNormal();
		initPlay();
		initInstructions();
		initRatings();
		initHighscores();
		initCredentials();
		initExit();
		initFanwork();
	}
		public void initNormal(){
		universalSheet.loadSpriteSheet("Resources/MenuScreen/Menu.png");
		mainMenuNormal = universalSheet.getSprite(0, 0, 500, 625);
		}
		public void initPlay(){
		universalSheet.loadSpriteSheet("Resources/MenuScreen/Play.png");
		mainMenuPlay = universalSheet.getSprite(0, 0, 500, 625);
	}
		public void initInstructions(){
		universalSheet.loadSpriteSheet("Resources/MenuScreen/Instructions.png");
		mainMenuInstructions = universalSheet.getSprite(0, 0, 500, 625);
	}
		public void initRatings(){
		universalSheet.loadSpriteSheet("Resources/MenuScreen/Ratings.png");
		mainMenuRatings = universalSheet.getSprite(0, 0, 500, 625);
	}
		public void initHighscores(){
		universalSheet.loadSpriteSheet("Resources/MenuScreen/Highscores.png");
		mainMenuHighscores = universalSheet.getSprite(0, 0, 500, 625);
	}
		public void initCredentials(){
		universalSheet.loadSpriteSheet("Resources/MenuScreen/Credentials.png");
		mainMenuCredentials = universalSheet.getSprite(0, 0, 500, 625);
	}
		public void initExit(){
		universalSheet.loadSpriteSheet("Resources/MenuScreen/Exit.png");
		mainMenuExit = universalSheet.getSprite(0, 0, 500, 625);
	}
		public void initFanwork(){
		universalSheet.loadSpriteSheet("Resources/MenuScreen/Bomberman2.png");
		mainMenuFanwork = universalSheet.getSprite(0, 0, 820,586);
	}
	
	public void initMenuScreens(){
		
		initBackground();
		initHighscoresScreen();
		initInstructionsScreen();
		initCredentialsScreen();
		initRatingScreen();
		initRedirectScreen();
		initStars();
		initMenuReturn();
		initMenuPopups();
	}	
		public void initBackground(){
		universalSheet.loadSpriteSheet("Resources/Layout sprites/background.png");
		background = universalSheet.getSprite(0, 0, 800, 600);
	}
		public void initHighscoresScreen(){
		universalSheet.loadSpriteSheet("Resources/Highscores/highscores.png");
		highscoresScreen = universalSheet.getSprite(0, 0, 650, 550);
	}
		public void initInstructionsScreen(){
		universalSheet.loadSpriteSheet("Resources/Instructions/instructions.png");
		instructionScreen = universalSheet.getSprite(0, 0, 650, 550);
	}
		public void initCredentialsScreen(){
		universalSheet.loadSpriteSheet("Resources/Credentials/Credentials.png");
		credentialScreen = universalSheet.getSprite(0, 0, 650, 550);
	}
		public void initRatingScreen(){
		universalSheet.loadSpriteSheet("Resources/Rating/Ratings.png");
		ratingScreen = universalSheet.getSprite(0, 0, 650, 550);
	}
		public void initRedirectScreen(){
		universalSheet.loadSpriteSheet("Resources/Rating/redirect.png");
		redirect = universalSheet.getSprite(0, 0, 500,500);
	}
		public void initStars(){
		universalSheet.loadSpriteSheet("Resources/Rating/Option1.png");
		stars1 = universalSheet.getSprite(0,0,650,550);
		universalSheet.loadSpriteSheet("Resources/Rating/Option2.png");
		stars2 = universalSheet.getSprite(0,0,650,550);
		universalSheet.loadSpriteSheet("Resources/Rating/Option3.png");
		stars3 = universalSheet.getSprite(0,0,650,550);
		universalSheet.loadSpriteSheet("Resources/Rating/Option4.png");
		stars4 = universalSheet.getSprite(0,0,650,550);
		universalSheet.loadSpriteSheet("Resources/Rating/Option5.png");
		stars5 = universalSheet.getSprite(0,0,650,550);
	}
		public void initMenuReturn(){
		universalSheet.loadSpriteSheet("Resources/Menu return/Menu-Return-Hover.png");
		menuReturn = universalSheet.getSprite(0, 0, 120, 50);
		
		universalSheet.loadSpriteSheet("Resources/Menu return/Menu-Return.png");
		menuReturnHover = universalSheet.getSprite(0, 0, 120, 50);
	}
		public void initMenuPopups(){
		universalSheet.loadSpriteSheet("Resources/Menu return/popup.png");
		menuPopup = universalSheet.getSprite(0, 0, 500, 500);
		
		universalSheet.loadSpriteSheet("Resources/Menu return/popupYes.png");
		menuPopupYes = universalSheet.getSprite(0, 0, 500, 500);
		
		universalSheet.loadSpriteSheet("Resources/Menu return/popupNo.png");
		menuPopupNo = universalSheet.getSprite(0, 0, 500, 500);
	}
	
	public void initPlayerSprites(){
		
		initWhiteSprites();
		initBlackSprites();
		
	}
		public void initWhiteSprites(){
	
		universalSheet.loadSpriteSheet("Resources/Bomberman sprites/white bomberman.png");
		whiteLookNormal = universalSheet.getSprite(8, 32, playerWidth, playerHeight);
		whiteLookBack = universalSheet.getSprite(8-1, 94,playerWidth, playerHeight);
		whiteLookLeft = universalSheet.getSprite(149, 125,playerWidth,playerHeight);
		whiteLookRight = universalSheet.getSprite(8, 64, playerWidth, playerHeight);
		whitePlayerUp = universalSheet.getSprite(30-5, 95, playerWidth, playerHeight);
		whitePlayerDown = universalSheet.getSprite(31-4, 33, playerWidth, playerHeight);
		whitePlayerLeft = universalSheet.getSprite(107-3, 125, playerWidth,playerHeight);
		whitePlayerRight = universalSheet.getSprite(50-3, 64, playerWidth, playerHeight);
		
	}
		public void initBlackSprites(){
		universalSheet.loadSpriteSheet("Resources/Bomberman sprites/black bomberman.png");
		blackLookNormal = universalSheet.getSprite(8, 32, playerWidth, playerHeight);
		blackLookBack = universalSheet.getSprite(8-1, 94,playerWidth, playerHeight);
		blackLookLeft = universalSheet.getSprite(149, 125,playerWidth,playerHeight);
		blackLookRight = universalSheet.getSprite(8, 60, playerWidth, playerHeight);
		blackPlayerUp = universalSheet.getSprite(31, 95-1, playerWidth, playerHeight);
		blackPlayerDown = universalSheet.getSprite(32, 33, playerWidth, playerHeight);
		blackPlayerLeft = universalSheet.getSprite(109, 125, playerWidth,playerHeight);
		blackPlayerRight = universalSheet.getSprite(54, 64-2, playerWidth, playerHeight);
		
	}
	
	public void initGrid(){
		
		initSnowball();
		initLayoutGrid();
		initBombs();
		initPowerUps();
		initGameScore();
		initExplosion();
	}
		public void initSnowball(){
		universalSheet.loadSpriteSheet("Resources/Layout sprites/snowball.png");
		snowball = universalSheet.getSprite(0, 0, 40, 40); 
			
	}
		public void initLayoutGrid(){
		universalSheet.loadSpriteSheet("Resources/Layout sprites/FinalLayout.png");
		layoutGrid = universalSheet.getSprite(0, 0,layoutWidth,layoutHeight);
		
	}
		public void initBombs(){
		universalSheet.loadSpriteSheet("Resources/Bomberman sprites/BombSprites.png");
		bombStateOne = universalSheet.getSprite(52,10,16,17);
		bombStateTwo = universalSheet.getSprite(37,11,16,17);
		bombStateThree = universalSheet.getSprite(18,11,18,18);
		
	}
		public void initPowerUps(){
		universalSheet.loadSpriteSheet("Resources/Layout sprites/powerups.png");
		firePowerUp = universalSheet.getSprite(79, 0, 38, 38);
		speedPowerUp = universalSheet.getSprite(1, 0, 38, 38);
		bombPowerUp = universalSheet.getSprite(39, 0, 38, 38);
		
		universalSheet.loadSpriteSheet("Resources/Layout sprites/extra.png");
		extraPowerUp = universalSheet.getSprite(0, 0, 38, 38);
		
	}
		public void initGameScore(){
		universalSheet.loadSpriteSheet("Resources/Layout sprites/game.png");
		gameScore = universalSheet.getSprite(0, 0, 400, 400);
			
	}
		public void initExplosion(){
		universalSheet.loadSpriteSheet("Resources/Bomberman sprites/BioExplosion.png");
		explosion = universalSheet.getSprite(0, 0, 40, 40);
		
	}
	
	// title related
	public BufferedImage getTitle(){
		return title;
	}
	//menu related
	public BufferedImage getMainMenuNormal(){
		return mainMenuNormal;
	}
	public BufferedImage getMainMenuPlay(){
		return mainMenuPlay;
	}
	public BufferedImage getMainMenuInstructions(){
		return mainMenuInstructions;
	}
	public BufferedImage getMainMenuRatings(){
		return mainMenuRatings;
	}
	public BufferedImage getMainMenuHighscores(){
		return mainMenuHighscores;
	}
	public BufferedImage getMainMenuCredentials(){
		return mainMenuCredentials;
	}
	public BufferedImage getMainMenuExit(){
		return mainMenuExit;
	}
	
	public BufferedImage getCredentialsScreen(){
		return credentialScreen;
	}
	public BufferedImage getRatingsScreen(){
		return ratingScreen;
	}
	public BufferedImage getHighscoresScreen(){
		return highscoresScreen;
	}
	public BufferedImage getInstructionsScreen(){
		return instructionScreen;
	}
	
	public BufferedImage getStars(int number){
		
		switch(number){
		case 1:return stars1;
		case 2:return stars2;
		case 3:return stars3;
		case 4:return stars4;
		case 5:return stars5;
		
		default: return null;
		}
		
		
	}
	public BufferedImage getRedirect(){
		return redirect;
	}
	
	public BufferedImage getMenuReturnImage(boolean hover){
		if(hover == true)
			return menuReturn;
		
		else
			return menuReturnHover;
		
			
	}
	public BufferedImage getBackground(){
		return background;
	}
	public BufferedImage getFanwork(){
		return mainMenuFanwork;
	}
	public BufferedImage getPopup(int image){
		
		switch(image){
		case 1: return menuPopup;	
		case 2: return menuPopupYes;
		case 3: return menuPopupNo;
		
		default: return null;
		
		}
	}
	
	//players
	public BufferedImage getWhiteBombermanSprite(int playerState){
	
		switch(playerState){
		case 1: return whitePlayerUp;
		case 2: return whitePlayerDown;
		case 3: return whitePlayerLeft;
		case 4: return whitePlayerRight;
		case 5: return whiteLookNormal;
		case 6: return whiteLookBack;
		case 7: return whiteLookLeft;
		case 8: return whiteLookRight;
		default: return null;
		}
			
		}
	public BufferedImage getBlackBombermanSprite(int playerState){
		
		switch(playerState){
		case 1: return blackPlayerUp;
		case 2: return blackPlayerDown;
		case 3: return blackPlayerLeft;
		case 4: return blackPlayerRight;
		case 5: return blackLookNormal;
		case 6: return blackLookBack;
		case 7: return blackLookLeft;
		case 8: return blackLookRight;
		default: return null;
		}
			
		}
		public int getHeight(){
		return playerHeight;
	}
		public int getWidth(){
		return playerWidth;
	}
	
	
	//in game related
	public BufferedImage getGameScore(){
		return gameScore;
	}	
	public BufferedImage getSnowball(){
		return snowball;
	}
	public BufferedImage getLayout(){
		return layoutGrid;
	}
	
	public BufferedImage getBomb(int state){
		
		switch(state){
		case 1: return bombStateOne;
		case 2: return bombStateTwo;
		case 3: return bombStateThree;
		}
		return null;
	}
	public BufferedImage getExplosion(){
		return explosion;
	}
		
	public BufferedImage getFirePower(){
		return firePowerUp;
	}
	public BufferedImage getSpeedPower(){
		return speedPowerUp;
	}
	public BufferedImage getBombPower(){
		return bombPowerUp;
	}
	public BufferedImage getExtraPowerUp(){
		return extraPowerUp;
	}
	
	}
