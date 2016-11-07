package orangepickinggame;

import static orangepickinggame.OrangePickingGame.play;
import static orangepickinggame.OrangePickingGame.state;
import static orangepickinggame.OrangePickingGame.splashScreen;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

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
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        super.update(gc, sbg, i);
        
        if(orangeGoal <= super.score){
            state = 1;
            sbg.enterState(splashScreen, new FadeOutTransition(Color.decode("#2fc38b")), new FadeInTransition(Color.black));
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
    }
    

}
