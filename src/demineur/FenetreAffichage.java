package demineur;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreAffichage extends JFrame {

    private static JPanel panneauFond;
    private GridLayout grille;
    private BoutonCase[][] casesPlateau;
    private int nbLignes;
    private Dimension tailleCase;

    public FenetreAffichage(boolean[][] casesMinees, int[][] nbBombesVoisines) {
        super("Démineur");
        tailleCase = new Dimension(50, 50);
        nbLignes = casesMinees.length;
        panneauFond = new JPanel();
        GridLayout grille = new GridLayout(nbLignes, nbLignes);
        for (int i = 0; i < casesMinees.length; i++) {
            for (int j = 0; j < casesMinees.length; j++) {
                BoutonCase boutonCase = new BoutonCase(i, j,
                        casesMinees, nbBombesVoisines, this);
                int tailleCar = boutonCase.getFontMetrics(boutonCase.getFont())
                        .stringWidth("M");
                boutonCase.setPreferredSize(new Dimension(tailleCar * 5, tailleCar * 5));
                panneauFond.add(boutonCase);
            }
        }
        panneauFond.setLayout(grille);

        this.add(panneauFond);

        pack();
        setVisible(true);
    }

    public void afficheMessagePerdu() {
        int selectedOption = JOptionPane.showConfirmDialog(null,
                "Voulez vous recommencer ?",
                "Vous avez perdu !",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (selectedOption == JOptionPane.YES_OPTION) {
            this.dispose();
            Demineur.start();
        }
        else {
            System.exit(0);
        }
    }
}
