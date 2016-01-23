//Name: William Granados
//Date: Tuesday, June 4th, 2013
//Class description: Handles mouse navigation between game states

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;


public class Mouse implements MouseMotionListener, MouseListener{
	//mouse components
	private boolean mouseClick = false;
	private Point2D mouse = new Point2D.Double();

	//getters and setters
	public boolean getMouseClick(){
		return mouseClick;
	}
	public void setMouseClick(boolean mouseClick){
		this.mouseClick = mouseClick;
	}
	public Point2D getMouse(){
		return mouse;
	}
	
	//mouse listener
	@Override
	//Mouse 
	public void mouseClicked(MouseEvent e) {
		mouseClick = true;
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {
		mouseClick = false;
	}
	public void mouseMoved(MouseEvent e) {
		mouse = new Point2D.Double(e.getX(),e.getY());
	}
}
