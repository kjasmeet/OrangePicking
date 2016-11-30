package orangepickinggame;

import java.util.ArrayList;
import static orangepickinggame.OrangePickingGame.congrats;
import static orangepickinggame.OrangePickingGame.playLevelThree;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author JasmeetKaur
 */

/* Level 3 - will eventually contain a version of initial level in which the player cannot retrace steps
 Increased difficulty in orange collection goal and time limit
 */
public class PlayLevelThree extends InitializeCode {
    /* methods are defined, for the most part, in InitializeCode. This extends.*/
    int orangeGoal = 10;
    
    ArrayList<Rectangle> rects = new ArrayList<>();
    int xrec = 0, yrec = 0;

    /* constructor for level*/
    public PlayLevelThree(int playState) {
        super();
    }

    @Override
    public int getID() {
        return playLevelThree;

    }

    /* enter level, sets time limit*/
    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) {
        super.setTime(30000);
        super.score = 0;
        super.x = 0;
        super.y = 0;
        super.xHeight = 0;
        super.yHeight = 0;
    }

    /* initialize level*/
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        super.init(gc, sbg);
    }

    /* render graphics*/
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        super.render(gc, sbg, grphcs);
        font.drawString(50, 20, "Level 3 - Watch where you step!" + xrec/1000 + " " + yrec/1000, Color.yellow);
        
        for(Rectangle rect: rects){
            grphcs.setColor(Color.gray);
            grphcs.fill(rect);
        }
    }

    /* update graphics*/
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        super.update(gc, sbg, i);
        
        if(super.x >=0 && super.y >= 0){
            rects.add(new Rectangle(super.x, super.y, 20, 20));
        }
        
        if (orangeGoal <= super.score) {
            sbg.enterState(congrats, new FadeOutTransition(Color.decode("#2fc38b")), new FadeInTransition(Color.black));

        }

    }

    /* change location of sprite based on key presses*/
    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
    }
}
