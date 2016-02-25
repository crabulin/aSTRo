package aSTRo.modele;

import java.util.LinkedList;


public class Zelda extends Entite {

	boolean selectionne = false;
	
	public Zelda(int x, int y, MapRectGrid map) {
		super(x, y, map);
		pourcentage = 0;
	}

	@Override
	public void selectionner() {
		selectionne = true;
		
	}

	@Override
	public void deselectionner() {
		selectionne=false;
	}

	@Override
	public boolean estSelectionne() {
		return selectionne;
	}

	@Override
	public void ordredAction(Cellule cel) {
				viderActionsEnAttente();

				// cellule ou sera l'entite a la fin de son deplacement
				int xr = x;
				int yr = y;
				if (actionEnCours != null && actionEnCours.nom == "DE") {
					xr += ((DeplacementElementaire) actionEnCours).xDirection;
					yr += ((DeplacementElementaire) actionEnCours).yDirection;
				}
				LinkedList<DeplacementElementaire> route = map.plusCourtDeplacements(this, map.getCellule(xr, yr),
								cel);
				for (DeplacementElementaire de : route) {
					ajouterAction(de);
				}	
		
	}

	
		
		
}
