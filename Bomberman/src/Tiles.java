//Name: William Granados
//Date: Tuesday, June 4th, 2013
//Class description: handles tile objects

import java.awt.geom.Rectangle2D;


public class Tiles {

	//attributes
	private int x;
	private int y;
	private final int height = 40;
	private final int width = 40;
	private boolean isSolid = false;
	private boolean isSnowball = false;
	private boolean bomb = false;
	
	//poweups
	private boolean firePower;
	private boolean bombPower;
	private boolean speedPower;
	private boolean isCool = false;
	

	
	public Tiles(int x,int y, boolean isSolid, boolean isSnowball){
		//attributes
		this.setX(x);
		this.setY(y);
		this.setIsSolid(isSolid);
		this.setSnowball(isSnowball);
		
		//powerups
		this.setFirePower(false);
		this.setBombPower(false);
		this.setSpeedPower(false);
		this.setIsCool(false);
	}
	
	public Tiles(int x,int y, boolean isSolid, boolean firePower,boolean bombPower,boolean speedPower, boolean isCool){
		//attributes
		this.setX(x);
		this.setY(y);
		this.setIsSolid(isSolid);
		this.setSnowball(isSolid);
		
		//powerups
		this.setFirePower(firePower);
		this.setBombPower(bombPower);
		this.setSpeedPower(speedPower);
		this.setIsCool(isCool);
	}

	public Tiles(){
		
	}


	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
	
	public boolean getIsSolid(){
		return isSolid;
	}
	public void setIsSolid(boolean isSolid){
		this.isSolid = isSolid;
	}
	
	public boolean isBomb() {
		return bomb;
	}
	public void setBomb(boolean bomb) {
		this.bomb = bomb;
	}
	
	public boolean isSnowball() {
		return isSnowball;
	}
	public void setSnowball(boolean isSnowball) {
		this.isSnowball = isSnowball;
	}
	
	public Rectangle2D tileBoundary(){
		Rectangle2D tileBoundary = new Rectangle2D.Double(x,y,width,height);
		return tileBoundary;
	}
	public Rectangle2D tileMiniBoundary(){
		Rectangle2D tileTopBoundary = new Rectangle2D.Double(x+15,y+15,width/4,height/4);
		return tileTopBoundary;
	}

	public boolean isFirePower() {
		return firePower;
	}
	public void setFirePower(boolean firePower) {
		this.firePower = firePower;
	}

	public boolean isBombPower() {
		return bombPower;
	}
	public void setBombPower(boolean bombPower) {
		this.bombPower = bombPower;
	}

	public boolean isSpeedPower() {
		return speedPower;
	}
	public void setSpeedPower(boolean speedPower) {
		this.speedPower = speedPower;
	}
	
	public boolean isCool(){
		return isCool;
	}
	public void setIsCool(boolean isCool){
		this.isCool = isCool;
	}
	

	
	
	
	
	
}
