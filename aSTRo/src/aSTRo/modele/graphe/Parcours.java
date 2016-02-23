package aSTRo.modele.graphe;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

/**Classe abstraite pour un parcours general de graphe depuis un sommet
 * @author david
 *
 */
public abstract class Parcours {
	/**
	 *L'objet Graphe dans lequel le parcours est effectué 
	 */
	protected Graphe graphe;
	
	/**
	 * Le sommet depuis lequel le parcours sera effectué
	 */
	protected Sommet depart;
	protected Sommet destination;
	/**
	 *Predecesseurs dans l'arborescence de parcours 
	 */
	protected HashMap<Sommet, Sommet> predecesseurs;
	
	/**
	 * Indique si un parcours a deja été effectué
	 */
	protected boolean parcouru= false;
	
	protected TreeSet<Sommet> sommetsRencontres;
	
	protected Sommet getPredecesseur(Sommet s){
		return predecesseurs.get(s);
	}

	protected HashMap<Sommet, Sommet> getPredecesseurMap() {
		return predecesseurs;
	}

	/**Indique si le sommet a été rencontré lors du parcours
	 * @param s
	 * @return
	 */
	public boolean connu(Sommet s){
		//erreur si pas encore de parcours
		if (!parcouru) {
			try {
				throw new Exception("parcours pas encore effectué !");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return sommetsRencontres.contains(s); 
	}
	
	/**
	 *Lancement du parcours du graphe 
	 */
	public abstract void parcourir();
	
	
	/**Renvoie un plus court chemin du sommet depart au sommet de destination
	 * précisé
	 * @param dest
	 * @return
	 */
	public abstract LinkedList<Sommet> plusCourtChemin(Sommet dest);

	public Parcours(Graphe graphe, Sommet depart, Sommet destination) {
		super();
		this.graphe = graphe;
		this.depart = depart;
		this.destination = destination;
		this.sommetsRencontres=new TreeSet<Sommet>();
	}
	
	public Parcours(Graphe graphe, Sommet depart) {
		this(graphe,depart,null);
		}

	public abstract double getDistance(Sommet s) ;
	
	
}
