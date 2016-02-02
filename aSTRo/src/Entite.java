import java.util.LinkedList;


public abstract class Entite implements selectionnable {
	int x,y ;
	double pourcentage;
	Action actionEnCours ;
	LinkedList<Action> actionsEnAttente ;

	public Entite(int x, int y) {
		this.x = x;
		this.y = y;
		actionEnCours = null ;
		actionsEnAttente = new LinkedList<Action>();
	}

	public void update(long dt) {
		if (actionEnCours != null) {
			boolean fini = actionEnCours.update(dt);
			if (fini)
				actionEnCours = null;
		}
		
		if (actionEnCours == null && (!actionsEnAttente.isEmpty())) {
			actionEnCours = actionsEnAttente.pop();
		}
		
		
			
		
	}
	
	
	public Action getAction() {
		return actionEnCours;
	}

	public double getPourcentage(){
		return pourcentage;
	}

	public void translation(int xDirection, int yDirection) {
		this.x += xDirection;
		this.y += yDirection;
	}
	
}
