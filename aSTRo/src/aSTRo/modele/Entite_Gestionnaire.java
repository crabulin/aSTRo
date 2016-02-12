package aSTRo.modele;

import java.util.LinkedList;



public class Entite_Gestionnaire {
	Entite liste[];
	MapRectGrid map;
	
	public Entite_Gestionnaire(MapRectGrid map) {
		int x=22;
		int y=0;
		Zelda z = new Zelda(x,y);
		liste = new Entite[] {z};
		this.map = map;
		
		LinkedList<DeplacementElementaire> route = map.routePlusCourte(z, map.cells[x][y], map.cells[11][11]);
		for(DeplacementElementaire de : route){
			z.actionsEnAttente.add(de);
		}
	}
	
	public Entite[] getListeEntite() {
		return liste;
	}

	public void update(long dt) {
		for (Entite ent : liste) {
			ent.update(dt);
		}
		
	}
}
