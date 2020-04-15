package demineur;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class FenetreAffichage extends JFrame {

    private static JPanel panneauFond;
    private GridLayout grille;

    private static int nbLignes, nbMines;
    private BoutonCase[][] grilleBoutons;
    private int[][] nbBombesVoisines;
    private ArrayList<ArrayList<Point>> groupeCasesVides;
    private Dimension tailleCase;

    public FenetreAffichage(boolean[][] casesMinees, int nbMines,
            int[][] nbBombesVoisines, int nbLignes, ArrayList<ArrayList<Point>> groupeCasesVides) {
        super("Démineur");
        tailleCase = new Dimension(50, 50);
        FenetreAffichage.nbLignes = nbLignes;
        FenetreAffichage.nbMines = nbMines;
        this.groupeCasesVides = groupeCasesVides;
        this.nbBombesVoisines = nbBombesVoisines;
        panneauFond = new JPanel();
        grille = new GridLayout(nbLignes, nbLignes);
        grilleBoutons = new BoutonCase[nbLignes][nbLignes];
        for (int i = 0; i < casesMinees.length; i++) {
            for (int j = 0; j < casesMinees.length; j++) {
                BoutonCase boutonCase = new BoutonCase(i, j,
                        casesMinees, nbBombesVoisines, this);
                int tailleCar = boutonCase.getFontMetrics(boutonCase.getFont())
                        .stringWidth("M");
                boutonCase.setPreferredSize(new Dimension(tailleCar * 5, tailleCar * 5));
                panneauFond.add(boutonCase);
                grilleBoutons[i][j] = boutonCase;
            }
        }
        panneauFond.setLayout(grille);

        this.add(panneauFond);

        pack();
        setVisible(true);
    }

    public void afficheMessagePerdu() {
        int selectedOptionPerdu = JOptionPane.showConfirmDialog(null,
                "Voulez vous recommencer ?",
                "Vous avez perdu !",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (selectedOptionPerdu == JOptionPane.YES_OPTION) {
            this.dispose();
            Demineur demineur = new Demineur();
            demineur.start();
        } else {
            System.exit(0);
        }
    }

    public void afficheMessageGagne() {
        int selectedOptionGagne = JOptionPane.showConfirmDialog(null,
                "Voulez vous recommencer ?",
                "Vous avez gagné !",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (selectedOptionGagne == JOptionPane.YES_OPTION) {
            this.dispose();
            Demineur demineur = new Demineur();
            demineur.start();
        } else {
            System.exit(0);
        }
    }

    public void afficheGroupeCasesVides(int i, int j) {
        Point caseVideCliquee = new Point(i, j);
        int n = 0;
        ArrayList<Point> groupeCasesVidesConnexes = this.groupeCasesVides.get(n);
        while (!groupeCasesVidesConnexes.contains(caseVideCliquee)) {
            n = n + 1;
            groupeCasesVidesConnexes = this.groupeCasesVides.get(n);
        }
        for (Point p : groupeCasesVidesConnexes) {
            int lin = p.x;
            int col = p.y;
            BoutonCase caseAAfficher = grilleBoutons[lin][col];
            caseAAfficher.setText("" + this.nbBombesVoisines[lin][col]);
            validate();
            revalidate();
        }
    }

    public static int getNbLignes() {
        return nbLignes;
    }

    public static int getNbMines() {
        return nbMines;
    }

    public  ArrayList<ArrayList<Point>> getGroupeCasesVides() {
        return this.groupeCasesVides;
    }

    public static void setNbLignes(int pNbLignes) {
        FenetreAffichage.nbLignes = pNbLignes;
    }

    public static void setNbMines(int pNbMines) {
        FenetreAffichage.nbMines = pNbMines;
    }

    public void setGroupeMines(ArrayList<ArrayList<Point>> pGroupeMines) {
        this.groupeCasesVides = pGroupeMines;
    }
}
