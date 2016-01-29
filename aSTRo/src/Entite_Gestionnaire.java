import java.util.LinkedList;


public class Entite_Gestionnaire {
	Entite liste[];
	MapRectGrid map;
	
	public Entite_Gestionnaire(MapRectGrid map) {
		Zelda z = new Zelda(5,3);
		liste = new Entite[] {z};
		this.map = map;
		
		LinkedList<DeplacementElementaire> route = map.routePlusCourte(z, map.cells[3][2], map.cells[10][15]);
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
