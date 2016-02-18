package aSTRo.modele.graphe;


public class Main {

	/**
	 * @param args
	 */
	public static void PASmain(String[] args) {
		Graphe g = new Graphe("src/graphes/metro.txt");
		System.out.println(g);
		BellmanFord bf = new BellmanFord(g, g.getSommetParNom("18"));
		bf.parcourir();
		System.out.println(bf.plusCourtChemin(g.getSommetParNom("27")));

	}

}
