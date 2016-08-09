
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

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;


public class Mouse implements MouseMotionListener, MouseListener{
	
	private boolean mouseClick = false;
	private Point2D mouse = new Point2D.Double();

	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	// getters and setters
	/**returns a boolean that represents whether the mouse was clicked*/
	public boolean getMouseClick(){
		return mouseClick;
	}
	/** sets the boolean that represents whether the mouse was clicked*/
	public void setMouseClick(boolean mouseClick){
		this.mouseClick = mouseClick;
	}
	/** returns the point2d object of the mouse*/
	public Point2D getMousePoint2D(){
		return mouse;
	}
	
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	//mouse listener
	/** based on user input assesses whether the mouse was clicked*/
	public void mouseClicked(MouseEvent e) {
		this.mouseClick = true;
	}
	/** based on user input assesses whether the mouse is within the graphical interface*/
	public void mouseEntered(MouseEvent e) {
	}
	/** based on user input assesses whether the mouse is outside the graphical interface*/
	public void mouseExited(MouseEvent e) {}
	/** based on user input assesses whether the mouse is being pressed*/
	public void mousePressed(MouseEvent e) {}
	/** based on user input assesses whether the mouse is being dragged*/
	public void mouseDragged(MouseEvent e) {}
	/** based on user input assesses whether the mouse is being released*/
	public void mouseReleased(MouseEvent e) {
		this.mouseClick = false;
	}
	/** based on user input assesses whether the mouse is moving and where to it is moving*/
	public void mouseMoved(MouseEvent e) {
		this.mouse = new Point2D.Double(e.getX(),e.getY());
	}


}
