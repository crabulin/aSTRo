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
	Graphiques graph;
	BufferStrategy bs;
	BufferedImage herbe;
	BufferedImage mur;
	BufferedImage zelda;
	int largeur_tuile = 32;

	// a voir
	int nbFrames = 0;
	long tempsCumule = 0;
	long moyenne;

	public Panneau(Graphiques graph) {
		this.bs = null;
		this.graph = graph;

		Dimension size = new Dimension(800, 600);
		setSize(size);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);

		// chargement des images, provisoire
		BufferedImage planche = null;

//		try {
//			planche = ImageIO.read(new File("src/images/grassgrid.png"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		herbe = planche.getSubimage(1, 1, 32, 32);
//		
		
		try {
			planche = ImageIO.read(new File("src/images/rpg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		herbe = planche.getSubimage(256, 64, 32, 32);
		mur = planche.getSubimage(160, 64, 32, 32);

		try {
			planche = ImageIO.read(new File("src/images/zeldalike2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		zelda = planche.getSubimage(1, 1, 32, 32);

		planche = null;
	}

	public void paint(Graphics g) {
		render(0L);

	}

	public void render(long dt) {
		if (this.bs == null) {
			this.createBufferStrategy(3);
			this.bs = this.getBufferStrategy();
		}

		this.requestFocus();
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHints(rh);

		// efface l'ecran
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, 800, 600);

		g.setColor(Color.BLACK);

		for (Cellule cell : graph.getModele().cellulesADessiner()) {
			if (cell.type == Cellule.HERBE) {
				g.drawImage(herbe, cell.x * largeur_tuile, cell.y
						* largeur_tuile, null);
			}
			if (cell.type == Cellule.MUR) {
				g.drawImage(mur, cell.x * largeur_tuile,
						cell.y * largeur_tuile, null);
			}

		}

		for (Entite ent : graph.getModele().entitesADessiner()) {
			double x = ent.x ;
			double y = ent.y;
			Action ac = ent.actionEnCours;
			if (ac != null &&  ac.nom == "DE") {
				x += ((DeplacementElementaire) ac).xDirection *ac.avancement / 100;
				y += ((DeplacementElementaire) ac).yDirection *ac.avancement / 100;
			}
			g.drawImage(zelda, (int) (x * largeur_tuile), (int) (y
					* largeur_tuile), null);
		}

		tempsCumule += dt;
		nbFrames++;
		if (tempsCumule > 1000000000L) {
			moyenne = 1000000000L * nbFrames / tempsCumule;
			tempsCumule = 0;
			nbFrames = 0;
		}

		g.drawString(moyenne + " fps", 20, 20);
		g.dispose();
		bs.show();

	}

}
