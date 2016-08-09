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

public class ComparisonPair {
	
	private Unit unit;
	private int prevX;
	private int prevY;
	
	/** Creates an object of type comparisonPair
	 *  @param prevX x position on grid before computer unit reaches the player unit
	 *  @param prevY y position on grid before computer unit reaches the player unit
	 *  @param unit  unit that is encountered after searching
	 *  */
	public ComparisonPair(int prevX, int prevY, Unit unit){
		this.setPrevX(prevX);
		this.setPrevY(prevY);
		this.setUnit(unit);
		
	}

	// getters and setters
	/** Returns the previous x position before reaching the unit*/
	public int getPrevX() {
		return prevX;
	}
	/** Sets the previous x position before reaching the unit*/
	public void setPrevX(int prevX) {
		this.prevX = prevX;
	}
	
	/** Returns the previous y position before reaching the unit*/
	public int getPrevY() {
		return prevY;
	}
	/** Sets the previous y position before reaching the unit*/
	public void setPrevY(int prevY) {
		this.prevY = prevY;
	}
	
	/** Returns the unit that is encountered*/
	public Unit getUnit() {
		return unit;
	}
	/** Sets the unit that is encountered*/
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
}
