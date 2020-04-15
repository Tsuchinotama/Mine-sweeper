package demineur;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Demineur implements ChangeListener, ActionListener {

    private JFrame frameChoix;

    private JPanel panneauChoixTailleGrille;
    private JLabel texteChoixNbLignes;
    private SpinnerModel nbLignesModelSpinner;
    private JSpinner spinnerChoixNbLignes;
    private int nbLignes = 10;

    private JPanel panneauChoixNbMines;
    private JLabel texteChoixNbMines;
    private SpinnerModel nbMinesModelSpinner;
    private JSpinner spinnerChoixNbMines;
    private int nbMines;

    private JPanel panneauValider;
    private JButton jButtonValider;
    
    private PlateauDemineur plateau;

    public void start() {

        frameChoix = new JFrame("Choix du mode de jeu");
        frameChoix.setLayout(new BoxLayout(frameChoix.getContentPane(), BoxLayout.Y_AXIS));
        frameChoix.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panneauChoixTailleGrille = new JPanel();
        panneauChoixTailleGrille.setLayout(new FlowLayout(FlowLayout.LEFT));

        texteChoixNbLignes = new JLabel("Choisissez le nombre de lignes de votre grille");
        panneauChoixTailleGrille.add(texteChoixNbLignes);

        nbLignesModelSpinner = new SpinnerNumberModel(10, 5, 25, 1);
        spinnerChoixNbLignes = new JSpinner(nbLignesModelSpinner);
        spinnerChoixNbLignes.addChangeListener(this);
        nbLignes = (int) spinnerChoixNbLignes.getValue();
        texteChoixNbLignes.setLabelFor(spinnerChoixNbLignes);
        panneauChoixTailleGrille.add(spinnerChoixNbLignes);

        frameChoix.add(panneauChoixTailleGrille);

        panneauChoixNbMines = new JPanel();
        panneauChoixNbMines.setLayout(new FlowLayout(FlowLayout.LEFT));

        texteChoixNbMines = new JLabel("Choisissez le nombre de mines de votre grille (entre " + nbLignes + " et "
            + (int) (nbLignes * nbLignes / 2) + " ) : ");
        panneauChoixNbMines.add(texteChoixNbMines);

        nbMinesModelSpinner = new SpinnerNumberModel(nbLignes, nbLignes, (int) (nbLignes * nbLignes / 2), 1);
        spinnerChoixNbMines = new JSpinner(nbMinesModelSpinner);
        spinnerChoixNbMines.addChangeListener(this);
        texteChoixNbMines.setLabelFor(spinnerChoixNbMines);
        panneauChoixNbMines.add(spinnerChoixNbMines);

        frameChoix.add(panneauChoixNbMines);

        panneauValider = new JPanel();
        jButtonValider = new JButton("Valider");
        jButtonValider.addActionListener(this);
        panneauValider.add(jButtonValider);
        frameChoix.add(panneauValider);
        
        frameChoix.pack();
        frameChoix.setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource().equals(spinnerChoixNbLignes)) {
            nbLignes = (int) spinnerChoixNbLignes.getValue();

            texteChoixNbMines.setText("Choisissez le nombre de mines de votre grille (entre " + nbLignes + " et "
            + (int) (nbLignes * nbLignes / 2) + " ) : ");
            
            nbMinesModelSpinner = new SpinnerNumberModel(nbLignes, nbLignes, (int) (nbLignes * nbLignes / 2), 1);
            spinnerChoixNbMines.setModel(nbMinesModelSpinner);
        }
        frameChoix.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButtonValider) {
            nbLignes = (int) spinnerChoixNbLignes.getValue();
            nbMines = (int) spinnerChoixNbMines.getValue();
            plateau = new PlateauDemineur(nbLignes, nbMines);
        }
    }
}
