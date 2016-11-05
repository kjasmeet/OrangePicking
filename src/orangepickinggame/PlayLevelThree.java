package orangepickinggame;

import static orangepickinggame.OrangePickingGame.play;
import static orangepickinggame.OrangePickingGame.playLevelTwo;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author JasmeetKaur
 */
public class PlayLevelThree extends InitializeCode {
    int orangeGoal = 10; 
    
    public PlayLevelThree(int playState) {
        super();
        super.setTime(30000);
    }

    @Override
    public int getID() {
        return play;
        
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        super.init(gc, sbg);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        super.render(gc, sbg, grphcs);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        super.update(gc, sbg, i);
        
        
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
    }
}
