package game_pong;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Table {
	
	// Sets the start text of the game.
	public void setStartText(GraphicsContext graphicsContext) {
		
		graphicsContext.setStroke(Color.WHITE);
		graphicsContext.setTextAlign(TextAlignment.CENTER);
		graphicsContext.strokeText("Click to Start...", (Pong.WIDTH / 2) , (Pong.HEIGHT / 3));
	}
	
	// Sets table's background.
	public void setBackground(GraphicsContext graphicsContext) {
		graphicsContext.setFill(Color.DARKSLATEBLUE); 
		graphicsContext.fillRect(0, 0, Pong.WIDTH, Pong.HEIGHT);
		graphicsContext.setFill(Color.WHITE);
		graphicsContext.strokeLine((Pong.WIDTH /2) , 0, (Pong.WIDTH / 2), Pong.HEIGHT);
		graphicsContext.fillOval(((Pong.WIDTH /2) - (Ball.BALL_R * 3/2)), 
								((Pong.HEIGHT / 2) - (Ball.BALL_R * 3/2)), 
								(Ball.BALL_R * 3), (Ball.BALL_R * 3));
	}

	// Set text color & font.
	public void setTextColorAndFont(GraphicsContext graphicsContext) {
		graphicsContext.setFill(Color.WHITE);
		graphicsContext.setFont(Font.font(25));
	}
		
	// Draws score board.
	public void drawScoreBoard(GraphicsContext graphicsContext, int scoreP1, int scoreP2) {
		graphicsContext.setFill(Color.WHITE);
		graphicsContext.fillText("Alice \t" + scoreP1 + "\t\t\t\t\t" + scoreP2 + "\t CPU", (Pong.WIDTH / 2), (Pong.HEIGHT / 6));
	}
	
	// Set 'Game Over' background.
	public void setGameOver(GraphicsContext graphicsContext, int scoreP1, int scoreP2) {
		// Clears table.
		graphicsContext.clearRect(0, 0, Pong.WIDTH, Pong.HEIGHT);
		graphicsContext.setFill(Color.DARKSLATEBLUE); 
		
		// Sets 'Game Over' & the winner message.
		graphicsContext.fillRect(0, 0, Pong.WIDTH, Pong.HEIGHT);
		graphicsContext.setStroke(Color.RED);
		graphicsContext.setTextAlign(TextAlignment.CENTER);
		graphicsContext.strokeText("Game Over...", (Pong.WIDTH / 2) , (Pong.HEIGHT / 6));
		graphicsContext.strokeText(scoreP1 + "-" + scoreP2, (Pong.WIDTH / 2) , (Pong.HEIGHT / 4));
		
		if(scoreP1 > scoreP2) { graphicsContext.strokeText("Alice WON!!! You are the Pong King!!!", (Pong.WIDTH / 2) , (Pong.HEIGHT / 2)); }
		else { graphicsContext.strokeText("CPU Won.... Shame... Shame... Shame...", (Pong.WIDTH / 2) , (Pong.HEIGHT / 2)); }
	}
	
	
}
