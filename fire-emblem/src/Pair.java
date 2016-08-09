
/* 
 * Copyright (c) 2014 William Granados<william.granados@wgma00.me>
 *
 * This file is part of Anamalous.
 *
 * Anamalous is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Anamalous is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Anamalous.  If not, see <http://www.gnu.org/licenses/>.
 */

public class Pair{
	
	private int x;
	private int y;
	
	private int prevX;
	private int prevY;
	
	private int depth;
	
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	// constructors
	/** Creates a pair object
	 * @param x x position of the current tile being traversed
	 * @param y y position of the current tile being traversed
	 * @param depth how far from the original unit area is
	 * */
	public Pair(int x, int y, int depth){
		this.setX(x);
		this.setY(y);
		this.setDepth(depth);
	}
	
	/** Creates a pair object
	 * @param x x position of the current tile being traversed
	 * @param y y position of the current tile being traversed
	 * @param prevX previous x position of the previous tile passed while traversing the level 
	 * @param prevY previous x position of the previous tile passed while travering the level
	 * @param depth how far from the original unit area is
	 * */
	public Pair(int x, int y,int prevX, int prevY, int depth){
		this.setX(x);
		this.setY(y);
		this.setPrevX(prevX);
		this.setPrevY(prevY);
		this.setDepth(depth);
		
		
	}
	
	
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	// getters and setters
	/** Returns the x position of the pair*/
	public int getX() {
		return x;
	}
	/** Sets the x position of the pair*/
	public void setX(int x) {
		this.x = x;
	}
	
	/** Returns the y position of the pair*/
	public int getY() {
		return y;
	}
	/** Sets the y position of the pair*/
	public void setY(int y) {
		this.y = y;
	}
	
	/** Returns the current depth of the breadth/depth first search*/
	public int getDepth() {
		return depth;
	}
	/** Sets the current depth of the breadth/depth first search*/
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	/** Returns the previous x position of the pair*/
	public int getPrevX() {
		return prevX;
	}
	/** Sets the previous x position of the pair*/
	public void setPrevX(int prevX) {
		this.prevX = prevX;
	}
	
	/** Returns the previous y position of the pair*/
	public int getPrevY() {
		return prevY;
	}
	/** Sets the previous y position of the pair*/
	public void setPrevY(int prevY) {
		this.prevY = prevY;
	}
	

	

}
