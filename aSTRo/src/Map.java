
public abstract class Map {

	public Pathfinder pf;
	public abstract void init();
	public abstract void update(long dt);
	public abstract Cellule[] cellulesADessiner() ;
	
	
}
