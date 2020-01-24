package demineur;

import java.awt.Point;
import java.util.ArrayList;
import static java.util.Collections.shuffle;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PlateauDemineur 
{
    private int nbLignes, nbMines;
    
    private boolean[][] casesMinees;
    private int[][] nbBombesVoisines;
    
    private BoutonCase[][] casesPlateau;
    
    private FenetreAffichage fenetre;
    
    public PlateauDemineur (int nbLignes, int nbMines)
    {
        this.nbLignes = nbLignes;
        this.nbMines = nbMines;
        boolean[][] casesMinee = new boolean[nbLignes][nbLignes];
        ArrayList<Boolean> tab = new ArrayList<>();
        for (int i=0; i < nbLignes*nbLignes; i++)
        {
            if (i<nbMines)
            {
                tab.add(Boolean.TRUE);
            }
            else
            {
                tab.add(Boolean.FALSE);
            }
        }
        shuffle(tab);
        for (int i=0; i<nbLignes; i++)
        {
            for (int j=0; j<nbLignes; j++)
            {
                casesMinee[i][j] = (boolean) tab.get(i*nbLignes+j);
            }
        }
        this.casesMinees = casesMinee;
        this.nbBombesVoisines = createPlateauNbBombesVoisines();
        
        this.casesPlateau = new BoutonCase[nbLignes][nbLignes];
        for (int i=0; i<nbLignes; i++)
        {
            for (int j=0; j<nbLignes; j++)
            {
                (this.casesPlateau)[i][j] =new BoutonCase(i, j,
                        this.casesMinees, this.nbBombesVoisines);;               
            }
        }

        for (boolean[] lignePlateau : this.casesMinees)
        {
            for (boolean minePresente : lignePlateau)
            {
                System.out.print(minePresente + " ");
            }
            System.out.println();
        }
        
        for (int[] lignePlateau : this.nbBombesVoisines)
        {
            for (int nbMinesVoisines : lignePlateau)
            {
                System.out.print(nbMinesVoisines + " ");
            }
            System.out.println();
        }
        
        FenetreAffichage fenetre = new FenetreAffichage(this.casesMinees,
                this.nbBombesVoisines);
    }

    public int[][] createPlateauNbBombesVoisines() 
    {
        int[][] tabNbBombesVoisines = new int[this.getNbLignes()][this.getNbLignes()];
        for (int i=0; i < this.getNbLignes(); i++)
        {
            for (int j=0; j < this.getNbLignes(); j++)
            {
                for (Point p : voisins(i, j))
                {
                    if (this.getCasesMinees()[p.x][p.y])
                    {
                        tabNbBombesVoisines[i][j]++;
                    }
                }
            }
        }
        return tabNbBombesVoisines;
    }

    public Point[] voisins(int i, int j) 
    {
        Point haut = new Point(i-1, j);
        Point haut_droite = new Point(i-1, j+1);
        Point droite = new Point(i, j+1);
        Point bas_droite = new Point(i+1, j+1);
        Point bas = new Point(i+1, j);
        Point bas_gauche = new Point(i+1, j-1);
        Point gauche = new Point(i, j-1);
        Point haut_gauche = new Point(i-1, j-1);
        if (i != 0 && i != this.getNbLignes()-1 && j != 0 && j != this.getNbLignes()-1)
        {
            Point[] voisins = {haut, haut_droite, droite, bas_droite, bas, bas_gauche, gauche, haut_gauche};
            return voisins;
        }
        else if (i == 0)
        {
            if (j == 0)
            {
                Point[] voisins = {droite, bas_droite};
                return voisins;
            }
            else if (j == this.getNbLignes()-1)
            {
                Point[] voisins = {bas, bas_gauche, gauche};
                return voisins;
            }
            else
            {
                Point[] voisins = {droite, bas_droite, bas, bas_gauche, gauche};
                return voisins;
            }
        }
        else if (i == getNbLignes()-1)
        {
            if (j == 0)
            {
                Point[] voisins = {haut, haut_droite, droite};
                return voisins;
            }
            else if (j == this.getNbLignes()-1)
            {
                Point[] voisins = {haut, gauche, haut_gauche};
                return voisins;
            }
            else
            {
                Point[] voisins = {haut, haut_droite, droite, gauche, haut_gauche};
                return voisins;
            }
        }
        else if (j == 0)
        {
            Point[] voisins = {haut, haut_droite, droite, bas_droite, bas};
            return voisins;
        }
        else 
        {
            Point[] voisins = {haut, bas, bas_gauche, gauche, haut_gauche};
            return voisins;
        }
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public int getNbMines() {
        return nbMines;
    }

    public boolean[][] getCasesMinees() {
        return casesMinees;
    }

    public int[][] getNbBombesVoisines() {
        return this.nbBombesVoisines;
    }

    public BoutonCase[][] getBoutons() {
        return this.casesPlateau;
    }

    public void setNbLignes(int nbLignes) {
        this.nbLignes = nbLignes;
    }

    public void setNbMines(int nbMines) {
        this.nbMines = nbMines;
    }

    public void setCasesMinees(boolean[][] casesMinees) {
        this.casesMinees = casesMinees;
    }

    public void setNbBombesVoisines(int[][] nbBombesVoisines) {
        this.nbBombesVoisines = nbBombesVoisines;
    }

    public void setBoutons(BoutonCase[][] boutons) {
        this.casesPlateau = boutons;
    }
}
