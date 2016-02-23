package aSTRo.modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import aSTRo.modele.graphe.Graphe;
import aSTRo.modele.graphe.Parcours;
import aSTRo.modele.graphe.ParcoursEnLargeur;
import aSTRo.modele.graphe.Sommet;



public class MapRectGrid extends Map {

	public final int largeur = 25; // nb cases en largeur
	public final int hauteur = 20; // nb cases en hauteur

	Cellule cells[][] = new Cellule[largeur][hauteur];
	Pathfinder pf;

	public Cellule getCell(int x, int y) {
		return cells[x][y];
	}
	
	public Cellule getCell(int[] coord) {
		return cells[coord[0]][coord[1]];
	}
	
	public Cellule getCell(Sommet v) {
		return getCell(pf.getCoordonneesSommet(v));
	}



	@Override
	public void update(long dt) {
		System.out.println("update");

	}

	@Override
	public void init() {
		
		for (int x = 0; x < largeur; x++) {
			for (int y = 0; y < hauteur; y++) {
				// System.out.println(x + " " + y);
				cells[x][y] = new Cellule(x, y, "h");
			}
		}

		
		for(int i=0;i<10 ; i++)
			cells[i+5][8] = new Cellule(i+5, 8, "m");
		
			for(int i=0;i<10 ; i++)
			cells[i+5][12] = new Cellule(i+5, 12, "m");
				
		for(int i=0;i<3 ; i++)
			cells[14][i+9] = new Cellule(14, i+9, "m");
		
		pf = new Pathfinder(this);
	}

	@Override
	public Cellule[] cellulesADessiner() {
		Cellule resultat[] = new Cellule[hauteur * largeur];
		for (int x = 0; x < largeur; x++) {
			for (int y = 0; y < hauteur; y++) {
				resultat[x * hauteur + y] = cells[x][y];
			}
		}

		return resultat;
	}

	public Cellule[][] listeToutesCellules() {
		return cells;
	}

	public ArrayList<Cellule> cellulesVoisines(Cellule cell) {

		ArrayList<Cellule> voisines = new ArrayList<Cellule>();

		int[][] directions = { { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 },
				{ -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 } };

		for (int k = 0; k < 8; k++) {
			int vx = cell.x + directions[k][0];
			int vy = cell.y + directions[k][1];
			if (0 <= vx && vx < largeur && 0 <= vy && vy < hauteur) {
				if (cells[vx][vy].getType().nonBloquant) {
					voisines.add(cells[vx][vy]);
				}
			}

		}
		return voisines;

	}

	@Override
	public LinkedList<DeplacementElementaire> plusCourtDeplacements(Entite acteur, Cellule c1, Cellule c2){
		return pf.plusCourtDeplacements(acteur, c1, c2);
		}

	@Override
	public Cellule getCellule(int x, int y) {
		if (0<=x && x<largeur && 0<=y && y<hauteur)
			return cells[x][y];
		else
			return null;
	}

	@Override
	public void supprimerObjetsStatiques() {
		for (int x = 0; x < largeur; x++) {
			for (int y = 0; y < hauteur; y++) {
				cells[x][y].setObjetStatique(null);
			}
		}

	
	}

	@Override
	public double getDistance(int x1, int y1, int x2, int y2) {
		double result = (cells[x1][y1].type.coutMouvement+ cells[x2][y2].type.coutMouvement)/2;
		if(x1!=x2 && y1!=y2)
			result *=1.41;
		return result;
		
	}
}
