package aSTRo.start;
import aSTRo.modele.Modele;
import aSTRo.vue.Graphiques;
import aSTRo.vue.GraphiquesAWT;


public class Lanceur {

	/**
	 * @param args
	 */
	
	Graphiques graphiques ;
	Modele modele;
	GameStateManager gsm ;
	
	public Lanceur()
	{
		System.out.println("debut");
		modele = new Modele();
		
		
		
		graphiques = new GraphiquesAWT(modele);
		graphiques.init();
		
		gsm = new GameStateManager(graphiques, modele);
		gsm.mainloop();

	}
	
	public static void main(String[] args) {

		@SuppressWarnings("unused")
		Lanceur lanceur = new Lanceur();
		
	}

}
