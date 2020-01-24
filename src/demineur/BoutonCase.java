package demineur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;


public class BoutonCase extends JButton implements MouseListener {
    
    private int nbMinesVoisines;
    private boolean pointInterrogation;
    private boolean caseMinee;
    private boolean revelee;
    private int lin, col;
    
    public BoutonCase(int lin, int col,
            boolean[][] casesMinees, int[][] nbBombesVoisines) {
        super();
        this.lin = lin;
        this.col = col;
        this.revelee = false;
        this.caseMinee = casesMinees[lin][col];
        this.nbMinesVoisines = nbBombesVoisines[lin][col];
        this.pointInterrogation = false;
        ImageIcon imageMine = new ImageIcon("mine.jpg");
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        BoutonCase caseCliquee = (BoutonCase) e.getSource();
//        if (e.getButton() == MouseEvent.BUTTON1) {
//            if (!caseCliquee.revelee) {
//                if (caseCliquee.caseMinee) {
//                    this.setIcon(this.imageMine);
//                }
//                else {
//                    this.setText("" + this.nbMinesVoisines);
//                }
//            }
//        }
//        else {
//            if (e.getButton() == MouseEvent.BUTTON3 && !caseCliquee.revelee) {
//                if (this.pointInterrogation) {
//                    this.setText("?");
//                }
//                else {
//                    this.setText("");
//                }  
//                this.pointInterrogation = ! this.pointInterrogation;
//            }
//        }
//        validate();
//        revalidate();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        BoutonCase caseCliquee = (BoutonCase) e.getSource();
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (!caseCliquee.revelee) {
                if (caseCliquee.caseMinee) {
                    this.setText("X");
                }
                else {
                    this.setText("" + this.nbMinesVoisines);
                }
            }
        }
        else {
            if (e.getButton() == MouseEvent.BUTTON3 && !caseCliquee.revelee) {
                if (this.pointInterrogation) {
                    this.setText("?");
                }
                else {
                    this.setText("");
                }  
                this.pointInterrogation = ! this.pointInterrogation;
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
