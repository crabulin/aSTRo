package aSTRo.modele.graphe;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

import aSTRo.modele.EntiteStatique;


public class Pathfinder {

	public Graphe g;

	public Pathfinder(Graphe grapheCarte) {
		super();
		this.g = grapheCarte;
	}

	public LinkedList<Sommet> cheminLargeur(Sommet s1, Sommet s2) {
		
		HashMap<Sommet, Sommet> predecesseur = new HashMap<>();
		TreeSet<Sommet> connus = new TreeSet<>();
		LinkedList<Sommet> attente = new LinkedList<>();
		attente.addLast(s1);

		Sommet courant = null;

		while (!attente.isEmpty() && courant != s2) {
//		while (!attente.isEmpty() ) {
			courant = attente.removeFirst();
			for (Sommet voisin : g.listeAdj.get(courant)) {
				if (!connus.contains(voisin)) {
					predecesseur.put(voisin, courant);
					
					String direction = "f";
					if(courant.cellule.x-voisin.cellule.x == 1)
						direction += "g";
					else if (courant.cellule.x-voisin.cellule.x == -1)
						direction += "d";
					if(courant.cellule.y-voisin.cellule.y == 1)
						direction += "h";
					else if (courant.cellule.y-voisin.cellule.y == -1)
						direction += "b";
					voisin.cellule.setObjetStatique(new EntiteStatique(direction));
					
							
					connus.add(voisin);
					attente.addLast(voisin);
				}
			}
		}

//		if (s2 != courant)
//			return null;

		// else
		LinkedList<Sommet> chemin = new LinkedList<>();
		courant = s2;
		Sommet suivant = null;
		while (courant != s1) {
			chemin.addFirst(courant);
			suivant = courant;
			courant = predecesseur.get(courant);
			String direction = "fr";
			if(suivant.cellule.x-courant.cellule.x == 1)
				direction += "d";
			if(suivant.cellule.x-courant.cellule.x == -1)
				direction += "g";
			if(suivant.cellule.y-courant.cellule.y == 1)
				direction += "b";
			if(suivant.cellule.y-courant.cellule.y == -1)
				direction += "h";
			courant.cellule.setObjetStatique(new EntiteStatique(direction));
		}
		chemin.addFirst(s1); 
		return chemin;
		
	}

}
