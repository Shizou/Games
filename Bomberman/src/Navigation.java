//Name: William Granados
//Date: Tuesday, June 4th, 2013
//Class description: handles in game player navigation

import java.awt.event.*;

public class Navigation implements KeyListener{

	//player1 & player2 components
	private boolean[] PLAYER_STATE = new boolean[5];
	private boolean[] PLAYER2_STATE = new boolean[5];
	private int PAST_PLAYERACTION;
	private int PAST_PLAYER2ACTION;
	
	private final int PLAYER_UP = 0;
	private final int PLAYER_DOWN = 1;
	private final int PLAYER_LEFT = 2;
	private final int PLAYER_RIGHT = 3;
	private final int PLAYER_BOMB = 4;

	private final int PLAYER_LOOKUP = 0;
	private final int PLAYER_LOOKDOWN = 1;
	private final int PLAYER_LOOKLEFT = 2;
	private final int PLAYER_LOOKRIGHT = 3;
	
	//past states
	public int getPastPlayerAction(){
		return PAST_PLAYERACTION;
	}
	public int getPastPlayer2Action(){
		return PAST_PLAYER2ACTION;
	}
	
	public void setPastPlayerAction(int index){
		PAST_PLAYERACTION = index;
	}
	public void setPastPlayer2Action(int index){
		PAST_PLAYER2ACTION = index;
	}
	
	public int getPlayerLookUp(){
		return PLAYER_LOOKUP;
	}
	public int getPlayerLookDown(){
		return PLAYER_LOOKDOWN;
	}
	public int getPlayerLookLeft(){
		return PLAYER_LOOKLEFT;
	}
	public int getPlayerLookRight(){
		return PLAYER_LOOKRIGHT;
	}
	
	//Player 1
	public boolean getPlayerUp(){
		return PLAYER_STATE[PLAYER_UP];
	}
	public boolean getPlayerDown(){
		return PLAYER_STATE[PLAYER_DOWN];
	}
	public boolean getPlayerLeft(){
		return PLAYER_STATE[PLAYER_LEFT];
	}
	public boolean getPlayerRight(){
		return PLAYER_STATE[PLAYER_RIGHT];
	}
	public boolean getPlayerBomb() {
		return PLAYER_STATE[PLAYER_BOMB];
	}
	public void setPlayerBomb(boolean bomb){
		PLAYER_STATE[PLAYER_BOMB] = bomb;
	}
	//Player 2
	public boolean getPlayer2Up(){
		return PLAYER2_STATE[PLAYER_UP];
	}
	public boolean getPlayer2Down(){
		return PLAYER2_STATE[PLAYER_DOWN];
	}
	public boolean getPlayer2Left(){
		return PLAYER2_STATE[PLAYER_LEFT];
	}
	public boolean getPlayer2Right(){
		return PLAYER2_STATE[PLAYER_RIGHT];
	}
	public boolean getPlayer2Bomb() {
		return PLAYER2_STATE[PLAYER_BOMB];
	}
	public void setPlayer2Bomb(boolean bomb){
		PLAYER2_STATE[PLAYER_BOMB] = bomb;
	}

	// Keyboard
	public void keyPressed(KeyEvent e) {
		
		player1KeyPressed(e);
		//player2KeyPressed(e);
		
	}
	public void keyReleased(KeyEvent e) {
		player1KeyReleased(e);
		//player2KeyReleased(e);
		
	}
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void player1KeyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_A  ){
			PLAYER_STATE[PLAYER_LEFT] = true;
			PAST_PLAYERACTION = PLAYER_LOOKLEFT;
		}
		else if(e.getKeyCode() == KeyEvent.VK_W ){
			PLAYER_STATE[PLAYER_UP] = true;
			PAST_PLAYERACTION = PLAYER_LOOKUP;
		}
		else if(e.getKeyCode() == KeyEvent.VK_D){
			PLAYER_STATE[PLAYER_RIGHT] = true;
			PAST_PLAYERACTION = PLAYER_LOOKRIGHT;
		}
		else if(e.getKeyCode() == KeyEvent.VK_S){
			PLAYER_STATE[PLAYER_DOWN] = true;
			PAST_PLAYERACTION = PLAYER_LOOKDOWN;
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE)
			PLAYER_STATE[PLAYER_BOMB] = true;
		
	}
	public void player2KeyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_LEFT ){
			PLAYER2_STATE[PLAYER_LEFT] = true;
			PAST_PLAYER2ACTION = PLAYER_LOOKLEFT;
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP ){
			PLAYER2_STATE[PLAYER_UP] = true;
			PAST_PLAYER2ACTION = PLAYER_LOOKUP;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			PLAYER2_STATE[PLAYER_RIGHT] = true;
			PAST_PLAYER2ACTION = PLAYER_LOOKRIGHT;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			PLAYER2_STATE[PLAYER_DOWN] = true;
			PAST_PLAYER2ACTION = PLAYER_LOOKDOWN;
		}
		else if(e.getKeyCode() == KeyEvent.VK_NUMPAD0)
			PLAYER2_STATE[PLAYER_BOMB] = true;
	}

	public void player1KeyReleased(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_A  )
			PLAYER_STATE[PLAYER_LEFT] = false;
		else if(e.getKeyCode() == KeyEvent.VK_D)
			PLAYER_STATE[PLAYER_RIGHT] = false;
		else if(e.getKeyCode() == KeyEvent.VK_W )
			PLAYER_STATE[PLAYER_UP] = false;
		else if(e.getKeyCode() == KeyEvent.VK_S)
			PLAYER_STATE[PLAYER_DOWN] = false;
		else if(e.getKeyCode() == KeyEvent.VK_SPACE)
			PLAYER_STATE[PLAYER_BOMB] = false;
	}
	public void player2KeyReleased(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_LEFT  )
			PLAYER2_STATE[PLAYER_LEFT] = false;
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			PLAYER2_STATE[PLAYER_RIGHT] = false;
		else if(e.getKeyCode() == KeyEvent.VK_UP )
			PLAYER2_STATE[PLAYER_UP] = false;
		else if(e.getKeyCode() == KeyEvent.VK_DOWN)
			PLAYER2_STATE[PLAYER_DOWN] = false;
		else if(e.getKeyCode() == KeyEvent.VK_SHIFT)
			PLAYER2_STATE[PLAYER_BOMB] = false;
	}

}
