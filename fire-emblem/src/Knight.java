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

public class Knight extends Unit {
	
	/** Creates a unit of type knight, stats are normal.
	 *  @param x units x position on the grid
	 *  @param y units y position on the grid
	 *  @param ally boolean for whether or not the unit belongs to the player*/
	public Knight(int x, int y, boolean ally){
		super(x,y,"knight",ally);
		this.setHitPoints(80);
		this.setAttack(48);
		this.setDefense(48);
		this.setMagic(20);
		this.setResistance(10);
		this.setSpeed(37);
		this.setSteps(3);
		this.setAlive(true);
	}
	
	/** Creates a unit of type knight, stats are overpowered.
	 *  @param x units x position on the grid
	 *  @param y units y position on the grid
	 *  @param ally boolean for whether or not the unit belongs to the player
	 *  @param overPowered boolean for whether or not the unit is overpowered (value is insignificant but sound cools)*/
	public Knight(int x, int y, boolean ally, boolean overPowered){
		super(x,y,"knight",ally);
		this.setHitPoints(100);
		this.setAttack(58);
		this.setDefense(48);
		this.setMagic(20);
		this.setResistance(10);
		this.setSpeed(37);
		this.setSteps(5);
		this.setAlive(true);
		this.setOverPowered(true);
	}
	

	
	


}
