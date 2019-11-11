/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static package1.WartoscPola.BialaDama;
import static package1.WartoscPola.Bialy;
import static package1.WartoscPola.Ciemne;
import static package1.WartoscPola.CzarnaDama;
import static package1.WartoscPola.Czarny;
import static package1.WartoscPola.Jasne;
import static package1.WartoscPola.Pusty;

/**
 *
 * @author Kompik1
 */
public class PanelGry2Graczy extends JPanel implements MouseListener{

    Point klikniecie = new Point();
    
    int rozmiarPola = 30, iPlanszy, jPlanszy, xPlanszy, yPlanszy, punktyBiale, punktyCzarne;
    
    Plansza plansza = new Plansza();
    int wielkoscPlanszy = plansza.getWymiar();
    boolean bialyGracz = true, czarnyGracz = false, pierwszyWybor = true, drugiWybor = false, koniecGry = false;

    
   
    public PanelGry2Graczy(){
        JPanel panel = new JPanel();
        System.out.println("sdtart");
        //rozmiarPola = this.getHeight()/10;
        this.setBackground(Color.gray);
        //this.setBounds(550,75,100,25);
        //setPreferredSize(new Dimension(800, 800));
        punktyBiale = plansza.puktyBiale;
        punktyCzarne = plansza.puktyCzarne;
        addMouseListener(this);
        
         
    }

    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
	Graphics2D g2d = (Graphics2D) g;
        
       
        for(int i = 0 ; i < wielkoscPlanszy; i++ ){
            
            for(int j = 0 ; j < wielkoscPlanszy; j++){
                g2d.setColor(Color.black);
                Rectangle2D rectangle = new Rectangle2D.Double(
                                    i * rozmiarPola,
                                    j * rozmiarPola,
                                    rozmiarPola,
                                    rozmiarPola
                                    );
                g2d.draw(rectangle);
                    System.out.println(i + j);
//                  Ellipse2D circle = new Ellipse2D.Double(i * rozmiarPola, j * rozmiarPola, rozmiarPola, rozmiarPola);
//                    g2d.draw(circle);  
                if(plansza.zwrocWartosc(i,j) == Bialy){
                    g2d.setColor(Color.white);
                    g2d.fillOval(i * rozmiarPola, j * rozmiarPola, rozmiarPola-2, rozmiarPola-2);

                } 
                else if(plansza.zwrocWartosc(i,j) == Czarny){
                    g2d.setColor(Color.black);
                    g2d.fillOval(i * rozmiarPola, j * rozmiarPola, rozmiarPola-2, rozmiarPola-2);
                }
                else if(plansza.zwrocWartosc(i,j) == CzarnaDama){
                    
                }
                else if(plansza.zwrocWartosc(i,j) == BialaDama){
                    
                }
                else if(plansza.zwrocWartosc(i,j) == Ciemne){
                    g2d.setColor(Color.gray);
                }
                else if(plansza.zwrocWartosc(i,j) == Jasne){
                    g2d.setColor(Color.lightGray);
                    g2d.fillRect(i * rozmiarPola,
                                    j * rozmiarPola,
                                    rozmiarPola,
                                    rozmiarPola);
                }
                
            }
        }
        
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        klikniecie = e.getPoint();
        if(!koniecGry)
        {
            
            if (bialyGracz) {
                if (pierwszyWybor) {
                    iPlanszy = klikniecie.x / rozmiarPola;
                    jPlanszy = klikniecie.y / rozmiarPola;

                    if (plansza.zwrocWartosc(iPlanszy, jPlanszy) == Bialy) {
                        pierwszyWybor = false;
                        drugiWybor = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Wybrano złe pole");
                    }

                } else if (drugiWybor) {
                    xPlanszy = klikniecie.x / rozmiarPola;
                    yPlanszy = klikniecie.y / rozmiarPola;
                    System.out.println(jPlanszy + " " + jPlanszy);
                    System.out.println(xPlanszy + " " + yPlanszy);
                    if (plansza.czyPoprawny(iPlanszy, jPlanszy, xPlanszy, yPlanszy)) {
                        if (czyBicie()) {
                            JOptionPane.showMessageDialog(null, "Wykonano");
                            punktyBiale = plansza.puktyBiale;
                            if(punktyBiale < 12)
                            {                                
                                plansza.setBite(false);
                                drugiWybor = false;
                                pierwszyWybor = true;
                            }
                            else
                            {
                                koniecGry = true;
                                JOptionPane.showMessageDialog(null, "Koniec Gry");
                            }
                        } else {
                            drugiWybor = false;
                            pierwszyWybor = true;
                            bialyGracz = false;
                            czarnyGracz = true;
                            JOptionPane.showMessageDialog(null, "Wykonano");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Wybrano złe pole");
                    }

                }

            } else if (czarnyGracz) {
                if (pierwszyWybor) {
                    iPlanszy = klikniecie.x / rozmiarPola;
                    jPlanszy = klikniecie.y / rozmiarPola;

                    if (plansza.zwrocWartosc(iPlanszy, jPlanszy) == Czarny) {
                        pierwszyWybor = false;
                        drugiWybor = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Wybrano złe pole");
                    }

                } else if (drugiWybor) {
                    xPlanszy = klikniecie.x / rozmiarPola;
                    yPlanszy = klikniecie.y / rozmiarPola;
                    System.out.println(jPlanszy + " " + jPlanszy);
                    System.out.println(xPlanszy + " " + yPlanszy);
                    if (plansza.czyPoprawny(iPlanszy, jPlanszy, xPlanszy, yPlanszy)) {
                        if (czyBicie()) {
                            JOptionPane.showMessageDialog(null, "Wykonano");
                            punktyCzarne = plansza.puktyCzarne;
                            if(punktyCzarne < 12)
                            {                                
                                plansza.setBite(false);
                                drugiWybor = false;
                                pierwszyWybor = true;
                            }
                            else
                            {
                                koniecGry = true;
                                JOptionPane.showMessageDialog(null, "Koniec Gry");
                            }

                        } else {
                            drugiWybor = false;
                            pierwszyWybor = true;
                            bialyGracz = true;
                            czarnyGracz = false;
                            JOptionPane.showMessageDialog(null, "Wykonano");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Wybrano złe pole");
                    }

                }
            }
        }
        
        
        
    }
    
    private void wykonywanieRuchu(){
        
    }
    
    //private void 
    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean czyBicie() {
        return plansza.bite;
    }
    
    public int getPunktyCzarne() {
        return punktyCzarne;
    }

    public int getPunktyBiale() {
        return punktyBiale;
    }
    
    public boolean isBialyGracz() {
        return bialyGracz;
    }
    
    
}
