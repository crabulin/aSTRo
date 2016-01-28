
public class DeplacementElementaire extends Action {

	int xDepart ;
	int yDepart ;
	int xDirection ;
	int yDirection ;
	
	public DeplacementElementaire(int xDepart, int yDepart, int xDirection,
			int yDirection) {
		
		super();
		nom = "depElem";
		this.xDepart = xDepart;
		this.yDepart = yDepart;
		this.xDirection = xDirection;
		this.yDirection = yDirection;
	}

}
