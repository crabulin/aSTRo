
public class Modele {
	
	Map mapp;
	
	
	
	
	
	public Modele()
	{
		mapp = new MapRectGrid();
		
	}





	public Cellule[] cellulesADessiner() {
		System.out.println("hhhha");
		return mapp.cellulesADessiner();
		
		
	}

}
