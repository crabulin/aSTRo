package aSTRo.modele.graphe;

import java.util.HashMap;

public abstract class GrapheAbstrait {

	public abstract void lancerParcours() ;

	public abstract void ajouterArete(Sommet s1, Sommet s2, Double l);

	public abstract void ajouterArete(Sommet s1, Sommet s2);

	public abstract Sommet getSommetParNom(String nom);

	public abstract void ajouterSommet(String nom);
		
	public abstract HashMap<Sommet, Sommet> getPredecesseurMap() ;

	public abstract Sommet getPredecesseur(Sommet s);

	public abstract void setParcours(Parcours parcours);


}
