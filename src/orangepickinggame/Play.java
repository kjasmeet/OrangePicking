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
/* Level 1 - "bare bones" level, will include only basic functionality (increased in later levels)
 Easiest difficulty
 */
public class Play extends InitializeCode {
    
    /* methods are defined, for the most part, in InitializeCode. This extends.*/
    int orangeGoal = 5;
    
    /* constructor for level*/
    public Play(int playState) {
        super();
    }

    @Override
    public int getID() {
        return play;
    }

    /* enters level. sets time limit for this specific instance*/
    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) {
        super.setTime(60000);
        super.score = 0;
        super.x = 0;
        super.y = 0;
        super.xHeight = 0;
        super.yHeight = 0;
    }

    /* initializes level*/
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        super.init(gc, sbg);
        
    }

    /* renders graphics for level*/
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        super.render(gc, sbg, grphcs);
        font.drawString(50, 20, "Level 1 - Pick oranges!" + " " + getHighscore(), Color.yellow);
    
        
    }

    /* updates graphics based on changes*/
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        super.update(gc, sbg, i);
        
        if (orangeGoal <= super.score) {
            state = 1;
            super.setHighscore(super.score);
            sbg.enterState(splashScreen, new FadeOutTransition(Color.decode("#2fc38b")), new FadeInTransition(Color.black));
        }
    }

    /* updates location of sprite based on key presses*/
    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
    }
    
}
