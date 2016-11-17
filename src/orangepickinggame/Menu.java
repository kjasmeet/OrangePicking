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
import static orangepickinggame.OrangePickingGame.menu;
import static orangepickinggame.OrangePickingGame.state;
import static orangepickinggame.OrangePickingGame.splashScreen;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

/**
 * The first screen presented to the user.
 * @author JasmeetKaur
 */
public class Menu extends BasicGameState {

    Image image;
    Image playImage;
    Image instruction_image;
    TrueTypeFont font;
    Music music;

    public Menu(int menuState) {

    }

    @Override
    public int getID() {
        return menu;
    }

    /**
     * This method initializes all the images, music, and font.
     * @param gc
     * @param sbg
     * @throws SlickException 
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        image = new Image("Images/gameBackground.PNG");
        playImage = new Image("Images/button_play.png");
        instruction_image = new Image("Images/button_instructions.png");
        /*music = new Music("res/menu.wav");
        music.loop();*/
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("Ubuntu-Title.ttf");
            Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont = awtFont.deriveFont(40f); // set font size
            font = new TrueTypeFont(awtFont, false);
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(Instructions.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method displays everything on the screen. 
     * @param gc
     * @param sbg
     * @param g
     * @throws SlickException 
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(image, 0, 0);
        
        font.drawString(50, 50, "Welcome to Orange Picking!", Color.yellow);
        playImage.draw(50, 150);
        instruction_image.draw(50, 250);
        
    }
    
    /**
     * The input of user click is sent to the goToNextState method.
     * @param gc
     * @param sbg
     * @param i
     * @throws SlickException 
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        int xPos = Mouse.getX();
        int yPos = Mouse.getY();
        
        sbg.enterState(goToNextState(xPos, yPos));
        
    }

    /**
     * this method decides what state to be returned based on user's click.
     * @param xPos
     * @param yPos
     * @return 
     */
    public int goToNextState(int xPos, int yPos) {
        int stateToBeReturned = 0;
        
        /*user clicked play button*/
        if ((xPos > 50 && xPos < 150) && (yPos > 439 && yPos < 479)) {
            if (Mouse.isButtonDown(0)) {
                state = 0;
                stateToBeReturned = splashScreen;

            }
        }

        /*user clicked instruction button*/
        if ((xPos > 50 && xPos < 372) && (yPos > 339 && yPos < 378)) {
            if (Mouse.isButtonDown(0)) {
                stateToBeReturned = instruct;

            }
        }
        return stateToBeReturned;
    }
}
