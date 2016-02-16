package aSTRo.modele;



public class Entite_Gestionnaire {
	Entite liste[];
	MapRectGrid map;
	Entite selection;
	
	public Entite_Gestionnaire(MapRectGrid map) {
		int x=22;
		int y=0;
		Zelda z = new Zelda(x,y);
		selection=z;
		liste = new Entite[] {z};
		this.map = map;
		
		
	}
	
	public Entite[] getListeEntite() {
		return liste;
	}

	public void update(long dt) {
		for (Entite ent : liste) {
			ent.update(dt);
		}
		
	}
	
	public Entite getSelection() {
		return selection;
	}
	
	//implementation trop basique !
	public Entite getEntiteAuxCoordonnees(int x, int y) {
		System.out.println("test");
		for(Entite ent : liste) {
			if (ent.x==x && ent.y == y)
				return ent;
		}
		return null;
	}
}
