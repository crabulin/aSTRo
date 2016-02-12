package aSTRo.modele;


public class DeplacementElementaire extends Action {

	public int xDirection ;
	public int yDirection ;
	public double vitesse;
	Entite acteur;
	
	public DeplacementElementaire(Entite acteur, int xDirection,
			int yDirection) {
		
		super();
		nom = "DE";
		avancement = 0;
		vitesse = 1.5 ;
		
		this.acteur = acteur;
		this.xDirection = xDirection;
		this.yDirection = yDirection;
		
	}

	@Override
	public boolean update(double dt) {
				avancement += 100 * (((double)dt)/1000000000*vitesse);
		if (avancement > 100) {
			resolve();
			return true;
		}
		return false;
	}
	
	public void resolve() {
		acteur.translation(xDirection,yDirection);
		
	}

}
