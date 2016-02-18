package aSTRo.modele.graphe;
import java.util.HashMap;


/**Classe pour calculer et compter les composantes connexes d'un graphe
 * @author david
 *
 */
public class CompConnexes {
	
	Graphe graphe;
	HashMap<Sommet, Integer> composantes;
	int nbComposantes;

	public int getNbComposantes() {
		return nbComposantes;
	}

	public CompConnexes(Graphe graphe) {
		super();
		this.graphe = graphe;
		this.calculer();
	}

	private void calculer() {
		composantes = new HashMap<Sommet, Integer>();
		Integer ncomp = 0;
		for(Sommet dep: graphe.getListeSommet())
			if (composantes.get(dep)==null){
				ncomp++;
				ParcoursEnLargeur pl = new ParcoursEnLargeur(graphe, dep);
				pl.parcourir();
				for(Sommet s : pl.sommetsRencontres) {
					composantes.put(s, ncomp);
				}
				
			}
		nbComposantes = ncomp;
		
	}
	
	
	
	

}
