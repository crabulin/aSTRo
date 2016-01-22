
public class Zelda extends Entite {

	public Zelda(int x, int y) {
		super(x, y);
		action = 1;
		pourcentage = 0;
	}

	double vitesse = 0.5;   //cases par seconde
	
	public void update(long dt){
		pourcentage += 100 * (((double)dt)/1000000000*vitesse);
		if (pourcentage > 100) {
			x += action;
			action = - action ;
			pourcentage = 0;
		}
		
		
	}
}
