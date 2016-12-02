/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package orangepickinggame;

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
    int front;
    int upAndDown;
    int dr;
    
    public Enemy(int xl, int yl, int xbox, int ybox, int ind, Animation image){
        enemyrec = new Rectangle(xl,yl,25,25);
        x = xl;
        y = yl;
        front += x + 70;
        upAndDown += y + 70;
        index = ind;
        BoxX = xbox;
        BoxY = ybox;
        anim = image;
    }
    
    public int getFront(){
        return front;
    }
    
    public int getUpAndDown(){
        return upAndDown;
    }
    
    public int getXVal(){
        return x;
    }
    
    public int getYVal(){
        return y;
    }
    
    public void setXVal(int lx){
        x = x + lx;
//        if(x>600){
//            x = x-600;
//            BoxX++;
//        }
//        else if(x<0){
//            x = x+600;
//            BoxX--;
//        }
    }
    
    public void setYVal(int ly){
        y = y+ ly;
        if(y>600){
            y = y-600;
            BoxY++;
        }
        if(y<0){
            y = y+600;
            BoxY--;
        }
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
    
    public void updateX(int plusX){
        x += plusX;
        enemyrec.setX(x);
    }
    
    public void updateY(int plusY){
        y += plusY;
        enemyrec.setY(y);
    }
    
    public void updateXandY(int updatex, int updatey){
        x += updatex;
        y += updatey;
        enemyrec.setX(x);
        enemyrec.setY(y);
    }
    
    public Animation getAnim(){
        return anim;
    }
}
