
public class GameStateManager {

	Graphiques graphiques;
	Modele modele;
	
	public GameStateManager(Graphiques graphiques, Modele modele) {
		this.graphiques = graphiques;
		this.modele = modele ;
	}

	public void mainloop() {
		boolean enCours = true;
		long framePrecedente ;
		long frameCourante = System.nanoTime();
		
		while(enCours){
			framePrecedente = frameCourante ;
			frameCourante = System.nanoTime();
			modele.update(frameCourante - framePrecedente);
			graphiques.update(frameCourante - framePrecedente);
			
			
		}
	}
}
