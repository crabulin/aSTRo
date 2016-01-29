import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MapRectGrid extends Map {

	final int largeur = 25; // nb cases en largeur
	final int hauteur = 20; // nb cases en hauteur

	Cellule cells[][] = new Cellule[largeur][hauteur];
	Pathfinder pf;

	public Cellule getCells(int x, int y) {
		return cells[x][y];
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
		cells[10][10] = new Cellule(10, 10, "m");
		cells[11][10] = new Cellule(11, 10, "m");
		cells[12][10] = new Cellule(12, 10, "m");
		
		
		
		pf = new Pathfinder(new Graphe(this));
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
			int vy = cell.x + directions[k][0];
			int vx = cell.y + directions[k][1];
			if (0 <= vx && vx < largeur && 0 <= vy && vy < hauteur) {
				if (cells[vx][vy].type.nonBloquant) {
					voisines.add(cells[vx][vy]);
				}
			}

		}

		return voisines;

	}
	
	public LinkedList<DeplacementElementaire> routePlusCourte(Entite acteur, Cellule c1, Cellule c2){
		LinkedList<DeplacementElementaire> deplacements = new LinkedList<DeplacementElementaire>();
		
		LinkedList<Sommet> chemin = pf.cheminLargeur(pf.g.sommets.get(c1.nom), pf.g.sommets.get(c2.nom));
		Iterator<Sommet> it = chemin.iterator();
		Cellule precedente = null;
		
		Cellule courante = it.next().cellule;
		while (it.hasNext()){
			precedente = courante;
			courante=it.next().cellule;
			deplacements.add(new DeplacementElementaire(acteur, courante.x-precedente.x, courante.y-precedente.y));
		}
		
		return deplacements;
	}
}
