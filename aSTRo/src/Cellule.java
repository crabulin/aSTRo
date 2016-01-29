public class Cellule {

	static final CellType HERBE = new CellType(true);
	static final CellType MUR = new CellType(false);
	int x, y;
	String nom;
	CellType type;


	public Cellule(int x, int y, String typeCode) {
		this.x = x;
		this.y = y;
		this.nom= x + " " + y;
		switch (typeCode) {
		case "h":
			this.type = HERBE;
			break;
		case "m":
			this.type = MUR;
			break;
		}

	}

	
}
