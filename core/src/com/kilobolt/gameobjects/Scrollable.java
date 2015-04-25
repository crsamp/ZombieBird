package com.kilobolt.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * @author Chris Samp
 *
 */
public class Scrollable {
	
	protected Vector2 position;
	protected Vector2 velocity;
	protected int width;
	protected int height;
	protected boolean isScrolledLeft;
	
	/**
	 * init for Scrollable class
	 * @param x float x position
	 * @param y	float y position
	 * @param width	integer width of object
	 * @param height	integer height of object
	 * @param scrollSpeed	float how fast the screen will scroll
	 */
	public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
		position = new Vector2(x,y);
		velocity = new Vector2(scrollSpeed,0);
		this.width = width;
		this.height = height;
		isScrolledLeft = false;
	}
	
	/**
	 * if the x position+width is less than 0 isScrolledLeft = true
	 * @param delta float how much the screen has moved between checks
	 */
	public void update(float delta) {
		position.add(velocity.cpy().scl(delta));
		
		if(position.x+width <0) {
			isScrolledLeft = true;
		}
	}
	
	/**
	 * makes the x velocity 0
	 */
	public void stop() {
	    velocity.x = 0;
		}
	
	/**
	 * resets x position and makes isScrolledLeft false
	 * @param newX flat resets the x positon with the newX
	 */
	public void reset(float newX) {
		position.x = newX;
		isScrolledLeft = false;
	}
	
	/**
	 * @return boolean isScrolledLeft
	 */
	public boolean isScrolledLeft() {
		return isScrolledLeft;
		
	}
	
	/**
	 * @return float the x position of the part of the object furthest to the right
	 */
	public float getTailX() {
		return position.x +width;
	}
	
	/**
	 * @return float x position
	 */
	public float getX() {
		return position.x;
		
	}
	
	/**
	 * @return float y position
	 */
	public float getY() {
		return position.y;
	}
	
	/**
	 * @return integer width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * @return integer height
	 */
	public int getHeight() {
		return height;
	}
}
