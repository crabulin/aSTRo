
public class MapRectGrid extends Map {

	private static final int largeur = 50 ; //nb cases en largeur
	private static final int hauteur = 30 ; //nb cases en hauteur

	Cellule cells[][] = new Cellule[largeur][hauteur];

	
	
	@Override
	public void update(long dt) {
		System.out.println("zob");

	}



	@Override
	public void init() {
		for(int i=0;i<hauteur;i++){
			for (int j=0;j<largeur;j++){
				cells[i][j]= new Cellule(i,j);
			}
		}
	}

	@Override
	public Cellule[] cellulesADessiner(){
		Cellule resultat[] = new Cellule[hauteur*largeur] ;
		for(int i=0;i<hauteur;i++){
			for (int j=0;j<largeur;j++){
				resultat[i*largeur+j] = cells[i][j];
			}
		}

		return resultat;
	}
}

