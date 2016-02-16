package aSTRo.modele;


public class Modele {
	
	public Map mapp;
	public Entite_Gestionnaire eg;
	
	
	
	
	
	public Modele()
	{
		mapp = new MapRectGrid();
		mapp.init();
		eg = new Entite_Gestionnaire((MapRectGrid)mapp);
		
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
