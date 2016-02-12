package aSTRo.modele;


public abstract class Map {

	public Pathfinder pf;
	public abstract void init();
	public abstract void update(long dt);
	public abstract Cellule[] cellulesADessiner() ;
	public abstract Cellule getCellule(int x, int y);
	
	
}
