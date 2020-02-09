package demineur;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class BoutonCase extends JButton implements MouseListener {

    private int nbMinesVoisines;
    private boolean pointInterrogation;
    private boolean flagged;
    private boolean caseMinee;
    private boolean revelee;
    private boolean perdu;
    private int lin, col;
    private ImageIcon mine = new ImageIcon(this.getClass().getResource("mine.gif"));
    private ImageIcon flag = new ImageIcon(this.getClass().getResource("flag.gif"));
    private FenetreAffichage fenetre;

    public BoutonCase(int lin, int col,
            boolean[][] casesMinees, int[][] nbBombesVoisines, FenetreAffichage fenetre) {
        super();
        this.lin = lin;
        this.col = col;
        this.revelee = false;
        this.caseMinee = casesMinees[lin][col];
        this.nbMinesVoisines = nbBombesVoisines[lin][col];
        this.pointInterrogation = false;
        this.flagged = false;
        this.perdu = false;
        this.fenetre = fenetre;
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        BoutonCase caseCliquee = (BoutonCase) e.getSource();

        if (e.getButton() == MouseEvent.BUTTON1) {
            if (!this.revelee && !this.flagged) {
                if (this.caseMinee) {
                    Image img = mine.getImage();
                    Image newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                    mine = new ImageIcon(newimg);
                    this.setIcon(mine);
                    this.fenetre.afficheMessagePerdu();
                } else {
                    this.setText("" + this.nbMinesVoisines);
                }
                caseCliquee.revelee = true;
            }
        } else {
            if (e.getButton() == MouseEvent.BUTTON3 && !this.revelee) {
                if (!this.pointInterrogation && !this.flagged) {
                    Image img = flag.getImage();
                    Image newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                    flag = new ImageIcon(newimg);
                    this.setIcon(flag);
                    this.flagged = true;
                } else if (this.flagged) {
                    this.setIcon(null);
                    this.setText("?");
                    this.pointInterrogation = true;
                    this.flagged = false;
                } else if (this.pointInterrogation) {
                    this.setText("");
                    this.pointInterrogation = false;
                }

            }
        }

        validate();
        revalidate();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
