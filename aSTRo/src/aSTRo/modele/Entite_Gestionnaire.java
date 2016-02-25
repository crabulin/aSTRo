package aSTRo.modele;



public class Entite_Gestionnaire {
	Entite liste[];
	MapRectGrid map;

	
	public Entite_Gestionnaire(MapRectGrid map) {
		int x=22;
		int y=0;
		Zelda z1 = new Zelda(x,y, map);
		
		x=10;
		y=5;
		Zelda z2 = new Zelda(x,y,map);


		liste = new Entite[] {z1,z2};
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
	

	
	//implementation trop basique !
	public Entite getEntiteAuxCoordonnees(int x, int y) {
		for(Entite ent : liste) {
			if (ent.x==x && ent.y == y)
				return ent;
		}
		return null;
	}
}
