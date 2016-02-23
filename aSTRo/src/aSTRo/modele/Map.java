package aSTRo.modele;

import java.util.LinkedList;



public abstract class Map {

	public Pathfinder pf;
	public abstract void init();
	public abstract void update(long dt);
	public abstract Cellule[] cellulesADessiner() ;
	public abstract Cellule getCellule(int x, int y);
	public abstract LinkedList<DeplacementElementaire> plusCourtDeplacements(Entite acteur, Cellule c1, Cellule c2);
	public abstract void supprimerObjetsStatiques();
	public abstract double getDistance(int x1, int y1, int x2, int y2);
		

	
	
}
