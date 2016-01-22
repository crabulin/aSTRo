
public class Entite_Gestionnaire {
	Entite liste[];
	
	public Entite_Gestionnaire() {
		liste = new Entite[] {new Zelda(5,3), new Zelda(10,5), new Zelda(2,7)};
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
