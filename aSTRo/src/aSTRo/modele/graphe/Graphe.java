package aSTRo.modele.graphe;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;



/**
 * Graphe est une classe permettant de gérer les graphes orientés et non orientés
 * @author david
 */
public class Graphe {
	/**
	 *L'ensemble des sommets, indexés par leur nom (String) 
	 */
	private HashMap <String, Sommet> sommets;
	
	/**
	 * Liste des Aretes, sans structure particuliere
	 */
	private ArrayList<Arete> listeAretes;
	
	/**
	 * Liste d'adjacence : listes des arètes incidentes à chaque sommet
	 * seulement les arètes sortantes dans le cas orienté
	 */
	private HashMap<Sommet,ArrayList<Arete>> listeAdj;
	
	/**
	 * Booléen indiquant si la liste d'adjacence listeAdj est à jour
	 * par rapport à la liste d'aretes listeAretes
	 */
	private boolean listeAdjAJour = false; 
	
	private Parcours parcours;
	
	public void setParcours(Parcours parcours) {
		this.parcours = parcours;
	}
	
	public void lancerParcours() {
		parcours.parcourir();
	}
	
	public double getDistance(Sommet s){
		return parcours.getDistance(s);
	}
	
	public Sommet getPredecesseur(Sommet s){
		return parcours.getPredecesseur(s);
	}
	
	

	/**
	 * Construit un graphe vide
	 * La liste d'adjacence n'est pas calculée 
	 */
	public Graphe(){
		sommets = new HashMap<>();
		listeAretes = new ArrayList<Arete>();
		listeAdjAJour = false;
	}
	
	/** Construit un graphe d'apres le fichier txt contenant ordre et liste
	 * des aretes
	 * @param nomfichier
	 */
	public Graphe(String nomfichier){
		this();
		
		//ouverture du fichier
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new FileReader(nomfichier));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//lecture et analyse des lignes
		String ligne = null;
		try {
			while((ligne = bf.readLine()) !=null) {
				String [] mots = ligne.split(" ");
				
				//ajout des sommets de 0 à (ordre-1)
				if(mots[0].equals("ordre")){
					int ordre = Integer.parseInt(mots[1]);
					for(int i=0;i<ordre;i++){
						ajouterSommet(new Sommet(""+i));
					}
				}
				
				//ajout d'une arete
				if(mots[0].equals("E")) {
					if (mots.length==3)
					ajouterArete(getSommetParNom(mots[1]), getSommetParNom(mots[2]));
					if (mots.length==4)
						ajouterArete(getSommetParNom(mots[1]), getSommetParNom(mots[2]), Double.parseDouble(mots[3]));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**Ajoute le nouveau sommet à l'ensemble des sommets
	 * @param s
	 */
	public void ajouterSommet(Sommet s){
		sommets.put(s.nom, s);
		listeAdjAJour=false;
	}
	
	public void ajouterSommet(String nom) {
		ajouterSommet(new Sommet(nom));
	}

	
	/**Renvoie le sommet dont le nom (String) est donné
	 * @param nom
	 * @return Sommet
	 */
	public Sommet getSommetParNom(String nom) {
		Sommet s = sommets.get(nom);
		if (s==null) {
			try {
				throw new NullPointerException("pas de sommet de ce nom");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return s;
	}

	/**Renvoie la liste des voisins du sommet s
	 * @param s
	 * @return une ArrayList de Sommets
	 */
	public ArrayList<Sommet> getVoisins(Sommet s){
		//si la liste d'adjacence n'est pas à jour, il faut la recalculer
		if (!listeAdjAJour)
			initListeAdj();
		
		//on va chercher les sommets qui sont sur les aretes
		//incidentes à s
		ArrayList<Arete> aretes = listeAdj.get(s);  	//aretes incidentes a s
		ArrayList<Sommet> voisins = new ArrayList<>();  //liste de voisins
		for(Arete ar : aretes) {
			if (ar.s1!=s)
				voisins.add(ar.s1);
			if (ar.s2!=s)
				voisins.add(ar.s2);
		}
		return voisins;
	}
	
	
	/**
	 * Met a jour la liste d'adjacence 
	 */
	private void initListeAdj() {
		listeAdj = new HashMap<Sommet, ArrayList<Arete>>() ;
		for(Sommet s: sommets.values())
			listeAdj.put(s, new ArrayList<Arete>());
		for(Arete ar : listeAretes) {
			listeAdj.get(ar.s1).add(ar);
			if (!ar.orientee)
				listeAdj.get(ar.s2).add(ar);
		}
		
		
		
	}

	
	/**ajoute une arete non orientee entre les deux sommets
	 * @param s1
	 * @param s2
	 */
	public void ajouterArete(Sommet s1, Sommet s2) {
		listeAretes.add(new Arete(s1, s2, false));
		listeAdjAJour=false;
	}
	
	public void ajouterArete(Sommet s1, Sommet s2, Double l) {
		listeAretes.add(new Arete(s1, s2, l, false));
		listeAdjAJour=false;
	}
	
	@Override
	public String toString() {
		String s = "ordre : " + sommets.size()+"\n"
				+ "taille : " + listeAretes.size() +"\n";
		for(Arete ar : listeAretes){
			s += ar  + "\n";
		}
		return s;
	}

	/**indique s'il existe un sommet de ce nom
	 * @param nom
	 * @return
	 */
	public boolean contientSommetDeNom(String nom) {
		return sommets.get(nom)!=null;
	}

	public ArrayList<Sommet> getListeSommet() {
		return new ArrayList<Sommet>(sommets.values());
	}
	
	public int getOrdre() {
		return sommets.size();
	}

	public ArrayList<Arete> getListeAretes() {
		return listeAretes;
	}

}

