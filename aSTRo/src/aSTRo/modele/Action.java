package aSTRo.modele;

public abstract class Action {
	public String nom ;
	public double avancement ;
	
	public abstract boolean update(double dt);
}
