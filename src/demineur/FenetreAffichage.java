package demineur;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreAffichage extends JFrame {
    
    private JPanel panneauFond;
    private GridLayout grille;
    private BoutonCase[][] casesPlateau;
    private int nbLignes;
    private Dimension tailleCase;
    
    public FenetreAffichage(boolean[][] casesMinees, int[][] nbBombesVoisines) {
        super("Démineur");
        tailleCase = new Dimension (50, 50);
        nbLignes = casesMinees.length;
        JPanel panneauFond = new JPanel();
        GridLayout grille = new GridLayout(nbLignes, nbLignes);
        for (int i=0; i<casesMinees.length; i++)
        {
            for (int j=0; j<casesMinees.length; j++)
            {
                BoutonCase boutonCase = new BoutonCase(i, j,
                                        casesMinees, nbBombesVoisines);
                int tailleCar = boutonCase.getFontMetrics(boutonCase.getFont())
                                .stringWidth("M");
                boutonCase.setPreferredSize(new Dimension (tailleCar*5, tailleCar*5));
                panneauFond.add(boutonCase);
            }
        }  
        panneauFond.setLayout(grille);
        
        add(panneauFond);
        
       pack();
       setVisible(true);
    }
}
