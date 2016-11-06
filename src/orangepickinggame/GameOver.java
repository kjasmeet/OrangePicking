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
import static orangepickinggame.OrangePickingGame.menu;
import static orangepickinggame.OrangePickingGame.play;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author JasmeetKaur
 */
public class GameOver extends BasicGameState {

    TrueTypeFont font;
    Image goToMenu;
    Image restart;

    GameOver(int gameOver) {
    }

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        restart = new Image("Images/button_play-again.png");
        goToMenu = new Image("Images/button_menu.png");
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("Ubuntu-Title.ttf");
            Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont = awtFont.deriveFont(40f); // set font size
            font = new TrueTypeFont(awtFont, false);
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(Instructions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        font.drawString(50, 100, "Game over!!", Color.white);
        font.drawString(50, 150, "Choose your options from below", Color.white);
        int xPos = Mouse.getX();
        int yPos = Mouse.getY();
        font.drawString(100, 400, xPos + " " + yPos, Color.yellow);
        restart.draw(50, 300);
        goToMenu.draw(300, 300);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        int xPos = Mouse.getX();
        int yPos = Mouse.getY();
        if ((xPos > 50 && xPos < 211) && (yPos > 288 && yPos < 328)) {
            if (Mouse.isButtonDown(0)) {
                sbg.enterState(play, new FadeOutTransition(Color.yellow), new FadeInTransition(Color.black));
            }
        }
        
        if ((xPos > 300 && xPos < 404) && (yPos > 288 && yPos < 329)) {
            if (Mouse.isButtonDown(0)) {
                sbg.enterState(menu, new FadeOutTransition(Color.yellow), new FadeInTransition(Color.black));
            }
        }

    }

}
