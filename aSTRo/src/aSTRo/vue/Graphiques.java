package aSTRo.vue;
import aSTRo.modele.Cellule;
import aSTRo.modele.Modele;


public abstract class Graphiques {

	Modele modele ;
	
	abstract Modele getModele();
	
	public abstract void init();
	public abstract void update(long dt);
	public abstract Cellule getCelluleDuClick(int x, int y);
}
