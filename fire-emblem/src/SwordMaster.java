
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
public class SwordMaster extends Unit {
	
	/** Creates a unit of type sword master, stats are normal.
	 *  @param x units x position on the grid
	 *  @param y units y position on the grid
	 *  @param ally boolean for whether or not the unit belongs to the player*/
	public SwordMaster(int x, int y, boolean ally){
		super(x,y,"swordmaster",ally);
		this.setHitPoints(80);
		this.setAttack(50);
		this.setDefense(33);
		this.setMagic(34);
		this.setResistance(38);
		this.setSpeed(46);
		this.setSteps(6);
		this.setAlive(true);
		
	}
	
	/** Creates a unit of type sword master, stats are overpowered.
	 *  @param x units x position on the grid
	 *  @param y units y position on the grid
	 *  @param ally boolean for whether or not the unit belongs to the player
	 *  @param overPowered boolean for whether or not the unit is overpowered (value is insignificant but sound cools)*/
	public SwordMaster(int x, int y, boolean ally, boolean overPowered){
		super(x,y,"swordmaster",ally);
		this.setHitPoints(100);
		this.setAttack(60);
		this.setDefense(33);
		this.setMagic(34);
		this.setResistance(38);
		this.setSpeed(46);
		this.setSteps(7);
		this.setAlive(true);
		this.setOverPowered(overPowered);
		
	}
	
	
}
