/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package orangepickinggame;

import javax.swing.text.html.parser.Entity;
import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Alina
 */
public class Enemy {
    private Rectangle enemyrec;
    int x, y, BoxX, BoxY, index;
    private Animation anim;
    int dr;
    
    public Enemy(int xl, int yl, int xbox, int ybox, int ind, Animation image){
        enemyrec = new Rectangle(xl,yl,25,25);
        x = xl;
        y = yl;
        index = ind;
        BoxX = xbox;
        BoxY = ybox;
        anim = image;
    }
    
    public int getXVal(){
        return x;
    }
    
    public int getYVal(){
        return y;
    }
    
    public Rectangle getRectangle(){
        return enemyrec;
    }
    
    public int getXBox(){
        return BoxX;
    }
    
    public int getYBox(){
        return BoxY;
    }
    
    public void updateRectangle(int plusX, int plusY){
        x +=plusX;
        //y +=plusY;
        if(x>=580){
            BoxX++;
            x-=580;
        }
        if(y>=580){
            BoxY++;
            y-=580;
        }
        if(BoxX>4){
            BoxX=0;
            x = 20;
        }
//        if(BoxY>4){
//            BoxY=0;
//            y = 20;
//        }
        enemyrec.setX(x);
        enemyrec.setY(y);
    }
    
    public Animation getAnim(){
        return anim;
    }
}
