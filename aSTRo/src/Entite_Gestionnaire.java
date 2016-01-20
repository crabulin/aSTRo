
public class Entite_Gestionnaire {
	Entite liste[];
	
	public Entite_Gestionnaire() {
		liste = new Entite[] {new Entite(10,15)};
	}
	
	public Entite[] getListeEntite() {
		return liste;
	}
}
