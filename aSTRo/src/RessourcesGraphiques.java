import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;


public class RessourcesGraphiques {
	
	private HashMap<String,BufferedImage> sprites = new HashMap<String,BufferedImage>();
	
	public BufferedImage getSprite(String type){
		return sprites.get(type);
	}
	
	private void setSprite(String type, BufferedImage img)
	{
		sprites.put(type, img);
	}
	
	public RessourcesGraphiques() {
		
		int[][] coord= {{256,64},{160,64}};
		BufferedImage[] images = RessourcesGraphiques.charger_images("src/images/rpg.png", 32, coord);
		setSprite("herbe", images[0]); 
		setSprite("mur",  images[1]);

	
		int[][] coord2 = {{1,1}};
		setSprite("zelda",  RessourcesGraphiques.charger_images("src/images/zeldalike2.png", 32,coord2 )[0]);
		
		int[][] coord3 = {{32*3,0},{32*4,0},{32*5,0},{32*6,0},{32*7,0},{32*0,32},{32*1,32},{32*2,32}};
		images = RessourcesGraphiques.charger_images("src/images/fleches.png", 32,coord3 );
		setSprite("fd",images[0]);
		setSprite("fdh",images[1]);
		setSprite("fh",images[2]);
		setSprite("fgh",images[3]);
		setSprite("fg",images[4]);
		setSprite("fgb",images[5]);
		setSprite("fb",images[6]);
		setSprite("fdb",images[7]);
		
		images = RessourcesGraphiques.charger_images("src/images/flechesRouges.png", 32,coord3 );
		setSprite("frd",images[0]);
		setSprite("frdh",images[1]);
		setSprite("frh",images[2]);
		setSprite("frgh",images[3]);
		setSprite("frg",images[4]);
		setSprite("frgb",images[5]);
		setSprite("frb",images[6]);
		setSprite("frdb",images[7]);
	}

	public static BufferedImage[] charger_images(String pathImage, int largeur,int[][] coordonnees){
		BufferedImage planche = null;
		int nb_images = coordonnees.length;
		BufferedImage[] tuiles = new BufferedImage[nb_images];
		
		try {
			planche = ImageIO.read(new File(pathImage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i =0; i<nb_images;i++){
			 tuiles[i] = planche.getSubimage(coordonnees[i][0], coordonnees[i][1], largeur, largeur);
		}
		return tuiles;
				
	}
	
	
	
}
