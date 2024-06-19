package game;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class PongPanel extends JPanel implements Runnable{

	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.55555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random rand;
	Paddle paddle1, paddle2;
	Ball ball;
	Score score;


	PongPanel(){
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		//game loop
		while(true) {
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			
			if(delta >= 1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	
	public void newBall() {
		//rand = new Random();
		ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),(GAME_HEIGHT/2)-(BALL_DIAMETER/2), BALL_DIAMETER);
	}
	public void newPaddles() {
		paddle1 = new Paddle(
				0,
				(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), 
				PADDLE_WIDTH, 
				PADDLE_HEIGHT, 
				1);
		paddle2 = new Paddle(
				GAME_WIDTH - PADDLE_WIDTH,
				(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2), 
				PADDLE_WIDTH, 
				PADDLE_HEIGHT, 
				2);
		
	}
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}
	public void draw(Graphics g) {
		score.draw(g);
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		
	}
	
	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	public void checkCollision() {
		if(paddle1.y <= 0) {
			paddle1.y = 0;
		}
		if(paddle1.y + paddle1.height >= GAME_HEIGHT) {
			paddle1.y = GAME_HEIGHT - paddle1.height ;
		}
		if(paddle2.y <= 0) {
			paddle2.y = 0;
		}
		if(paddle2.y + paddle2.height >= GAME_HEIGHT) {
			paddle2.y = GAME_HEIGHT - paddle2.height ;
		}
		if(ball.y<=0) {
			
			ball.setYDir(-ball.yVel);
		}
		if(ball.y  >= GAME_HEIGHT - BALL_DIAMETER) {
			
			ball.setYDir(-ball.yVel);
		}
		
		if(ball.x<=0) {
			score.player2_score++;
			newPaddles();
			newBall();
			
		}
		if(ball.x  >= GAME_WIDTH - BALL_DIAMETER) {
			score.player1_score++;
			newPaddles();
			newBall();
		}
		
		if(ball.intersects(paddle1)) {
			ball.xVel= Math.abs(ball.xVel);
			ball.xVel += 0.65f;
			if(ball.yVel>0)
				ball.yVel += .65f;
			else
				ball.yVel -= 0.05f;
			
			ball.setXDir(ball.xVel);
			ball.setYDir(ball.yVel);
			
		}
		if(ball.intersects(paddle2)) {
			ball.xVel= Math.abs(ball.xVel);
			ball.xVel += 0.65f;
			if(ball.yVel>0)
				ball.yVel += .65f;
			else
				ball.yVel -= 0.05f;
			ball.setXDir(-ball.xVel);
			ball.setYDir(ball.yVel);
		}
	}

}
