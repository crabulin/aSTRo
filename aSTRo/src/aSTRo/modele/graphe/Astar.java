package aSTRo.modele.graphe;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

public class Astar extends Parcours{


	/**
	 * distance depuis le sommet de depart
	 */
	HashMap<Sommet, Double> distance;
	
	public Astar(Graphe graphe, Sommet depart) {
		super(graphe, depart);
	}
	
	public Astar(Graphe graphe, Sommet depart, Sommet destination) {
		super(graphe, depart, destination);
	}



	@Override
	public void parcourir() {
		parcouru = true;
		// initialisation
		predecesseurs = new HashMap<Sommet, Sommet>();
		distance = new HashMap<Sommet, Double>();
		distance.put(depart, 0.0);
		TreeSet<Sommet> file = new TreeSet<Sommet>(); // file FIFO
		file.add(depart);
		sommetsRencontres.add(depart);
		
		// boucle principale
		boolean enCours = true;
		while (enCours) {
			Sommet courant = extraireMin(file);
			for (Arete ar : graphe.getAretesIncidentes(courant)) {
				Sommet voisin;
				if(ar.s1.equals(courant))
					voisin = ar.s2;
				else
					voisin = ar.s1;
				if(!sommetsRencontres.contains(voisin) || file.contains(voisin)) {
				if (distance.get(voisin)==null || distance.get(courant) + ar.longueur < distance.get(voisin)) {
					if(!sommetsRencontres.contains(voisin)){
							sommetsRencontres.add(voisin);
							file.add(voisin);
					}
					predecesseurs.put(voisin, courant);
					distance.put(voisin, distance.get(courant) + ar.longueur);

					if (destination != null) {
						enCours = enCours && !voisin.equals(destination);
					}
				}

			}
			}
			enCours = enCours && !file.isEmpty();
		}

	}

	@Override
	public LinkedList<Sommet> plusCourtChemin(Sommet dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getDistance(Sommet s) {
		return distance.get(s);
	}
	
	private double heuristique(Sommet s) {
		int coor_dest2[] = getCoordonneesSommet(destination);
		int coor_s[] = getCoordonneesSommet(s);
		
		return Math.sqrt(Math.pow(coor_dest2[0]-coor_s[0], 2) + Math.pow(coor_dest2[1]-coor_s[1], 2)); 
	}
	
	private double distanceTotale(Sommet s){
		return distance.get(s)+ heuristique(s);
	}
	
	private Sommet extraireMin(TreeSet<Sommet> liste) {
		double minval = 10000000000.0;
		Sommet min=null;
		for(Sommet s: liste){
			if (distanceTotale(s)<minval) {
				minval = distanceTotale(s);
				min = s;
			}
		}
		liste.remove(min);
		return min;
		
		
		
	}
	
	int[] getCoordonneesSommet(Sommet s) {
		int[] coor = new int[2];
		String[] split = s.nom.split(" ");
		coor[0] = Integer.parseInt(split[0]);
		coor[1] = Integer.parseInt(split[1]);
		return coor;
	}
}
