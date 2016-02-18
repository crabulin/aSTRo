package aSTRo.modele.graphe;
import java.util.HashMap;
import java.util.LinkedList;


/**Classe pour stocker les informations d'un parcours en largeur
 * calcule les distances
 * @author david
 *
 */
public class ParcoursEnLargeur extends Parcours {

	/**
	 * distance depuis le sommet de depart 
	 */
	HashMap<Sommet,Integer> distance;
	
	
	public ParcoursEnLargeur(Graphe graphe, Sommet depart) {
		super(graphe, depart);
	}


	
	
	
	
	@Override
	public void parcourir() {
		parcouru = true;
		//initialisation
		predecesseurs = new HashMap<Sommet, Sommet>();
		distance = new HashMap<Sommet, Integer>();
		distance.put(depart, 0);
		LinkedList<Sommet> file =  new LinkedList<Sommet>(); //file FIFO
		file.addLast(depart);
		sommetsRencontres.add(depart);
		
		//boucle principale
		while(!file.isEmpty()) {
			Sommet courant = file.removeFirst();
			for (Sommet voisin : graphe.getVoisins(courant)) {
				if (voisin !=depart && !connu(voisin))
				{
					sommetsRencontres.add(voisin);
					file.addLast(voisin);
					predecesseurs.put(voisin, courant);
					distance.put(voisin, distance.get(courant)+1);
				}
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
		return (double) distance.get(s);
	}

}
