
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

import javax.sound.sampled.*;


public class Music {
	
	private Clip mapOneClip;
	private Clip mapTwoClip;
	private Clip mapThreeClip;
	
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	// Initializers 
	/** Creates a music object and initializes the music variables.*/
	public Music(){
		mapOneSound("res/04 El Dorado.wav");
		mapTwoSound("res/03 Clash of Empires.wav");
		mapThreeSound("res/11 Wrath of Sea.wav");
	}
	
	private void mapOneSound(String path){
		
		try{
			AudioInputStream audio = AudioSystem.getAudioInputStream(Music.class.getResource(path));
			mapOneClip = AudioSystem.getClip();
			mapOneClip.open(audio);
					
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}
		
		
	}
	private void mapTwoSound(String path){
		
		try{
			AudioInputStream audio = AudioSystem.getAudioInputStream(Music.class.getResource(path));
			mapTwoClip = AudioSystem.getClip();
			mapTwoClip.open(audio);
			
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}
		
		
	}
	private void mapThreeSound(String path){
		try{
			AudioInputStream audio = AudioSystem.getAudioInputStream(Music.class.getResource(path));
			mapThreeClip = AudioSystem.getClip();
			mapThreeClip.open(audio);
			
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}
		
	}
	
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	// opening music files
	/** Opens the level one map music.*/
	public void mapOneOpen(){
		mapOneClip.setFramePosition(0);
		mapOneClip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	/** Opens the level two map music.*/
	public void mapTwoOpen(){
		mapTwoClip.setFramePosition(0);
		mapTwoClip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	/** Opens the level three map music.*/
	public void mapThreeOpen(){
		mapThreeClip.start();
	
	}
	
	// closing music files
	/** Closes the level one map music.*/
	public void mapOneClose(){
		mapOneClip.stop();
		
	}
	/** Closes the level one map music.*/
	public void mapTwoClose(){
		mapTwoClip.stop();
		mapTwoClip.setFramePosition(0);
	}
	/** Closes the level one map music.*/
	public void mapThreeClose(){
		if(!mapThreeClip.isActive()){
			
			mapThreeClip.setFramePosition(0);
		}
	}
	
}
