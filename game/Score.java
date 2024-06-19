package game;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Score extends Rectangle {
	
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int player1_score, player2_score; 
	
	Score(int width, int height){
		Score.GAME_WIDTH = width;
		Score.GAME_HEIGHT = height;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Impact", Font.PLAIN, 60));
		
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		g.drawRect(0, 1, GAME_WIDTH-2, GAME_HEIGHT-2);
		g.drawRoundRect((GAME_WIDTH/2) - 75, (GAME_HEIGHT/2) - 75, 150, 150, 150, 150);
		
		g.drawString(String.valueOf(player1_score/10) + 
					String.valueOf(player1_score%10), 
					(GAME_WIDTH/4) - 20, 60);
		g.drawString(String.valueOf(player2_score/10) +
					String.valueOf(player2_score%10), 
					(GAME_WIDTH/2)+(GAME_WIDTH/4)-20, 60);
	}
}
