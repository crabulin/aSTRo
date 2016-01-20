
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class GraphiquesAWT extends Graphiques {

	JFrame fenetre ;
	Modele modele ;
	JPanel controlPanel;
	Panneau pan;
	
	public GraphiquesAWT(Modele modele){
		this.modele = modele ;
	}
	@Override
	void init() {
		// TODO Auto-generated method stub
		fenetre = new JFrame();
		fenetre.setTitle("aSTRo");
		//fenetre.setSize(800,600);
		//fenetre.setLocationRelativeTo(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		fenetre.setLocation(dim.width/2-fenetre.getSize().width/2, dim.height/2-fenetre.getSize().height/2);
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		controlPanel = new JPanel(new BorderLayout() );
		
		
		pan = new Panneau(this);
		controlPanel.add(pan, BorderLayout.CENTER);
		fenetre.setContentPane(controlPanel);
		
		fenetre.pack();
		pan.setVisible(true);
		
		fenetre.setVisible(true);
		

	

	}

	@Override
	void update(long dt) {
		pan.render();
	}
	@Override
	Modele getModele() {
		return modele;	
	}
	
	

}
