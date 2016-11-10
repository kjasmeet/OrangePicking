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
    
    /* defines an orange object
       Rectangle orangerec is used for collision detection with sprite
       (Defines Rectangle within current coordinate square, hence the mod operator)
       index - index with the larger array defined in InitializeCode
       x and y - coordinates of location on map
    */
    public Orange(int xl, int yl,int in){
        x = xl;
        y = yl;
        orangerec = new Rectangle(x%600,y%600, 25,25);
        index = in;
    }
    
    /* returns Rectangle for collision detection*/
    public Rectangle getRectangle(){
        return orangerec;
    }
    
    /* returns x coordinate*/
    int getXVal(){
        return x;
    }
    
    /* returns y coordinate*/
    int getYVal(){
        return y;
    }
    
    /* returns index*/
    int getIndex(){
        return index;
    }
}
