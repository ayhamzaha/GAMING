package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjKey extends SuperObject{
	
	GamePanel gp;
	
	public ObjKey(GamePanel gp) {
		
		this.gp = gp;
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			uTool.scaleImage(image,gp.tileSize,gp.tileSize);
		}catch(IOException e){			
			e.printStackTrace();
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
