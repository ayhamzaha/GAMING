package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.ObjKey;

public class UI {
	
	GamePanel gp;
	Font TNR_40,TNR_80B;
	BufferedImage keyImage;
	
	public boolean messageOn = false;
	public String message = "";
	
	int messageCounter = 0;
	
	public boolean gameFinished;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	
	
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		TNR_40 = new Font("Serif", Font.PLAIN,40);
		TNR_80B = new Font("Serif", Font.BOLD,80);
		ObjKey key = new ObjKey(gp);
		keyImage = key.image;
	}
	public void showMessage(String text) { 
		message = text;
		messageOn = true;
	}
	public void draw(Graphics2D g2) {
		if(gameFinished == true) {
			
			g2.setFont(TNR_40);
			g2.setColor(Color.WHITE);
			
			String text;
			int textLength;
			int x;
			int y;
			
			text = "You found the treasure!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenwidth/2 - textLength/2;
			y = gp.screenheight/2 - gp.tileSize*3;
			g2.drawString(text, x,y);
			
			
			text = "Your time is: " + dFormat.format(playTime) + "!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenwidth/2 - textLength/2;
			y = gp.screenheight/2 + gp.tileSize*4;
			g2.drawString(text, x,y);
			
			
			
			g2.setFont(TNR_80B);
			g2.setColor(Color.BLUE);
			text = "Congratulations!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenwidth/2 - textLength/2;
			y = gp.screenheight/2 + gp.tileSize*2;
			g2.drawString(text, x,y);
			gp.gameThread = null;
			
			
		}
		else {
			
		g2.setFont(TNR_40);
		g2.setColor(Color.WHITE);
		g2.drawImage(keyImage, gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
		g2.drawString("x "+gp.player.hasKey, 74, 65);
		
		
		playTime += (double)1/60;
		g2.drawString("Time: "+dFormat.format(playTime), gp.tileSize*11, 65);
		
		if(messageOn == true) {
			g2.setFont(g2.getFont().deriveFont(30F));
			g2.drawString(message, gp.tileSize/2, gp.tileSize*2);
			
			messageCounter++;
			if(messageCounter > 90) {
				messageCounter = 0;
				messageOn = false;
			}
		}
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
