/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;


import javax.swing.JOptionPane;
import static package1.WartoscPola.BialaDama;
import static package1.WartoscPola.Bialy;
import static package1.WartoscPola.CzarnaDama;
import static package1.WartoscPola.Czarny;
import static package1.WartoscPola.Jasne;
import static package1.WartoscPola.Pusty;

/**
 *
 * @author Kompik1
 */
public class Plansza {
    
    static int wymiar = 8;    
    int puktyCzarne = 0, puktyBiale = 0;
    boolean bite = false;

   
    
    

   
    WartoscPola [][] tab = new WartoscPola[wymiar][wymiar];
    
    Plansza(){
        for(int i = 0 ; i < wymiar; i++)
        {
            for(int j = 0 ; j < wymiar; j++)
            {
                if( j <= 2  && (i+j)%2==1)
                {
                    tab[i][j] = Bialy;
                }
                if(j >= 5 && j <= 7  && (i+j)%2==1)
                {
                    tab[i][j] = Czarny;
                }
                if((i+j)%2==0)
                    tab[i][j] = Jasne;
                if(j >= 3 && j <= 4 &&  (i+j)%2==1)
                    tab[i][j] = Pusty;
            }
        }
    }
    
    public static int getWymiar() {
        return wymiar;
    }
    
    public WartoscPola zwrocWartosc(int i, int j){
        return tab[i][j];
    }
    
    public boolean czyPoprawny(int i, int j, int x , int y){
        if(tab[x][y] != Pusty)
        {
            return false;
        }
        else
        {
            if(tab[i][j] == Bialy)
            {
                if((x == i+1 && y == j+1) || (x == i-1 && y == j+1))
                {
                    przesun(i,j,x,y);
                    return true;
                }
                else if((x == i+2 && y == j+2))
                {
                    if(zwrocWartosc(i+1,j+1) == Czarny)
                    {
                        czyByloBicie(Bialy,i,j,i+1,j+1,x,y);
                        return true;
                    }
                }
                else if((x == i-2 && y == j+2))
                {
                    if(zwrocWartosc(i-1,j+1) == Czarny)
                    {
                        czyByloBicie(Bialy,i,j,i-1,j+1,x,y);
                        return true;
                    }
                }
            
            }
            if(tab[i][j] == Czarny)
            {
                if((x == i-1 && y == j-1) || (x == i+1 && y == j-1))
                {
                    przesun(i,j,x,y);
                    return true;
                }
                 else if((x == i+2 && y == j-2))
                {
                    if(zwrocWartosc(i+1,j-1) == Bialy)
                    {
                        czyByloBicie(Czarny,i,j,i+1,j-1,x,y);
                        return true;
                    }
                }
                else if((x == i-2 && y == j-2))
                {
                    if(zwrocWartosc(i-1,j-1) == Bialy)
                    {
                        czyByloBicie(Czarny,i,j,i-1,j-1,x,y);
                        return true;
                    }
                }
            
            }
            else
                return false;
        }
        return true;
    }
    
    private void przesun(int i,int j, int x, int y)
    {
        if(zwrocWartosc(i,j) == Bialy && j == 7)
        {
            tab[x][y] = BialaDama;
            tab[i][j] = Pusty;
            JOptionPane.showMessageDialog(null, "Biali zdobywaja dame");
        }
        else if(zwrocWartosc(i,j) == Czarny && j == 0)
        {
            tab[x][y] = CzarnaDama;
            tab[i][j] = Pusty;
            JOptionPane.showMessageDialog(null, "Czarni zdobywają dame");
        }
        else
        {
            tab[x][y] = tab[i][j];
            tab[i][j] = Pusty;    
        }
            
    }
    
    private boolean czyByloBicie(WartoscPola gracz, int i, int j , int bi, int bj, int x, int y )
    {
        bite = true;
        
        if(gracz == Bialy)
        {
           puktyBiale++;
           tab[i][j] = Pusty;
           tab[bi][bj] = Bialy;
           przesun(bi,bj,x,y);
           JOptionPane.showMessageDialog(null, "Bicie");
            
        }
        else if(gracz == Czarny)
        {
           puktyCzarne++;
           tab[i][j] = Pusty;
           tab[bi][bj] = Czarny;
           przesun(bi,bj,x,y); 
           JOptionPane.showMessageDialog(null, "Bicie");
        }
        return true;
    }
    
    public int getPuktyCzarne() {
        return puktyCzarne;
    }

    public int getPuktyBiale() {
        return puktyBiale;
    }
    
     public void setBite(boolean bite) {
        this.bite = bite;
    }

    public boolean isBite() {
        return bite;
    }

    
}
