package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjChest extends SuperObject{
	
	GamePanel gp;
	
	
	public ObjChest(GamePanel gp) {
		this.gp = gp;
		name = "Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
			uTool.scaleImage(image,gp.tileSize,gp.tileSize);
		}catch(IOException e){			
			e.printStackTrace();
			
		}
	}
}
