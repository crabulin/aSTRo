public class Cellule {

	static final CellType HERBE = new CellType();
	static final CellType MUR = new CellType();
	int x, y;
	CellType type;

	public Cellule(int x, int y, String typeCode) {
		this.x = x;
		this.y = y;
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
