/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package orangepickinggame;
import java.util.Random;
import org.newdawn.slick.geom.*;
/**
 *
 * @author Alina
 */
public class Orange {
    private Rectangle orangerec;
    int x, y, index;
    
    public Orange(int xl, int yl,int in){
        x = xl;
        y = xl;
        orangerec = new Rectangle(x,y, 25,25);
        index = in;
    }
    
    public Rectangle getRectangle(){
        return orangerec;
    }
    
    int getXVal(){
        return x;
    }
    
    int getYVal(){
        return y;
    }
    
    int getIndex(){
        return index;
    }
}
