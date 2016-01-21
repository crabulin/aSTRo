import java.awt.AlphaComposite;
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
	BufferedImage zelda;
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

			
		//chargement des images, provisoire
		BufferedImage planche = null ;
		try {
			planche = ImageIO.read(new File("src/images/grassgrid.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		herbe = planche.getSubimage(1, 1, 32, 32);

		try {
			planche = ImageIO.read(new File("src/images/zeldalike2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		zelda = planche.getSubimage(1, 1, 32, 32);

	}
	
	public void paint(Graphics g)
	{
		render(0L);
		
	}
	
	public void render(long dt)
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
	    //g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		
	    //efface l'ecran
	    g.setColor(Color.CYAN);
	    g.fillRect(0, 0, 800, 600);
	    
	    g.setColor(Color.BLACK);
	    
	    for(Cellule cell : graph.getModele().cellulesADessiner()){
	    	if (cell.type!=null){
	    		g.drawImage(herbe, cell.x * largeur_tuile, cell.y * largeur_tuile, null);
	    	}
	    	if (cell.type==Cellule.ZELDA){
	    		g.drawImage(zelda, cell.x * largeur_tuile, cell.y * largeur_tuile, null);
	    	}

	    	
	    }
	    
	    g.drawString((int) (1000000000.0/dt)+ " fps", 20, 20);
		g.dispose();
		bs.show();
		
	}
	

	
	
}
