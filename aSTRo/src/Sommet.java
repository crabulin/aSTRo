
public class Sommet implements Comparable<Sommet>{
	String nom ;
	Cellule cellule ;

	public Sommet(String nom, Cellule c) {
		super();
		this.nom = nom;
		this.cellule = c;
	}


	@Override
	public int compareTo(Sommet s) {
		return this.nom.compareTo(s.nom);
	}
	
}
