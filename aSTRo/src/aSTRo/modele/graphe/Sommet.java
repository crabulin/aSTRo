package aSTRo.modele.graphe;

public class Sommet implements Comparable<Sommet>{
	public String nom ;

	public Sommet(String nom) {
		super();
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return "s("+nom+")";
	}

	@Override
	public int compareTo(Sommet s) {
		return this.nom.compareTo(((Sommet)s).nom);
	}


}
