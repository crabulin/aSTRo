package aSTRo.modele.graphe;

import java.util.HashMap;

public abstract class GrapheAbstrait {

	/**
	 * ajoute le sommet de nom donné au graphe
	 * 
	 * @param nom
	 */
	public abstract void ajouterSommet(String nom);

	/**
	 * ajoute l'arete entre les sommets donnes par nom, de longueur l
	 * 
	 * @param nom1
	 * @param nom2
	 * @param l
	 */
	public abstract void ajouterArete(String nom1, String nom2, Double l);

	/**
	 * renvoie le sommet de nom donné
	 * 
	 * @param nom
	 * @return
	 */
	public abstract Sommet getSommetParNom(String nom);

	/**
	 * change le parcours du graphe
	 * 
	 * @param parcours
	 */
	public abstract void setParcours(Parcours parcours);

	/**
	 * lance le parcours du graphe
	 */
	public abstract void lancerParcours();

	/**renvoie la map des predecesseurs en entier
	 * @return
	 */
	public abstract HashMap<Sommet, Sommet> getPredecesseurMap();

	/**renvoie uniquement le predecesseur d'un sommet pour le parcours courant
	 * @param s
	 * @return
	 */
	public abstract Sommet getPredecesseur(Sommet s);

}
