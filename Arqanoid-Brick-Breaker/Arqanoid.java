//William Granados
//Wednesday March 20th, 2013
//Arqanoid project, a simple brick breaker game

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Arqanoid extends JPanel implements Runnable, KeyListener{
	private MainPanel main;
	public final static int MAXWIDTH = 1024, MAXHEIGHT = 768; //width & height of JPanel
	private boolean running; //keeps track of state of the program
	private Thread thread;
	private Graphics2D graphics;
	private Image image; //used for double buffering
	
	//game objects
	private LevelLoader levelLoad;
	private Brick[][] brickGrid;
	private Paddle paddle;
	private Ball ball; 	
	private Sprite sprite;
	private Mouse mouse = new Mouse();
	private Highscores highscores ;
	//keyboard variables
	private boolean[] keysPressed = new boolean[4];
	private final int KEY_LEFT = 0, KEY_RIGHT = 1, KEY_SPACE = 2, KEY_ENTER = 3;
	
	//ball states
	private int ballState;
	private final static int BALL_START = 0;
	private final static int BALL_MOVING = 1;
	
	//game states
	private int gameState;
	private final static int GAME_START = 0;
	private final static int GAME_PLAY = 1;
	private final static int GAME_LOSE = 2;
	private final static int GAME_HIGHSCORES = 3;
	
	//game components
	private int score;
	private int lives;
	private int level;
	private int[] highscoresList = new int[10];
	
	
	
	public Arqanoid(MainPanel main) {
		this.setDoubleBuffered(false); //we'll use our own double buffering method
		this.setBackground(Color.black);
		this.setPreferredSize(new Dimension(MAXWIDTH, MAXHEIGHT));
		this.setFocusable(true);
		this.requestFocus();
		addKeyListener(this);
		addMouseMotionListener(mouse);
		addMouseListener(mouse);
		this.main = main;
	}
	
	public static void main(String[] args) {
		new MainPanel();
	}
	
	public void addNotify() {
		super.addNotify();
		startGame();
	}
	
	public void stopGame() {
		running = false;
	}
	
	//Creates a thread and starts it
	public void startGame() {
		if (thread == null || !running) {
			thread = new Thread(this);
		}
		thread.start(); //calls run()
	}
	
	public void run() {
		running = true;

		//initialize all objects, load level, etc
		init();
		
		//game loop
		while (running) {
			createImage(); //creates image for double buffering
			
			if(gameState != GAME_HIGHSCORES){
				updateGame();
			}
			else
				updateHighscores();
			
			mouse.setMouseClick(false);
			
			drawImage(); //draws on the JPanel
		}
		
		levelLoad.closeFile();
		System.exit(0);
	}
	
	public void init() {
		initializeGame();
		initializeLevelGrid();
		initializeHighscores();
		initializePaddle();
		initializeBall();
		initializeSprites();
	}
	public void initializeGame() {
		score = 0;
		lives = 3;
		level = 1;
	}
	public void initializeLevelGrid() {
		levelLoad = new LevelLoader();
		System.out.println(level);
		if(level == 1)
			levelLoad.openLevelFile("level1.txt");
		else if(level == 2)
			levelLoad.openLevelFile("level2.txt");
		
		levelLoad.createLevelGrid();
		brickGrid = levelLoad.getBrickGrid();
		
	}
	public void initializePaddle() {
		paddle = new Paddle(MAXWIDTH/2 - 75, 700, 25, 150);
	}
	public void initializeBall() {
		ballState = BALL_START;
		ball = new Ball(MAXWIDTH/2 - 7, 686, 14, 14, Color.white);
	}
	public void initializeSprites(){
		sprite = new Sprite();
	}
	public void initializeHighscores(){
		highscores = new Highscores();
		highscores.openHighscoresFile("Highscores/Highscores.txt");
		highscores.createHighscoresList();
		
		highscoresList = highscores.getHighScores();
	}
	
	//updates
	public void updateGame() {
		drawBackground();
				
	
		drawLevelGrid(); //do this
		
		updateGameState();
		
		updatePaddle();
		drawPaddle();
		
		updateBall();
		checkForCollisions();
		drawBall();

		
		drawScore();
		
	}
	public void updateHighscores(){
		drawBackground();
		drawHighscores();
		
		drawGameButton(checkGameButton());
	}
	
	public void updateGameState() {
		if(lives <= 0) {
			gameState = GAME_LOSE;
		}
		
		if(gameState == GAME_START) {
			initializeGame();
			initializeLevelGrid();
			gameState = GAME_PLAY;
		}
		else if(gameState == GAME_LOSE) {
			drawLoseScreen();
			if(keysPressed[KEY_SPACE]) {
				gameState = GAME_START;
				lives = 3;
				level = 1;
			}
			
		}
		
		
		if(winConditionReached()) {
			gameState = GAME_PLAY;
			level++;
			initializeLevelGrid();
			resetBall();
			highscores.updateHighscoresFile(score);
		}
	}
	public void updatePaddle() {
		
		if(keysPressed[0] == true && paddle.getX() > 0)//left movement
			paddle.setVelocityX(-7);
		else if(keysPressed[1] == true && paddle.getX()+ paddle.getWidth() < MAXWIDTH)//right movement
			paddle.setVelocityX(7);
		else
			paddle.setVelocityX(0);// no movement
		
		//updates paddle
		paddle.setX(paddle.getX() + paddle.getVelocityX());
	}
	public void updateBall() {
		if(ballState == BALL_START) {
			resetBall();
		}
		else if(ballState == BALL_MOVING) {
			if(ball.getX() + ball.getVelocityX() >= MAXWIDTH)
				ball.flipVelocityX();
			else if(ball.getX() + ball.getVelocityX() < 0)
				ball.flipVelocityX();
			ball.setX(ball.getX() + ball.getVelocityX());
			
			if(ball.getY() + ball.getVelocityY() >= MAXHEIGHT) {
				lives--;
				
				resetBall();
			}
			else if(ball.getY() + ball.getVelocityY() < 0)
				ball.flipVelocityY();
			ball.setY(ball.getY() + ball.getVelocityY());
		}
	}
	public void updateScore() {
		score += 100;
	}
	public boolean winConditionReached() {
		
		// returns false if any bricks are alive
		boolean winCondition = true;
		
		for(int row=0; row < brickGrid.length; row++) {
			for(int col=0; col < brickGrid[0].length; col++) {
				if(brickGrid[row][col] != null){
					winCondition = false;
					break;
				}
					
			}
		}
		
		return winCondition;
		
	}
	
	public void resetBall() {
		ball.setX(paddle.getX() + paddle.getWidth()/2);
		ball.setY(686);
		ball.setVelocityX(5);
		ball.setVelocityY(-5);
		ballState = BALL_START;
	}
	public void brickLocationWithBall(Brick currentBrick) {
		currentBrick.setAlive(false);
		Rectangle2D top = currentBrick.getTopBounds();
		Rectangle2D bottom = currentBrick.getBottomBounds();
		Rectangle2D left = currentBrick.getLeftBounds();
		Rectangle2D right = currentBrick.getRightBounds();

		Rectangle2D ballBounds = currentBrick.getBoundaryRectangle();
		if(ballBounds.intersects(top))
			ball.flipVelocityY();
		else if(ballBounds.intersects(bottom))
			ball.flipVelocityY();
		else if(ballBounds.intersects(left))
			ball.flipVelocityX();
		else if(ballBounds.intersects(right))
			ball.flipVelocityX();
	}	
	
	public void checkForCollisions() {
		checkForPaddleCollision();
		checkForBrickCollision();
	}
	public void checkForPaddleCollision() {
		
		// ball rebounds if collision is made
			if( ball.getBoundaryRectangle().intersects(paddle.getBoundaryRectangle()) == true)
				ball.flipVelocityY();	
		
	}
	public void checkForBrickCollision() {
		
		/* transverses through BrickGrid
		 * if ball makes contact with sides x-velocity is flipped
		 * if ball makes contact with top or bottom y-velocity is flipped
		 * */
		for(int row=0; row < brickGrid.length; row++) 
		{
			for(int col=0; col < brickGrid[0].length; col++) 
			{
				if(brickGrid[row][col] != null)
				{
					if(brickGrid[row][col].getBoundaryRectangle().intersects(ball.getBoundaryRectangle()) ){
												
						if(brickGrid[row][col].getTopBounds().intersects(ball.getBoundaryRectangle()) || brickGrid[row][col].getBottomBounds().intersects(ball.getBoundaryRectangle()) )
							ball.flipVelocityY();		
						else if(brickGrid[row][col].getLeftBounds().intersects(ball.getBoundaryRectangle())|| brickGrid[row][col].getRightBounds().intersects(ball.getBoundaryRectangle()) )
								ball.flipVelocityX();
						
						brickGrid[row][col] = null;
						updateScore();
					}
				}
			}
		}
		
	}
	public boolean checkHighscoresButton(){
		Rectangle2D boundary = new Rectangle2D.Double(900,9,120,25);
		
		if(boundary.contains(mouse.getMouse())){
			if(mouse.getMouseClick()){
				gameState = GAME_HIGHSCORES;
			}
			return true;
		}
		else 
			return false;
	}
	public void drawHighscoresButton(boolean hover){	
		graphics.drawImage(sprite.getHighscoresButton(hover), 900,0,120,50,null);
	}
	
	public boolean checkGameButton(){
		Rectangle2D boundary = new Rectangle2D.Double(900,9,120,25);
		
		if(boundary.contains(mouse.getMouse())){
			if(mouse.getMouseClick()){
				gameState = GAME_LOSE;
			}
			return true;
		}
		else 
			return false;
	}
	public void drawGameButton(boolean hover){
		graphics.drawImage(sprite.getGameButton(hover), 900,0,120,50,null);
		
	}
	//draw
	public void drawBackground() {
		
		//makes black background equivalent to application dimensions
		graphics.drawImage(sprite.getBackground(),0,0,MAXWIDTH,MAXHEIGHT,null);
	}
	public void drawLevelGrid() {
		
		//draws bricks using drawBrick method
		for(int row=0; row < brickGrid.length; row++) {
			for(int col=0; col < brickGrid[0].length; col++) {
				if(brickGrid[row][col] != null)
					drawBrick(row, col);
			}
		}
	}
	public void drawPaddle() {
		 
		//makes paddle object then prints on screen
		
		graphics.drawImage(sprite.getPaddle(), paddle.getX(), paddle.getY(), paddle.getWidth(),paddle.getHeight(),null);
	}
	public void drawBall() {
		
		//creates new ball then prints on screen
		
		graphics.drawImage(sprite.getBall(),ball.getX()+ball.getVelocityX(), ball.getY() + ball.getVelocityY(), null);
	}	
	public void drawBrick(int row, int col) {
		Brick currentBrick = brickGrid[row][col];
		if(currentBrick.isAlive()) {
			int x = currentBrick.getX();
			int y = currentBrick.getY();
			int w = currentBrick.getWidth();
			int h = currentBrick.getHeight();
			
			if(currentBrick.getColour() == Color.blue){
				graphics.drawImage(sprite.getBlueBrick(),x,y,w,h,null);
			}
			else if(currentBrick.getColour() == Color.green){
				graphics.drawImage(sprite.getGreenBrick(),x,y,w,h,null);
			}
			else if(currentBrick.getColour() == Color.red){
				graphics.drawImage(sprite.getRedBrick(),x,y,w,h,null);
			}
			
		
		}
	}
	
	public void drawHighscores(){
		graphics.drawImage(sprite.getHighscoresScreen(),200,50,650,550,null);
		
		for(int i = 0;i < 10;i++){
			String scores = Integer.toString(highscoresList[i]);
			Font serif = new Font("Serif",Font.PLAIN,40) ;
			graphics.setFont(serif);
			graphics.setColor(Color.GRAY);
			graphics.drawString(scores, 500,150+ 40*i);
		}
		
	}
	public void drawLoseScreen() {
		
		graphics.drawImage(sprite.getLoseScreen(),MAXWIDTH/2-200,MAXHEIGHT/10,500,250,null);
		drawHighscoresButton(checkHighscoresButton());
	}
	public void drawScore() {
		
		//creates new font then prints out on screen
		String scoreBoard = "Score: " + Integer.toString(score);
		Font serif = new Font("Serif",Font.PLAIN,50) ;
		graphics.setFont(serif);
		graphics.setColor(Color.GRAY);
		graphics.drawString(scoreBoard, 25, 50);
		
		
	}
	
	
	//creates an image for double buffering
	public void createImage() {
		if (image == null) {
			image = createImage(MAXWIDTH, MAXHEIGHT);
			
			if (image == null) {
				System.out.println("Cannot create buffer");
				return;
			}
			else
				graphics = (Graphics2D)image.getGraphics(); //get graphics object from Image
		}
	}
	
	//outputs everything to the JPanel
	public void drawImage() {
		Graphics g;
		try {
			g = this.getGraphics(); //a new image is created for each frame, this gets the graphics for that image so we can draw on it
			if (g != null && image != null) {
				g.drawImage(image, 0, 0, null);
				g.dispose(); //not associated with swing, so we have to free memory ourselves (not done by the JVM)
			}
			image = null;
		}catch(Exception e) {System.out.println("Graphics objects error");}
	}
	
	//KEYLISTENER METHODS
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			keysPressed[KEY_LEFT] = true;
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			keysPressed[KEY_RIGHT] = true;
		else if(ballState == BALL_START && e.getKeyCode() == KeyEvent.VK_SPACE) {
			keysPressed[KEY_SPACE] = true;
			ballState = BALL_MOVING;
		}
		else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			keysPressed[KEY_ENTER] = true;
		}
			
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			keysPressed[KEY_LEFT] = false;
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			keysPressed[KEY_RIGHT] = false;
		else if(e.getKeyCode() == KeyEvent.VK_SPACE)
			keysPressed[KEY_SPACE] = false;
	}
	
	public void keyTyped(KeyEvent e) {}
}
