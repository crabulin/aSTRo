
public class Entite_Gestionnaire {
	Entite liste[];
	
	public Entite_Gestionnaire() {
		Zelda z = new Zelda(5,3);
		liste = new Entite[] {z};
		z.actionsEnAttente.add(new DeplacementElementaire(z, 0, 1));
		z.actionsEnAttente.add(new DeplacementElementaire(z, 0, 1));
		z.actionsEnAttente.add(new DeplacementElementaire(z, 1, 1));
		z.actionsEnAttente.add(new DeplacementElementaire(z, 1, 0));
		z.actionsEnAttente.add(new DeplacementElementaire(z, 1, 0));
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
