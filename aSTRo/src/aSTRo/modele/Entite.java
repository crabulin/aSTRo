package aSTRo.modele;

import java.util.LinkedList;

import aSTRo.vue.Selectionnable;


public abstract class Entite implements Selectionnable {
	public int x,y ; 
	public double pourcentage;
	public Action actionEnCours ;
	protected MapRectGrid map;
	LinkedList<Action> actionsEnAttente ;

	public Entite(int x, int y, MapRectGrid map) {
		this.x = x;
		this.y = y;
		actionEnCours = null ;
		actionsEnAttente = new LinkedList<Action>();
		this.map = map;
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
	
	public void viderActionsEnAttente(){
		actionsEnAttente.clear();
	}
	
	public void ajouterAction(Action ac) {
		actionsEnAttente.add(ac);
	}

	public void translation(int xDirection, int yDirection) {
		this.x += xDirection;
		this.y += yDirection;
	}

	public abstract void ordredAction(Cellule cel) ;
	
}
