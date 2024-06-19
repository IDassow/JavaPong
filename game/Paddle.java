package game;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends Rectangle{
	
	int id;
	int yVel, speed = 10;
	Paddle(int pos_x, int pos_y, int paddleWidth, int paddleHeight, int p_id){
		super(pos_x, pos_y, paddleWidth, paddleHeight);
		this.id = p_id;
	}
	
	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setYDir(-speed);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				setYDir(speed);
				move();
			}
			break;
		case 2:
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDir(-speed);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDir(speed);
				move();
			}
			break;
		}
	}
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode() == KeyEvent.VK_W) {
				setYDir(0);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				setYDir(0);
				move();
			}
			break;
		case 2:
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				setYDir(0);
				move();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setYDir(0);
				move();
			}
			break;
		}
	}
	public void setYDir(int ys) {
		yVel = ys;
	}
	public void move() {
		y += yVel;
	}
	public void draw(Graphics g) {
		Color c = (id == 1) ? Color.blue : Color.RED;
		g.setColor(c);
		g.fillRect(x,y,width, height);
	}
}
