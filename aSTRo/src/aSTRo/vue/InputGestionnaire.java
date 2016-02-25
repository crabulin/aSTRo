package aSTRo.vue;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.Objects;

import aSTRo.modele.Cellule;
import aSTRo.modele.DeplacementElementaire;
import aSTRo.modele.Entite;
import aSTRo.modele.Modele;

public class InputGestionnaire implements KeyListener, FocusListener,
		MouseListener, MouseMotionListener {

	// Selectionnable selection = null;
	Graphiques graphiq;
	Modele mod;
	Selectionnable selection = null;

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
		// on détermine la cellule dans laquelle le clic a eu lieu
		Cellule cel = graphiq.getCelluleDuClick(arg0.getX(), arg0.getY());

		// quel bouton?
		switch (arg0.getModifiers()) {
		case (InputEvent.BUTTON1_MASK): {
			// on chope l'entite se trouvant dans cette cellule
			Entite ent = mod.eg.getEntiteAuxCoordonnees(cel.x, cel.y);
			if (ent != null) {
				if(selection!=null)
					selection.deselectionner();
				selection = ent;
				ent.selectionner();
			} else if (selection != null) {
				selection.deselectionner();
				selection = null;
			}

			break;
		}

		case (InputEvent.BUTTON3_MASK): {
			if (selection != null && cel.type.nonBloquant) {
	
				mod.mapp.supprimerObjetsStatiques();
				Entite ent = (Entite) selection;
				ent.ordredAction(cel);

			}
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
