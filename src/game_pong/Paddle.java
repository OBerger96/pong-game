package game_pong;

import javafx.scene.canvas.GraphicsContext;

public class Paddle {
	// Player' paddle variables.
	static final int PLAYER_HEIGHT = 100; 
	static final int PLAYER_WIDTH = 15;
	
	Ball ball;
	private int type; // Player1, Player2, CPU.
	private double paddleYCoor;
	private double paddleXCoor;
	
	// CTOR.
	public Paddle(Ball otherBall ,int otherType) {
		this.ball = otherBall;
		this.type = otherType;
		setPaddleYCoor(Pong.HEIGHT / 2);
		if(this.type == Pong.HUMAN_Player) { setPaddleXCoor(0); }
		else if (this.type == Pong.CPU) {setPaddleXCoor(Pong.WIDTH - Paddle.PLAYER_WIDTH); }
	}

	// Ball getter & setter.
	public int getBall() { return type; }

	public void setBell(Ball otherBall) { this.ball = otherBall; }
	
	// Player paddle's type getter & setter.
	public int getType() { return type; }

	public void setType(int otherType) { this.type = otherType; }
	
	// Paddle's Y coordinate getter & setter.
	public double getPaddleYCoor() { return paddleYCoor; }

	public void setPaddleYCoor(double otherPaddleYCoor) { this.paddleYCoor = otherPaddleYCoor; }

	// Paddle's X coordinate getter & setter.
	public double getPaddleXCoor() { return paddleXCoor; }

	public void setPaddleXCoor(double otherPaddleXCoor) { this.paddleXCoor = otherPaddleXCoor; }

	// Keeps the paddle in the canvas' boundaries.
	public void keepBoundaries() {
		if(this.paddleYCoor >= (Pong.HEIGHT - Paddle.PLAYER_HEIGHT)) { setPaddleYCoor(Pong.HEIGHT - Paddle.PLAYER_HEIGHT); }
		if(this.paddleYCoor <= 0) { setPaddleYCoor(0); }
	}
	
	// Set CPU opponent's paddle to follow the ball.
	public void setCPUPaddle() {
		if(this.type != Pong.CPU) { return; }
		
		setPaddleYCoor(this.ball.getBallYPos() - (Paddle.PLAYER_HEIGHT / 2));
		
//		if(this.ball.getBallXPos() < (Pong.WIDTH - (Pong.WIDTH  / 4))) { setPaddleYCoor(this.ball.getBallYPos() - (Paddle.PLAYER_HEIGHT / 2)); }
//		else if(this.ball.getBallYPos() > (getPaddleYCoor() + (Paddle.PLAYER_HEIGHT / 2))) { setPaddleYCoor(getPaddleYCoor() + 1); }
//		else { setPaddleYCoor(getPaddleYCoor() - 1); }
	}
	
	// Checks if the paddle hit the ball.
	public boolean checkHit() {
		if(this.type == Pong.HUMAN_Player) {
			if((this.ball.getBallXPos() < (getPaddleXCoor() + Paddle.PLAYER_WIDTH)) &&
			   (this.ball.getBallYPos() >= getPaddleYCoor()) &&
			   (this.ball.getBallYPos() <= (getPaddleYCoor() + Paddle.PLAYER_HEIGHT))) { 
				return true; 
			}
		}
		else if((this.type == Pong.CPU)) {
			if(((this.ball.getBallXPos() + Ball.BALL_R) > getPaddleXCoor()) &&
				(this.ball.getBallYPos() >= getPaddleYCoor()) &&
				(this.ball.getBallYPos() <= (getPaddleYCoor() + Paddle.PLAYER_HEIGHT))) {
				return true;
			}
		}
		
		return false;
	}	
	
	// Draws the player's paddle.
	public void draw(GraphicsContext graphicsContext) {
		graphicsContext.fillRect(getPaddleXCoor(), getPaddleYCoor(), Paddle.PLAYER_WIDTH, Paddle.PLAYER_HEIGHT);
	}
}
