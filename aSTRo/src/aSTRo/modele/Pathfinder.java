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

public class Pathfinder {

	public Graphe g;
	MapRectGrid map;

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
						Sommet s1 = g.getSommetParNom(cells[x][y].nom);
						Sommet s2 = g.getSommetParNom(v.nom);
						g.ajouterArete(s1,s2,longueur);
								
					}

				}
			}
		}

	}

	public LinkedList<Sommet> cheminLargeur(Sommet s1, Sommet s2) {

		g.setParcours(new Astar(g, s1, s2));
		// g.setParcours(new ParcoursEnLargeur(g, s1, null));
		g.lancerParcours();

		for (Entry<Sommet, Sommet> entry : g.getPredecesseurMap().entrySet())
		{
		int coordCourant[] = getCoordonneesSommet(entry.getKey());
		int coordPred[] = getCoordonneesSommet(entry.getValue());
		
//		for (Sommet s : g.getListeSommet()) {
//			if (g.getPredecesseur(s) != null) {
//				int coordCourant[] = getCoordonneesSommet(s);
//				int coordPred[] = getCoordonneesSommet(g.getPredecesseur(s));
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

	int[] getCoordonneesSommet(Sommet s) {
		int[] coor = new int[2];
		String[] split = s.nom.split(" ");
		coor[0] = Integer.parseInt(split[0]);
		coor[1] = Integer.parseInt(split[1]);
		return coor;
	}

}
