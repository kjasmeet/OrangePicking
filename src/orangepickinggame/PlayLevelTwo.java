package orangepickinggame;

import static orangepickinggame.OrangePickingGame.playLevelTwo;
import static orangepickinggame.OrangePickingGame.splashScreen;
import static orangepickinggame.OrangePickingGame.state;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author JasmeetKaur
 */
/* Level 2 - will eventually contain version of level in which enemies can kill you. Will include 3 lives
   Increased difficulty in orange collection goal and time limit
*/

public class PlayLevelTwo extends InitializeCode {
/* methods are defined, for the most part, in InitializeCode. This extends.*/

    int orangeGoal = 7;

    /* constructor for level*/
    public PlayLevelTwo(int playState) {
        super();
    }

    /* enter level, sets time limit*/
    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) {
        super.setTime(45000);
        super.score = 0;
        super.x = 0; super.y = 0;
    }

    @Override
    public int getID() {
        return playLevelTwo;
    }

    /*initialize level*/
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        super.init(gc, sbg);
    }

    /* render graphics for level*/
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        super.render(gc, sbg, grphcs);
        font.drawString(50, 20, "Level 2 - Avoid the enemies!", Color.yellow);

    }

    /* update graphics*/
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        super.update(gc, sbg, i);
        if (orangeGoal <= super.score) {
            state = 2;
            sbg.enterState(splashScreen, new FadeOutTransition(Color.decode("#2fc38b")), new FadeInTransition(Color.black));

        }
    }

    /* change location of sprite based on key presses*/
    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
    }
}
