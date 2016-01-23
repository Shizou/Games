//Name: William Granados
//Date: Tuesday, June 4th, 2013
//Class description: handles player related objects

import java.awt.geom.Rectangle2D;


public class Player {
		
		// attributes
		private int x,y;
		private final int playerHeight = 31;
		private final int playerWidth = 24;
		
		// power ups
		private int bombCount;
		private int tempBombCount;
		private int speed;
		private int bombRadius;
		
		
		public Player(){
	
			this.setX(250);
			this.setY(69);
			this.setSpeed(2);
			this.setBombCount(3);
			this.setTempBombCount(3);
			this.setBombRadius(1);

		}
		public Player(int random){
			
			this.setX(725);
			this.setY(469);
			this.setSpeed(2);
			this.setBombCount(3);
			this.setTempBombCount(3);
			this.setBombRadius(1);

		}
		
		//getters & setters
		
		public int getX(){
			return x;
		}
		public void setX(int x){
			this.x = x;
		}
		
		public Rectangle2D spriteBoundary(){
			Rectangle2D spriteBoundary = new Rectangle2D.Double((x+10),(y+playerHeight/2),(playerWidth/2),playerHeight/2);
			
			return spriteBoundary;
		}
		public Rectangle2D sprite2Boundary(){
			Rectangle2D spriteBoundary = new Rectangle2D.Double((x),(y+playerHeight/2),(playerWidth),playerHeight/2);
			
			return spriteBoundary;
		}
		
		public int getY(){
			return y;
		}
		public void setY(int y){
			this.y = y;
		}	
		
		public int getBombCount(){
			return bombCount;
		}
		public void setBombCount(int bombCount){
			this.bombCount = bombCount;
		}
		
		public int getSpeed(){
			return speed;
		}
		public void setSpeed(int speed){
			if(speed < 5){
				this.speed = speed;
			}
			
		}
		
		public int getBombRadius() {

			return bombRadius;
		}
		public void setBombRadius(int bombRadius) {
			this.bombRadius = bombRadius;
		}

		public int getTempBombCount() {
			return tempBombCount;
		}
		public void setTempBombCount(int tempBombCount) {
			this.tempBombCount = tempBombCount;
		}

		
		
		
}
