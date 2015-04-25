package com.kilobolt.gameworld;

import com.kilobolt.gameobjects.Bird;
import com.kilobolt.gameobjects.ScrollHandler;
import com.kilobolt.zbhelpers.AssetLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Intersector;

public class GameWorld {
	
	private Bird bird;
	private ScrollHandler scroller;
	private Rectangle ground;
	
	public GameWorld(int midPointY) {
		bird = new Bird(33, midPointY - 5, 17, 12);
		ground = new Rectangle(0,midPointY+66,136,11);
		scroller = new ScrollHandler(midPointY +66);
	}
	
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
	
	public Bird getBird() {
		return bird;
	}

	public ScrollHandler getScroller() {
		return scroller;
	}
}
