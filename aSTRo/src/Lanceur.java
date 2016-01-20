
public class Lanceur {

	/**
	 * @param args
	 */
	
	Graphiques graphiques ;
	Modele modele;
	
	public Lanceur()
	{
		System.out.println("debut");
		modele = new Modele();
		graphiques = new GraphiquesAWT(modele);
		graphiques.init();
		graphiques.update(1L);

	}
	
	public static void main(String[] args) {

		Lanceur lanceur = new Lanceur();
		
	}

}
