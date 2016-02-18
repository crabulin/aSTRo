package aSTRo.modele.graphe;

public class Arete {
	Sommet s1;
	Sommet s2;
	double longueur =1;
	boolean orientee;
	
	public Arete(Sommet s1, Sommet s2, boolean orientee) {
		super();
		this.s1 = s1;
		this.s2 = s2;
		this.orientee = orientee;
	}
	
	
	public Arete(Sommet s1, Sommet s2, double longueur, boolean orientee) {
		super();
		this.s1 = s1;
		this.s2 = s2;
		this.longueur = longueur;
		this.orientee = orientee;
	}


	@Override
	public String toString(){
		return "ar["+s1+"->"+s2+" : "+longueur+"]";
	}
}
