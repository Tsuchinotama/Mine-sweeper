package demineur;

import java.util.Scanner;
import javax.swing.*;

public class Demineur extends JInternalFrame
{
    private int nbLignes, nbMines;
    private PlateauDemineur grille;
    private JPanel panneauChoix;
    private JInternalFrame fenetreChoix;
    
    public static void main(String[] args) 
    {   
        int nbMines;
        System.out.println("Rentrez le nombre de lignes de la grille : ");
        Scanner scan1 = new Scanner(System.in);
        int nbLignes = scan1.nextInt();
        do 
        {
            System.out.println("Rentrez le nombre de mines de la grille");
            Scanner scan2 = new Scanner(System.in);
            nbMines = scan2.nextInt(); 
        }
        while (nbMines > nbLignes*nbLignes/2 && nbMines < nbLignes*nbLignes/10);
        PlateauDemineur plateau = new PlateauDemineur(nbLignes, nbMines);
    }
}
