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
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import static orangepickinggame.OrangePickingGame.play;
import static orangepickinggame.OrangePickingGame.playLevelThree;
import static orangepickinggame.OrangePickingGame.playLevelTwo;
import static orangepickinggame.OrangePickingGame.state;
import static orangepickinggame.OrangePickingGame.splashScreen;
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
public class SplashScreen extends BasicGameState {

    TrueTypeFont font;
    double time = 0;
    Image slashBackground;

    public SplashScreen(int id) {

    }

    @Override
    public int getID() {
        return splashScreen;
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) {
        time = 5000;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        slashBackground = new Image("Images/slashBackground.png");
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
        slashBackground.draw(0, 0);
        double calculateTime = time / 1000;
        DecimalFormat df = new DecimalFormat("#");
        Color purple = Color.decode("#8A2BE2");
        font.drawString(50, 100, "Goal: ", purple);
        
        font.drawString(200, 300, df.format(calculateTime), Color.red);
        if (state == 1) {
            font.drawString(50, 50, "Congratulations!", purple);
            font.drawString(50, 150, "Collect 7 oranges in 45 seconds!", purple);
            font.drawString(50, 200, "Level 2 starts in", purple);
        } else if (state == 2) {
            font.drawString(50, 50, "Congratulations!", purple);
            font.drawString(50, 150, "Collect 10 oranges in 30 seconds!", purple);
            font.drawString(50, 200, "Level 3 starts in", purple);
        } else if (state == 0) {
            font.drawString(50, 150, "Collect 5 oranges in 60 seconds!", purple);
            font.drawString(50, 200, "Level 1 starts in", purple);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        time -= i;

        if (time <= 0) {
            if (state == 1) {
                sbg.enterState(playLevelTwo, new FadeOutTransition(Color.decode("#2fc38b")), new FadeInTransition(Color.black));

            } else if (state == 2) {
                sbg.enterState(playLevelThree, new FadeOutTransition(Color.decode("#2fc38b")), new FadeInTransition(Color.black));

            } else if (state == 0) {
                sbg.enterState(play, new FadeOutTransition(Color.decode("#2fc38b")), new FadeInTransition(Color.black));

            }
        }
    }

}
