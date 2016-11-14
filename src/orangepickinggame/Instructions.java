/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orangepickinggame;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import static orangepickinggame.OrangePickingGame.instruct;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

/**
 *When the player clicks on Instructions button, this screen is presented
 * @author JasmeetKaur
 */
public class Instructions extends BasicGameState {

    Image background;
    Image back;
    TrueTypeFont font;
    public Instructions(int instructions) {
    }

    @Override
    public int getID() {
        return instruct;
    }

    /**
     * This init method initiliazes the background and back images. 
     * It also sets the font for this window.
     * @param gc
     * @param sbg
     * @throws SlickException 
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("Images/patterns.PNG");
        back = new Image("Images/button_back.PNG");
        
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("Ubuntu-Title.ttf");
            Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont = awtFont.deriveFont(25f); // set font size
            font = new TrueTypeFont(awtFont, false);
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(Instructions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * This render method is displayed on the screen. Here, all 
     * the instructions for the game are provided.
     * @param gc
     * @param sbg
     * @param g
     * @throws SlickException 
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(background, 0, 0);
        font.drawString(25, 100, "Press your arrows keys to move up, down, left or right.", Color.black);
        font.drawString(25, 150, "Collect oranges by walking through them", Color.black);
        font.drawString(25, 200, "Meet the goal for the level to pass on to the next level.", Color.black);
        font.drawString(25, 250, "Hit the back button to go to menu!", Color.black);
        g.drawImage(back, 225, 400);
        g.setColor(Color.black);
        int xx = Mouse.getX();
        int yy = Mouse.getY();
        g.drawString(xx + " " + yy, 300, 50);
    }

    /**
     * This method allows the user to click on the back button to be taken
     * back to the menu screen. 
     * @param gc
     * @param sbg
     * @param i
     * @throws SlickException 
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        int xPos = Mouse.getX();
        int yPos = Mouse.getY();
       
        
        if((xPos>225 && xPos<322) && (yPos>188 && yPos < 229)){
            if(Mouse.isButtonDown(0)){
                sbg.enterState(0);
            }
        }
    }
    
}
