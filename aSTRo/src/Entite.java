
public abstract class Entite implements selectionnable {
	int x,y ;
	int action;
	double pourcentage;

	public Entite(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public abstract void update(long dt) ;
	
	
	public int getAction() {
		return action;
	}

	public double getPourcentage(){
		return pourcentage;
	}
	
}
