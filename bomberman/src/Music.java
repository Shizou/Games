//Name: William Granados
//Date: Tuesday, June 4th, 2013
//Class description: loads music from resource file

import javax.sound.sampled.*;


public class Music {
	
	private Clip menuClip;
	private Clip gameClip;
	private Clip bombClip;
	
	
	public Music(){
		menuSound("res/Midiri-Iro.wav");
		gameSound("res/TooGood.wav");
		bombSound("res/Bongos.wav");
	}
	public void menuSound(String path){
		
		try{
			AudioInputStream audio = AudioSystem.getAudioInputStream(Music.class.getResource(path));
			menuClip = AudioSystem.getClip();
			menuClip.open(audio);
					
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}
		
		
	}
	public void gameSound(String path){
		
		try{
			AudioInputStream audio = AudioSystem.getAudioInputStream(Music.class.getResource(path));
			gameClip = AudioSystem.getClip();
			gameClip.open(audio);
			
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}
		
		
	}
	public void bombSound(String path){
		try{
			AudioInputStream audio = AudioSystem.getAudioInputStream(Music.class.getResource(path));
			bombClip = AudioSystem.getClip();
			bombClip.open(audio);
			
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}
		
	}
	
	public void menuOpen(){
		menuClip.setFramePosition(0);
		menuClip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void gameOpen(){
		gameClip.setFramePosition(0);
		gameClip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void bombOpen(){
		bombClip.start();
	
	}
	
	public void menuClose(){
		menuClip.stop();
		
	}
	public void gameClose(){
		gameClip.stop();
		gameClip.setFramePosition(0);
	}
	public void bombClose(){
		if(!bombClip.isActive()){
			
			bombClip.setFramePosition(0);
		}
	}
	
}
