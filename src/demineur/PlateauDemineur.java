package demineur;

import java.awt.Point;
import java.util.ArrayList;
import static java.util.Collections.shuffle;
import java.util.List;

public class PlateauDemineur {

    private int nbLignes, nbMines;

    private boolean[][] casesMinees;
    private int[][] nbBombesVoisines;
    private ArrayList<ArrayList<Point>> groupeCasesVidesVoisines;

    private FenetreAffichage fenetre;

    public PlateauDemineur(int nbLignes, int nbMines) {
        this.nbLignes = nbLignes;
        this.nbMines = nbMines;
        boolean[][] casesMinee = new boolean[nbLignes][nbLignes];
        ArrayList<Boolean> tab = new ArrayList<>();
        for (int i = 0; i < nbLignes * nbLignes; i++) {
            if (i < nbMines) {
                tab.add(Boolean.TRUE);
            } else {
                tab.add(Boolean.FALSE);
            }
        }
        shuffle(tab);
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbLignes; j++) {
                casesMinee[i][j] = (boolean) tab.get(i * nbLignes + j);
            }
        }
        this.casesMinees = casesMinee;
        this.nbBombesVoisines = createPlateauNbBombesVoisines();
        this.groupeCasesVidesVoisines = createGroupeCasesVidesVoisines();
        
        System.out.println("Position des mines : \n");
        for (boolean[] lignePlateau : this.casesMinees) {
            for (boolean minePresente : lignePlateau) {
                System.out.print(minePresente + " ");
            }
            System.out.println();
        }
        System.out.println("\n Nombres de mines voisines  : \n");
        for (int[] lignePlateau : this.nbBombesVoisines) {
            for (int nbMinesVoisines : lignePlateau) {
                System.out.print(nbMinesVoisines + " ");
            }
            System.out.println();
        }

        fenetre = new FenetreAffichage(this.casesMinees, this.nbMines,
                this.nbBombesVoisines, this.nbLignes, this.groupeCasesVidesVoisines);
    }

    public int[][] createPlateauNbBombesVoisines() {
        int[][] tabNbBombesVoisines = new int[this.getNbLignes()][this.getNbLignes()];
        Voisins voisins;
        List<Point> voisinsDiag;
        for (int i = 0; i < this.getNbLignes(); i++) {
            for (int j = 0; j < this.getNbLignes(); j++) {
                voisins = new Voisins(i, j, this.nbLignes);
                voisinsDiag = voisins.getVoisinsDiagonales();
                for (Point p : voisinsDiag) {
                    if (this.getCasesMinees()[p.x][p.y]) {
                        tabNbBombesVoisines[i][j]++;
                    }
                }
            }
        }
        return tabNbBombesVoisines;
    }

    private ArrayList<ArrayList<Point>> createGroupeCasesVidesVoisines() {
        boolean[][] casesVidesVisitees = new boolean[this.nbLignes][];
        for (int i = 0; i < this.nbLignes; i++) {
            casesVidesVisitees[i] = this.casesMinees[i].clone();
        }
        int nbCasesVidesNonVisitees = this.nbLignes * this.nbLignes - this.nbMines;
        ArrayList<ArrayList<Point>> tabGroupeCasesVidesVoisines = new ArrayList<>();
        ArrayList<Point> fileCasesBombes = new ArrayList<>();
        while (nbCasesVidesNonVisitees != 0) {
            ArrayList<Point> groupeBombesConnexe = new ArrayList<>();
            int indDepart = 0;
            while (casesVidesVisitees[indDepart / nbLignes][indDepart % nbLignes]) {
                indDepart = indDepart + 1;
            }
            Point pointDepart = new Point(indDepart / nbLignes, indDepart % nbLignes);
            casesVidesVisitees[pointDepart.x][pointDepart.y] = true;
            nbCasesVidesNonVisitees--;
            groupeBombesConnexe.add(pointDepart);
            fileCasesBombes.add(pointDepart);
            while (!fileCasesBombes.isEmpty()) {
                Point caseActuelle = fileCasesBombes.get(0);
                Voisins voisinsCaseActuelle = new Voisins(caseActuelle.x, caseActuelle.y, this.nbLignes);
                for (Point p : voisinsCaseActuelle.getVoisinsAdjacents()) {
                    if (!casesVidesVisitees[p.x][p.y]) {
                        fileCasesBombes.add(p);
                        casesVidesVisitees[p.x][p.y] = true;
                        groupeBombesConnexe.add(p);
                        nbCasesVidesNonVisitees--;
                    }
                }
                fileCasesBombes.remove(caseActuelle);
            }
            tabGroupeCasesVidesVoisines.add(groupeBombesConnexe);
        }
        
        System.out.println("Coordonnées des composantes connexes des cases vides : \n");
        for (ArrayList<Point> groupeBombes : tabGroupeCasesVidesVoisines) {
            for (Point p : groupeBombes) {
                System.out.print("(" + p.x + "," + p.y + ") ");
            }
            System.out.println();
        };
        System.out.println();

        return tabGroupeCasesVidesVoisines;
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
}
