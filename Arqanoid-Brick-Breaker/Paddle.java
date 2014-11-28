import java.awt.Color;
import java.awt.geom.Rectangle2D;



public class Paddle {

	private int x,y;
	private int velocityX = 0;
	private int height ,width ;
	private Color colour = Color.white;
	
	public Paddle(int x, int y, int height, int width){
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}
	
	// instance methods
	public Rectangle2D getBoundaryRectangle(){
		Rectangle2D paddleBoundary = new Rectangle2D.Double(x,y,width,height);
		return paddleBoundary;
	}

	// getters and setters
	public int getX(){
		return this.x;
	}
	public void setX(int x){
		this.x = x;
	}
	
	public int getY(){
		return this.y;
	}
	public void setY(int y){
		this.y = y;
	}

	public int getHeight(){
		return this.height;
	}
	public void setHeight(int height){
		this.height = height;
	}

	public int getWidth(){
		return this.width;
	}
	public void setWidth(int width){
		this.width = width;
	}

	public int getVelocityX(){
		return this.velocityX;
	}
	public void setVelocityX(int velX){
		this.velocityX = velX;
	}

	public Color getColour(){
		return this.colour;
	}
	public void setColour(Color colour){
		this.colour = colour;
	}
}
