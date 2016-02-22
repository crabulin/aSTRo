package aSTRo.vue;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import aSTRo.modele.Cellule;
import aSTRo.modele.DeplacementElementaire;
import aSTRo.modele.Entite;
import aSTRo.modele.Modele;
import aSTRo.modele.Selectionnable;


public class InputGestionnaire implements KeyListener, FocusListener,
		MouseListener, MouseMotionListener {

	//Selectionnable selection = null;
	Graphiques graphiq;
	Modele mod;

	public InputGestionnaire(Graphiques graphiq) {
		super();
		this.graphiq = graphiq;
		mod = graphiq.getModele();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		//on détermine la cellule dans laquelle le clic a eu lieu
		Cellule cel = graphiq.getCelluleDuClick(arg0.getX(), arg0.getY());
		//l'entite selectionnee va se diriger cers cette cellule
		Entite ent = mod.eg.getSelection();
		if (ent!=null && cel.type.nonBloquant) {
			ent.viderActionsEnAttente();
			mod.mapp.supprimerObjetsStatiques();
			
			LinkedList<DeplacementElementaire> route = mod.mapp.routePlusCourte(ent,mod.mapp.getCellule(ent.x,ent.y), cel);
			for(DeplacementElementaire de : route){
				ent.ajouterAction(de);
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
