import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Panneau extends Canvas {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Graphiques graph ;
	BufferStrategy bs;
	BufferedImage herbe;
	int largeur_tuile = 32;
	
	public Panneau(Graphiques graph)
	{
		this.bs = null;
		this.graph = graph;
		Dimension size = new Dimension(800, 600);
		setSize(size);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);

			
		//chargement de l'image, provisoire
		BufferedImage planche = null ;
		try {
			planche = ImageIO.read(new File("src/images/grassgrid.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		herbe = planche.getSubimage(1, 1, 32, 32);
	    	
	}
	
	public void paint(Graphics g)
	{
		render();
		
	}
	
	public void render()
	{
		if (this.bs==null)
		{
			this.createBufferStrategy(3);
			this.bs = this.getBufferStrategy();
		}
		
		this.requestFocus();
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_ANTIALIASING,
	             RenderingHints.VALUE_ANTIALIAS_ON);
	    g.setRenderingHints(rh);
		
	    //efface l'ecran
	    g.setColor(Color.blue);
	    g.fillRect(0, 0, 800, 600);
	    
	    g.setColor(Color.BLACK);
	    
	    for(Cellule cell : graph.getModele().cellulesADessiner()){
	    	if (cell!=null){
	    		g.drawImage(herbe, cell.x * largeur_tuile, cell.y * largeur_tuile, null);
	    	}
	    		
	    	
	    }

		g.dispose();
		bs.show();
		
	}
	

	
	
}