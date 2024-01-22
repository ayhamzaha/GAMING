package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	
	
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenwidth/2 - (gp.tileSize/2);
		screenY = gp.screenheight/2- (gp.tileSize/2);
		
		solidArea = new Rectangle(8,16,32,32);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
	}
	public void getPlayerImage() {
		d1 = setup("down_1");
		d2 = setup("down_2");
		l1 = setup("left_1");
		l2 = setup("left_2");
		r1 = setup("right_1");
		r2 = setup("right_2");
		u1 = setup("up_1");
		u2 = setup("up_2");
		idle = setup("idle");
		
	}
	public BufferedImage setup(String imageName) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/Player/"+imageName+".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
			
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	public void update() {
		direction = "idle";	
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				direction = "down";
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
			}
			//CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			
			
			//IF COLLISION IS FALSE PLAYER CANT MOVE
			if(collisionOn == false) {
				switch(direction) {
				case "up" :
					worldY-=speed;
					break;
				case "down":
					worldY+=speed;
					break;
				case "left":
					worldX-=speed;
					break;
				case "right":
					worldX+=speed;
					break;
				}
			}
		}
			
			
			spriteCounter++;
			if(spriteCounter > 15) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}	
		}
	public void pickUpObject(int i) {
		if(i!=999) {
			
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Key":
				gp.playSE(1);
				hasKey++;
				gp.obj[i] = null;
				gp.ui.showMessage("You got a key!");
				break;
			case "Door":
				gp.playSE(3);
				if(hasKey > 0) {
					gp.obj[i] = null;
					hasKey--;
					gp.ui.showMessage("You opened the door!");
				}
				else {
					gp.ui.showMessage("You need a key!");
				}
				break;
			case "Boots":
				gp.playSE(2);
				speed += 2;
				gp.obj[i] = null;
				gp.ui.showMessage("Speed Up!");
				break;
			case "Chest":
				gp.ui.gameFinished = true;
				gp.stopMusic();
				gp.playSE(4);
				break;
				
			}
		}
	}
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		switch(direction) {
		case "up" :
			if(spriteNum == 1) {
				image = u1;
			}
			if(spriteNum == 2) {
				image = u2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = d1;
			}
			if(spriteNum == 2) {
				image = d2;
			}
			break;
		case "left" :
			if(spriteNum == 1) {
				image = l1;
			}
			if(spriteNum == 2) {
				image = l2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = r1;
			}
			if(spriteNum == 2) {
				image = r2;
			}
			break;
		case "idle":
			image = idle;
		}
		g2.drawImage(image, screenX, screenY,null);
	}
		
}
