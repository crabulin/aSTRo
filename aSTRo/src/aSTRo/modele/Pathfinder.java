package aSTRo.modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeSet;

import aSTRo.modele.graphe.Arete;
import aSTRo.modele.graphe.Astar;
import aSTRo.modele.graphe.BellmanFord;
import aSTRo.modele.graphe.Dijkstra;
import aSTRo.modele.graphe.Graphe;
import aSTRo.modele.graphe.Parcours;
import aSTRo.modele.graphe.ParcoursEnLargeur;
import aSTRo.modele.graphe.Sommet;
/**
 * Classe faisant le lien dans le modèle entre la carte (Map) et le Graphe. 
 * Crée le graphe associé à la carte, lance les requetes de plus courts chemins
 * et traduit les résultats du vocabulaire des graphes
 *  en termes de carte (cellules, entités, entités statiques) 
 * @author david
 *
 */

public class Pathfinder {

	/** le graphe associé à la carte (Map) où sont effectués les calculs de plus courts chemins
	 *  
	 */
	private Graphe g;
	
	/** la carte depuis laquelle le graphe sera calculé
	 * 
	 */
	private MapRectGrid map;

	
	public Pathfinder(MapRectGrid map) {
		super();
		this.map = map;

		this.g = new Graphe();
		// on cree le graphe

		Cellule[][] cells = map.listeToutesCellules();

		// remplissage des sommets
		for (int x = 0; x < map.largeur; x++) {
			for (int y = 0; y < map.hauteur; y++) {
				if (cells[x][y].getType().nonBloquant) {
					g.ajouterSommet(cells[x][y].nom);
				}
			}
		}

		// remplissage des aretes
		for (int x = 0; x < map.largeur; x++) {
			for (int y = 0; y < map.hauteur; y++) {
				if (cells[x][y].getType().nonBloquant) {
					ArrayList<Cellule> voisins = map
							.cellulesVoisines(cells[x][y]);
					for (Cellule v : voisins) {
						double longueur;
						if(Math.abs(v.x-x)+Math.abs(v.y-y)==1)
							longueur = 1;
						else
							longueur = 1.41;
						g.ajouterArete(cells[x][y].nom, v.nom,longueur);
								
					}

				}
			}
		}

	}

	private LinkedList<Sommet> plusCourtChemin(Sommet s1, Sommet s2) {

		///////////////////////////////////////////////////////////////////////
		//Décommenter une seule des lignes suivantes
		g.setParcours(new Astar(g, s1, s2));
		//g.setParcours(new ParcoursEnLargeur(g, s1, s2));
		//g.setParcours(new BellmanFord(g, s1));
		//g.setParcours(new Dijkstra(g, s1, s2));
		///////////////////////////////////////////////////////////////////////
		
		
		
		g.lancerParcours();

		for (Entry<Sommet, Sommet> entry : g.getPredecesseurMap().entrySet())
		{
		int coordCourant[] = getCoordonneesSommet(entry.getKey());
		int coordPred[] = getCoordonneesSommet(entry.getValue());
		
				String direction = "f";

				if (coordPred[0] - coordCourant[0] == 1)
					direction += "g";
				else if (coordPred[0] - coordCourant[0] == -1)
					direction += "d";
				if (coordPred[1] - coordCourant[1] == 1)
					direction += "h";
				else if (coordPred[1] - coordCourant[1] == -1)
					direction += "b";
				map.cells[coordCourant[0]][coordCourant[1]]
						.setObjetStatique(new EntiteStatique(direction));
			}
		

		LinkedList<Sommet> chemin = new LinkedList<>();
		Sommet courant = s2;
		Sommet suivant = null;
		while (courant != s1) {
			chemin.addFirst(courant);
			suivant = courant;
			courant = g.getPredecesseur(courant);
			int coordCourant[] = getCoordonneesSommet(courant);
			int coordSuivant[] = getCoordonneesSommet(suivant);

			String direction = "fr";
			if (coordSuivant[0] - coordCourant[0] == 1)
				direction += "d";
			if (coordSuivant[0] - coordCourant[0] == -1)
				direction += "g";
			if (coordSuivant[1] - coordCourant[1] == 1)
				direction += "b";
			if (coordSuivant[1] - coordCourant[1] == -1)
				direction += "h";
			map.cells[coordCourant[0]][coordCourant[1]]
					.setObjetStatique(new EntiteStatique(direction));
		}

		chemin.addFirst(s1);
		return chemin;

	}

	/**calcule les coordonnées d'un sommet a partir de son nom
	 * Dans le constructeur du pathfinder, les sommets du graphe ont un nom
	 * de la forme "12 27" où 12 et 27 sont les coordonnées de la cellule (case) correspondante.
	 * @param s
	 * @return
	 */
	public int[] getCoordonneesSommet(Sommet s) {
		int[] coor = new int[2];
		String[] split = s.nom.split(" ");
		coor[0] = Integer.parseInt(split[0]);
		coor[1] = Integer.parseInt(split[1]);
		return coor;
	}

	/** Renvoie la suite de deplacements élémentaires pour
	 * se deplacer de c1 à c2,
	 * en appelant la méthode plusCourtChemin
	 * @param acteur
	 * @param c1
	 * @param c2
	 * @return
	 */
	
	public LinkedList<DeplacementElementaire> plusCourtDeplacements(Entite acteur, Cellule c1, Cellule c2) {
			
		LinkedList<DeplacementElementaire> deplacements = new LinkedList<DeplacementElementaire>();
		
		LinkedList<Sommet> chemin = plusCourtChemin(g.getSommetParNom(c1.nom), g.getSommetParNom(c2.nom));
		Iterator<Sommet> it = chemin.iterator();
		Cellule precedente = null;
		
		Cellule courante = map.getCell(it.next());
		while (it.hasNext()){
			precedente = courante;
			courante=map.getCell(it.next());
			deplacements.add(new DeplacementElementaire(acteur, courante.x-precedente.x, courante.y-precedente.y));
		}
		
		return deplacements;
		
	}

}
