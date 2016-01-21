
public class GameStateManager {

	Graphiques graphiques;
	
	public GameStateManager(Graphiques graphiques) {
		this.graphiques = graphiques;
	}

	public void mainloop() {
		boolean enCours = true;
		long framePrecedente ;
		long frameCourante = System.nanoTime();
		
		while(enCours){
			framePrecedente = frameCourante ;
			frameCourante = System.nanoTime();
			graphiques.update(frameCourante - framePrecedente);
			
			
		}
	}
}
