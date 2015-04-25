package com.kilobolt.gameobjects;

import java.util.Random;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Intersector;

/**
 * @author Chris Samp
 *
 */

public class Pipe extends Scrollable {
	
	private Random r;
	private Rectangle skullUp, skullDown, barUp, barDown;
	public static final int VERTICAL_GAP = 45;
	public static final int SKULL_WIDTH = 24;
	public static final int SKULL_HEIGHT = 11;
	private float groundY;
	private boolean isScored = false;
	
	/**
	 * @param x float x position
	 * @param y	float starting y position
	 * @param width	int width of pipe
	 * @param height	int height of pipe
	 * @param scrollSpeed	float how fast the pipe scrolls to the left
	 * @param groundY	float y position
	 */
	public Pipe(float x, float y, int width, int height, float scrollSpeed, float groundY) {
		super(x,y,width,height,scrollSpeed);
		
		r = new Random();
		skullUp = new Rectangle();
	    skullDown = new Rectangle();
	    barUp = new Rectangle();
	    barDown = new Rectangle();
	    this.groundY = groundY;
	}
	
	/* (non-Javadoc)
	 * @see com.kilobolt.gameobjects.Scrollable#update(float)
	 */
	@Override
	public void update(float delta) {
		super.update(delta);
		barUp.set(position.x, position.y, width, height);
        barDown.set(position.x, position.y + height + VERTICAL_GAP, width,
                groundY - (position.y + height + VERTICAL_GAP));
        
        skullUp.set(position.x - (SKULL_WIDTH - width) / 2, position.y + height
                - SKULL_HEIGHT, SKULL_WIDTH, SKULL_HEIGHT);
        skullDown.set(position.x - (SKULL_WIDTH - width) / 2, barDown.y,
                SKULL_WIDTH, SKULL_HEIGHT);

	}
	
	/* (non-Javadoc)
	 * @see com.kilobolt.gameobjects.Scrollable#reset(float)
	 */
	@Override
	public void reset(float newX) {
		super.reset(newX);
		height = r.nextInt(90)+15;
		isScored = false;
	}
	
	/**
	 * @param bird object of reference Bird
	 * @return boolean
	 */
	public boolean collides(Bird bird) {
        if (position.x < bird.getX() + bird.getWidth()) {
            return (Intersector.overlaps(bird.getBoundingCircle(), barUp)
                    || Intersector.overlaps(bird.getBoundingCircle(), barDown)
                    || Intersector.overlaps(bird.getBoundingCircle(), skullUp) || Intersector
                        .overlaps(bird.getBoundingCircle(), skullDown));
        }
        return false;
    }
	
	/**
	 * @return boolean
	 */
	public boolean isScored() {
		return isScored;
	}
	
	/**
	 * @param b boolean
	 */
	public void setScored(boolean b) {
		isScored = b;
	}
	
	/**
	 * @return rectangle object
	 */
	public Rectangle getSkullUp() {
		return skullUp;
	}
	
	/**
	 * @return rectangle object
	 */
	public Rectangle getSkullDown() {
		return skullDown;
	}
	
	/**
	 * @return rectangle object
	 */
	public Rectangle getBarUp() {
		return barUp;
	}
	
	/**
	 * @return rectangle object
	 */
	public Rectangle getBarDown() {
		return barDown;
	}

}
