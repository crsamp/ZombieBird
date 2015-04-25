package com.kilobolt.gameworld;

import com.kilobolt.gameobjects.Bird;
import com.kilobolt.gameobjects.ScrollHandler;
import com.kilobolt.zbhelpers.AssetLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Intersector;

/**
 * @author Chris Samp
 *
 */
public class GameWorld {
	
	private Bird bird;
	private ScrollHandler scroller;
	private Rectangle ground;
	private int score = 0;
	
	/**
	 * initializes GameWorld
	 * @param midPointY integer middle of the screen in the y direction
	 */
	public GameWorld(int midPointY) {
		bird = new Bird(33, midPointY - 5, 17, 12);
		ground = new Rectangle(0,midPointY+66,136,11);
		scroller = new ScrollHandler(this,midPointY +66);
	}
	
	/**
	 * updates bird and scroller and if there is a collision, the bird dies and the scroller stops scrolling
	 * @param delta float how much of a difference between the last render and this render
	 */
	public void update(float delta) {
		
		if(delta>.15f)
		{
			delta = .15f;
		}


		bird.update(delta);
		scroller.update(delta);
		
		if (scroller.collides(bird) && bird.isAlive()) {
	        // Clean up on game over
	        scroller.stop();
	        bird.die();
	        //AssetLoader.dead.play();
	    }
		
		if(Intersector.overlaps(bird.getBoundingCircle(),ground)) {
			scroller.stop();
			bird.die();
			bird.decelerate();
		}
    }
	
	/**
	 * gets the bird
	 * @return Bird returns your bird object
	 */
	public Bird getBird() {
		return bird;
	}

	/**
	 * returns the scroller
	 * @return	ScrollHandler returns your ScrollHandler object
	 */
	public ScrollHandler getScroller() {
		return scroller;
	}
	
	/**
	 * returns the score
	 * @return integer returns the score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * increments score
	 * @param increment interger number you want to increment by
	 */
	public void addScore(int increment) {
		score+=increment;
	}
}
