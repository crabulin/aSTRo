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

		public LinkedList<Sommet> cheminLargeur(Sommet s1, Sommet s2){
		HashMap<Sommet, Sommet> predecesseur = new HashMap<>();
		TreeSet<Sommet> connus = new TreeSet<>();
		LinkedList<Sommet> attente = new LinkedList<>();
		attente.addLast(s1);
		
		Sommet courant = null;
		
		while(!attente.isEmpty() && courant != s2) {
			courant = attente.removeFirst();
			for(Sommet voisin : g.listeAdj.get(courant) ) {
				System.out.println(g.listeAdj.toString());
				if(!connus.contains(voisin)){
					predecesseur.put(voisin, courant);
					connus.add(voisin);
					attente.addLast(voisin);
				}
			}
		}
		
		if (s2!=courant)
			return null;
					
		//else
		LinkedList<Sommet> chemin = new LinkedList<>();
		courant = s2;
		while(courant!=s1) {
			chemin.addFirst(courant);
			courant = predecesseur.get(courant);
		}
		chemin.addFirst(s1);
		return chemin;
	}
	
}
