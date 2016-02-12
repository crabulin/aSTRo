package aSTRo.modele;

public class Cellule {

	public static final CellType HERBE = new CellType(true);
	public static final CellType MUR = new CellType(false);
	public static final CellType FD = new CellType(false);
	public int x;
	public int y;
	String nom;
	private CellType type;
	private EntiteStatique objetStatique = null;


	public Cellule(int x, int y, String typeCode) {
		this.x = x;
		this.y = y;
		this.nom= x + " " + y;
		switch (typeCode) {
		case "h":
			this.setType(HERBE);
			break;
		case "m":
			this.setType(MUR);
			break;
		case "fd":
			this.setType(FD);
			break;
		}

	}

	@Override
	public String toString() {
		return "c("+x+","+y+")";
	}

	public CellType getType() {
		return type;
	}

	public void setType(CellType type) {
		this.type = type;
	}

	public EntiteStatique getObjetStatique() {
		return objetStatique;
	}

	public void setObjetStatique(EntiteStatique objetStatique) {
		this.objetStatique = objetStatique;
	}
	
}
