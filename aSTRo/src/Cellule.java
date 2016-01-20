public class Cellule {

	static final CellType HERBE = new CellType();
	static final CellType ZELDA = new CellType();
	int x, y;
	CellType type;

	public Cellule(int x, int y, String typeCode) {
		this.x = x;
		this.y = y;
		switch (typeCode) {
		case "h":
			this.type = HERBE;
			break;
		case "z":
			this.type = ZELDA;
			break;
		}

	}

	
}
