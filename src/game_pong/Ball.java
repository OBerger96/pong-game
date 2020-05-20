package game_pong;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball {
	// Ball variables.
	static final double BALL_R = 15;
	static final double MAX_SPEED = 11.0;
	
	private double ballXCoor;
	private double ballYCoor;
	private double ballXSpeed;
	private double ballYSpeed;
	
	// CTOR.
	public Ball() {
		ballXCoor  = Pong.WIDTH / 2;
		ballYCoor = Pong.HEIGHT / 2;
		ballXSpeed = 1;
		ballYSpeed = 1;
	}
 
	// Ball's X coordinate getter & setter.
	public double getBallXPos() { return ballXCoor; }

	public void setBallXPos(double otherBallXCoor) { this.ballXCoor = otherBallXCoor; }
	
	// Ball's Y coordinate getter & setter.
	public double getBallYPos() { return ballYCoor; }

	public void setBallYPos(double otherBallYCoor) { this.ballYCoor = otherBallYCoor; }

	// Ball's X speed getter & setter.
	public double getBallXSpeed() { return ballXSpeed; }

	public void setBallXSpeed(double ballXSpeed) { this.ballXSpeed = ballXSpeed; }
	
	// Ball's Y speed getter & setter.
	public double getBallYSpeed() { return ballYSpeed; }

	public void setBallYSpeed(double ballYSpeed) { this.ballYSpeed = ballYSpeed; }
	
	// Checks if the ball hit the canvas' edges. 
	// If the ball hits the canvas' edges of the field, it will bounce back.
	public void checkEdgesOnCanvas() {
		if((getBallYPos() >= Pong.HEIGHT) || (getBallYPos() <= 0)) { setBallYSpeed(getBallYSpeed() * (-1)); }
	}
	
	// Reset ball's speed & direction ( -3 <= X & Y <= 3).
	public void resetDirectionAndSpeed() {
		double random = (Math.random() * 7) - 3;
		if((int)random == 0 ) { random++; }
		setBallXSpeed(random);
		
		random = (Math.random() * 7) - 3;
		if((int)random == 0 ) { random++; }
		setBallYSpeed(random);
	}
	
	// Reset the ball starting position.
	public void resetBallToStartingPos() {
		setBallXPos(Pong.WIDTH / 2);			
		setBallYPos(Pong.HEIGHT / 2);	
	}
	
	// Set ball's movement.
	public void setMovement() {
		setBallXPos(getBallXPos() + getBallXSpeed());
		setBallYPos(getBallYPos() + getBallYSpeed());
	}
	
	// Draws the ball.
	public void draw(GraphicsContext graphicsContext) {
		graphicsContext.setFill(Color.ORANGERED);
		graphicsContext.fillOval(getBallXPos(), getBallYPos(), Ball.BALL_R, Ball.BALL_R);
	}
	
	// Increase ball's speed each time it hits the paddle (until it reaches to MAX_SPEED).
	public void increaseSpeed() {
		
		// Keep the ball in MAX_SPEED if the ball reach that level.
		if(getBallXSpeed() >= MAX_SPEED) { setBallXSpeed(MAX_SPEED); return; }
		if(getBallYSpeed() >= MAX_SPEED) { setBallYSpeed(MAX_SPEED); return; }
		
		setBallXSpeed(getBallXSpeed() + 1 * Math.signum(getBallXSpeed()));
		setBallXSpeed(getBallXSpeed() * -1);
		setBallYSpeed(getBallYSpeed() + 1 * Math.signum(getBallYSpeed()));
		setBallYSpeed(getBallYSpeed() * -1);
	}
}
