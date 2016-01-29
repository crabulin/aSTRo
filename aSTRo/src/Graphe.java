import java.util.ArrayList;
import java.util.HashMap;

public class Graphe {

	HashMap<String, Sommet> sommets;

	HashMap<Sommet, ArrayList<Sommet>> listeAdj;

	public void ajouterSommet(String nom, Cellule cel) {
		sommets.put(nom, new Sommet(nom, cel));
	}

	// pour l'instant non oriente seulement
	public void ajouterArete(Sommet s1, Sommet s2) {
		if (! listeAdj.containsKey(s1)) {
			listeAdj.put(s1, new ArrayList<Sommet>());
		}	
		if (! listeAdj.containsKey(s2)) {
			listeAdj.put(s2, new ArrayList<Sommet>());
		}
		
		
		if (!listeAdj.get(s1).contains(s2)) {
			listeAdj.get(s1).add(s2);
			listeAdj.get(s2).add(s1);
		}
	}

	public Graphe(MapRectGrid map) {
		sommets = new HashMap<String, Sommet>();

		listeAdj = new HashMap<Sommet, ArrayList<Sommet>>();

		Cellule[][] cells = map.listeToutesCellules();

		// remplissage des sommets
		for (int x = 0; x < map.largeur; x++) {
			for (int y = 0; y < map.hauteur; y++) {
				if (cells[x][y].type.nonBloquant) {
					this.ajouterSommet(cells[x][y].nom, cells[x][y]);
				}
			}
		}

		// remplissage des aretes
		for (int x = 0; x < map.largeur; x++) {
			for (int y = 0; y < map.hauteur; y++) {
				ArrayList<Cellule> voisins = map.cellulesVoisines(cells[x][y]);
				for (Cellule v : voisins) {
					this.ajouterArete(sommets.get(cells[x][y].nom),
							sommets.get(v.nom));
				}
			}
		}
	}
	

}