/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orangepickinggame;

import static orangepickinggame.InitializeCode.highscore;
import static orangepickinggame.OrangePickingGame.congrats;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This screen presents when the player completes all 3 levels
 * @author JasmeetKaur
 */
public class Congratulations extends BasicGameState {

    Congratulations(int congrats) {
        
    }

    @Override
    public int getID() {
        return congrats;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    /**
     * render method shows the high score to the user
     * @param gc
     * @param sbg
     * @param grphcs
     * @throws SlickException 
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        grphcs.drawString("Congratutions! You won!!", 100, 100);
        grphcs.drawString("Your high score was: " + highscore, 100, 150);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    }

    
    
}
