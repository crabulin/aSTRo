package aSTRo.modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

import aSTRo.modele.graphe.Arete;
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
						g.ajouterArete(g.getSommetParNom(cells[x][y].nom),
								g.getSommetParNom(v.nom));
					}

				}
			}
		}

	}

	public LinkedList<Sommet> cheminLargeur(Sommet s1, Sommet s2) {

		HashMap<Sommet, Sommet> predecesseur = new HashMap<>();
		TreeSet<Sommet> connus = new TreeSet<>();
		LinkedList<Sommet> attente = new LinkedList<>();
		attente.addLast(s1);

		Sommet courant = null;

		while (!attente.isEmpty() && courant != s2) {
			// while (!attente.isEmpty() ) {
			courant = attente.removeFirst();
			for (Sommet voisin : g.getVoisins(courant)) {
				if (!connus.contains(voisin)) {
					predecesseur.put(voisin, courant);

					String direction = "f";
					int coordCourant[] = getCoordonneesSommet(courant);
					int coordVoisin[] = getCoordonneesSommet(voisin);
					if (coordCourant[0] - coordVoisin[0] == 1)
						direction += "g";
					else if (coordCourant[0] - coordVoisin[0] == -1)
						direction += "d";
					if (coordCourant[1] - coordVoisin[1] == 1)
						direction += "h";
					else if (coordCourant[1] - coordVoisin[1] == -1)
						direction += "b";
					map.cells[coordVoisin[0]][coordVoisin[1]]
							.setObjetStatique(new EntiteStatique(direction));

					connus.add(voisin);
					attente.addLast(voisin);
				}
			}
		}

		// if (s2 != courant)
		// return null;

		// else
		LinkedList<Sommet> chemin = new LinkedList<>();
		courant = s2;
		Sommet suivant = null;
		while (courant != s1) {
			chemin.addFirst(courant);
			suivant = courant;
			courant = predecesseur.get(courant);
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
	
	public LinkedList<DeplacementElementaire> routePlusCourte(Entite acteur, Cellule c1, Cellule c2){
		LinkedList<DeplacementElementaire> deplacements = new LinkedList<DeplacementElementaire>();
		
		
		LinkedList<Sommet> chemin = cheminLargeur(g.getSommetParNom(c1.nom), g.getSommetParNom(c2.nom));
		Iterator<Sommet> it = chemin.iterator();
		Cellule precedente = null;
	
		
		Cellule courante = map.getCell( getCoordonneesSommet(it.next()));
		while (it.hasNext()) {
			precedente = courante;
			courante= map.getCell( getCoordonneesSommet(it.next()));
			deplacements.add(new DeplacementElementaire(acteur, courante.x-precedente.x, courante.y-precedente.y));
		}
		
		return deplacements;
	}
	
}
