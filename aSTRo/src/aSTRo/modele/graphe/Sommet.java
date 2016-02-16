package aSTRo.modele.graphe;

import aSTRo.modele.Cellule;


public class Sommet implements Comparable<Sommet>{
	String nom ;
	public Cellule cellule ;

	public Sommet(String nom, Cellule c) {
		super();
		this.nom = nom;
		this.cellule = c;
	}


	@Override
	public int compareTo(Sommet s) {
		return this.nom.compareTo(s.nom);
	}

	@Override
	public String toString(){
		return "("+this.cellule.x+","+this.cellule.y+")";
	}
}
