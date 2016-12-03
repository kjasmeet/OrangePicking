/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package orangepickinggame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Rectangle;

/**
 * This class sets up the enemy rectangle and all 
 * the other appropriate fields 
 * @author Alina
 */
public class Enemy {
    
    //initialization of all variables
    private Rectangle enemyrec;
    int x, y, BoxX, BoxY, index;
    private Animation anim;
    int front;
    int upAndDown;
    int dr;
    
    /**
     * constructor of class; sets up all the variables 
     * @param xl
     * @param yl
     * @param xbox
     * @param ybox
     * @param ind
     * @param image 
     */
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
    
    /**
     * get 70 more than the x value
     * @return front
     */
    public int getFront(){
        return front;
    }
    
    /**
     * get 70 more than the y value
     * @return upAndDown
     */
    public int getUpAndDown(){
        return upAndDown;
    }
    
    /**
     * get the x value
     * @return x
     */
    public int getXVal(){
        return x;
    }
    
    /**
     * get the y value
     * @return y
     */
    public int getYVal(){
        return y;
    }
    
    /**
     * get the enemy rectangle
     * @return enemyrec
     */
    public Rectangle getRectangle(){
        return enemyrec;
    }
    
    /**
     * get the x position box
     * @return BoxX
     */
    public int getXBox(){
        return BoxX;
    }
    
    /**
     * get the y position box
     * @return BoxY
     */
    public int getYBox(){
        return BoxY;
    }
    
    /**
     * makes the horizontal movement of enemies possible; adds
     * the x value with the one in parameter
     * @param plusX 
     */
    public void updateX(int plusX){
        x += plusX;
        enemyrec.setX(x);
    }
    
    /**
     * makes the vertical movement possible
     * @param plusY 
     */
    public void updateY(int plusY){
        y += plusY;
        enemyrec.setY(y);
    }
    
    /**
     * makes the diagonal movement possible
     * @param updatex
     * @param updatey 
     */
    public void updateXandY(int updatex, int updatey){
        x += updatex;
        y += updatey;
        enemyrec.setX(x);
        enemyrec.setY(y);
    }
    
    /**
     * get animation
     * @return 
     */
    public Animation getAnim(){
        return anim;
    }
}
