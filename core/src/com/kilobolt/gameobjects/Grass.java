package com.kilobolt.gameobjects;

/**
 * @author Chris Samp
 *
 */
public class Grass extends Scrollable{
	
	/**
	 * @param x float starting x position of the grass
	 * @param y float starting y position of the grass
	 * @param width	integer width of the grass
	 * @param height	integer height of the grass
	 * @param scrollSpeed	float how fast the grass scrolls to the left
	 */
	public Grass(float x, float y, int width, int height, float scrollSpeed) {
		super(x,y,width,height,scrollSpeed);
	}
}
