/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testProject;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * This class will test what happens when player clicks 
 * on something
 * @author JasmeetKaur
 */
public class MouseClickTest extends BasicGame {
    
    /**
     * Declaration of all variables used
     */
    Image clickHere;
    static int widthMouseClick = 800;
    static int heightMouseClick = 600;
    int stating = 0;
    
    public MouseClickTest(String title) {
        super(title);
    }

    /**
     * initialized an image of button
     * @param gc
     * @throws SlickException 
     */
    @Override
    public void init(GameContainer gc) throws SlickException {
        clickHere = new Image("TestImages/button_click-here.png");
    }

    /**
     * when the user clicks on button, state becomes one
     * @param gc
     * @param i
     * @throws SlickException 
     */
    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        int xPos = Mouse.getX();
        int yPos = Mouse.getY();
       
        
        if((xPos>361 && xPos<503) && (yPos>261 && yPos < 297)){
            if(Mouse.isButtonDown(0)){
                 stating = 1;
            }
        }
    }

    /**
     * the x and y value of players mouse movement are shown and when the
     * user clicks on the button, state becomes 1 which shows the string
     * @param gc
     * @param grphcs
     * @throws SlickException 
     */
    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        int xx = Mouse.getX();
        int yy = Mouse.getY();
        grphcs.drawString(xx + " " + yy, 300, 50);
        clickHere.draw(widthMouseClick/2-40, heightMouseClick/2);
        if(stating == 1){
            grphcs.drawString("Hello! You just clicked a button!", 300, 200);
        }
    }
    
    /**
     * Start of the program
     * @param argv 
     */
    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer(new MouseClickTest("Testing click"));
            container.setDisplayMode(widthMouseClick, heightMouseClick, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
    
}
