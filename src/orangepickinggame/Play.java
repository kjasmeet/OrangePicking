package orangepickinggame;

import static orangepickinggame.OrangePickingGame.play;
import static orangepickinggame.OrangePickingGame.playLevelTwo;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author JasmeetKaur
 */
public class Play extends InitializeCode {
    
    int orangeGoal = 10; 
    
    public Play(int playState) {
        super();
    }

    @Override
    public int getID() {
        return play;
    }
    
    @Override
    public void enter(GameContainer gc, StateBasedGame sbg){
        super.setTime(80000);
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
        
        if(orangeGoal <= super.score){
            sbg.enterState(playLevelTwo);
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
    }
    

}
