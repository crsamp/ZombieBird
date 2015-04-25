package com.kilobolt.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Circle;
import com.kilobolt.zbhelpers.AssetLoader;

/**
 * @author Chris Samp
 *
 */
public class Bird {
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private float rotation;
	private int width;
	private int height;
	
	private Circle boundingCircle;
	
	private boolean isAlive;
	
	/**
	 * @param x float x position
	 * @param y	float y position
	 * @param width	integer width of bird
	 * @param height	integer height of bird
	 */
	public Bird(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 460);
		boundingCircle = new Circle();
		isAlive = true;
	}
	
	/**updates position of the bird
	 * @param delta float change in new position vs previous positon
	 */
	public void update(float delta) {
		
		velocity.add(acceleration.cpy().scl(delta));
		boundingCircle.set(position.x+9, position.y+6,6.5f);
		
		if (velocity.y > 200) {
			velocity.y = 200;
			
		}
		
		position.add(velocity.cpy().scl(delta));
		
		//rotate counterclockwise
		if(velocity.y <0) {
			rotation-=600*delta;
			
			if (rotation<-20) {
				rotation = -20;
			}
		}
			
		//rotate clockwise
		if(isFalling() || !isAlive) {
			rotation +=480*delta;
			if(rotation >90) {
				rotation = 90;
			}
		}
		
		
	}
	
	/**
	 * changes isAlive to false and the velocity of the bird to 0
	 */
	public void die() {
		isAlive = false;
		velocity.y = 0;
	}
	
	/**
	 * makes the acceleration of the bird 0
	 */
	public void decelerate() {
		acceleration.y = 0;
	}
	
	/**
	 * @return boolean returns isAlive
	 */
	public boolean isAlive() {
		return isAlive;
	}
	
	/**
	 * causes the bird to go up on click.
	 */
	public void onClick() {
        if(isAlive) {
        	//AssetLoader.flap.play();
        	velocity.y = -140;
        }
    }

    /**
     * returns the x position of the bird
     * @return float x position
     */
    public float getX() {
        return position.x;
    }

    /**
     * returns the y position of the bird
     * @return float y position
     */
    public float getY() {
        return position.y;
    }

    /**
     * returns the width of the bird
     * @return float width
     */
    public float getWidth() {
        return width;
    }

    /**
     * returns the height of the bird
     * @return float height
     */
    public float getHeight() {
        return height;
    }

    /**
     * returns the rotation of the bird
     * @return float rotation
     */
    public float getRotation() {
        return rotation;
    }
    
    /**
     * if velocity in y direction is >110, returns true else false
     * @return boolean if the bird is falling
     */
    public boolean isFalling() {
    	return velocity.y>110;
    }
    
    /**
     * returns if the bird should or should not flap its wings anymore
     * @return boolean
     */
    public boolean shouldntFlap() {
    	return velocity.y >70 || !isAlive;
    }
    /**
     * returns the boudning circle(hitbox) of the bird
     * @return Circle returns a circle object
     */
    public Circle getBoundingCircle() {
        return boundingCircle;
    }
 }
	

