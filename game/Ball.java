package game;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle {
	
	Random random;
	float xVel, yVel;
	int initSpeed = 2;
	
	Ball(int pos_x, int pos_y, int ballDiameter){
		super(pos_x, pos_y, ballDiameter, ballDiameter);
		random = new Random();
		int random_x = random.nextInt(2);
		int random_y = random.nextInt(2);
		if(random_x == 0) random_x--;
		setXDir(random_x * initSpeed);
		if(random_y == 0) random_y--;
		setYDir(random_y * initSpeed);
	}
	
	public void setYDir(float yd) {
		yVel = yd;
	}
	public void setXDir(float xd) {
		xVel = xd;
	}
	public void move() {
		x += xVel;
		y += yVel;
	}
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, height, width);;
	}
}
