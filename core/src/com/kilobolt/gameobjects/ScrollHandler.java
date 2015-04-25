package com.kilobolt.gameobjects;

import com.kilobolt.gameworld.GameWorld;
import com.kilobolt.zbhelpers.AssetLoader;

/**
 * @author Chris Samp
 *
 */
public class ScrollHandler {
	
	private Grass frontGrass,backGrass;
	private Pipe pipe1, pipe2,pipe3;
	
	private GameWorld gameWorld;
	
	public static final int SCROLL_SPEED = -59;
	public static final int PIPE_GAP = 49;
	
	/**
	 * @param gameWorld the world you want to add all the objects onto
	 * @param yPos	y position of the objects
	 */
	public ScrollHandler(GameWorld gameWorld, float yPos) {
        this.gameWorld = gameWorld;
        frontGrass = new Grass(0, yPos, 143, 11, SCROLL_SPEED);
        backGrass = new Grass(frontGrass.getTailX(), yPos, 143, 11,
                SCROLL_SPEED);

        pipe1 = new Pipe(210, 0, 22, 60, SCROLL_SPEED, yPos);
        pipe2 = new Pipe(pipe1.getTailX() + PIPE_GAP, 0, 22, 70, SCROLL_SPEED,
                yPos);
        pipe3 = new Pipe(pipe2.getTailX() + PIPE_GAP, 0, 22, 60, SCROLL_SPEED,
                yPos);
    }
	
	/**
	 * checks to see if the pipes are grass has scrolled off the screen and if it has
	 * it replaces it.
	 * @param delta change in position
	 */
	public void update(float delta) {
		
		frontGrass.update(delta);
		backGrass.update(delta);
		pipe1.update(delta);
		pipe2.update(delta);
		pipe3.update(delta);
		
		if (pipe1.isScrolledLeft()) {
            pipe1.reset(pipe3.getTailX() + PIPE_GAP);
        } else if (pipe2.isScrolledLeft()) {
            pipe2.reset(pipe1.getTailX() + PIPE_GAP);

        } else if (pipe3.isScrolledLeft()) {
            pipe3.reset(pipe2.getTailX() + PIPE_GAP);
        }

		if(frontGrass.isScrolledLeft()) {
			frontGrass.reset(backGrass.getTailX());
		}
		else if(backGrass.isScrolledLeft()) {
			backGrass.reset(frontGrass.getTailX());
		}
	}
	
	/**
	 * makes the grass and pipes stop scrolling
	 */
	public void stop() {
        frontGrass.stop();
        backGrass.stop();
        pipe1.stop();
        pipe2.stop();
        pipe3.stop();
    }

    /**
     * checks to see if the bird has crossed the midway point of the tube so
     * it can add the point to your score.
     * @param bird bird object
     * @return boolean
     */
    public boolean collides(Bird bird) {
    	if (!pipe1.isScored()
                && pipe1.getX() + (pipe1.getWidth() / 2) < bird.getX()
                        + bird.getWidth()) {
            addScore(1);
            pipe1.setScored(true);
            
        } else if (!pipe2.isScored()
                && pipe2.getX() + (pipe2.getWidth() / 2) < bird.getX()
                        + bird.getWidth()) {
            addScore(1);
            pipe2.setScored(true);
            

        } else if (!pipe3.isScored()
                && pipe3.getX() + (pipe3.getWidth() / 2) < bird.getX()
                        + bird.getWidth()) {
            addScore(1);
            pipe3.setScored(true);
            

        }

        return (pipe1.collides(bird) || pipe2.collides(bird) || pipe3
                .collides(bird));
    }
    
    /**
     * @param increment integer The amount you want to add to score
     */
    private void addScore(int increment) {
    	gameWorld.addScore(increment);
    }
	
	/**
	 * returns the frontGrass object
	 * @return Grass object
	 */
	public Grass getFrontGrass() {
		return frontGrass;
	}
	
	/**
	 * @return
	 */
	public Grass getBackGrass() {
		return backGrass;
	}
	
	/**
	 * returns pipe 1
	 * @return Pipe object
	 */
	public Pipe getPipe1() {
		return pipe1;
	}
	
	/**
	 * returns pipe 2
	 * @return Pipe object
	 */
	public Pipe getPipe2() {
		return pipe2;
	}
	
	/**
	 * returns pipe 3
	 * @return Pipe object
	 */
	public Pipe getPipe3() {
		return pipe3;
	}
}
