//Name: William Granados
//Date: Tuesday, June 4th, 2013
//Class description: Handles sprite loading

import java.awt.image.BufferedImage;


public class Sprite {
	
	private int brickWidth = 56;
	private int brickHeight = 20;
	
	private int ballDiameter = 14;
	
	private int paddleWidth = 150;
	private int paddleHeight = 25;
	
	private int MAXWIDTH = 1024;
	private int MAXHEIGHT = 768;
	
	// only one sheet to load all images
	SpriteSheet universalSheet = new SpriteSheet();
	
	private BufferedImage blueBrick;
	private BufferedImage greenBrick;
	private BufferedImage redBrick;
	
	private BufferedImage paddle;
	private BufferedImage ball;
	private BufferedImage background;
	private BufferedImage loseScreen;
	private BufferedImage highscoresScreen;
	
	private BufferedImage gameButton;
	private BufferedImage gameButtonHover;
	private BufferedImage highscoresButton;
	private BufferedImage highscoresButtonHover;
	
	public Sprite(){
		initBricks();
		initBall();
		initPaddle();
		initButtons();
		initBackground();
		initScreens();
	}
			
	public void initBricks(){
		universalSheet.loadSpriteSheet("Bricks/BlueBrick.png");
		blueBrick = universalSheet.getSprite(0, 0, brickWidth, brickHeight);
		
		universalSheet.loadSpriteSheet("Bricks/GreenBrick.png");
		greenBrick = universalSheet.getSprite(0, 0, brickWidth, brickHeight);
		
		universalSheet.loadSpriteSheet("Bricks/RedBrick.png");
		redBrick = universalSheet.getSprite(0, 0, brickWidth, brickHeight);
	
	}
	public void initBall(){
		universalSheet.loadSpriteSheet("Ball/ball.png");
		ball = universalSheet.getSprite(0, 0, ballDiameter, ballDiameter);
		
	}
	public void initPaddle(){
		universalSheet.loadSpriteSheet("Paddle/paddle.png");
		paddle = universalSheet.getSprite(0, 0, paddleWidth, paddleHeight);
		
	}
	public void initBackground(){
		universalSheet.loadSpriteSheet("Background/background.png");
		background = universalSheet.getSprite(0, 0, MAXWIDTH, MAXHEIGHT);
		
	}
	public void initScreens(){
		universalSheet.loadSpriteSheet("Background/Lose Screen.png");
		loseScreen = universalSheet.getSprite(0, 0, 500, 250 );
		
		//TODO
		universalSheet.loadSpriteSheet("Highscores/highscores.png");
		highscoresScreen = universalSheet.getSprite(0, 0, 650, 550 );
	}
	public void initButtons(){
		
		universalSheet.loadSpriteSheet("Buttons/gameButton.png");
		gameButton = universalSheet.getSprite(0, 0, 120, 50 );
		
		universalSheet.loadSpriteSheet("Buttons/gameButtonHover.png");
		gameButtonHover = universalSheet.getSprite(0, 0, 120, 50 );
		
		universalSheet.loadSpriteSheet("Buttons/highscoresButton.png");
		highscoresButton = universalSheet.getSprite(0, 0, 120, 50 );
		
		universalSheet.loadSpriteSheet("Buttons/highscoresButtonHover.png");
		highscoresButtonHover = universalSheet.getSprite(0, 0, 120, 50 );
	}
	
	
 	public BufferedImage getBlueBrick(){
		return blueBrick;
	}
	public BufferedImage getGreenBrick(){
		return greenBrick;
	}
	public BufferedImage getRedBrick(){
		return redBrick;
	}
	
	public BufferedImage getPaddle(){
		return paddle;
	}
	public BufferedImage getBall(){
		return ball;
	}
	public BufferedImage getBackground(){
		return background;
	}
	public BufferedImage getLoseScreen(){
		return loseScreen;
	}
	public BufferedImage getHighscoresScreen(){
		return highscoresScreen;
	}
	
	public BufferedImage getHighscoresButton(boolean hover){
		if(hover)
			return highscoresButtonHover;
		else 
			return highscoresButton;
	}
	public BufferedImage getGameButton(boolean hover){
		if(hover)
			return gameButtonHover;
		else 
			return gameButton;
	}
}
