import java.awt.Color;
import java.awt.geom.Rectangle2D;


public class Ball {
	
		//ball variables
		private int x ,y;
		private int height,width ;
		private int velX = 0,velY = 0 ;
		
		//colour
		private Color colour = Color.white;
		
		
	public Ball(int x, int y, int height, int width, Color colour)  {
		this.x = x; this.y = y;
		this.width = width; this.height = height;
		this.colour = colour;
		
	}
	
	
	// instance methods
	public Rectangle2D getBoundaryRectangle(){
		Rectangle2D circleBoundary = new Rectangle2D.Double(x, y, width/2, height/2);
		
		return circleBoundary;
	}
	
	public void flipVelocityX() {
		if(velX != 0)
			velX *= -1;
		
	}

	public void flipVelocityY() {
		if(velY != 0)
			velY *= -1;
	}

	
	// getters and setters
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
	
	
	public int getVelocityY(){
		return velY;
	}
	public void setVelocityY(int velY){
		this.velY = velY;
	}
	
	public int getVelocityX(){
		return velX;
	}
	public void setVelocityX(int velX){
		this.velX = velX;
	}

	public int getHeight(){
		return height;
	}
	public void setHeight(int height){
		this.height = height;
	}
	
	public int getWidth(){
		return width;
	}
	public void setWidth(int width){
		this.width = width;
	}

	public Color getColour(){
		return colour;
	}
	public void setColour(Color colour){
		this.colour = colour;
	}
}
