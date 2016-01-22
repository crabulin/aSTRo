
public class Modele {
	
	Map mapp;
	Entite_Gestionnaire eg;
	
	
	
	
	
	public Modele()
	{
		mapp = new MapRectGrid();
		mapp.init();
		eg = new Entite_Gestionnaire();
		
	}





	public Cellule[] cellulesADessiner() {
		return mapp.cellulesADessiner();
		
	}
	
	public Entite[] entitesADessiner() {
		return eg.getListeEntite();
	}





	public void update(long dt) {
		eg.update(dt);
		
	}

}
