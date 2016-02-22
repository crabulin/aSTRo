package aSTRo.modele.graphe;
import java.util.HashMap;
import java.util.LinkedList;


public class BellmanFord extends Parcours {

	HashMap<Sommet,Double> distance;
	
	public BellmanFord(Graphe graphe, Sommet depart) {
		super(graphe, depart);
	}

	@Override
	public void parcourir() {
		predecesseurs = new HashMap<Sommet, Sommet>();
		distance = new HashMap<Sommet, Double>();
		distance.put(depart, 0.0);
		for(int i = 0;i<graphe.getOrdre();i++){
			for (Arete ar : graphe.getListeAretes()) {
				relacher(ar);
			}
		}
		parcouru=true;
	}

	private void relacher(Arete ar) {
		if (distance.containsKey(ar.s1) && 
				( !distance.containsKey(ar.s2) || 
						(distance.get(ar.s1) + ar.longueur < distance.get(ar.s2) ))) {
			distance.put(ar.s2, distance.get(ar.s1) + ar.longueur);
			predecesseurs.put(ar.s2, ar.s1);
		}
		
		//on relache l'arete inverse si non oriente
		if(! ar.orientee) {
			if (distance.containsKey(ar.s2) && 
					( !distance.containsKey(ar.s1) || 
							(distance.get(ar.s2) + ar.longueur < distance.get(ar.s1) ))) {
				distance.put(ar.s1, distance.get(ar.s2) + ar.longueur); 
			predecesseurs.put(ar.s1, ar.s2);
			}
			
		}
		
	}

	@Override
	public LinkedList<Sommet> plusCourtChemin(Sommet dest) {
		//cas du parcours non effectué
		if(!parcouru){
			throw new NullPointerException("Parcours pas encore effectué");
		}
		
		// cas du sommet non accessible
		if (predecesseurs.get(dest)==null){
			return null; 
		}
		
		//cas général
		LinkedList<Sommet> chemin = new LinkedList<>();
		Sommet courant = dest;
		
		while(courant!=depart){
			chemin.addFirst(courant);
			courant = getPredecesseur(courant);
		}
		chemin.addFirst(depart);
	
		
		return chemin;
	}

	@Override
	public double getDistance(Sommet s) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
}
