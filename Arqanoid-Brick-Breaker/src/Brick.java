import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Brick {
	private int x , y;
	private final int brickHeight = 20;
	private final int brickWidth = 56;
	private boolean isAlive = true;
	private Color colour;
	
	public Brick(int x, int y, Color colour){
		this.x = x;
		this.y = y;
		this.colour = colour;
		
	}
	
	//instance methods
	public Rectangle2D getBoundaryRectangle() {
		Rectangle2D boundaryBrick = new Rectangle2D.Double(x,y,brickWidth,brickHeight);
		return boundaryBrick;
	}
	public Rectangle2D getTopBounds(){
		Rectangle2D topBounds = new Rectangle2D.Double(x,y,brickWidth,1);

		return topBounds;
	}
	public Rectangle2D getBottomBounds(){
		Rectangle2D bottomBounds = new Rectangle2D.Double(x,y+brickHeight,brickWidth,1);

		return bottomBounds;
	}
	public Rectangle2D getLeftBounds(){
		Rectangle2D leftBounds = new Rectangle2D.Double(x,y,1,brickHeight);
		

		return leftBounds;
	}
	public Rectangle2D getRightBounds(){
		Rectangle2D rightBounds = new Rectangle2D.Double(x+brickWidth,y,1,brickHeight);

		return rightBounds;
	}
	
	//getters and setters
	public int getX(){
		return x;
	}
	public void setX(int x){
		this.x = x;
	}
	
	public int getY(){
		return y;
	}
	public void setY(int y){
		this.y = y;
	}

	public int getHeight(){
		return this.brickHeight;
	}
	public int getWidth(){
		return this.brickWidth;
	}
	
	public boolean isAlive(){
		
		if(this.isAlive == true)
			return true;
		else
			return false;
	}	
	public void setAlive(boolean life){
		this.isAlive =  life;
	}

	public Color getColour(){
		return this.colour;
	}
	public void setColour(Color currentBrickColour){
		this.colour = currentBrickColour ;
	}
	
}
