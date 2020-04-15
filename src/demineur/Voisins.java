
package demineur;

import java.awt.Point;
import java.util.*;

public class Voisins {
    private List<Point> voisinsDiagonales;
    private List<Point> voisinsAdjacents;

    public Voisins(int i, int j, int nbLignes) {
        Point haut = new Point(i - 1, j);
        Point haut_droite = new Point(i - 1, j + 1);
        Point droite = new Point(i, j + 1);
        Point bas_droite = new Point(i + 1, j + 1);
        Point bas = new Point(i + 1, j);
        Point bas_gauche = new Point(i + 1, j - 1);
        Point gauche = new Point(i, j - 1);
        Point haut_gauche = new Point(i - 1, j - 1);
        
        Point[] voisinsDiag = {haut, haut_droite, droite, bas_droite, bas, bas_gauche, gauche, haut_gauche};
        Point[] voisinsAdj = {haut, droite, bas, gauche};
        
        List<Point> listVoisinsDiag = new ArrayList<>(Arrays.asList(voisinsDiag));
        List<Point> listVoisinsAdj = new ArrayList<>(Arrays.asList(voisinsAdj));       
        
        if (i == 0) {
            listVoisinsDiag.remove(haut);
            listVoisinsDiag.remove(haut_droite);
            listVoisinsDiag.remove(haut_gauche);
            listVoisinsAdj.remove(haut);
        }
        else if (i == (nbLignes - 1)) {
            listVoisinsDiag.remove(bas);
            listVoisinsDiag.remove(bas_droite);
            listVoisinsDiag.remove(bas_gauche);
            listVoisinsAdj.remove(bas);
        }
        
        if (j == 0) {
            listVoisinsDiag.remove(gauche);
            listVoisinsDiag.remove(haut_gauche);
            listVoisinsDiag.remove(bas_gauche);
            listVoisinsAdj.remove(gauche);
        }
        else if (j == (nbLignes - 1)) {
            listVoisinsDiag.remove(droite);
            listVoisinsDiag.remove(haut_droite);
            listVoisinsDiag.remove(bas_droite);
            listVoisinsAdj.remove(droite);
        }
        
        this.voisinsAdjacents = listVoisinsAdj;
        this.voisinsDiagonales = listVoisinsDiag;
    }

    
    
    public List<Point> getVoisinsDiagonales() {
        return voisinsDiagonales;
    }

    public List<Point> getVoisinsAdjacents() {
        return voisinsAdjacents;
    }

    public void setVoisinsDiagonales(List<Point> voisinsDiagonales) {
        this.voisinsDiagonales = voisinsDiagonales;
    }

    public void setVoisinsAdjacents(List<Point> voisinsAdjacents) {
        this.voisinsAdjacents = voisinsAdjacents;
    }
    
    
}
