import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

public class Pathfinder {

	Graphe g;

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

//		while (!attente.isEmpty() && courant != s2) {
		while (!attente.isEmpty() ) {
			courant = attente.removeFirst();
			for (Sommet voisin : g.listeAdj.get(courant)) {
				if (!connus.contains(voisin)) {
					predecesseur.put(voisin, courant);
					
					String direction = "f";
					if(courant.cellule.x-voisin.cellule.x == 1)
						direction += "d";
					else if (courant.cellule.x-voisin.cellule.x == -1)
						direction += "g";
					if(courant.cellule.y-voisin.cellule.y == 1)
						direction += "b";
					else if (courant.cellule.y-voisin.cellule.y == -1)
						direction += "h";
					voisin.cellule.objetStatique = new EntiteStatique(direction);
					
							
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
		while (courant != s1) {
			chemin.addFirst(courant);
			courant = predecesseur.get(courant);
		}
		chemin.addFirst(s1); 
		return chemin;
		
	}

}
