package orangepickinggame;

import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import static orangepickinggame.OrangePickingGame.play;
import static orangepickinggame.OrangePickingGame.playLevelTwo;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author JasmeetKaur
 */
public class Play extends InitializeCode {
    
    int orangeGoal = 5; 
    
    public Play(int playState) {
        super();
    }

    @Override
    public int getID() {
        return play;
    }
    
    @Override
    public void enter(GameContainer gc, StateBasedGame sbg){
        super.setTime(60000);
        super.score = 0;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        super.init(gc, sbg);

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        super.render(gc, sbg, grphcs);
        font.drawString(50, 20, "Level 1 - Pick oranges!", Color.yellow);
        grphcs.setColor(Color.yellow);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        super.update(gc, sbg, i);
        
        if(super.getTime() <= 0){
            sbg.enterState(playLevelTwo);
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
    }
    

}
