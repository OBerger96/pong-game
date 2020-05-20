package game_pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Pong extends Application {
	// Board variables.
	static final int HEIGHT = 600;
	static final int WIDTH = 800;
	static final int HUMAN_Player = 1;
	static final int CPU = 2;
	static final int GAMES = 11; // ONLY odd numbers!
	
	// Game variables.
	private int scoreP1;
	private int scoreP2;
	private boolean isStarted;
	private Paddle paddle1; // Left.
	private Paddle paddle2; // Right
	private Ball ball;
	private Table table;
	
	// CTOR.
	public Pong() {
		this.scoreP1 = 0;
		this.scoreP2 = 0;
		this.isStarted = false;
		this.ball = new Ball();
		this.paddle1 = new Paddle(ball, HUMAN_Player);
		this.paddle2 = new Paddle(ball, CPU);
		this.table = new Table();
	}
	
	// Start Ping-Pong game!
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("P O N G");
		stage.setResizable(false);
		
		// Set background's size.
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		
		// JavaFX Timeline = free form animation defined by KeyFrames and their duration.
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> run(graphicsContext)));
		timeline.setCycleCount(Timeline.INDEFINITE); // Cycles repeat indefinitely.
		
		// Mouse control (click & move).
		canvas.setOnMouseMoved(e -> this.paddle1.setPaddleYCoor(e.getY())); 
		canvas.setOnMouseClicked(e -> this.isStarted = true); // To start the game, mouse must be clicked.
		
		stage.setScene(new Scene(new StackPane(canvas)));
		stage.show();
		timeline.play();
	}
	
	private void run(GraphicsContext graphicsContext) {
		// Set background.
		table.setBackground(graphicsContext);
		
		// Set text color.
		this.table.setTextColorAndFont(graphicsContext);
		
		if(this.isStarted == true) {
			// Set ball movement.
			this.ball.setMovement();
		
			// Set CPU opponent's paddle (to follow the ball). Only if needed.
			this.paddle1.setCPUPaddle();
			this.paddle2.setCPUPaddle();
			this.paddle2.keepBoundaries();
			
			// Draw ball.
			this.ball.draw(graphicsContext);
		}
		else {
			// Set the start text.
			this.table.setStartText(graphicsContext);
			
			// Reset the ball starting position.
			this.ball.resetBallToStartingPos();			
			
			// Reset ball's speed & direction.
			this.ball.resetDirectionAndSpeed();
		}
		
		// If the ball hits the canvas' edges of the field, it will bounce back.
		this.ball.checkEdgesOnCanvas();
		
		// Player's paddle miss the ball -> CPU opponent gets a point.
		if(this.ball.getBallXPos() < (this.paddle1.getPaddleXCoor() - Paddle.PLAYER_WIDTH)) { this.scoreP2++; this.isStarted = false; } 				

		// CPU opponent's paddle miss the ball -> Human player gets a point.
		if(this.ball.getBallXPos() > (this.paddle2.getPaddleXCoor() + Paddle.PLAYER_WIDTH)) { this.scoreP1++; this.isStarted = false; } 	
		
		// Increase the speed after player's paddle hits the ball.
		if(this.paddle1.checkHit() || this.paddle2.checkHit()) { this.ball.increaseSpeed(); }

		// Draws score board.
		this.table.drawScoreBoard(graphicsContext, this.scoreP1, this.scoreP2);
		
		// Draws Players' paddles.
		this.paddle1.draw(graphicsContext);
		this.paddle2.draw(graphicsContext);
		
		// Keeps the paddle in the canvas' boundaries.
		this.paddle1.keepBoundaries();
		this.paddle2.keepBoundaries();
		
		// If game over. finish the game. 
		if((this.scoreP1 + this.scoreP2) == GAMES) {
			this.table.setGameOver(graphicsContext, scoreP1, scoreP2);
			this.isStarted = false;
			return;
		}
	}
	
	// Starts the application.
	public static void main(String[] args) {
		launch(args);
	}
}