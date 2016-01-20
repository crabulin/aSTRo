
public class Modele {
	
	Map mapp;
	
	
	
	
	
	public Modele()
	{
		mapp = new MapRectGrid();
		mapp.init();
		
	}





	public Cellule[] cellulesADessiner() {
		return mapp.cellulesADessiner();
		
		
	}

}
