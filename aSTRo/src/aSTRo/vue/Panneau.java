package aSTRo.vue;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import aSTRo.modele.Action;
import aSTRo.modele.Cellule;
import aSTRo.modele.DeplacementElementaire;
import aSTRo.modele.Entite;



public class Panneau extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Graphiques graph;
	RessourcesGraphiques ressources = new RessourcesGraphiques();
	BufferStrategy bs;
	InputGestionnaire input ;
	
	int largeur_tuile = 32;

	// a voir
	int nbFrames = 0;
	long tempsCumule = 0;
	long moyenne;
	int largeur = 800;
	int hauteur = 600;

	public Panneau(Graphiques graphiq) {
		this.bs = null;
		this.graph = graphiq;

		this.input = new InputGestionnaire(graphiq);
		addKeyListener(input);
		addFocusListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);
		
		Dimension size = new Dimension(largeur, hauteur);
		setSize(size);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);


		
		
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
		g.fillRect(0, 0, 800, 640);

		g.setColor(Color.BLACK);

		for (Cellule cell : graph.getModele().cellulesADessiner()) {
			if (cell.getType() == Cellule.HERBE) {
				g.drawImage(ressources.getSprite("herbe"), cell.x * largeur_tuile, cell.y
						* largeur_tuile, null);
			}
			if (cell.getType() == Cellule.MUR) {
				g.drawImage(ressources.getSprite("mur"), cell.x * largeur_tuile,
						cell.y * largeur_tuile, null);
			}
			
			
			if (cell.getObjetStatique()!=null){
				g.drawImage(ressources.getSprite(cell.getObjetStatique().type), (int) (cell.x * largeur_tuile), (int) (cell.y
						* largeur_tuile), null);
				
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
			g.drawImage(ressources.getSprite("zelda"), (int) (x * largeur_tuile), (int) (y
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

	public Cellule getCellule(int x, int y) {
		int xc = x/largeur_tuile;
		int yc=y /largeur_tuile;
		return graph.getModele().mapp.getCellule(xc, yc);
	}

}
